package uz.pdp.warehouse.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.model.dto.auth.refreshtoken.RefreshTokenDto;
import uz.pdp.warehouse.model.dto.auth.TokenDto; // Assuming this DTO exists for JwtUtils return type
import uz.pdp.warehouse.model.entity.User;     // Assuming this entity exists
import uz.pdp.warehouse.repository.UserRepository; // New dependency needed for user lookup
import uz.pdp.warehouse.utils.JwtUtils; // Renamed to JwtUtils from JwtService

/**
 * Service class responsible for handling all business logic related to token management,
 * including refresh token validation, and access token generation.
 */
@Service
@RequiredArgsConstructor
public class TokenService {

    private final UserRepository userRepository; // To fetch the user object
    private final JwtUtils jwtUtils;           // Utility class for JWT operations

    /**
     * Validates the refresh token and issues a new access token and rotating refresh token.
     *
     * @param refreshToken The refresh token from the client request.
     * @return A DTO containing the new access token and the renewed refresh token.
     */
    public RefreshTokenDto refreshTokens(String refreshToken) {

        if (!jwtUtils.isTokenValid(refreshToken)) {
            throw new RuntimeException("Refresh token is invalid or has expired.");
        }

        String username = jwtUtils.extractUsername(refreshToken);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found for token subject: " + username));

        TokenDto newAccessTokenDto = jwtUtils.generateAccessToken(user);

        TokenDto newRefreshTokenDto = jwtUtils.generateRefreshToken(user);

        return RefreshTokenDto.builder()
                .accessToken(newAccessTokenDto.getToken())
                .refreshToken(newRefreshTokenDto.getToken())
                .tokenType("Bearer")
                .build();
    }

    // The placeholder isValid method is removed as jwtUtils.isTokenValid handles the check.
}