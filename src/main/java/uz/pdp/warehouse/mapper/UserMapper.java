package uz.pdp.warehouse.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.warehouse.model.dto.user.UserCreateDto;
import uz.pdp.warehouse.model.dto.user.UserDto;
import uz.pdp.warehouse.model.dto.user.UserUpdateDto;
import uz.pdp.warehouse.model.entity.User;

@Component
public class UserMapper {
    public User fromCreateDto(UserCreateDto dto) {
        User user = new User();
        user.setFullName(dto.getFullName());
        user.setPhone(dto.getPhone());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setUsername(dto.getUsername());
        return user;
    }

    public User fromUpdateDto(UserUpdateDto updateDto, User updatedUser) {
        updatedUser.setFullName(updateDto.getFullName());
        updatedUser.setPhone(updateDto.getPhone());
        updatedUser.setPassword(updateDto.getPassword());
        updatedUser.setRole(updateDto.getRole());
        return updatedUser;
    }

    public UserDto toDto(User u) {
        UserDto userDto = new UserDto();
        userDto.setId(u.getId());
        userDto.setFullName(u.getFullName());
        userDto.setPhone(u.getPhone());
        userDto.setPassword(u.getPassword());
        userDto.setRole(u.getRole());
        userDto.setUsername(u.getUsername());
        return userDto;
    }

    public User fromDto(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setFullName(userDto.getFullName());
        user.setPhone(userDto.getPhone());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        user.setUsername(userDto.getUsername());
        return user;
    }
}
