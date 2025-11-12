package uz.pdp.warehouse.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.warehouse.model.dto.orderItem.OrderItemDto;
import uz.pdp.warehouse.model.entity.OrderItem;

@Component
public class OrderItemMapper {
    public OrderItemDto toDto(OrderItem orderItem) {
        OrderItemDto dto = new OrderItemDto();
        dto.setId(orderItem.getId());
        dto.setQuantity(orderItem.getQuantity());
        dto.setPrice(orderItem.getPrice());
        dto.setProductId(orderItem.getProduct().getId());
        dto.setOrderId(orderItem.getOrder().getId());
        return dto;
    }
}
