package br.com.empresa.bo;

import java.util.List;

import br.com.empresa.exception.BOException;
import br.com.empresa.vo.UsuarioClienteVO;
import br.com.empresa.vo.UsuarioVO;

public interface IUsuarioClienteBO {
	
	/**
	 * Retorna todos as ligações entre cliente e usuário. 
	 * 
	 * @param usuarioVO
	 * @return
	 * @throws BOException
	 */
	public abstract List<UsuarioClienteVO> listarClientesUsuario(UsuarioVO usuarioVO) throws BOException;
	
	/**
	 * Busca a quantidade de ligações entre os clientes e os usuários.
	 * 
	 * @param usuarioVO
	 * @return
	 * @throws BOException
	 */
	public abstract int buscarQuantidadeClientesUsuario(UsuarioVO usuarioVO) throws BOException;

}
