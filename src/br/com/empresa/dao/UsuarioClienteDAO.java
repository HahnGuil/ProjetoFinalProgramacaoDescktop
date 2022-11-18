package br.com.empresa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.empresa.exception.BOException;
import br.com.empresa.vo.UsuarioClienteVO;
import br.com.empresa.vo.UsuarioVO;

public class UsuarioClienteDAO implements IUsuarioClienteDAO {

	public UsuarioClienteDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<UsuarioClienteVO> listarClientesUsuario(UsuarioVO usuarioVO) throws BOException {

		EntityManager em = HibernateUtil.getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<UsuarioClienteVO> criteria = cb.createQuery(UsuarioClienteVO.class);

		// From
		Root<UsuarioClienteVO> usuarioClienteFrom = criteria.from(UsuarioClienteVO.class);

		System.out.println("================================");
		System.out.println("Id sendo utilizado para select: " + usuarioVO.getId());

		// Where
		Predicate usuarioClienteWhere = cb.equal(usuarioClienteFrom.get("usuarioVO"), usuarioVO.getId());

		criteria.where(usuarioClienteWhere);

		TypedQuery<UsuarioClienteVO> query = em.createQuery(criteria);

		List<UsuarioClienteVO> usuarioClientesVO = query.getResultList();

		em.close();

		return usuarioClientesVO;
	}

	@Override
	public int buscarQuantidadeClientesUsuario(UsuarioVO usuarioVO) throws BOException {

		int qtd = 0;

		List<UsuarioClienteVO> usuarioClienteVOs = Dados.getUsuarioClienteVOs();

		if (usuarioClienteVOs != null) {
			for (UsuarioClienteVO usuarioClienteVO : usuarioClienteVOs) {
				if (usuarioClienteVO.getUsuarioVO().equals(usuarioVO)) {
					qtd++;
				}
			}
		}
		return qtd;
	}

}
