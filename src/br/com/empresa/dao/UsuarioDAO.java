package br.com.empresa.dao;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.UsuarioVO;

public class UsuarioDAO implements IUsuarioDAO {

	public UsuarioDAO() {

	}

	@Override
	public UsuarioVO buscarUsuario(String login, String senha) throws BOValidationException, BOException {

		EntityManager em = HibernateUtil.getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<UsuarioVO> criteria = cb.createQuery(UsuarioVO.class);

		// From
		Root<UsuarioVO> usuarioFrom = criteria.from(UsuarioVO.class);

		// Where
		Predicate usuarioWhere = cb.equal(usuarioFrom.get("logusu"), login);

		usuarioWhere = cb.and(usuarioWhere, cb.equal(usuarioFrom.get("senusu"), senha));

		criteria.where(usuarioWhere);

		TypedQuery<UsuarioVO> query = em.createQuery(criteria);

		UsuarioVO usuarioVO = query.getSingleResult();

		em.close();

		return usuarioVO;

		// List<UsuarioVO> usuarioVOs = Dados.getUsuarioVOs();

		// boolean encontrado = false;

		// for (UsuarioVO usuarioVO : usuarioVOs) {
		// if (usuarioVO.getLogusu().equals(login) &&
		// usuarioVO.getSenusu().equals(senha)) {
		// return usuarioVO;
		// }
		// }

		// return null;
	}

}
