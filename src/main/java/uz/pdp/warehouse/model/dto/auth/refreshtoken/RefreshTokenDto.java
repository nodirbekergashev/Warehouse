package uz.pdp.warehouse.model.dto.auth.refreshtoken;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenDto {

    private String accessToken;
    private String refreshToken;

    private String tokenType = "Bearer";
}
