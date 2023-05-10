package Auto.service.user.impl;

import Auto.dto.user.UserCreateDto;
import Auto.dto.user.UserDto;
import Auto.dto.user.UserUpdateDto;
import Auto.entity.user.UserEntity;
import Auto.exception.DataConflictException;
import Auto.exception.NotFoundException;
import Auto.mapper.UserMapper;
import Auto.repository.user.UserRepository;
import Auto.service.user.UserService;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto create(UserCreateDto userDto) {
        UserEntity userEntity = userMapper.toEntity(userDto);

        String passwordHash = bCryptPasswordEncoder.encode(userDto.getPassword());
        userEntity.setPasswordHash(passwordHash);

        try {
            userEntity = userRepository.save(userEntity);
        } catch (DataIntegrityViolationException e) {
            Throwable cause = e.getCause();
            if (cause instanceof ConstraintViolationException) {
                if ("users_username_key".equals(((ConstraintViolationException) cause).getConstraintViolations())) {
                    String errorMessage = String.format("User with username '%s' already exists!", userEntity.getUsername());
                    throw new DataConflictException(errorMessage);
                }
            }

            throw e;
        }
        return userMapper.toDto(userEntity);
    }

    @Override
    public List<UserDto> findAll() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userMapper.toDtoList(userEntities);
    }

    @Override
    public UserDto find(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found!"));
        return userMapper.toDto(userEntity);
    }

    @Override
    public void update(Long id, UserUpdateDto userDto) {
        // Home task
    }

    @Override
    @Transactional
    public void delete(Long id) {
        int affectedRaws = userRepository.removeById(id);
        if (affectedRaws == 0) {
            throw new NotFoundException("User not found!");
        }
    }
}
