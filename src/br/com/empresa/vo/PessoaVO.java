package br.com.empresa.vo;

import java.io.Serializable;
import java.math.BigInteger;

public class PessoaVO implements Serializable {

	private static final long serialVersionUID = -1241089139574999180L;

	private BigInteger id;

	//CPF CNPJ - 12 caracteres
	private String cpfcnp;

	//Tipo de pessoa - 1 caracteres
	private String tippes;

	//Nome - 50 caracteres
	private String descri;

	//CEP - 9 caracteres
	private Integer cepend;

	//Bairro - 30 caracteres
	private String baiend;

	//Rua - 80 caracteres
	private String ruaend;

	//Número - 20 caracteres
	private String numend;

	//Complemento - 80 caracteres
	private String comend;

	//Cidade - 30 caracteres
	private String cidade;

	//Estado - 2 caracteres
	private String estado;

	//Cliente
	private ClienteVO client;

	public PessoaVO() {

	}

	public PessoaVO(BigInteger id) {
		super();
		this.id = id;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getCpfcnp() {
		return cpfcnp;
	}

	public void setCpfcnp(String cpfcnp) {
		this.cpfcnp = cpfcnp;
	}

	public String getTippes() {
		return tippes;
	}

	public void setTippes(String tippes) {
		this.tippes = tippes;
	}

	public String getDescri() {
		return descri;
	}

	public void setDescri(String descri) {
		this.descri = descri;
	}

	public Integer getCepend() {
		return cepend;
	}

	public void setCepend(Integer cepend) {
		this.cepend = cepend;
	}

	public String getBaiend() {
		return baiend;
	}

	public void setBaiend(String baiend) {
		this.baiend = baiend;
	}

	public String getRuaend() {
		return ruaend;
	}

	public void setRuaend(String ruaend) {
		this.ruaend = ruaend;
	}

	public String getNumend() {
		return numend;
	}

	public void setNumend(String numend) {
		this.numend = numend;
	}

	public String getComend() {
		return comend;
	}

	public void setComend(String comend) {
		this.comend = comend;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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
		PessoaVO other = (PessoaVO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
