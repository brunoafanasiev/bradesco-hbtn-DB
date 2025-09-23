CREATE TABLE Aluno (id integer, nome varchar(255), primary key (id));
CREATE TABLE Curso (id integer, nome varchar(255), professor_id bigint, primary key (id));
CREATE TABLE curso_aluno (curso_id bigint not null, aluno_id bigint not null);
CREATE TABLE Endereco (id integer, cidade varchar(255), rua varchar(255), aluno_id bigint, primary key (id));
CREATE TABLE Material (id integer, descricao varchar(255), curso_id bigint unique, primary key (id));
CREATE TABLE Professor (id integer, nome varchar(255), primary key (id));
CREATE TABLE Telefone (id integer, numero varchar(255), aluno_id bigint, primary key (id));