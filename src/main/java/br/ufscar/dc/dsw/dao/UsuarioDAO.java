package br.ufscar.dc.dsw.dao;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import br.ufscar.dc.dsw.domain.Usuario;

// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Statement;
// import java.util.ArrayList;
// import java.util.List;

import br.ufscar.dc.dsw.domain.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario> {
    @Override
    public Usuario find(String CPF) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Usuario usuario = em.find(Usuario.class, id);
        
        tx.commit();
        em.close();
        return Usuario;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Usuario> findAll() {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("SELECT p FROM Pessoa p");
        List<Usuario> lista = q.getResultList();

        tx.commit();
        em.close();
        return lista;
    }

    @Override
    public void update(Usuario usuario) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.merge(usuario);

        tx.commit();
        em.close();
        return lista;
    }

    @Override
    public void delete(String cpf) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        Usuario usuario = em.getReference(Usuario.class, cpf)
        tx.begin();

        em.remove(usuario)

        tx.commit();
        em.close();
        return lista;
    }
}

// public class UsuarioDAO extends GenericDAO {
//     public enum Papel {
//         Cliente, Admin, Profissional, Nenhum
//     }

//     public void insert(Usuario usuario) {

//         String sql = "INSERT INTO Usuario (CPF, email, senha, nome, sexo, "
//         		+ "telefone, data_nascimento) VALUES (?, ?, ?, ?, ?, ?, ?)";

//         try {
//             Connection conn = this.getConnection();
//             PreparedStatement statement = conn.prepareStatement(sql);

//             statement.setString(1, usuario.getCPF());
//             statement.setString(2, usuario.getEmail());
//             statement.setString(3, usuario.getSenha());
//             statement.setString(4, usuario.getNome());
//             statement.setString(5, usuario.getSexo());
//             statement.setString(6, usuario.getTelefone());
//             statement.setString(7, usuario.getData_nascimento());
//             statement.executeUpdate();

//             statement.close();
//             conn.close();
//         } catch (SQLException e) {
//             throw new RuntimeException(e);
//         }
//     }

//     public Papel getRole(Usuario usuario) {
//         // Verifica qual o papel específico do usuário
//         ProfissionalDAO proDAO = new ProfissionalDAO();
//         ClienteDAO clienteDAO = new ClienteDAO();
//         AdminDAO admDAO = new AdminDAO();

//         String CPF = usuario.getCPF();
//         if (proDAO.valid(CPF)){
//             return Papel.Profissional;
//         } else if (clienteDAO.valid(CPF)){
//             return Papel.Cliente;
//         } else if (admDAO.valid(CPF)){
//             return Papel.Admin;
//         } else {
//             return Papel.Nenhum;
//         }
//     }

//     public List<Usuario> getAll() {

//         List<Usuario> listaUsuarios = new ArrayList<>();

//         String sql = "SELECT * from Usuario u order by u.CPF";

//         try {
//             Connection conn = this.getConnection();
//             Statement statement = conn.createStatement();

//             ResultSet resultSet = statement.executeQuery(sql);
//             while (resultSet.next()) {
//                 String cpf = resultSet.getString("CPF");
//                 String email = resultSet.getString("email");
//                 String senha = resultSet.getString("senha");
//                 String nome = resultSet.getString("nome");
//                 String sexo = resultSet.getString("sexo");
//                 String telefone = resultSet.getString("telefone");
//                 String data_nascimento = resultSet.getString("data_nascimento");
//                 Usuario usuario =  new Usuario(cpf, email, senha, nome, sexo, telefone, data_nascimento);
               
//                 listaUsuarios.add(usuario);
//             }

//             resultSet.close();
//             statement.close();
//             conn.close();
//         } catch (SQLException e) {
//             throw new RuntimeException(e);
//         }
//         return listaUsuarios;
//     }

//     public void delete(Usuario usuario) {
//         String sql = "DELETE FROM Usuario where CPF = ?";

//         try {
//             Connection conn = this.getConnection();
//             PreparedStatement statement = conn.prepareStatement(sql);

//             statement.setString(1, usuario.getCPF());
//             statement.executeUpdate();

//             statement.close();
//             conn.close();
//         } catch (SQLException e) {
//             throw new RuntimeException(e);
//         }
//     }

//     public void update(Usuario usuario) {
//         String sql = "UPDATE Usuario SET CPF = ?, email = ?, senha = ?, "
//         		+ "nome = ?, sexo = ?, telefone = ?, data_nascimento = ?";
//         sql += "WHERE CPF = ?";

//         try {
//             Connection conn = this.getConnection();
//             PreparedStatement statement = conn.prepareStatement(sql);

//             statement.setString(1, usuario.getCPF());
//             statement.setString(2, usuario.getEmail());
//             statement.setString(3, usuario.getSenha());
//             statement.setString(4, usuario.getNome());
//             statement.setString(5, usuario.getSexo());
//             statement.setString(6, usuario.getTelefone());
//             statement.setString(7, usuario.getData_nascimento());
//             statement.executeUpdate();

//             statement.close();
//             conn.close();
//         } catch (SQLException e) {
//             throw new RuntimeException(e);
//         }
//     }

//     public Usuario get(String CPF) {
//         Usuario usuario = null;

//         String sql = "SELECT * from Usuario u where u.CPF = ?";

//         try {
//             Connection conn = this.getConnection();
//             PreparedStatement statement = conn.prepareStatement(sql);

//             statement.setString(1, CPF);
//             ResultSet resultSet = statement.executeQuery();
//             if (resultSet.next()) {
//                 String cpf = resultSet.getString("cpf");
//                 String email = resultSet.getString("email");
//                 String senha = resultSet.getString("senha");
//                 String nome = resultSet.getString("nome");
//                 String sexo = resultSet.getString("sexo");
//                 String telefone = resultSet.getString("telefone");
//                 String data_nascimento = resultSet.getString("data_nascimento");
                

//                 usuario = new Usuario(cpf, email, senha, nome, sexo, telefone, data_nascimento);
//             }

//             resultSet.close();
//             statement.close();
//             conn.close();
//         } catch (SQLException e) {
//             throw new RuntimeException(e);
//         }
//         return usuario;
//     }

//     public Usuario getByMail(String Email) {
//         Usuario usuario = null;

//         String sql = "SELECT * from Usuario u where u.email = ?";

//         try {
//             Connection conn = this.getConnection();
//             PreparedStatement statement = conn.prepareStatement(sql);

//             statement.setString(1, Email);
//             ResultSet resultSet = statement.executeQuery();
//             if (resultSet.next()) {
//                 String cpf = resultSet.getString("cpf");
//                 String email = resultSet.getString("email");
//                 String senha = resultSet.getString("senha");
//                 String nome = resultSet.getString("nome");
//                 String sexo = resultSet.getString("sexo");
//                 String telefone = resultSet.getString("telefone");
//                 String data_nascimento = resultSet.getString("data_nascimento");
                

//                 usuario = new Usuario(cpf, email, senha, nome, sexo, telefone, data_nascimento);
//             }

//             resultSet.close();
//             statement.close();
//             conn.close();
//         } catch (SQLException e) {
//             throw new RuntimeException(e);
//         }
//         return usuario;
//     }
// }
