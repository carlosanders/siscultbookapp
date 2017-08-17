
CREATE SEQUENCE public.niveldeacesso_codigoniveldeacesso_seq_1;

CREATE TABLE public.NivelDeAcesso (
                codigoNivelDeAcesso INTEGER NOT NULL DEFAULT nextval('public.niveldeacesso_codigoniveldeacesso_seq_1'),
                nivelDeAcesso VARCHAR(45) NOT NULL,
                CONSTRAINT niveldeacesso_pk PRIMARY KEY (codigoNivelDeAcesso)
);


ALTER SEQUENCE public.niveldeacesso_codigoniveldeacesso_seq_1 OWNED BY public.NivelDeAcesso.codigoNivelDeAcesso;

CREATE SEQUENCE public.acesso_codigoacesso_seq;

CREATE TABLE public.Acesso (
                codigoAcesso INTEGER NOT NULL DEFAULT nextval('public.acesso_codigoacesso_seq'),
                comando VARCHAR(50) NOT NULL,
                descricao VARCHAR(60) NOT NULL,
                CONSTRAINT codigoacesso_pk PRIMARY KEY (codigoAcesso)
);


ALTER SEQUENCE public.acesso_codigoacesso_seq OWNED BY public.Acesso.codigoAcesso;

CREATE TABLE public.Acesso_Nivel (
                codigoAcesso INTEGER NOT NULL,
                codigoNivelDeAcesso INTEGER NOT NULL,
                CONSTRAINT acesso_nivel_pk PRIMARY KEY (codigoAcesso, codigoNivelDeAcesso)
);


CREATE SEQUENCE public.produto_codigoproduto_seq;

CREATE TABLE public.Produto (
                codigoProduto INTEGER NOT NULL DEFAULT nextval('public.produto_codigoproduto_seq'),
                descricaoCurta VARCHAR(100),
                descricaoLonga VARCHAR,
                preco NUMERIC,
                CONSTRAINT codigoproduto_pk PRIMARY KEY (codigoProduto)
);


ALTER SEQUENCE public.produto_codigoproduto_seq OWNED BY public.Produto.codigoProduto;

CREATE SEQUENCE public.item_codigoitem_seq;

CREATE TABLE public.Item (
                codigoItem INTEGER NOT NULL DEFAULT nextval('public.item_codigoitem_seq'),
                quantidade INTEGER NOT NULL,
                codigoProduto INTEGER NOT NULL,
                CONSTRAINT item_pk PRIMARY KEY (codigoItem)
);


ALTER SEQUENCE public.item_codigoitem_seq OWNED BY public.Item.codigoItem;

CREATE SEQUENCE public.autor_codigoautor_seq;

CREATE TABLE public.Autor (
                codigoAutor INTEGER NOT NULL DEFAULT nextval('public.autor_codigoautor_seq'),
                nomecompleto VARCHAR(90) NOT NULL,
                datanascimento DATE NOT NULL,
                cpf VARCHAR(11) NOT NULL,
                sexo VARCHAR NOT NULL,
                rua VARCHAR NOT NULL,
                bairro VARCHAR NOT NULL,
                cidade VARCHAR NOT NULL,
                estado VARCHAR NOT NULL,
                sobrenome VARCHAR(20),
                CONSTRAINT codigoautor_pk PRIMARY KEY (codigoAutor)
);


ALTER SEQUENCE public.autor_codigoautor_seq OWNED BY public.Autor.codigoAutor;

CREATE SEQUENCE public.editora_codigoeditora_seq;

CREATE TABLE public.Editora (
                codigoEditora INTEGER NOT NULL DEFAULT nextval('public.editora_codigoeditora_seq'),
                NomeEditora VARCHAR(80) NOT NULL,
                cnpj VARCHAR(15) NOT NULL,
                obs TEXT,
                CONSTRAINT codigoeditora_pk PRIMARY KEY (codigoEditora)
);


ALTER SEQUENCE public.editora_codigoeditora_seq OWNED BY public.Editora.codigoEditora;

CREATE TABLE public.Livro (
                codigoProduto INTEGER NOT NULL,
                codigoEditora INTEGER NOT NULL,
                isbn VARCHAR(100) NOT NULL,
                titulo VARCHAR(150) NOT NULL,
                estoque INTEGER NOT NULL,
                assunto VARCHAR NOT NULL,
                figura VARCHAR(150) NOT NULL,
                anoPublicacao VARCHAR(4) NOT NULL,
                CONSTRAINT livro_pk PRIMARY KEY (codigoProduto)
);


CREATE TABLE public.Livro_Autor (
                codigoAutor INTEGER NOT NULL,
                codigoProduto INTEGER NOT NULL,
                CONSTRAINT livro_autor_pk PRIMARY KEY (codigoAutor, codigoProduto)
);


CREATE SEQUENCE public.funcionario_codigofuncionario_seq;

CREATE TABLE public.Funcionario (
                codigoFuncionario INTEGER NOT NULL DEFAULT nextval('public.funcionario_codigofuncionario_seq'),
                cargo VARCHAR NOT NULL,
                dataAdmissao DATE NOT NULL,
                dataDemissao DATE,
                nomeCompleto VARCHAR NOT NULL,
                dataNascimento DATE NOT NULL,
                cpf VARCHAR NOT NULL,
                sexo VARCHAR NOT NULL,
                rua VARCHAR NOT NULL,
                bairro VARCHAR NOT NULL,
                cidade VARCHAR NOT NULL,
                estado VARCHAR NOT NULL,
                senha VARCHAR NOT NULL,
                ultimoAcesso DATE NOT NULL,
                status VARCHAR NOT NULL,
                codigoNivelDeAcesso INTEGER NOT NULL,
                CONSTRAINT codigofuncionario_pk PRIMARY KEY (codigoFuncionario)
);


ALTER SEQUENCE public.funcionario_codigofuncionario_seq OWNED BY public.Funcionario.codigoFuncionario;

CREATE SEQUENCE public.cliente_codigocliente_seq;

CREATE TABLE public.Cliente (
                codigoCliente INTEGER NOT NULL DEFAULT nextval('public.cliente_codigocliente_seq'),
                email VARCHAR,
                telefone VARCHAR(25) NOT NULL,
                desde DATE,
                nomeCompleto VARCHAR,
                dataNascimento DATE,
                cpf VARCHAR,
                sexo VARCHAR,
                estadoCivil VARCHAR,
                rua VARCHAR,
                bairro VARCHAR,
                cidade VARCHAR,
                estado VARCHAR,
                senha VARCHAR,
                ultimoAcesso DATE,
                status VARCHAR,
                codigoNivelDeAcesso INTEGER NOT NULL,
                CONSTRAINT codigocliente_pk PRIMARY KEY (codigoCliente)
);


ALTER SEQUENCE public.cliente_codigocliente_seq OWNED BY public.Cliente.codigoCliente;

CREATE SEQUENCE public.pedido_codigopedido_seq;

CREATE TABLE public.Pedido (
                codigoPedido INTEGER NOT NULL DEFAULT nextval('public.pedido_codigopedido_seq'),
                codigoCliente INTEGER NOT NULL,
                descricao VARCHAR(150),
                statusPedido VARCHAR(50) NOT NULL,
                CONSTRAINT pedido_pk PRIMARY KEY (codigoPedido)
);


ALTER SEQUENCE public.pedido_codigopedido_seq OWNED BY public.Pedido.codigoPedido;

CREATE TABLE public.Pedito_Item (
                codigoItem INTEGER NOT NULL,
                codigoPedido INTEGER NOT NULL,
                CONSTRAINT pedito_item_pk PRIMARY KEY (codigoItem, codigoPedido)
);


CREATE SEQUENCE public.pagamento_codigopagamento_seq;

CREATE TABLE public.Pagamento (
                codigoPagamento INTEGER NOT NULL DEFAULT nextval('public.pagamento_codigopagamento_seq'),
                valorTotal DOUBLE PRECISION NOT NULL,
                tipoBanco VARCHAR(65),
                cedente VARCHAR(100),
                bandeira VARCHAR(45),
                numeroCartao VARCHAR(20),
                validadeCartao VARCHAR(20),
                codigoSeguranca VARCHAR(3),
                codigoControle VARCHAR(25),
                codigoPedido INTEGER NOT NULL,
                CONSTRAINT pagamento_pk PRIMARY KEY (codigoPagamento)
);


ALTER SEQUENCE public.pagamento_codigopagamento_seq OWNED BY public.Pagamento.codigoPagamento;

ALTER TABLE public.Cliente ADD CONSTRAINT niveldeacesso_cliente_fk
FOREIGN KEY (codigoNivelDeAcesso)
REFERENCES public.NivelDeAcesso (codigoNivelDeAcesso)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Funcionario ADD CONSTRAINT niveldeacesso_funcionario_fk
FOREIGN KEY (codigoNivelDeAcesso)
REFERENCES public.NivelDeAcesso (codigoNivelDeAcesso)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Acesso_Nivel ADD CONSTRAINT niveldeacesso_acesso_funcionario_fk
FOREIGN KEY (codigoNivelDeAcesso)
REFERENCES public.NivelDeAcesso (codigoNivelDeAcesso)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Acesso_Nivel ADD CONSTRAINT acesso_acesso_funcionario_fk
FOREIGN KEY (codigoAcesso)
REFERENCES public.Acesso (codigoAcesso)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Livro ADD CONSTRAINT produto_livro_fk
FOREIGN KEY (codigoProduto)
REFERENCES public.Produto (codigoProduto)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Item ADD CONSTRAINT produto_item_fk
FOREIGN KEY (codigoProduto)
REFERENCES public.Produto (codigoProduto)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Pedito_Item ADD CONSTRAINT item_pedito_item_fk
FOREIGN KEY (codigoItem)
REFERENCES public.Item (codigoItem)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Livro_Autor ADD CONSTRAINT autor_livro_autor_fk
FOREIGN KEY (codigoAutor)
REFERENCES public.Autor (codigoAutor)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Livro ADD CONSTRAINT editora_livro_fk
FOREIGN KEY (codigoEditora)
REFERENCES public.Editora (codigoEditora)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Livro_Autor ADD CONSTRAINT livro_livro_autor_fk
FOREIGN KEY (codigoProduto)
REFERENCES public.Livro (codigoProduto)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Pedido ADD CONSTRAINT cliente_pedido_fk
FOREIGN KEY (codigoCliente)
REFERENCES public.Cliente (codigoCliente)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Pagamento ADD CONSTRAINT pedido_pagamento_fk
FOREIGN KEY (codigoPedido)
REFERENCES public.Pedido (codigoPedido)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Pedito_Item ADD CONSTRAINT pedido_pedito_item_fk
FOREIGN KEY (codigoPedido)
REFERENCES public.Pedido (codigoPedido)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
