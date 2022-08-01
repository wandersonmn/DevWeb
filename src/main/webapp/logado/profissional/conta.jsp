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
            <h1>Painel do Profissional</h1>
            <h3>
                <a href="/<%=contextPath%>/login">Inicio</a>
            </h3>
        </div>

        <div align="center">
            <table border="1">
                <caption>consultas</caption>
                <tr>
                    <th>Paciente</th>
                    <th>Data</th>
                    <th>Hora</th>
                </tr>
                <c:forEach var="agenda" items="${requestScope.listaAgendamentos}">
                    <tr>
                        <td>${agenda.getCliente().getNome()}</td>
                        <td>${agenda.getData()}</td>
                        <td>${agenda.getHora()}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>