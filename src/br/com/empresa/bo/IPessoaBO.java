package br.com.empresa.bo;

import java.util.List;
import java.util.Map;

import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.PessoaVO;

public interface IPessoaBO {

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
	public abstract List<PessoaVO> listarPessoas(String tipoPessoa, String nomePessoa, String cpfCnpj, String cidade, String estado, ClienteVO cliente) throws BOValidationException, BOException;

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
	public abstract List<PessoaVO> listarPessoas(int first, int pageSize, Map<String, Object> filters, ClienteVO cliente) throws BOValidationException, BOException;

	/**
	 * Exclui uma pessoa.
	 * 
	 * @param pessoaVO
	 * @throws BOException
	 */
	public abstract void excluirPessoa(PessoaVO pessoaVO) throws BOValidationException, BOException;

	/**
	 * Salvar uma pessoa.
	 * 
	 * @param pessoaVO
	 * @throws BOValidationException
	 * @throws BOException
	 */
	public abstract void salvarPessoa(PessoaVO pessoaVO) throws BOValidationException, BOException;

}
