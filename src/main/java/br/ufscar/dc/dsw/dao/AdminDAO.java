package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Admin;

public class AdminDAO extends GenericDAO {

    public void insert(Admin admin) {
        UsuarioDAO dao = new UsuarioDAO();
        dao.insert(admin);

        String sql = "INSERT INTO Admin (CPF_Cliente) VALUES (?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, admin.getCPF());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Admin> getAll() {

        List<Admin> listaClientes = new ArrayList<>();

        String sql = "SELECT * from Admin p, Usuario u where p.CPF_Adm = u.CPF order by p.CPF_Adm";

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
                Admin admin = new Admin(cpf, email, senha, nome, sexo, telefone, data_nascimento);
                listaClientes.add(admin);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaClientes;
    }

    public void delete(Admin admin) {
        String sql = "DELETE FROM Admin where CPF_Adm = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, admin.getCPF());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Admin admin) {
        String sql = "UPDATE Admin SET CPF = ?, email = ?, senha = ?, "
        		+ "nome = ?, sexo = ?, telefone = ?, data_nascimento = ?";
        sql += "WHERE CPF = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, admin.getCPF());
            statement.setString(2, admin.getEmail());
            statement.setString(3, admin.getSenha());
            statement.setString(4, admin.getNome());
            statement.setString(5, admin.getSexo());
            statement.setString(6, admin.getTelefone());
            statement.setString(7, admin.getData_nascimento());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public boolean valid(String CPF) {
        String sql = "SELECT * FROM ADM u where u.CPF_Adm = ?";

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

    public Admin get(String CPF) {
        Admin admin = null;

        String sql = "SELECT * from Admin u where u.CPF = ?";

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
                
                admin = new Admin(cpf, email, senha, nome, sexo, telefone, data_nascimento);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return admin;
    }
}
