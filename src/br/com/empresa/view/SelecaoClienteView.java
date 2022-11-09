package br.com.empresa.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import br.com.empresa.dao.Dados;
import br.com.empresa.exception.BOException;
import br.com.empresa.service.IServicoBeanLocal;
import br.com.empresa.service.ServicoBeanLocal;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.UsuarioClienteVO;

public class SelecaoClienteView extends JFrame {

	private JPanel contentPane;
	private JTextField tfFiltro;
	private JList list;

	/**
	 * Create the frame.
	 */
	public SelecaoClienteView() {
		setTitle("Seleção de instituição");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 349, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ListModel<ClienteVO> listModel = new DefaultListModel<ClienteVO>();
		list = new JList();
		list.setModel(listModel);
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JScrollPane scrollPane = new JScrollPane(list);
		
		carregarValoresListModel();

		JButton btnSelecionar = new JButton("Selecionar");
		btnSelecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarCliente();
			}
		});
		btnSelecionar.setBounds(15, 282, 133, 25);
		contentPane.add(btnSelecionar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelar();
			}
		});
		btnCancelar.setBounds(204, 282, 133, 25);
		contentPane.add(btnCancelar);

		JLabel lblFiltro = new JLabel("Filtro");
		lblFiltro.setBounds(12, 5, 70, 15);
		contentPane.add(lblFiltro);

		tfFiltro = new JTextField();
		tfFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				carregarValoresListModel();
			}

		});
		tfFiltro.setBounds(12, 25, 325, 19);
		contentPane.add(tfFiltro);
		tfFiltro.setColumns(10);

		
		scrollPane.setBounds(15, 56, 319, 214);
		contentPane.add(scrollPane);

		

		//Coloca a tela no centro da janela.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
	}
	
	private void selecionarCliente() {
		
		DefaultListModel defaultListModel = (DefaultListModel) list.getModel();
		
		if(list.getSelectedIndex() >= 0) {
			ClienteVO clienteVO = (ClienteVO)defaultListModel.get(list.getSelectedIndex());
			Dados.setClienteSelecionado(clienteVO);
			
			MenuSistemaView menu = new MenuSistemaView();
			menu.setVisible(true);
			this.setVisible(false);
			
		}else {
			
			JOptionPane.showMessageDialog(null, "É necessário selecionar um cliente para continuar.", "Validação", JOptionPane.WARNING_MESSAGE);		
			
		}
		
		
	}
	
	private void cancelar() {
		LoginView frame = new LoginView();
		frame.setVisible(true);		
		this.setVisible(false);
	}

	private void carregarValoresListModel() {

		try {

			IServicoBeanLocal servicoBeanLocal = new ServicoBeanLocal();
			List<UsuarioClienteVO> usuarioClienteVOs = servicoBeanLocal.listarClientesUsuario(Dados.getUsuarioSelecionado());

			DefaultListModel defaultListModel = (DefaultListModel) list.getModel();
			defaultListModel.clear();

			if (tfFiltro != null && tfFiltro.getText() != null) {
				for (UsuarioClienteVO usuarioClienteVO : usuarioClienteVOs) {
					if (usuarioClienteVO.getClienteVO().getDescri().contains(tfFiltro.getText())) {
						defaultListModel.addElement(usuarioClienteVO.getClienteVO());
					}
				}
			} else {
				for (UsuarioClienteVO usuarioClienteVO : usuarioClienteVOs) {
					defaultListModel.addElement(usuarioClienteVO.getClienteVO());
				}
			}

		} catch (BOException e) {
			e.printStackTrace();
		}

	}
}
