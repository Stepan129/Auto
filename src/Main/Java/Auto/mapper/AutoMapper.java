package Auto.mapper;

import Auto.dto.AutoDto;
import Auto.dto.BaseAutoDto;
import Auto.entity.AutoEntity;

import java.util.List;

import org.mapstruct.Mapper;



@Mapper(componentModel = "spring")
public interface AutoMapper {
    AutoEntity toEntity(BaseAutoDto baseAutoDto);
    AutoDto toDto(AutoEntity autoEntity);
    List<AutoDto> toDtoList(List<AutoEntity> bookEntities);
}
