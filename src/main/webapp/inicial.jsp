<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SistAgenda</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css"/>
    </head>
    <body>
        <%
		String contextPath = request.getContextPath().replace("/", "");
	    %>
	    <header>
	      <div class="container">
	        <h3 style="color: white;">
	          Profissionais Online
	        </h3>
	
	        <div class="menu-section">
	          <nav>
	            <ul>
	              <li />
	              <li />
	              <li />
	              <li />
	              <li>
	              	<form name="submitForm" method="POST" action="/<%=contextPath%>/redirect">
                    	<input type="submit" name="btn" value="Minha Conta">
                	</form>
	              </li>
	            </ul>
	          </nav>
	        </div>
	      </div>
    	</header>
    	
    	<section class="hero">
	      <div class="container">
	        <div>
	          <h2>
	            Profissionais Disponiveis
	          </h2>
	        </div>
	      </div>
	    </section>
	  
		<section class="cards">
          <table class="tab">
            <thead>
            <tr>
              <th>Nome</th>
              <th>Área</th>
              <th>Especialidade</th>
              <th>Ações</th>
            </tr>
            </thead>
            
            <tbody>
            <c:forEach var="profissional" items="${requestScope.listaProfissionais}">
	            <tr>
	              <td>&nbsp;${profissional.getNome()}</td>
	              <td>&nbsp;${profissional.getArea_atuacao()}</td>
	              <td>&nbsp;${profissional.getEspecialidade()}</td>
	              <td>
                      <form name="submitForm" method="POST" action="/<%= contextPath%>/cliente/agendar">
                          <input type="hidden" name="nome" value ="${profissional.getNome()}">
                          <input type="hidden" name="cpf" value ="${profissional.getCPF()}">
                          <input type="submit" name="btn" value="Agendar Horário">
                      </form>
	              </td>
	            </tr>
            </c:forEach>
            </tbody>
          </table>
      	</section>

        <script type="text/javascript">
        // CRIANDO FILTRO POR AREA E ESPECIALIDADE
        </script>
    </body>
</html>