package conpany.uz.company_service.payload;

import jakarta.validation.constraints.NotNull;

public class WorkerDto {

    @NotNull
    private String fullName;

    @NotNull
    private String phoneNumber;

    @NotNull
    private Long departmentId;

    @NotNull
    private String street;

    @NotNull
    private Integer homeNumber;

    public WorkerDto() {
    }

    public WorkerDto(String fullName, String phoneNumber, Long departmentId, String street, Integer homeNumber) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.departmentId = departmentId;
        this.street = street;
        this.homeNumber = homeNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(Integer homeNumber) {
        this.homeNumber = homeNumber;
    }
}
