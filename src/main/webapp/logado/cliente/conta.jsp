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
                <a href="/<%=contextPath%>/">Inicio</a>
            </h3>
            <h3>
                <a href="/<%=contextPath%>/login">Logout</a>
            </h3>
        </div>

        <div align="center">
            <table border="1">
                <caption>Consultas agendadas</caption>
                <tr>
                    <th>Profissional</th>
                    <th>Especialidade</th>
                    <th>Data</th>
                    <th>Hora</th>
                    <th>Ações</th>
                </tr>
                <c:forEach var="agenda" items="${requestScope.listaAgendamentos}">
                    <tr>
                        <td>${agenda.getProfissional().getNome()}</td>
                        <td>${agenda.getProfissional().getEspecialidade()}</td>
                        <td>${agenda.getData()}</td>
                        <td>${agenda.getHora()}</td>
                        <td>
                            <form name="submitForm" method="POST" action="/<%= contextPath%>/cliente/cancelarHorario">
                                <input type="hidden" name="pro" value="${agenda.getProfissional().getCPF()}"">
                                <input type="hidden" name="data" value="${agenda.getData()}"">
                                <input type="hidden" name="hora" value="${agenda.getHora()}">
                                <input type="submit" name="btn" value="Cancelar">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>