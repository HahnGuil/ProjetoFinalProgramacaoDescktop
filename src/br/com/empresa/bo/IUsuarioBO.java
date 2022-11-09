package br.com.empresa.bo;

import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.UsuarioVO;

public interface IUsuarioBO {

	/**
	 * Valida se um usuário e senha estão corretamente informados.
	 * 
	 * @param login
	 * @param senha
	 * @return
	 * @throws BOValidationException
	 * @throws BOException
	 */
	public abstract UsuarioVO validarAcesso(String login, String senha) throws BOValidationException, BOException;

}
