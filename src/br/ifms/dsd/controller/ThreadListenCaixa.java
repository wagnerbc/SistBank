package br.ifms.dsd.controller;

import br.ifms.dsd.map.ArrayMap;
import br.ifms.dsd.map.Mapping;

public class ThreadListenCaixa extends Thread {

	Mapping mapping;
	ArrayMap arrayMap;
	
	
	ThreadListenCaixa(ArrayMap arrayMap, Mapping mapping) {
		this.mapping = mapping;
		this.arrayMap = arrayMap;
	}
	
	public void ouvir(){
		try {
			String msg = mapping.receive();  // recebe mensagem do caixa caso esse encerre o processo através da opção "Sair"do menu.
			arrayMap.delete(mapping.getNome());  // retira o caixa da lista de caixas
			System.out.println("Mensagem recebida: " + msg);
		} catch (Exception e) {
			
		}	
	}	
	
	public void run(){	
		ouvir();
	}
}


