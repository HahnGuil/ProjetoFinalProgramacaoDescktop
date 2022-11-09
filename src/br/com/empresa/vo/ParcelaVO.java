package br.com.empresa.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class ParcelaVO implements Serializable {

	private static final long serialVersionUID = -5605106644026288160L;

	//Código
	private BigInteger id;
	
	//Data da parcela - timestamp without timezone
	private Date datpar;
	
	//Valor parcela - 7 inteiros e 2 decimais
	private BigDecimal vlrpar;
	
	//Valor juros - 7 inteiros e 2 decimais
	private BigDecimal vlrjur;
	
	//Valor multa - 7 inteiros e 2 decimais
	private BigDecimal vlrmul;
	
	//Valor total - 7 inteiros e 2 decimais
	private BigDecimal vlrtot;
	
	//Status - 1 caracter
	private String status;
	
	//Cliente
	private ClienteVO client;

	public ParcelaVO() {
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public Date getDatpar() {
		return datpar;
	}

	public void setDatpar(Date datpar) {
		this.datpar = datpar;
	}

	public BigDecimal getVlrpar() {
		return vlrpar;
	}

	public void setVlrpar(BigDecimal vlrpar) {
		this.vlrpar = vlrpar;
	}

	public BigDecimal getVlrjur() {
		return vlrjur;
	}

	public void setVlrjur(BigDecimal vlrjur) {
		this.vlrjur = vlrjur;
	}

	public BigDecimal getVlrmul() {
		return vlrmul;
	}

	public void setVlrmul(BigDecimal vlrmul) {
		this.vlrmul = vlrmul;
	}

	public BigDecimal getVlrtot() {
		return vlrtot;
	}

	public void setVlrtot(BigDecimal vlrtot) {
		this.vlrtot = vlrtot;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public ClienteVO getClient() {
		return client;
	}

	public void setClient(ClienteVO client) {
		this.client = client;
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
		ParcelaVO other = (ParcelaVO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ParcelaVO [id=" + id + "]";
	}
}
