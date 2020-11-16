package io;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 * Classe responsavel por gerencia os arquivos de uma maneira geral
 * 
 * @author tabatista
 * @version 1.1
 */
public class GerenciadorArquivo {

	/**
	 * Exclui arquivos que comecem com a variavel <b>comeco</b> e terminem com
	 * <b>fim</b>.
	 * 
	 * Muita <b>atencao</b> ao utilizar este metodo, ele pode deletar arquivos
	 * indesejados e estes nao vao para a lixeira - sao excluidos diretamente
	 * 
	 * @param comeco
	 * @param fim
	 *            geralmente a extensao do arquivo. Necessário colocar ponto
	 */
	public static void excluirArquivosComecoFim(String caminho, String comeco, String fim) {

		// diretório que será listado.
		File baseFolder = new File(caminho);

		// obtem a lista de arquivos
		File[] files = baseFolder.listFiles();

		for (File arq : files) {

			if (arq.getName().startsWith(comeco) && arq.getName().endsWith(fim)) {
				arq.delete();
				System.out.println("Arquivo " + arq.getName() + " excluído!");
			}
		}

		System.out.println("\nTodos os arquivos [" + comeco + "] [" + fim + "] de " + caminho + " foram excluídos!");

	}

	/**
	 * Exclui arquivos *sem extensao e *sem conteudo
	 */
	public static void excluirArquivosIndefinidos(String caminho) {
		// diretório que será listado.
		File baseFolder = new File(caminho);

		// obtem a lista de arquivos
		File[] files = baseFolder.listFiles();

		for (File arq : files) {

			int i = 0;

			try {
				// uma pasta eh um arquivo sem extensao, mas pode ter conteudo
				// dentro, se houver, nao serah excluida
				i = arq.listFiles().length;

			} catch (NullPointerException e) {

			}

			// -1 = nao localiza a extensao do arquivo, mas se houver ponto em
			// qualquer parte de seu nome, nao será excluido
			if (arq.getName().lastIndexOf(".") == -1 && i == 0) {
				arq.delete();
				System.out.println("Arquivo " + arq.getName() + " excluído!");
			}
		}

		System.out.println("\nTodos os arquivos *sem extensão (e sem ponto) e sem conteúdo foram excluídos!");

	}

	/**
	 * Renomeia o arquivo
	 * 
	 * @param nomeArquivo
	 *            o nome atual do arquivo
	 * @param novoNome
	 *            o novo nome a ser definido
	 * @return se foi renomeado com sucesso
	 */
	public boolean renomearArquivo(String nomeArquivo, String novoNome) {

		return new File(nomeArquivo).renameTo(new File(novoNome));
	}

	/**
	 * Apaga um arquivo com contagem. OBS: ha espaco entre o nome e o contador
	 * 
	 * @param caminho
	 *            o caminho onde esta arquivo
	 * @param nomeArquivo
	 *            o nome do arquivo
	 * @param extensao
	 *            a extasao do arquivo. Nao precisa de ponto
	 * @return se apagou com sucesso
	 */
	public boolean excluirArquivo(String caminho, String nomeArquivo, String extensao) {
		return new File(caminho + nomeArquivo + "." + extensao).delete();
	}

	/**
	 * Metodo responsavel por abrir em tela um arquivo
	 * 
	 * @param caminho
	 *            o caminho onde esta arquivo
	 * @param nomeArquivo
	 *            o nome completo do arquivo, incluindo a extensao
	 * @throws IOException
	 */
	public static void abrirArquivo(String caminho, String nomeArquivoExtensao) throws IOException {

		File arquivo = new File(caminho + nomeArquivoExtensao);

		System.out.println("Caminho Excel --> " + arquivo);

		Desktop desktop = Desktop.getDesktop();

		// Abre o Arquivo
		desktop.open(arquivo);
	}

}
