package br.com.empresa.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.PessoaVO;
import br.com.empresa.vo.ProdutoVO;
import br.com.empresa.vo.UsuarioClienteVO;
import br.com.empresa.vo.UsuarioVO;

public class Dados {
	
	static UsuarioVO usuarioSelecionado;
	static ClienteVO clienteSelecionado;
	
	static List<UsuarioVO> usuarioVOs;
	static List<ClienteVO> clienteVOs;
	static List<UsuarioClienteVO> usuarioClienteVOs;
	static List<PessoaVO> pessoaVOs;
	static List<ProdutoVO> produtoVOs;

	static {

		//Inclusão de usuários
		usuarioVOs = new ArrayList<UsuarioVO>();
		UsuarioVO u1 = new UsuarioVO();
		u1.setId(new BigInteger("1"));
		u1.setLogusu("pedro");
		u1.setSenusu("123456");
		
		UsuarioVO u2 = new UsuarioVO();
		u2.setId(new BigInteger("2"));
		u2.setLogusu("joao");
		u2.setSenusu("123456");
		
		UsuarioVO u3 = new UsuarioVO();
		u3.setId(new BigInteger("3"));
		u3.setLogusu("ana");
		u3.setSenusu("123456");
		
		usuarioVOs.add(u1);
		usuarioVOs.add(u2);
		usuarioVOs.add(u3);
		
		//Inclusão de clientes
		clienteVOs = new ArrayList<ClienteVO>();
		ClienteVO c1 = new ClienteVO(new BigInteger("1"), "Cliente 1");
		ClienteVO c2 = new ClienteVO(new BigInteger("2"), "Cliente 2");
		clienteVOs.add(c1);
		clienteVOs.add(c2);
		
		//Vínculo entre ambos
		usuarioClienteVOs = new ArrayList<UsuarioClienteVO>();
		UsuarioClienteVO uc1 = new UsuarioClienteVO();
		uc1.setClienteVO(c1);
		uc1.setUsuarioVO(u1);
		UsuarioClienteVO uc2 = new UsuarioClienteVO();
		uc2.setClienteVO(c2);
		uc2.setUsuarioVO(u1);
		usuarioClienteVOs.add(uc1);
		usuarioClienteVOs.add(uc2);
		
		UsuarioClienteVO uc3 = new UsuarioClienteVO();
		uc3.setClienteVO(c1);
		uc3.setUsuarioVO(u2);
		usuarioClienteVOs.add(uc3);
		
		//Inclusão de pessoas
		PessoaVO p1 = new PessoaVO();
		p1.setId(new BigInteger("1"));
		p1.setDescri("Pedro da Silva");
		p1.setCpfcnp("031174549-64");
		p1.setTippes("F");
		p1.setRuaend("Rua Varci Vasconcelos");
		p1.setBaiend("Bairro X");		
		p1.setNumend("547");
		p1.setCidade("Criciúma");
		p1.setEstado("SC");
		p1.setCepend(88818686);
		p1.setClient(c1);
		
		PessoaVO p2 = new PessoaVO();
		p2.setId(new BigInteger("2"));
		p2.setDescri("Maria Joaquina");
		p2.setCpfcnp("878745454-44");
		p2.setTippes("F");
		p2.setRuaend("Rua João Cechinel");
		p2.setBaiend("Lagoinha Azul");		
		p2.setNumend("54");
		p2.setCidade("Criciúma");
		p2.setEstado("SC");
		p2.setCepend(68550530);
		p2.setClient(c1);
		
		pessoaVOs = new ArrayList<PessoaVO>();
		pessoaVOs.add(p1);
		pessoaVOs.add(p2);

		//Inicialização dos produtos;
		produtoVOs = new ArrayList<>();
		
	}

	public static List<UsuarioVO> getUsuarioVOs() {
		return usuarioVOs;
	}

	public static void setUsuarioVOs(List<UsuarioVO> usuarioVOs) {
		Dados.usuarioVOs = usuarioVOs;
	}

	public static UsuarioVO getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public static void setUsuarioSelecionado(UsuarioVO usuarioSelecionado) {
		Dados.usuarioSelecionado = usuarioSelecionado;
	}

	public static ClienteVO getClienteSelecionado() {
		return clienteSelecionado;
	}

	public static void setClienteSelecionado(ClienteVO clienteSelecionado) {
		Dados.clienteSelecionado = clienteSelecionado;
	}

	public static List<ClienteVO> getClienteVOs() {
		return clienteVOs;
	}

	public static void setClienteVOs(List<ClienteVO> clienteVOs) {
		Dados.clienteVOs = clienteVOs;
	}

	public static List<UsuarioClienteVO> getUsuarioClienteVOs() {
		return usuarioClienteVOs;
	}

	public static void setUsuarioClienteVOs(List<UsuarioClienteVO> usuarioClienteVOs) {
		Dados.usuarioClienteVOs = usuarioClienteVOs;
	}

	public static List<PessoaVO> getPessoaVOs() {
		return pessoaVOs;
	}

	public static void setPessoaVOs(List<PessoaVO> pessoaVOs) {
		Dados.pessoaVOs = pessoaVOs;
	}

	public static List<ProdutoVO> getProdutoVOs() {
		return produtoVOs;
	}

	public static void setProdutoVOs(List<ProdutoVO> produtoVOs) {
		Dados.produtoVOs = produtoVOs;
	}
	
	

}
