package com.btg.connection;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import com.btg.constants.BtgRabbitMQConstants;

@Component
public class BtgRabbitMQConnection {
	
	private static final String NAME_EXCHANGE = "amq.direct";
	private AmqpAdmin amqpAdmin;	
	
	public BtgRabbitMQConnection(AmqpAdmin amqpAdmin) {
		this.amqpAdmin = amqpAdmin;
	}

	private Queue queue(String nameQueue) {
		return new Queue(nameQueue, true, false, false);
	}
	
	private DirectExchange directExchange() {
		return new DirectExchange(NAME_EXCHANGE);
	}
	
	private Binding relationship(Queue queue, DirectExchange directExchange) {
		return new Binding(queue.getName(), Binding.DestinationType.QUEUE, directExchange.getName(), queue.getName(), null);
	}
	
	/**
	 * Com a anotação PostConstruct assim que a Classe for instanciada pelo spring boot
	 * esse método irá ser chamado
	 * @param nameQueue
	 */
	@PostConstruct
	private void toAddNameQueue() { 
		
		Queue customerRequest = this.queue(BtgRabbitMQConstants.CUSTOMER_REQUEST_QUEUE);
		
		DirectExchange queueSwitch = this.directExchange();
		
		Binding connectioncustomerRequest = this.relationship(customerRequest, queueSwitch);
		
		//Criação das filas do RabbitMQ
		this.amqpAdmin.declareQueue(customerRequest);
		
		this.amqpAdmin.declareExchange(queueSwitch);
		
		this.amqpAdmin.declareBinding(connectioncustomerRequest);	
	}

}
