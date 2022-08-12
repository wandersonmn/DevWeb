<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Autenticação de Usuário</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/layout.css"/>
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
	              <li>
	                <a href="/<%=contextPath%>/">Inicio</a>
	              </li>
	            </ul>
	          </nav>
	        </div>
	      </div>
    	</header>
    	
        <c:if test="${mensagens.existeErros}">
            <div id="erro">
                <ul>
                    <c:forEach var="erro" items="${mensagens.erros}">
                        <li> ${erro} </li>
                        </c:forEach>
                </ul>
            </div>
        </c:if>
        <!-- Passa o controle para LoginController.java (Que tem urlpatterns = /login) após o submit -->
        <div class="login-page">
		  <div class="form">
		    <h2>Login</h2>
		    <form method="post" action="login" class="login-form">
		      <input type="email" placeholder="email" name="login" value="${param.login}"/>
		      <input type="password" name="senha" placeholder="password"/>
		      <button type="submit" name="bOK" value="Entrar">Entrar</button>
		    </form>
		  </div>
		</div>
		
    </body>
</html>