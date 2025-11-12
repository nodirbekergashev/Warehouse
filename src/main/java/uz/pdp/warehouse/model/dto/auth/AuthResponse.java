package uz.pdp.warehouse.model.dto.auth;

import lombok.Builder;
import lombok.Data;
import uz.pdp.warehouse.model.enums.Role;

import java.util.Date;

@Data
@Builder
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
    private String username;
    private Role role;
    private Date expiresAt;
}