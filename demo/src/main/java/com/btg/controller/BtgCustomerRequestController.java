package com.btg.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.btg.constants.BtgRabbitMQConstants;
import com.btg.dto.ItemsDTO;
import com.btg.service.BtgCustomerRequestService;

import model.CustomerRequest;

@RestController
@RequestMapping(value = "pedido")
public class BtgCustomerRequestController {
	
	private static final Logger log = LoggerFactory.getLogger(BtgCustomerRequestController.class);
	private static final String TOTAL_ORDER_VALUE = "Valor total do pedido: R$";
	private static final String NUMBER_OF_ORDERS_PER_CUSTOMER = "Quantidade de Pedidos por Cliente: ";
		
	@Autowired
	private BtgCustomerRequestService btgCustomerRequestService;
	
	@PostMapping
	public ResponseEntity<CustomerRequest> sendMessage(@RequestBody CustomerRequest customerRequest) {
		log.info("Controller: Starting the sendMessage Method");
		
		this.btgCustomerRequestService.sendMessage(BtgRabbitMQConstants.CUSTOMER_REQUEST_QUEUE, customerRequest);
		return new ResponseEntity<CustomerRequest>(customerRequest, HttpStatus.OK);
	}
		
	@GetMapping(value = "/valortotalpedido")
	public ResponseEntity<String> totalOrderValue() {
		log.info("Controller: Starting the totalOrderValue Method");
		
		Double totalValue = this.btgCustomerRequestService.totalOrderValue();
		return new ResponseEntity<String>(TOTAL_ORDER_VALUE + totalValue.toString(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/codigocliente")
	public ResponseEntity<String> numberOfOrdersPerCustomer(@RequestParam int codClient) {
		log.info("Controller: Starting the totalOrderValue Method");
		
		Integer qtdPedido = this.btgCustomerRequestService.numberOfOrdersPerCustomer(codClient);
		return new ResponseEntity<String>(NUMBER_OF_ORDERS_PER_CUSTOMER + qtdPedido.toString(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/listapedidoscliente")
	public ResponseEntity<List<List<ItemsDTO>>> listOrdersPlacedCustomers(@RequestParam int codClient) {
		log.info("Controller: Starting the listOrdersPlacedCustomers Method");
		
		List<List<ItemsDTO>> listItems = this.btgCustomerRequestService.listOrdersPlacedCustomers(codClient);
		return new ResponseEntity<List<List<ItemsDTO>>>(listItems, HttpStatus.OK);
	}

}
