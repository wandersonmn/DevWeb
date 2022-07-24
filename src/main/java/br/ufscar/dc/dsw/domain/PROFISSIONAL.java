package br.ufscar.dc.dsw.domain;

public class PROFISSIONAL {
	private String CPF_Profissional;
	private String area_atuacao;
	private String especialidade;
	private String qualificacoes;
	private USUARIO usuario;
	
	
	public String getCPF_Profissional() {
		return CPF_Profissional;
	}
	public void setCPF_Profissional(String cPF_Profissional) {
		CPF_Profissional = cPF_Profissional;
	}
	public String getArea_atuacao() {
		return area_atuacao;
	}
	public void setArea_atuacao(String area_atuacao) {
		this.area_atuacao = area_atuacao;
	}
	public String getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	public String getQualificacoes() {
		return qualificacoes;
	}
	public void setQualificacoes(String qualificacoes) {
		this.qualificacoes = qualificacoes;
	}
	public USUARIO getUsuario() {
		return usuario;
	}
	public void setUsuario(USUARIO usuario) {
		this.usuario = usuario;
	}
	
}
