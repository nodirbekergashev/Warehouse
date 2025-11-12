package uz.pdp.warehouse.model.dto.supplier;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierDto {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String contactPerson;
}
