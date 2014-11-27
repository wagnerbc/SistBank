/* Caixa - (cliente) */

package br.ifms.dsd.cliente;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.Scanner;

import br.ifms.dsd.map.Mapping;
import br.ifms.dsd.model.TipoOperacao;

public class Caixa {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Scanner read = new Scanner(System.in);
		Socket caixa = new Socket("127.0.0.1", 12345);
		Socket caixaControlador = new Socket("127.0.0.1", 1234);
		
		Mapping mapping = new Mapping(caixa);
		Mapping mappingControlador = new Mapping(caixaControlador);
		
		
		String resposta = "";
		String valor;
		String receber;
		String numConta = "";
		String senha = "";
		TipoOperacao opt = TipoOperacao.NULO;
		String msgThread = null;
		Boolean status = true;
		
		ThreadListenControler threadListenControler = new ThreadListenControler(caixaControlador);
		threadListenControler.start();
		
		while (true) {	
			while ((!resposta.equals("true"))) {
				System.out.println("******Efetue o Acesso à Conta*****");
				System.out.println("Informe o número da conta: ");
				numConta = read.nextLine();
				
				System.out.println("Informe Senha: ");
				senha = read.nextLine();
				
				mapping.send(TipoOperacao.ACCESS.value + "-" + numConta + "-" + senha);  // envia os dados para fazer o acesso à conta
				
				resposta = mapping.receive();  // recebe a resposta da função de login...
											   // se foi permitido ou não
				if(resposta.equals("true")){
					System.out.println("\n****************************\nAcesso realizado com sucesso.\n****************************\n");
				}else{
					System.out.println("\n****************************\nAcesso Negado.\n****************************\n");
				}
			}
			
			System.out.println("Informe uma das Opções a seguir.\n-> Saque\n-> Deposito\n-> Saldo\n-> Extrato\n-> Logout\n-> Sair");
			String opcao = read.nextLine();
			opt = TipoOperacao.getEnumByString(opcao);
			
			switch (opt){
				case SAQUE:
					if(resposta.equals("")){
						System.out.println("\n*************************\nEfetue o Acesso à conta!\n*************************\n");
					}else{
						System.out.println("Informe o valor do saque");
						valor = read.nextLine();
						
						mapping.send(TipoOperacao.SAQUE.value + "-" + numConta + "-" + valor);  // envia os dados para realizar o saque
						
						receber = mapping.receive();  // recebe os dados de retorno da função de saque
						System.out.println("\n*******************************************\n" + receber +
								"\n*******************************************\n");
					}
					
					break;
					
				case DEPOSITO:
					if(resposta.equals("")){
						System.out.println("\n*************************\nEfetue o Acesso à conta!\n*************************\n");
						
					}else{
						System.out.println("Informe o valor do deposito");
						valor = read.nextLine();
												
						mapping.send(TipoOperacao.DEPOSITO.value + "-" + numConta + "-" + valor);  // envia os dados para realizar o depósito
						
						receber = mapping.receive();  // recebe os dados de retorno da função de saque
						System.out.println("\n*******************************************\n" + receber +
								"\n*******************************************\n");
					}
					
					break;
					
				case SALDO:
					if(resposta.equals("")){
						System.out.println("\n*************************\nEfetue o Acesso à conta!\n*************************\n");
						
					}else{
						mapping.send(TipoOperacao.SALDO.value + "-" + numConta);  // envia os dados para tirar Saldo
						
						receber = mapping.receive();  // recebe os dados de retorno da função de Saldo
						
						System.out.println("\n*******************************************\n" + "Saldo: R$ " + receber +
								"\n*******************************************\n");
					}				
					
					break;
					
				case EXTRATO:
					if(resposta.equals("")){
						System.out.println("\n*************************\nEfetue o Acesso à conta!\n*************************\n");
						
					}else{
						mapping.send(TipoOperacao.EXTRATO.value + "-" + numConta);  // envia os dados para imprimir o extrato
						receber = mapping.receive();				// recebe a String com os dados para montar o extrato
						
						mapping.send(TipoOperacao.SALDO.value + "-" + numConta);  // Envia os dados para tirar o Saldo
						receber += "-                                  _________-                    Saldo Atual >>  " +
								mapping.receive();							// Concatena ao final da String de extrato o retorno da função saldo
						receber += "-**********************************************-";
						
						receber = receber.replaceAll("-", "\n");  // substitui "-" por "\n" para inserir quebras de linha na String do extrato...
						System.out.println(receber);			  // para ser impresso corretamente
					}	
					
					break;
					
				case LOGOUT:
					if(resposta.equals("")){
						System.out.println("\n*************************\nEfetue o Acesso à conta!\n*************************\n");
						
					}else{
						resposta = "";
						numConta= "";
						senha = "";
						System.out.println("\n*******************************************\n Acesso Finalizado \n"
								+ "*******************************************\n");
					}				
					break;
					
				case SAIR:
					if(resposta.equals("")){
						System.out.println("\n*************************\nEfetue o Acesso à conta!\n*************************\n");
						
					}else{
						System.out.println("\nCaixa Desconectado....");
						mappingControlador.send("mensagem"); // envia mensagem para encerrar a conexão do caixa com o servidor
						mappingControlador.close();  // fecha o mapeamento
						System.exit(0);   // encerra o processo do caixa
					}	
					break;
					
				default:
					System.out.println("\n*************************\nOpção inválida\n*************************\n");
					
					break;
			}
		}
	}
}
