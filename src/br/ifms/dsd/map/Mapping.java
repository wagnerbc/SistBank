package br.ifms.dsd.map;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Mapping {

	private Socket conection;
	private Scanner read;
	PrintStream exit;
	private String nome;

	public Mapping(Socket conection) {
		this.conection = conection;
		
		try {
			exit = new PrintStream(conection.getOutputStream());
			read = new Scanner(conection.getInputStream());  
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close(){
		try {
			conection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}

	public void send(String msg){  // Envia mensagem
		exit.println(msg);
	}
	
	public String receive(){  // recebe a mensagem
		return read.nextLine();
	}
	
	public String getNome(){
		return nome;
	}
	
}
