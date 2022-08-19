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

import br.ufscar.dc.dsw.dao.*;
import br.ufscar.dc.dsw.dao.UsuarioDAO.Papel;
import br.ufscar.dc.dsw.domain.*;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/admin/*")
public class AdminController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ClienteDAO cldao;
    private ProfissionalDAO prdao;
    private List<String> users;
    
    
    
    @Override
    public void init() {
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
                    case "/edicao":
                    	apresentaFormEdicao(request, response);
                        break;
                    case "/atualizacao":
                    	atualize(request, response);
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
        request.setAttribute("listusers", users);
        
        String result = "Clientes";
        if(request.getParameter("tipousuario")!= null) {
        	result = request.getParameter("tipousuario");
        }
        
        
        if(new String(result).equals("Clientes")){
        	request.setAttribute("listaUsuarios", listaClientes);}
        else  {
        	request.setAttribute("listaUsuarios", listaProfissionais);
        }
       
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/conta.jsp");
        dispatcher.forward(request, response);
    }
    /*
    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("editoras", getEditoras());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/livro/formulario.jsp");
        dispatcher.forward(request, response);
    }
	*/
    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cpf = request.getParameter("cpf");
        PROFISSIONAL profissional;
        CLIENTE cliente;
        
        String result = "Clientes";
        if(!request.getParameter("tipousuario").equals("")) {
        	result = request.getParameter("tipousuario");
        }
        
        
        System.out.println(request.getParameter("tipousuario"));
        
        if(new String(result).equals("Clientes")){
        	cliente = cldao.get(cpf);
        	request.setAttribute("usuarios", cliente);}
        else  {
        	profissional = prdao.get(cpf);
        	request.setAttribute("usuarios", profissional);
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/formulario.jsp");
        dispatcher.forward(request, response);
    }
    /*
    private void insere(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        Integer ano = Integer.parseInt(request.getParameter("ano"));
        Float preco = Float.parseFloat(request.getParameter("preco"));
        
        Long editoraID = Long.parseLong(request.getParameter("editora"));
        Editora editora = new EditoraDAO().get(editoraID);
        
        Livro livro = new Livro(titulo, autor, ano, preco, editora);
        dao.insert(livro);
        response.sendRedirect("lista");
    }
	*/
    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	System.out.println("LOG -- atualize()");
        request.setCharacterEncoding("UTF-8");
        String cpf = request.getParameter("cpf");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String sexo = request.getParameter("sexo");
        String telefone = request.getParameter("telefone");
        String data_nascimento = request.getParameter("data_nascimento");
        String senha = request.getParameter("senha");
        
        //profissional
        String area_atuacao = request.getParameter("area_atuacao");
        String especialidade = request.getParameter("especialidade");
        String qualificacoes = request.getParameter("qualificacoes");
        
        USUARIO user = new USUARIO(cpf, email, senha,  nome,  sexo, 
    			 telefone,  data_nascimento);
        
        System.out.printf("%s %s %s %s %s %s %s", cpf, email, senha,  nome,  sexo, 
   			 telefone,  data_nascimento);
        System.out.println(request.getParameter("tipousuario"));
        if(request.getParameter("tipousuario").equals("Profissionais")) {
        	PROFISSIONAL pro = new PROFISSIONAL(user, area_atuacao,especialidade,qualificacoes);
        	prdao.update(pro);
        }else {
        	CLIENTE cli = new CLIENTE(user);
        	cldao.update(cli);
        }
        response.sendRedirect("lista");
    }
    /*
    private void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Livro livro = new Livro(id);
        dao.delete(livro);
        response.sendRedirect("lista");
    }
    */
}
