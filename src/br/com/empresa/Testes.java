package br.com.empresa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import antlr.collections.List;
import br.com.empresa.dao.HibernateUtil;
import br.com.empresa.vo.ProdutoVO;

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

							ProdutoVO produto = new ProdutoVO();

						}

					}

					em.close();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}

	public static void main(String[] args) throws FileNotFoundException {

		Testes testes = new Testes();

//		testes.lerArquivo();
		testes.escreverArquivo();

	}

	private void escreverArquivo() {

		/*
		 * while(enquanto tiver retorno da consulta sql){ Precisa criar uma varíavel
		 * auxiliar para converter o ID que vem em big interger para string
		 * 
		 * Criar um array, onde cada posição dele vai ser uma das colunas do arquivo.
		 * 
		 * Primeira coluna: codigo segunda: descri: terceia: codbar quarta: qtEstoque
		 * quinta: vlrcompra sexta: vlrvenda sétima: vlrlucro oitava: datavalidade
		 * 
		 * 
		 * 
		 * }
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 */

	}

}
