/* Classe de contas bancárias */

package br.ifms.dsd.model;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Conta {
	public String numConta;
	private String password;
	private double saldo = 0;
	private String extrato;
	
	
	
	public Conta(String numConta, String password){
		this.numConta = numConta;
		this.password = password;
		this.extrato = "**********************************************-                   EXTRATO-Conta: " + numConta +
				"-____________________________________________ -Data                    Operação    Valor";
	}
	
	public String getData(){  // recebe a data e horário no formato especificado 
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy  hh:mm:ss");
		
		return dateFormat.format(new Date());
	}
	
	public boolean access (String numConta, String senha){
		if (this.password.equals(senha)){
			return true;
		}
		
		return false;
	}
	
	
	public String saldo(){
		
		return ajustaDecimal(saldo);
	}
	
	public void deposito(double valor){
		saldo += valor;
		extrato += "-" + getData() + "    Depósito    " + ajustaDecimal(valor);
		
	}
	
	public String saque(double valor){
		 if (valor <= saldo){
			 saldo = saldo - valor;
			 extrato += "-" + getData() + "    Saque       " + ajustaDecimal(valor);
			 
			 return "Saque realizado com sucesso";
		 }
		 
		 return "Saldo insuficiente para esta operação";
	}

	public String extrato(String numConta){
		return extrato;
	}
	
	public String ajustaDecimal(Double valor){  // Formata a saída dos valores
		DecimalFormat valorTratado = new DecimalFormat("##0.00");
		
		return valorTratado.format(valor);
	}
	
	public String getNumConta() {
		return numConta;
	}

	public void setNumConta(String numConta) {
		this.numConta = numConta;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	
}
