package re.edu.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import re.edu.model.dto.request.CreateSupplyRequest;
import re.edu.model.dto.response.SupplyResponse;
import re.edu.model.entity.Supply;

@Component
@RequiredArgsConstructor
public class SupplyMapper {

    private final ModelMapper modelMapper;

    public SupplyResponse toResponse(Supply supply) {
        return modelMapper.map(supply, SupplyResponse.class);
    }
    public Supply toEntity(CreateSupplyRequest request) {
        return modelMapper.map(request, Supply.class);
    }
}
