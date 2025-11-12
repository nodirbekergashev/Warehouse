package uz.pdp.warehouse.model.dto.order;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.warehouse.model.dto.orderItem.OrderItemDto;
import uz.pdp.warehouse.model.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class OrderDto {
    private Long id;
    private Long customerId;
    private Instant createdAt;
    private Instant updatedAt;
    private Boolean deleted;
    private OrderStatus status;
    private BigDecimal totalPrice;
    private List<OrderItemDto> orderItems;
}
