package Auto.service.impl;

import Auto.dto.AutoDto;
import Auto.dto.BaseAutoDto;
import Auto.dto.AutoPatch;
import Auto.dto.common.ValueDto;
import Auto.dto.quary.params.AutoFitterOptions;
import Auto.entity.AutoEntity;
import Auto.exception.BadRequestException;
import Auto.mapper.AutoMapper;
import Auto.repository.auto.AutoRepository;
import lombok.AllArgsConstructor;
import Auto.service.AutoService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@AllArgsConstructor
public class AutoServiceImpl implements AutoService {

    private final AutoRepository autoRepository;

    private final AutoMapper autoMapper;

    @Override
    public AutoDto create(BaseAutoDto autoDto) {
        AutoEntity autoEntity = autoMapper.toEntity(autoDto);
        AutoEntity createdAutoEntity = autoRepository.create(autoEntity);
        return autoMapper.toDto(createdAutoEntity);
    }

    @Override
    public List<AutoDto> findAll(@RequestParam(required = false) AutoFitterOptions fitterOptions, @RequestParam(defaultValue = "100", required = false) Integer limit, @RequestParam(defaultValue = "0", required = false) Integer offset) {
        List<AutoEntity> autosEntities = autoRepository.findAll(fitterOptions, limit, offset);
        return autoMapper.toDtoList(autosEntities);
    }

    @Override
    public AutoDto find(Long id) {
        AutoEntity autoEntity = autoRepository.find(id);
        return autoMapper.toDto(autoEntity);
    }

    @Override
    public ValueDto<Integer> count(AutoFitterOptions params) {
        int count = autoRepository.count(params);
        return new ValueDto<>(count);
    }

    @Override
    public void update(Long id, BaseAutoDto autoDto) {
        AutoEntity autoEntity = autoMapper.toEntity(autoDto);
        autoEntity.setId(id);

        autoRepository.update(autoEntity);
    }

    @Override
    public void patch(Long id, AutoPatch autoPatch) {
        if (autoPatch.isEmpty()) {
            throw new BadRequestException("Faculty patch is empty!");
        }

        autoRepository.patch(id, autoPatch);
    }

    @Override
    public void delete(Long id) {
        autoRepository.delete(id);
    }
}
