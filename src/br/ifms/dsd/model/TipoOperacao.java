/* Enum com os tipos de operações possíveis  */

package br.ifms.dsd.model;

public enum TipoOperacao {
	DEPOSITO("Deposito"),
	SAQUE("Saque"),
	TRANSFERENCIA("Transferencia"), 
	SALDO("Saldo"),
	ACCESS("Access"),
	EXTRATO("Extrato"),
	LOGOUT("Logout"),
	SAIR("Sair"),
	NULO("Nulo");
	
	public String value;

	private TipoOperacao(String value){
		this.value = value;
	}

	public static TipoOperacao getEnumByString(String text){
		for(TipoOperacao opt : TipoOperacao.values()){
			if (opt.value.equals(text)){
				return opt;
			}
		}
		return NULO;
	}
}
