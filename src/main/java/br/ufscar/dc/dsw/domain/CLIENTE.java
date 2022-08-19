package br.ufscar.dc.dsw.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Cliente")
public class Cliente extends Usuario{	
	
	public Cliente(){
		super("VAZIO", "Usuario_VAZIO", "Usuario_VAZIO", "Usuario_VAZIO", "M", "Usuario_VAZIO", "Usuario_VAZIO");
	}

	public Cliente(String cpf,
				   String email, 
				   String senha, 
				   String nome, 
				   String sexo, 
				   String telefone, 
				   String data_nascimento
	) {
		super(cpf, email, senha, nome, sexo, telefone, data_nascimento);
    }
	
	public Cliente(Usuario u) {
		super(u.getCPF(),u.getEmail() , u.getSenha(), u.getNome(), u.getSexo(),u.getTelefone(), u.getData_nascimento());
		
	}

}
