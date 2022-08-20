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
	    
	    <section id="form">
	      <form name="submitForm" method="POST" action="/<%= contextPath%>/profissional/novoHorario"
	      style="padding: 5px 20px;" >
	      	<p style="color: rgb(154, 142, 191);font-size: 1.8rem;line-height: 3.2rem;margin: 10px 0px;">Filtre por área</p>
	        
	        <div class="form-group">
	          <select class="input-control" id="selecione_area" name="area" placeholder="Filtre por área">
	          	<option value="todos">Todos</option>
          	    <option value="medicina">Medicina</option>
                <option value="engenharia">Engenharia</option>
                <option value="mecaninca">Mecanica</option>
	            <!-- Adicione uma area aqui -->
               </select>
              
              <!--    
	          <input class="input-control" type="text" list="especialidades" placeholder="Filtre por especialidade" name="especialidade" value="todos" />
                <datalist id="especialidades">
	                <option>Cardiologia</option>
	                <option>Direção Hidráulica</option>
	                <option>Produção</option>
	                Adicione uma especialidade aqui
                </datalist>
               -->
	        </div>
	        
	      </form>
	    </section>
	  
		<section class="cards">
          <table class="tab" id="table">
            <thead>
            <tr>
              <th>Nome</th>
              <th>Área</th>
              <th>Especialidade</th>
              <th>Ações</th>
            </tr>
            </thead>
            
            <tbody id="tbody">
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
        var select = document.querySelector("#selecione_area");
        select.addEventListener("change", (e) => {
        	var selected = select.value.toLowerCase();
        	
        	var table = document.getElementById("tbody");
        	var tableRows = table.rows.length;
        	var trElements = table.getElementsByTagName("tr");
        	
        	for (var i = 0; i < tableRows; i++)
        	{
        		var tdElements = trElements[i].getElementsByTagName("td");
        		var tdText = tdElements[1].innerText || tdElements[1].textContent;
        		tdText = tdText.toLowerCase().replace(/\W/g, '');
        		if (selected === "todos")
        		{
        			trElements[i].style.display = "";
        		}
        		else if (tdText.toLowerCase() === selected)
        		{
        			trElements[i].style.display = "";
        		}
        		else
        		{
        			trElements[i].style.display = "none";
        		}
        	}
        	
        });
        
        </script>
    </body>
</html>