package uz.pdp.warehouse.repository;

import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouse.model.entity.Supplier;

import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    boolean findByPhone(String phone);

    Optional<Supplier> findByEmail(@Email(message = "Invalid email format") String email);
}
