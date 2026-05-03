package re.edu.dto.request;

import lombok.Data;

@Data
public class OrderItemRequest {
    private Long productId;
    private Integer quantity;
}
