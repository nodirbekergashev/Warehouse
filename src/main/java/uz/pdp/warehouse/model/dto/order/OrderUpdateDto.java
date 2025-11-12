package uz.pdp.warehouse.model.dto.order;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.warehouse.model.enums.OrderStatus;


@Getter
@Setter
public class OrderUpdateDto {
    private Long customerId;
    private OrderStatus status;
}
