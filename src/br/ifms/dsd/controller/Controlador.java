package br.ifms.dsd.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import br.ifms.dsd.map.ArrayMap;
import br.ifms.dsd.map.Mapping;

public class Controlador {

	public static void main(String[] args) throws IOException {
		
		 ServerSocket server = new ServerSocket(1234);
		 ArrayMap caixas = new ArrayMap();
		 
		 int i = 1;
		 
		 ThreadMenuController threadMenuController = new ThreadMenuController(caixas);
		 threadMenuController.start();  // Thread para formar o menu do Controlador
		 
		 while (true) {
			Socket caixa = server.accept();
			
			Mapping temp = new Mapping(caixa);
			temp.setNome("caixa" + i);
			caixas.add(temp);  // Adiciona o "mapeamento" do caixa que foi conectado ao Array de caixas conectados
			
			ThreadListenCaixa threadListenCaixa = new ThreadListenCaixa(caixas, temp);
			threadListenCaixa.start();  // Thread para verificar se o caixa foi desconectado...
										// através da opção "Sair" do menu no caixa 
			i++;
		}
		
	}
}
