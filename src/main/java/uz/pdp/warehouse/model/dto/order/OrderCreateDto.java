package uz.pdp.warehouse.model.dto.order;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.warehouse.model.dto.orderItem.OrderItemCreateDto;
import uz.pdp.warehouse.model.enums.OrderStatus;

import java.util.List;

@Getter
@Setter
public class OrderCreateDto {
    private Long customerId;
    private OrderStatus status;
    private List<OrderItemCreateDto> orderItems;
}
