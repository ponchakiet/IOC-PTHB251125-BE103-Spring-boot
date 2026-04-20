package re.edu.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.model.dto.request.CreateSupplyRequest;
import re.edu.model.dto.request.InventoryRequest;
import re.edu.model.dto.response.ApiResponse;
import re.edu.model.dto.response.DailyExportResponse;
import re.edu.model.dto.response.SupplyResponse;
import re.edu.model.dto.response.TopExportResponse;
import re.edu.service.SupplyService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/supplies")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Medical Supplies", description = "API quản lý vật tư y tế")
public class SupplyController {

    private final SupplyService supplyService;

    @PostMapping
    public ResponseEntity<ApiResponse<SupplyResponse>> createSupply(
            @Valid @RequestBody CreateSupplyRequest request) {

        SupplyResponse data = supplyService.createSupply(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Tạo vật tư thành công", data, HttpStatus.CREATED.value()));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SupplyResponse>> updateSupply(
            @Parameter(description = "ID vật tư cần cập nhật") @PathVariable Long id,
            @RequestBody Map<String, Object> rawBody) {

        SupplyResponse data = supplyService.updateSupply(id, rawBody);
        return ResponseEntity.ok(
                ApiResponse.success("Cập nhật vật tư thành công", data, HttpStatus.OK.value()));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupply(
            @Parameter(description = "ID vật tư cần xóa") @PathVariable Long id) {

        supplyService.deleteSupply(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<SupplyResponse>>> getAllSupplies() {
        List<SupplyResponse> data = supplyService.getAllSupplies();
        return ResponseEntity.ok(
                ApiResponse.success("Lấy danh sách vật tư thành công", data, HttpStatus.OK.value()));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<SupplyResponse>>> searchSupplies(
            @Parameter(description = "Từ khóa tìm kiếm theo tên vật tư")
            @RequestParam("name") String name) {

        List<SupplyResponse> data = supplyService.searchSuppliesByName(name);
        return ResponseEntity.ok(
                ApiResponse.success("Kết quả tìm kiếm", data, HttpStatus.OK.value()));
    }

    @PatchMapping("/{id}/export")
    public ResponseEntity<ApiResponse<SupplyResponse>> exportSupply(
            @Parameter(description = "ID vật tư cần xuất kho") @PathVariable Long id,
            @Valid @RequestBody InventoryRequest request) {

        SupplyResponse data = supplyService.exportSupply(id, request);
        return ResponseEntity.ok(
                ApiResponse.success("Xuất kho thành công", data, HttpStatus.OK.value()));
    }

    @PatchMapping("/{id}/import")
    public ResponseEntity<ApiResponse<SupplyResponse>> importSupply(
            @Parameter(description = "ID vật tư cần nhập kho") @PathVariable Long id,
            @Valid @RequestBody InventoryRequest request) {

        SupplyResponse data = supplyService.importSupply(id, request);
        return ResponseEntity.ok(
                ApiResponse.success("Nhập kho thành công", data, HttpStatus.OK.value()));
    }

    @GetMapping("/statistics/daily-export")
    public ResponseEntity<ApiResponse<List<DailyExportResponse>>> getDailyExportStatistics() {
        List<DailyExportResponse> data = supplyService.getDailyExportStatistics();
        return ResponseEntity.ok(
                ApiResponse.success("Thống kê xuất kho trong ngày", data, HttpStatus.OK.value()));
    }

    @GetMapping("/statistics/top-export")
    public ResponseEntity<ApiResponse<TopExportResponse>> getTopExportSupply() {
        TopExportResponse data = supplyService.getTopExportSupply();
        return ResponseEntity.ok(
                ApiResponse.success("Vật tư xuất kho nhiều nhất", data, HttpStatus.OK.value()));
    }
}
