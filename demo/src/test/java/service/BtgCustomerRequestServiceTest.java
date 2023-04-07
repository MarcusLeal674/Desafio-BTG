package service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;

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
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.btg.dto.ItemsDTO;
import com.btg.objects.ObjectsTests;
import com.btg.repository.CustomerRequestRepository;
import com.btg.service.BtgCustomerRequestService;

import model.CustomerRequest;

@ExtendWith(MockitoExtension.class)
public class BtgCustomerRequestServiceTest {

	private static final Logger log = LoggerFactory.getLogger(BtgCustomerRequestServiceTest.class);
	private static final String TEST_NAME = "testName";
	private static final int ONE = 1;

	@InjectMocks
	private BtgCustomerRequestService btgCustomerRequestService;

	@Mock
	private RabbitTemplate rabbitTemplateBtg;

	@Mock
	private CustomerRequestRepository customerRequestRepository;

	@DisplayName("Send Message to rabbitTemplate in service layer")
	@Test
	public void sendMessageServiceTest() {
		log.info("Tests: Starting the sendMessageServiceTest Method");

		ObjectsTests objectsTests = new ObjectsTests();

		doNothing().when(rabbitTemplateBtg).convertAndSend(anyString(), any(CustomerRequest.class));

		btgCustomerRequestService.sendMessage(TEST_NAME, objectsTests.objectCustomerRequest());
	}
	
	@DisplayName("Returns total order value from the customer")
	@Test
	public void totalOrderValueTest() throws Exception {
		log.info("Tests: Starting the totalOrderValueTest Method");
		
		double one = 1.0;
		
		ObjectsTests objectsTests = new ObjectsTests();
		
		Mockito.when(customerRequestRepository.findAll()).thenReturn(objectsTests.listObjectCustomerRequest());
		
		Double totalOrderValueReturn = btgCustomerRequestService.totalOrderValue();
		
		Assertions.assertEquals(one, totalOrderValueReturn);
	}
	
	@DisplayName("Returns number of orders per customer")
	@Test
	public void numberOfOrdersPerCustomerTest() throws Exception {
		log.info("Tests: Starting the numberOfOrdersPerCustomerTest Method");
		
		ObjectsTests objectsTests = new ObjectsTests();
		
		Mockito.when(customerRequestRepository.findByCodigoCliente(anyInt())).thenReturn(objectsTests.listObjectCustomerRequest());
		
		int countReturn = btgCustomerRequestService.numberOfOrdersPerCustomer(ONE);
		
		Assertions.assertEquals(ONE, countReturn);
	}
	
	@DisplayName("Returns list orders placed customers")
	@Test
	public void listOrdersPlacedCustomersTest() throws Exception {
		log.info("Tests: Starting the listOrdersPlacedCustomersTest Method");
		
		int two = 2;
		
		ObjectsTests objectsTests = new ObjectsTests();
		
		Mockito.when(customerRequestRepository.findByCodigoCliente(anyInt())).thenReturn(objectsTests.listObjectCustomerRequest());
		
		List<List<ItemsDTO>> countReturn = btgCustomerRequestService.listOrdersPlacedCustomers(ONE);
		
		Assertions.assertEquals(two, countReturn.get(0).get(0).getQuantidade());
	}

}
