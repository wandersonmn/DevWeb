package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.domain.CLIENTE;
import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.domain.PROFISSIONAL;
import br.ufscar.dc.dsw.dao.AgendamentoDAO;
import br.ufscar.dc.dsw.domain.AGENDAMENTO;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/index.jsp")
public class IndexController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private ProfissionalDAO pDao;
    private AgendamentoDAO aDao;

    @Override
    public void init() {
        pDao = new ProfissionalDAO();
        aDao = new AgendamentoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        
		System.out.println("== [LOG]: IndexController");

        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        // try {
        //     switch (action) {
		// 		// ! Não sei se é o ideal colocar o agendamento e a conta aqui
        //         case "/agendar":
        //             agendar(request, response); // TODO: mudar
        //             break;
        //         default:
        //             lista(request, response);
        //     } catch (RuntimeException | IOException | ServletException e) {
        //         throw new ServletException(e);
        //     }
        
        // }
        try {
            switch (action) {
                default:
                    lista(request, response);
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<PROFISSIONAL> listaProfissionais = pDao.getAll();
        request.setAttribute("listaProfissionais", listaProfissionais);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/inicial.jsp");
        dispatcher.forward(request, response);
    }

    // private void agendar(HttpServletRequest request, HttpServletResponse response) 
    //         throws ServletException, IOException {
    //     String cpf_profissional = request.getParameter("CPF_Profissional");
    //     PROFISSIONAL profissional = pDao.get(cpf_profissional);
    //     request.setAttribute("profissional", profissional);
    //     request.setAttribute("agendamentos", aDao.getAllProfissional(profissional.getCPF()));
    //     RequestDispatcher dispatcher = request.getRequestDispatcher("/profissional/formulario.jsp");
    //     dispatcher.forward(request, response);
    // }
    
    // private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
    //         throws ServletException, IOException {
    //     request.setAttribute("editoras", getEditoras());
    //     RequestDispatcher dispatcher = request.getRequestDispatcher("/livro/formulario.jsp");
    //     dispatcher.forward(request, response);
    // }

    // private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
    //         throws ServletException, IOException {
    //     Long id = Long.parseLong(request.getParameter("id"));
    //     Livro livro = dao.get(id);
    //     request.setAttribute("livro", livro);
    //     request.setAttribute("editoras", getEditoras());
    //     RequestDispatcher dispatcher = request.getRequestDispatcher("/livro/formulario.jsp");
    //     dispatcher.forward(request, response);
    // }

    // private void insere(HttpServletRequest request, HttpServletResponse response)
    //         throws ServletException, IOException {
    //     request.setCharacterEncoding("UTF-8");
        
    //     String titulo = request.getParameter("titulo");
    //     String autor = request.getParameter("autor");
    //     Integer ano = Integer.parseInt(request.getParameter("ano"));
    //     Float preco = Float.parseFloat(request.getParameter("preco"));
        
    //     Long editoraID = Long.parseLong(request.getParameter("editora"));
    //     Editora editora = new EditoraDAO().get(editoraID);
        
    //     Livro livro = new Livro(titulo, autor, ano, preco, editora);
    //     dao.insert(livro);
    //     response.sendRedirect("lista");
    // }

    // private void atualize(HttpServletRequest request, HttpServletResponse response)
    //         throws ServletException, IOException {

    //     request.setCharacterEncoding("UTF-8");
    //     Long id = Long.parseLong(request.getParameter("id"));
    //     String titulo = request.getParameter("titulo");
    //     String autor = request.getParameter("autor");
    //     Integer ano = Integer.parseInt(request.getParameter("ano"));
    //     Float preco = Float.parseFloat(request.getParameter("preco"));
        
    //     Long editoraID = Long.parseLong(request.getParameter("editora"));
    //     Editora editora = new EditoraDAO().get(editoraID);
        
    //     Livro livro = new Livro(id, titulo, autor, ano, preco, editora);
    //     dao.update(livro);
    //     response.sendRedirect("lista");
    // }

    // private void remove(HttpServletRequest request, HttpServletResponse response)
    //         throws IOException {
    //     Long id = Long.parseLong(request.getParameter("id"));

    //     Livro livro = new Livro(id);
    //     dao.delete(livro);
    //     response.sendRedirect("lista");
    // }
}
