package uz.pdp.warehouse.mapper;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.warehouse.model.dto.order.OrderCreateDto;
import uz.pdp.warehouse.model.dto.order.OrderDto;
import uz.pdp.warehouse.model.dto.order.OrderUpdateDto;
import uz.pdp.warehouse.model.dto.orderItem.OrderItemDto; // Assuming this DTO exists
import uz.pdp.warehouse.model.entity.Order;
import uz.pdp.warehouse.model.entity.Customer; // Required to set the customer ID
import uz.pdp.warehouse.model.enums.OrderStatus; // Required for status
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final OrderItemMapper orderItemMapper;

    public Order fromCreateDto(OrderCreateDto dto) {
        Order order = new Order();
        order.setStatus(dto.getStatus());
        return order;
    }


    public OrderDto toDto(Order order) {
        List<OrderItemDto> itemDtos = order.getOrderItems().stream()
                .map(orderItemMapper::toDto)
                .toList();

        OrderDto dto = new OrderDto();

        dto.setId(order.getId());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setUpdatedAt(order.getUpdatedAt());
        dto.setDeleted(order.isDeleted());

        if (order.getCustomer() != null) {
            dto.setCustomerId(order.getCustomer().getId());
        }

        dto.setStatus(order.getStatus());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setOrderItems(itemDtos);

        return dto;
    }

    public Order fromUpdateDto(OrderUpdateDto dto, Order order) {
        if (dto.getStatus() != null) {
            order.setStatus(dto.getStatus());
        }
        return order;
    }
}