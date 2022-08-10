package br.ufscar.dc.dsw.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.domain.USUARIO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO.Papel;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/redirect/*")
public class Redirect extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("== [LOG]: RedirectController");
    	
    	USUARIO usuario = (USUARIO) request.getSession().getAttribute("usuarioLogado");
    	Erro erros = new Erro();
		UsuarioDAO dao = new UsuarioDAO();
    	
    	if (usuario == null) {
    		response.sendRedirect("login");
    	} else if (Papel.Admin == dao.getRole(usuario)) {
    		response.sendRedirect("admin/");
    	} else if (Papel.Profissional == dao.getRole(usuario)) {
    		response.sendRedirect("profissional/");
    	} else if (Papel.Cliente == dao.getRole(usuario)) {
    		response.sendRedirect("cliente/");
    	}  else {
    		erros.add("Usuário não possui papel!");
    		request.setAttribute("mensagens", erros);
            response.sendRedirect("/noAuth.jsp");
    	}
    }
}
