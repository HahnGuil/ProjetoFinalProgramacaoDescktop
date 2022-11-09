package br.com.empresa.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class VendaVO implements Serializable {
	
	private static final long serialVersionUID = 6777839680192609364L;

	private BigInteger id;
	
	//Data da venda - timestamp without timezone
	private Date datven;
	
	//Data da primeira parcela - timestamp without timezone
	private Date datpri;
	
	//Data da entrada - timestamp without timezone
	private Date datent;
	
	//Percentual de juros - 3 inteiros e 2 decimais
	private BigDecimal perjur;
	
	//Número de parcelas - Integer
	private Integer numpar;
	
	//Haverá entrada - 1 caractere
	private String entrad;
	
	//Usuário responsável pela venda.
	private UsuarioVO usuari;
	
	//Cliente
	private ClienteVO client;

	public VendaVO() {
		super();
	}

	public VendaVO(BigInteger id) {
		super();
		this.id = id;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public Date getDatven() {
		return datven;
	}

	public void setDatven(Date datven) {
		this.datven = datven;
	}

	public Date getDatpri() {
		return datpri;
	}

	public void setDatpri(Date datpri) {
		this.datpri = datpri;
	}

	public Date getDatent() {
		return datent;
	}

	public void setDatent(Date datent) {
		this.datent = datent;
	}

	public Integer getNumpar() {
		return numpar;
	}

	public void setNumpar(Integer numpar) {
		this.numpar = numpar;
	}

	public String getEntrad() {
		return entrad;
	}

	public void setEntrad(String entrad) {
		this.entrad = entrad;
	}

	public UsuarioVO getUsuari() {
		return usuari;
	}

	public void setUsuari(UsuarioVO usuari) {
		this.usuari = usuari;
	}

	public ClienteVO getClient() {
		return client;
	}

	public void setClient(ClienteVO client) {
		this.client = client;
	}
	
	public BigDecimal getPerjur() {
		return perjur;
	}

	public void setPerjur(BigDecimal perjur) {
		this.perjur = perjur;
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
		VendaVO other = (VendaVO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VendaVO [id=" + id + "]";
	}

}
