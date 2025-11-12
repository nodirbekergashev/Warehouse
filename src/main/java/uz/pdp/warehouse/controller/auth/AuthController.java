package uz.pdp.warehouse.controller.auth;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.warehouse.model.dto.auth.AuthResponse;
import uz.pdp.warehouse.model.dto.user.UserCreateDto;
import uz.pdp.warehouse.model.dto.user.UserLoginDto;
import uz.pdp.warehouse.service.auth.AuthService;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserLoginDto loginDto) {
        AuthResponse authResponse = authService.login(loginDto);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody UserCreateDto userCreateDto) {
        authService.register(userCreateDto);
        return ResponseEntity.ok().build();
    }
}
