package br.ufscar.dc.dsw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Agendamento")
public class Agendamento {

	@OneToOne
	private Cliente cliente;

	@OneToOne
	private Profissional profissional;

	@Column(nullable = true, unique = false, length = 40)
	private String status;

	// temporalValues.setUtilDate(
	// 	new SimpleDateFormat("yyyy-MM-dd").parse("2017-11-15"));
	// temporalValues.setUtilTime(
	// 	new SimpleDateFormat("HH:mm:ss").parse("15:30:14"));

	@Column(nullable = false, unique = false, length = 40)
	@Temporal(TemporalType.DATE)
	private java.util.Date data; /*pesquisar tipo adequado*/

	@Column(nullable = false, unique = false, length = 40)
	@Temporal(TemporalType.TIME)
	private java.util.Date hora; /*pesquisar tipo adequado*/
	
	public Agendamento (Cliente cliente, 
						Profissional profissional, 
						String status, 
						String data, 
						String hora
						){
		this.cliente = cliente;
		this.profissional = profissional;
		this.status = status;
		this.data = data;
		this.hora = hora;
	}

	public Agendamento (Cliente c, Profissional p, String data, String hora){
		this(c, p, "ativo", data, hora);
	}

	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Profissional getProfissional() {
		return profissional;
	}
	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public java.util.Date getData() {
		return data;
	}
	public void setData(java.util.Date data) {
		this.data = data;
	}
	public java.util.Date getHora() {
		return hora;
	}
	public void setHora(java.util.Date hora) {
		this.hora = hora;
	}
}
