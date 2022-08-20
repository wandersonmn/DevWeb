package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.USUARIO;

public class UsuarioDAO extends GenericDAO {
    public enum Papel {
        Cliente, Admin, Profissional, Nenhum
    }

    public void insert(USUARIO usuario) {

        String sql = "INSERT INTO USUARIO (CPF, email, senha, nome, sexo, "
        		+ "telefone, data_nascimento) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, usuario.getCPF());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getSenha());
            statement.setString(4, usuario.getNome());
            statement.setString(5, usuario.getSexo());
            statement.setString(6, usuario.getTelefone());
            statement.setString(7, usuario.getData_nascimento());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Papel getRole(USUARIO usuario) {
        // Verifica qual o papel específico do usuário
        ProfissionalDAO proDAO = new ProfissionalDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        AdminDAO admDAO = new AdminDAO();

        String CPF = usuario.getCPF();
        if (proDAO.valid(CPF)){
            return Papel.Profissional;
        } else if (clienteDAO.valid(CPF)){
            return Papel.Cliente;
        } else if (admDAO.valid(CPF)){
            return Papel.Admin;
        } else {
            return Papel.Nenhum;
        }
    }

    public List<USUARIO> getAll() {

        List<USUARIO> listaUsuarios = new ArrayList<>();

        String sql = "SELECT * from USUARIO u order by u.CPF";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String cpf = resultSet.getString("CPF");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String sexo = resultSet.getString("sexo");
                String telefone = resultSet.getString("telefone");
                String data_nascimento = resultSet.getString("data_nascimento");
                USUARIO usuario =  new USUARIO(cpf, email, senha, nome, sexo, telefone, data_nascimento);
               
                listaUsuarios.add(usuario);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaUsuarios;
    }

    public void delete(String cpf) {
        String sql = "DELETE FROM USUARIO where cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cpf);
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(USUARIO usuario) {
        String sql = "UPDATE USUARIO SET cpf = ?, email = ?, senha = ?, "
        		+ "nome = ?, sexo = ?, telefone = ?, data_nascimento = ?";
        sql += "WHERE cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setString(1, usuario.getCPF());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getSenha());
            statement.setString(4, usuario.getNome());
            statement.setString(5, usuario.getSexo());
            statement.setString(6, usuario.getTelefone());
            statement.setString(7, usuario.getData_nascimento());
            statement.setString(8, usuario.getCPF());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public USUARIO get(String CPF) {
        USUARIO usuario = null;

        String sql = "SELECT * from USUARIO u where u.CPF = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, CPF);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String cpf = resultSet.getString("cpf");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String sexo = resultSet.getString("sexo");
                String telefone = resultSet.getString("telefone");
                String data_nascimento = resultSet.getString("data_nascimento");
                

                usuario = new USUARIO(cpf, email, senha, nome, sexo, telefone, data_nascimento);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

    public USUARIO getByMail(String Email) {
        USUARIO usuario = null;

        String sql = "SELECT * from USUARIO u where u.email = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, Email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String cpf = resultSet.getString("cpf");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String sexo = resultSet.getString("sexo");
                String telefone = resultSet.getString("telefone");
                String data_nascimento = resultSet.getString("data_nascimento");
                

                usuario = new USUARIO(cpf, email, senha, nome, sexo, telefone, data_nascimento);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }
}
