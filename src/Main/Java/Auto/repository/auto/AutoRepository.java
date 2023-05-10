package Auto.repository.auto;

import Auto.dto.AutoPatch;
import Auto.dto.quary.params.AutoFitterOptions;
import Auto.entity.AutoEntity;

import java.util.List;

public interface AutoRepository {
    AutoEntity create(AutoEntity faculty);
    List<AutoEntity> findAll(AutoFitterOptions fitterOptions, Integer limit, Integer offset);
    AutoEntity find(Long id);
    int count(AutoFitterOptions params);
    void update(AutoEntity faculty);
    void patch(Long id, AutoPatch facultyPatch);
    void delete(Long id);
}
