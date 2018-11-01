CREATE SCHEMA IF NOT EXISTS `maria` DEFAULT CHARACTER SET utf8 ;
USE `maria` ;

drop table if exists SKU_PHASE;
drop table if exists HISTORICO;
drop table if exists CANAL;
drop table if exists PRODUTO;
drop table if exists FAMILIA_COMERCIAL;

create table FAMILIA_COMERCIAL(
ID_FAMILIA_COMERCIAL int(20) NOT NULL AUTO_INCREMENT,
COD_FAMILIA_COMERCIAL varchar(20),
NOME_FAMILIA_COMERCIAL varchar(500),
constraint PK_FAMILIA_COMERCIAL primary key (ID_FAMILIA_COMERCIAL),
constraint UN_FAMILIA_COMERCIAL unique (COD_FAMILIA_COMERCIAL)
);

create table CANAL(
ID_CANAL int(20) NOT NULL AUTO_INCREMENT,
DESCRICAO varchar(255),
constraint PK_CANAL primary key (ID_CANAL)
);

create table PRODUTO(
SKU int(20) NOT NULL,
NOME_PRODUTO varchar(255),
ID_FAMILIA_COMERCIAL int(20),
constraint PK_PRODUTO_SKU primary key (SKU),
constraint FK_PRODUTO_FAMILIA_COMERCIAL foreign key (ID_FAMILIA_COMERCIAL) references FAMILIA_COMERCIAL (ID_FAMILIA_COMERCIAL)
);

create table HISTORICO(
ID_HISTORICO int(20) NOT NULL AUTO_INCREMENT,
MES_ANO Date,
QUANTIDADE int(20),
PRODUTO_SKU int(20),
ID_CANAL int(20),
constraint PK_HISTORICO primary key (ID_HISTORICO),
constraint FK_PRODUTO_SKU_HISTORICO foreign key (PRODUTO_SKU) references PRODUTO (SKU),
constraint FK_CANAL_HISTORICO foreign key (ID_CANAL) references CANAL (ID_CANAL)
);

create table SKU_PHASE(
SKU_PHASE_IN int(20),
SKU_PHASE_OUT int(20),
constraint PK_SKU_PHASE primary key (SKU_PHASE_IN,SKU_PHASE_OUT),
constraint FK_SKU_PHASE_IN foreign key (SKU_PHASE_IN) references PRODUTO (SKU),
constraint FK_SKU_PHASE_OUT foreign key (SKU_PHASE_OUT) references PRODUTO (SKU)
);
