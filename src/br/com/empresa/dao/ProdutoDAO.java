package br.com.empresa.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.ProdutoVO;

public class ProdutoDAO implements IProdutoDAO {

	public ProdutoDAO() {
		// TODO implementar de forma correta posteriormente.
	}

	@Override
	public ProdutoVO buscarProdutoPorId(ProdutoVO produtoVO) throws BOException {

		List<ProdutoVO> produtoVOs = Dados.getProdutoVOs();

		for (ProdutoVO p : produtoVOs) {

			if (produtoVO.equals(p)) {
				return p;
			}
		}

		return null;
	}

	@Override
	public List<ProdutoVO> listarProduto(BigInteger id, String descri, String status, String codbar, ClienteVO client) throws BOException {

		// TODO implementar de forma correta posteriormente.

		List<ProdutoVO> produtoVOs = Dados.getProdutoVOs();
		List<ProdutoVO> retorno = new ArrayList<>();

		for (ProdutoVO produto : produtoVOs) {

			if (produto.getClient().equals(client) == false) {
				continue;
			}

			if (id != null) {
				if(produto.getId().equals(id) == false) {
					continue;
				}
			}
			
			if(descri != null && descri.trim().length() > 0) {
				if(produto.getDescri().contains(descri) == false) {
					continue;
				}
			}
			
			if(status != null) {
				if(produto.getStatus().equals(status) == false) {
					continue;
				}
			}
			
			if(codbar != null && codbar.trim().length() > 0) {
				if(produto.getCodbar().contains(codbar) == false) {
					continue;
				}
			}

			retorno.add(produto);
		}

		return retorno;
	}

	@Override
	public int listarProdutoCount(BigInteger id, String descri, String status, String codbar, ClienteVO client) throws BOException {

		// TODO implementar de forma correta posteriormente.

		List<ProdutoVO> produtoVOs = Dados.getProdutoVOs();

		if (produtoVOs != null) {
			return produtoVOs.size();
		}

		return 0;
	}

	@Override
	public List<ProdutoVO> listarProduto(int first, int pageSize, Map<String, Object> filters, ClienteVO cliente) throws BOException {

		// TODO implementar de forma correta posteriormente.

		List<ProdutoVO> produtoVOs = Dados.getProdutoVOs();

		return produtoVOs;
	}

	@Override
	public int listarProdutoCount(Map<String, Object> filters, ClienteVO cliente) throws BOException {

		// TODO implementar de forma correta posteriormente.

		List<ProdutoVO> produtoVOs = Dados.getProdutoVOs();

		if (produtoVOs != null) {
			return produtoVOs.size();
		}

		return 0;
	}

	@Override
	public void salvarProduto(ProdutoVO produtoVO) throws BOValidationException, BOException {

		List<ProdutoVO> produtoVOs = Dados.getProdutoVOs();

		if (produtoVO.getId() == null) {
			if (produtoVOs.size() > 0) {
				ProdutoVO ultimoProduto = produtoVOs.get(produtoVOs.size() - 1);
				produtoVO.setId(ultimoProduto.getId().add(BigInteger.ONE));
			} else {
				produtoVO.setId(BigInteger.ONE);
			}

			Dados.getProdutoVOs().add(produtoVO);
		} else {
			for (int i = 0; i < produtoVOs.size(); i++) {
				if (produtoVOs.get(i).equals(produtoVO)) {
					Dados.getProdutoVOs().set(i, produtoVO);
				}
			}
		}
	}

	@Override
	public void excluirProduto(ProdutoVO produtoVO) throws BOValidationException, BOException {

		for (int i = 0; i < Dados.getProdutoVOs().size(); i++) {
			if (Dados.getProdutoVOs().get(i).equals(produtoVO)) {
				Dados.getProdutoVOs().remove(i);
			}
		}

	}

}
