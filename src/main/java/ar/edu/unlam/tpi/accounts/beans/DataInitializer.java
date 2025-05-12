package ar.edu.unlam.tpi.accounts.beans;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import ar.edu.unlam.tpi.accounts.models.ApplicantCompanyEntity;
import ar.edu.unlam.tpi.accounts.models.CompanyTypeEntity;
import ar.edu.unlam.tpi.accounts.models.GeolocationEntity;
import ar.edu.unlam.tpi.accounts.models.SupplierCompanyEntity;
import ar.edu.unlam.tpi.accounts.models.WorkerEntity;
import ar.edu.unlam.tpi.accounts.persistence.repository.ApplicantCompanyRepository;
import ar.edu.unlam.tpi.accounts.persistence.repository.CompanyTypeRepository;
import ar.edu.unlam.tpi.accounts.persistence.repository.GeolocationRepository;
import ar.edu.unlam.tpi.accounts.persistence.repository.SupplierCompanyRepository;
import ar.edu.unlam.tpi.accounts.persistence.repository.WorkerRepository;
import ar.edu.unlam.tpi.accounts.utils.ApplicantCompanyDataHelper;
import ar.edu.unlam.tpi.accounts.utils.CompanyTypeDataHelper;
import ar.edu.unlam.tpi.accounts.utils.GeolocationDataHelper;
import ar.edu.unlam.tpi.accounts.utils.SupplierCompanyHelper;
import ar.edu.unlam.tpi.accounts.utils.WorkerDataHelper;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final CompanyTypeRepository companyTypeRepository;
    private final SupplierCompanyRepository supplierCompanyRepository;
    private final GeolocationRepository geolocationRepository;
    private final WorkerRepository workerRepository;
    private final ApplicantCompanyRepository applicantCompanyRepository;


    @Override
    public void run(String... args) throws Exception {
        

        //Inicializar datos de los tipos de compañia
        List<CompanyTypeEntity> companyTypes = CompanyTypeDataHelper.getCompanyTypes();
        companyTypeRepository.saveAll(companyTypes);

        //Inicializar Geolocaciones de compañia
        List<GeolocationEntity> geolocations = GeolocationDataHelper.getGeolocations();
        geolocationRepository.saveAll(geolocations);




        //Inicializar datos de compañias solicitantes
        List<ApplicantCompanyEntity> applicants = ApplicantCompanyDataHelper.getApplicantCompanyList();
        applicants.get(0).setGeolocation(geolocationRepository.findById(4L).get());

        applicants.get(1).setGeolocation(geolocationRepository.findById(5L).get());

        applicantCompanyRepository.saveAll(applicants);


        //Inicializar entidades de proveedores  
        List<SupplierCompanyEntity> suppliers = SupplierCompanyHelper.getSupplierCompanies();
        
        suppliers.get(0).setCompanyType(companyTypeRepository.findByName("Limpieza e Higiene Industrial").get());            
        suppliers.get(0).setGeolocation(geolocationRepository.findById(1L).get());
        
        suppliers.get(1).setCompanyType(companyTypeRepository.findByName("Tecnología y Software").get());
        suppliers.get(1).setGeolocation(geolocationRepository.findById(2L).get());
        
        suppliers.get(2).setCompanyType(companyTypeRepository.findByName("Agroindustria").get());
        suppliers.get(2).setGeolocation(geolocationRepository.findById(3L).get());    

        supplierCompanyRepository.saveAll(suppliers);

        //Inicializar datos de workers
        List<WorkerEntity> workers = WorkerDataHelper.getWorkers();
        
        workers.get(0).setCompany(suppliers.get(0)); // Juan Pérez - Arrteh
        workers.get(1).setCompany(suppliers.get(0)); // María García - Arrteh
        workers.get(2).setCompany(suppliers.get(1)); // Carlos Rodríguez - TechSolutions
        workers.get(3).setCompany(suppliers.get(1)); // Ana Martínez - TechSolutions
        workers.get(4).setCompany(suppliers.get(2)); // Roberto López - AgroFertil
        workers.get(5).setCompany(suppliers.get(2)); // Laura Sánchez - AgroFertil
        
        workerRepository.saveAll(workers);

        log.info("Data initialized successfully");


    }
}