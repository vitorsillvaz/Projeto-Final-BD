CREATE DATABASE SUPERMERCADO;

USE SUPERMERCADO;

CREATE TABLE CLIENTE (
 id_cliente int primary key not null auto_increment,
 nome text,
 endereco text,
 telefone text
);

create table Produto (
    id_produto integer not null auto_increment,
    nome varchar(255) not null,
    validade int not null,
    id_cliente int not null,
    primary key (id_produto),
    foreign key (id_cliente) references cliente (id_cliente)
);

CREATE TABLE usuario (
  id_usuario int NOT NULL AUTO_INCREMENT,
  email text NOT NULL,
  senha text NOT NULL,
  nome text NOT NULL,
  PRIMARY KEY (id_usuario)
);
