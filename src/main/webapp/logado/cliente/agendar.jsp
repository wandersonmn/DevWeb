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
            <h1>Agendando com ${nomeProfissional}</h1>
            <h3>
                <a href="/<%=contextPath%>/">Inicio</a>
            </h3>
            <h3>
                <a href="/<%=contextPath%>/login">Logout</a>
            </h3>
        </div>

        <div align="center">
            <table border="1">
                <caption>Consultas Disponíveis</caption>
                <tr>
                    <th>Data</th>
                    <th>Hora</th>
                    <th>Ações</th>
                    
                </tr>
                <c:forEach var="agenda" items="${listaHorariosDisponiveis}">
                    <tr>
                        <td><c:out value="${agenda.getData()}"/></td>
                        <td><c:out value="${agenda.getHora()}"/></td>
                        <td><a href="/<%= contextPath%>/cliente/agendarHorario?pro=${agenda.getProfissional().getCPF()}&data=${agenda.getData()}&hora=${agenda.getHora()}}">Selecionar Horário</a></a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>