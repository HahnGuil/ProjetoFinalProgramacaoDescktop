package br.com.empresa;
import br.com.empresa.view.LoginView;

public class Principal {

	public Principal() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

		try {
			LoginView frame = new LoginView();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
