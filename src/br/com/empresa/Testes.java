package br.com.empresa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.PreparedStatement;

import javax.persistence.EntityManager;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import antlr.collections.List;
import br.com.empresa.dao.HibernateUtil;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.ProdutoVO;

public class Testes {

	public void selecionarArquivo() throws FileNotFoundException {

		File arquivoOrigem = null;
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int opcao = fileChooser.showDialog(fileChooser, null);

		if (opcao == JFileChooser.APPROVE_OPTION) {

			arquivoOrigem = fileChooser.getSelectedFile();

			lerArquivoSelecionado(arquivoOrigem);
		} else {
			JOptionPane.showMessageDialog(null, "Não pode seguir");
		}
	}

	private void lerArquivoSelecionado(File arquivoOrigem) {

		try {
			FileReader fileReader = new FileReader(arquivoOrigem);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			EntityManager em = HibernateUtil.getEntityManager();

			String linha = null;
			int numLinha = 0;

			while ((linha = bufferedReader.readLine()) != null) {
				numLinha++;

				if (numLinha > 1) {
					ClienteVO clienteVO = new ClienteVO();
					clienteVO.setId(BigInteger.ONE);

					String[] particionamento;

					if (linha.contains(",")) {
						particionamento = linha.split(",");
					} else {
						particionamento = linha.split(";");
					}

					String descri = particionamento[1].replaceAll("\\s+", " ");
					String codbar = particionamento[2].trim();

					ProdutoVO novoProduto = new ProdutoVO();
					novoProduto.setDescri(descri);
					novoProduto.setCodbar(codbar);
					novoProduto.setClient(clienteVO);
					novoProduto.setQtdest(BigDecimal.ZERO);
					novoProduto.setStatus("I");
					novoProduto.setValcom(BigDecimal.ZERO);
					novoProduto.setValven(BigDecimal.ZERO);

					em.getTransaction().begin();
					em.persist(novoProduto);
					em.getTransaction().commit();
				}
			}

			fileReader.close();
			bufferedReader.close();
			em.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

							ClienteVO clienteVO = new ClienteVO();
							clienteVO.setId(BigInteger.ONE);

							String[] particionamento = linha.split(";");

							String descri = particionamento[1].replaceAll("\\s+", " ");
							String codbar = particionamento[2].trim();

							ProdutoVO novoProduto = new ProdutoVO();
							novoProduto.setDescri(descri);
							novoProduto.setCodbar(codbar);
							novoProduto.setClient(clienteVO);
							novoProduto.setQtdest(BigDecimal.ZERO);
							novoProduto.setStatus("I");
							novoProduto.setValcom(BigDecimal.ZERO);
							novoProduto.setValven(BigDecimal.ZERO);

							em.getTransaction().begin();
							em.persist(novoProduto);
							em.getTransaction().commit();

							// String status = particionamento[3].trim();

							// ps.setString(1, descri);
							// ps.setString(2, codbar);
							// ps.setString(3, "A");
							// ps.setBigDecimal(4, BigDecimal.ZERO);
							// ps.setBigDecimal(5, BigDecimal.ZERO);
							// ps.setBigDecimal(6, BigDecimal.ZERO);
							// ps.setLong(7, 1L);
							//
							// ps.execute();
							// ps.close();

						}

					}

					em.close();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					em.close();
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

		testes.selecionarArquivo();

	}

}
