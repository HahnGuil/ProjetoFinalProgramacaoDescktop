package br.com.empresa.dao;

import java.util.List;

import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.UsuarioVO;

public class UsuarioDAO implements IUsuarioDAO {

	public UsuarioDAO() {
		
	}

	@Override
	public UsuarioVO buscarUsuario(String login, String senha) throws BOValidationException, BOException {
		
		List<UsuarioVO> usuarioVOs = Dados.getUsuarioVOs();

		boolean encontrado = false;

		for (UsuarioVO usuarioVO : usuarioVOs) {
			if (usuarioVO.getLogusu().equals(login) && usuarioVO.getSenusu().equals(senha)) {
				return usuarioVO;
			}
		}
		
		return null;
	}

}
