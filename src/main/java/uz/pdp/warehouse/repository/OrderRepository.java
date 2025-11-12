package uz.pdp.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouse.model.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
