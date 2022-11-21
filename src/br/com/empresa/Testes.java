package br.com.empresa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Path;
import java.sql.PreparedStatement;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

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

		// testes.lerArquivo();
		testes.escreverArquivo();

	}

	private void escreverArquivo() {

		ClienteVO clienteVO = new ClienteVO();
		clienteVO.setId(BigInteger.ONE);

		EntityManager em = HibernateUtil.getEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<ProdutoVO> criteria = cb.createQuery(ProdutoVO.class);

		// From
		Root<ProdutoVO> produtoFrom = criteria.from(ProdutoVO.class);

		// Where
		Predicate produtoWhere = cb.equal(produtoFrom.get("client"), clienteVO);

		criteria.where(produtoWhere);

		TypedQuery<ProdutoVO> query = em.createQuery(criteria);

		List<ProdutoVO> retornoProdutos = query.getResultList();

		em.close();

		File destinoArquivo = null;
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int opcao = fileChooser.showDialog(fileChooser, null);

		destinoArquivo = fileChooser.getSelectedFile();

		String path = destinoArquivo.getAbsolutePath() + "\\produto.csv";

		File newFile = new File(path);

		try {

			OutputStream outputStream = new FileOutputStream(newFile, true);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "ISO-8859-1"); // UTF-8
			PrintWriter printWriter = new PrintWriter(outputStream, true);

			printWriter.println("id,descri,codbar,status,qtdest,valcom,valven,client");

			for (ProdutoVO produtoVO : retornoProdutos) {
				printWriter.println(
						produtoVO.getId() + "," +
								produtoVO.getDescri() + "," +
								produtoVO.getCodbar() + "," +
								produtoVO.getStatus() + "," +
								produtoVO.getQtdest() + "," +
								produtoVO.getValcom() + "," +
								produtoVO.getValven() + "," +
								produtoVO.getClient().getId());
			}

			printWriter.close();
			outputStreamWriter.close();
			outputStream.close();

			System.out.println("Escrita completa");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
