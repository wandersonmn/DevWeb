package br.ufscar.dc.dsw.domain;

public class CLIENTE extends USUARIO{	
	
	public CLIENTE(String cpf,String nome, String senha, String email, String sexo, String telefone, String data_nascimento) {
		super(cpf, email, senha, nome, sexo, telefone, data_nascimento);
    }

}
