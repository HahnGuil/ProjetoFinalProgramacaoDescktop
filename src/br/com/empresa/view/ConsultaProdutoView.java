package br.com.empresa.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
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
import br.com.empresa.vo.ProdutoVO;
import br.com.empresa.vo.enums.StatusEnum;

public class ConsultaProdutoView extends JDialog {

	private JTextField tfDescricao;
	private JTextField tfCodBarra;
	private JTable table;
	private TableModel tableModel;
	private JComboBox cbStatus;
	private JFormattedTextField ftfCodigo;

	private IServicoBeanLocal servicoBeanLocal;

	/**
	 * Create the dialog.
	 */
	public ConsultaProdutoView() {

		setTitle("Manutenção de Produto");

		servicoBeanLocal = new ServicoBeanLocal();

		setBounds(100, 100, 656, 443);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(12, 12, 630, 119);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblCodigo = new JLabel("Código");
		lblCodigo.setBounds(12, 12, 62, 15);
		panel.add(lblCodigo);

		ftfCodigo = new JFormattedTextField();
		ftfCodigo.setBounds(78, 10, 116, 19);
		String formatString = "###########";
		MascaraJFormattedTextField.formatField(formatString, ftfCodigo);
		ftfCodigo.setFocusLostBehavior(JFormattedTextField.PERSIST);
		panel.add(ftfCodigo);

		JLabel lblDescricao = new JLabel("Descrição");
		lblDescricao.setBounds(199, 12, 79, 15);
		panel.add(lblDescricao);

		tfDescricao = new JTextField();
		tfDescricao.setBounds(292, 10, 326, 19);
		panel.add(tfDescricao);
		tfDescricao.setColumns(10);

		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(12, 48, 62, 15);
		panel.add(lblStatus);

		cbStatus = new JComboBox();
		DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel<>(StatusEnum.values());
		cbStatus.setModel(defaultComboBoxModel);		
		defaultComboBoxModel.insertElementAt(null, 0);
		cbStatus.setSelectedIndex(0);
		cbStatus.setBounds(78, 43, 116, 24);
		panel.add(cbStatus);

		JLabel lblCodBar = new JLabel("Cód. barras");
		lblCodBar.setBounds(199, 48, 98, 15);
		panel.add(lblCodBar);

		tfCodBarra = new JTextField();
		tfCodBarra.setBounds(292, 46, 326, 19);
		panel.add(tfCodBarra);
		tfCodBarra.setColumns(10);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});
		btnPesquisar.setBounds(501, 77, 117, 25);
		panel.add(btnPesquisar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparPesquisa();
			}
		});
		btnLimpar.setBounds(379, 77, 117, 25);
		panel.add(btnLimpar);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarRegistro();
			}
		});
		btnAdicionar.setBounds(12, 143, 117, 25);
		getContentPane().add(btnAdicionar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarRegistro();
			}
		});
		btnEditar.setBounds(134, 143, 117, 25);
		getContentPane().add(btnEditar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirRegistro();
			}
		});
		btnExcluir.setBounds(255, 143, 117, 25);
		getContentPane().add(btnExcluir);

		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fecharJanela();
			}
		});
		btnFechar.setBounds(525, 377, 117, 25);
		getContentPane().add(btnFechar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(12, 180, 632, 185);
		getContentPane().add(scrollPane);

		tableModel = new TableModel();
		tableModel.addColumn("Código");
		tableModel.addColumn("Descrição");
		tableModel.addColumn("Qtd. Estoque");
		tableModel.addColumn("Status");
		tableModel.addColumn("Vlr. compra");
		tableModel.addColumn("Vlr. venda");

		table = new JTable(tableModel);
		table.setAutoscrolls(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumnModel tableColumnModel = table.getColumnModel();
		tableColumnModel.getColumn(0).setPreferredWidth(100);
		tableColumnModel.getColumn(1).setPreferredWidth(180);
		tableColumnModel.getColumn(2).setPreferredWidth(110);
		tableColumnModel.getColumn(3).setPreferredWidth(100);
		tableColumnModel.getColumn(4).setPreferredWidth(80);
		tableColumnModel.getColumn(5).setPreferredWidth(80);

		scrollPane.setViewportView(table);
		pesquisar();

		//Coloca a tela no centro da janela.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

	}

	private void adicionarRegistro() {
		ProdutoView produtoView = new ProdutoView(this);
		produtoView.setVisible(true);
	}

	private void editarRegistro() {

		if (table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(this, "É necessário selecionar um registro!", "Mensagem de aviso", JOptionPane.WARNING_MESSAGE);
		} else {

			try {

				ProdutoView produtoView = new ProdutoView(this);
				ProdutoVO aux = (ProdutoVO) tableModel.getRows().get(table.getSelectedRow()).getElement();
				produtoView.editar(servicoBeanLocal.buscarProdutoPorId(aux));
				produtoView.setVisible(true);

			} catch (BOException e) {
				JOptionPane.showMessageDialog(null, "Ocorreu um erro ao realizar a operação.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	private void excluirRegistro() {

		if (table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(this, "É necessário selecionar um registro!", "Mensagem de aviso", JOptionPane.WARNING_MESSAGE);
		} else {

			Object[] options = { "Sim!", "Não" };
			int n = JOptionPane.showOptionDialog(null, "Deseja realmente excluir o registro?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

			if (n == 0) {

				ProdutoVO produto = (ProdutoVO) tableModel.getRows().get(table.getSelectedRow()).getElement();
				try {
					servicoBeanLocal.excluirProduto(produto);
					pesquisar();
				} catch (BOException e) {
					JOptionPane.showMessageDialog(this, "Ocorreu um erro ao realizar a operação!", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
				}

			}

		}
	}

	private void limparPesquisa() {

		this.tfCodBarra.setText(null);
		this.tfDescricao.setText(null);
		this.cbStatus.setSelectedIndex(0);
		this.ftfCodigo.setText(null);
		pesquisar();

	}

	public void pesquisar() {

		TableModel tableModel = (TableModel) table.getModel();
		tableModel.clearTable();

		try {

			Map<String, Object> filters = new HashMap<>();
			
			BigInteger id = null;
			if (this.ftfCodigo.getText() != null && this.ftfCodigo.getText().trim().length() > 0) {
				id = new BigInteger(ftfCodigo.getText().trim());
				filters.put("id", id);
			}

			String descri = this.tfDescricao.getText();
			filters.put("descri", descri);
			
			String status = null;
			if (cbStatus.getSelectedItem() != null) {
				StatusEnum statusEnum = (StatusEnum)this.cbStatus.getSelectedItem();
				status = statusEnum.name();
				filters.put("status", status);
			}
			
			String codbar = null;
			if(this.tfCodBarra.getText() != null && this.tfCodBarra.getText().trim().length() > 0) {
				codbar = this.tfCodBarra.getText();
				filters.put("codbar", codbar);
			}

			List<ProdutoVO> produtoVOs = servicoBeanLocal.listarProduto(id, descri, status, codbar, Dados.getClienteSelecionado());

			if (produtoVOs != null) {
				DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
				DecimalFormat decimalFormatQtd = new DecimalFormat("###,###.000");

				for (ProdutoVO p : produtoVOs) {
					RowData rowData = new RowData();
					rowData.getValues().put(0, p.getId().toString());
					rowData.getValues().put(1, p.getDescri());

					if (p.getQtdest() != null) {
						rowData.getValues().put(2, decimalFormatQtd.format(p.getQtdest()));
					}

					if (p.getStatus().equals("A")) {
						rowData.getValues().put(3, "Ativo");
					} else if (p.getStatus().equals("I")) {
						rowData.getValues().put(3, "Inativo");
					}

					if (p.getValcom() != null) {
						rowData.getValues().put(4, decimalFormat.format(p.getValcom()));
					}
					if (p.getValven() != null) {
						rowData.getValues().put(5, decimalFormat.format(p.getValven()));
					}
					rowData.setElement(p);
					tableModel.addRow(rowData);
				}
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
