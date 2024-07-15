package conpany.uz.company_service.service;

import conpany.uz.company_service.entity.Address;
import conpany.uz.company_service.entity.Company;
import conpany.uz.company_service.payload.ApiResponse;
import conpany.uz.company_service.payload.CompanyDto;
import conpany.uz.company_service.repository.AddressRepository;
import conpany.uz.company_service.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    AddressRepository addressRepository;

    /**
     * BARCHA COMPANY RUYXATINI QAYTARADI
     * @return List<Company>
     */
    public List<Company> getCompanyAll(){
        //todo:Get All Company
        return companyRepository.findAll();
    }

    /**
     * IDGA TEGISHLI COMPANYNI QAYTARADI
     * @param id
     * @return Company
     */
    public Company getCompany(Long id){
        //todo: Get One Company
        Optional<Company> optionalCompany = companyRepository.findById(id);
        return optionalCompany.orElse(null);
    }

    /**
     * COMPANY QO'SHISH
     * @param companyDto
     * @return ApiResponse
     */
    public ApiResponse addCompany(CompanyDto companyDto){
        //todo: Add Company
        //COMPANY MANZIL QUSHISH
        Address address = new Address();
        address.setStreet(companyDto.getStreet());
        address.setHomeNumber(companyDto.getHomeNumber());
        Address savedAddress = addressRepository.save(address);

        //COMPANY QUSHISH
        Company company = new Company();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        company.setAddress(savedAddress);
        companyRepository.save(company);
        return new ApiResponse("Company created", true);
    }

    /**
     * COMPANY TAHRIRLASH
     * @param companyDto
     * @param id
     * @return ApiResponse
     */
    public ApiResponse editCompany(CompanyDto companyDto, Long id){
        //todo: Edit Company
        // Edit Company
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent()){
            return new ApiResponse("Bunday company mavjud emas", false);
        }
        Company company = optionalCompany.get();
        Address address = company.getAddress();

        address.setStreet(companyDto.getStreet());
        address.setHomeNumber(companyDto.getHomeNumber());
        addressRepository.save(address);

        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        companyRepository.save(company);
        return new ApiResponse("Company updated", true);
    }

    /**
     * COMPANY DELETED
     * @param id
     * @return ApiResponse
     */
    public ApiResponse deleteCompany(Long id){
        //todo: Delete Company
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent()){
            return new ApiResponse("Bunday Company mavjud emas", false);
        }
        Company company = optionalCompany.get();
        Address address = company.getAddress();
        companyRepository.delete(company);
        addressRepository.delete(address);
        return new ApiResponse("Company deleted", true);
    }
}
