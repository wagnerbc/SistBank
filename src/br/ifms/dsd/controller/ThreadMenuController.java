package br.ifms.dsd.controller;

import java.net.Socket;
import java.util.Scanner;

import br.ifms.dsd.map.ArrayMap;
import br.ifms.dsd.map.Mapping;

public class ThreadMenuController extends Thread {

	Mapping mapping;
	Boolean status;
	ArrayMap caixas;
	Scanner read = new Scanner(System.in);
	
	
	ThreadMenuController(ArrayMap caixas) {
		this.caixas = caixas;
		this.status = true;
	}
	
		
	public void envia(){
		
		try {
			System.out.println("Operações Disponíveis:\n-> 1 - Listar Caixas\n-> 2 - Desativar Caixa");
			String opcao = read.nextLine();
			
			switch (opcao) {
			
				case "1":
					caixas.list();
					
					break;
					
				case "2":
					
					System.out.println("\nInforme o nome do caixa para ser desativado: ");
					String n = read.nextLine();
					caixas.desativar(n);  // desativa o caixa selecionado
					
					
					break;

			default:
				break;
			}
		} catch (Exception e) {
		
		}
		
		
	}
	
	public void run(){
		
		while(true){
			envia();
		}
	}
	
}
