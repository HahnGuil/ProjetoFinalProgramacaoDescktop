package br.com.empresa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;

import javax.persistence.EntityManager;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import br.com.empresa.dao.HibernateUtil;

public class Testes {

	public void lerArquivo() throws FileNotFoundException {

		File arquivoOrigem = null;
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int opcao = fileChooser.showDialog(fileChooser, null);

		if (opcao == JFileChooser.APPROVE_OPTION) {

			arquivoOrigem = fileChooser.getSelectedFile();

			System.out.println(arquivoOrigem.getAbsolutePath());
			System.out.println(arquivoOrigem.getName());

			lerPastaDoArquivo(arquivoOrigem);
		} else {
			JOptionPane.showMessageDialog(null, "Não pode seguir");
		}
	}

	private void lerPastaDoArquivo(File arquivoOrigem) throws FileNotFoundException {
		File[] listagem = arquivoOrigem.listFiles();

		for (File file : listagem) {
			System.out.println(file.getAbsolutePath());
			if (file.isDirectory()) {
				System.out.println("Arquivo");
			} else {
				System.out.println("Arquivo");

				EntityManager em = HibernateUtil.getEntityManager();
				
				FileReader fileReader = new FileReader(arquivoOrigem);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				
				String linha = null;

				int numLinha = 0;

				try {
					while ((linha = bufferedReader.readLine()) != null) {

						numLinha++;

						if (numLinha > 1) {

							String[] particionamento = linha.split(";");

							String descri = particionamento[1].replaceAll("\\s+", " ");
							String codbar = particionamento[2].trim();
//						String status = particionamento[3].trim();

							

//						ps.setString(1, descri);
//						ps.setString(2, codbar);
//						ps.setString(3, "A");
//						ps.setBigDecimal(4, BigDecimal.ZERO);
//						ps.setBigDecimal(5, BigDecimal.ZERO);
//						ps.setBigDecimal(6, BigDecimal.ZERO);
//						ps.setLong(7, 1L);
//
//						ps.execute();
//						ps.close();

						}

					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

			}
		}

	}

	public static void main(String[] args) throws FileNotFoundException {

		Testes testes = new Testes();

		testes.lerArquivo();

	}

}
