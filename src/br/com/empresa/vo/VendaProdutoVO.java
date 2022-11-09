package br.com.empresa.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class VendaProdutoVO implements Serializable {

	private static final long serialVersionUID = 3089414096459370641L;

	private BigInteger id;
	
	//Produto
	private ProdutoVO produt;
	
	//Valor unitário - 7 inteiros e 2 decimais
	private BigDecimal valuni;
	
	//Quantidade vendida - 7 inteiros e 2 decimais
	private BigDecimal qtdven;
	
	//Cliente
	private ClienteVO client;
	
	public VendaProdutoVO() {
		super();
	}

	public VendaProdutoVO(BigInteger id) {
		super();
		this.id = id;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public ProdutoVO getProdut() {
		return produt;
	}

	public void setProdut(ProdutoVO produt) {
		this.produt = produt;
	}

	public BigDecimal getValuni() {
		return valuni;
	}

	public void setValuni(BigDecimal valuni) {
		this.valuni = valuni;
	}

	public BigDecimal getQtdven() {
		return qtdven;
	}

	public void setQtdven(BigDecimal qtdven) {
		this.qtdven = qtdven;
	}

	public ClienteVO getClient() {
		return client;
	}

	public void setClient(ClienteVO client) {
		this.client = client;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VendaProdutoVO other = (VendaProdutoVO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
