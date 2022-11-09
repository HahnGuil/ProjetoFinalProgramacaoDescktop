package br.com.empresa.bo;

import java.util.List;
import java.util.Map;

import br.com.empresa.dao.IPessoaDAO;
import br.com.empresa.dao.PessoaDAO;
import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.validator.CNPJValidator;
import br.com.empresa.validator.CPFValidator;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.PessoaVO;
import br.com.empresa.vo.enums.EstadoEnum;

public class PessoaBO implements IPessoaBO {

	private IPessoaDAO pessoaDAO;

	public PessoaBO() {
		pessoaDAO = new PessoaDAO();
	}

	@Override
	public List<PessoaVO> listarPessoas(ClienteVO cliente) throws BOException {

		return pessoaDAO.listarPessoas(cliente);
	}

	@Override
	public List<PessoaVO> listarPessoas(String tipoPessoa, String nomePessoa, String cpfCnpj, String cidade, String estado, ClienteVO cliente) throws BOValidationException, BOException {

		if (cliente == null || cliente.getId() == null) {
			throw new BOException("Ocorreu um erro ao listar pessoas. Motivo: Cliente nulo ou não informado.");			
		}
		
		if (cpfCnpj != null && cpfCnpj.trim().length() > 1) {
			
			if(tipoPessoa.equals("F")) {
				CPFValidator cpfValidator = new CPFValidator();
				try {
					cpfValidator.validate(cpfCnpj);
				} catch (Exception e) {
					throw new BOValidationException("CPF: erro de validação. O CPF informado está incorreto.");
				}
			}else if(tipoPessoa.equals("J") && cpfCnpj.replaceAll("\\.", "").replaceAll("/", "").replaceAll("-", "").trim().length() > 0) {
				CNPJValidator cnpjValidator = new CNPJValidator();
				try {
					cnpjValidator.validate(cpfCnpj);
				} catch (Exception e) {
					throw new BOValidationException("CNPJ: erro de validação. O CNPJ informado está incorreto.");
				}
			}
		}

		return pessoaDAO.listarPessoas(tipoPessoa, nomePessoa, cpfCnpj, cidade, estado, cliente);
	}

	@Override
	public List<PessoaVO> listarPessoas(int first, int pageSize, Map<String, Object> filters, ClienteVO cliente) throws BOValidationException, BOException {

		if (cliente == null || cliente.getId() == null) {
			throw new BOException("Ocorreu um erro ao listar pessoas. Motivo: Cliente nulo ou não informado.");
		}

		return pessoaDAO.listarPessoas(first, pageSize, filters, cliente);
	}

	@Override
	public void excluirPessoa(PessoaVO pessoaVO) throws BOValidationException, BOException {
		
		if(pessoaVO == null || pessoaVO.getId() == null) {
			throw new BOException("Ocorreu um erro ao excluir a pessoa.");
		}
		
		pessoaDAO.excluirPessoa(pessoaVO);
	}

	@Override
	public void salvarPessoa(PessoaVO pessoaVO) throws BOValidationException, BOException {
		
		if(pessoaVO == null) {
			throw new BOException("Não é possível salvar a pessoa pois o objeto informado é nulo.");
		}else if(pessoaVO.getDescri() == null || pessoaVO.getDescri().trim().length() == 0) {
			throw new BOValidationException("Nome: erro de validação. O nome deve ser preenchido.");
		}else if(pessoaVO.getCepend() == null) {
			throw new BOValidationException("CEP: erro de validação. O CEP deve ser preenchido.");
		}else if(pessoaVO.getRuaend() == null || pessoaVO.getRuaend().trim().length() == 0) {
			throw new BOValidationException("Rua: erro de validação. O nome da rua deve ser preenchido.");
		}else if(pessoaVO.getNumend() == null || pessoaVO.getNumend().trim().length() == 0) {
			throw new BOValidationException("Número: erro de validação. O número da residência deve ser preenchido.");
		}else if(pessoaVO.getBaiend() == null || pessoaVO.getBaiend().trim().length() == 0) {
			throw new BOValidationException("Bairro: erro de validação. O nome do bairro deve ser preenchido.");
		}else if(pessoaVO.getCidade() == null || pessoaVO.getCidade().trim().length() == 0) {
			throw new BOValidationException("Cidade: erro de validação. O nome da cidade deve ser preenchido.");
		}else if(pessoaVO.getEstado() == null || pessoaVO.getEstado().trim().length() == 0) {
			throw new BOValidationException("Estado: erro de validação. O estado deve ser preenchido.");
		}else if(pessoaVO.getCpfcnp() == null || pessoaVO.getCpfcnp().replaceAll("\\.", "").replaceAll("/", "").replaceAll("-", "").trim().length() == 0) {
			throw new BOValidationException("CPF/CNPJ: erro de validação. O CPF/CNPJ deve ser preenchido.");
		}
		
		pessoaDAO.salvarPessoa(pessoaVO);
		
	}

}
