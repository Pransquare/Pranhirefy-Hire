package com.pranhirefy.hire.fallbackcontroller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.pranhirefy.hire.controller.FallbackController;


public class StatusMasterFallBackControllerTest {
	 @Test
	    void testFallbackDirectCall_returns404AndMessage() {
	        FallbackController controller = new FallbackController();

	        ResponseEntity<String> response = controller.fallback();

	        assertNotNull(response);
	        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	        assertEquals("Invalid Base URL", response.getBody());
	    }

}
