package uz.pdp.warehouse.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.model.dto.auth.AuthResponse;
import uz.pdp.warehouse.model.dto.auth.TokenDto; // Added import for TokenDto
import uz.pdp.warehouse.model.dto.user.UserCreateDto;
import uz.pdp.warehouse.model.dto.user.UserLoginDto;
import uz.pdp.warehouse.model.entity.User; // Assuming the User entity is here
import uz.pdp.warehouse.service.UserService;
import uz.pdp.warehouse.utils.JwtUtils;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    /**
     * Authenticates a user by username and password, and generates access and refresh tokens.
     * The JwtUtils is updated to return TokenDto, which we handle here.
     * @param loginDto The user login credentials.
     * @return AuthResponse containing tokens and user details.
     */
    public AuthResponse login(UserLoginDto loginDto) {
        User user = userService.findByUsername(loginDto.getUsername());

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        TokenDto accessTokenDto = jwtUtils.generateAccessToken(user);
        TokenDto refreshTokenDto = jwtUtils.generateRefreshToken(user);

        return AuthResponse.builder()
                .accessToken(accessTokenDto.getToken())
                .refreshToken(refreshTokenDto.getToken())
                .username(user.getUsername())
                .role(user.getRole())
                .expiresAt(accessTokenDto.getExpiry())
                .build();
    }

    /**
     * Registers a new user, encodes the password, saves the user, and generates tokens.
     * @param userCreateDto The DTO containing new user details.
     */
    public void register(UserCreateDto userCreateDto) {
        String encodedPassword = passwordEncoder.encode(userCreateDto.getPassword());
        userCreateDto.setPassword(encodedPassword);
        userService.save(userCreateDto);
    }
}