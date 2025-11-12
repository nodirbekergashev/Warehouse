package uz.pdp.warehouse.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.model.dto.user.UserCreateDto;
import uz.pdp.warehouse.model.dto.user.UserDto;
import uz.pdp.warehouse.model.dto.user.UserUpdateDto;
import uz.pdp.warehouse.model.entity.User;
import uz.pdp.warehouse.exceptions.exception.UserNotFoundException;
import uz.pdp.warehouse.mapper.UserMapper;
import uz.pdp.warehouse.repository.UserRepository;
import uz.pdp.warehouse.service.abstractServices.AbstractService;
import uz.pdp.warehouse.service.abstractServices.CRUDService;
import uz.pdp.warehouse.validator.UserValidator;

import java.util.List;

@Service
public class UserService
        extends
        AbstractService<
                UserMapper,
                UserValidator,
                UserRepository>
        implements
        CRUDService<
                User,
                UserCreateDto,
                UserUpdateDto,
                UserDto>,
        UserDetailsService {

    protected UserService(UserMapper mapper, UserValidator validator, UserRepository repository) {
        super(mapper, validator, repository);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return repository.findByUsername(username)
                .filter(User::isEnabled)
                .filter(User::isDeleted)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public UserDto save(UserCreateDto dto) {
        validator
                .validateOnCreate(dto);
        User user = mapper
                .fromCreateDto(dto);
        repository
                .save(user);
        return mapper
                .toDto(user);
    }

    @Override
    public UserDto update(UserUpdateDto updateDto, Long id) {
        User user = mapper
                .fromUpdateDto(updateDto, findById(id));
        repository
                .save(user);
        return mapper
                .toDto(user);
    }

    @Override
    public void delete(Long id) {
        User user = findById(id);
        user.softDelete();
        repository.save(user);
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public List<UserDto> findAll() {
        return repository.findAll()
                .stream()
                .map(u -> mapper.toDto(u))
                .toList();
    }

    @Override
    public UserDto getDto(User user) {
        return mapper.toDto(user);
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("username", username));
    }

}
