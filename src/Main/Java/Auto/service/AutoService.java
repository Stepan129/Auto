package Auto.service;

import Auto.dto.AutoDto;
import Auto.dto.BaseAutoDto;
import Auto.dto.AutoPatch;
import Auto.dto.common.ValueDto;
import Auto.dto.quary.params.AutoFitterOptions;

import java.util.List;

public interface AutoService {
    AutoDto create(BaseAutoDto bookDto);
    List<AutoDto> findAll(AutoFitterOptions fitterOptions, Integer limit, Integer offset);
    AutoDto find(Long id);
    ValueDto<Integer> count(AutoFitterOptions params);
    void update(Long id, BaseAutoDto facultyDto);
    void patch(Long id, AutoPatch facultyPatch);
    void delete(Long id);
}
