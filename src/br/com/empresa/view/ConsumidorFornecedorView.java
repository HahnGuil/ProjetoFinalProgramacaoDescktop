package br.com.empresa.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.com.empresa.dao.Dados;
import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.service.IServicoBeanLocal;
import br.com.empresa.service.ServicoBeanLocal;
import br.com.empresa.view.util.MascaraJFormattedTextField;
import br.com.empresa.view.util.TamanhoFixoJTextField;
import br.com.empresa.vo.PessoaVO;
import br.com.empresa.vo.enums.EstadoEnum;
import br.com.empresa.vo.enums.TipoPessoaEnum;

public class ConsumidorFornecedorView extends JDialog {

	private JLabel lblCpfCnpj;

	private JTextField tfCodigo;
	private JTextField tfNome;
	private JTextField tfEndereco;
	private JTextField tfComplemento;
	private JTextField tfNumero;
	private JTextField tfCidade;
	private JComboBox cbPessoa;
	private JComboBox cbEstado;
	private JFormattedTextField ftfCpfCnpj;
	private JFormattedTextField ftfCep;
	private ConsultaConsumidorFornecedorView telaAnterior;

	private PessoaVO pessoaVO;

	private IServicoBeanLocal servicoBeanLocal;
	private JTextField tfBairro;

	/**
	 * Create the frame.
	 */
	public ConsumidorFornecedorView(ConsultaConsumidorFornecedorView jDialog) {
		super(jDialog, true);
		telaAnterior = jDialog;
		inicializarComponentes();
		servicoBeanLocal = new ServicoBeanLocal();
	}

	/**
	 * Create the frame.
	 */
	public ConsumidorFornecedorView() {
		inicializarComponentes();
	}

	private void inicializarComponentes() {

		setTitle("Manutenção");

		pessoaVO = new PessoaVO();

		setBounds(100, 100, 488, 420);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		btnFechar.setBounds(359, 351, 117, 25);
		getContentPane().add(btnFechar);

		JLabel lblCodigo = new JLabel("Código:");
		lblCodigo.setBounds(12, 15, 117, 15);
		getContentPane().add(lblCodigo);

		JLabel lblTipoPessoa = new JLabel("Tipo Pessoa:");
		lblTipoPessoa.setBounds(12, 45, 109, 15);
		getContentPane().add(lblTipoPessoa);

		lblCpfCnpj = new JLabel("CPF: *");
		lblCpfCnpj.setBounds(12, 75, 109, 15);
		getContentPane().add(lblCpfCnpj);

		JLabel lblNome = new JLabel("Nome: *");
		lblNome.setBounds(12, 105, 109, 15);
		getContentPane().add(lblNome);

		JLabel lblCep = new JLabel("CEP: *");
		lblCep.setBounds(12, 135, 109, 15);
		getContentPane().add(lblCep);

		JLabel lblEndereco = new JLabel("Endereço: *");
		lblEndereco.setBounds(12, 165, 109, 15);
		getContentPane().add(lblEndereco);

		JLabel lblNumero = new JLabel("Número: *");
		lblNumero.setBounds(12, 225, 109, 15);
		getContentPane().add(lblNumero);

		JLabel lblCidade = new JLabel("Cidade: *");
		lblCidade.setBounds(12, 286, 109, 15);
		getContentPane().add(lblCidade);

		JLabel lblEstado = new JLabel("Estado: *");
		lblEstado.setBounds(12, 316, 109, 15);
		getContentPane().add(lblEstado);

		JLabel lblComplemento = new JLabel("Complemento:");
		lblComplemento.setBounds(12, 195, 109, 15);
		getContentPane().add(lblComplemento);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar();
			}
		});
		btnSalvar.setBounds(230, 351, 117, 25);
		getContentPane().add(btnSalvar);

		tfCodigo = new JTextField();
		tfCodigo.setEditable(false);
		tfCodigo.setBounds(128, 13, 114, 19);
		getContentPane().add(tfCodigo);
		tfCodigo.setColumns(10);

		cbPessoa = new JComboBox();
		cbPessoa.setModel(new DefaultComboBoxModel(TipoPessoaEnum.values()));
		cbPessoa.setBounds(128, 40, 114, 24);
		getContentPane().add(cbPessoa);

		cbPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarTipoPessoa();
			}
		});

		ftfCpfCnpj = new JFormattedTextField();
		ftfCpfCnpj.setBounds(128, 73, 177, 19);
		getContentPane().add(ftfCpfCnpj);
		ftfCpfCnpj.setFocusLostBehavior(JFormattedTextField.PERSIST);
		alterarTipoPessoa();

		tfNome = new JTextField();
		tfNome.setDocument(new TamanhoFixoJTextField(50));
		tfNome.setBounds(128, 103, 322, 19);
		getContentPane().add(tfNome);
		tfNome.setColumns(10);

		ftfCep = new JFormattedTextField();
		String formatString = "#####-###";
		MascaraJFormattedTextField.formatField(formatString, ftfCep);
		ftfCep.setBounds(128, 133, 114, 19);
		getContentPane().add(ftfCep);

		tfEndereco = new JTextField();
		tfEndereco.setDocument(new TamanhoFixoJTextField(80));
		tfEndereco.setBounds(128, 163, 348, 19);
		getContentPane().add(tfEndereco);
		tfEndereco.setColumns(10);

		tfComplemento = new JTextField();
		tfComplemento.setDocument(new TamanhoFixoJTextField(80));
		tfComplemento.setBounds(128, 193, 348, 19);
		getContentPane().add(tfComplemento);
		tfComplemento.setColumns(10);

		tfNumero = new JTextField();
		tfNumero.setDocument(new TamanhoFixoJTextField(20));
		tfNumero.setBounds(128, 223, 114, 19);
		getContentPane().add(tfNumero);
		tfNumero.setColumns(10);

		tfCidade = new JTextField();
		tfCidade.setDocument(new TamanhoFixoJTextField(30));
		tfCidade.setBounds(128, 284, 263, 19);
		getContentPane().add(tfCidade);
		tfCidade.setColumns(10);

		cbEstado = new JComboBox(new DefaultComboBoxModel(EstadoEnum.values()));
		cbEstado.setSelectedIndex(-1);
		cbEstado.setBounds(128, 311, 204, 24);
		getContentPane().add(cbEstado);
		
		JLabel lblBairro = new JLabel("Bairro: *");
		lblBairro.setBounds(12, 256, 109, 15);
		getContentPane().add(lblBairro);
		
		tfBairro = new JTextField();
		tfBairro.setColumns(10);
		tfBairro.setDocument(new TamanhoFixoJTextField(30));
		tfBairro.setBounds(128, 254, 263, 19);
		getContentPane().add(tfBairro);

		//Coloca a tela no centro da janela.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

	}

	private void salvar() {
		
		try {
			
			TipoPessoaEnum tp = (TipoPessoaEnum) this.cbPessoa.getSelectedItem();
			
			pessoaVO.setTippes(tp.name());
			pessoaVO.setCpfcnp(ftfCpfCnpj.getText());
			pessoaVO.setDescri(tfNome.getText());
			String cepaux = ftfCep.getText();
			if(cepaux.trim().length() > 1) {
				pessoaVO.setCepend(Integer.parseInt(ftfCep.getText().replaceAll("-", "")));
			}
			pessoaVO.setRuaend(tfEndereco.getText());
			pessoaVO.setComend(tfComplemento.getText());
			pessoaVO.setNumend(tfNumero.getText());
			pessoaVO.setBaiend(tfBairro.getText());
			EstadoEnum estadoEnum = (EstadoEnum)cbEstado.getSelectedItem();
			if(estadoEnum != null) {
				pessoaVO.setEstado(estadoEnum.name());
			}
			pessoaVO.setCidade(tfCidade.getText());
			pessoaVO.setClient(Dados.getClienteSelecionado());
			
			servicoBeanLocal.salvarPessoa(pessoaVO);
			
			tfCodigo.setText(pessoaVO.getId().toString());
			
			telaAnterior.pesquisar();
			
			JOptionPane.showMessageDialog(this, "Operação realizada com sucesso!", "Mensagem de confirmação", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (BOValidationException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Mensagem de alerta", JOptionPane.WARNING_MESSAGE);
		} catch (BOException e) {
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao realizar a operação!", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void editar(PessoaVO pessoa) {
		
		this.setPessoaVO(pessoa);
		this.tfCodigo.setText(pessoa.getId().toString());
		this.cbPessoa.setSelectedItem(TipoPessoaEnum.valueOf(pessoa.getTippes()));
		this.cbEstado.setSelectedItem(EstadoEnum.valueOf(pessoa.getEstado()));
		this.ftfCpfCnpj.setText(pessoa.getCpfcnp());
		this.tfNome.setText(pessoa.getDescri());
		this.ftfCep.setText(pessoa.getCepend().toString());
		this.tfEndereco.setText(pessoa.getRuaend());
		this.tfNumero.setText(pessoa.getNumend());
		this.tfComplemento.setText(pessoa.getComend());
		this.tfBairro.setText(pessoa.getBaiend());
		this.tfCidade.setText(pessoa.getCidade());
		
	}

	private void fechar() {
		setVisible(false);
		dispose();
	}

	private void alterarTipoPessoa() {

		TipoPessoaEnum tipoPessoa = (TipoPessoaEnum) cbPessoa.getSelectedItem();
		if (tipoPessoa.name().equals("F")) {

			lblCpfCnpj.setText("CPF: *");
			String formatString = "#########-##";
			MascaraJFormattedTextField.formatField(formatString, ftfCpfCnpj);

		} else if (tipoPessoa.name().equals("J")) {
			lblCpfCnpj.setText("CNPJ: *");
			String formatString = "##.###.###/####-##";
			MascaraJFormattedTextField.formatField(formatString, ftfCpfCnpj);
		}

	}

	public PessoaVO getPessoaVO() {
		return pessoaVO;
	}

	public void setPessoaVO(PessoaVO pessoaVO) {
		this.pessoaVO = pessoaVO;
	}
}
