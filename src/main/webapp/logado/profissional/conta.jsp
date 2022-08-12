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
                <a href="/<%=contextPath%>/">Inicio</a>
            </h3>
            <h3>
                <a href="/<%=contextPath%>/login">Logout</a>
            </h3>
        </div>

        <div align="center">
            <table border="1">
                <caption>Consultas Agendadas</caption>
                <tr>
                    <th>Paciente</th>
                    <th>Data</th>
                    <th>Hora</th>
                    <th>Ações</th>
                </tr>
                <c:forEach var="agenda" items="${requestScope.listaAgendamentos}">
                    <tr>
                        <td>${agenda.getCliente().getNome()}</td>
                        <td>${agenda.getData()}</td>
                        <td>${agenda.getHora()}</td>
                        <td>
                            <form name="submitForm" method="POST" action="/<%= contextPath%>/profissional/cancelarHorario">
                                <input type="hidden" name="data" value="${agenda.getData()}"">
                                <input type="hidden" name="hora" value="${agenda.getHora()}">
                                <input type="submit" name="btn" value="Cancelar">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <br><br>
        <div align="center">
            <form name="submitForm" method="POST" action="/<%= contextPath%>/profissional/novoHorario">
                <input type="date" id="data" name="data" size="20" required value="${agenda.data}" />
                <input type="time" id="hora" list="horas" name="hora" size="20" required value="${agenda.hora}" />
                <datalist id="horas">
	                <option>09:00</option>
	                <option>10:00</option>
	                <option>11:00</option>
	                <option>12:00</option>
	                <option>13:00</option>
	                <option>14:00</option>
	                <option>15:00</option>
	                <option>16:00</option>
	                <option>17:00</option>
	                <option>18:00</option>
                </datalist>
                
                <input type="submit" name="btn" value="Adicionar Horário">
            </form>
        </div>
        <br>
        <div align="center">
            <table border="1">
                <caption>Horários Livres Cadastrados</caption>
                <tr>
                    <th>Data</th>
                    <th>Hora</th>
                    <th>Ações</th>
                </tr>
                <c:forEach var="agenda" items="${requestScope.listaLivres}">
                    <tr>
                        <td>${agenda.getData()}</td>
                        <td>${agenda.getHora()}</td>
                        <td>
                            <form name="submitForm" method="POST" action="/<%= contextPath%>/profissional/removerHorario">
                                <input type="hidden" name="data" value="${agenda.getData()}"">
                                <input type="hidden" name="hora" value="${agenda.getHora()}">
                                <input type="submit" name="btn" value="Remover Horário">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    <script src="${pageContext.request.contextPath}/scripts.js" async defer></script>
    </body>
</html>