package uz.pdp.warehouse.model.dto.user;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.warehouse.model.enums.Role;


@Getter
@Setter
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private Role role;
    private String fullName;
    private String phone;
}
