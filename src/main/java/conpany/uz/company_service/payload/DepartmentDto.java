package conpany.uz.company_service.payload;

public class DepartmentDto {

    private String name;

    private Long companyId;

    public DepartmentDto() {
    }

    public DepartmentDto(String name, Long companyId) {
        this.name = name;
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
