package br.com.empresa.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.empresa.exception.BOException;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.PessoaVO;
import br.com.empresa.vo.ProdutoVO;

public class PessoaDAO implements IPessoaDAO {

	public PessoaDAO() {

	}

	@Override
	public List<PessoaVO> listarPessoas(ClienteVO cliente) throws BOException {

		// List<PessoaVO> pessoas = Dados.getPessoaVOs();
		List<PessoaVO> pessoas = buscarPessoasPorCliente(cliente);
		List<PessoaVO> retorno = new ArrayList<PessoaVO>();

		for (PessoaVO pessoaVO : pessoas) {

			if (pessoaVO.getClient().equals(cliente) == false) {
				continue;
			}

			retorno.add(pessoaVO);
		}

		return retorno;
	}

	public List<PessoaVO> buscarPessoasPorCliente(ClienteVO cliente) throws BOException {

		EntityManager em = HibernateUtil.getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PessoaVO> criteria = cb.createQuery(PessoaVO.class);

		// Cláusula from
		Root<PessoaVO> pessoaFrom = criteria.from(PessoaVO.class);

		// Cláusula Where
		// Predicate pessoaWhere = cb.equal(pessoaFrom.get("client"), cliente);

		return null;

	}

	@Override
	public List<PessoaVO> listarPessoas(String tipoPessoa, String nomePessoa, String cpfCnpj, String cidade,
			String estado, ClienteVO cliente) throws BOException {

		List<PessoaVO> pessoas = buscarPessoasPorCliente(cliente);
		List<PessoaVO> retorno = new ArrayList<PessoaVO>();

		for (PessoaVO pessoaVO : pessoas) {

			if (pessoaVO.getClient().equals(cliente) == false) {
				continue;
			}

			if (tipoPessoa != null) {
				if (pessoaVO.getTippes() != null && pessoaVO.getTippes().equals(tipoPessoa) == false) {
					continue;
				}
			}

			if (nomePessoa != null && nomePessoa.trim().length() > 0) {
				if (pessoaVO.getDescri().contains(nomePessoa) == false) {
					continue;
				}
			}

			if (cpfCnpj != null
					&& cpfCnpj.replaceAll("\\.", "").replaceAll("/", "").replaceAll("-", "").trim().length() > 1) {

				if (pessoaVO.getCpfcnp() != null && pessoaVO.getCpfcnp().contains(cpfCnpj) == false) {
					continue;
				}
			}

			if (cidade != null && cidade.trim().length() > 0) {
				if (pessoaVO.getCidade() != null && pessoaVO.getCidade().contains(cidade) == false) {
					continue;
				}
			}

			if (estado != null && estado.equals("ZZ") == false && estado.trim().length() > 0) {
				if (pessoaVO.getEstado() != null && pessoaVO.getEstado().contains(estado) == false) {
					continue;
				}
			}

			retorno.add(pessoaVO);

		}

		return retorno;
	}

	@Override
	public List<PessoaVO> listarPessoas(int first, int pageSize, Map<String, Object> filters, ClienteVO cliente)
			throws BOException {

		return null;
	}

	@Override
	public void excluirPessoa(PessoaVO pessoaVO) throws BOException {

		List<PessoaVO> pessoas = buscarPessoasPorCliente(pessoaVO.getClient());

		// for (int i = 0; i < pessoas.size(); i++) {
		// if (pessoas.get(i).equals(pessoaVO)) {
		// pessoas.remove(i);
		// }
		// }

	}

	@Override
	public void salvarPessoa(PessoaVO pessoaVO) throws BOException {

		List<PessoaVO> pessoaVOs = buscarPessoasPorCliente(pessoaVO.getClient());

		if (pessoaVO.getId() == null) {
			if (pessoaVOs.size() > 0) {
				PessoaVO ultimaPessoa = pessoaVOs.get(pessoaVOs.size() - 1);
				pessoaVO.setId(ultimaPessoa.getId().add(BigInteger.ONE));
			} else {
				pessoaVO.setId(BigInteger.ONE);
			}

			// Dados.getPessoaVOs().add(pessoaVO);
		} else {
			for (int i = 0; i < pessoaVOs.size(); i++) {
				if (pessoaVOs.get(i).equals(pessoaVO)) {
					// Dados.getPessoaVOs().set(i, pessoaVO);
				}
			}
		}
	}

}
