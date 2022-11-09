package br.com.empresa.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MenuSistemaView extends JFrame {

	
	/**
	 * Create the frame.
	 */
	public MenuSistemaView() {
		setTitle("Sistema simples de vendas");
		setBounds(100, 100, 915, 562);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		mnArquivo.setMnemonic('A');
		menuBar.add(mnArquivo);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sairSistema();
			}
		});
		mnArquivo.add(mntmSair);
		
		JMenu mnManutencao = new JMenu("Manutenção");
		mnManutencao.setMnemonic('M');
		menuBar.add(mnManutencao);
		
		JMenuItem mntmConsumidorFornecedor = new JMenuItem("Consumidor/Fornecedor");
		mntmConsumidorFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manterConsumidorFornecedor();
			}
		});
		mnManutencao.add(mntmConsumidorFornecedor);
		
		JMenuItem mntmProduto = new JMenuItem("Produto");
		mntmProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manterProduto();
			}
		});
		mnManutencao.add(mntmProduto);
		
		JMenuItem mntmRecebimento = new JMenuItem("Recebimento");
		mnManutencao.add(mntmRecebimento);
		
		JMenuItem mntmVenda = new JMenuItem("Venda");
		mnManutencao.add(mntmVenda);
		
		JMenu mnSuporte = new JMenu("Suporte");
		mnSuporte.setMnemonic('S');
		menuBar.add(mnSuporte);
		
		JMenuItem mntmSobre = new JMenuItem("Sobre");
		mnSuporte.add(mntmSobre);
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		
		//Esse bloco abaixo é para a inclusão de uma imagem no centro da tela principal do programa.
		try {
			
			InputStream streamLogo = getClass().getResourceAsStream("senac_logo.png");
			BufferedImage img = ImageIO.read(streamLogo);
			ImageIcon imageIcon = new ImageIcon(img);
			
			JLabel centerLabel = new JLabel(imageIcon);
			
			JPanel main = new JPanel(new BorderLayout());
			main.add(centerLabel, BorderLayout.CENTER);
			
			getContentPane().add(main, BorderLayout.CENTER);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao abrir a tela.", "Erro!", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	private void manterProduto() {
		JDialog consultaProdutoView =  new ConsultaProdutoView();
		consultaProdutoView.setModal(true);
		consultaProdutoView.setVisible(true);
	}
	
	private void manterConsumidorFornecedor() {
		JDialog consultaConsumidorFornecedorView = new ConsultaConsumidorFornecedorView();
		consultaConsumidorFornecedorView.setModal(true);
		consultaConsumidorFornecedorView.setVisible(true);
		
	}
	
	private void sairSistema() {
		Object[] options = {"Sim!", "Não..."};
		int n = JOptionPane.showOptionDialog(
		null, "Tem certeza?",
		"Confirmação",
		JOptionPane.YES_NO_OPTION,
		JOptionPane.QUESTION_MESSAGE,
		null,
		options,
		options[1]);
		
		if(n == 0) {
			System.exit(0);
		}
		
	}

}
