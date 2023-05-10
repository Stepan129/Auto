package Auto.mapper;

import Auto.dto.user.UserCreateDto;
import Auto.dto.user.UserDto;
import Auto.entity.user.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-10T10:03:23+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity toEntity(UserCreateDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setUsername( userDto.getUsername() );
        userEntity.setAdmin( userDto.isAdmin() );
        userEntity.setFirstName( userDto.getFirstName() );
        userEntity.setMiddleName( userDto.getMiddleName() );
        userEntity.setLastName( userDto.getLastName() );
        userEntity.setPhone( userDto.getPhone() );
        userEntity.setEmail( userDto.getEmail() );
        userEntity.setInfo( userDto.getInfo() );

        return userEntity;
    }

    @Override
    public UserDto toDto(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( userEntity.getId() );
        userDto.setUsername( userEntity.getUsername() );
        userDto.setAdmin( userEntity.isAdmin() );
        userDto.setFirstName( userEntity.getFirstName() );
        userDto.setMiddleName( userEntity.getMiddleName() );
        userDto.setLastName( userEntity.getLastName() );
        userDto.setPhone( userEntity.getPhone() );
        userDto.setEmail( userEntity.getEmail() );
        userDto.setInfo( userEntity.getInfo() );

        return userDto;
    }

    @Override
    public List<UserDto> toDtoList(List<UserEntity> userEntities) {
        if ( userEntities == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( userEntities.size() );
        for ( UserEntity userEntity : userEntities ) {
            list.add( toDto( userEntity ) );
        }

        return list;
    }
}
