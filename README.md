# Sistema para agendamento de consultas online com profissionais

**Convenção de nomeclatura**

.	Os diretórios na raiz devem sempre ser com letras capitalizadas e de breve descrição
.	Adicione seu diretório a tabela abaixo e descreva brevemente a função dele

| DIRETÓRIO |                                            FUNÇÃO                                           |
|:---------:|:-------------------------------------------------------------------------------------------:|
|     BD    | contém as informações sobre o banco, diagrama entidade relacionamento e scripts para o CRUD |
|           |                                                                                             |
|           |                                                                                             |

## Configuração para rodar o repositório

Pré-requisitos: Antes de executar o programa é necessário instalar e configurar:
- `MySQL` (Localmente, anote seu usuário e senha)
- `Tomcat` (Versão testada: `9.0.64`)
- `Maven` (Para compilação)

Para compilar o programa:
- Em um terminal inicie as variáveis de ambiente `SQL_USER` e `SQL_PASS`
    e.g `export SQL_USER=Valter`
- Nesse mesmo terminal inicie o tomcat (<Diretório do Tomcat>/bin/ -> ./catalina.sh start)
- Também tenha certeza que o `MySQL` está rodando, e execute o script em `BD/scripts/scripts.sql` (Utilizando o `source`)
- Compile o programa a partir de sua pasta raiz usando `mvn tomcat7:deploy` ou `mvn tomcat7:redeploy` caso o programa já tenha sido compilado anteriormente

Acesse [localhost:8080/SistAgenda/](http://localhost:8080/SistAgenda/)

### ciclo de interação neste repósitório

```bash
# primeiro veja se há atualizações
git pull

# em seguida push suas edições
git add . && git commit -m "Seu comentário resumido aqui" && git push
```

### comandos gits úteis

```bash
# atualiza o repositório
git pull

# adiciona todas as edições feitas para o rastreador
git add .

# insere um comentário sobre sua ultima inserção
git commit -m "Seu comentário resumido aqui"

# envia suas edições para o repositório
git push
```
