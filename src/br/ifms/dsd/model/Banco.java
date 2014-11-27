package br.ifms.dsd.model;

import java.util.ArrayList;

public class Banco {

	ArrayList<Conta> contas;
	
	public Banco(){
	
		contas = new ArrayList<Conta>();
		
		Conta c1 = new Conta("123", "123");
		Conta c2 = new Conta("456", "456");
		Conta c3 = new Conta("789", "789");
		
		contas.add(c1);
		contas.add(c2);
		contas.add(c3);
	}
	
	public Conta buscar(String numConta){
		
		for (Conta conta: contas) {
			
			if(conta.numConta.equals(numConta)){
				return conta;
			}
		}
		return null;
	}
	
	public boolean acessarConta(String numConta, String password){
		
		Conta conta = buscar(numConta);
		
		
		if(conta != null){
			if(conta.getNumConta().equals(numConta)){
				if(conta.getPassword().equals(password)){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}
		return false;
		
	}
	
	
	public String deposito(String numConta, double valor){
		
		Conta conta = buscar(numConta);
		
		if(conta != null){
			conta.deposito(valor);
			
			return "Deposito realizado"; 
		}
		
		return "Conta inválida";
	}
	
	public String saque(String numConta, double valor){
		
		Conta conta = buscar(numConta);
		
		if(conta != null){
			String resposta = conta.saque(valor);
			return resposta;
		}
		
		return "n-Conta inválida";
		
	}
	
	public String saldo(String numConta){
		
		Conta conta = buscar(numConta);
		
		if(conta != null){
			return conta.saldo();
		}
		
		return "Conta inválida";
		
	}
	
	public String extrato(String numConta){
		
		Conta conta = buscar(numConta);
		
		if(conta != null){
			return conta.extrato(numConta);
		}
		
		return null;
	}
}
