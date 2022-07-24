package br.ufscar.dc.dsw.domain;

public class AGENDAMENTO {
	private CLIENTE cliente;
	private PROFISSIONAL profissional;
	private String status;
	private String data; /*pesquisar tipo adequado*/
	private String hora; /*pesquisar tipo adequado*/
	
	
	public CLIENTE getCliente() {
		return cliente;
	}
	public void setCliente(CLIENTE cliente) {
		this.cliente = cliente;
	}
	public PROFISSIONAL getProfissional() {
		return profissional;
	}
	public void setProfissional(PROFISSIONAL profissional) {
		this.profissional = profissional;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
}
