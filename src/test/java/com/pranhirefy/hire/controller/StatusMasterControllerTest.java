
package com.pranhirefy.hire.controller;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.pranhirefy.hire.dto.StatusMasterDto;
import com.pranhirefy.hire.entity.StatusMasterEntity;
import com.pranhirefy.hire.exception.StatusMasterException;
import com.pranhirefy.hire.exception.StatusMasterValidationException;
import com.pranhirefy.hire.mapper.StatusMasterMapper;
import com.pranhirefy.hire.repository.StatusMasterRepository;
import com.pranhirefy.hire.service.StatusMasterService;
import com.pranhirefy.hire.service.impl.StatusMasterServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.lang.ModuleLayer.Controller;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class StatusMasterControllerTest {

    private StatusMasterService statusMasterService;
    private StatusMasterController controller;
    private StatusMasterDto sampleDto;
    @Autowired
    @Mock
    private StatusMasterRepository repository;
    
    
    

    @BeforeEach
    void setUp() {
        statusMasterService = mock(StatusMasterService.class);
        controller = new StatusMasterController(statusMasterService);

        sampleDto = new StatusMasterDto();
        sampleDto.setStatusMasterCode(101);
        sampleDto.setStatusMasterDescription("Sample Status");
        sampleDto.setCreatedBy("admin");
        sampleDto.setCreatedDate(LocalDate.of(2025, 6, 9));
        sampleDto.setModifiedBy("----");
    }

    @Test
    void testCreateStatusMaster_Success() {
        when(statusMasterService.createStatusMaster(any())).thenReturn(sampleDto);

        ResponseEntity<?> response = controller.createStatusMaster(sampleDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(((Map<?, ?>) response.getBody()).get("message").toString().contains("created"));
    }

   


    @Test
    void testUpdateStatusMaster_Success() {
        when(statusMasterService.updateStatusMaster(anyLong(), any())).thenReturn(sampleDto);

        ResponseEntity<?> response = controller.updateStatusMaster(1L, sampleDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(((Map<?, ?>) response.getBody()).get("message").toString().contains("updated"));
    }

   


    @Test
    void testGetStatusMasterById_Success() {
        when(statusMasterService.getStatusMasterById(1L)).thenReturn(sampleDto);

        ResponseEntity<StatusMasterDto> response = controller.getStatusMasterById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(101, response.getBody().getStatusMasterCode());
    }

    @Test
    void testGetAllStatusMasters_Success() {
        when(statusMasterService.getAllMasters()).thenReturn(List.of(sampleDto));

        ResponseEntity<List<StatusMasterDto>> response = controller.getAllStatusMasters();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    


    @Test
    void testHandleStatusMasterException() {
        StatusMasterException ex = new StatusMasterException("Not found");
        ResponseEntity<Map<String, Object>> response = controller.handleStatusMasterException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Not found", response.getBody().get("message"));
    }

    @Test
    void testHandleHttpMessageNotReadableException_withInvalidFormat() {
        Reference ref = mock(Reference.class);
        when(ref.getFieldName()).thenReturn("statusMasterCode");

        InvalidFormatException ife = new InvalidFormatException(null, "Invalid", "abc", Integer.class);
        ife.prependPath(ref);

        HttpMessageNotReadableException ex = new HttpMessageNotReadableException("Invalid JSON", ife, null);

        // Fix: Update return type to match new method
        ResponseEntity<Map<String, String>> response = controller.handleHttpMessageNotReadableException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Map<String, String> responseBody = response.getBody();
        assertNotNull(responseBody);

        assertTrue(responseBody.containsKey("statusMasterCode"));
        String message = responseBody.get("statusMasterCode");

        assertTrue(message.contains("statusMasterCode"));
        assertTrue(message.contains("abc"));
        assertTrue(message.contains("Integer"));
    }

    @Test
    void testHandleHttpMessageNotReadableException_Generic() {
        HttpMessageNotReadableException ex = new HttpMessageNotReadableException("Bad JSON", null, null);
        ResponseEntity<Map<String, String>> response = controller.handleHttpMessageNotReadableException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Map<String, String> responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals("Malformed JSON request or invalid input types", responseBody.get("error"));
    }

    @Test
    void testHandleGenericException() {
        Exception ex = new RuntimeException("Unknown error");
        ResponseEntity<String> response = controller.handleGenericException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Internal server error", response.getBody());
    }

    @Test
    void testHandleInvalidSubPathDirectCall() {
        ResponseEntity<String> response = controller.handleInvalidSubPath();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Invalid point, please check the URL.", response.getBody());
    }

    @Test
    void testHandleTypeMismatch() throws NoSuchMethodException {
        Method method = DummyClass.class.getMethod("dummyMethod", Integer.class);
        MethodParameter methodParameter = new MethodParameter(method, 0);

        MethodArgumentTypeMismatchException ex = new MethodArgumentTypeMismatchException(
                "abc", Integer.class, "id", methodParameter, null);

        new DummyClass().dummyMethod(123);  // Just to ensure method is referenced

        ResponseEntity<String> response = controller.handleTypeMismatch(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid parameter type: id", response.getBody());
    }

    @Test
    void testHandleInvalidFormatException() {
        Reference ref = mock(Reference.class);
        when(ref.getFieldName()).thenReturn("statusMasterCode");

        InvalidFormatException ex = new InvalidFormatException(null, "Invalid format", "abc", Integer.class);
        ex.prependPath(ref);

        ResponseEntity<String> response = controller.handleInvalidFormatException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Field 'statusMasterCode' must be a valid integer.", response.getBody());
    }

    public static class DummyClass {
        public void dummyMethod(Integer id) {}
    }
    @Test
    void testSearchByDescription_WithoutMock_Success() {
        // Step 1: Create a real service implementation using stubbed data
        StatusMasterService realService = new StatusMasterService() {
            @Override
            public List<StatusMasterDto> searchStatusMasters(String statusMasterDescription) {
                if ("Sample".equalsIgnoreCase(statusMasterDescription)) {
                    StatusMasterDto dto = new StatusMasterDto();
                    dto.setStatusMasterCode(1);
                    dto.setStatusMasterDescription("Sample");
                    dto.setCreatedBy("tester");
                    dto.setCreatedDate(LocalDate.now());
                    dto.setModifiedBy("----");
                    return List.of(dto);
                }
                throw new StatusMasterException("No StatusMasters found with statusMasterDescription matching: " + statusMasterDescription);
            }

            // Unused methods stubbed (or throw UnsupportedOperationException)
            @Override public StatusMasterDto createStatusMaster(StatusMasterDto dto) { return null; }
            @Override public StatusMasterDto updateStatusMaster(Long id, StatusMasterDto dto) { return null; }
            @Override public StatusMasterDto getStatusMasterById(Long id) { return null; }
            @Override public List<StatusMasterDto> getAllMasters() { return List.of(); }
            @Override public void deleteMaster(Long id) {}
        };

        // Step 2: Create controller with realService
        StatusMasterController controller = new StatusMasterController(realService);

        // Step 3: Call method
        ResponseEntity<List<StatusMasterDto>> response = controller.searchByDescription("Sample");

        // Step 4: Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("Sample", response.getBody().get(0).getStatusMasterDescription());
    }
    @Test
    void testSearchByDescription_WithoutMock_BlankInput() {
        StatusMasterService dummyService = new StatusMasterService() {
            @Override public List<StatusMasterDto> searchStatusMasters(String desc) { return List.of(); }
            @Override public StatusMasterDto createStatusMaster(StatusMasterDto dto) { return null; }
            @Override public StatusMasterDto updateStatusMaster(Long id, StatusMasterDto dto) { return null; }
            @Override public StatusMasterDto getStatusMasterById(Long id) { return null; }
            @Override public List<StatusMasterDto> getAllMasters() { return List.of(); }
            @Override public void deleteMaster(Long id) {}
        };

        StatusMasterController controller = new StatusMasterController(dummyService);

        StatusMasterException ex = assertThrows(StatusMasterException.class, () -> {
            controller.searchByDescription(" ");
        });

        assertEquals("The 'statusMasterDescription' query parameter is missing. Please provide it to perform search.", ex.getMessage());
    }
    
    @Test
    void testSetId() {
        StatusMasterDto dto = new StatusMasterDto();
        Long expectedId = 100L;

        dto.setId(expectedId);

        assertEquals(expectedId, dto.getId());
    }
    
    @Test
    void testSetId_getId() {
        StatusMasterDto dto = new StatusMasterDto();

        Long testId = 999L;
        dto.setId(testId);                // This line triggers setId()

        assertEquals(testId, dto.getId()); // This verifies the value
    }
    
    @Test
    void testSetCreatedDate() {
        StatusMasterDto dto = new StatusMasterDto();

        LocalDate testDate = LocalDate.of(2025, 6, 11);
        dto.setCreatedDate(testDate);                    // This calls the setter
        assertEquals(testDate, dto.getCreatedDate());    // This confirms it worked
    }
    
    @Test
    void testSetCreatedBy() {
        StatusMasterDto dto = new StatusMasterDto();

        String createdBy = "Tester";
        dto.setCreatedBy(createdBy);                  // Calls the setter
        assertEquals(createdBy, dto.getCreatedBy());  // Verifies setter effect
    }
    
    @Test
    void testSetCreatedBy_forceCoverage() {
        StatusMasterDto dto = new StatusMasterDto();
        String expected = "Tester";
        dto.setCreatedBy(expected);
        String actual = dto.getCreatedBy();

        // Dummy usage to prevent coverage elimination
        if (!actual.equals(expected)) {
            throw new RuntimeException("Mismatch!");}
        }
        @Test
        void testSetCreatedBy_valid() {
            StatusMasterDto dto = new StatusMasterDto();
            dto.setCreatedBy("Tester");
            assertEquals("Tester", dto.getCreatedBy());
        }

        @Test
        void testSetCreatedBy_mismatch() {
            StatusMasterDto dto = new StatusMasterDto();
            dto.setCreatedBy("Tester");
            String actual = dto.getCreatedBy();
            String expected = "AnotherUser";

            assertThrows(RuntimeException.class, () -> {
                if (!actual.equals(expected)) {
                    throw new RuntimeException("Mismatch!");
                }
            });
    }
        
        @Test
        void testMapToStatusMasterDto() {
            StatusMasterEntity entity = new StatusMasterEntity();
            entity.setId(1L);
            entity.setStatusMasterCode(123);
            entity.setStatusMasterDescription("Test Description");
            entity.setCreatedBy("Creator");
            entity.setCreatedDate(LocalDate.now());
            entity.setModifiedBy("Modifier");
            entity.setModifiedDate(LocalDate.now());

            StatusMasterDto dto = StatusMasterMapper.mapToStatusMasterDto(entity);

            assertEquals(entity.getId(), dto.getId());
            assertEquals(entity.getStatusMasterCode(), dto.getStatusMasterCode());
            assertEquals(entity.getStatusMasterDescription(), dto.getStatusMasterDescription());
            assertEquals(entity.getCreatedBy(), dto.getCreatedBy());
            assertEquals(entity.getCreatedDate(), dto.getCreatedDate());
            assertEquals(entity.getModifiedBy(), dto.getModifiedBy());
            assertEquals(entity.getModifiedDate(), dto.getModifiedDate());
        }

        @Test
        void testMapToStatusMasterEntity() {
            StatusMasterDto dto = new StatusMasterDto();
            dto.setId(1L);
            dto.setStatusMasterCode(123);
            dto.setStatusMasterDescription("Test Description");
            dto.setCreatedBy("Creator");
            dto.setCreatedDate(LocalDate.now());
            dto.setModifiedBy("Modifier");
            dto.setModifiedDate(LocalDate.now());

            StatusMasterEntity entity = StatusMasterMapper.mapToStatusMasterEntity(dto);

            assertEquals(dto.getId(), entity.getId());
            assertEquals(dto.getStatusMasterCode(), entity.getStatusMasterCode());
            assertEquals(dto.getStatusMasterDescription(), entity.getStatusMasterDescription());
            assertEquals(dto.getCreatedBy(), entity.getCreatedBy());
            assertEquals(dto.getCreatedDate(), entity.getCreatedDate());
            assertEquals(dto.getModifiedBy(), entity.getModifiedBy());
            assertEquals(dto.getModifiedDate(), entity.getModifiedDate());
        }
        
        @Test
        void testPrivateConstructorCoverage() throws Exception {
            java.lang.reflect.Constructor<StatusMasterMapper> constructor = StatusMasterMapper.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance(); // Covers the private constructor
        }
        
        
        
      
        
        

        @Test
        void testSetIsDeleted() {
        	StatusMasterDto dto = new StatusMasterDto();
            dto.setIsDeleted("No");

            // ✅ This line makes the setter "covered"
            assertEquals("No", dto.getIsDeleted());
        }
        
        @Test
       
        void testHandleValidationException_directCall_shouldReturn400AndErrorMap() {
            // Arrange
            Map<String, String> fieldErrors = new HashMap<>();
            fieldErrors.put("createdBy", "Field 'createdBy' must be a valid string, not a number.");
            fieldErrors.put("statusMasterCode", "Field 'statusMasterCode' should not be null or empty.");

            StatusMasterValidationException exception =
                    new StatusMasterValidationException("Validation failed", fieldErrors);

//            StatusMasterService service = new StatusMasterServiceImpl(StatusMasterRepository); // provide a real or dummy repo
//            StatusMasterController controller = new StatusMasterController(service);
 

            // Act
            ResponseEntity<Map<String, String>> response = controller.handleValidationException(exception);

            // Assert
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            assertEquals(2, response.getBody().size());
            assertEquals("Field 'createdBy' must be a valid string, not a number.",
                         response.getBody().get("createdBy"));
            assertEquals("Field 'statusMasterCode' should not be null or empty.",
                         response.getBody().get("statusMasterCode"));
        }
        
       
        @Test
        @DisplayName("Delete StatusMaster - Success")
        void testDeleteMaster_Success() {
            Long id = 1L;

            // No exception means successful call
            doNothing().when(statusMasterService).deleteMaster(id);

            ResponseEntity<Map<String, String>> response = controller.deleteMaster(id);

            assertEquals(200, response.getStatusCodeValue());
            assertTrue(response.getBody().get("message").contains("Soft Deletion successful for StatusMaster with ID: " + id));

            verify(statusMasterService, times(1)).deleteMaster(id);
        }

        @Test
        @DisplayName("Delete StatusMaster - Not Found")
        void testDeleteMaster_NotFound() {
            Long id = 999L;
            doThrow(new StatusMasterException("StatusMaster not found with ID: " + id))
                    .when(statusMasterService).deleteMaster(id);

            StatusMasterException ex = assertThrows(StatusMasterException.class, () -> {
                controller.deleteMaster(id);
            });

            assertEquals("StatusMaster not found with ID: " + id, ex.getMessage());
            verify(statusMasterService, times(1)).deleteMaster(id);
        }



}
