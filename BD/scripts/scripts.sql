-- -----------------------------------------------------
-- Schema AA1-DESENVOLVIMENTO DE SOFTWARE PARA WEB
-- SISTEMA PARA AGENDAMENTO DE CONSULTAS 
-- ONLINE COM PROFISSIONAIS
-- -----------------------------------------------------

-- INICIALIZACAO
DROP DATABASE `CONSULTAS_ONLINE`;
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
  `sexo` CHAR(1) NULL,
  `telefone` VARCHAR(15) NOT NULL,
  `data_nascimento` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`CPF`)
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
    ON DELETE NO ACTION -- `CASCADE` TO DELETE HERE AS WELL
    ON UPDATE NO ACTION
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
    ON DELETE NO ACTION -- `CASCADE` TO DELETE HERE AS WELL
    ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `AGENDAMENTO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AGENDAMENTO` (
  `CPF_Cliente` VARCHAR(14) NOT NULL,
  `CPF_Profissional` VARCHAR(14) NOT NULL,
  `data` DATE NOT NULL, -- range 0001-01-01 to 9999-12-31
  `hora` TIME NOT NULL, -- range 00:00:00.0000000 to 23:59:59.9999999
  `status` VARCHAR(10) NOT NULL DEFAULT 'ativo',
  PRIMARY KEY (`CPF_Cliente`,`CPF_Profissional`,`data`, `hora`),
  CONSTRAINT `fk_CPF_Cliente_Agendamento`
    FOREIGN KEY (`CPF_Cliente`)
    REFERENCES `CLIENTE` (`CPF_Cliente`)
    ON DELETE NO ACTION -- `CASCADE` TO DELETE HERE AS WELL
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CPF_Profissional_Agendamento`
    FOREIGN KEY (`CPF_Profissional`)
    REFERENCES `PROFISSIONAL` (`CPF_Profissional`)
    ON DELETE NO ACTION -- `CASCADE` TO DELETE HERE AS WELL
    ON UPDATE NO ACTION
);

-- ------------------------------------------------------
-- exemplos de inserção e consulta
-- ------------------------------------------------------
-- !!! OS VALORES DEVEM ESTAR ENTRE ASPAS SIMPLES (') !!!

-- 2 usuarios
INSERT INTO USUARIO (`CPF`,`email`,`senha`,`nome`,`sexo`,`telefone`,`data_nascimento`) 
VALUES ('12345678900','user1@gmail.com','1234','Glauber Cliente da Silva','M','5511970707070','2000-12-21');

INSERT INTO USUARIO (`CPF`,`email`,`senha`,`nome`,`sexo`,`telefone`,`data_nascimento`) 
VALUES ('98765432100','user2@gmail.com','4321','Gecomonildo Profissional da Silva','M','5511960606060','1999-12-21');

-- 1 profissional
INSERT INTO PROFISSIONAL (`CPF_Profissional`,`area_atuacao`,`especialidade`,`qualificacoes`) VALUES ('98765432100','mecanico','fuselagem','/src/qualy/0243.pdf');

-- 1 cliente
INSERT INTO CLIENTE (`CPF_Cliente`) VALUES ('12345678900');

-- 1 agendamento entre o cliente e o usuario criados anteriormente
INSERT INTO AGENDAMENTO (`CPF_Cliente`,`CPF_Profissional`,`data`,`hora`) 
VALUES ('12345678900','98765432100','2022-07-25','14:00:00.0000000');

-- listagem dos horarios de conulstas de um cliente
SELECT * FROM AGENDAMENTO WHERE CPF_Cliente = '12345678900';

-- listagem dos horarios de conulstas de um profissional
SELECT * FROM AGENDAMENTO WHERE CPF_Profissional = '98765432100';

-- DELETE FROM USUARIO
-- ATENÇÃO, SIGA A SEQUENCIA INDICADA
--
SET FOREIGN_KEY_CHECKS=0; 
DELETE FROM USUARIO WHERE CPF = '12345678900'; 
SET FOREIGN_KEY_CHECKS=1;

-- DELETE FROM AGENDAMENTO
-- ATENÇÃO, SIGA A SEQUENCIA INDICADA
--
SET FOREIGN_KEY_CHECKS=0; 
DELETE FROM AGENDAMENTO WHERE CPF_Cliente = '12345678900'; 
SET FOREIGN_KEY_CHECKS=1;