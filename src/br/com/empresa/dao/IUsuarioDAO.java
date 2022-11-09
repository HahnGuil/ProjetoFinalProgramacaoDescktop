package br.com.empresa.dao;

import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.UsuarioVO;

public interface IUsuarioDAO {

	/**
	 * Valida se um usuário e senha estão corretamente informados.
	 * 
	 * @param login
	 * @param senha
	 * @return
	 * @throws BOValidationException
	 * @throws BOException
	 */
	public abstract UsuarioVO buscarUsuario(String login, String senha) throws BOValidationException, BOException;

}
