package main;

import java.io.IOException;

import io.LeitorArquivo;
import util.MeuUtil;

public class Teste {

	public static void main(String[] args) {

		LeitorArquivo l = new LeitorArquivo();
		try {
			l.abrirArquivo(MeuUtil.getUserHome() + "/Desktop/mensagens.txt");

			String lin = l.lerLinha();
			int count = 0;

			while (lin != null) {
				count++;
				lin = l.lerLinha();
			}
			
			l.fecharArquivo();
			System.out.println(count);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
