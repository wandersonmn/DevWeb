package br.ufscar.dc.dsw.domain;

public class PROFISSIONAL extends USUARIO{
	private String area_atuacao;
	private String especialidade;
	private String qualificacoes;
	
	
	public PROFISSIONAL(String cpf,String nome, String senha, String email, String sexo, String telefone, String data_nascimento, String area_atuacao, String especialidade, String qualificacoes) {
		super(cpf, email, senha, nome, sexo, telefone, data_nascimento);
		this.area_atuacao = area_atuacao;
        this.especialidade = especialidade;
        this.qualificacoes = qualificacoes;   
    }
	
	public PROFISSIONAL(USUARIO u, String area_atuacao, String especialidade, String qualificacoes) {
		super(u.getCPF(),u.getEmail() , u.getSenha(), u.getNome(), u.getSexo(),u.getTelefone(), u.getData_nascimento());
		this.area_atuacao = area_atuacao;
        this.especialidade = especialidade;
        this.qualificacoes = qualificacoes; 
		
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

	
}
