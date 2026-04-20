package re.edu.service;

import re.edu.model.dto.request.CreateSupplyRequest;
import re.edu.model.dto.request.InventoryRequest;
import re.edu.model.dto.response.DailyExportResponse;
import re.edu.model.dto.response.SupplyResponse;
import re.edu.model.dto.response.TopExportResponse;

import java.util.List;
import java.util.Map;

public interface SupplyService {
    SupplyResponse createSupply(CreateSupplyRequest request);
    SupplyResponse updateSupply(Long id, Map<String, Object> rawBody);
    void deleteSupply(Long id);
    List<SupplyResponse> getAllSupplies();
    List<SupplyResponse> searchSuppliesByName(String name);
    SupplyResponse exportSupply(Long id, InventoryRequest request);
    SupplyResponse importSupply(Long id, InventoryRequest request);
    List<DailyExportResponse> getDailyExportStatistics();
    TopExportResponse getTopExportSupply();

}
