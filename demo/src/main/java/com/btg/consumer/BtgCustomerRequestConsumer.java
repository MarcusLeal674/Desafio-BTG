package com.btg.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.btg.constants.BtgRabbitMQConstants;
import com.btg.repository.CustomerRequestRepository;

import model.CustomerRequest;

@Component
public class BtgCustomerRequestConsumer {
	
	private static final Logger log = LoggerFactory.getLogger(BtgCustomerRequestConsumer.class);
	
	@Autowired
	private CustomerRequestRepository customerRequestRepository;
	
	/**
	 * Este método irá trazer todos os dados que estão na fila do RabbitMQ e salvar no MongoDB 
	 * @param customerRequest
	 */
	@RabbitListener(queues = BtgRabbitMQConstants.CUSTOMER_REQUEST_QUEUE)
	public void consumer(CustomerRequest customerRequest) {
		log.info("Component: Starting the consumer Method");
		this.customerRequestRepository.save(customerRequest);
	}

}
