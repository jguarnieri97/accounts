package ar.edu.unlam.tpi.accounts.beans;

import ar.edu.unlam.tpi.accounts.models.CompanyTypeEnum;
import ar.edu.unlam.tpi.accounts.models.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import ar.edu.unlam.tpi.accounts.persistence.repository.ApplicantCompanyRepository;
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
@ConditionalOnProperty(name = "app.start.mode", havingValue = "test", matchIfMissing = true)
public class DataInitializer implements CommandLineRunner {

    private final SupplierCompanyRepository supplierCompanyRepository;
    private final WorkerRepository workerRepository;
    private final ApplicantCompanyRepository applicantCompanyRepository;


    @Override
    public void run(String... args) throws Exception {
        


        //Inicializar datos de compañias solicitantes
        List<ApplicantCompanyEntity> applicants = ApplicantCompanyDataHelper.getApplicantCompanyList();
        applicants.get(0).setGeolocation(new Geolocation(-0.96f, -0.96f));

        applicants.get(1).setGeolocation(new Geolocation(-0.96f, -0.96f));

        applicantCompanyRepository.saveAll(applicants);


        //Inicializar entidades de proveedores  
        List<SupplierCompanyEntity> suppliers = SupplierCompanyHelper.getSupplierCompanies();
        
        suppliers.get(0).setCompanyType(CompanyTypeEnum.ELECTRICIAN);
        suppliers.get(0).setGeolocation(new Geolocation(-0.96f, -0.96f));
        
        suppliers.get(1).setCompanyType(CompanyTypeEnum.ELECTRICIAN);
        suppliers.get(1).setGeolocation(new Geolocation(-0.96f, -0.96f));
        
        suppliers.get(2).setCompanyType(CompanyTypeEnum.ELECTRICIAN);
        suppliers.get(2).setGeolocation(new Geolocation(-0.96f, -0.96f));

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