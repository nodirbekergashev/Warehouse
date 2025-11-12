package uz.pdp.warehouse.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.warehouse.model.dto.user.UserCreateDto;
import uz.pdp.warehouse.exceptions.exception.ValidationException;
import uz.pdp.warehouse.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void validateOnCreate(UserCreateDto createDto) {
        if (userRepository.findByUsername(createDto.getUsername()).isPresent()) {
            throw new ValidationException("Username already exists: " + createDto.getUsername());
        }

        validatePassword(createDto.getPassword());
    }


    private void validatePassword(String password) {
        if (password == null || password.length() < 6) {
            throw new ValidationException("Password must be at least 6 characters long");
        }
    }
}