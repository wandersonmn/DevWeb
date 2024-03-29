package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.AGENDAMENTO;

public class AgendamentoDAO extends GenericDAO {

    // NOTA! Insert insere apenas agendamentos vazios, deve ser usado pelo profissional por exemplo
    // Use agendar para atualizar um agendamento existente para utilizar o CPF do cliente
    public void insert(AGENDAMENTO agendamento) {

        String sql = "INSERT INTO AGENDAMENTO (CPF_Cliente, CPF_Profissional, data, hora) VALUES ('VAZIO', ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            System.out.println(agendamento.getProfissional().getCPF() + " " + agendamento.getData() + " " + agendamento.getHora());
            statement.setString(1, agendamento.getProfissional().getCPF());
            statement.setString(2, agendamento.getData());
            statement.setString(3, agendamento.getHora());
       
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void agendar(AGENDAMENTO agendamento){
        // Colocar o CPF_Cliente de agendamento em agendamento com CPF_Cliente = "VAZIO"
        String sql = "UPDATE AGENDAMENTO SET CPF_Cliente = ? WHERE CPF_Cliente = 'VAZIO' AND CPF_Profissional = ? AND data = ? AND hora = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, agendamento.getCliente().getCPF());
            statement.setString(2, agendamento.getProfissional().getCPF());
            statement.setString(3, agendamento.getData());
            statement.setString(4, agendamento.getHora());
       
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cancelar(AGENDAMENTO agendamento){
        // Setar como vazio o CPF_Cliente de um agendamento específico
        String sql = "UPDATE AGENDAMENTO SET CPF_Cliente = 'VAZIO' WHERE CPF_Profissional = ? AND data = ? AND hora = ?";
        System.out.println("aDAO: CPF_Profissional = " + agendamento.getProfissional().getCPF());
        System.out.println("aDAO: data = " + agendamento.getData());
        System.out.println("aDAO: hora = " + agendamento.getHora());
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, agendamento.getProfissional().getCPF());
            statement.setString(2, agendamento.getData());
            statement.setString(3, agendamento.getHora());
       
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Por profissional
    public List<AGENDAMENTO> getDisp(String CPF_Profissional) {
        List<AGENDAMENTO> listaAgendamentos = new ArrayList<>();

        String sql = "SELECT * from AGENDAMENTO WHERE CPF_Cliente = 'VAZIO' AND CPF_Profissional = '" + CPF_Profissional + "' ORDER BY data, hora";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            // statement.setString(1, CPF_Profissional);

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String CPF_Cliente = resultSet.getString("CPF_Cliente");
                // String CPF_Profissional = resultSet.getString("CPF_Profissional");
                String data = resultSet.getString("data");
                String hora = resultSet.getString("hora");
                String status = resultSet.getString("status");

                ClienteDAO cdao = new ClienteDAO();
                ProfissionalDAO fdao = new ProfissionalDAO();

                AGENDAMENTO agendamento = new AGENDAMENTO(cdao.get(CPF_Cliente), fdao.get(CPF_Profissional),status, data, hora);
                listaAgendamentos.add(agendamento);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaAgendamentos;    
    }

    public List<AGENDAMENTO> getOcup(String CPF_Profissional) {
        List<AGENDAMENTO> listaAgendamentos = new ArrayList<>();

        String sql = "SELECT * from AGENDAMENTO WHERE CPF_Cliente != 'VAZIO' AND CPF_Profissional = '" + CPF_Profissional + "' ORDER BY data, hora";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            // statement.setString(1, CPF_Profissional);

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String CPF_Cliente = resultSet.getString("CPF_Cliente");
                // String CPF_Profissional = resultSet.getString("CPF_Profissional");
                String data = resultSet.getString("data");
                String hora = resultSet.getString("hora");
                String status = resultSet.getString("status");

                ClienteDAO cdao = new ClienteDAO();
                ProfissionalDAO fdao = new ProfissionalDAO();

                AGENDAMENTO agendamento = new AGENDAMENTO(cdao.get(CPF_Cliente), fdao.get(CPF_Profissional),status, data, hora);
                listaAgendamentos.add(agendamento);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaAgendamentos;    
    }

    public List<AGENDAMENTO> getAll() {

        List<AGENDAMENTO> listaAgendamentos = new ArrayList<>();

        String sql = "SELECT * from AGENDAMENTO order by data, hora";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String CPF_Cliente = resultSet.getString("CPF_Cliente");
                String CPF_Profissional = resultSet.getString("CPF_Profissional");
                String data = resultSet.getString("data");
                String hora = resultSet.getString("hora");
                String status = resultSet.getString("status");

                ClienteDAO cdao = new ClienteDAO();
                ProfissionalDAO fdao = new ProfissionalDAO();

                AGENDAMENTO agendamento = new AGENDAMENTO(cdao.get(CPF_Cliente), fdao.get(CPF_Profissional),status, data, hora);
                listaAgendamentos.add(agendamento);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaAgendamentos;
    }

    public List<AGENDAMENTO> getAllProfissional(String CPF_Profissional) {

        List<AGENDAMENTO> listaAgendamentos = new ArrayList<>();

        String sql = "SELECT * from AGENDAMENTO WHERE CPF_Profissional = ? ORDER BY data, hora";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, CPF_Profissional);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String CPF_Cliente = resultSet.getString("CPF_Cliente");
                CPF_Profissional = resultSet.getString("CPF_Profissional");
                String data = resultSet.getString("data");
                String hora = resultSet.getString("hora");
                String status = resultSet.getString("status");

                ClienteDAO cdao = new ClienteDAO();
                ProfissionalDAO fdao = new ProfissionalDAO();

                AGENDAMENTO agendamento = new AGENDAMENTO(cdao.get(CPF_Cliente), fdao.get(CPF_Profissional),status, data, hora);
                listaAgendamentos.add(agendamento);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaAgendamentos;
    }

    public List<AGENDAMENTO> getAllCliente(String CPF_Cliente) {

        List<AGENDAMENTO> listaAgendamentos = new ArrayList<>();

        String sql = "SELECT * from AGENDAMENTO WHERE CPF_Cliente = ? ORDER BY data, hora";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, CPF_Cliente);
            statement.executeQuery();
            
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CPF_Cliente = resultSet.getString("CPF_Cliente");
                String CPF_Profissional = resultSet.getString("CPF_Profissional");
                String data = resultSet.getString("data");
                String hora = resultSet.getString("hora");
                String status = resultSet.getString("status");
                
                ClienteDAO cdao = new ClienteDAO();
                ProfissionalDAO fdao = new ProfissionalDAO();

                AGENDAMENTO agendamento = new AGENDAMENTO(cdao.get(CPF_Cliente), fdao.get(CPF_Profissional),status,  data, hora);
                listaAgendamentos.add(agendamento);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaAgendamentos;
    }

    public void deleteProfissional(AGENDAMENTO agendamento) {
        String sql = "DELETE FROM AGENDAMENTO where CPF_Profissional = ? AND data = ? AND hora = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, agendamento.getProfissional().getCPF());
            statement.setString(2, agendamento.getData());
            statement.setString(3, agendamento.getHora());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(AGENDAMENTO agendamento) {
        String sql = "UPDATE AGENDAMENTO SET CPF_Cliente = ?, CPF_Profissional = ?, data = ?, hora = ?, status = ?";
        sql += "WHERE CPF_Profissional = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, agendamento.getCliente().getCPF());
            statement.setString(2, agendamento.getProfissional().getCPF());
            statement.setString(3, agendamento.getData());
            statement.setString(4, agendamento.getHora());
            statement.setString(5, agendamento.getStatus());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

