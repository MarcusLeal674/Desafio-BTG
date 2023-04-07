package com.btg.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.btg.dto.ItemsDTO;
import com.btg.objects.ObjectsTests;
import com.btg.service.BtgCustomerRequestService;

import model.CustomerRequest;

@ExtendWith(MockitoExtension.class)
public class BtgCustomerRequestControllerTest {
	
	private static final Logger log = LoggerFactory.getLogger(BtgCustomerRequestControllerTest.class);
	private static final int ONE = 1;
	
	@InjectMocks
	BtgCustomerRequestController btgCustomerRequestController;
	
	@Mock
	private BtgCustomerRequestService btgCustomerRequestService;
	
	@DisplayName("Send Message to rabbitTemplate in service layer")
	@Test
	public void sendMessageTest() {
		log.info("Tests: Starting the sendMessageTest Method");
		
		ObjectsTests objectsTests = new ObjectsTests();
		
		doNothing().when(btgCustomerRequestService).sendMessage(anyString(), any(CustomerRequest.class));
		
		ResponseEntity<CustomerRequest> sendMessageReturn = btgCustomerRequestController.sendMessage(objectsTests.objectCustomerRequest());
		
		Assertions.assertEquals(HttpStatus.OK.value(), sendMessageReturn.getStatusCode().value());
	}
	
	@DisplayName("Returns total order value from the customer")
	@Test
	public void totalOrderValueTest() throws Exception {
		log.info("Tests: Starting the totalOrderValueTest Method");
		
		Mockito.when(btgCustomerRequestService.totalOrderValue()).thenReturn(0.0);
		
		ResponseEntity<String> totalOrderValueReturn = btgCustomerRequestController.totalOrderValue();
		
		Assertions.assertEquals(HttpStatus.OK.value(), totalOrderValueReturn.getStatusCode().value());
	}
	
	@DisplayName("Returns Number of Orders per Customer")
	@Test
	public void numberOfOrdersPerCustomerTest() throws Exception {
		log.info("Tests: Starting the numberOfOrdersPerCustomerTest Method");
		
		Mockito.when(btgCustomerRequestService.numberOfOrdersPerCustomer(anyInt())).thenReturn(ONE);
		
		ResponseEntity<String> numberOfOrdersPerCustomerReturn = btgCustomerRequestController.numberOfOrdersPerCustomer(ONE);
		
		Assertions.assertEquals(HttpStatus.OK.value(), numberOfOrdersPerCustomerReturn.getStatusCode().value());
	}
	
	@DisplayName("Returns list orders placed customers")
	@Test
	public void listOrdersPlacedCustomersTest() throws Exception {
		log.info("Tests: Starting the listOrdersPlacedCustomersTest Method");
		
		ObjectsTests objectsTests = new ObjectsTests();
		
		List<List<ItemsDTO>> listTolist = new ArrayList<>();
		listTolist.add(objectsTests.listItens());
		
		Mockito.when(btgCustomerRequestService.listOrdersPlacedCustomers(anyInt())).thenReturn(listTolist);
		
		ResponseEntity<List<List<ItemsDTO>>> listOrdersPlacedCustomersReturn = btgCustomerRequestController.listOrdersPlacedCustomers(ONE);
		
		Assertions.assertEquals(HttpStatus.OK.value(), listOrdersPlacedCustomersReturn.getStatusCode().value());
	}

}
