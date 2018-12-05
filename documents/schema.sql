--Tables
CREATE TABLE ENDERECOS(
	id integer,
	cep integer,
	logradouro varchar,
	numero integer,
	bairro varchar,
	cidade varchar,
	estado varchar,
	flag_ativo integer,
	CONSTRAINT pk_enderecos PRIMARY KEY (id)
);

CREATE TABLE CONTATOS(
	id integer,
	email varchar,
	ddd integer,
	telefone integer,
	flag_ativo integer,
	CONSTRAINT pk_contatos PRIMARY KEY (id)
);

CREATE TABLE ESPECIALIDADES(
	id integer,
	nome varchar,
	flag_ativo integer,
	CONSTRAINT pk_especialidades PRIMARY KEY (id)
);

CREATE TABLE DATA_HORA_CONSULTAS(
	id integer,
	data_consulta varchar,
	hora_consulta varchar,
	flag_ativo integer,
	CONSTRAINT pk_data_hora_consultas PRIMARY KEY (id)
);

CREATE TABLE PACIENTES(
	id integer,
	nome varchar,
	cpf bigint,
	rg bigint,
	endereco integer,
	contato integer,
	flag_ativo integer,
	CONSTRAINT pk_pacientes PRIMARY KEY (id),
	CONSTRAINT fk_endereco_pacientes FOREIGN KEY (endereco) REFERENCES ENDERECOS(id),
	CONSTRAINT fk_contato_pacientes FOREIGN KEY (contato) REFERENCES CONTATOS(id)
);

CREATE TABLE FUNCIONARIOS(
	id integer,
	nome varchar,
	cpf bigint,
	rg bigint,
	tipo varchar,
	flag_ativo integer,
	contato integer,
	endereco integer,
	CONSTRAINT pk_funcionarios PRIMARY KEY (id),
	CONSTRAINT fk_endereco_funcionarios FOREIGN KEY (endereco) REFERENCES ENDERECOS(id),
	CONSTRAINT fk_contato_funcionarios FOREIGN KEY (contato) REFERENCES CONTATOS(id)
);

CREATE TABLE MEDICOS(
	crm bigint,
	funcionario integer,
	especialidade integer,
	flag_ativo integer,
	CONSTRAINT pk_medicos PRIMARY KEY (crm),
	CONSTRAINT fk_funcionario_medicos FOREIGN KEY (funcionario) REFERENCES FUNCIONARIOS(id),
	CONSTRAINT fk_especialidade_medicos FOREIGN KEY (especialidade) REFERENCES ESPECIALIDADES(id)
);
    
CREATE TABLE CONSULTAS(
    id integer,
    paciente bigint,
    especialidade int,
    medico bigint,
    data_hora_consulta integer,
    CONSTRAINT pk_consultas PRIMARY KEY (id),
    CONSTRAINT fk_paciente_consultas FOREIGN KEY (paciente) REFERENCES PACIENTES(id),
    CONSTRAINT fk_especialidade_consultas FOREIGN KEY (especialidade) REFERENCES ESPECIALIDADES(id),
    CONSTRAINT fk_medico_consultas FOREIGN KEY (medico) REFERENCES MEDICOS(crm),
    CONSTRAINT fk_data_hora_consulta_consultas FOREIGN KEY (data_hora_consulta) REFERENCES DATA_HORA_CONSULTAS(id)
);




INSERT INTO DATA_HORA_CONSULTAS VALUES
(nextval('data_hora_consultas_seq'), '28/11/2018', '12:00', 1),
(nextval('data_hora_consultas_seq'), '28/11/2018', '12:30', 1),
(nextval('data_hora_consultas_seq'), '28/11/2018', '13:00', 1),
(nextval('data_hora_consultas_seq'), '28/11/2018', '13:30', 1),
(nextval('data_hora_consultas_seq'), '28/11/2018', '15:00', 1),
(nextval('data_hora_consultas_seq'), '28/11/2018', '15:30', 1),
(nextval('data_hora_consultas_seq'), '28/11/2018', '16:00', 1),
(nextval('data_hora_consultas_seq'), '28/11/2018', '16:30', 1),
(nextval('data_hora_consultas_seq'), '28/11/2018', '17:00', 1),
(nextval('data_hora_consultas_seq'), '29/11/2018', '12:00', 1),
(nextval('data_hora_consultas_seq'), '29/11/2018', '12:30', 1),
(nextval('data_hora_consultas_seq'), '29/11/2018', '13:30', 1),
(nextval('data_hora_consultas_seq'), '29/11/2018', '15:00', 1),
(nextval('data_hora_consultas_seq'), '29/11/2018', '15:30', 1),
(nextval('data_hora_consultas_seq'), '29/11/2018', '16:00', 1),
(nextval('data_hora_consultas_seq'), '29/11/2018', '16:30', 1),
(nextval('data_hora_consultas_seq'), '29/11/2018', '17:00', 1);


--Insert especialidades
INSERT INTO ESPECIALIDADES VALUES 
(nextval('especialidades_seq'),'Cardiologia',1),
(nextval('especialidades_seq'),'Pediatria',1),
(nextval('especialidades_seq'),'Ginecologia',1);
(nextval('especialidades_seq'),'Cardiologia',1),
(nextval('especialidades_seq'),'Oftalmologia',1),
(nextval('especialidades_seq'),'Ginecologia',1),
(nextval('especialidades_seq'),'Dermatologia',1),
(nextval('especialidades_seq'),'Ortopedia',1),
(nextval('especialidades_seq'),'Geriatria',1);


--Sequences
CREATE SEQUENCE pacientes_seq;
CREATE SEQUENCE enderecos_seq;
CREATE SEQUENCE contatos_seq;
CREATE SEQUENCE funcionarios_seq;
CREATE SEQUENCE especialidades_seq;
CREATE SEQUENCE consultas_seq;
CREATE SEQUENCE data_hora_consultas_seq;

--Selects
SELECT * FROM PACIENTES;
SELECT * FROM ENDERECOS;
SELECT * FROM CONTATOS;
SELECT * FROM ESPECIALIDADES;
SELECT * FROM FUNCIONARIOS;
SELECT * FROM CONSULTAS;
SELECT * FROM MEDICOS;
SELECT * FROM DATA_HORA_CONSULTAS

--Drop
DROP TABLE CONTATOS;
DROP TABLE ENDERECOS;
DROP TABLE PACIENTES;
DROP TABLE FUNCIONARIOS;
DROP TABLE ESPECIALIDADES;
DROP TABLE CONSULTAS;
DROP TABLE MEDICOS;