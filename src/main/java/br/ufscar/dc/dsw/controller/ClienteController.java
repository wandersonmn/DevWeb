package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.UsuarioDAO.Papel;
import br.ufscar.dc.dsw.domain.USUARIO;
import br.ufscar.dc.dsw.domain.*;
import br.ufscar.dc.dsw.dao.*;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/cliente/*")
public class ClienteController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private AgendamentoDAO Agdao;
    
    @Override
    public void init() {
        Agdao = new AgendamentoDAO();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("== [LOG]: ClienteController");

    	USUARIO usuario = (USUARIO) request.getSession().getAttribute("usuarioLogado");
    	Erro erros = new Erro();
		UsuarioDAO dao = new UsuarioDAO();
    	
    	if (usuario == null) {
    		RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
			dispatcher.forward(request, response);
    	} else if (Papel.Cliente == dao.getRole(usuario)) {
            // Confirmado que o usuário está logado
            String action = request.getPathInfo();
            if (action == null) {
                action = "";
            }

            try {
                switch (action) {
                    case "/agendar":
                        agendar(request, response); // TODO: mudar
                        break;
                    default:
                        lista(request, response);
                }
            } catch (RuntimeException | IOException | ServletException e) {
                throw new ServletException(e);
            }
    	} else {
    		erros.add("Acesso não autorizado!");
    		erros.add("Apenas Papel [CLIENT] tem acesso a essa página");
    		request.setAttribute("mensagens", erros);
    		RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
    		rd.forward(request, response);
    	}    	
    }

    private void lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	USUARIO usuario = (USUARIO) request.getSession().getAttribute("usuarioLogado");
    	String cpf = usuario.getCPF();
        List<AGENDAMENTO> listaAgendamentos = Agdao.getAllCliente(cpf);
        request.setAttribute("listaAgendamentos", listaAgendamentos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/cliente/conta.jsp");
        dispatcher.forward(request, response);
    }

    private void agendar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cpf_profissional = (String)request.getAttribute("cpf");
        String nome_profissional = (String)request.getAttribute("nome");
        List<AGENDAMENTO> listaHorariosDisponiveis = Agdao.getDisp(cpf_profissional);
        request.setAttribute("listaHorariosDisponiveis", listaHorariosDisponiveis);
        request.setAttribute("nomeProfissional", nome_profissional);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/cliente/agendar.jsp");
        dispatcher.forward(request, response);
    }
}