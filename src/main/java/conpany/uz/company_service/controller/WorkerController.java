package conpany.uz.company_service.controller;

import conpany.uz.company_service.entity.Worker;
import conpany.uz.company_service.payload.ApiResponse;
import conpany.uz.company_service.payload.WorkerDto;
import conpany.uz.company_service.service.WorkerService;
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
@RequestMapping("/api/worker")
public class WorkerController {
    @Autowired
    WorkerService workerService;

    /**
     * KAMPANIYA KO'RA OLADIGAN ISHCHILAR
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Worker>> getAllWorkers(){
        List<Worker> allWorkers = workerService.getAllWorkers();
        return ResponseEntity.status(200).body(allWorkers);
    }

    /**
     * BULIM KO'RA OLADIGAN ISHCHILAR
     * @param departmentId
     * @return
     */
    @GetMapping("/byDepartment/{departmentId}")
    public ResponseEntity<List<Worker>> getDepartmentWorkers(@PathVariable Long departmentId){
        List<Worker> departmentWorkers = workerService.getDepartmentWorkers(departmentId);
        return ResponseEntity.status(200).body(departmentWorkers);
    }

    /**
     * ID GA TEGISHLI KAMPANIYA KURA OLADIGAN ISHCHI
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Worker> getCompanyWorker(@PathVariable Long id){
        Worker worker = workerService.getWorker(id);
        return ResponseEntity.status(200).body(worker);
    }

    /**
     * ID GA TEGISHLI BULIM KURA OLADIGAN ISHCHI
     * @param id
     * @return Worker
     */
    @GetMapping("/{department_id}/{id}")
    public ResponseEntity<Worker> getDepartmentWorker(@PathVariable Long id, @PathVariable Long department_id){
        Worker worker = workerService.getDepartmentWorker(department_id, id);
        return ResponseEntity.status(200).body(worker);
    }

    /**
     * ISHCHI QO'SHISH
     * @param workerDto
     * @return ApiRepository
     */
    @PostMapping
    public ResponseEntity<ApiResponse> addWorker(@RequestBody WorkerDto workerDto){
        ApiResponse apiResponse = workerService.addWorker(workerDto);
        return ResponseEntity.status(apiResponse.isStatus()?201:409).body(apiResponse);
    }

    /**
     * ISHCHI TAHRIRLASH
     * @param workerDto
     * @param id
     * @return ApiRepository
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editWorker(@RequestBody WorkerDto workerDto, @PathVariable Long id){
        ApiResponse apiResponse = workerService.editWorker(workerDto, id);
        return ResponseEntity.status(apiResponse.isStatus()?202:409).body(apiResponse);
    }

    /**
     * WORKER DELETED
     * @param id
     * @return ApiResponse
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteWorker(@PathVariable Long id){
        ApiResponse apiResponse = workerService.deleteWorker(id);
        return ResponseEntity.status(apiResponse.isStatus()?204:409).body(apiResponse);
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
