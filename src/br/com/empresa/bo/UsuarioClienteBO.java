package br.com.empresa.bo;

import java.util.List;

import br.com.empresa.dao.IUsuarioClienteDAO;
import br.com.empresa.dao.UsuarioClienteDAO;
import br.com.empresa.exception.BOException;
import br.com.empresa.vo.UsuarioClienteVO;
import br.com.empresa.vo.UsuarioVO;

public class UsuarioClienteBO implements IUsuarioClienteBO {

	private IUsuarioClienteDAO usuarioClienteDAO;

	public UsuarioClienteBO() {
		usuarioClienteDAO = new UsuarioClienteDAO();
	}

	@Override
	public List<UsuarioClienteVO> listarClientesUsuario(UsuarioVO usuarioVO) throws BOException {

		//TODO: Realizar verificações adicionais.

		return usuarioClienteDAO.listarClientesUsuario(usuarioVO);
	}

	@Override
	public int buscarQuantidadeClientesUsuario(UsuarioVO usuarioVO) throws BOException {

		//TODO: Realizar verificações adicionais.

		return usuarioClienteDAO.buscarQuantidadeClientesUsuario(usuarioVO);
	}

}
