<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SistAgenda</title>
    </head>
    <body>
        <%
		String contextPath = request.getContextPath().replace("/", "");
		
	    %>
        <div align="center">
            <h1>Painel do Adm</h1>
            <h3>
                <a href="/<%=contextPath%>/">Inicio</a>
                <a href="/<%=contextPath%>/">	Novo Usuario</a>
                <a href="/<%=contextPath%>/login">	Logout</a>
            </h3>
        </div>
        <div align="center">
        <form action="" id="tipousuario" method="POST">
		<select id="tipousuario" name="tipousuario" onchange="this.form.submit()">
					<c:forEach items="${requestScope.listusers}" var="user">
					<option value="${user}"
    					<c:if test="${param.tipousuario eq user}">selected="selected"</c:if>
   					 	>
   					 	${user}
					</option>
                	</c:forEach>          	
                </select>
        
        </div>
        
        <div align="center">
            <table border="1">
                <caption>Usuários cadastrados</caption>
                <tr>
                    <th>Nome</th>
                    <th>E-mail</th>
                    <th>Ações</th>
                    
                </tr>
                <c:forEach var="usuarios" items="${requestScope.listaUsuarios}">
                    <tr>
                        <td>${usuarios.getNome()}</td>
                        <td>${usuarios.getEmail()}</td>
                        <td><a href="/<%= contextPath%>/admin/edicao?cpf=${usuarios.getCPF()}&tipousuario=${param.tipousuario}">Editar</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="/<%= contextPath%>/admin/remocao?cpf=${usuarios.getCPF()}}"
						onclick="return confirm('Tem certeza de que deseja excluir este usuário?');">
							Remover </a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        </form>
    </body>
</html>