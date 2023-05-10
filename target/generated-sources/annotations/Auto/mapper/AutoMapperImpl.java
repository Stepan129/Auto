package Auto.mapper;

import Auto.dto.AutoDto;
import Auto.dto.BaseAutoDto;
import Auto.entity.AutoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-10T10:03:22+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class AutoMapperImpl implements AutoMapper {

    @Override
    public AutoEntity toEntity(BaseAutoDto baseAutoDto) {
        if ( baseAutoDto == null ) {
            return null;
        }

        AutoEntity autoEntity = new AutoEntity();

        autoEntity.setId( baseAutoDto.getId() );
        autoEntity.setType( baseAutoDto.getType() );
        autoEntity.setBrand( baseAutoDto.getBrand() );
        autoEntity.setModel( baseAutoDto.getModel() );
        autoEntity.setYear( baseAutoDto.getYear() );
        autoEntity.setCountry( baseAutoDto.getCountry() );
        autoEntity.setCondition( baseAutoDto.getCondition() );
        autoEntity.setMileage( baseAutoDto.getMileage() );
        autoEntity.setPrice( baseAutoDto.getPrice() );
        autoEntity.setInfo( baseAutoDto.getInfo() );

        return autoEntity;
    }

    @Override
    public AutoDto toDto(AutoEntity autoEntity) {
        if ( autoEntity == null ) {
            return null;
        }

        AutoDto autoDto = new AutoDto();

        autoDto.setType( autoEntity.getType() );
        autoDto.setBrand( autoEntity.getBrand() );
        autoDto.setModel( autoEntity.getModel() );
        autoDto.setYear( autoEntity.getYear() );
        autoDto.setCountry( autoEntity.getCountry() );
        autoDto.setCondition( autoEntity.getCondition() );
        autoDto.setMileage( autoEntity.getMileage() );
        autoDto.setPrice( autoEntity.getPrice() );
        autoDto.setInfo( autoEntity.getInfo() );
        autoDto.setId( autoEntity.getId() );

        return autoDto;
    }

    @Override
    public List<AutoDto> toDtoList(List<AutoEntity> bookEntities) {
        if ( bookEntities == null ) {
            return null;
        }

        List<AutoDto> list = new ArrayList<AutoDto>( bookEntities.size() );
        for ( AutoEntity autoEntity : bookEntities ) {
            list.add( toDto( autoEntity ) );
        }

        return list;
    }
}
