package uz.pdp.warehouse.model.dto.user;


import lombok.Getter;
import lombok.Setter;
import uz.pdp.warehouse.model.enums.Role;


@Getter
@Setter

public class UserCreateDto {
    private String username;
    private String password;
    private Role role = Role.WORKER;
    private String fullName;
    private String phone;
}
