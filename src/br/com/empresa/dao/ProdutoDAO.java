package br.com.empresa.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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

		// LISTAR POR ID
		EntityManager em = HibernateUtil.getEntityManager();

		ProdutoVO produto = em.find(ProdutoVO.class, produtoVO.getId());

		em.close();

		return produto;
	}

	@Override
	public List<ProdutoVO> listarProduto(BigInteger id, String descri, String status, String codbar, ClienteVO client)
			throws BOException {

		EntityManager em = HibernateUtil.getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<ProdutoVO> criteria = cb.createQuery(ProdutoVO.class);

		// From
		Root<ProdutoVO> produtoFrom = criteria.from(ProdutoVO.class);

		// Where
		Predicate produtoWhere = cb.equal(produtoFrom.get("client"), client);

		// Filtro por ID
		if (id != null) {
			produtoWhere = cb.and(produtoWhere, cb.equal(produtoFrom.get("id"), id));
		}

		// Filtro por descrição
		if (descri != null && descri.trim().length() > 0) {
			produtoWhere = cb.and(produtoWhere,
					cb.like(
							cb.lower(produtoFrom.get("descri")),
							"%" + descri.toLowerCase() + "%"));
		}

		// Filtro por status
		if (status != null) {
			produtoWhere = cb.and(produtoWhere, cb.equal(produtoFrom.get("status"), status));
		}

		// Filtro por codigo de barras
		if (codbar != null && codbar.trim().length() > 0) {
			produtoWhere = cb.and(produtoWhere,
					cb.like(
							cb.lower(produtoFrom.get("codbar")),
							"%" + codbar.toLowerCase() + "%"));
		}

		criteria.where(produtoWhere);

		TypedQuery<ProdutoVO> query = em.createQuery(criteria);

		query.setMaxResults(25);

		List<ProdutoVO> retornoProdutos = query.getResultList();

		em.close();

		return retornoProdutos;
	}

	@Override
	public int listarProdutoCount(BigInteger id, String descri, String status, String codbar, ClienteVO client)
			throws BOException {

		// TODO implementar de forma correta posteriormente.

		// List<ProdutoVO> produtoVOs = Dados.getProdutoVOs();

		// if (produtoVOs != null) {
		// return produtoVOs.size();
		// }

		return 0;
	}

	@Override
	public List<ProdutoVO> listarProduto(int first, int pageSize, Map<String, Object> filters, ClienteVO cliente)
			throws BOException {

		// TODO implementar de forma correta posteriormente.

		List<ProdutoVO> produtoVOs = null; // Dados.getProdutoVOs();

		return produtoVOs;
	}

	@Override
	public int listarProdutoCount(Map<String, Object> filters, ClienteVO cliente) throws BOException {

		// TODO implementar de forma correta posteriormente.

		List<ProdutoVO> produtoVOs = null; // Dados.getProdutoVOs();

		if (produtoVOs != null) {
			return produtoVOs.size();
		}

		return 0;
	}

	@Override
	public void salvarProduto(ProdutoVO produtoVO) throws BOValidationException, BOException {

		// List<ProdutoVO> produtoVOs = null; // Dados.getProdutoVOs();
		EntityManager em = HibernateUtil.getEntityManager();

		try {

			if (produtoVO.getId() == null) {
				em.getTransaction().begin();
				em.persist(produtoVO);
				em.getTransaction().commit();
			} else {

				em.getTransaction().begin();
				ProdutoVO produtoBanco = em.find(ProdutoVO.class, produtoVO.getId());

				produtoBanco.setDescri(produtoVO.getDescri());
				produtoBanco.setCodbar(produtoVO.getCodbar());
				produtoBanco.setStatus(produtoVO.getStatus());
				produtoBanco.setQtdest(produtoVO.getQtdest());
				produtoBanco.setValcom(produtoVO.getValcom());
				produtoBanco.setValven(produtoVO.getValven());

				em.merge(produtoBanco);
				em.getTransaction().commit();
			}
		} catch (Exception e) {
			throw new BOException(e);
		} finally {
			em.close();
		}
	}

	@Override
	public void excluirProduto(ProdutoVO produtoVO) throws BOValidationException, BOException {

		EntityManager em = HibernateUtil.getEntityManager();

		try {
			em.getTransaction().begin();
			ProdutoVO produto = em.find(ProdutoVO.class, produtoVO.getId());
			em.remove(produto);
			em.getTransaction().commit();
		} catch (Exception e) {
			throw new BOException(e);
		} finally {
			em.close();
		}

	}

	@Override
	public void importarProdutosViaCSV(File file, ClienteVO clienteVO) throws BOException {

		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			EntityManager em = HibernateUtil.getEntityManager();

			String linha = null;
			int numLinha = 0;

			while ((linha = bufferedReader.readLine()) != null) {
				numLinha++;

				if (numLinha > 1) {
					String[] particionamento;

					if (linha.contains(",")) {
						particionamento = linha.split(",");
					} else {
						particionamento = linha.split(";");
					}

					String descri = particionamento[1].replaceAll("\\s+", " ");
					String codbar = particionamento[2].trim();

					ProdutoVO novoProduto = new ProdutoVO();
					novoProduto.setDescri(descri);
					novoProduto.setCodbar(codbar);
					novoProduto.setClient(clienteVO);
					novoProduto.setQtdest(BigDecimal.ZERO);
					novoProduto.setStatus("I");
					novoProduto.setValcom(BigDecimal.ZERO);
					novoProduto.setValven(BigDecimal.ZERO);

					em.getTransaction().begin();
					em.persist(novoProduto);
					em.getTransaction().commit();
				}
			}

			fileReader.close();
			bufferedReader.close();
			em.close();

		} catch (FileNotFoundException e) {
			throw new BOException(e);
		} catch (IOException e) {
			throw new BOException(e);
		}

	}

}
