package com.btg.objects;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.btg.dto.ItemsDTO;

import model.CustomerRequest;

public class ObjectsTests {
	
	private static final Logger log = LoggerFactory.getLogger(ObjectsTests.class);	
	
	public CustomerRequest objectCustomerRequest() {
		log.info("Tests: Starting the objectCustomerRequest Method");
		
		CustomerRequest customerRequest = new CustomerRequest();			
		customerRequest.setCodigoCliente(1);
		customerRequest.setCodigoPedido(2);		
		customerRequest.setItens(this.listItens());
		
		return customerRequest;
	}

	public List<ItemsDTO> listItens() {
		log.info("Tests: Starting the listItens Method");
		
		List<ItemsDTO> itens = new ArrayList<>();
		itens.add(this.itemsDTO());
		
		return itens;
	}
	
	public ItemsDTO itemsDTO() {
		log.info("Tests: Starting the itemsDTO Method");
		
		ItemsDTO itemsDTO = new ItemsDTO();
		itemsDTO.setPreco(1.0);
		itemsDTO.setProduto("Produto");
		itemsDTO.setQuantidade(2);
		
		return itemsDTO;
	}
	
	public List<CustomerRequest> listObjectCustomerRequest() {
		List<CustomerRequest> listCustomerRequest = new ArrayList<>();
		listCustomerRequest.add(this.objectCustomerRequest());
		
		return listCustomerRequest;
	}

}
