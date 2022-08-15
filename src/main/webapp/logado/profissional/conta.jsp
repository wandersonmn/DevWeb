<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SistAgenda</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styled.css" />
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
	              <li>
	              	<a href="/<%=contextPath%>/login">Logout</a>
	              </li>
	            </ul>
	          </nav>
	        </div>
	      </div>
    	</header>
    	
    	<!-- CONSULTAS AGENDADAS -->
    	<section class="hero">
	      <div class="container">
	        <div>
	          <h2>
	            Painel do Profissional
	          </h2>
	          <p>
	            Consultas Agendadas
	          </p>
	        </div>
	      </div>
	    </section>

        <section class="cards">
          <table id="table_consultas_agendadas" class="tab">
            <thead>
            <tr>
              <th>Cliente</th>
              <th>Data<br>(AAAA-MM-DD)</th>
              <th>Hora</th>
              <th>Ações</th>
            </tr>
            </thead>
            
            <tbody>
            <c:forEach var="agenda" items="${requestScope.listaAgendamentos}" varStatus="loop">
	            <tr>
	              <td>&nbsp;${agenda.getCliente().getNome()}</td>
	              <td>&nbsp;${agenda.getData()}</td>
	              <td>&nbsp;${agenda.getHora()}</td>
	              <td>
                        <form name="submitForm" method="POST" action="/<%= contextPath%>/profissional/cancelarHorario">
                            <input type="hidden" id="data_${loop.index}" name="data" value="${agenda.getData()}"">
                            <input type="hidden" id="hora_${loop.index}" name="hora" value="${agenda.getHora()}">
                            <input type="submit" id="btn_${loop.index}" name="btn" value="Cancelar">
                        </form>
	              </td>
	            </tr>
            </c:forEach>
            </tbody>
          </table>
      	</section>
        
	    <section id="form">
	      <form name="submitForm" method="POST" action="/<%= contextPath%>/profissional/novoHorario">
	        <h3>Adicionar horario livre</h3>
	        <div class="form-group">
	          <input class="input-control" type="date" id="data" name="data" required value="${agenda.data}" />
	          <input class="input-control" type="time" id="hora" list="horas" name="hora" required value="${agenda.hora}" />
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
	          <button type="submit" name="btn" value="Adicionar Horário" class="button">Adicionar Horário</button>
	        </div>
	      </form>
	    </section>

    	<section class="hero">
	      <div class="container">
	          <p>
	            Horarios Livres Cadastrados
	          </p>
	        </div>
	      </div>
	    </section>
	            
        <section class="cards">
          <table id="table_consultas_agendadas" class="tab">
            <thead>
            <tr>
              <th>Data<br>(AAAA-MM-DD)</th>
              <th>Hora</th>
              <th>Ações</th>
            </tr>
            </thead>
            
            <tbody>
            <c:forEach var="agenda" items="${requestScope.listaLivres}" varStatus="loop">
	            <tr>
	              <td>&nbsp;${agenda.getData()}</td>
	              <td>&nbsp;${agenda.getHora()}</td>
	              <td>
                        <form name="submitForm" method="POST" action="/<%= contextPath%>/profissional/removerHorario">
                            <input type="hidden" id="data_${loop.index}" name="data" value="${agenda.getData()}"">
                            <input type="hidden" id="hora_${loop.index}" name="hora" value="${agenda.getHora()}">
                            <button type="submit" id="btn_${loop.index}" name="btn" value="Remover Horário">Remover Horário</button>
                        </form>
	              </td>
	            </tr>
            </c:forEach>
            </tbody>
          </table>
      	</section>        
        <!-- <div align="center">
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
                                <input class="input-control" type="hidden" name="data" value="${agenda.getData()}"">
                                <input class="input-control" type="hidden" name="hora" value="${agenda.getHora()}">
                                <input type="submit" name="btn" value="Remover Horário">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div> -->
        
    <script async defer>
 	// NAO PERMITIR DATAS ANTERIORES AO DIA DE HOJE
    today = new Date();
    humanMonth = today.getUTCMonth() + 1;

    if (humanMonth < 10){
    	humanMonth = '0' + humanMonth;
    }

    minDate = today.getFullYear() + "-" + humanMonth + "-" + today.getUTCDate();
    const inputDateElement = document.querySelector("#data");
    inputDateElement.setAttribute("min", minDate);
    
 	// NAO PERMITIR CANCELAMENTO COM MENOS DE 3 DIAS
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