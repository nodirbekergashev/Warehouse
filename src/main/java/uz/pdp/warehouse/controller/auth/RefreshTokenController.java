package uz.pdp.warehouse.controller.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.warehouse.model.dto.auth.refreshtoken.RefreshTokenCreateDto;
import uz.pdp.warehouse.model.dto.auth.refreshtoken.RefreshTokenDto;
import uz.pdp.warehouse.service.auth.TokenService;

@RestController
@RequestMapping("/auth/refresh-token")
@RequiredArgsConstructor
public class RefreshTokenController {

    private final TokenService tokenService;

    /**
     * Endpoint to exchange an expired access token using a valid refresh token.
     * The refresh token is provided in the request body.
     * * @param request DTO containing the refresh token.
     * @return ResponseEntity with the new access and refresh tokens.
     */
    @PostMapping
    public ResponseEntity<RefreshTokenDto> refresh(
            @Valid @RequestBody RefreshTokenCreateDto request) {

        RefreshTokenDto response = tokenService.refreshTokens(request.getRefreshToken());

        return ResponseEntity.ok(response);
    }
}
