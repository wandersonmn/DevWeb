package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import br.ufscar.dc.dsw.dao.UsuarioDAO.Papel;
import br.ufscar.dc.dsw.domain.USUARIO;
import br.ufscar.dc.dsw.domain.*;
import br.ufscar.dc.dsw.dao.*;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/profissional/*")
public class ProfissionalController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
	private AgendamentoDAO Agdao;
    private ProfissionalDAO pDao;
    
    @Override
    public void init() {
        Agdao = new AgendamentoDAO();
		pDao = new ProfissionalDAO();
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
    		// Confirmado que o usuário está logado
            String action = request.getPathInfo();
            if (action == null) {
                action = "";
            }

            try {
                switch (action) {
					case "/novoHorario":
                        novoHorario(request, response);
                        break;
                    case "/cancelarHorario":
                        cancelarHorario(request, response);
                        break;
                    case "/removerHorario":
                        removerHorario(request, response);
                        break;
                    default: // /conta
                        lista(request, response);
                        break;
                }
            } catch (RuntimeException | IOException | ServletException e) {
                throw new ServletException(e);
            }
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

        List<AGENDAMENTO> listaAgendamentos = Agdao.getOcup(cpf);
		List<AGENDAMENTO> listaLivres = Agdao.getDisp(cpf);

        request.setAttribute("listaAgendamentos", listaAgendamentos);
        request.setAttribute("listaLivres", listaLivres);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/profissional/conta.jsp");
        dispatcher.forward(request, response);
    }

	private void novoHorario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String data = (String)request.getParameter("data");
		String hora = (String)request.getParameter("hora");

		// Criando horário com cliente vazio no BD
		USUARIO usuario = (USUARIO) request.getSession().getAttribute("usuarioLogado");
		PROFISSIONAL profissional = pDao.get(usuario.getCPF());
		CLIENTE cliente = new CLIENTE(); // CLIENTE VAZIO
		AGENDAMENTO agendamento = new AGENDAMENTO(cliente, profissional, data, hora);
		Agdao.insert(agendamento);

		lista(request, response);
	}

	private void cancelarHorario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String data = (String)request.getParameter("data");
		String hora = (String)request.getParameter("hora");
		
		// Cancelando consulta no BD
		USUARIO usuario = (USUARIO) request.getSession().getAttribute("usuarioLogado");
		CLIENTE cliente = new CLIENTE();
		System.out.println("ProfissionalController - Buscando profissional de cpf " + usuario.getCPF());
		PROFISSIONAL profissional = pDao.get(usuario.getCPF());
		System.out.println("ProfissionalController - Buscando profissional de cpf " + usuario.getCPF());
		AGENDAMENTO agendamento = new AGENDAMENTO(cliente, profissional, data, hora);
		Agdao.cancelar(agendamento);

		lista(request, response);
	}

	private void removerHorario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String data = (String)request.getParameter("data");
		String hora = (String)request.getParameter("hora");

		// Deletando consulta no BD
		USUARIO usuario = (USUARIO) request.getSession().getAttribute("usuarioLogado");
		CLIENTE cliente = new CLIENTE();
		PROFISSIONAL profissional = pDao.get(usuario.getCPF());
		AGENDAMENTO agendamento = new AGENDAMENTO(cliente, profissional, data, hora);
		Agdao.deleteProfissional(agendamento);

		lista(request, response);
	}
}