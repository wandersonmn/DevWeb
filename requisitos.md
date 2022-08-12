# REQUISITOS

**Grupo E**

Sistema para agendamento de consultas online com profissionais

Aqui estão todos os requisitos do sistema

1. [ ] CRUD CLIENTES (APENAS ADM)
1.1  [ ]	CREATE
1.2	 [ ]	READ
1.3  [ ]	UPDATE
1.4  [ ]	DELETE

2. [ ] CRUD PROFISSIONAIS (APENAS ADM)
2.1	[ ]	CREATE
2.2 [ ]	READ
2.3	[ ]	UPDATE
2.4 [ ] DELETE

3. [ ] listagem de todos os profissionais em 1 unica página (nao requer login) com filtros (filtrar por area do conhecimento e posteriormente por especialidade)

4. [ ] Agendar horario de consulta com profissional (requer login do cliente). Clicar em um profissional o Cliente pode cadastrar uma consulta. Escolher **data** e **hora**, este agendamento deve ser gravado na tabela AGENDAMENTO.

5. [ ] Nao permitir cadastros de consultas de clientes com o mesmo profissional na mesma data e hora

6. [ ] listagem de todos os horarios de consultas de um cliente (requer login cliente)

7. [ ] listagem todas as consultas de um profissional (requer login profissional)

8. [ ] cliente ou profissionais poderão cancelar consultas com no mínimo 3 dias de antecedencia. Após alteração, os horarios deverao ser atualizados.

9. [ ] todos os erros possiveis:
9.1	[x] e-mail invalido na tela de login
9.2 [ ]