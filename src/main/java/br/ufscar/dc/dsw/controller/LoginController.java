package br.ufscar.dc.dsw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO.Papel;
import br.ufscar.dc.dsw.domain.PROFISSIONAL;
import br.ufscar.dc.dsw.domain.USUARIO;
import br.ufscar.dc.dsw.domain.ADMIN;
import br.ufscar.dc.dsw.domain.CLIENTE;
import br.ufscar.dc.dsw.util.Erro;

/*
 * Servlet de Login
 * 
 * Vai utilizar o atributo "usuarioLogado" e "direcao"
 * Se já autenticado, redireciona para "direcao"
 * Se não, apresenta tela de login, e redireciona para "direcao" ou página de erro
 */
@WebServlet(name = "Index", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Erro erros = new Erro();

		System.out.println("== [LOG]: LoginController");

		if (request.getParameter("bOK") != null) {
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			if (login == null || login.isEmpty()) {
				erros.add("Login não informado!");
			}
			if (senha == null || senha.isEmpty()) {
				erros.add("Senha não informada!");
			}
			if (!erros.isExisteErros()) {
				UsuarioDAO dao = new UsuarioDAO();
				USUARIO usuario = dao.getByMail(login); // ! Login == Email do usuário
				if (usuario != null) {
					if (usuario.getSenha().equals(senha)) {
						request.getSession().setAttribute("usuarioLogado", usuario);
						Papel p = dao.getRole(usuario);
						System.out.println("==== Papel: " + p + " Email: " + usuario.getEmail());
						if (p == Papel.Admin) {
							response.sendRedirect("admin/");
						} else if (p == Papel.Profissional){
							response.sendRedirect("profissional/");
						} else if (p == Papel.Cliente){
							response.sendRedirect("cliente/");
						} else {
							erros.add("Usuário não possui papel!");
						}
						return;
					} else {
						erros.add("Senha inválida!");
					}
				} else {
					erros.add("Usuário não encontrado!");
				}
			}
		}
		request.getSession().invalidate();

		request.setAttribute("mensagens", erros);

		String URL = "/login.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(URL);
		rd.forward(request, response);
	}
}