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
            <table id="table_consultas_agendadas" border="1">
                <caption>Consultas agendadas</caption>
                <tr>
                    <th>Profissional</th>
                    <th>Especialidade</th>
                    <th>Data</th>
                    <th>Hora</th>
                    <th>Ações</th>
                </tr>
                <c:forEach var="agenda" items="${requestScope.listaAgendamentos}" varStatus="loop">
                    <tr>
                        <td>${agenda.getProfissional().getNome()}</td>
                        <td>${agenda.getProfissional().getEspecialidade()}</td>
                        <td>${agenda.getData()}</td>
                        <td>${agenda.getHora()}</td>
                        <td>
                            <form name="submitForm" method="POST" action="/<%= contextPath%>/cliente/cancelarHorario">
                                <input type="hidden" name="pro" value="${agenda.getProfissional().getCPF()}"">
                                <input type="hidden" id="data_${loop.index}" name="data" value="${agenda.getData()}"">
                                <input type="hidden" id="hora_${loop.index}" name="hora" value="${agenda.getHora()}">
                                <input type="submit" id="btn_${loop.index}" name="btn" value="Cancelar">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <script async defer>
     	// NAO PERMITIR CANCELAMENTO COM MENOS DE 3 DIAS
     	today = new Date();
        numAgendamentos = document.getElementById("table_consultas_agendadas").rows.length;
        diasLimiteParaCancelar = 3;

        for (var i = 0; i < numAgendamentos; i++)
        {
        	var agendamentoData = document.getElementById("data_"+i).value;
        	var agendamentoHora = document.getElementById("hora_"+i).value;
        	var agendamentoDate = new Date(agendamentoData + "T" + agendamentoHora);
        	
        	// (dia da consulta) - (dia atual)
        	var agendamentoDelta = agendamentoDate - today;
        	var deltaEmDias = Math.floor(agendamentoDelta/(24 * 3600 * 1000));
        	
        	if (deltaEmDias < diasLimiteParaCancelar)
        	{
        		var btnCancel = document.getElementById("btn_"+i);
        		btnCancel.setAttribute("disabled", "disabled");
        	}
        }
        </script>
    </body>
</html>