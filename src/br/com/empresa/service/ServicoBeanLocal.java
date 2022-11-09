package br.com.empresa.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import br.com.empresa.bo.IPessoaBO;
import br.com.empresa.bo.IProdutoBO;
import br.com.empresa.bo.IUsuarioBO;
import br.com.empresa.bo.IUsuarioClienteBO;
import br.com.empresa.bo.PessoaBO;
import br.com.empresa.bo.ProdutoBO;
import br.com.empresa.bo.UsuarioBO;
import br.com.empresa.bo.UsuarioClienteBO;
import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.PessoaVO;
import br.com.empresa.vo.ProdutoVO;
import br.com.empresa.vo.UsuarioClienteVO;
import br.com.empresa.vo.UsuarioVO;

public class ServicoBeanLocal implements IServicoBeanLocal {

	public ServicoBeanLocal() {
	}

	@Override
	public UsuarioVO validarAcesso(String login, String senha) throws BOValidationException, BOException {

		IUsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.validarAcesso(login, senha);

	}

	@Override
	public List<UsuarioClienteVO> listarClientesUsuario(UsuarioVO usuarioVO) throws BOException {

		IUsuarioClienteBO usuarioClienteBO = new UsuarioClienteBO();

		return usuarioClienteBO.listarClientesUsuario(usuarioVO);
	}

	@Override
	public int buscarQuantidadeClientesUsuario(UsuarioVO usuarioVO) throws BOException {

		IUsuarioClienteBO usuarioClienteBO = new UsuarioClienteBO();

		return usuarioClienteBO.buscarQuantidadeClientesUsuario(usuarioVO);
	}

	@Override
	public List<PessoaVO> listarPessoas(ClienteVO cliente) throws BOException {

		IPessoaBO pessoaBO = new PessoaBO();

		return pessoaBO.listarPessoas(cliente);
	}

	@Override
	public List<PessoaVO> listarPessoas(String tipoPessoa, String nomePessoa, String cpfCnpj, String cidade, String estado, ClienteVO cliente) throws BOValidationException, BOException {

		IPessoaBO pessoaBO = new PessoaBO();

		return pessoaBO.listarPessoas(tipoPessoa, nomePessoa, cpfCnpj, cidade, estado, cliente);
	}

	@Override
	public List<PessoaVO> listarPessoas(int first, int pageSize, Map<String, Object> filters, ClienteVO cliente) throws BOException {

		IPessoaBO pessoaBO = new PessoaBO();

		return pessoaBO.listarPessoas(first, pageSize, filters, cliente);
	}

	@Override
	public void excluirPessoa(PessoaVO pessoaVO) throws BOException {

		IPessoaBO pessoaBO = new PessoaBO();

		pessoaBO.excluirPessoa(pessoaVO);

	}

	@Override
	public void salvarPessoa(PessoaVO pessoaVO) throws BOValidationException, BOException {

		IPessoaBO pessoaBO = new PessoaBO();

		pessoaBO.salvarPessoa(pessoaVO);

	}

	@Override
	public ProdutoVO buscarProdutoPorId(ProdutoVO produtoVO) throws BOException {
		
		IProdutoBO produtoBO = new ProdutoBO();
		
		return produtoBO.buscarProdutoPorId(produtoVO);
	}

	@Override
	public List<ProdutoVO> listarProduto(int first, int pageSize, Map<String, Object> filters, ClienteVO cliente) throws BOException {
		
		IProdutoBO produtoBO = new ProdutoBO();
		
		return produtoBO.listarProduto(first, pageSize, filters, cliente);
		
	}

	@Override
	public int listarProdutoCount(Map<String, Object> filters, ClienteVO cliente) throws BOException {
		
		IProdutoBO produtoBO = new ProdutoBO();
		
		return produtoBO.listarProdutoCount(filters, cliente);
	}

	@Override
	public void salvarProduto(ProdutoVO produtoVO) throws BOValidationException, BOException {

		IProdutoBO produtoBO = new ProdutoBO();

		produtoBO.salvarProduto(produtoVO);

	}

	@Override
	public void excluirProduto(ProdutoVO produtoVO) throws BOValidationException, BOException {
		
		IProdutoBO produtoBO = new ProdutoBO();

		produtoBO.excluirProduto(produtoVO);

	}

	@Override
	public List<ProdutoVO> listarProduto(BigInteger id, String descri, String status, String codbar, ClienteVO client) throws BOException {
		
		IProdutoBO produtoBO = new ProdutoBO();
		
		return produtoBO.listarProduto(id, descri, status, codbar, client);
		
	}

	@Override
	public int listarProdutoCount(BigInteger id, String descri, String status, String codbar, ClienteVO client) throws BOException {

		IProdutoBO produtoBO = new ProdutoBO();
		
		return produtoBO.listarProdutoCount(id, descri, status, codbar, client);

	}

}
