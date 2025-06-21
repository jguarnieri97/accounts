package ar.edu.unlam.tpi.accounts.service.impl;

import ar.edu.unlam.tpi.accounts.controller.impl.UserControllerImpl;
import ar.edu.unlam.tpi.accounts.dto.request.UserRequest;
import ar.edu.unlam.tpi.accounts.dto.request.SupplierRegisterRequestDto;
import ar.edu.unlam.tpi.accounts.dto.request.WorkerRegisterRequestDto;
import ar.edu.unlam.tpi.accounts.dto.request.ApplicantRegisterRequestDto;
import ar.edu.unlam.tpi.accounts.dto.response.GenericResponse;
import ar.edu.unlam.tpi.accounts.dto.response.UserDetailResponse;
import ar.edu.unlam.tpi.accounts.dto.response.UserResponse;
import ar.edu.unlam.tpi.accounts.mapper.UserMapper;
import ar.edu.unlam.tpi.accounts.mapper.UserRequestFactory;
import ar.edu.unlam.tpi.accounts.dto.response.UserCreatedResponse;
import ar.edu.unlam.tpi.accounts.models.SupplierCompanyEntity;
import ar.edu.unlam.tpi.accounts.models.WorkerEntity;
import ar.edu.unlam.tpi.accounts.models.ApplicantCompanyEntity;
import ar.edu.unlam.tpi.accounts.persistence.dao.SupplierCompanyDAO;
import ar.edu.unlam.tpi.accounts.persistence.dao.WorkerDAO;
import ar.edu.unlam.tpi.accounts.persistence.dao.ApplicantCompanyDAO;
import ar.edu.unlam.tpi.accounts.service.UserService;
import ar.edu.unlam.tpi.accounts.utils.Constants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRequestFactory userRequestFactory;

    @Mock
    private SupplierCompanyDAO supplierCompanyDAO;

    @Mock
    private WorkerDAO workerDAO;

    @Mock
    private ApplicantCompanyDAO applicantCompanyDAO;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserControllerImpl controller;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    void givenUserRequestList_whenGetUsersDetails_thenReturnsGroupedUserResponse() {
        // Given
        List<UserRequest> userRequests = List.of(
                UserRequest.builder().userId(1L).type("applicant").build(),
                UserRequest.builder().userId(2L).type("supplier").build(),
                UserRequest.builder().userId(3L).type("worker").build()
        );

        UserResponse mockResponse = UserResponse.builder()
                .applicants(List.of(UserDetailResponse.builder().id(1L).name("Empresa Uno").build()))
                .suppliers(List.of(UserDetailResponse.builder().id(2L).name("Proveedor Dos").build()))
                .workers(List.of(UserDetailResponse.builder().id(3L).name("Carlos Trabajador").build()))
                .build();

        when(userService.getUsersDetail(userRequests)).thenReturn(mockResponse);

        // When
        GenericResponse<UserResponse> response = controller.getUsersDetails(userRequests);

        // Then
        assertNotNull(response);
        assertEquals(Constants.STATUS_OK, response.getCode());
        assertEquals(Constants.SUCCESS_MESSAGE, response.getMessage());
        assertEquals(mockResponse, response.getData());
        assertEquals(1, response.getData().getApplicants().size());
        assertEquals(1, response.getData().getSuppliers().size());
        assertEquals(1, response.getData().getWorkers().size());
    }

    @Test
    void givenWorkerRequest_whenCreateUser_thenReturnsUserCreatedResponse() {
        // Arrange
        Map<String, Object> requestJson = Map.of("type", "worker", "companyId", 1L, "name", "Juan");

        WorkerRegisterRequestDto dto = new WorkerRegisterRequestDto();
        dto.setCompanyId(1L);
        dto.setName("Juan");

        SupplierCompanyEntity supplier = new SupplierCompanyEntity();
        WorkerEntity worker = new WorkerEntity();
        worker.setId(77L);

        when(userRequestFactory.resolve(requestJson)).thenReturn(dto);
        when(supplierCompanyDAO.findById(1L)).thenReturn(supplier);
        when(userMapper.toWorkerEntity(dto, supplier)).thenReturn(worker);
        when(workerDAO.save(any(WorkerEntity.class))).thenReturn(worker);

        // Act
        UserCreatedResponse response = userServiceImpl.createUser(requestJson);

        // Assert
        assertNotNull(response);
        assertEquals(77L, response.getId());
        verify(workerDAO).save(any(WorkerEntity.class));
    }

    @Test
    void givenSupplierRequest_whenCreateUser_thenReturnsUserCreatedResponse() {
        // Arrange
        Map<String, Object> requestJson = Map.of("type", "supplier", "name", "Empresa Uno", "lat", 1.0, "ln", 1.0);

        SupplierRegisterRequestDto dto = new SupplierRegisterRequestDto();
        dto.setName("Empresa Uno");
        dto.setLat(1.0f);
        dto.setLn(1.0f);

        SupplierCompanyEntity supplier = new SupplierCompanyEntity();
        supplier.setId(77L);

        when(userRequestFactory.resolve(requestJson)).thenReturn(dto);
        when(userMapper.toSupplierEntity(dto)).thenReturn(supplier);
        when(supplierCompanyDAO.save(any(SupplierCompanyEntity.class))).thenReturn(supplier);

        // Act
        UserCreatedResponse response = userServiceImpl.createUser(requestJson);

        // Assert
        assertNotNull(response);
        assertEquals(77L, response.getId());
        verify(supplierCompanyDAO).save(any(SupplierCompanyEntity.class));
    }          

    @Test
    void givenApplicantRequest_whenCreateUser_thenReturnsUserCreatedResponse() {
        // Arrange
        Map<String, Object> requestJson = Map.of("type", "applicant", "name", "Empresa Uno", "lat", 1.0, "ln", 1.0);

        ApplicantRegisterRequestDto dto = new ApplicantRegisterRequestDto();
        dto.setName("Empresa Uno");
        dto.setLat(1.0f);
        dto.setLn(1.0f);

        ApplicantCompanyEntity applicant = new ApplicantCompanyEntity();
        applicant.setId(77L);

        when(userRequestFactory.resolve(requestJson)).thenReturn(dto);
        when(userMapper.toApplicantEntity(dto)).thenReturn(applicant);
        when(applicantCompanyDAO.save(any(ApplicantCompanyEntity.class))).thenReturn(applicant);

        // Act
        UserCreatedResponse response = userServiceImpl.createUser(requestJson);

        // Assert
        assertNotNull(response);
        assertEquals(77L, response.getId());
        verify(applicantCompanyDAO).save(any(ApplicantCompanyEntity.class));
    }           
}
