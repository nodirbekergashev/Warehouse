package uz.pdp.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouse.model.entity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
