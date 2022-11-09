package br.com.empresa.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.empresa.exception.BOException;
import br.com.empresa.vo.UsuarioClienteVO;
import br.com.empresa.vo.UsuarioVO;

public class UsuarioClienteDAO implements IUsuarioClienteDAO {

	public UsuarioClienteDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<UsuarioClienteVO> listarClientesUsuario(UsuarioVO usuarioVO) throws BOException {

		List<UsuarioClienteVO> usuarioClienteVOs = Dados.getUsuarioClienteVOs();

		List<UsuarioClienteVO> filtro = new ArrayList<UsuarioClienteVO>();

		for (UsuarioClienteVO usuarioClienteVO : usuarioClienteVOs) {
			if (usuarioClienteVO.getUsuarioVO().equals(usuarioVO)) {
				filtro.add(usuarioClienteVO);
			}
		}

		return filtro;
	}

	@Override
	public int buscarQuantidadeClientesUsuario(UsuarioVO usuarioVO) throws BOException {

		int qtd = 0;

		List<UsuarioClienteVO> usuarioClienteVOs = Dados.getUsuarioClienteVOs();

		for (UsuarioClienteVO usuarioClienteVO : usuarioClienteVOs) {
			if (usuarioClienteVO.getUsuarioVO().equals(usuarioVO)) {
				qtd++;
			}
		}
		return qtd;
	}

}
