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

        UsuarioDAO.insert(profissional);

        String sql = "INSERT INTO PROFISSIONAL (CPF_Profissional, area_atuacao, especialidade, qualificacoes) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = getConnection();
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
            Connection conn = getConnection();
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
            Connection conn = getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, profissional.getCPF());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(PROFISSIONAL profissional) {
        String sql = "UPDATE PROFISSIONAL SET CPF_Profissional = ?, area_atuacao = ?, especialidade = ?, qualificacoes = ?";
        sql += "WHERE CPF_Profissional = ?";

        try {
            Connection conn = getConnection();
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

    public PROFISSIONAL get(String CPF_Profissional) {
        PROFISSIONAL profissional = null;

        String sql = "SELECT * from PROFISSIONAL p inner join USUARIO u on (p.CPF_Profissional = u.CPF) p where p.CPF_Profissional = ?";

        try {
            Connection conn = getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, CPF_Profissional);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                
                String area_atuacao = resultSet.getString("area_atuacao");
                String especialidade = resultSet.getString("especialidade");
                String qualificacoes = resultSet.getString("qualificacoes");
                

                String cpf = resultSet.getString("cpf");
                USUARIO usuario = new UsuarioDAO().get(cpf);

                profissional = new PROFISSIONAL(usuario, area_atuacao, especialidade, qualificacoes);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profissional;
    }
}

