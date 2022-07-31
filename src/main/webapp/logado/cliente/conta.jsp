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
            <h1>Painel do Cliente</h1>
            <h3>
                <a href="/<%=contextPath%>/login">Minha Conta</a>
            </h3>
        </div>

        <div align="center">
            <table border="1">
                <caption>Profissionais Disponíveis</caption>
                <tr>
                    <th>Nome</th>
                    <th>Área de Atuação</th>
                    <th>Especialidade</th>
                    <th>Ações</th>
                </tr>
                <c:forEach var="profissional" items="${requestScope.listaProfissionais}">
                    <tr>
                        <td>${profissional.getNome()}</td>
                        <td>${profissional.getArea_atuacao()}</td>
                        <td>${profissional.getEspecialidade()}</td>
                        <td><a href="/<%= contextPath%>/agendar?cpf_profissional=${profissional.getCPF()}">Agendar</a></a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>