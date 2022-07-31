package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.ADMIN;

public class AdminDAO extends GenericDAO {

    public void insert(ADMIN admin) {
        UsuarioDAO dao = new UsuarioDAO();
        dao.insert(admin);

        String sql = "INSERT INTO ADMIN (CPF_Cliente) VALUES (?)";

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

    public List<ADMIN> getAll() {

        List<ADMIN> listaClientes = new ArrayList<>();

        String sql = "SELECT * from ADMIN p, USUARIO u where p.CPF_Adm = u.CPF order by p.CPF_Adm";

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
                ADMIN admin = new ADMIN(cpf, email, senha, nome, sexo, telefone, data_nascimento);
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

    public void delete(ADMIN admin) {
        String sql = "DELETE FROM ADMIN where CPF_Adm = ?";

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

    public void update(ADMIN admin) {
        String sql = "UPDATE ADMIN SET CPF = ?, email = ?, senha = ?, "
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

    public ADMIN get(String CPF) {
        ADMIN admin = null;

        String sql = "SELECT * from ADMIN u where u.CPF = ?";

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
                
                admin = new ADMIN(cpf, email, senha, nome, sexo, telefone, data_nascimento);
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
