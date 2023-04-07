package model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.btg.dto.ItemsDTO;

@Document(collection = "customer_request_db")
public class CustomerRequest implements Serializable {
	
	private static final long serialVersionUID = -2856924708271801222L;
	
	@Id
    private String id;
	private int codigoPedido;
	private int codigoCliente;
	private List<ItemsDTO> itens;
	
	public CustomerRequest(int codigoPedido, int codigoCliente, List<ItemsDTO> itens) {
		super();
		this.codigoPedido = codigoPedido;
		this.codigoCliente = codigoCliente;
		this.itens = itens;
	}
	
	public CustomerRequest() {
		super();
	}



	public int getCodigoPedido() {
		return codigoPedido;
	}

	public void setCodigoPedido(int codigoPedido) {
		this.codigoPedido = codigoPedido;
	}

	public int getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(int codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public List<ItemsDTO> getItens() {
		return itens;
	}

	public void setItens(List<ItemsDTO> itens) {
		this.itens = itens;
	}	

}
