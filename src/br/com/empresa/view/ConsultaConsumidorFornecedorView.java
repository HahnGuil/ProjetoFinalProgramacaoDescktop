package br.com.empresa.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.TableColumnModel;

import br.com.empresa.dao.Dados;
import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.service.IServicoBeanLocal;
import br.com.empresa.service.ServicoBeanLocal;
import br.com.empresa.view.util.MascaraJFormattedTextField;
import br.com.empresa.view.util.RowData;
import br.com.empresa.view.util.TableModel;
import br.com.empresa.view.util.TamanhoFixoJTextField;
import br.com.empresa.vo.PessoaVO;
import br.com.empresa.vo.enums.EstadoEnum;
import br.com.empresa.vo.enums.TipoPessoaEnum;

public class ConsultaConsumidorFornecedorView extends JDialog {

	private JLabel lblCpfCnpj;
	private JTextField txtNome;
	private JTextField txtCidade;
	private JTable table;
	private TableModel tableModel;
	private JComboBox cbPessoa;
	private JComboBox cbEstado;
	private JFormattedTextField ftfCpfCnpj;

	private IServicoBeanLocal servicoBeanLocal;

	/**
	 * Create the frame.
	 */
	public ConsultaConsumidorFornecedorView() {

		servicoBeanLocal = new ServicoBeanLocal();

		setTitle("Manutenção de Consumidor/Fornecedor");
		setBounds(100, 100, 758, 450);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(12, 12, 734, 102);
		getContentPane().add(panel);
		panel.setLayout(null);

		lblCpfCnpj = new JLabel("CNPJ");
		lblCpfCnpj.setBounds(12, 46, 47, 15);
		panel.add(lblCpfCnpj);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(243, 14, 47, 15);
		panel.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(297, 12, 378, 19);
		panel.add(txtNome);
		txtNome.setColumns(10);
		txtNome.setDocument(new TamanhoFixoJTextField(10));

		JLabel lblTipoPessoa = new JLabel("Tipo pessoa");
		lblTipoPessoa.setBounds(12, 14, 97, 15);
		panel.add(lblTipoPessoa);

		cbPessoa = new JComboBox();
		cbPessoa.setModel(new DefaultComboBoxModel(TipoPessoaEnum.values()));
		cbPessoa.insertItemAt(null, 0);
		cbPessoa.setSelectedIndex(0);
		cbPessoa.setBounds(109, 9, 122, 24);
		
		cbPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarTipoPessoa();
			}
		});
		
		panel.add(cbPessoa);

		ftfCpfCnpj = new JFormattedTextField();
		//Altera o comportamento do componente quando perde o foco. https://docs.oracle.com/javase/6/docs/api/javax/swing/JFormattedTextField.html
		ftfCpfCnpj.setFocusLostBehavior(JFormattedTextField.PERSIST);
		alterarTipoPessoa();
		ftfCpfCnpj.setBounds(53, 44, 178, 19);
		panel.add(ftfCpfCnpj);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(243, 46, 49, 15);
		panel.add(lblEstado);

		cbEstado = new JComboBox(new DefaultComboBoxModel(EstadoEnum.values()));
		cbEstado.insertItemAt(null, 0);
		cbEstado.setSelectedIndex(0);
		cbEstado.setBounds(297, 41, 161, 24);
		panel.add(cbEstado);

		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(466, 46, 58, 15);
		panel.add(lblCidade);

		txtCidade = new JTextField();
		txtCidade.setBounds(523, 44, 152, 19);
		panel.add(txtCidade);
		txtCidade.setColumns(10);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});
		btnPesquisar.setBounds(558, 69, 117, 25);
		panel.add(btnPesquisar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparPesquisa();
			}
		});
		btnLimpar.setBounds(437, 69, 117, 25);
		panel.add(btnLimpar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(12, 163, 734, 212);
		getContentPane().add(scrollPane);

		tableModel = new TableModel();
		tableModel.addColumn("Código");
		tableModel.addColumn("CPF / CNPJ");
		tableModel.addColumn("Nome");
		tableModel.addColumn("Cidade");
		tableModel.addColumn("Estado");

		table = new JTable(tableModel);
		table.setAutoscrolls(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumnModel tableColumnModel = table.getColumnModel();
		tableColumnModel.getColumn(0).setPreferredWidth(50);
		tableColumnModel.getColumn(1).setPreferredWidth(150);
		tableColumnModel.getColumn(2).setPreferredWidth(230);
		tableColumnModel.getColumn(3).setPreferredWidth(100);
		tableColumnModel.getColumn(4).setPreferredWidth(150);

		pesquisar();

		scrollPane.setViewportView(table);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarRegistro();
			}
		});
		btnAdicionar.setBounds(12, 126, 117, 25);
		getContentPane().add(btnAdicionar);

		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fecharJanela();
			}
		});
		btnFechar.setBounds(629, 384, 117, 25);
		getContentPane().add(btnFechar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarRegistro();
			}
		});
		btnEditar.setBounds(133, 126, 117, 25);
		getContentPane().add(btnEditar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirRegistro();
			}
		});
		btnExcluir.setBounds(254, 126, 117, 25);
		getContentPane().add(btnExcluir);

		//Coloca a tela no centro da janela.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
	}

	private void adicionarRegistro() {
		ConsumidorFornecedorView consumidorFornecedorView = new ConsumidorFornecedorView(this);
		consumidorFornecedorView.setVisible(true);
	}

	private void editarRegistro() {

		if (table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(this, "É necessário selecionar um registro!", "Mensagem de aviso", JOptionPane.WARNING_MESSAGE);
		} else {

			ConsumidorFornecedorView consumidorFornecedorView = new ConsumidorFornecedorView(this);
			consumidorFornecedorView.editar((PessoaVO) tableModel.getRows().get(table.getSelectedRow()).getElement());
			consumidorFornecedorView.setVisible(true);
		}

	}

	private void excluirRegistro() {

		if (table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(this, "É necessário selecionar um registro!", "Mensagem de aviso", JOptionPane.WARNING_MESSAGE);
		} else {

			Object[] options = { "Sim!", "Não" };
			int n = JOptionPane.showOptionDialog(null, "Deseja realmente excluir o registro?", "Confirmação",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

			if (n == 0) {

				PessoaVO pessoa = (PessoaVO) tableModel.getRows().get(table.getSelectedRow()).getElement();
				try {
					servicoBeanLocal.excluirPessoa(pessoa);
					pesquisar();
				} catch (BOException e) {
					JOptionPane.showMessageDialog(this, "Ocorreu um erro ao realizar a operação!", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
				}

			}

		}

	}

	private void limparPesquisa() {

		txtCidade.setText(null);
		txtNome.setText(null);
		cbEstado.setSelectedIndex(-1);
		cbPessoa.setSelectedIndex(0);
		alterarTipoPessoa();
		ftfCpfCnpj.setValue(null);
		pesquisar();

	}

	private void alterarTipoPessoa() {

		TipoPessoaEnum tipoPessoa = (TipoPessoaEnum) cbPessoa.getSelectedItem();
		if (tipoPessoa == null || tipoPessoa.name().equals("F")) {

			lblCpfCnpj.setText("CPF");
			String formatString = "#########-##";
			MascaraJFormattedTextField.formatField(formatString, ftfCpfCnpj);

		} else if (tipoPessoa.name().equals("J")) {
			lblCpfCnpj.setText("CNPJ");
			String formatString = "##.###.###/####-##";
			MascaraJFormattedTextField.formatField(formatString, ftfCpfCnpj);
		}

	}

	public void pesquisar() {

		TableModel tableModel = (TableModel) table.getModel();
		tableModel.clearTable();

		try {

			TipoPessoaEnum tipoPessoa = (TipoPessoaEnum) cbPessoa.getSelectedItem();
			EstadoEnum estado = (EstadoEnum) cbEstado.getSelectedItem();

			List<PessoaVO> pessoas = servicoBeanLocal.listarPessoas(((tipoPessoa != null) ? tipoPessoa.name() : null), this.txtNome.getText(), 
					ftfCpfCnpj.getText(), txtCidade.getText(),
					((estado != null) ? estado.name() : null), Dados.getClienteSelecionado());

			for (PessoaVO p : pessoas) {
				RowData rowData = new RowData();
				rowData.getValues().put(0, p.getId().toString());
				rowData.getValues().put(1, p.getCpfcnp());
				rowData.getValues().put(2, p.getDescri());
				rowData.getValues().put(3, p.getCidade());
				rowData.getValues().put(4, p.getEstado());
				rowData.setElement(p);
				tableModel.addRow(rowData);
			}

		} catch (BOValidationException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Mensagem de aviso", JOptionPane.WARNING_MESSAGE);
		} catch (BOException e) {
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao executar a operação.", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void fecharJanela() {
		this.setVisible(false);
		dispose();
	}

}
