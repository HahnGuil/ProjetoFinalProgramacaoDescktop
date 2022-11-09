package br.com.empresa.vo;

import java.io.Serializable;
import java.math.BigInteger;

public class UsuarioClienteVO implements Serializable {

	private static final long serialVersionUID = -160930675481611138L;

	private BigInteger id;

	//Usuário
	private UsuarioVO usuarioVO;

	//Cliente
	private ClienteVO clienteVO;

	public UsuarioClienteVO() {
	}

	public UsuarioClienteVO(UsuarioVO usuarioVO, ClienteVO clienteVO) {
		super();
		this.usuarioVO = usuarioVO;
		this.clienteVO = clienteVO;
	}

	public UsuarioVO getUsuarioVO() {
		return usuarioVO;
	}

	public void setUsuarioVO(UsuarioVO usuarioVO) {
		this.usuarioVO = usuarioVO;
	}

	public ClienteVO getClienteVO() {
		return clienteVO;
	}

	public void setClienteVO(ClienteVO clienteVO) {
		this.clienteVO = clienteVO;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clienteVO == null) ? 0 : clienteVO.hashCode());
		result = prime * result + ((usuarioVO == null) ? 0 : usuarioVO.hashCode());
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
		UsuarioClienteVO other = (UsuarioClienteVO) obj;
		if (clienteVO == null) {
			if (other.clienteVO != null)
				return false;
		} else if (!clienteVO.equals(other.clienteVO))
			return false;
		if (usuarioVO == null) {
			if (other.usuarioVO != null)
				return false;
		} else if (!usuarioVO.equals(other.usuarioVO))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return clienteVO.getDescri();
	}

}
