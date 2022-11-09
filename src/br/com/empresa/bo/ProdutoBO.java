package br.com.empresa.bo;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import br.com.empresa.dao.IProdutoDAO;
import br.com.empresa.dao.ProdutoDAO;
import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.ProdutoVO;

public class ProdutoBO implements IProdutoBO {

	private IProdutoDAO produtoDAO;

	public ProdutoBO() {
		produtoDAO = new ProdutoDAO();
	}

	@Override
	public ProdutoVO buscarProdutoPorId(ProdutoVO produtoVO) throws BOException {

		if (produtoVO == null || produtoVO.getId() == null) {
			throw new BOException();
		}

		return produtoDAO.buscarProdutoPorId(produtoVO);
	}

	@Override
	public List<ProdutoVO> listarProduto(BigInteger id, String descri, String status, String codbar, ClienteVO client) throws BOException {

		if (client == null || client.getId() == null) {
			throw new BOException();
		}

		return produtoDAO.listarProduto(id, descri, status, codbar, client);
	}

	@Override
	public int listarProdutoCount(BigInteger id, String descri, String status, String codbar, ClienteVO client) throws BOException {

		if (client == null || client.getId() == null) {
			throw new BOException();
		}

		return produtoDAO.listarProdutoCount(id, descri, status, codbar, client);
	}

	@Override
	public List<ProdutoVO> listarProduto(int first, int pageSize, Map<String, Object> filters, ClienteVO client) throws BOException {

		if (client == null || client.getId() == null) {
			throw new BOException();
		}

		return produtoDAO.listarProduto(first, pageSize, filters, client);
	}

	@Override
	public int listarProdutoCount(Map<String, Object> filters, ClienteVO client) throws BOException {

		if (client == null || client.getId() == null) {
			throw new BOException();
		}

		return produtoDAO.listarProdutoCount(filters, client);
	}

	@Override
	public void salvarProduto(ProdutoVO produtoVO) throws BOValidationException, BOException {

		if (produtoVO == null) {
			throw new BOException();
		} else if (produtoVO.getClient() == null) {
			throw new BOException();
		} else if (produtoVO.getDescri() == null || produtoVO.getDescri().trim().length() == 0) {
			throw new BOValidationException("Descrição: erro de validação. Preenchimento obrigatório.");
		} else if (produtoVO.getStatus() == null) {
			throw new BOValidationException("Status: erro de validação. Preenchimento obrigatório.");
		} else if (produtoVO.getStatus().equals("A")) { //Se for ativo.

			if (produtoVO.getQtdest() == null) {
				throw new BOValidationException("Quantidade em estoque: erro de validação. Preenchimento obrigatório.");
			} else if (produtoVO.getValcom() == null) {
				throw new BOValidationException("Valor de compra: erro de validação. Preenchimento obrigatório.");
			} else if (produtoVO.getValven() == null) {
				throw new BOValidationException("Valor de venda: erro de validação. Preenchimento obrigatório.");
			}

		}

		produtoDAO.salvarProduto(produtoVO);
	}

	@Override
	public void excluirProduto(ProdutoVO produtoVO) throws BOValidationException, BOException {

		if (produtoVO == null) {
			throw new BOException();
		} else if (produtoVO.getId() == null) {
			throw new BOException();
		}
		
		//TODO: No futuro fazer consulta se o produto foi utilizado em uma venda.

		produtoDAO.excluirProduto(produtoVO);

	}

}
