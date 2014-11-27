/* Servidor de acesso ao Banco */

package br.ifms.dsd.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import br.ifms.dsd.model.Banco;

public class Server {
	public static void main(String[] args) throws IOException {
		
		 ServerSocket server = new ServerSocket(12345);
		 Banco banco = new Banco();
		 
		 
		 while (true) {
			 
			Socket caixa = server.accept();
			 
			ThreadTratamento threadTratamento = new ThreadTratamento(caixa, banco);
			
			threadTratamento.start();
			
	     }
	}
} 
