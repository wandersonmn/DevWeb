drop database if exists Usuario;

create database Usuario;

use Usuario;

create table BaseUsuario(id bigint not null auto_increment, email varchar(20) not null unique, senha varchar(64) not null, papel varchar(10) not null, cpf varchar(18) not null unique, nome varchar(256) not null, primary key (id));

create table Profissional(id bigint not null auto_increment, area_atuacao varchar(50) not null, especialidade varchar(50) not null, qualificacoes varchar(256) not null, usuario_id bigint not null, primary key (id), foreign key (usuario_id) references BaseUsuario(id));

create table Cliente(id bigint not null auto_increment, sexo varchar(10) not null, telefone varchar(20) not null, ddn varchar(10) not null, usuario_id bigint not null, primary key (id), foreign key (usuario_id) references BaseUsuario(id));

create table Consulta(id bigint not null auto_increment, data datetime not null, cliente_id bigint not null, profissional_id bigint not null, primary key (id), foreign key (cliente_id) references Cliente(id), foreign key (profissional_id) references Profissional(id));

insert into BaseUsuario(email, senha, papel, cpf, nome) values  ('admin@admin', 'admin', 'ADMIN', '000.000.000.00', 'Administrador');

insert into BaseUsuario(email, senha, papel, cpf, nome) values  ('cliente@cliente', 'cliente', 'CLIENT', '111.111.111.11', 'Cliente Fulano');

insert into BaseUsuario(email, senha, papel, cpf, nome) values  ('profissional@profissional', 'profissional', 'PRO', '222.222.222.22', 'Profissional Tal');

insert into Profissional(area_atuacao, especialidade, qualificacoes, usuario_id) values  ('Area_de_atuacao', 'Especialidade', 'Este profissional e qualificado em 1, 2 e 3', 3);

insert into Cliente(sexo, telefone, ddn, usuario_id) values  ('sexoClient', '(00) 0000-0000', 'dd/mm/aaaa', 2);

insert into Consulta(data, client_id, profissional_id) values ('2022-07-25 23:59:59', 1, 1);


