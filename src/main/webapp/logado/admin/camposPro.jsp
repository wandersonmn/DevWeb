<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table border="1">
	<caption>
		<c:choose>
			<c:when test="${usuarios != null}">
                            Edição
                        </c:when>
			<c:otherwise>
                            Cadastro
                        </c:otherwise>
		</c:choose>
	</caption>
	<c:if test="${usuarios != null}">
		<input type="hidden" name="id" value="${usuarios.getCPF()}" />
	</c:if>
		<input type="hidden" name="tipousuario" value="Profissionais" />
	<tr>
		<td><label for="nome">Nome</label></td>
		<td><input type="text" id="nome" name="nome" size="45"
			required value="${usuarios.getNome()}" /></td>
	</tr>
	<tr>
		<td><label for="email">E-mail</label></td>
		<td><input type="text" id="email" name="email" size="45" required
			value="${usuarios.getEmail()}" /></td>
	</tr>
	<tr>
		<td><label for="cpf">CPF</label></td>
		<td><input type="text" id="cpf" name="cpf" size="45" required
			value="${usuarios.getCPF()}" /></td>
	</tr>
	<tr>
		<td><label for="sexo">Sexo</label></td>
		<td><input type="text" id="sexo" name="sexo" size="45" required
			value="${usuarios.getSexo()}" /></td>
	</tr>
	<tr>
		<td><label for="telefone">Telefone</label></td>
		<td><input type="text" id="telefone" name="telefone" size="45" required
			value="${usuarios.getTelefone()}" /></td>
	</tr>
	<tr>
		<td><label for="data_nascimento">Data Nasc.</label></td>
		<td><input type="text" id="data_nascimento" name="data_nascimento" size="45" required
			value="${usuarios.getData_nascimento()}" /></td>
	</tr>
	<tr>
		<td><label for="senha">Senha</label></td>
		<td><input type="text" id="senha" name="senha" size="45" required
			value="${usuarios.getSenha()}" /></td>
	</tr>
	<tr>
		<td><label for="area_atuacao">Área de atuação</label></td>
		<td><input type="text" id="area_atuacao" name="area_atuacao" size="45" required
			value="${usuarios.getArea_atuacao()}" /></td>
	</tr>
	<tr>
		<td><label for="especialidade">Especialidade</label></td>
		<td><input type="text" id="especialidade" name="especialidade" size="45" required
			value="${usuarios.getEspecialidade()}" /></td>
	</tr>
	<tr>
		<td><label for="qualificacoes">Qualificacoes</label></td>
		<td><input type="text" id="qualificacoes" name="qualificacoes" size="45" required
			value="${usuarios.getQualificacoes()}" /></td>
	</tr>
	
	<tr>
		<td colspan="2" align="center"><input type="submit" value="Salva" /></td>
	</tr>
</table>