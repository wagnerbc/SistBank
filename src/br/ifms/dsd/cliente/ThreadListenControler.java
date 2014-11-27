package br.ifms.dsd.cliente;

import java.net.Socket;

import br.ifms.dsd.map.Mapping;

public class ThreadListenControler extends Thread {
	Socket caixa;
	Mapping mapping;
	Boolean status;
	
	ThreadListenControler(Socket caixa) {
		this.caixa = caixa;
		mapping = new Mapping(caixa);
		status = true;
	}
	
	public void ouvir(){
		try {
			String msg = mapping.receive();  // recebe mensagem do Controlador para encerrar o caixa 
			status = false;
			System.out.println("*******************************\nCaixa encerrado Remotamente.\n*******************************");
			System.exit(0);  // fecha o processo do caixa
		} catch (Exception e) {
			
		}
	}
	
	public void run(){
		
		while(true){	
			ouvir();
		}
	}
	
	public Boolean getStatus(){
		return status;
	}
}
