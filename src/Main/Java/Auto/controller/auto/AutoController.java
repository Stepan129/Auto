package Auto.controller.auto;

import Auto.dto.AutoDto;
import Auto.dto.BaseAutoDto;
import Auto.dto.AutoPatch;
import Auto.dto.common.ValueDto;
import Auto.dto.quary.params.AutoFitterOptions;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import Auto.service.AutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("autoes")
public class AutoController {

    private final AutoService autoService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AutoDto create(@RequestBody BaseAutoDto faculty) {
        return autoService.create(faculty);
    }

    @GetMapping
    @Operation(
            parameters = {
                    @Parameter(name = "type"),
                    @Parameter(name = "brand")
            }
    )
    public List<AutoDto> findAll(@Parameter(hidden = true) AutoFitterOptions fitterOptions,
                                 @RequestParam(required = false) Integer limit,
                                 @RequestParam(required = false) Integer offset) {
        return autoService.findAll(fitterOptions, limit, offset);
    }

    @GetMapping("{id}")
    public AutoDto find(@PathVariable Long id) {
        return autoService.find(id);
    }

    @GetMapping("count")
    @Operation(
            parameters = {
                    @Parameter(name = "type"),
                    @Parameter(name = "brand")
            }
    )
    public ValueDto<Integer> count(@Parameter(hidden = true) AutoFitterOptions params) {
        return autoService.count(params);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody BaseAutoDto faculty) {
        autoService.update(id, faculty);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patch(@PathVariable Long id, @RequestBody AutoPatch facultyPatch) {
        autoService.patch(id, facultyPatch);
    }

    @DeleteMapping({"{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> delete(@PathVariable Long id) {
        autoService.delete(id);
        return ResponseEntity.ok("Auto deleted successfully");
    }
}
