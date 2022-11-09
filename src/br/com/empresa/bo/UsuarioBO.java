package br.com.empresa.bo;

import br.com.empresa.dao.IUsuarioDAO;
import br.com.empresa.dao.UsuarioDAO;
import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.UsuarioVO;

public class UsuarioBO implements IUsuarioBO {

	private IUsuarioDAO usuarioDAO;

	public UsuarioBO() {
		usuarioDAO = new UsuarioDAO();
	}

	@Override
	public UsuarioVO validarAcesso(String login, String senha) throws BOValidationException, BOException {

		if (login == null || login.trim().length() == 0) {
			throw new BOValidationException("Login: dado inválido ou não preenchido.");
		}

		if (senha == null || senha.trim().length() == 0) {
			throw new BOValidationException("Senha: dado inválido ou não preenchido.");
		}

		UsuarioVO usuarioVO = usuarioDAO.buscarUsuario(login, senha);

		if (usuarioVO == null) {
			throw new BOValidationException("Usuário e/ou Senha inválido!");
		}

		return usuarioVO;
	}

}
