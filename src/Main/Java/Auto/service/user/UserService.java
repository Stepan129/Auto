package Auto.service.user;

import Auto.dto.user.UserCreateDto;
import Auto.dto.user.UserDto;
import Auto.dto.user.UserUpdateDto;

import java.util.List;

public interface UserService {
    UserDto create(UserCreateDto userDto);
    List<UserDto> findAll();
    UserDto find(Long id);
    void update(Long id, UserUpdateDto userDto);
    void delete(Long id);
}
