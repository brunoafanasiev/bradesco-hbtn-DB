CREATE TABLE Aluno (id integer, Matricula varchar(255), dataNascimento date, email varchar(255), nomeCompleto varchar(255), primary key (id));
CREATE TABLE Curso (id integer, nome varchar(255), professor_id bigint, primary key (id));
CREATE TABLE curso_aluno (curso_id bigint not null, aluno_id bigint not null);
CREATE TABLE Endereco (id integer, bairro varchar(255), cep varchar(255), cidade varchar(255), endereco varchar(255), estado varchar(255), logradouro varchar(255), numero varchar(255), aluno_id bigint, primary key (id));
CREATE TABLE Material (id integer, descricao varchar(255), url varchar(255), curso_id bigint unique, primary key (id));
CREATE TABLE Professor (id integer, nome varchar(255), primary key (id));
CREATE TABLE Telefone (id integer, numero varchar(255), aluno_id bigint, primary key (id));