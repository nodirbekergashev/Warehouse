package uz.pdp.warehouse.model.dto.orderItem;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemCreateDto {
    private Long orderId;
    private Long productId;
    private Integer quantity;
    private BigDecimal price;
}
