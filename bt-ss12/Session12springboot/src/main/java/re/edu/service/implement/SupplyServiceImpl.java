package re.edu.service.implement;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import re.edu.exception.BadRequestException;
import re.edu.exception.ResourceNotFoundException;
import re.edu.mapper.SupplyMapper;
import re.edu.model.dto.request.CreateSupplyRequest;
import re.edu.model.dto.request.InventoryRequest;
import re.edu.model.dto.response.DailyExportResponse;
import re.edu.model.dto.response.SupplyResponse;
import re.edu.model.dto.response.TopExportResponse;
import re.edu.model.entity.Supply;
import re.edu.model.entity.Transaction;
import re.edu.model.entity.TransactionType;
import re.edu.repository.SupplyRepository;
import re.edu.repository.TransactionRepository;
import re.edu.service.SupplyService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SupplyServiceImpl implements SupplyService {

    private static final Logger historyLogger = LoggerFactory.getLogger("HISTORY_LOGGER");

    private final SupplyRepository supplyRepository;
    private final TransactionRepository transactionRepository;
    private final SupplyMapper supplyMapper;

    @Override
    @Transactional
    public SupplyResponse createSupply(CreateSupplyRequest request) {
        Supply supply = supplyMapper.toEntity(request);
        supply.setQuantity(0);
        supply.setIsDeleted(false);

        Supply saved = supplyRepository.save(supply);
        log.info("Đã tạo mới vật tư: [{}] với ID: [{}]", saved.getName(), saved.getId());

        return supplyMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public SupplyResponse updateSupply(Long id, Map<String, Object> rawBody) {
        Set<String> forbiddenFields = Set.of("id", "quantity");
        for (String forbidden : forbiddenFields) {
            if (rawBody.containsKey(forbidden)) {
                log.warn("Client cố tình gửi field bị cấm '{}' trong request cập nhật vật tư ID: {}",
                        forbidden, id);
                throw new BadRequestException(
                        "Không được phép cập nhật trường '" + forbidden
                                + "'. Chỉ cho phép: name, specification, provider.");
            }
        }

        Supply supply = supplyRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy vật tư với ID: " + id));

        if (rawBody.containsKey("name") && rawBody.get("name") != null) {
            supply.setName(rawBody.get("name").toString());
        }
        if (rawBody.containsKey("specification")) {
            supply.setSpecification(rawBody.get("specification") != null
                    ? rawBody.get("specification").toString() : null);
        }
        if (rawBody.containsKey("provider")) {
            supply.setProvider(rawBody.get("provider") != null
                    ? rawBody.get("provider").toString() : null);
        }

        Supply updated = supplyRepository.save(supply);
        log.info("Đã cập nhật vật tư ID: [{}]", updated.getId());

        return supplyMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void deleteSupply(Long id) {
        Supply supply = supplyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy vật tư với ID: " + id));

        if (Boolean.TRUE.equals(supply.getIsDeleted())) {
            throw new ResourceNotFoundException(
                    "Vật tư với ID: " + id + " đã bị xóa trước đó.");
        }

        supply.setIsDeleted(true);
        supplyRepository.save(supply);
        log.info("Đã xóa mềm vật tư ID: [{}]", id);
    }

    @Override
    public List<SupplyResponse> getAllSupplies() {
        List<Supply> supplies = supplyRepository.findAllByIsDeletedFalse();
        log.debug("Truy vấn danh sách vật tư: tìm thấy {} bản ghi (isDeleted = false)",
                supplies.size());

        return supplies.stream()
                .map(supplyMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<SupplyResponse> searchSuppliesByName(String name) {
        List<Supply> supplies =
                supplyRepository.findByNameContainingIgnoreCaseAndIsDeletedFalse(name);

        if (supplies.isEmpty()) {
            log.info("Không tìm thấy vật tư nào khớp với từ khóa: [{}]", name);
        }

        return supplies.stream()
                .map(supplyMapper::toResponse)
                .collect(Collectors.toList());
    }
    @Override
    @Transactional
    public SupplyResponse exportSupply(Long id, InventoryRequest request) {
        Supply supply = supplyRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy vật tư với ID: " + id));

        int currentQty = supply.getQuantity();
        int amount = request.getAmount();

        if (currentQty < amount) {
            log.error("Thất bại khi xuất kho ID [{}]: Yêu cầu [{}], hiện có [{}]",
                    id, amount, currentQty);
            throw new BadRequestException("Số lượng tồn kho không đủ để xuất");
        }

        supply.setQuantity(currentQty - amount);
        Supply updated = supplyRepository.save(supply);

        Transaction tx = new Transaction();
        tx.setSupply(updated);
        tx.setType(TransactionType.EXPORT);
        tx.setAmount(amount);
        transactionRepository.save(tx);

        log.info("Xuất kho thành công vật tư ID: [{}], số lượng xuất: [{}], tồn còn lại: [{}]",
                id, amount, updated.getQuantity());

        return supplyMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public SupplyResponse importSupply(Long id, InventoryRequest request) {
        Supply supply = supplyRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy vật tư với ID: " + id));

        int oldQty = supply.getQuantity();
        int amount = request.getAmount();
        supply.setQuantity(oldQty + amount);

        Supply updated = supplyRepository.save(supply);

        Transaction tx = new Transaction();
        tx.setSupply(updated);
        tx.setType(TransactionType.IMPORT);
        tx.setAmount(amount);
        transactionRepository.save(tx);

        historyLogger.info("Nhập kho ID [{}], số lượng [+{}], tồn cũ [{}]",
                id, amount, oldQty);

        return supplyMapper.toResponse(updated);
    }

    @Override
    public List<DailyExportResponse> getDailyExportStatistics() {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1);

        log.info("Bắt đầu thống kê xuất kho ngày {} lúc {}", LocalDate.now(), LocalDateTime.now());

        List<DailyExportResponse> result = transactionRepository.findDailyExportStatistics(
                TransactionType.EXPORT, startOfDay, endOfDay);

        log.info("Hoàn thành thống kê xuất kho ngày {}: {} loại vật tư lúc {}",
                LocalDate.now(), result.size(), LocalDateTime.now());

        return result;
    }

    @Override
    public TopExportResponse getTopExportSupply() {
        if (!transactionRepository.existsByType(TransactionType.EXPORT)) {
            throw new ResourceNotFoundException("Chưa có dữ liệu giao dịch để thống kê");
        }

        List<TopExportResponse> results =
                transactionRepository.findTopExportSupply(TransactionType.EXPORT);
        return results.get(0);
    }
}
