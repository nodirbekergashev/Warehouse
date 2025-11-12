package uz.pdp.warehouse.model.dto.customer;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
    private Long id;
    private String fullName;
    private String phone;
    private String email;
    private String address;
    private Boolean enabled;
}