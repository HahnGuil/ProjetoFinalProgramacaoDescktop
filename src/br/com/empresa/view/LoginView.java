package br.com.empresa.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.empresa.dao.Dados;
import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.service.IServicoBeanLocal;
import br.com.empresa.service.ServicoBeanLocal;
import br.com.empresa.vo.UsuarioClienteVO;
import br.com.empresa.vo.UsuarioVO;

public class LoginView extends JFrame {

	private JPanel contentPane;
	private final JLabel lblLogin = new JLabel("Login:");
	private JTextField txtLogin;
	private JPasswordField pwdSenha;

	/**
	 * Create the frame.
	 */
	public LoginView() {
		setTitle("Autenticação");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 453, 167);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblLogin.setBounds(12, 22, 70, 15);
		contentPane.add(lblLogin);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(12, 63, 70, 15);
		contentPane.add(lblSenha);

		JButton btnAcessar = new JButton("Acessar");

		btnAcessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				executarLogin();
			}
		});
		btnAcessar.setBounds(89, 101, 117, 25);
		contentPane.add(btnAcessar);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.exit(0);

			}
		});
		btnSair.setBounds(247, 101, 117, 25);
		contentPane.add(btnSair);

		txtLogin = new JTextField();
		txtLogin.setBounds(89, 20, 275, 19);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);

		pwdSenha = new JPasswordField();
		pwdSenha.setBounds(89, 61, 275, 19);
		contentPane.add(pwdSenha);

		//Coloca a tela no centro da janela.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
	}

	private void executarLogin() {

		IServicoBeanLocal servicoBeanLocal = new ServicoBeanLocal();

		String senha = new String(pwdSenha.getPassword());

		try {

			UsuarioVO usuario = servicoBeanLocal.validarAcesso(txtLogin.getText(), senha);
			Dados.setUsuarioSelecionado(usuario);

			int qtd = servicoBeanLocal.buscarQuantidadeClientesUsuario(usuario);

			if (qtd == 1) {

				List<UsuarioClienteVO> usuarioClienteVOs = servicoBeanLocal.listarClientesUsuario(Dados.getUsuarioSelecionado());

				Dados.setClienteSelecionado(usuarioClienteVOs.get(0).getClienteVO());

				MenuSistemaView menu = new MenuSistemaView();
				menu.setVisible(true);
				this.setVisible(false);

			} else if (qtd == 0) {

				throw new BOValidationException("Você não possui instituições habilitadas, consulte o administrador.");

			} else if (qtd > 1) {
				SelecaoClienteView selecaoClienteView = new SelecaoClienteView();
				selecaoClienteView.setVisible(true);
				super.setVisible(false);
			}

		} catch (BOValidationException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Validação", JOptionPane.WARNING_MESSAGE);
		} catch (BOException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
}
