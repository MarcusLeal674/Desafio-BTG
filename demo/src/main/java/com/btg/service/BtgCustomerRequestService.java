package com.btg.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btg.dto.ItemsDTO;
import com.btg.repository.CustomerRequestRepository;

import model.CustomerRequest;

@Service
public class BtgCustomerRequestService {
	
	private static final Logger log = LoggerFactory.getLogger(BtgCustomerRequestService.class);

	@Autowired
	private RabbitTemplate rabbitTemplateBtg;
	
	@Autowired
	private CustomerRequestRepository customerRequestRepository;
	
	/**
	 * Este método irá enviar o Objeto CustomerRequest para ficar na fila do RabbitMQ
	 * @param nomeFila
	 * @param customerRequest
	 */
	public void sendMessage(String nomeFila, CustomerRequest customerRequest) {
		log.info("Service: Starting the sendMessage Method");
		
		this.rabbitTemplateBtg.convertAndSend(nomeFila, customerRequest);
	}
		
	private List<CustomerRequest> allCustomerRequest() {
		log.info("Service: Starting the allCustomerRequest Method");
		
		List<CustomerRequest> allCustomerRequest = this.customerRequestRepository.findAll();
		return allCustomerRequest;
	}
	
	/**
	 * Esse método irá trazer a soma de todos os preços
	 * @return 
	 */
	public Double totalOrderValue() {
		log.info("Service: Starting the totalOrderValue Method");
		Double addPreco = 0.0;		
		
		List<CustomerRequest> totalValue = this.allCustomerRequest();
		
		List<List<ItemsDTO>> listItemsDTO = this.listItemsDTO(totalValue);
		
		for (List<ItemsDTO> list : listItemsDTO) {
			for (ItemsDTO itemsDTO : list) { addPreco += itemsDTO.getPreco(); }
		}
		
		return addPreco;
	}
	
	/**
	 * Este método irá retornar a Quantidade de Pedidos por Cliente
	 * @param codClient
	 * @return
	 */
	public int numberOfOrdersPerCustomer(int codClient) {
		log.info("Service: Starting the numberOfOrdersPerCustomer Method");
		int count = 0;
		
		List<CustomerRequest> totalValue = this.customerRequestRepository.findByCodigoCliente(codClient);
		
		for (int i = 0; i <= totalValue.size(); i++) {
			count = i;
		}			
		
		return count;
	}
	
	/**
	 * Este método irá retornar a Lista de Pedidos Realizados por Clientes
	 * @param codClient
	 * @return
	 */
	public List<List<ItemsDTO>> listOrdersPlacedCustomers(int codClient) {
		log.info("Service: Starting the listOrdersPlacedCustomers Method");
		
		List<CustomerRequest> totalValue = this.customerRequestRepository.findByCodigoCliente(codClient);
		
		return listItemsDTO(totalValue);
	}

	private List<List<ItemsDTO>> listItemsDTO(List<CustomerRequest> totalValue) {	
		log.info("Service: Starting the listItemsDTO Method");
		
		List<List<ItemsDTO>> listItems = new ArrayList<>();
		
		for (CustomerRequest customerRequest : totalValue) {
			listItems.add(customerRequest.getItens());
		}
		
		return listItems;
	}
}
