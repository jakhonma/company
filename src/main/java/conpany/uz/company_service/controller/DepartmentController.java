package conpany.uz.company_service.controller;

import conpany.uz.company_service.entity.Department;
import conpany.uz.company_service.payload.ApiResponse;
import conpany.uz.company_service.payload.DepartmentDto;
import conpany.uz.company_service.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    /**
     * BARCHA BULIMLARNI RUYXATINI QAYTARADI
     * @return List<Department>
     */
    @GetMapping
    public ResponseEntity<List<Department>> getDepartmentAll(){
        List<Department> allDepartments = departmentService.getAllDepartments();
        return ResponseEntity.status(HttpStatus.OK).body(allDepartments);
    }

    /**
     * IDGA TEGISHLI DEPARTMENT QAYTARADI
     * @param id
     * @return Department
     */
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable Long id){
        Department department = departmentService.getDepartment(id);
        return ResponseEntity.status(HttpStatus.OK).body(department);
    }

    /**
     * DEPARTMENT QO'SHISH
     * @param departmentDto
     * @return ApiResponse
     */
    @PostMapping
    public ResponseEntity<ApiResponse> addDepartment(@RequestBody DepartmentDto departmentDto){
        ApiResponse apiRepository = departmentService.addDepartment(departmentDto);
        return ResponseEntity.status(apiRepository.isStatus()?201:409).body(apiRepository);
    }

    /**
     * COMPANY TAHRIRLASH
     * @param departmentDto
     * @param id
     * @return ApiResponse
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editDepartment(@PathVariable Long id, @RequestBody DepartmentDto departmentDto){
        ApiResponse apiRepository = departmentService.editDepartment(departmentDto, id);
        return ResponseEntity.status(apiRepository.isStatus()?202:409).body(apiRepository);
    }

    /**
     * COMPANY DELETED
     * @param id
     * @return ApiResponse
     */
    public ResponseEntity<ApiResponse> deleteDepartment(@PathVariable Long id){
        ApiResponse apiRepository = departmentService.deleteDepartment(id);
        return ResponseEntity.status(apiRepository.isStatus()?204:409).body(apiRepository);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handlerValidationExeptions(MethodArgumentNotValidException exception){
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) ->{
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
