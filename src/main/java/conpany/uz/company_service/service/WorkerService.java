package conpany.uz.company_service.service;

import conpany.uz.company_service.entity.Address;
import conpany.uz.company_service.entity.Department;
import conpany.uz.company_service.entity.Worker;
import conpany.uz.company_service.payload.ApiResponse;
import conpany.uz.company_service.payload.WorkerDto;
import conpany.uz.company_service.repository.AddressRepository;
import conpany.uz.company_service.repository.DepartmentRepository;
import conpany.uz.company_service.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    /**
     * KAMPANIYA KO'RA OLADIGAN ISHCHILAR
     * @return
     */
    public List<Worker> getAllWorkers(){
        return workerRepository.findAll();
    }

    /**
     * BULIM KO'RA OLADIGAN ISHCHILAR
     * @param departmentId
     * @return
     */
    public List<Worker> getDepartmentWorkers(Long departmentId){
        return workerRepository.findByDepartmentId(departmentId);
    }

    /**
     * ID GA TEGISHLI KAMPANIYA KURA OLADIGAN ISHCHI
     * @param id
     * @return Worker
     */
    public Worker getWorker(Long id){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        return optionalWorker.orElse(null);
    }

    /**
     * ID GA TEGISHLI BULIM KURA OLADIGAN ISHCHI
     * @param id
     * @return Worker
     */
    public Worker getDepartmentWorker(Long department_id, Long id){
        return workerRepository.findByIdAndDepartmentId(id, department_id);
    }

    /**
     * ISHCHI QO'SHISH
     * @param workerDto
     * @return ApiRepository
     */
    public ApiResponse addWorker(WorkerDto workerDto){
        boolean existsByPhoneNumber = workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber());
        if (existsByPhoneNumber){
            return new ApiResponse("Bunday Ishchi mavjud", false);
        }

        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (!optionalDepartment.isPresent()){
            return new ApiResponse("Bunday Bulim mavjud emas", false);
        }

        // MANZIL YARATISH
        Address address = new Address();
        address.setStreet(workerDto.getStreet());
        address.setHomeNumber(workerDto.getHomeNumber());
        Address savedAddress = addressRepository.save(address);

        //ISHCHI YARATISH
        Worker worker = new Worker();
        worker.setFullName(workerDto.getFullName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setDepartment(optionalDepartment.get());
        worker.setAddress(address);
        workerRepository.save(worker);
        return new ApiResponse("Ishchi muvaffaqiyatli qo'shildi", true);
    }

    /**
     * ISHCHINI TAHRIRLASH
     * @param workerDto
     * @param id
     * @return ApiRepository
     */
    public ApiResponse editWorker(WorkerDto workerDto, Long id){
        boolean existsByPhoneNumberAndIdNot = workerRepository.existsByPhoneNumberAndIdNot(workerDto.getPhoneNumber(), id);
        if (existsByPhoneNumberAndIdNot){
            return new ApiResponse("Bunday telefon raqamli ishchi mavjud", false);
        }
        Optional<Worker> optionalWorker = workerRepository.findById(id);

        if (!optionalWorker.isPresent()){
            return new ApiResponse("Bunday ishchi mavjud emas", false);
        }
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (!optionalDepartment.isPresent()){
            return new ApiResponse("Bunday Bulim mavjud emas", false);
        }

        Worker worker = optionalWorker.get();

        Address address = worker.getAddress();
        address.setStreet(workerDto.getStreet());
        address.setHomeNumber(workerDto.getHomeNumber());
        Address savedAddress = addressRepository.save(address);

        worker.setFullName(workerDto.getFullName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setAddress(savedAddress);
        worker.setDepartment(optionalDepartment.get());
        workerRepository.save(worker);

        return new ApiResponse("Ishchi muvaffaqiyatli o'zgartirildi", true);
    }

    /**
     * ISHCHINI O'CHIRISH
     * @param id
     * @return ApiResponse
     */
    public ApiResponse deleteWorker(Long id){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent()){
            return new ApiResponse("Bunday Ishchi mavjud emas", false);
        }
        Worker worker = optionalWorker.get();
        Address address = worker.getAddress();
        workerRepository.delete(worker);
        addressRepository.delete(address);
        return new ApiResponse("Ishchi muvaffaqiyatli o'chirildi", true);
    }
}
