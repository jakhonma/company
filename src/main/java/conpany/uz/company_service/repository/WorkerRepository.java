package conpany.uz.company_service.repository;

import conpany.uz.company_service.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Long id);

    List<Worker> findByDepartmentId(Long department_id);

    @Query("select wk from Worker wk where wk.department.id=:department_id")
    List<Worker> findByDepartmentIdJpaQuery(Long department_id);

    @Query(value = "select * from worker wk where wk.department_id=:departmentId", nativeQuery = true)
    List<Worker> findByDepartmentIdNativeQuery(Long departmentId);

    Worker findByIdAndDepartmentId(Long id, Long department_id);
}
