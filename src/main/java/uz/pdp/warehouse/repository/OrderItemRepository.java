package uz.pdp.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouse.model.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
