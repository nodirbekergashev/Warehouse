package uz.pdp.warehouse.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.warehouse.model.dto.supplier.SupplierCreateDto;
import uz.pdp.warehouse.model.dto.supplier.SupplierDto;
import uz.pdp.warehouse.model.dto.supplier.SupplierUpdateDto;
import uz.pdp.warehouse.model.entity.Supplier;

@Component
public class SupplierMapper {

    public Supplier fromCreateDto(SupplierCreateDto dto) {
        Supplier supplier = new Supplier();
        supplier.setName(dto.getName());
        supplier.setPhone(dto.getPhone());
        supplier.setEmail(dto.getEmail());
        supplier.setAddress(dto.getAddress());
        supplier.setContactPerson(dto.getContactPerson());
        return supplier;
    }

    public Supplier fromUpdateDto(SupplierUpdateDto dto, Supplier supplier) {
        supplier.setName(dto.getName());
        supplier.setPhone(dto.getPhone());
        supplier.setEmail(dto.getEmail());
        supplier.setAddress(dto.getAddress());
        supplier.setContactPerson(dto.getContactPerson());
        return supplier;
    }

    public SupplierDto toDto(Supplier supplier) {
        SupplierDto dto = new SupplierDto();
        dto.setId(supplier.getId());
        dto.setName(supplier.getName());
        dto.setPhone(supplier.getPhone());
        dto.setEmail(supplier.getEmail());
        dto.setAddress(supplier.getAddress());
        dto.setContactPerson(supplier.getContactPerson());
        return dto;
    }
}
