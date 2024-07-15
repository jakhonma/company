package conpany.uz.company_service.controller;

import conpany.uz.company_service.entity.Company;
import conpany.uz.company_service.payload.ApiResponse;
import conpany.uz.company_service.payload.CompanyDto;
import conpany.uz.company_service.service.CompanyService;
import jakarta.validation.Valid;
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
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    /**
     * BARCHA COMPANY RUYXATINI CHIQARISH
     * @return List<ApiResponse>
     */
    @GetMapping
    public ResponseEntity<List<Company>> getAllCompany(){
        List<Company> companyAll = companyService.getCompanyAll();
        return ResponseEntity.status(HttpStatus.OK).body(companyAll);
    }

    /**
     * IDGA TEGISHLI COMPANYNI QAYTARAI
     * @param id
     * @return ApiResponse
     */
    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Long id){
        Company company = companyService.getCompany(id);
        return ResponseEntity.status(HttpStatus.OK).body(company);
    }

    /**
     * COMPANY QO'SHISH
     * @param companyDto
     * @return ApiResponse
     */
    @PostMapping
    public ResponseEntity<ApiResponse> addCompany(@Valid @RequestBody CompanyDto companyDto){
        ApiResponse apiRepository = companyService.addCompany(companyDto);
        return ResponseEntity.status(apiRepository.isStatus()?201:409).body(apiRepository);
    }

    /**
     * COMPANY TAHRIRLASH
     * @param companyDto
     * @param id
     * @return ApiResponse
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editCompany(@PathVariable Long id, @Valid @RequestBody CompanyDto companyDto){
        ApiResponse apiRepository = companyService.editCompany(companyDto, id);
        return ResponseEntity.status(apiRepository.isStatus()?202:409).body(apiRepository);
    }

    /**
     * COMPANY DELETED
     * @param id
     * @return ApiResponse
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCompany(@PathVariable Long id){
        ApiResponse apiRepository = companyService.deleteCompany(id);
        return ResponseEntity.status(apiRepository.isStatus()?204:402).body(apiRepository);
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
