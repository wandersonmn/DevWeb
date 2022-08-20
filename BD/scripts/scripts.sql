-- -----------------------------------------------------
-- Schema AA1-DESENVOLVIMENTO DE SOFTWARE PARA WEB
-- SISTEMA PARA AGENDAMENTO DE CONSULTAS 
-- ONLINE COM PROFISSIONAIS
-- -----------------------------------------------------

-- INICIALIZACAO
DROP DATABASE CONSULTAS_ONLINE;
CREATE DATABASE `CONSULTAS_ONLINE`;
USE `CONSULTAS_ONLINE`;

-- POSSIVEIS TRATAMENTOS DE ERROS
--
--
--
--
--
--
--


-- -----------------------------------------------------
-- Table `USUARIO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `USUARIO` (
  `CPF` VARCHAR(14) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `senha` VARCHAR(20) NOT NULL,
  `nome` VARCHAR(50) NOT NULL,
  `sexo` CHAR(1) NULL, -- M (macho), F (femea), O (outro)
  `telefone` VARCHAR(15) NOT NULL,
  `data_nascimento` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`CPF`),
  CONSTRAINT sexo_check CHECK (sexo IN ('M', 'F', 'O'))
);

-- -----------------------------------------------------
-- Table `ADM`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ADM` (
  `CPF_Adm` VARCHAR(14) NOT NULL,
  PRIMARY KEY (`CPF_Adm`),
  CONSTRAINT `fk_Adm_Usuario`
    FOREIGN KEY (`CPF_Adm`)
    REFERENCES `USUARIO` (`CPF`)
    ON DELETE CASCADE -- `CASCADE` TO DELETE HERE AS WELL
    ON UPDATE CASCADE
);

-- -----------------------------------------------------
-- Table `PROFISSIONAL`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PROFISSIONAL` (
  `CPF_Profissional` VARCHAR(14) NOT NULL,
  `area_atuacao` VARCHAR(50) NOT NULL,
  `especialidade` VARCHAR(50) NULL,
  `qualificacoes` VARCHAR(512) CHARACTER SET 'ascii' COLLATE 'ascii_general_ci',
  PRIMARY KEY (`CPF_Profissional`),
  CONSTRAINT `fk_Profissional_Usuario`
    FOREIGN KEY (`CPF_Profissional`)
    REFERENCES `USUARIO` (`CPF`)
    ON DELETE CASCADE -- `CASCADE` TO DELETE HERE AS WELL
    ON UPDATE CASCADE
);

-- -----------------------------------------------------
-- Table `CLIENTE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CLIENTE` (
  `CPF_Cliente` VARCHAR(14) NOT NULL,
  PRIMARY KEY (`CPF_Cliente`),
  CONSTRAINT `fk_Cliente_Usuario`
    FOREIGN KEY (`CPF_Cliente`)
    REFERENCES `USUARIO` (`CPF`)
    ON DELETE CASCADE -- `CASCADE` TO DELETE HERE AS WELL
    ON UPDATE CASCADE
);

-- -----------------------------------------------------
-- Table `AGENDAMENTO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AGENDAMENTO` (
  `CPF_Cliente` VARCHAR(14) NOT NULL,
  `CPF_Profissional` VARCHAR(14) NOT NULL,
  `data` DATE NOT NULL, -- range 0001-01-01 to 9999-12-31
  `hora` TIME NOT NULL, -- range 00:00:00.0000000 to 23:59:59.9999999
  `status` VARCHAR(10) NOT NULL DEFAULT 'ativo', -- {ativo, cancelado}
  PRIMARY KEY (`CPF_Profissional`,`data`, `hora`,`status`),
  CONSTRAINT `fk_CPF_Cliente_Agendamento`
    FOREIGN KEY (`CPF_Cliente`)
    REFERENCES `CLIENTE` (`CPF_Cliente`)
    ON DELETE NO ACTION -- `CASCADE` TO DELETE HERE AS WELL
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CPF_Profissional_Agendamento`
    FOREIGN KEY (`CPF_Profissional`)
    REFERENCES `PROFISSIONAL` (`CPF_Profissional`)
    ON DELETE NO ACTION -- `CASCADE` TO DELETE HERE AS WELL
    ON UPDATE NO ACTION,
    CHECK (status IN ('ativo', 'cancelado'))
);

-- -----------------------------------------------------
-- Table `HORARIO_CADASTRADO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HORARIO_CADASTRADO` (
  `CPF_Profissional` VARCHAR(14) NOT NULL,
  `data` DATE NOT NULL, -- range 0001-01-01 to 9999-12-31
  `hora` TIME NOT NULL, -- range 00:00:00.0000000 to 23:59:59.9999999
  `status` VARCHAR(10) NOT NULL DEFAULT 'livre', -- {livre, ocupado}
  PRIMARY KEY (`CPF_Profissional`,`data`, `hora`),
  CONSTRAINT `fk_CPF_Profissional_Horario_Cadastrado`
    FOREIGN KEY (`CPF_Profissional`)
    REFERENCES `PROFISSIONAL` (`CPF_Profissional`)
    ON DELETE CASCADE -- `CASCADE` TO DELETE HERE AS WELL
    ON UPDATE CASCADE,
  CHECK (status IN ('livre', 'ocupado'))
);

-- ------------------------------------------------------
-- exemplos de inserção e consulta
-- ------------------------------------------------------
-- !!! OS VALORES DEVEM ESTAR ENTRE ASPAS SIMPLES (') !!!

-- USUARIOS
INSERT INTO USUARIO (`CPF`,`email`,`senha`,`nome`,`sexo`,`telefone`,`data_nascimento`) 
VALUES ('12345678900','user1@gmail.com','usr','Glauber Cliente da Silva','M','5511970707070','2000-12-21');

INSERT INTO USUARIO (`CPF`,`email`,`senha`,`nome`,`sexo`,`telefone`,`data_nascimento`) 
VALUES ('98765432100','user2@gmail.com','pro','Gecomonildo Profissional da Silva','M','5511960606060','1999-12-21');

INSERT INTO USUARIO (`CPF`,`email`,`senha`,`nome`,`sexo`,`telefone`,`data_nascimento`) 
VALUES ('98765432200','profi0@gmail.com','pro','Profissional da Costa','M','5511960606060','1999-12-21');

INSERT INTO USUARIO (`CPF`,`email`,`senha`,`nome`,`sexo`,`telefone`,`data_nascimento`) 
VALUES ('42069621000','sandra@gmail.com','pro','Sandra Profissional da Silva','M','5511992837615','1997-11-12');

INSERT INTO USUARIO (`CPF`,`email`,`senha`,`nome`,`sexo`,`telefone`,`data_nascimento`) 
VALUES ('CPFEHSTRING','paulo@admin.com','adm','Paulo Administrador da Silva','M','5511987676545','2002-02-02');

-- PROFISSIONAIS
INSERT INTO PROFISSIONAL (`CPF_Profissional`,`area_atuacao`,`especialidade`,`qualificacoes`) VALUES ('98765432100','Mecanica','Direção Hidráulica','/src/qualy/0243.pdf');
INSERT INTO PROFISSIONAL (`CPF_Profissional`,`area_atuacao`,`especialidade`,`qualificacoes`) VALUES ('42069621000','Medicina','Cardiologia','todas');
INSERT INTO PROFISSIONAL (`CPF_Profissional`,`area_atuacao`,`especialidade`,`qualificacoes`) VALUES ('98765432200','Engenharia','Producao','todas');
INSERT INTO ADM (`CPF_Adm`) VALUES ('CPFEHSTRING');

-- CLIENTE
INSERT INTO CLIENTE (`CPF_Cliente`) VALUES ('12345678900');

-- Usuário VAZIO
INSERT INTO USUARIO (`CPF`,`email`,`senha`,`nome`,`sexo`,`telefone`,`data_nascimento`) 
VALUES ('VAZIO','USUARIO_VAZIO','USUARIO_VAZIO','USUARIO_VAZIO','M','USUARIO_VAZIO','USUARIO_VAZIO');

INSERT INTO CLIENTE (`CPF_Cliente`) VALUES ('VAZIO'); 

-- 1 agendamento entre o cliente e o usuario criados anteriormente
INSERT INTO AGENDAMENTO (`CPF_Cliente`,`CPF_Profissional`,`data`,`hora`) 
VALUES ('12345678900','98765432100','2022-07-25','14:00:00.0000000');

INSERT INTO AGENDAMENTO (`CPF_Cliente`,`CPF_Profissional`,`data`,`hora`) 
VALUES ('12345678900','42069621000','2022-08-12','13:30:00.0000000');

-- Inserindo alguns horários "vazios"
INSERT INTO AGENDAMENTO (`CPF_Cliente`,`CPF_Profissional`,`data`,`hora`) 
VALUES ('VAZIO','42069621000','2022-08-13','13:30:00.0000000');

INSERT INTO AGENDAMENTO (`CPF_Cliente`,`CPF_Profissional`,`data`,`hora`) 
VALUES ('VAZIO','42069621000','2022-08-14','13:30:00.0000000');

INSERT INTO AGENDAMENTO (`CPF_Cliente`,`CPF_Profissional`,`data`,`hora`) 
VALUES ('VAZIO','42069621000','2022-08-15','13:30:00.0000000');

-- INSERINDO HORARIOS DISPONIVEIS
INSERT INTO HORARIO_CADASTRADO (`CPF_Profissional`,`data`,`hora`) VALUES ('98765432200','2022-08-15','13:30');
INSERT INTO HORARIO_CADASTRADO (`CPF_Profissional`,`data`,`hora`) VALUES ('98765432200','2022-08-15','14:30');
INSERT INTO HORARIO_CADASTRADO (`CPF_Profissional`,`data`,`hora`) VALUES ('98765432200','2022-08-15','15:30');
INSERT INTO HORARIO_CADASTRADO (`CPF_Profissional`,`data`,`hora`) VALUES ('98765432200','2022-08-15','16:30');

-- listagem dos horarios de conulstas de um cliente
SELECT * FROM AGENDAMENTO WHERE CPF_Cliente = '12345678900';

-- listagem dos horarios de conulstas de um profissional
SELECT * FROM AGENDAMENTO WHERE CPF_Profissional = '98765432100';

select * from USUARIO;

-- DELETE FROM USUARIO
-- ATENÇÃO, SIGA A SEQUENCIA INDICADA
--

-- DELETE FROM USUARIO WHERE CPF = '12345678900'; 

-- SET FOREIGN_KEY_CHECKS=0; 
-- DELETE FROM USUARIO WHERE CPF = '12345678900'; 
-- SET FOREIGN_KEY_CHECKS=1;

-- DELETE FROM AGENDAMENTO
-- ATENÇÃO, SIGA A SEQUENCIA INDICADA
--

-- DELETE FROM AGENDAMENTO WHERE CPF_Cliente = '12345678900';

-- SET FOREIGN_KEY_CHECKS=0; 
-- DELETE FROM AGENDAMENTO WHERE CPF_Cliente = '12345678900'; 
-- SET FOREIGN_KEY_CHECKS=1;

-- UPDATE
-- ATUALIZA O SEXO DO REGISTRO ONDE O CPF É
-- UPDATE USUARIO SET SEXO = 'F' WHERE CPF = '42069621000';

-- UPDATE [TABELA] SET [CAMPO1] = [VALOR1], [CAMPO2] = [VALOR2] WHERE [CAMPO] = [VALOR];

INSERT INTO USUARIO (`CPF`,`email`,`senha`,`nome`,`sexo`,`telefone`,`data_nascimento`) 
VALUES ('121212122','prof3@admin.com','pro','Neymar Junior da Silva','M','5511987676545','2002-02-02');

INSERT INTO USUARIO (`CPF`,`email`,`senha`,`nome`,`sexo`,`telefone`,`data_nascimento`) 
VALUES ('131313133','prof4@admin.com','pro','Cassio Ferraz','M','5511987676545','2002-02-02');

INSERT INTO USUARIO (`CPF`,`email`,`senha`,`nome`,`sexo`,`telefone`,`data_nascimento`) 
VALUES ('141414144','prof5@admin.com','pro','Ronaldo Fenomeno','M','5511987676545','2002-02-02');

INSERT INTO USUARIO (`CPF`,`email`,`senha`,`nome`,`sexo`,`telefone`,`data_nascimento`) 
VALUES ('151515155','prof6@admin.com','pro','Marta','F','5511987676545','2002-02-02');

INSERT INTO USUARIO (`CPF`,`email`,`senha`,`nome`,`sexo`,`telefone`,`data_nascimento`) 
VALUES ('161616166','prof7@admin.com','pro','Camisimiro Reage','M','5511987676545','2002-02-02');

-- PROFISSIONAIS
INSERT INTO PROFISSIONAL (`CPF_Profissional`,`area_atuacao`,`especialidade`,`qualificacoes`) VALUES ('121212122','Mecanica','Direção Eletrica','0243.pdf');
INSERT INTO PROFISSIONAL (`CPF_Profissional`,`area_atuacao`,`especialidade`,`qualificacoes`) VALUES ('131313133','Medicina','Cardiologia','todas');
INSERT INTO PROFISSIONAL (`CPF_Profissional`,`area_atuacao`,`especialidade`,`qualificacoes`) VALUES ('141414144','Engenharia','Civil','todas');
INSERT INTO PROFISSIONAL (`CPF_Profissional`,`area_atuacao`,`especialidade`,`qualificacoes`) VALUES ('151515155','Medicina','Cardiologia','todas');
INSERT INTO PROFISSIONAL (`CPF_Profissional`,`area_atuacao`,`especialidade`,`qualificacoes`) VALUES ('161616166','Engenharia','Mecatronica','todas');
