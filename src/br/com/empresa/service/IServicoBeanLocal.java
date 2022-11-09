package br.com.empresa.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.PessoaVO;
import br.com.empresa.vo.ProdutoVO;
import br.com.empresa.vo.UsuarioClienteVO;
import br.com.empresa.vo.UsuarioVO;

public interface IServicoBeanLocal {

	/**
	 * Valida se um usuário e senha estão corretamente informados.
	 * 
	 * @param login
	 * @param senha
	 * @return
	 * @throws BOValidationException
	 * @throws BOException
	 */
	public UsuarioVO validarAcesso(String login, String senha) throws BOValidationException, BOException;

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

	/**
	 * Lista todas as pessoas de um determinado cliente.
	 * 
	 * @param cliente
	 * @return
	 * @throws BOException
	 */
	public abstract List<PessoaVO> listarPessoas(ClienteVO cliente) throws BOException;

	/**
	 * Lista todos as pessoas de um determinado cliente fazendo um filtro dos mesmos.
	 * 
	 * @param tipoPessoa
	 * @param nomePessoa
	 * @param cpfCnpj
	 * @param cidade
	 * @param estado
	 * @param cliente
	 * @return
	 * @throws BOException
	 */
	public abstract List<PessoaVO> listarPessoas(String tipoPessoa, String nomePessoa, String cpfCnpj, String cidade, String estado,
			ClienteVO cliente) throws BOValidationException, BOException;

	/**
	 * Lista todos as pessoas de um determinado cliente fazendo um filtro dos mesmos.
	 * 
	 * @param first
	 * @param pageSize
	 * @param filters
	 * @param cliente
	 * @return
	 * @throws BOException
	 */
	public abstract List<PessoaVO> listarPessoas(int first, int pageSize, Map<String, Object> filters, ClienteVO cliente) throws BOException;

	/**
	 * Exclui uma pessoa.
	 * 
	 * @param pessoaVO
	 * @throws BOException
	 */
	public abstract void excluirPessoa(PessoaVO pessoaVO) throws BOException;

	/**
	 * Salvar uma pessoa.
	 * 
	 * @param pessoaVO
	 * @throws BOValidationException
	 * @throws BOException
	 */
	public abstract void salvarPessoa(PessoaVO pessoaVO) throws BOValidationException, BOException;

	/**
	 * Busca um determinado produto a partir do seu código de identificação.
	 * 
	 * @param produtoVO
	 * @return
	 * @throws BOException
	 */
	public abstract ProdutoVO buscarProdutoPorId(ProdutoVO produtoVO) throws BOException;

	/**
	 * Lista todos os produtos disponíveis.
	 * 
	 * @param id
	 * @param descri
	 * @param status
	 * @param codbar
	 * @param client
	 * @return
	 * @throws BOException
	 */
	public abstract List<ProdutoVO> listarProduto(BigInteger id, String descri, String status, String codbar, ClienteVO client) throws BOException;

	/**
	 * 
	 * @param id
	 * @param descri
	 * @param status
	 * @param codbar
	 * @param client
	 * @return
	 * @throws BOException
	 */
	public abstract int listarProdutoCount(BigInteger id, String descri, String status, String codbar, ClienteVO client) throws BOException;

	/**
	 * Lista todos os produtos disponíveis.
	 * 
	 * @param first
	 * @param pageSize
	 * @param filters
	 * @param cliente
	 * @return
	 * @throws BOException
	 */
	public abstract List<ProdutoVO> listarProduto(int first, int pageSize, Map<String, Object> filters, ClienteVO cliente) throws BOException;

	/**
	 * Consulta da quantidade de produtos existentes na base de dados.
	 * 
	 * @param filters
	 * @param cliente
	 * @return
	 * @throws BOException
	 */
	public abstract int listarProdutoCount(Map<String, Object> filters, ClienteVO cliente) throws BOException;

	/**
	 * Salva um determinado produto na base de dados.
	 * 
	 * @param produtoVO
	 * @throws BOValidationException
	 * @throws BOException
	 */
	public abstract void salvarProduto(ProdutoVO produtoVO) throws BOValidationException, BOException;

	/**
	 * Exclui um determinado produto da base de dados.
	 * 
	 * @param produtoVO
	 * @throws BOValidationException
	 * @throws BOException
	 */
	public abstract void excluirProduto(ProdutoVO produtoVO) throws BOValidationException, BOException;

}
