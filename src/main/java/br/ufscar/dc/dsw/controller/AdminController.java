package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.domain.USUARIO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.dao.*;
import br.ufscar.dc.dsw.dao.UsuarioDAO.Papel;
import br.ufscar.dc.dsw.domain.*;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/admin/*")
public class AdminController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UsuarioDAO dao;
    private ClienteDAO cldao;
    private ProfissionalDAO prdao;
    private List<String> users;
    
    
    
    @Override
    public void init() {
        dao = new UsuarioDAO();
        cldao = new ClienteDAO();
        prdao = new ProfissionalDAO();
        users = new ArrayList<String>();
        users.add("Clientes");
        users.add("Profissionais");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setAttribute("tipousersel", request.getAttribute("tipousuario"));
    	doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("== [LOG]: AdminController");
    	
    	USUARIO usuario = (USUARIO) request.getSession().getAttribute("usuarioLogado");
    	Erro erros = new Erro();
		UsuarioDAO dao = new UsuarioDAO();
    	
    	if (usuario == null) {
    		RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
			dispatcher.forward(request, response);
    	} else if (Papel.Admin == dao.getRole(usuario)) {
            String action = request.getPathInfo();
            if (action == null) {
                action = "";
            }

            try {
                switch (action) {
                    case "/editar":
                        lista(request, response); // TODO: mudar
                        break;
                    case "/deletar":
                    	lista(request, response);
                    	break;
                    default:
                        lista(request, response);
                }
            } catch (RuntimeException | IOException | ServletException e) {
                throw new ServletException(e);
            }
            	
    	} else {
    		erros.add("Acesso não autorizado!");
    		erros.add("Apenas Papel [ADMIN] tem acesso a essa página");
    		request.setAttribute("mensagens", erros);
    		RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
    		rd.forward(request, response);
    	}
    }
    
    private void lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	List<CLIENTE> listaClientes = cldao.getAll();
    	List<PROFISSIONAL> listaProfissionais = prdao.getAll();
        List<USUARIO> listaUsuarios = dao.getAll();
        request.setAttribute("listusers", users);
        
        
        String result = "Clientes";
        if(request.getParameter("tipousuario")!= null) {
        	result = request.getParameter("tipousuario");
        }else {
        	//pra ficar os clientes selecionados na primeira vez
        	request.setAttribute("tipousersel", "Clientes");
        	request.setAttribute("tipousuario", "Clientes");
        }
        
        
        if(new String(result).equals("Clientes")){
        	request.setAttribute("listaUsuarios", listaClientes);}
        else  {
        	request.setAttribute("listaUsuarios", listaProfissionais);
        }
        
     	
        
        
        //request.setAttribute("listaUsuarios", listaClientes);
        /*
        request.setAttribute("listaUsuarios", listaUsuarios);
        request.setAttribute("listaClientes", listaClientes);
        request.setAttribute("listaProfissionais", listaProfissionais);*/
       
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/conta.jsp");
        dispatcher.forward(request, response);
    }
}
