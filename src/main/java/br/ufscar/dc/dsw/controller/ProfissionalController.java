package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.AgendamentoDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO.Papel;
import br.ufscar.dc.dsw.domain.AGENDAMENTO;
import br.ufscar.dc.dsw.domain.*;
import br.ufscar.dc.dsw.domain.USUARIO;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/profissional/*")
public class ProfissionalController extends HttpServlet {

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
    	
    	USUARIO usuario = (USUARIO) request.getSession().getAttribute("usuarioLogado");
    	Erro erros = new Erro();
		UsuarioDAO dao = new UsuarioDAO();
    	
		System.out.println("== [LOG]: ProfissionalController");

    	if (usuario == null) {
    		RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
			dispatcher.forward(request, response);
    	} else if (Papel.Profissional == dao.getRole(usuario)) {
    		lista(request, response);
    	} else {
    		erros.add("Acesso não autorizado!");
    		erros.add("Apenas Papel [PRO] tem acesso a essa página");
    		request.setAttribute("mensagens", erros);
    		RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
    		rd.forward(request, response);
    	}    	
    }
    	private void lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	USUARIO usuario = (USUARIO) request.getSession().getAttribute("usuarioLogado");
    	String cpf = usuario.getCPF();
		System.out.println(cpf);
        List<AGENDAMENTO> listaAgendamentos = Agdao.getAllProfissional(cpf);
        request.setAttribute("listaAgendamentos", listaAgendamentos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/profissional/conta.jsp");
        dispatcher.forward(request, response);
    }
}