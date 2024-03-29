/*
 	private String area_atuacao;
	private String especialidade;
	private String qualificacoes;
*/


package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.USUARIO;
import br.ufscar.dc.dsw.domain.PROFISSIONAL;

public class ProfissionalDAO extends GenericDAO {

    public void insert(PROFISSIONAL profissional) {

        UsuarioDAO udao = new UsuarioDAO();
        udao.insert(profissional);

        String sql = "INSERT INTO PROFISSIONAL (CPF_Profissional, area_atuacao, especialidade, qualificacoes) VALUES (?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, profissional.getCPF());
            statement.setString(2, profissional.getArea_atuacao());
            statement.setString(3, profissional.getEspecialidade());
            statement.setString(4, profissional.getQualificacoes());
       
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PROFISSIONAL> getAll() {

        List<PROFISSIONAL> listaProfissionais = new ArrayList<>();

        String sql = "SELECT * from PROFISSIONAL p, USUARIO u where p.CPF_Profissional = u.CPF order by p.CPF_Profissional";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String area_atuacao = resultSet.getString("area_atuacao");
                String especialidade = resultSet.getString("especialidade");
                String qualificacoes = resultSet.getString("qualificacoes");
                String cpf = resultSet.getString("CPF");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String sexo = resultSet.getString("sexo");
                String telefone = resultSet.getString("telefone");
                String data_nascimento = resultSet.getString("data_nascimento");
                PROFISSIONAL profissional = new PROFISSIONAL(cpf, nome, senha, email, sexo, telefone, data_nascimento, area_atuacao, especialidade, qualificacoes);
                listaProfissionais.add(profissional);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaProfissionais;
    }

    public void delete(PROFISSIONAL profissional) {
        String sql = "DELETE FROM PROFISSIONAL where CPF_Profissional = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, profissional.getCPF());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(PROFISSIONAL pro) {
        //USUARIO user = new USUARIO(pro.getCPF(),pro.getEmail(),pro.getSenha(),pro.getNome(),pro.getSexo(),pro.getTelefone(),pro.getData_nascimento());
        UsuarioDAO udao = new UsuarioDAO();
        udao.update(pro);
    	String sql = "UPDATE PROFISSIONAL SET cpf_profissional = ?, area_atuacao = ?, especialidade = ?, qualificacoes = ?";
        sql += "WHERE cpf_profissional = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, pro.getCPF());
            statement.setString(2, pro.getArea_atuacao());
            statement.setString(3, pro.getEspecialidade());
            statement.setString(4, pro.getQualificacoes());
            statement.setString(5, pro.getCPF());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean valid(String CPF) {
        String sql = "SELECT * FROM PROFISSIONAL u where u.CPF_Profissional = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            // Verifica se existe um cliente com esse CPF
            statement.setString(1, CPF);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next() == false) {
                resultSet.close();
                statement.close();
                conn.close();
                
                return false;
            } else {
                resultSet.close();
                statement.close();
                conn.close();

                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public PROFISSIONAL get(String CPF_Profissional) {
        System.out.println("ProfissionalDAO - Buscando profissional de cpf " + CPF_Profissional);
        PROFISSIONAL profissional = null;
        String sql = "SELECT * from PROFISSIONAL p inner join USUARIO u on (p.CPF_Profissional = u.CPF) where p.CPF_Profissional = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, CPF_Profissional);
            System.out.println("ProfissionalDAO - Executando Query");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                
                String area_atuacao = resultSet.getString("area_atuacao");
                String especialidade = resultSet.getString("especialidade");
                String qualificacoes = resultSet.getString("qualificacoes");
                

                String cpf = resultSet.getString("cpf");
                USUARIO usuario = new UsuarioDAO().get(cpf);

                profissional = new PROFISSIONAL(usuario, area_atuacao, especialidade, qualificacoes);
            }
            System.out.println("ProfissionalDAO - Query Executado, cpf obtido = " + profissional.getCPF());

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("ProfissionalDAO - Erro na execução ");
            throw new RuntimeException(e);
        }
        return profissional;
    }
}

