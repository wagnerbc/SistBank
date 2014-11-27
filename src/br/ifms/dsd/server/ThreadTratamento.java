/* Thread do Servidor  */

package br.ifms.dsd.server;

import java.net.Socket;

import br.ifms.dsd.map.Mapping;
import br.ifms.dsd.model.Banco;
import br.ifms.dsd.model.TipoOperacao;

public class ThreadTratamento extends Thread {

	Socket caixa;
	Banco banco;
	TipoOperacao opt = TipoOperacao.NULO;
		
	public ThreadTratamento(Socket caixa, Banco banco) {
		this.caixa = caixa;
		this.banco = banco;
	}
	
	public void tratar(){
		try {
			Mapping mapping = new Mapping(caixa);
			 
			String msg = mapping.receive();
			 
			String[] msgVetor = msg.split("-");  // cria vetor com a mensagem repassada pelo caixa
												//	onde a primeira posição será sempre a operação a ser realizada
			
			opt = TipoOperacao.getEnumByString(msgVetor[0]);
			 
			switch (opt) {
				case ACCESS:
					mapping.send(String.valueOf(banco.acessarConta(msgVetor[1], msgVetor[2])));
					break;
					
				case SAQUE:
					mapping.send(banco.saque(msgVetor[1], Double.parseDouble(msgVetor[2])));
					break;
					
				case DEPOSITO:
					mapping.send(banco.deposito(msgVetor[1],  Double.parseDouble(msgVetor[2])));
					break;
					
				case SALDO:
					mapping.send(banco.saldo(msgVetor[1]));
					break;
					
				case EXTRATO:
					mapping.send(banco.extrato(msgVetor[1]));
					break;
					
				default:
					System.out.println("Opção inválida");
					break;
			}
			
		} catch (Exception e) {
			
		}		
	}
	
	public void run(){
		while(true){
			tratar();
		}
	}
}
