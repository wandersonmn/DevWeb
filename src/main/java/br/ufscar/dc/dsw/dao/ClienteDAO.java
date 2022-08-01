package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.CLIENTE;

public class ClienteDAO extends GenericDAO {

    public void insert(CLIENTE cliente) {
        UsuarioDAO dao = new UsuarioDAO();
        dao.insert(cliente);

        String sql = "INSERT INTO CLIENTE (CPF_Cliente) VALUES (?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cliente.getCPF());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CLIENTE> getAll() {

        List<CLIENTE> listaClientes = new ArrayList<>();

        String sql = "SELECT * from CLIENTE p, USUARIO u where p.CPF_Cliente = u.CPF order by p.CPF_Cliente";

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
                CLIENTE cliente = new CLIENTE(cpf, email, senha, nome, sexo, telefone, data_nascimento);
                listaClientes.add(cliente);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaClientes;
    }

    public void delete(CLIENTE cliente) {
    	// deve-se deletar de usuario, no cliente sera deletado por cascade
        String sql = "DELETE FROM CLIENTE where CPF_Cliente = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cliente.getCPF());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(CLIENTE cliente) {
        String sql = "UPDATE CLIENTE SET CPF_Cliente = ?, email = ?, senha = ?, "
        		+ "nome = ?, sexo = ?, telefone = ?, data_nascimento = ?";
        sql += "WHERE CPF_Cliente = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cliente.getCPF());
            statement.setString(2, cliente.getEmail());
            statement.setString(3, cliente.getSenha());
            statement.setString(4, cliente.getNome());
            statement.setString(5, cliente.getSexo());
            statement.setString(6, cliente.getTelefone());
            statement.setString(7, cliente.getData_nascimento());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean valid(String CPF) {
        String sql = "SELECT * FROM CLIENTE u where u.CPF_Cliente = ?";

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

    public CLIENTE get(String CPF) {
        CLIENTE cliente = null;

        String sql = "SELECT * from CLIENTE c,USUARIO u where CPF_Cliente = ? and c.CPF_Cliente = u.CPF";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, CPF);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String cpf = resultSet.getString("CPF_Cliente");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String sexo = resultSet.getString("sexo");
                String telefone = resultSet.getString("telefone");
                String data_nascimento = resultSet.getString("data_nascimento");
                
                cliente = new CLIENTE(cpf, email, senha, nome, sexo, telefone, data_nascimento);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }
}
