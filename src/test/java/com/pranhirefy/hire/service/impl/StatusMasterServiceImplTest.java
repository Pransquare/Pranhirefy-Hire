package com.pranhirefy.hire.service.impl;

import com.pranhirefy.hire.dto.StatusMasterDto;
import com.pranhirefy.hire.entity.StatusMasterEntity;
import com.pranhirefy.hire.exception.StatusMasterException;
import com.pranhirefy.hire.exception.StatusMasterValidationException;
import com.pranhirefy.hire.repository.StatusMasterRepository;
import com.pranhirefy.hire.service.StatusMasterService;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StatusMasterServiceImplTest {

    @Autowired
    @Mock
    private StatusMasterService statusMasterService;

    @Autowired
    @Mock
    private StatusMasterRepository statusMasterRepository;

    private static Long createdId;
    private static int counter = 101;

    private StatusMasterDto getValidDto() {
        StatusMasterDto dto = new StatusMasterDto();
        dto.setStatusMasterCode(counter++); // auto-increment for uniqueness
        dto.setStatusMasterDescription("Valid Description");
        dto.setCreatedBy("Tester");
        return dto;
    }

   

    @Test
    @Order(1)
    void testCreateStatusMasterSuccess() {
        // Arrange: Create a valid DTO
        StatusMasterDto dto = new StatusMasterDto();
        dto.setStatusMasterCode(101); // Make sure this code is unique
        dto.setCreatedBy("Admin");
        dto.setStatusMasterDescription("Test Description");

        // Act: Call the service to create
        StatusMasterDto saved = statusMasterService.createStatusMaster(dto);

        // Assert: Validate returned DTO
        assertNotNull(saved, "Saved DTO should not be null");
        assertNotNull(saved.getId(), "ID should be generated");
        assertEquals(dto.getStatusMasterCode(), saved.getStatusMasterCode());
        assertEquals(dto.getCreatedBy(), saved.getCreatedBy());
        assertEquals(dto.getStatusMasterDescription(), saved.getStatusMasterDescription());

        // Optionally: Store ID for later tests
        createdId = saved.getId();
    }



    @Test
    @Order(3)
    void testUpdateStatusMasterSuccess() {
        // Arrange: Fetch the existing record to get createdBy and createdDate
        StatusMasterDto existing = statusMasterService.getStatusMasterById(createdId);

        StatusMasterDto updateDto = new StatusMasterDto();
        updateDto.setStatusMasterCode(202); // New unique code for update
        updateDto.setStatusMasterDescription("Updated Description");
        updateDto.setModifiedBy("Updater"); // Updated value
        updateDto.setCreatedBy(existing.getCreatedBy()); // Must match existing
        updateDto.setCreatedDate(existing.getCreatedDate()); // Must match existing

        // Act: Call update
        StatusMasterDto updated = statusMasterService.updateStatusMaster(createdId, updateDto);

        // Assert: Verify update results
        assertNotNull(updated);
        assertEquals(updateDto.getStatusMasterCode(), updated.getStatusMasterCode());
        assertEquals(updateDto.getStatusMasterDescription(), updated.getStatusMasterDescription());
        assertEquals("Updater", updated.getModifiedBy());
        assertEquals(existing.getCreatedBy(), updated.getCreatedBy());
    }


    @Test
    @Order(4)
    void testUpdateStatusMasterChangingCreatedBy_shouldFail() {
        StatusMasterDto dto = statusMasterService.getStatusMasterById(createdId);

        StatusMasterDto updateDto = new StatusMasterDto();
        updateDto.setStatusMasterCode(999);
        updateDto.setStatusMasterDescription("Invalid Update");
        updateDto.setModifiedBy("InvalidUser");
        updateDto.setCreatedBy("Hacker"); // Different from original
        updateDto.setCreatedDate(dto.getCreatedDate());

        StatusMasterException ex = assertThrows(StatusMasterException.class, () -> {
            statusMasterService.updateStatusMaster(createdId, updateDto);
        });

        assertTrue(ex.getMessage().contains("createdBy"));
    }

    @Test
    @Order(5)
    void testUpdateStatusMasterChangingCreatedDate_shouldFail() {
        // Fetch an existing DTO from DB
        StatusMasterDto dto = statusMasterService.getStatusMasterById(createdId);
        assertNotNull(dto);
        assertNotNull(dto.getCreatedDate(), "createdDate should not be null");

        // Print current createdDate
        System.out.println("Original createdDate: " + dto.getCreatedDate());

        // Modify it
        StatusMasterDto updateDto = new StatusMasterDto();
        updateDto.setStatusMasterCode(555);
        updateDto.setStatusMasterDescription("Another Invalid");
        updateDto.setModifiedBy("Someone");
        updateDto.setCreatedBy(dto.getCreatedBy());
        updateDto.setCreatedDate(dto.getCreatedDate().minusDays(1)); // ❌ changed

        System.out.println("Modified createdDate: " + updateDto.getCreatedDate());

        // Expect exception
        StatusMasterException ex = assertThrows(StatusMasterException.class, () -> {
            statusMasterService.updateStatusMaster(createdId, updateDto);
        });

        System.out.println("Actual: " + ex.getMessage());

        assertEquals("Field createdDate cannot be changed during update.", ex.getMessage());
    }




    @Test
    @Order(6)
    void testGetStatusMasterById_success() {
        StatusMasterDto dto = statusMasterService.getStatusMasterById(createdId);
        assertNotNull(dto);
        assertEquals(createdId, dto.getId());
    }

    @Test
    @Order(7)
    void testGetStatusMasterById_invalidId() {
        StatusMasterException ex = assertThrows(StatusMasterException.class, () -> {
            statusMasterService.getStatusMasterById(999999L);
        });

        assertTrue(ex.getMessage().contains("not found"));
    }

    @Test
    @Order(8)
    void testGetAllMasters_success() {
        List<StatusMasterDto> list = statusMasterService.getAllMasters();
        assertNotNull(list);
        assertFalse(list.isEmpty());
    }

    @Test
    @Order(9)
    void testDeleteMaster_success() {
        // Act: Soft delete the record
        statusMasterService.deleteMaster(createdId);

        // Assert: Trying to fetch should throw StatusMasterException
        StatusMasterException ex = assertThrows(StatusMasterException.class, () -> {
            statusMasterService.getStatusMasterById(createdId);
        });

        assertTrue(ex.getMessage().contains("not found"));
    }


    @Test
    @Order(10)
    void testDeleteMaster_InvalidIdOrAlreadyDeleted_ShouldThrowException() {
        // Use a non-existing or soft-deleted ID
        Long invalidId = 999999L;

        StatusMasterException exception = assertThrows(StatusMasterException.class, () -> {
            statusMasterService.deleteMaster(invalidId);
        });

        assertEquals("StatusMaster not found or already deleted with ID: " + invalidId, exception.getMessage());
    }


    
    @Test
    @Order(11)
    void testUpdateStatusMaster_nullDto_shouldThrow() {
        StatusMasterException ex = assertThrows(StatusMasterException.class, () -> {
            statusMasterService.updateStatusMaster(1L, null);
        });
        assertEquals("Request body is null or empty.", ex.getMessage());
    }

    @Test
    @Order(12)
    void testUpdateStatusMaster_nullStatusMasterCode_shouldThrowValidationException() {
        StatusMasterDto dto = new StatusMasterDto();
        dto.setModifiedBy("Admin");
        dto.setStatusMasterDescription("Test Desc");

        StatusMasterValidationException ex = assertThrows(StatusMasterValidationException.class, () -> {
            statusMasterService.updateStatusMaster(1L, dto);
        });

        // Check that specific field error is returned
        assertTrue(ex.getFieldErrors().containsKey("statusMasterCode"));
        assertEquals("Field statusMasterCode should not be null or empty.",
                     ex.getFieldErrors().get("statusMasterCode"));
    }


    @Test
    @Order(13)
    void testUpdateStatusMaster_blankModifiedBy_shouldThrow() {
        StatusMasterDto dto = new StatusMasterDto();
        dto.setStatusMasterCode(101);
        dto.setModifiedBy(" "); // Blank modifiedBy
        dto.setStatusMasterDescription("Some description");

        StatusMasterValidationException ex = assertThrows(StatusMasterValidationException.class, () -> {
            statusMasterService.updateStatusMaster(1L, dto);
        });

        assertEquals("Validation failed", ex.getMessage());
        assertTrue(ex.getFieldErrors().containsKey("modifiedBy"));
        assertEquals("Field modifiedBy should not be null or empty.", ex.getFieldErrors().get("modifiedBy"));
    }


    @Test
    @Order(14)
    void testUpdateStatusMaster_numericModifiedBy_shouldThrow() {
        StatusMasterDto dto = new StatusMasterDto();
        dto.setStatusMasterCode(101);
        dto.setModifiedBy("12345"); // numeric input
        dto.setStatusMasterDescription("Valid description");

        StatusMasterValidationException ex = assertThrows(StatusMasterValidationException.class, () -> {
            statusMasterService.updateStatusMaster(1L, dto);
        });

        assertEquals("Validation failed", ex.getMessage());
        assertTrue(ex.getFieldErrors().containsKey("modifiedBy"));
        assertEquals("Field modifiedBy must be a valid string, not a number.",
                     ex.getFieldErrors().get("modifiedBy"));
    }


    @Test
    @Order(15)
    void testUpdateStatusMaster_blankDescription_shouldThrow() {
        StatusMasterDto dto = new StatusMasterDto();
        dto.setStatusMasterCode(101);
        dto.setModifiedBy("Updater");
        dto.setStatusMasterDescription("  "); // blank

        StatusMasterValidationException ex = assertThrows(StatusMasterValidationException.class, () -> {
            statusMasterService.updateStatusMaster(1L, dto);
        });

        assertEquals("Validation failed", ex.getMessage());
        assertTrue(ex.getFieldErrors().containsKey("statusMasterDescription"));
        assertEquals("Field statusMasterDescription should not be null or empty.",
                     ex.getFieldErrors().get("statusMasterDescription"));
    }


    @Test
    @Order(16)
    void testUpdateStatusMaster_numericOnlyDescription_shouldThrow() {
        StatusMasterDto dto = new StatusMasterDto();
        dto.setStatusMasterCode(101);
        dto.setModifiedBy("Updater");
        dto.setStatusMasterDescription("123456");

        StatusMasterValidationException ex = assertThrows(StatusMasterValidationException.class, () -> {
            statusMasterService.updateStatusMaster(1L, dto);
        });

        assertEquals("Validation failed", ex.getMessage());
        assertTrue(ex.getFieldErrors().containsKey("statusMasterDescription"));
        assertEquals("Field statusMasterDescription must not be numbers only.",
                     ex.getFieldErrors().get("statusMasterDescription"));
    }


    @Test
    @Order(17)
    void testUpdateStatusMaster_invalidCharactersInDescription_shouldThrow() {
        StatusMasterDto dto = new StatusMasterDto();
        dto.setStatusMasterDescription("101");

        dto.setModifiedBy("Updater");
        dto.setStatusMasterDescription("Invalid@Desc!");

        StatusMasterValidationException ex = assertThrows(StatusMasterValidationException.class, () -> {
            statusMasterService.updateStatusMaster(1L, dto);
        });

        assertEquals("Validation failed", ex.getMessage());
        assertTrue(ex.getFieldErrors().containsKey("statusMasterDescription"));
        assertEquals("Field statusMasterDescription contains invalid characters.",
                     ex.getFieldErrors().get("statusMasterDescription"));
    }

    
    @Test
    @Order(1)
    void testCreateStatusMaster_nullDto_shouldThrow() {
        StatusMasterValidationException ex = assertThrows(StatusMasterValidationException.class, () -> {
            statusMasterService.createStatusMaster(null);
        });

        assertNotNull(ex.getFieldErrors());
        assertTrue(ex.getFieldErrors().containsKey("body"));
        assertEquals("Request body is null or empty.", ex.getFieldErrors().get("body"));
    }

    @Test
    @Order(2)
    void testCreateStatusMaster_nullStatusMasterCode_shouldThrowValidationException() {
        // Arrange
        StatusMasterDto dto = new StatusMasterDto();
        dto.setStatusMasterCode(null); // testing null input
        dto.setCreatedBy("Admin");
        dto.setStatusMasterDescription("Valid Description");

        // Act & Assert
        StatusMasterValidationException ex = assertThrows(StatusMasterValidationException.class, () -> {
            statusMasterService.createStatusMaster(dto);
        });

        // Assert that the fieldErrors map contains the expected message
        assertNotNull(ex.getFieldErrors());
        assertTrue(ex.getFieldErrors().containsKey("statusMasterCode"));
      //  assertEquals("Field statusMasterCode must be an integer.", ex.getFieldErrors().get("statusMasterCode"));
    }





    @Test
    @Order(3)
    void testCreateStatusMaster_blankCreatedBy_shouldThrowValidationException() {
        // Arrange
        StatusMasterDto dto = new StatusMasterDto();
        dto.setStatusMasterCode(101);
        dto.setCreatedBy(" "); // blank (only whitespace)
        dto.setStatusMasterDescription("Valid description");

        // Act & Assert
        StatusMasterValidationException ex = assertThrows(StatusMasterValidationException.class, () -> {
            statusMasterService.createStatusMaster(dto);
        });

        // Validate the error message for the 'createdBy' field
        Map<String, String> errors = ex.getFieldErrors();
        assertTrue(errors.containsKey("createdBy"));
        assertEquals("Field createdBy should not be null or empty.", errors.get("createdBy"));
    }


    @Test
    @Order(4)
    void testCreateStatusMaster_numericCreatedBy_shouldThrowValidationException() {
        // Arrange
        StatusMasterDto dto = new StatusMasterDto();
        dto.setStatusMasterCode(101);
        dto.setCreatedBy("12345"); // invalid numeric input
        dto.setStatusMasterDescription("Valid description");

        // Act & Assert
        StatusMasterValidationException ex = assertThrows(StatusMasterValidationException.class, () -> {
            statusMasterService.createStatusMaster(dto);
        });

        // Check that the validation error map has the correct key and message
        assertTrue(ex.getFieldErrors().containsKey("createdBy"));
        assertEquals("Field createdBy must contain only letters and spaces.", ex.getFieldErrors().get("createdBy"));
    }


    @Test
    @Order(5)
    void testCreateStatusMaster_blankDescription_shouldThrowValidationException() {
        // Arrange
        StatusMasterDto dto = new StatusMasterDto();
        dto.setStatusMasterCode(101);
        dto.setCreatedBy("Admin");
        dto.setStatusMasterDescription("  "); // blank description

        // Act & Assert
        StatusMasterValidationException ex = assertThrows(StatusMasterValidationException.class, () -> {
            statusMasterService.createStatusMaster(dto);
        });

        // Validate specific error message for statusMasterDescription
        assertTrue(ex.getFieldErrors().containsKey("statusMasterDescription"));
        assertEquals("Field statusMasterDescription should not be null or empty.",
                     ex.getFieldErrors().get("statusMasterDescription"));
    }


    @Test
    @Order(6)
    void testCreateStatusMaster_numericOnlyDescription_shouldThrowValidationException() {
        // Arrange
        StatusMasterDto dto = new StatusMasterDto();
        dto.setStatusMasterCode(101);
        dto.setCreatedBy("Admin");
        dto.setStatusMasterDescription("123456"); // invalid: only numbers

        // Act & Assert
        StatusMasterValidationException ex = assertThrows(StatusMasterValidationException.class, () -> {
            statusMasterService.createStatusMaster(dto);
        });

        // Check specific validation error
        assertTrue(ex.getFieldErrors().containsKey("statusMasterDescription"));
        assertEquals("Field statusMasterDescription must not be numbers only.",
                     ex.getFieldErrors().get("statusMasterDescription"));
    }


    @Test
    @Order(7)
    void testCreateStatusMaster_invalidCharactersInDescription_shouldThrowValidationException() {
        // Arrange
        StatusMasterDto dto = new StatusMasterDto();
        dto.setStatusMasterCode(101);
        dto.setCreatedBy("Admin");
        dto.setStatusMasterDescription("Invalid@#$%"); // contains invalid characters

        // Act & Assert
        StatusMasterValidationException ex = assertThrows(StatusMasterValidationException.class, () -> {
            statusMasterService.createStatusMaster(dto);
        });

        // Check that the error message is correct for the invalid characters
        assertTrue(ex.getFieldErrors().containsKey("statusMasterDescription"));
        assertEquals("Field statusMasterDescription contains invalid characters.",
                     ex.getFieldErrors().get("statusMasterDescription"));
    }

    

    @Test
    @Order(18)
    void testSearchStatusMasters_success() {
        // Arrange: Create a new status that will be searched
        StatusMasterDto dto = new StatusMasterDto();
        dto.setStatusMasterCode(777);
        dto.setStatusMasterDescription("SearchTestDescription");
        dto.setCreatedBy("SearchTester");
        StatusMasterDto created = statusMasterService.createStatusMaster(dto);

        // Act
        List<StatusMasterDto> results = statusMasterService.searchStatusMasters("SearchTest");

        // Assert
        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertTrue(results.stream().anyMatch(r -> r.getStatusMasterDescription().contains("SearchTestDescription")));

        // Cleanup (optional)
        statusMasterService.deleteMaster(created.getId());
    }
    @Test
    @Order(19)
    void testSearchStatusMasters_noMatch_shouldThrow() {
        StatusMasterException ex = assertThrows(StatusMasterException.class, () -> {
            statusMasterService.searchStatusMasters("DescriptionThatDoesNotExist12345");
        });

        assertTrue(ex.getMessage().contains("No StatusMasters found with statusMasterDescription matching"));
    }

    @Test
    @Order(20)
    void testGetStatusMasterById_invalidIdNullOrZero_shouldThrow() {
        // Case 1: null ID
        StatusMasterException ex1 = assertThrows(StatusMasterException.class, () -> {
            statusMasterService.getStatusMasterById(null);
        });
        assertEquals("Invalid StatusMaster ID", ex1.getMessage());

        // Case 2: ID <= 0
        StatusMasterException ex2 = assertThrows(StatusMasterException.class, () -> {
            statusMasterService.getStatusMasterById(0L);
        });
        assertEquals("Invalid StatusMaster ID", ex2.getMessage());

      
    }

    
    @Test
    @Order(21)
    void testGetAllMasters_whenEmpty_shouldThrowException() {
        // First, delete all existing data to ensure repository is empty
        statusMasterRepository.deleteAll();

        StatusMasterException ex = assertThrows(StatusMasterException.class, () -> {
            statusMasterService.getAllMasters();
        });

        assertEquals("No StatusMasters available", ex.getMessage());
    }

    
    @Test
    @Order(22)
    void testGetStatusMasterById_nullId_shouldThrowException() {
        StatusMasterException ex = assertThrows(StatusMasterException.class, () -> {
            statusMasterService.getStatusMasterById(null);
        });
        assertEquals("Invalid StatusMaster ID", ex.getMessage());
    }

    @Test
    @Order(23)
    void testGetStatusMasterById_negativeId_shouldThrowException() {
        StatusMasterException ex = assertThrows(StatusMasterException.class, () -> {
            statusMasterService.getStatusMasterById(-5L);
        });
        assertEquals("Invalid StatusMaster ID", ex.getMessage());
    }

    @Test
    @Order(24)
    void testGetStatusMasterById_zeroId_shouldThrowException() {
        StatusMasterException ex = assertThrows(StatusMasterException.class, () -> {
            statusMasterService.getStatusMasterById(0L);
        });
        assertEquals("Invalid StatusMaster ID", ex.getMessage());
    }

    @Test
    @Order(30)
    void testUpdateStatusMaster_withNullOrInvalidId_shouldThrowException() {
        StatusMasterDto dto = new StatusMasterDto();
        dto.setStatusMasterCode(101);
        dto.setStatusMasterDescription("Valid Description");
        dto.setModifiedBy("Updater");

        // Case 1: null ID
        StatusMasterException ex1 = assertThrows(StatusMasterException.class, () -> {
            statusMasterService.updateStatusMaster(null, dto);
        });
        assertEquals("Invalid StatusMaster ID", ex1.getMessage());

        // Case 2: ID <= 0
        StatusMasterException ex2 = assertThrows(StatusMasterException.class, () -> {
            statusMasterService.updateStatusMaster(0L, dto);
        });
        assertEquals("Invalid StatusMaster ID", ex2.getMessage());

        StatusMasterException ex3 = assertThrows(StatusMasterException.class, () -> {
            statusMasterService.updateStatusMaster(-10L, dto);
        });
        assertEquals("Invalid StatusMaster ID", ex3.getMessage());
    }

    
    @Test
    void testCreateStatusMaster_WithModifiedDate_ShouldThrowException() {
        // Arrange
        StatusMasterDto dto = new StatusMasterDto();
        dto.setStatusMasterCode(1001);  // Valid code
        dto.setCreatedBy("admin");      // Valid creator
        dto.setStatusMasterDescription("Active"); // Valid description
        dto.setModifiedDate(LocalDate.now());     // This should not be set during creation

        // Act & Assert
        StatusMasterException ex = assertThrows(StatusMasterException.class, () -> {
            statusMasterService.createStatusMaster(dto);
        });

        assertEquals("Field modifiedDate should be derived automatically from 'createdDate' during creation", ex.getMessage());
    }

    @Test
    void testCreateStatusMaster_WithModifiedBy_ShouldThrowException() {
        // Arrange
        StatusMasterDto dto = new StatusMasterDto();
        dto.setStatusMasterCode(1002);                // Valid code
        dto.setCreatedBy("admin");                    // Valid createdBy
        dto.setStatusMasterDescription("Pending");    // Valid description
        dto.setModifiedBy("user123");                 // Should not be set explicitly during creation

        // Act & Assert
        StatusMasterException ex = assertThrows(StatusMasterException.class, () -> {
            statusMasterService.createStatusMaster(dto);
        });

        assertEquals("Field modifiedBy should be derived automatically from 'createdBy' during creation.", ex.getMessage());
    }

    @Test
    void testUpdateStatusMaster_WhenModifiedDateIsChanged_ShouldThrowException() {
        // Arrange
        Long id = 1L;

        // Simulate existing entity
        StatusMasterEntity existingEntity = new StatusMasterEntity();
        existingEntity.setId(id);
        existingEntity.setStatusMasterCode(1003);
        existingEntity.setStatusMasterDescription("Active");
        existingEntity.setCreatedBy("admin");
        existingEntity.setCreatedDate(LocalDate.of(2024, 6, 1));
        existingEntity.setModifiedBy("admin");
        existingEntity.setModifiedDate(LocalDate.of(2024, 6, 1));

    }

    @Test
    void testUpdateStatusMaster_ModifiedDateChanged_ThrowsException() {
        // Step 1: Create and save an initial StatusMasterEntity
        StatusMasterEntity original = new StatusMasterEntity();
        original.setStatusMasterCode(2001);
        original.setStatusMasterDescription("Original Description");
        original.setCreatedBy("admin");
        original.setCreatedDate(LocalDate.of(2024, 1, 1));
        original.setModifiedBy("admin");
        original.setModifiedDate(LocalDate.of(2024, 1, 1));
        StatusMasterEntity saved = statusMasterRepository.save(original);

        // Step 2: Prepare an update DTO with changed modifiedDate
        StatusMasterDto dto = new StatusMasterDto();
        dto.setStatusMasterCode(2001);
        dto.setStatusMasterDescription("Updated Description");
        dto.setCreatedBy("admin");
        dto.setCreatedDate(LocalDate.of(2024, 1, 1));
        dto.setModifiedBy("admin"); // This is allowed
        dto.setModifiedDate(LocalDate.of(2024, 2, 1)); // <- changed date to trigger error

        // Step 3: Call update and assert the expected exception
        StatusMasterException ex = assertThrows(StatusMasterException.class, () -> {
            statusMasterService.updateStatusMaster(saved.getId(), dto);
        });

        assertEquals("Field modifiedDate should be generated automatically and must not be changed manually.", ex.getMessage());
    }

    
    
    
    
    
    @Test
    void testCreateStatusMaster_whenStatusMasterCodeIsNull_thenThrowValidationException() {
        // Arrange
        StatusMasterDto dto = new StatusMasterDto();
        dto.setStatusMasterCode(null); // simulate missing code
        dto.setCreatedBy("admin");
        dto.setStatusMasterDescription("Test description");

        // Act & Assert
        StatusMasterValidationException exception = assertThrows(StatusMasterValidationException.class, () -> {
            statusMasterService.createStatusMaster(dto);
        });

        // Validate exception message
        assertEquals("Validation failed", exception.getMessage());

        // Validate field errors
        assertTrue(exception.getFieldErrors().containsKey("statusMasterCode"));
        assertEquals("Field statusMasterCode should not be null or empty.", exception.getFieldErrors().get("statusMasterCode"));
    }

    @Test
    void testUpdateStatusMaster_DuplicateCodeDifferentId_ThrowsValidationException() {
        // Step 1: Save an existing entity with code 123 and ID 1
        StatusMasterDto existingDto = new StatusMasterDto();
        existingDto.setStatusMasterCode(123);
        existingDto.setCreatedBy("Admin");
        existingDto.setStatusMasterDescription("Original description");
        StatusMasterDto saved = statusMasterService.createStatusMaster(existingDto);

        // Step 2: Try updating another ID (e.g., 9999) with same code 123
        StatusMasterDto updateDto = new StatusMasterDto();
        updateDto.setStatusMasterCode(123); // same code
        updateDto.setCreatedBy("Editor");
        updateDto.setStatusMasterDescription("Trying duplicate code");

        StatusMasterValidationException ex = assertThrows(StatusMasterValidationException.class, () -> {
            statusMasterService.updateStatusMaster(9999L, updateDto); // Different ID
        });

        assertTrue(ex.getFieldErrors().containsKey("statusMasterCode"));
        assertEquals("StatusMasterCode '123' already exists.", ex.getFieldErrors().get("statusMasterCode"));
    }
    
    
    @Test
    void testCreateStatusMaster_DuplicateCode_ThrowsValidationException() {
        // Step 1: Save an initial entity with statusMasterCode = 100
        StatusMasterDto existingDto = new StatusMasterDto();
        existingDto.setStatusMasterCode(100);
        existingDto.setCreatedBy("Admin");
        existingDto.setStatusMasterDescription("Initial Description");

        StatusMasterDto created = statusMasterService.createStatusMaster(existingDto);

        // Step 2: Try to create a new DTO with the same code
        StatusMasterDto duplicateDto = new StatusMasterDto();
        duplicateDto.setStatusMasterCode(100); // Same code
        duplicateDto.setCreatedBy("TestUser");
        duplicateDto.setStatusMasterDescription("Duplicate entry attempt");

        // Step 3: Assert exception is thrown
        StatusMasterValidationException ex = assertThrows(StatusMasterValidationException.class, () -> {
            statusMasterService.createStatusMaster(duplicateDto);
        });

        // Step 4: Assert the message contains the expected duplicate error
        assertTrue(ex.getFieldErrors().containsKey("statusMasterCode"));
        assertEquals("StatusMasterCode '100' already exists.",
                     ex.getFieldErrors().get("statusMasterCode"));
    }

    @Test
    void testCreateStatusMaster_NullDto_ThrowsValidationException() {
        StatusMasterValidationException ex = assertThrows(StatusMasterValidationException.class, () -> {
            statusMasterService.createStatusMaster(null);
        });

        assertNotNull(ex.getFieldErrors());
        assertTrue(ex.getFieldErrors().containsKey("body"));
        assertEquals("Request body is null or empty.", ex.getFieldErrors().get("body"));
    }
    
    @Test
    void testCreateStatusMaster_NullDto_ShouldThrowException() {
        StatusMasterValidationException exception = assertThrows(StatusMasterValidationException.class, () -> {
            statusMasterService.createStatusMaster(null);
        });

        assertEquals("Validation failed", exception.getMessage());
    }


    
    @Test
    void testCreateStatusMaster_DuplicateCode_ThrowsException() {
        // Arrange
        StatusMasterDto dto = new StatusMasterDto();
        dto.setStatusMasterCode(123);
        dto.setCreatedBy("John");
        dto.setStatusMasterDescription("Valid Description");

        // Simulate repository returning true (duplicate code exists)
        //when(statusMasterRepository.existsByStatusMasterCode(123)).thenReturn(true);

        // Act & Assert
       // StatusMasterException exception = assertThrows(StatusMasterException.class, () ->
           // statusMasterService.createStatusMaster(dto)
        //);

        // Assert correct message
       // assertEquals("StatusMaster with code 123 already exists", exception.getMessage());
    }





    
    




}














//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import com.pranhirefy.hire.dto.StatusMasterDto;
//import com.pranhirefy.hire.entity.StatusMasterEntity;
//import com.pranhirefy.hire.exception.StatusMasterException;
//import com.pranhirefy.hire.exception.StatusMasterValidationException;
//import com.pranhirefy.hire.mapper.StatusMasterMapper;
//import com.pranhirefy.hire.repository.StatusMasterRepository;
//
//@ExtendWith(MockitoExtension.class)
//class StatusMasterServiceImplTest {
// @InjectMocks
//    private StatusMasterServiceImpl statusMasterService;
//
//    @Mock
//    private StatusMasterRepository statusMasterRepository;
//
//    private StatusMasterDto validDto;
//
//    @BeforeEach
//    void setup() {
//        validDto = new StatusMasterDto();
//        validDto.setStatusMasterCode(101);
//        validDto.setCreatedBy("Tester");
//        validDto.setStatusMasterDescription("Valid Description");
//    }
//   
//
//    
//    @Test
//    void testCreateStatusMaster_NullDto_ThrowsValidationException() {
//        StatusMasterValidationException ex = assertThrows(StatusMasterValidationException.class, () -> {
//            statusMasterService.createStatusMaster(null);
//        });
//        assertEquals("Validation failed", ex.getMessage());
//    }
//
//
//    @Test
//    void testCreateStatusMaster_WithModifiedBy_ThrowsException() {
//        validDto.setModifiedBy("Someone");
//
//        StatusMasterException ex = assertThrows(StatusMasterException.class, () ->
//            statusMasterService.createStatusMaster(validDto)
//        );
//
//        assertTrue(ex.getMessage().contains("Field modifiedBy should be derived"));
//    }
//
//    @Test
//    void testCreateStatusMaster_DuplicateCode_ThrowsException() {
//        when(statusMasterRepository.existsByStatusMasterCode(validDto.getStatusMasterCode())).thenReturn(true);
//
//        StatusMasterException ex = assertThrows(StatusMasterException.class, () ->
//            statusMasterService.createStatusMaster(validDto)
//        );
//
//        assertEquals("StatusMaster with code 101 already exists", ex.getMessage());
//    }
//
//    @Test
//    void testCreateStatusMaster_Success() {
//        when(statusMasterRepository.existsByStatusMasterCode(validDto.getStatusMasterCode())).thenReturn(false);
//
//        StatusMasterEntity savedEntity = StatusMasterMapper.mapToStatusMasterEntity(validDto);
//        savedEntity.setId(1L);
//        when(statusMasterRepository.save(any())).thenReturn(savedEntity);
//
//        StatusMasterDto result = statusMasterService.createStatusMaster(validDto);
//
//        assertNotNull(result);
//        assertEquals(validDto.getStatusMasterCode(), result.getStatusMasterCode());
//    }
//
//    @Test
//    void testUpdateStatusMaster_InvalidId_ShouldThrow() {
//        StatusMasterDto dto = new StatusMasterDto();
//        StatusMasterException ex = assertThrows(StatusMasterException.class, () ->
//            statusMasterService.updateStatusMaster(-1L, dto)
//        );
//        assertEquals("Invalid StatusMaster ID", ex.getMessage());
//    }
//
//    @Test
//    void testUpdateStatusMaster_ChangingCreatedBy_ShouldFail() {
//        StatusMasterEntity existing = StatusMasterMapper.mapToStatusMasterEntity(validDto);
//        existing.setId(1L);
//        when(statusMasterRepository.findById(1L)).thenReturn(Optional.of(existing));
//
//        StatusMasterDto updateDto = new StatusMasterDto();
//        updateDto.setCreatedBy("Hacker"); // should trigger validation
//        updateDto.setCreatedDate(existing.getCreatedDate());
//        updateDto.setModifiedBy("User");
//        updateDto.setStatusMasterCode(101);
//        updateDto.setStatusMasterDescription("Update");
//
//        StatusMasterException ex = assertThrows(StatusMasterException.class, () ->
//            statusMasterService.updateStatusMaster(1L, updateDto)
//        );
//
//        assertEquals("Field createdBy cannot be changed during update.", ex.getMessage());
//    }
//
//    @Test
//    void testUpdateStatusMaster_Success() {
//        // Arrange - existing entity in DB
//        StatusMasterEntity existing = StatusMasterMapper.mapToStatusMasterEntity(validDto);
//        existing.setId(1L);
//        existing.setCreatedDate(LocalDate.of(2024, 1, 1));
//        existing.setIsDeleted("No");
//
//        // Ensure repository returns this entity
//        when(statusMasterRepository.findById(1L)).thenReturn(Optional.of(existing));
//
//        // Create update DTO that matches createdDate and createdBy exactly
//        StatusMasterDto updateDto = new StatusMasterDto();
//        updateDto.setStatusMasterCode(202); // Change
//        updateDto.setStatusMasterDescription("Updated Description"); // Change
//        updateDto.setCreatedBy("Tester"); // Match
//        updateDto.setCreatedDate(LocalDate.of(2024, 1, 1)); // Match
//        updateDto.setModifiedBy("Updater"); // New value
//
//        // Save will return same entity
//        when(statusMasterRepository.save(any())).thenAnswer(inv -> {
//            StatusMasterEntity e = inv.getArgument(0);
//            e.setId(1L);
//            return e;
//        });
//
//        // Act
//        StatusMasterDto updated = statusMasterService.updateStatusMaster(1L, updateDto);
//
//        // Assert
//        assertNotNull(updated);
//        assertEquals(202, updated.getStatusMasterCode());
//        assertEquals("Updated Description", updated.getStatusMasterDescription());
//    }
//
//
//    @Test
//    void testGetById_InvalidId_ThrowsException() {
//        StatusMasterException ex = assertThrows(StatusMasterException.class, () ->
//            statusMasterService.getStatusMasterById(0L)
//        );
//        assertEquals("Invalid StatusMaster ID", ex.getMessage());
//    }
//
//    @Test
//    void testGetById_NotFound_ShouldThrow() {
//        when(statusMasterRepository.findByIdAndIsDeleted(1L, "No")).thenReturn(Optional.empty());
//
//        StatusMasterException ex = assertThrows(StatusMasterException.class, () ->
//            statusMasterService.getStatusMasterById(1L)
//        );
//        assertTrue(ex.getMessage().contains("StatusMaster not found with ID"));
//    }
//
//    @Test
//    void testGetById_Success() {
//        StatusMasterEntity entity = StatusMasterMapper.mapToStatusMasterEntity(validDto);
//        entity.setId(1L);
//
//        when(statusMasterRepository.findByIdAndIsDeleted(1L, "No")).thenReturn(Optional.of(entity));
//
//        StatusMasterDto result = statusMasterService.getStatusMasterById(1L);
//
//        assertEquals(validDto.getStatusMasterCode(), result.getStatusMasterCode());
//    }
//
//    @Test
//    void testGetAllMasters_Empty_ShouldThrow() {
//        when(statusMasterRepository.findAllByIsDeleted("No")).thenReturn(List.of());
//
//        StatusMasterException ex = assertThrows(StatusMasterException.class, () ->
//            statusMasterService.getAllMasters()
//        );
//
//        assertEquals("No StatusMasters available", ex.getMessage());
//    }
//
//    @Test
//    void testGetAllMasters_Success() {
//        StatusMasterEntity entity = StatusMasterMapper.mapToStatusMasterEntity(validDto);
//        entity.setId(1L);
//        when(statusMasterRepository.findAllByIsDeleted("No")).thenReturn(List.of(entity));
//
//        List<StatusMasterDto> list = statusMasterService.getAllMasters();
//
//        assertFalse(list.isEmpty());
//        assertEquals(1, list.size());
//    }
//
//    @Test
//    void testDeleteMaster_Success() {
//        StatusMasterEntity entity = StatusMasterMapper.mapToStatusMasterEntity(validDto);
//        entity.setId(1L);
//        entity.setIsDeleted("No");
//
//        when(statusMasterRepository.findByIdAndIsDeleted(1L, "No")).thenReturn(Optional.of(entity));
//
//        statusMasterService.deleteMaster(1L);
//
//        verify(statusMasterRepository).save(entity);
//        assertEquals("Yes", entity.getIsDeleted());
//    }
//
//    @Test
//    void testSearchStatusMasters_NoMatch_ShouldThrow() {
//        when(statusMasterRepository.findByStatusMasterDescriptionContainingIgnoreCaseAndIsDeleted("xyz", "No"))
//            .thenReturn(List.of());
//
//        StatusMasterException ex = assertThrows(StatusMasterException.class, () ->
//            statusMasterService.searchStatusMasters("xyz")
//        );
//
//        assertTrue(ex.getMessage().contains("No StatusMasters found"));
//    }
//
//    @Test
//    void testSearchStatusMasters_Success() {
//        StatusMasterEntity entity = StatusMasterMapper.mapToStatusMasterEntity(validDto);
//        entity.setId(1L);
//
//        when(statusMasterRepository.findByStatusMasterDescriptionContainingIgnoreCaseAndIsDeleted("Valid", "No"))
//            .thenReturn(List.of(entity));
//
//        List<StatusMasterDto> results = statusMasterService.searchStatusMasters("Valid");
//
//        assertFalse(results.isEmpty());
//    }
//}


//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//import java.time.LocalDate;
//
//import org.hibernate.cache.spi.SecondLevelCacheLogger_.logger;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import com.pranhirefy.hire.dto.StatusMasterDto;
//import com.pranhirefy.hire.entity.StatusMasterEntity;
//import com.pranhirefy.hire.exception.StatusMasterException;
//import com.pranhirefy.hire.exception.StatusMasterValidationException;
//import com.pranhirefy.hire.mapper.StatusMasterMapper;
//import com.pranhirefy.hire.repository.StatusMasterRepository;
//
//@ExtendWith(MockitoExtension.class)
//    class StatusMasterServiceImplTest {
//
//        @Mock
//        private StatusMasterRepository statusMasterRepository;
//
//        private StatusMasterServiceImpl statusMasterService;
//
//        private StatusMasterDto validDto;
//
//        statusMasterService = new StatusMasterServiceImpl(statusMasterRepository);
//
//        @BeforeEach
//        void setup() {
//            validDto = new StatusMasterDto();
//            validDto.setStatusMasterCode(101);
//            validDto.setStatusMasterDescription("Valid Desc");
//            validDto.setCreatedBy("Tester");
//            validDto.setCreatedDate(LocalDate.of(2024, 1, 1));
//            validDto.setModifiedBy("Updater");
//            validDto.setModifiedDate(LocalDate.of(2024, 6, 18));
//        }
//
//
//        
//        public void StatusMasterServiceImpl(StatusMasterRepository statusmasterrepository) {
//            this.statusMasterRepository = statusmasterrepository;
//            //logger.info("StatusMasterServiceImpl initialized");
//        }
//        
//        
////        @Test
////        void testCreateStatusMaster_NullDto_ThrowsValidationException() {
////            StatusMasterValidationException ex = assertThrows(StatusMasterValidationException.class, () -> {
////                statusMasterService.createStatusMaster(null);
////            });
////            assertEquals("Validation failed", ex.getMessage());
////        }
//
//        @Test
//        void testCreateStatusMaster_NullDto_ThrowsValidationException() {
//            StatusMasterException ex = assertThrows(StatusMasterException.class, () ->
//                statusMasterService.createStatusMaster(null)
//            );
//            assertEquals("Request body is null or empty.", ex.getMessage()); // Match message from implementation
//        }
//        
//        @Test
//        void testMapToEntityAndBack_Success() {
//            StatusMasterEntity entity = StatusMasterMapper.mapToStatusMasterEntity(validDto);
//            StatusMasterDto back = StatusMasterMapper.mapToStatusMasterDto(entity);
//
//            assertNotNull(back);
//            assertEquals(validDto.getStatusMasterCode(), back.getStatusMasterCode());
//            assertEquals(validDto.getStatusMasterDescription(), back.getStatusMasterDescription());
//            assertEquals(validDto.getCreatedBy(), back.getCreatedBy());
//            assertEquals(validDto.getCreatedDate(), back.getCreatedDate());
//            assertEquals(validDto.getModifiedBy(), back.getModifiedBy());
//            assertEquals(validDto.getModifiedDate(), back.getModifiedDate());
//        }
//
//}
        


