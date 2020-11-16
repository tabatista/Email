package io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Classe responsavel por gravar o arquivo de saida
 * 
 * @author tabatista
 * @version 1.1
 */
public class GravadorArquivo {

	/** stream (inputStream) */
	private FileWriter outputStream;

	/** manipulador de arquivo */
	private PrintWriter arquivoSaida;

	/**
	 * Abre um arquivo para escrita sequencial, enquanto verifica a existencia do
	 * mesmo
	 * 
	 * @param caminhoArquivo
	 *            O caminho completo(caminho + nome + extensao) ateh o arquivo
	 * @param continuaMesmoArquivo
	 *            Se deve continuar escrevendo no mesmo arquivo, dando continuidade
	 *            ao conteudo ja existente, ou refaz o mesmo e comeca do zero
	 * @return se o arquivo existe
	 * @throws IOException
	 */
	public boolean abrirArquivo(String caminhoArquivo, boolean continuaMesmoArquivo) throws IOException {
		// instancia os objetos
		File arquivo = new File(caminhoArquivo);
		boolean existeArquivo = arquivo.exists();

		if (existeArquivo) {
			// 'true' para continuar gravando apos criacao do arquivo
			outputStream = new FileWriter(caminhoArquivo, continuaMesmoArquivo);
		} else {
			// sem true para nao ter pulo na primeira linha, deixando apenas espaços em
			// branco
			outputStream = new FileWriter(caminhoArquivo);
		}
		arquivoSaida = new PrintWriter(outputStream);
		return existeArquivo;
	}

	/**
	 * Escreve uma linha no arquivo.
	 * 
	 * @param A
	 *            linha a ser escrita
	 * @throws IOException
	 */
	public void escreverLinha(String linha) {

		arquivoSaida.println(linha);

	}

	/**
	 * Fecha o arquivo
	 * 
	 * @throws IOException
	 */
	public void fecharArquivo() throws IOException {

		arquivoSaida.close();
		outputStream.close();

	}

}
