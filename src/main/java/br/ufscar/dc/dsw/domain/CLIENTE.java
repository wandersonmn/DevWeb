package br.ufscar.dc.dsw.domain;

public class CLIENTE {
	private String CPF_Cliente;
	private USUARIO usuario;
	
	
	public String getCPF_Cliente() {
		return CPF_Cliente;
	}
	public void setCPF_Cliente(String cPF_Cliente) {
		CPF_Cliente = cPF_Cliente;
	}
	public USUARIO getUsuario() {
		return usuario;
	}
	public void setUsuario(USUARIO usuario) {
		this.usuario = usuario;
	}
	
	
}
