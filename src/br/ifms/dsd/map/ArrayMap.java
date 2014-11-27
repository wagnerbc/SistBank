package br.ifms.dsd.map;

import java.util.ArrayList;

public class ArrayMap {
	ArrayList<Mapping> arrayMap;

	public ArrayMap() {
		arrayMap = new ArrayList<Mapping>();
	}
	
	public void add(Mapping mapping){
		arrayMap.add(mapping);
	}
	
	public void list(){  // lista os caixas conectados 
		System.out.println("\n*************************\n" + arrayMap.size() + " Caixa(s) conectado(s).");
		for(Mapping mapping : arrayMap){
			System.out.println("  -> " + mapping.getNome()); // imprime o nome dos caixas conectados
		}
		System.out.println("*************************\n");
	}
	
	public void delete(String nome){  // remove da lista de caixas o caixa selecionado - usado na opção "Sair" do caixa
		Mapping temp = null;
		for(Mapping mapping : arrayMap){
			if(mapping.getNome().equals(nome)){
				temp = mapping;
				break;
			}
		}
		if(temp != null){
			arrayMap.remove(temp);
		}else{
			System.out.println("NULO");
		}
	}
	
	public void desativar(String nome){  // Desativa o caixa informado - usado pelo controlador
		for(Mapping mapping : arrayMap){
			if(mapping.getNome().equals(nome)){
				mapping.send("Desativando Caixa");
				arrayMap.remove(mapping);
				System.out.println("\n*******************************\n" + nome +
						" Desativado com Sucesso\n*******************************\n");
				break;
			}else{
				System.out.println("\n**************************************\n"
						+ "O Caixa informado não está conectado\n**************************************\n");
			}
		}
	}
}
