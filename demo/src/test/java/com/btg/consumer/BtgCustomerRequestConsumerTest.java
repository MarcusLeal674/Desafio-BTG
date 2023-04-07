package com.btg.consumer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.btg.objects.ObjectsTests;
import com.btg.repository.CustomerRequestRepository;

@ExtendWith(MockitoExtension.class)
public class BtgCustomerRequestConsumerTest {
	
	private static final Logger log = LoggerFactory.getLogger(BtgCustomerRequestConsumerTest.class);
	
	@InjectMocks
	private BtgCustomerRequestConsumer btgCustomerRequestConsumer;
	
	@Mock
	private CustomerRequestRepository customerRequestRepository;;
	
	@Test
	public void requestConsumerTest() throws Exception {
		log.info("Tests: Starting the requestConsumerTest Method");
		
		ObjectsTests objectsTests = new ObjectsTests();
		btgCustomerRequestConsumer.consumer(objectsTests.objectCustomerRequest());
	}
	

}
