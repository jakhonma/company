package conpany.uz.company_service.service;

import conpany.uz.company_service.entity.Company;
import conpany.uz.company_service.entity.Department;
import conpany.uz.company_service.payload.ApiResponse;
import conpany.uz.company_service.payload.DepartmentDto;
import conpany.uz.company_service.repository.CompanyRepository;
import conpany.uz.company_service.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    CompanyRepository companyRepository;

    /**
     * BARCHA BULIMLARNI RUYXATINI QAYTARADI
     * @return List<Department>
     */
    public List<Department> getAllDepartments(){
        //todo:GET ALL DEPARTMENT
        return departmentRepository.findAll();
    }

    /**
     * IDGA TEGISHLI DEPARTMENT QAYTARADI
     * @param id
     * @return Department
     */
    public Department getDepartment(Long id){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        return optionalDepartment.orElse(null);
    }

    /**
     * DEPARTMENT QO'SHISH
     * @param departmentDto
     * @return ApiResponse
     */
    public ApiResponse addDepartment(DepartmentDto departmentDto){
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent()){
            return new ApiResponse("Bu bulim kamaniyaga tegishli emas", false);
        }
        Department department = new Department();
        department.setName(departmentDto.getName());
        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);
        return new ApiResponse("Department created", true);
    }

    /**
     * DEPARTMENT TAHRIRLASH
     * @param departmentDto
     * @param id
     * @return ApiResponse
     */
    public ApiResponse editDepartment(DepartmentDto departmentDto, Long id){
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent()){
            return new ApiResponse("Bu bulim kamaniyaga tegishli emas", false);
        }
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent()){
            return new ApiResponse("Bu bulim bulim tegishli emas", false);
        }
        Department department = optionalDepartment.get();
        department.setName(departmentDto.getName());
        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);
        return new ApiResponse("Department updated", true);
    }

    /**
     * DEPARTMENT DELETED
     * @param id
     * @return ApiResponse
     */
    public ApiResponse deleteDepartment(Long id){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent()){
            return new ApiResponse("Bu bulim bulim tegishli emas", false);
        }
        departmentRepository.deleteById(id);
        return new ApiResponse("Department deleted", true);
    }
}
