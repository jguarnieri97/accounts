package ar.edu.unlam.tpi.accounts.beans;

import ar.edu.unlam.tpi.accounts.models.CompanyTypeEnum;
import ar.edu.unlam.tpi.accounts.models.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import ar.edu.unlam.tpi.accounts.persistence.repository.ApplicantCompanyRepository;
import ar.edu.unlam.tpi.accounts.persistence.repository.GeolocationRepository;
import ar.edu.unlam.tpi.accounts.persistence.repository.SupplierCompanyRepository;
import ar.edu.unlam.tpi.accounts.persistence.repository.WorkerRepository;
import ar.edu.unlam.tpi.accounts.utils.ApplicantCompanyDataHelper;
import ar.edu.unlam.tpi.accounts.utils.GeolocationDataHelper;
import ar.edu.unlam.tpi.accounts.utils.SupplierCompanyHelper;
import ar.edu.unlam.tpi.accounts.utils.WorkerDataHelper;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final SupplierCompanyRepository supplierCompanyRepository;
    private final GeolocationRepository geolocationRepository;
    private final WorkerRepository workerRepository;
    private final ApplicantCompanyRepository applicantCompanyRepository;


    @Override
    public void run(String... args) throws Exception {
        

        //Inicializar Geolocaciones de compañia
        List<GeolocationEntity> geolocations = GeolocationDataHelper.getGeolocations();
        geolocationRepository.saveAll(geolocations);


        //Inicializar datos de compañias solicitantes
        List<ApplicantCompanyEntity> applicants = ApplicantCompanyDataHelper.getApplicantCompanyList();
        applicants.get(0).setGeolocation(geolocationRepository.findById(1L).get()); // San justo

        applicants.get(1).setGeolocation(geolocationRepository.findById(5L).get()); // Palermo

        applicants.get(2).setGeolocation(geolocationRepository.findById(9L).get()); // Parque Patricios

        applicantCompanyRepository.saveAll(applicants);


        //Inicializar entidades de proveedores  
        List<SupplierCompanyEntity> suppliers = SupplierCompanyHelper.getSupplierCompanies();

        // San justo
        suppliers.get(0).setCompanyType(CompanyTypeEnum.ELECTRICIDAD);
        suppliers.get(0).setGeolocation(geolocationRepository.findById(2L).get());
        
        suppliers.get(1).setCompanyType(CompanyTypeEnum.ELECTRICIDAD);
        suppliers.get(1).setGeolocation(geolocationRepository.findById(3L).get());
        
        suppliers.get(6).setCompanyType(CompanyTypeEnum.LIMPIEZA);
        suppliers.get(6).setGeolocation(geolocationRepository.findById(4L).get());

        // Palermo
        suppliers.get(3).setCompanyType(CompanyTypeEnum.CONTRATISTA);
        suppliers.get(3).setGeolocation(geolocationRepository.findById(6L).get());

        suppliers.get(4).setCompanyType(CompanyTypeEnum.CONTRATISTA);
        suppliers.get(4).setGeolocation(geolocationRepository.findById(7L).get());

        suppliers.get(5).setCompanyType(CompanyTypeEnum.CONTRATISTA);
        suppliers.get(5).setGeolocation(geolocationRepository.findById(8L).get());

        // Parque Patricios
        suppliers.get(2).setCompanyType(CompanyTypeEnum.ELECTRICIDAD);
        suppliers.get(2).setGeolocation(geolocationRepository.findById(10L).get());

        suppliers.get(7).setCompanyType(CompanyTypeEnum.LIMPIEZA);
        suppliers.get(7).setGeolocation(geolocationRepository.findById(11L).get());

        suppliers.get(8).setCompanyType(CompanyTypeEnum.LIMPIEZA);
        suppliers.get(8).setGeolocation(geolocationRepository.findById(12L).get());

        supplierCompanyRepository.saveAll(suppliers);

        //Inicializar datos de workers
        List<WorkerEntity> workers = WorkerDataHelper.getWorkers();
        
        workers.get(0).setCompany(suppliers.get(0)); // Juan Pérez - Arrteh
        workers.get(1).setCompany(suppliers.get(0)); // María García - Arrteh
        workers.get(2).setCompany(suppliers.get(1)); // Carlos Rodríguez - TechSolutions
        workers.get(3).setCompany(suppliers.get(1)); // Ana Martínez - TechSolutions
        workers.get(4).setCompany(suppliers.get(2)); // Roberto López - AgroFertil
        workers.get(5).setCompany(suppliers.get(2)); // Laura Sánchez - AgroFertil
        workers.get(6).setCompany(suppliers.get(3));
        workers.get(7).setCompany(suppliers.get(3));
        workers.get(8).setCompany(suppliers.get(4));
        workers.get(9).setCompany(suppliers.get(5));
        workers.get(10).setCompany(suppliers.get(6));
        workers.get(12).setCompany(suppliers.get(7));
        workers.get(13).setCompany(suppliers.get(8));


        workerRepository.saveAll(workers);

        log.info("Data initialized successfully");


    }
}