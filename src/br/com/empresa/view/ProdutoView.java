package br.com.empresa.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;

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
import br.com.empresa.vo.ProdutoVO;
import br.com.empresa.vo.enums.StatusEnum;
import br.com.empresa.vo.enums.TipoPessoaEnum;

public class ProdutoView extends JDialog {

	private JTextField tfcodigo;
	private JTextField tfDescricao;
	private JComboBox cbStatus;
	private JFormattedTextField ftfCodBar;
	private JFormattedTextField ftfQtd;
	private JFormattedTextField ftfVlrCompra;
	private JFormattedTextField ftfVlrVenda;

	private ProdutoVO produtoVO;

	private IServicoBeanLocal servicoBeanLocal;
	private ConsultaProdutoView consultaProdutoView;

	/**
	 * Create the dialog.
	 */
	public ProdutoView() {
		inicializarComponente();
	}

	public ProdutoView(ConsultaProdutoView consultaProdutoView) {
		super(consultaProdutoView, true);
		this.consultaProdutoView = consultaProdutoView;
		inicializarComponente();

		servicoBeanLocal = new ServicoBeanLocal();
	}

	private void inicializarComponente() {

		setTitle("Manutenção de Produto");

		produtoVO = new ProdutoVO();

		setBounds(100, 100, 466, 300);

		//Coloca a tela no centro da janela.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		getContentPane().setLayout(null);

		JLabel lblCodigo = new JLabel("Código:");
		lblCodigo.setBounds(12, 14, 117, 15);
		getContentPane().add(lblCodigo);

		tfcodigo = new JTextField();
		tfcodigo.setEditable(false);
		tfcodigo.setColumns(10);
		tfcodigo.setBounds(128, 12, 114, 19);
		getContentPane().add(tfcodigo);

		JLabel lblDescrio = new JLabel("Descrição: *");
		lblDescrio.setBounds(12, 43, 109, 15);
		getContentPane().add(lblDescrio);

		tfDescricao = new JTextField();
		tfDescricao.setColumns(10);
		tfDescricao.setBounds(128, 41, 322, 19);
		getContentPane().add(tfDescricao);

		JLabel lblCodBar = new JLabel("Cód. Barras: *");
		lblCodBar.setBounds(12, 72, 109, 15);
		getContentPane().add(lblCodBar);

		ftfCodBar = new JFormattedTextField();
		MascaraJFormattedTextField.formatNumericField("###################", ftfCodBar);
		ftfCodBar.setBounds(128, 70, 194, 19);
		getContentPane().add(ftfCodBar);

		ftfQtd = new JFormattedTextField();
		MascaraJFormattedTextField.formatNumericField("#######.000", ftfQtd);
		ftfQtd.setBounds(128, 99, 84, 19);
		getContentPane().add(ftfQtd);

		JLabel lblQtd = new JLabel("Qtd. Estoque: *");
		lblQtd.setBounds(12, 101, 109, 15);
		getContentPane().add(lblQtd);

		JLabel lblVlrCompra = new JLabel("Vlr. Compra: *");
		lblVlrCompra.setBounds(12, 130, 109, 15);
		getContentPane().add(lblVlrCompra);

		ftfVlrCompra = new JFormattedTextField();
		MascaraJFormattedTextField.formatNumericField(ftfVlrCompra);
		ftfVlrCompra.setBounds(128, 128, 114, 19);
		getContentPane().add(ftfVlrCompra);

		JLabel lblVlrVenda = new JLabel("Vlr. Venda: *");
		lblVlrVenda.setBounds(12, 159, 109, 15);
		getContentPane().add(lblVlrVenda);

		ftfVlrVenda = new JFormattedTextField();
		MascaraJFormattedTextField.formatNumericField(ftfVlrVenda);
		ftfVlrVenda.setBounds(128, 157, 114, 19);
		getContentPane().add(ftfVlrVenda);

		JLabel lbStatus = new JLabel("Status: *");
		lbStatus.setBounds(12, 191, 109, 15);
		getContentPane().add(lbStatus);

		cbStatus = new JComboBox();
		cbStatus.setModel(new DefaultComboBoxModel(StatusEnum.values()));
		cbStatus.setBounds(128, 186, 114, 24);
		getContentPane().add(cbStatus);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar();
			}
		});
		btnSalvar.setBounds(208, 234, 117, 25);
		getContentPane().add(btnSalvar);

		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		btnFechar.setBounds(337, 234, 117, 25);
		getContentPane().add(btnFechar);
	}

	private void salvar() {
		
		try {
			
			String codigo = tfcodigo.getText();
			if(codigo.trim().length() > 0) {
				produtoVO.setId(new BigInteger(codigo));
			}
			
			String descri = tfDescricao.getText();
			produtoVO.setDescri(descri);

			String vlrcom = ftfVlrCompra.getText().trim();
			vlrcom = vlrcom.replaceAll("\\.", "").replaceAll(",", ".");
			if(vlrcom.length() > 1) {
				BigDecimal vlrCompra = new BigDecimal(vlrcom);
				produtoVO.setValcom(vlrCompra);
			}
			
			String vlrven = ftfVlrVenda.getText().trim();
			vlrven = vlrven.replaceAll("\\.", "").replaceAll(",", ".");
			if(vlrven.length() > 1) {
				BigDecimal vlrVenda = new BigDecimal(vlrven);
				produtoVO.setValven(vlrVenda);
			}
			
			String qtd = ftfQtd.getText().trim();
			qtd = qtd.replaceAll("\\.", "").replaceAll(",", ".");
			if(qtd.length() > 1) {
				BigDecimal qtdest = new BigDecimal(qtd);
				produtoVO.setQtdest(qtdest);
			}
			
			String codbar = ftfCodBar.getText().trim();
			if(codbar.length() > 0) {
				produtoVO.setCodbar(codbar);
			}
			
			StatusEnum status = (StatusEnum)cbStatus.getSelectedItem();
			if(status != null) {
				produtoVO.setStatus(status.name());
			}
			
			produtoVO.setClient(Dados.getClienteSelecionado());
			
			servicoBeanLocal.salvarProduto(produtoVO);
			
			tfcodigo.setText(produtoVO.getId().toString());
			
			consultaProdutoView.pesquisar();
			
			JOptionPane.showMessageDialog(this, "Operação realizada com sucesso!", "Mensagem de confirmação", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (BOValidationException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(), "Mensagem de alerta", JOptionPane.WARNING_MESSAGE);
		} catch (BOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao realizar a operação!", "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
		}

	}

	public void editar(ProdutoVO produtoVO) {

		this.tfcodigo.setText(produtoVO.getId().toString());
		this.tfDescricao.setText(produtoVO.getDescri());
		this.ftfCodBar.setText(produtoVO.getCodbar());
		this.ftfVlrCompra.setText(produtoVO.getValcom().toPlainString());
		this.ftfVlrVenda.setText(produtoVO.getValven().toPlainString());
		this.ftfQtd.setText(produtoVO.getQtdest().toPlainString());

	}

	private void fechar() {
		setVisible(false);
		dispose();
	}

}
