package conpany.uz.company_service.payload;

import jakarta.validation.constraints.NotNull;

public class CompanyDto {

    @NotNull
    private String corpName;

    @NotNull
    private String directorName;

    @NotNull
    private String street;

    @NotNull
    private Integer homeNumber;

    public CompanyDto() {
    }

    public CompanyDto(String corpName, String directorName, String street, Integer homeNumber) {
        this.corpName = corpName;
        this.directorName = directorName;
        this.street = street;
        this.homeNumber = homeNumber;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
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
