package br.ufscar.dc.dsw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

@Entity
@Table(name = "Usuario")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Usuario {
	@Id
	@Column(length = 14)
	private String CPF;

	@Column(nullable = true, unique = false, length = 40)
	private String email;

	@Column(nullable = false, unique = false, length = 40)
	private String senha;

	@Column(nullable = false, unique = false, length = 40)
	private String nome;

	@Column(nullable = false, unique = false)
	private Character sexo;

	@Column(nullable = true, unique = false, length = 15)
	private String telefone;

	@Column(nullable = ture, unique = false, length = 50)
	private String data_nascimento;
	
	
	public Usuario(String CPF, String email, String senha, String nome, String sexo, 
			String telefone, String data_nascimento) {
        this.CPF = CPF;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.sexo = sexo;
        this.telefone= telefone;
        this.data_nascimento = data_nascimento;
    }
	
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getData_nascimento() {
		return data_nascimento;
	}
	public void setData_nascimento(String data_nascimento) {
		this.data_nascimento = data_nascimento;
	}
}
