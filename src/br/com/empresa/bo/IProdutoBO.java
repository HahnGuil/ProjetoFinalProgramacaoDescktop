package br.com.empresa.bo;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.ProdutoVO;

/**
 * Descreve todos os métodos relacionados a operação de consulta e manutenção de produto.
 */
public interface IProdutoBO {

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
