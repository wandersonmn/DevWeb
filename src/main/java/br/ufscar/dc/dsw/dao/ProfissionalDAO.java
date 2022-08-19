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

import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Profissional;

public class ProfissionalDAO extends GenericDAO {

    public void insert(Profissional profissional) {

        UsuarioDAO udao = new UsuarioDAO();
        udao.insert(profissional);

        String sql = "INSERT INTO Profissional (CPF_Profissional, area_atuacao, especialidade, qualificacoes) VALUES (?, ?, ?, ?)";

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

    public List<Profissional> getAll() {

        List<Profissional> listaProfissionais = new ArrayList<>();

        String sql = "SELECT * from Profissional p, Usuario u where p.CPF_Profissional = u.CPF order by p.CPF_Profissional";

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
                Profissional profissional = new Profissional(cpf, nome, senha, email, sexo, telefone, data_nascimento, area_atuacao, especialidade, qualificacoes);
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

    public void delete(Profissional profissional) {
        String sql = "DELETE FROM Profissional where CPF_Profissional = ?";

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

    public void update(Profissional profissional) {
        String sql = "UPDATE Profissional SET CPF_Profissional = ?, area_atuacao = ?, especialidade = ?, qualificacoes = ?";
        sql += "WHERE CPF_Profissional = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, profissional.getCPF());
            statement.setString(2, profissional.getArea_atuacao());
            statement.setString(3, profissional.getEspecialidade());
            statement.setString(4, profissional.getQualificacoes());
            statement.setString(5, profissional.getCPF());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean valid(String CPF) {
        String sql = "SELECT * FROM Profissional u where u.CPF_Profissional = ?";

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

    public Profissional get(String CPF_Profissional) {
        System.out.println("ProfissionalDAO - Buscando profissional de cpf " + CPF_Profissional);
        Profissional profissional = null;
        String sql = "SELECT * from Profissional p inner join Usuario u on (p.CPF_Profissional = u.CPF) where p.CPF_Profissional = ?";
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
                Usuario usuario = new UsuarioDAO().get(cpf);

                profissional = new Profissional(usuario, area_atuacao, especialidade, qualificacoes);
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

