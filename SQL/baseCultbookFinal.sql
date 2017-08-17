--
-- PostgreSQL database dump
--

-- Dumped from database version 9.0.0
-- Dumped by pg_dump version 9.0.0
-- Started on 2011-06-04 22:50:18

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 1934 (class 1262 OID 41630)
-- Name: baseCultbook; Type: DATABASE; Schema: -; Owner: cultbook
--

CREATE DATABASE "baseCultbook" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Portuguese, Brazil' LC_CTYPE = 'Portuguese, Brazil';


ALTER DATABASE "baseCultbook" OWNER TO cultbook;

\connect "baseCultbook"

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 356 (class 2612 OID 11574)
-- Name: plpgsql; Type: PROCEDURAL LANGUAGE; Schema: -; Owner: postgres
--

CREATE OR REPLACE PROCEDURAL LANGUAGE plpgsql;


ALTER PROCEDURAL LANGUAGE plpgsql OWNER TO postgres;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1548 (class 1259 OID 41631)
-- Dependencies: 6
-- Name: acesso; Type: TABLE; Schema: public; Owner: cultbook; Tablespace: 
--

CREATE TABLE acesso (
    codigoacesso integer NOT NULL,
    comando character varying(50) NOT NULL,
    descricao character varying(60) NOT NULL
);


ALTER TABLE public.acesso OWNER TO cultbook;

--
-- TOC entry 1549 (class 1259 OID 41634)
-- Dependencies: 6 1548
-- Name: acesso_codigoacesso_seq; Type: SEQUENCE; Schema: public; Owner: cultbook
--

CREATE SEQUENCE acesso_codigoacesso_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.acesso_codigoacesso_seq OWNER TO cultbook;

--
-- TOC entry 1937 (class 0 OID 0)
-- Dependencies: 1549
-- Name: acesso_codigoacesso_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: cultbook
--

ALTER SEQUENCE acesso_codigoacesso_seq OWNED BY acesso.codigoacesso;


--
-- TOC entry 1938 (class 0 OID 0)
-- Dependencies: 1549
-- Name: acesso_codigoacesso_seq; Type: SEQUENCE SET; Schema: public; Owner: cultbook
--

SELECT pg_catalog.setval('acesso_codigoacesso_seq', 48, true);


--
-- TOC entry 1550 (class 1259 OID 41636)
-- Dependencies: 6
-- Name: acesso_nivel; Type: TABLE; Schema: public; Owner: cultbook; Tablespace: 
--

CREATE TABLE acesso_nivel (
    codigoacesso integer NOT NULL,
    codigoniveldeacesso integer NOT NULL
);


ALTER TABLE public.acesso_nivel OWNER TO cultbook;

--
-- TOC entry 1551 (class 1259 OID 41639)
-- Dependencies: 6
-- Name: autor; Type: TABLE; Schema: public; Owner: cultbook; Tablespace: 
--

CREATE TABLE autor (
    codigoautor integer NOT NULL,
    nomecompleto character varying(90) NOT NULL,
    datanascimento date NOT NULL,
    cpf character varying(11) NOT NULL,
    sexo character varying NOT NULL,
    rua character varying NOT NULL,
    bairro character varying NOT NULL,
    cidade character varying NOT NULL,
    estado character varying NOT NULL,
    sobrenome character varying(20),
    cep character varying,
    status character varying(20) NOT NULL
);


ALTER TABLE public.autor OWNER TO cultbook;

--
-- TOC entry 1552 (class 1259 OID 41645)
-- Dependencies: 1551 6
-- Name: autor_codigoautor_seq; Type: SEQUENCE; Schema: public; Owner: cultbook
--

CREATE SEQUENCE autor_codigoautor_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.autor_codigoautor_seq OWNER TO cultbook;

--
-- TOC entry 1939 (class 0 OID 0)
-- Dependencies: 1552
-- Name: autor_codigoautor_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: cultbook
--

ALTER SEQUENCE autor_codigoautor_seq OWNED BY autor.codigoautor;


--
-- TOC entry 1940 (class 0 OID 0)
-- Dependencies: 1552
-- Name: autor_codigoautor_seq; Type: SEQUENCE SET; Schema: public; Owner: cultbook
--

SELECT pg_catalog.setval('autor_codigoautor_seq', 22, true);


--
-- TOC entry 1553 (class 1259 OID 41647)
-- Dependencies: 6
-- Name: cliente; Type: TABLE; Schema: public; Owner: cultbook; Tablespace: 
--

CREATE TABLE cliente (
    codigocliente integer NOT NULL,
    email character varying,
    telefone character varying(25) NOT NULL,
    desde date,
    nomecompleto character varying,
    datanascimento date,
    cpf character varying,
    sexo character varying,
    estadocivil character varying,
    rua character varying,
    bairro character varying,
    cidade character varying,
    estado character varying,
    senha character varying,
    ultimoacesso timestamp without time zone NOT NULL,
    status character varying,
    cep character varying,
    codigoniveldeacesso integer NOT NULL
);


ALTER TABLE public.cliente OWNER TO cultbook;

--
-- TOC entry 1554 (class 1259 OID 41653)
-- Dependencies: 6 1553
-- Name: cliente_codigocliente_seq; Type: SEQUENCE; Schema: public; Owner: cultbook
--

CREATE SEQUENCE cliente_codigocliente_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cliente_codigocliente_seq OWNER TO cultbook;

--
-- TOC entry 1941 (class 0 OID 0)
-- Dependencies: 1554
-- Name: cliente_codigocliente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: cultbook
--

ALTER SEQUENCE cliente_codigocliente_seq OWNED BY cliente.codigocliente;


--
-- TOC entry 1942 (class 0 OID 0)
-- Dependencies: 1554
-- Name: cliente_codigocliente_seq; Type: SEQUENCE SET; Schema: public; Owner: cultbook
--

SELECT pg_catalog.setval('cliente_codigocliente_seq', 16, true);


--
-- TOC entry 1555 (class 1259 OID 41655)
-- Dependencies: 6
-- Name: editora; Type: TABLE; Schema: public; Owner: cultbook; Tablespace: 
--

CREATE TABLE editora (
    codigoeditora integer NOT NULL,
    nomeeditora character varying(80) NOT NULL,
    cnpj character varying(15) NOT NULL,
    obs text,
    status character varying(20)
);


ALTER TABLE public.editora OWNER TO cultbook;

--
-- TOC entry 1556 (class 1259 OID 41661)
-- Dependencies: 1555 6
-- Name: editora_codigoeditora_seq; Type: SEQUENCE; Schema: public; Owner: cultbook
--

CREATE SEQUENCE editora_codigoeditora_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.editora_codigoeditora_seq OWNER TO cultbook;

--
-- TOC entry 1943 (class 0 OID 0)
-- Dependencies: 1556
-- Name: editora_codigoeditora_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: cultbook
--

ALTER SEQUENCE editora_codigoeditora_seq OWNED BY editora.codigoeditora;


--
-- TOC entry 1944 (class 0 OID 0)
-- Dependencies: 1556
-- Name: editora_codigoeditora_seq; Type: SEQUENCE SET; Schema: public; Owner: cultbook
--

SELECT pg_catalog.setval('editora_codigoeditora_seq', 8, true);


--
-- TOC entry 1557 (class 1259 OID 41663)
-- Dependencies: 6
-- Name: entrega; Type: TABLE; Schema: public; Owner: cultbook; Tablespace: 
--

CREATE TABLE entrega (
    codigoentrega integer NOT NULL,
    codigopedido integer NOT NULL,
    frete numeric,
    nomecompleto character varying NOT NULL,
    rua character varying,
    bairro character varying,
    cidade character varying,
    estado character varying NOT NULL,
    cep character varying NOT NULL,
    telefone character varying
);


ALTER TABLE public.entrega OWNER TO cultbook;

--
-- TOC entry 1558 (class 1259 OID 41669)
-- Dependencies: 6 1557
-- Name: entrega_codigoentrega_seq; Type: SEQUENCE; Schema: public; Owner: cultbook
--

CREATE SEQUENCE entrega_codigoentrega_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.entrega_codigoentrega_seq OWNER TO cultbook;

--
-- TOC entry 1945 (class 0 OID 0)
-- Dependencies: 1558
-- Name: entrega_codigoentrega_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: cultbook
--

ALTER SEQUENCE entrega_codigoentrega_seq OWNED BY entrega.codigoentrega;


--
-- TOC entry 1946 (class 0 OID 0)
-- Dependencies: 1558
-- Name: entrega_codigoentrega_seq; Type: SEQUENCE SET; Schema: public; Owner: cultbook
--

SELECT pg_catalog.setval('entrega_codigoentrega_seq', 11, true);


--
-- TOC entry 1559 (class 1259 OID 41671)
-- Dependencies: 6
-- Name: funcionario; Type: TABLE; Schema: public; Owner: cultbook; Tablespace: 
--

CREATE TABLE funcionario (
    codigofuncionario integer NOT NULL,
    cargo character varying NOT NULL,
    dataadmissao date NOT NULL,
    datademissao date,
    nomecompleto character varying NOT NULL,
    datanascimento date NOT NULL,
    cpf character varying NOT NULL,
    sexo character varying NOT NULL,
    rua character varying NOT NULL,
    bairro character varying NOT NULL,
    cidade character varying NOT NULL,
    estado character varying NOT NULL,
    senha character varying NOT NULL,
    ultimoacesso timestamp without time zone NOT NULL,
    status character varying NOT NULL,
    cep character varying,
    codigoniveldeacesso integer NOT NULL
);


ALTER TABLE public.funcionario OWNER TO cultbook;

--
-- TOC entry 1560 (class 1259 OID 41677)
-- Dependencies: 1559 6
-- Name: funcionario_codigofuncionario_seq; Type: SEQUENCE; Schema: public; Owner: cultbook
--

CREATE SEQUENCE funcionario_codigofuncionario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.funcionario_codigofuncionario_seq OWNER TO cultbook;

--
-- TOC entry 1947 (class 0 OID 0)
-- Dependencies: 1560
-- Name: funcionario_codigofuncionario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: cultbook
--

ALTER SEQUENCE funcionario_codigofuncionario_seq OWNED BY funcionario.codigofuncionario;


--
-- TOC entry 1948 (class 0 OID 0)
-- Dependencies: 1560
-- Name: funcionario_codigofuncionario_seq; Type: SEQUENCE SET; Schema: public; Owner: cultbook
--

SELECT pg_catalog.setval('funcionario_codigofuncionario_seq', 6, true);


--
-- TOC entry 1561 (class 1259 OID 41679)
-- Dependencies: 6
-- Name: item_pedido; Type: TABLE; Schema: public; Owner: cultbook; Tablespace: 
--

CREATE TABLE item_pedido (
    codigopedido integer NOT NULL,
    codigoproduto integer NOT NULL,
    quantidade integer NOT NULL
);


ALTER TABLE public.item_pedido OWNER TO cultbook;

--
-- TOC entry 1562 (class 1259 OID 41682)
-- Dependencies: 6
-- Name: livro; Type: TABLE; Schema: public; Owner: cultbook; Tablespace: 
--

CREATE TABLE livro (
    codigoproduto integer NOT NULL,
    codigoeditora integer NOT NULL,
    isbn character varying(100) NOT NULL,
    titulo character varying(150) NOT NULL,
    estoque integer NOT NULL,
    assunto character varying NOT NULL,
    figura character varying(150),
    anopublicacao character varying(4) NOT NULL,
    pagprincipal character(3),
    status character varying(20)
);


ALTER TABLE public.livro OWNER TO cultbook;

--
-- TOC entry 1563 (class 1259 OID 41688)
-- Dependencies: 6
-- Name: livro_autor; Type: TABLE; Schema: public; Owner: cultbook; Tablespace: 
--

CREATE TABLE livro_autor (
    codigoautor integer NOT NULL,
    codigoproduto integer NOT NULL
);


ALTER TABLE public.livro_autor OWNER TO cultbook;

--
-- TOC entry 1564 (class 1259 OID 41691)
-- Dependencies: 6
-- Name: niveldeacesso; Type: TABLE; Schema: public; Owner: cultbook; Tablespace: 
--

CREATE TABLE niveldeacesso (
    codigoniveldeacesso integer NOT NULL,
    niveldeacesso character varying(45) NOT NULL
);


ALTER TABLE public.niveldeacesso OWNER TO cultbook;

--
-- TOC entry 1565 (class 1259 OID 41694)
-- Dependencies: 6 1564
-- Name: niveldeacesso_codigoniveldeacesso_seq_1; Type: SEQUENCE; Schema: public; Owner: cultbook
--

CREATE SEQUENCE niveldeacesso_codigoniveldeacesso_seq_1
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.niveldeacesso_codigoniveldeacesso_seq_1 OWNER TO cultbook;

--
-- TOC entry 1949 (class 0 OID 0)
-- Dependencies: 1565
-- Name: niveldeacesso_codigoniveldeacesso_seq_1; Type: SEQUENCE OWNED BY; Schema: public; Owner: cultbook
--

ALTER SEQUENCE niveldeacesso_codigoniveldeacesso_seq_1 OWNED BY niveldeacesso.codigoniveldeacesso;


--
-- TOC entry 1950 (class 0 OID 0)
-- Dependencies: 1565
-- Name: niveldeacesso_codigoniveldeacesso_seq_1; Type: SEQUENCE SET; Schema: public; Owner: cultbook
--

SELECT pg_catalog.setval('niveldeacesso_codigoniveldeacesso_seq_1', 4, true);


--
-- TOC entry 1566 (class 1259 OID 41696)
-- Dependencies: 6
-- Name: pagamento; Type: TABLE; Schema: public; Owner: cultbook; Tablespace: 
--

CREATE TABLE pagamento (
    codigopagamento integer NOT NULL,
    codigopedido integer NOT NULL,
    valortotal numeric NOT NULL
);


ALTER TABLE public.pagamento OWNER TO cultbook;

--
-- TOC entry 1567 (class 1259 OID 41702)
-- Dependencies: 1566 6
-- Name: pagamento_codigopagamento_seq; Type: SEQUENCE; Schema: public; Owner: cultbook
--

CREATE SEQUENCE pagamento_codigopagamento_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pagamento_codigopagamento_seq OWNER TO cultbook;

--
-- TOC entry 1951 (class 0 OID 0)
-- Dependencies: 1567
-- Name: pagamento_codigopagamento_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: cultbook
--

ALTER SEQUENCE pagamento_codigopagamento_seq OWNED BY pagamento.codigopagamento;


--
-- TOC entry 1952 (class 0 OID 0)
-- Dependencies: 1567
-- Name: pagamento_codigopagamento_seq; Type: SEQUENCE SET; Schema: public; Owner: cultbook
--

SELECT pg_catalog.setval('pagamento_codigopagamento_seq', 11, true);


--
-- TOC entry 1568 (class 1259 OID 41704)
-- Dependencies: 6
-- Name: pagamentoboleto; Type: TABLE; Schema: public; Owner: cultbook; Tablespace: 
--

CREATE TABLE pagamentoboleto (
    codigopagamento integer NOT NULL,
    numerodocumento character varying(15) NOT NULL,
    vencimento date,
    cedente character varying(100)
);


ALTER TABLE public.pagamentoboleto OWNER TO cultbook;

--
-- TOC entry 1569 (class 1259 OID 41707)
-- Dependencies: 6
-- Name: pagamentocartao; Type: TABLE; Schema: public; Owner: cultbook; Tablespace: 
--

CREATE TABLE pagamentocartao (
    codigopagamento integer NOT NULL,
    numerocartao character varying(20),
    validadecartao character varying(20),
    bandeira character varying(45),
    codigoseguranca character varying(3),
    nometitular character varying(25),
    numeroparcelas integer,
    valorparcela numeric
);


ALTER TABLE public.pagamentocartao OWNER TO cultbook;

--
-- TOC entry 1570 (class 1259 OID 41713)
-- Dependencies: 6
-- Name: pedido; Type: TABLE; Schema: public; Owner: cultbook; Tablespace: 
--

CREATE TABLE pedido (
    codigopedido integer NOT NULL,
    codigocliente integer NOT NULL,
    descricao character varying(150),
    statuspedido character varying(50) NOT NULL,
    datapedido date
);


ALTER TABLE public.pedido OWNER TO cultbook;

--
-- TOC entry 1571 (class 1259 OID 41716)
-- Dependencies: 6 1570
-- Name: pedido_codigopedido_seq; Type: SEQUENCE; Schema: public; Owner: cultbook
--

CREATE SEQUENCE pedido_codigopedido_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pedido_codigopedido_seq OWNER TO cultbook;

--
-- TOC entry 1953 (class 0 OID 0)
-- Dependencies: 1571
-- Name: pedido_codigopedido_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: cultbook
--

ALTER SEQUENCE pedido_codigopedido_seq OWNED BY pedido.codigopedido;


--
-- TOC entry 1954 (class 0 OID 0)
-- Dependencies: 1571
-- Name: pedido_codigopedido_seq; Type: SEQUENCE SET; Schema: public; Owner: cultbook
--

SELECT pg_catalog.setval('pedido_codigopedido_seq', 11, true);


--
-- TOC entry 1572 (class 1259 OID 41718)
-- Dependencies: 6
-- Name: produto; Type: TABLE; Schema: public; Owner: cultbook; Tablespace: 
--

CREATE TABLE produto (
    codigoproduto integer NOT NULL,
    descricaocurta character varying(150),
    descricaolonga text NOT NULL,
    preco numeric
);


ALTER TABLE public.produto OWNER TO cultbook;

--
-- TOC entry 1573 (class 1259 OID 41724)
-- Dependencies: 6 1572
-- Name: produto_codigoproduto_seq; Type: SEQUENCE; Schema: public; Owner: cultbook
--

CREATE SEQUENCE produto_codigoproduto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.produto_codigoproduto_seq OWNER TO cultbook;

--
-- TOC entry 1955 (class 0 OID 0)
-- Dependencies: 1573
-- Name: produto_codigoproduto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: cultbook
--

ALTER SEQUENCE produto_codigoproduto_seq OWNED BY produto.codigoproduto;


--
-- TOC entry 1956 (class 0 OID 0)
-- Dependencies: 1573
-- Name: produto_codigoproduto_seq; Type: SEQUENCE SET; Schema: public; Owner: cultbook
--

SELECT pg_catalog.setval('produto_codigoproduto_seq', 35, true);


--
-- TOC entry 1851 (class 2604 OID 41726)
-- Dependencies: 1549 1548
-- Name: codigoacesso; Type: DEFAULT; Schema: public; Owner: cultbook
--

ALTER TABLE acesso ALTER COLUMN codigoacesso SET DEFAULT nextval('acesso_codigoacesso_seq'::regclass);


--
-- TOC entry 1852 (class 2604 OID 41727)
-- Dependencies: 1552 1551
-- Name: codigoautor; Type: DEFAULT; Schema: public; Owner: cultbook
--

ALTER TABLE autor ALTER COLUMN codigoautor SET DEFAULT nextval('autor_codigoautor_seq'::regclass);


--
-- TOC entry 1853 (class 2604 OID 41728)
-- Dependencies: 1554 1553
-- Name: codigocliente; Type: DEFAULT; Schema: public; Owner: cultbook
--

ALTER TABLE cliente ALTER COLUMN codigocliente SET DEFAULT nextval('cliente_codigocliente_seq'::regclass);


--
-- TOC entry 1854 (class 2604 OID 41729)
-- Dependencies: 1556 1555
-- Name: codigoeditora; Type: DEFAULT; Schema: public; Owner: cultbook
--

ALTER TABLE editora ALTER COLUMN codigoeditora SET DEFAULT nextval('editora_codigoeditora_seq'::regclass);


--
-- TOC entry 1855 (class 2604 OID 41730)
-- Dependencies: 1558 1557
-- Name: codigoentrega; Type: DEFAULT; Schema: public; Owner: cultbook
--

ALTER TABLE entrega ALTER COLUMN codigoentrega SET DEFAULT nextval('entrega_codigoentrega_seq'::regclass);


--
-- TOC entry 1856 (class 2604 OID 41731)
-- Dependencies: 1560 1559
-- Name: codigofuncionario; Type: DEFAULT; Schema: public; Owner: cultbook
--

ALTER TABLE funcionario ALTER COLUMN codigofuncionario SET DEFAULT nextval('funcionario_codigofuncionario_seq'::regclass);


--
-- TOC entry 1857 (class 2604 OID 41732)
-- Dependencies: 1565 1564
-- Name: codigoniveldeacesso; Type: DEFAULT; Schema: public; Owner: cultbook
--

ALTER TABLE niveldeacesso ALTER COLUMN codigoniveldeacesso SET DEFAULT nextval('niveldeacesso_codigoniveldeacesso_seq_1'::regclass);


--
-- TOC entry 1858 (class 2604 OID 41733)
-- Dependencies: 1567 1566
-- Name: codigopagamento; Type: DEFAULT; Schema: public; Owner: cultbook
--

ALTER TABLE pagamento ALTER COLUMN codigopagamento SET DEFAULT nextval('pagamento_codigopagamento_seq'::regclass);


--
-- TOC entry 1859 (class 2604 OID 41734)
-- Dependencies: 1571 1570
-- Name: codigopedido; Type: DEFAULT; Schema: public; Owner: cultbook
--

ALTER TABLE pedido ALTER COLUMN codigopedido SET DEFAULT nextval('pedido_codigopedido_seq'::regclass);


--
-- TOC entry 1860 (class 2604 OID 41735)
-- Dependencies: 1573 1572
-- Name: codigoproduto; Type: DEFAULT; Schema: public; Owner: cultbook
--

ALTER TABLE produto ALTER COLUMN codigoproduto SET DEFAULT nextval('produto_codigoproduto_seq'::regclass);


--
-- TOC entry 1916 (class 0 OID 41631)
-- Dependencies: 1548
-- Data for Name: acesso; Type: TABLE DATA; Schema: public; Owner: cultbook
--

INSERT INTO acesso VALUES (1, 'cadastrarCliente', 'Cadastrar Cliente');
INSERT INTO acesso VALUES (2, 'consultarCliente', 'Consultar Cliente');
INSERT INTO acesso VALUES (3, 'atualizarCliente', 'Atualizar Cliente');
INSERT INTO acesso VALUES (5, 'excluirCliente', 'Excluir Cliente');
INSERT INTO acesso VALUES (6, 'cadastrarFuncionario', 'Cadastrar Funcionario');
INSERT INTO acesso VALUES (7, 'consultarFuncionario', 'Consultar Funcionario');
INSERT INTO acesso VALUES (8, 'atualizarFuncionario', 'Atualizar Funcionario');
INSERT INTO acesso VALUES (10, 'excluirFuncionario', 'Excluir Funcionario');
INSERT INTO acesso VALUES (11, 'cadastrarEditora', 'Cadastrar Editora');
INSERT INTO acesso VALUES (12, 'consultarEditora', 'Consultar Editora');
INSERT INTO acesso VALUES (13, 'atualizarEditora', 'Atualizar Editora');
INSERT INTO acesso VALUES (15, 'excluirEditora', 'Excluir Editora');
INSERT INTO acesso VALUES (16, 'cadastrarAutor', 'Cadastrar Autor');
INSERT INTO acesso VALUES (17, 'consultarAutor', 'Consultar Autor');
INSERT INTO acesso VALUES (18, 'atualizarAutor', 'Atualizar Autor');
INSERT INTO acesso VALUES (20, 'excluirAutor', 'Excluir Autor');
INSERT INTO acesso VALUES (21, 'cadastrarLivro', 'Cadastrar Livro');
INSERT INTO acesso VALUES (23, 'atualizarLivro', 'Atualizar Livro');
INSERT INTO acesso VALUES (24, 'consultarLivro', 'Consultar Livro');
INSERT INTO acesso VALUES (25, 'excluirLivro', 'Excluir Livro');
INSERT INTO acesso VALUES (26, 'autorizarAcesso', 'Autorizar Acessos');
INSERT INTO acesso VALUES (27, 'atualizarAcesso', 'Atualizar Acessos');
INSERT INTO acesso VALUES (29, 'consultarNivelAcesso', 'Consultar Níveis de Acesso');
INSERT INTO acesso VALUES (30, 'cadastrarNivelAcesso', 'Cadastrar Nível de Acesso');
INSERT INTO acesso VALUES (31, 'atualizarNivelAcesso', 'Atualizar Nível de Acesso');
INSERT INTO acesso VALUES (32, 'excluirNivelAcesso', 'Excluir Nível de Acesso');
INSERT INTO acesso VALUES (33, 'gravarPedido', 'Efetivar Pedido');
INSERT INTO acesso VALUES (35, 'consultarMeusPedidos', 'Consultar Meus Pedidos');
INSERT INTO acesso VALUES (38, 'atualizarFuncionarioPainel', 'Editar Dados Funcionario');
INSERT INTO acesso VALUES (36, 'atualizarClientePainel', 'Editar Dados Cliente');
INSERT INTO acesso VALUES (45, 'exibirPedidosEntreAsDatas', 'Exibir Pedidos por período');
INSERT INTO acesso VALUES (47, 'exibirPosicaoLivros', 'Exibir a posição dos livros');
INSERT INTO acesso VALUES (46, 'exibirPosicaoPedidos', 'Exibir posição Pedidos');
INSERT INTO acesso VALUES (44, 'consultarRelatorios', 'Consultar Relatorios');
INSERT INTO acesso VALUES (42, 'exibirItensPCliente', 'Consultar Ped. Cliente');
INSERT INTO acesso VALUES (40, 'exibirItensPedido', 'Consultar Itens do Pedido');
INSERT INTO acesso VALUES (43, 'exibirPedido', 'Exibir Pedido');
INSERT INTO acesso VALUES (48, 'exibirLivrosDosPedidos', 'Exibir Livros Pedidos');
INSERT INTO acesso VALUES (4, 'editarCliente', 'Adicionar Cliente');
INSERT INTO acesso VALUES (9, 'editarFuncionario', 'Adcionar Funcionario');
INSERT INTO acesso VALUES (14, 'editarEditora', 'Adcionar Editora');
INSERT INTO acesso VALUES (19, 'editarAutor', 'Adicionar Autor');
INSERT INTO acesso VALUES (22, 'editarLivro', 'Adicionar Livro');
INSERT INTO acesso VALUES (28, 'editarNivelAcesso', 'Adicionar Nível de Acesso');
INSERT INTO acesso VALUES (39, 'consultarTodosPedidos', 'Consultar Pedidos');


--
-- TOC entry 1917 (class 0 OID 41636)
-- Dependencies: 1550
-- Data for Name: acesso_nivel; Type: TABLE DATA; Schema: public; Owner: cultbook
--

INSERT INTO acesso_nivel VALUES (25, 2);
INSERT INTO acesso_nivel VALUES (24, 2);
INSERT INTO acesso_nivel VALUES (23, 2);
INSERT INTO acesso_nivel VALUES (22, 2);
INSERT INTO acesso_nivel VALUES (21, 2);
INSERT INTO acesso_nivel VALUES (20, 2);
INSERT INTO acesso_nivel VALUES (9, 2);
INSERT INTO acesso_nivel VALUES (8, 2);
INSERT INTO acesso_nivel VALUES (19, 2);
INSERT INTO acesso_nivel VALUES (7, 2);
INSERT INTO acesso_nivel VALUES (18, 2);
INSERT INTO acesso_nivel VALUES (6, 2);
INSERT INTO acesso_nivel VALUES (17, 2);
INSERT INTO acesso_nivel VALUES (5, 2);
INSERT INTO acesso_nivel VALUES (16, 2);
INSERT INTO acesso_nivel VALUES (48, 2);
INSERT INTO acesso_nivel VALUES (4, 2);
INSERT INTO acesso_nivel VALUES (15, 2);
INSERT INTO acesso_nivel VALUES (47, 2);
INSERT INTO acesso_nivel VALUES (3, 2);
INSERT INTO acesso_nivel VALUES (14, 2);
INSERT INTO acesso_nivel VALUES (46, 2);
INSERT INTO acesso_nivel VALUES (13, 2);
INSERT INTO acesso_nivel VALUES (2, 2);
INSERT INTO acesso_nivel VALUES (45, 2);
INSERT INTO acesso_nivel VALUES (1, 2);
INSERT INTO acesso_nivel VALUES (12, 2);
INSERT INTO acesso_nivel VALUES (44, 2);
INSERT INTO acesso_nivel VALUES (11, 2);
INSERT INTO acesso_nivel VALUES (43, 2);
INSERT INTO acesso_nivel VALUES (10, 2);
INSERT INTO acesso_nivel VALUES (42, 2);
INSERT INTO acesso_nivel VALUES (40, 2);
INSERT INTO acesso_nivel VALUES (39, 2);
INSERT INTO acesso_nivel VALUES (38, 2);
INSERT INTO acesso_nivel VALUES (36, 2);
INSERT INTO acesso_nivel VALUES (40, 4);
INSERT INTO acesso_nivel VALUES (36, 4);
INSERT INTO acesso_nivel VALUES (35, 4);
INSERT INTO acesso_nivel VALUES (33, 4);
INSERT INTO acesso_nivel VALUES (39, 3);
INSERT INTO acesso_nivel VALUES (36, 3);
INSERT INTO acesso_nivel VALUES (19, 3);
INSERT INTO acesso_nivel VALUES (18, 3);
INSERT INTO acesso_nivel VALUES (17, 3);
INSERT INTO acesso_nivel VALUES (48, 3);
INSERT INTO acesso_nivel VALUES (16, 3);
INSERT INTO acesso_nivel VALUES (47, 3);
INSERT INTO acesso_nivel VALUES (15, 3);
INSERT INTO acesso_nivel VALUES (46, 3);
INSERT INTO acesso_nivel VALUES (45, 3);
INSERT INTO acesso_nivel VALUES (14, 3);
INSERT INTO acesso_nivel VALUES (13, 3);
INSERT INTO acesso_nivel VALUES (44, 3);
INSERT INTO acesso_nivel VALUES (12, 3);
INSERT INTO acesso_nivel VALUES (43, 3);
INSERT INTO acesso_nivel VALUES (42, 3);
INSERT INTO acesso_nivel VALUES (11, 3);
INSERT INTO acesso_nivel VALUES (40, 3);
INSERT INTO acesso_nivel VALUES (5, 3);
INSERT INTO acesso_nivel VALUES (25, 3);
INSERT INTO acesso_nivel VALUES (4, 3);
INSERT INTO acesso_nivel VALUES (24, 3);
INSERT INTO acesso_nivel VALUES (3, 3);
INSERT INTO acesso_nivel VALUES (23, 3);
INSERT INTO acesso_nivel VALUES (2, 3);
INSERT INTO acesso_nivel VALUES (1, 3);
INSERT INTO acesso_nivel VALUES (22, 3);
INSERT INTO acesso_nivel VALUES (21, 3);
INSERT INTO acesso_nivel VALUES (20, 3);
INSERT INTO acesso_nivel VALUES (29, 1);
INSERT INTO acesso_nivel VALUES (28, 1);
INSERT INTO acesso_nivel VALUES (27, 1);
INSERT INTO acesso_nivel VALUES (26, 1);
INSERT INTO acesso_nivel VALUES (25, 1);
INSERT INTO acesso_nivel VALUES (24, 1);
INSERT INTO acesso_nivel VALUES (23, 1);
INSERT INTO acesso_nivel VALUES (22, 1);
INSERT INTO acesso_nivel VALUES (21, 1);
INSERT INTO acesso_nivel VALUES (20, 1);
INSERT INTO acesso_nivel VALUES (9, 1);
INSERT INTO acesso_nivel VALUES (8, 1);
INSERT INTO acesso_nivel VALUES (19, 1);
INSERT INTO acesso_nivel VALUES (7, 1);
INSERT INTO acesso_nivel VALUES (18, 1);
INSERT INTO acesso_nivel VALUES (17, 1);
INSERT INTO acesso_nivel VALUES (6, 1);
INSERT INTO acesso_nivel VALUES (16, 1);
INSERT INTO acesso_nivel VALUES (48, 1);
INSERT INTO acesso_nivel VALUES (5, 1);
INSERT INTO acesso_nivel VALUES (15, 1);
INSERT INTO acesso_nivel VALUES (4, 1);
INSERT INTO acesso_nivel VALUES (47, 1);
INSERT INTO acesso_nivel VALUES (46, 1);
INSERT INTO acesso_nivel VALUES (14, 1);
INSERT INTO acesso_nivel VALUES (3, 1);
INSERT INTO acesso_nivel VALUES (13, 1);
INSERT INTO acesso_nivel VALUES (45, 1);
INSERT INTO acesso_nivel VALUES (2, 1);
INSERT INTO acesso_nivel VALUES (1, 1);
INSERT INTO acesso_nivel VALUES (44, 1);
INSERT INTO acesso_nivel VALUES (12, 1);
INSERT INTO acesso_nivel VALUES (43, 1);
INSERT INTO acesso_nivel VALUES (11, 1);
INSERT INTO acesso_nivel VALUES (42, 1);
INSERT INTO acesso_nivel VALUES (10, 1);
INSERT INTO acesso_nivel VALUES (40, 1);
INSERT INTO acesso_nivel VALUES (39, 1);
INSERT INTO acesso_nivel VALUES (38, 1);
INSERT INTO acesso_nivel VALUES (36, 1);
INSERT INTO acesso_nivel VALUES (32, 1);
INSERT INTO acesso_nivel VALUES (31, 1);
INSERT INTO acesso_nivel VALUES (30, 1);


--
-- TOC entry 1918 (class 0 OID 41639)
-- Dependencies: 1551
-- Data for Name: autor; Type: TABLE DATA; Schema: public; Owner: cultbook
--

INSERT INTO autor VALUES (1, 'Cindy Tutsch', '1979-05-25', '07504022705', 'feminino', 'rua do brasil s/n', 'rio das ostras', 'rio de janeiro', 'AL', 'Cindy Tutsch', '21210000', 'Ativo');
INSERT INTO autor VALUES (21, 'Douglas, William', '1979-05-25', '07504022725', 'masculino', 'rua do brasil s/n', 'rio das ostras', 'rio de janeiro', 'AL', 'Douglas, William', '21210000', 'Inativo');
INSERT INTO autor VALUES (22, 'Marcela Silva', '1980-01-19', '05513614766', 'masculino', 'rua f bloco b-3 ap-405', 'vicente de carvalho', 'rio de janeiro', 'AP', 'MAnders', '21210000', 'Ativo');
INSERT INTO autor VALUES (2, 'Ben S. Carson', '1979-05-25', '07504022706', 'masculino', 'rua do brasil s/n', 'rio das ostras', 'rio de janeiro', 'AL', 'Ben S. Carson', '21210000', 'Ativo');
INSERT INTO autor VALUES (3, 'Marcos Blanco
', '1979-05-25', '07504022707', 'masculino', 'rua do brasil s/n', 'rio das ostras', 'rio de janeiro', 'AL', 'Marcos Blanco
', '21210000', 'Ativo');
INSERT INTO autor VALUES (4, 'Trudy J. Morgan-Cole', '1979-05-25', '07504022708', 'masculino', 'rua do brasil s/n', 'rio das ostras', 'rio de janeiro', 'AL', 'Trudy J. Morgan-Cole', '21210000', 'Ativo');
INSERT INTO autor VALUES (5, 'Derek J. Morris', '1979-05-25', '07504022709', 'masculino', 'rua do brasil s/n', 'rio das ostras', 'rio de janeiro', 'AL', 'Derek J. Morris', '21210000', 'Ativo');
INSERT INTO autor VALUES (6, 'Karl Haffner', '1979-05-25', '07504022710', 'masculino', 'rua do brasil s/n', 'rio das ostras', 'rio de janeiro', 'AL', 'Karl Haffner', '21210000', 'Ativo');
INSERT INTO autor VALUES (7, 'Fernanda Lima', '1979-05-25', '07504022711', 'masculino', 'rua do brasil s/n', 'rio das ostras', 'rio de janeiro', 'AL', 'Fernanda Lima', '21210000', 'Ativo');
INSERT INTO autor VALUES (9, 'Sandro Silva', '1979-05-25', '07504022713', 'masculino', 'rua do brasil s/n', 'rio das ostras', 'rio de janeiro', 'AL', 'Sandro S.', '21210000', 'Ativo');
INSERT INTO autor VALUES (11, 'Erich Gama', '1979-05-25', '07504022715', 'masculino', 'rua do brasil s/n', 'rio das ostras', 'rio de janeiro', 'AL', 'Erich Gama', '21210000', 'Ativo');
INSERT INTO autor VALUES (14, 'João Vicente Pereyra', '1979-05-25', '07504022718', 'masculino', 'rua do brasil s/n', 'rio das ostras', 'rio de janeiro', 'AL', 'João Vicente Pereyra', '21210000', 'Ativo');
INSERT INTO autor VALUES (17, 'Boothman, Nicholas', '1979-05-25', '07504022721', 'masculino', 'rua do brasil s/n', 'rio das ostras', 'rio de janeiro', 'AL', 'Boothman, Nicholas', '21210000', 'Ativo');
INSERT INTO autor VALUES (19, 'Noel, Alyson
', '1979-05-25', '07504022723', 'masculino', 'rua do brasil s/n', 'rio das ostras', 'rio de janeiro', 'AL', 'Noel, Alyson
', '21210000', 'Ativo');
INSERT INTO autor VALUES (8, 'Troy Fitzgerald', '1979-05-25', '07504022712', 'feminino', 'rua do brasil s/n', 'rio das ostras', 'rio de janeiro', 'AL', 'Troy Fitzgerald', '21210000', 'Ativo');
INSERT INTO autor VALUES (10, 'Elmasri Navathe', '1979-05-25', '07504022714', 'feminino', 'rua do brasil s/n', 'rio das ostras', 'rio de janeiro', 'AL', 'Elmasri N.', '21210000', 'Ativo');
INSERT INTO autor VALUES (12, 'Ralph Johnson', '1979-05-25', '07504022716', 'feminino', 'rua do brasil s/n', 'rio das ostras', 'rio de janeiro', 'AL', 'Ralph Johnson', '21210000', 'Ativo');
INSERT INTO autor VALUES (15, 'Neila D. Oliveira', '1979-05-25', '07504022719', 'feminino', 'rua do brasil s/n', 'rio das ostras', 'rio de janeiro', 'AL', 'Neila D. Oliveira', '21210000', 'Ativo');
INSERT INTO autor VALUES (16, 'Denis Cruz ', '1979-05-25', '07504022720', 'feminino', 'rua do brasil s/n', 'rio das ostras', 'rio de janeiro', 'AL', 'Denis Cruz ', '21210000', 'Ativo');
INSERT INTO autor VALUES (18, 'Ward, J. R', '1979-05-25', '07504022722', 'masculino', 'rua do brasil s/n', 'rio das ostras', 'rio de janeiro', 'AL', 'Ward, J. R', '21210000', 'Ativo');
INSERT INTO autor VALUES (13, 'Rubem E. Ferreira', '1979-05-25', '07504022717', 'masculino', 'rua do brasil s/n', 'rio das ostras', 'rio de janeiro', 'AL', 'Rubem E. F.', '21210000', 'Ativo');
INSERT INTO autor VALUES (20, 'Bellos, Alex', '1979-05-25', '07504022724', 'masculino', 'rua do brasil s/n', 'rio das ostras', 'rio de janeiro', 'AL', 'Bellos, Alex', '21210000', 'Inativo');


--
-- TOC entry 1919 (class 0 OID 41647)
-- Dependencies: 1553
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: cultbook
--

INSERT INTO cliente VALUES (11, 'carlosanders@gmail.com', '2121046446', '2011-05-27', 'Thiago Valverde', '1979-01-19', '07504022715', 'masculino', 'Solteiro', 'Avenida Vicente de Carvalho, 1086 - Rua F bloco B-3 Ap-405', 'Vicente de Cavalho', 'Rio de Janeiro', 'RJ', '12345678', '2011-05-27 23:37:09.49', 'Ativo', '21210000', 4);
INSERT INTO cliente VALUES (7, 'carlosanders@gmail.com', '2121046446', '2011-05-27', 'Filipe Cavalcante', '1979-01-19', '07504022711', 'masculino', 'Casado', 'Avenida Vicente de Carvalho, 1086 - Rua F bloco B-3 Ap-405', 'Vicente de Cavalho', 'Rio de Janeiro', 'RJ', '12345678', '2011-05-27 23:37:09.49', 'Ativo', '21210000', 4);
INSERT INTO cliente VALUES (9, 'carlosanders@gmail.com', '2121046446', '2011-05-27', 'José Elísio', '1979-01-19', '07504022713', 'masculino', 'Solteiro', 'Avenida Vicente de Carvalho, 1086 - Rua F bloco B-3 Ap-405', 'Vicente de Cavalho', 'Rio de Janeiro', 'RJ', '12345678', '2011-05-27 23:37:09.49', 'Ativo', '21210000', 4);
INSERT INTO cliente VALUES (3, 'carlosanders@gmail.com', '2121046446', '2011-05-27', 'Silva Anders', '1979-01-19', '07504022707', 'masculino', 'Divorciado', 'Avenida Vicente de Carvalho, 1086 - Rua F bloco B-3 Ap-405', 'Vicente de Cavalho', 'Rio de Janeiro', 'RJ', '12345678', '2011-05-29 08:13:57.016', 'Ativo', '21210000', 4);
INSERT INTO cliente VALUES (12, 'carlosanders@gmail.com', '2121046446', '2011-05-27', 'Sabrina Sato', '1979-01-19', '07504022716', 'feminino', 'Casado', 'Avenida Vicente de Carvalho, 1086 - Rua F bloco B-3 Ap-405', 'Vicente de Cavalho', 'Rio de Janeiro', 'RJ', '12345678', '2011-05-27 23:37:09.49', 'Inativo', '21210000', 4);
INSERT INTO cliente VALUES (14, 'carlosanders@bol.com.br', '2121046161', '2011-05-29', 'Sandro Anders', '1979-05-25', '07504022719', 'masculino', 'Solteiro', 'rua do brasil s/n', 'rio das ostras', 'rio de janeiro', 'MA', '12345678', '2011-05-29 08:38:54.383', 'Ativo', '21210000', 4);
INSERT INTO cliente VALUES (10, 'carlosanders@gmail.com', '2121046446', '2011-05-27', 'Daniel Rodrigues', '1979-01-19', '07504022714', 'masculino', 'Casado', 'Avenida Vicente de Carvalho, 1086 - Rua F bloco B-3 Ap-405', 'Vicente de Cavalho', 'Rio de Janeiro', 'RJ', '12345678', '2011-06-04 20:30:21.925', 'Ativo', '21210000', 4);
INSERT INTO cliente VALUES (15, 'carlosanders1@gmail.com', '2221064461', '2011-05-31', 'Anderson da Silva Anders', '1980-02-19', '07504022705', 'masculino', 'Solteiro', 'rua f bloco b-3 ap-405', 'vicente de carvalho', 'rio de janeiro', 'ES', '12345678', '2011-06-04 20:10:49.665', 'Ativo', '21210000', 4);


--
-- TOC entry 1920 (class 0 OID 41655)
-- Dependencies: 1555
-- Data for Name: editora; Type: TABLE DATA; Schema: public; Owner: cultbook
--

INSERT INTO editora VALUES (4, 'Book Livros', '00394502043896', NULL, 'Ativo');
INSERT INTO editora VALUES (6, 'São Paulo', '00394502043894', NULL, 'Ativo');
INSERT INTO editora VALUES (7, 'Rio de Janeiro', '00394502043893', 'teste2', 'Ativo');
INSERT INTO editora VALUES (5, 'Cultbook', '00394502043895', 'editora teste', 'Ativo');
INSERT INTO editora VALUES (3, 'Saraiva', '00394502043899', 'saraiva editora de livros modernos', 'Ativo');
INSERT INTO editora VALUES (2, 'Cultura Rio', '00394502043898', 'cultura do brasil', 'Ativo');
INSERT INTO editora VALUES (1, 'Casa Publicadora Brasileira', '00394502043897', 'editora evangélica!', 'Ativo');


--
-- TOC entry 1921 (class 0 OID 41663)
-- Dependencies: 1557
-- Data for Name: entrega; Type: TABLE DATA; Schema: public; Owner: cultbook
--

INSERT INTO entrega VALUES (2, 2, 0, 'Silva Anders', 'Avenida Vicente de Carvalho, 1086 - Rua F bloco B-3 Ap-405', 'Vicente de Cavalho', 'Rio de Janeiro', 'RJ', '21210000', '2121046446');
INSERT INTO entrega VALUES (4, 4, 45, 'Thiago Valverde', 'Avenida Vicente de Carvalho, 1086 - Rua F bloco B-3 Ap-405', 'Vicente de Cavalho', 'Rio de Janeiro', 'PE', '21210000', '2121046446');
INSERT INTO entrega VALUES (5, 5, 45, 'José Elísio da silva', 'Avenida Vicente de Carvalho, 1086 - Rua F bloco B-3 Ap-405', 'Vicente de Cavalho', 'Rio de Janeiro', 'RJ', '21210000', '2121046446');
INSERT INTO entrega VALUES (1, 1, 45, 'Sandro S. Anders', 'rua do brasil nr 26', 'rio das ostras', 'rio de janeiro', 'PI', '21210000', '2121046161');
INSERT INTO entrega VALUES (6, 6, 45, 'Anderson da Silva Anders', 'rua f bloco b-3 ap-405', 'vicente de carvalho', 'rio de janeiro', 'ES', '21210000', '212106446');
INSERT INTO entrega VALUES (7, 7, 15, 'Anderson da Silva Anders', 'rua f bloco b-3 ap-405', 'vicente de carvalho', 'rio de janeiro', 'ES', '21210000', '212106446');
INSERT INTO entrega VALUES (8, 8, 30, 'Anderson da Silva Anders', 'rua f bloco b-3 ap-405', 'vicente de carvalho', 'rio de janeiro', 'ES', '21210000', '222106446');
INSERT INTO entrega VALUES (9, 9, 20.5, 'Anderson da Silva Anders', 'rua f bloco b-3 ap-405', 'vicente de carvalho', 'rio de janeiro', 'ES', '21210000', '222106446');
INSERT INTO entrega VALUES (3, 3, 45, 'Filipe Cavalcante', 'Avenida Vicente de Carvalho, 1086 - Rua F bloco B-3 Ap-405', 'Vicente de Cavalho', 'Rio de Janeiro', 'RJ', '21210000', '2121046446');
INSERT INTO entrega VALUES (10, 10, 0, 'Anderson da Silva Anders', 'rua f bloco b-3 ap-405', 'vicente de carvalho', 'rio de janeiro', 'PR', '21210000', '222106446');
INSERT INTO entrega VALUES (11, 11, 30, 'Daniel Rodrigues', 'Avenida Vicente de Carvalho, 1086 - Rua F bloco B-3 Ap-405', 'Vicente de Cavalho', 'Rio de Janeiro', 'RJ', '21210000', '2121046446');


--
-- TOC entry 1922 (class 0 OID 41671)
-- Dependencies: 1559
-- Data for Name: funcionario; Type: TABLE DATA; Schema: public; Owner: cultbook
--

INSERT INTO funcionario VALUES (6, 'Vendedor', '2011-06-16', NULL, 'Marcela Silva', '1981-05-20', '05513614766', 'feminino', 'rua f bloco b-3 ap-405', 'vicente de carvalho', 'rio de janeiro', 'AL', 'anders', '2011-06-03 03:37:10.769', 'Ativo', '21210005', 3);
INSERT INTO funcionario VALUES (2, 'Gerente', '2011-06-04', NULL, 'Carlos Anders Funcionario', '1979-01-19', '07504022705', 'masculino', 'Avenida Vicente de Carvalho, 1086 - Rua F bloco B-3 Ap-405', 'Vicente de Cavalho', 'Rio de Janeiro', 'RJ', '12345678', '2011-06-03 03:38:05.736', 'Ativo', '21210000', 2);
INSERT INTO funcionario VALUES (1, 'Administrador', '2011-05-27', NULL, 'Administrador Geral Sistema', '2011-05-27', '01234567890', 'masculino', 'rua', 'bairro', 'cidade', 'RJ', '12345678', '2011-06-04 22:49:22.989', 'Ativo', '12345678', 1);


--
-- TOC entry 1923 (class 0 OID 41679)
-- Dependencies: 1561
-- Data for Name: item_pedido; Type: TABLE DATA; Schema: public; Owner: cultbook
--

INSERT INTO item_pedido VALUES (1, 4, 1);
INSERT INTO item_pedido VALUES (1, 6, 2);
INSERT INTO item_pedido VALUES (1, 10, 1);
INSERT INTO item_pedido VALUES (2, 3, 1);
INSERT INTO item_pedido VALUES (2, 6, 1);
INSERT INTO item_pedido VALUES (2, 12, 1);
INSERT INTO item_pedido VALUES (3, 1, 5);
INSERT INTO item_pedido VALUES (3, 11, 1);
INSERT INTO item_pedido VALUES (3, 14, 1);
INSERT INTO item_pedido VALUES (4, 2, 1);
INSERT INTO item_pedido VALUES (4, 24, 1);
INSERT INTO item_pedido VALUES (4, 4, 1);
INSERT INTO item_pedido VALUES (4, 30, 1);
INSERT INTO item_pedido VALUES (4, 22, 1);
INSERT INTO item_pedido VALUES (4, 17, 1);
INSERT INTO item_pedido VALUES (4, 5, 2);
INSERT INTO item_pedido VALUES (5, 7, 1);
INSERT INTO item_pedido VALUES (5, 28, 1);
INSERT INTO item_pedido VALUES (5, 8, 5);
INSERT INTO item_pedido VALUES (5, 32, 1);
INSERT INTO item_pedido VALUES (6, 3, 1);
INSERT INTO item_pedido VALUES (6, 1, 1);
INSERT INTO item_pedido VALUES (6, 23, 1);
INSERT INTO item_pedido VALUES (6, 29, 3);
INSERT INTO item_pedido VALUES (7, 2, 1);
INSERT INTO item_pedido VALUES (8, 9, 10);
INSERT INTO item_pedido VALUES (8, 4, 1);
INSERT INTO item_pedido VALUES (9, 2, 1);
INSERT INTO item_pedido VALUES (10, 5, 11);
INSERT INTO item_pedido VALUES (10, 14, 1);
INSERT INTO item_pedido VALUES (11, 24, 1);
INSERT INTO item_pedido VALUES (11, 17, 1);


--
-- TOC entry 1924 (class 0 OID 41682)
-- Dependencies: 1562
-- Data for Name: livro; Type: TABLE DATA; Schema: public; Owner: cultbook
--

INSERT INTO livro VALUES (1, 1, '9788534513456', 'Risco Calculado', 10, 'Religião', 'cultbook_2011-05-28_015310.png', '2010', 'sim', 'Ativo');
INSERT INTO livro VALUES (3, 1, '9788534512800', 'A Volta Por Cima', 35, 'Religião', 'cultbook_2011-05-28_015746.png', '2011', 'sim', 'Ativo');
INSERT INTO livro VALUES (4, 1, '9788534513388', 'A Oração Radical', 65, 'Religião', 'cultbook_2011-05-28_015928.png', '2011', 'sim', 'Ativo');
INSERT INTO livro VALUES (5, 2, '9788534513357', 'O Ouro de Katya', 200, 'Religião', 'cultbook_2011-05-28_020151.png', '2009', 'sim', 'Ativo');
INSERT INTO livro VALUES (6, 1, '9788534513432', 'Milagres não ocorrem por acaso', 25, 'Religião', 'cultbook_2011-05-28_020531.png', '2011', NULL, 'Ativo');
INSERT INTO livro VALUES (7, 3, '9788579302107', 'Amante Liberto', 36, 'Literatura', 'cultbook_2011-05-28_021028.jpg', '2009', 'sim', 'Ativo');
INSERT INTO livro VALUES (8, 3, '9788579301179', 'Amante Desperto - Vol. 3 - Col. Black Dagger Brotherhood', 65, 'Literatura', 'cultbook_2011-05-28_021710.jpg', '2010', 'sim', 'Ativo');
INSERT INTO livro VALUES (25, 4, '9788502087293', 'Contabilidade Básica Fácil - 27ª Ed', 9, 'Contabilidade', 'cultbook_2011-05-28_030821.jpg', '2010', NULL, 'Ativo');
INSERT INTO livro VALUES (33, 3, '9788501088710', 'A Saga Brasileira', 6, 'Economia', 'cultbook_2011-05-31_230218.jpg', '2009', 'sim', 'Ativo');
INSERT INTO livro VALUES (9, 4, '8575421026', 'O Monge e o Executivo - Uma História Sobre a Essência da Liderança', 5, 'Administração', 'cultbook_2011-05-28_022746.jpg', '2010', 'sim', 'Ativo');
INSERT INTO livro VALUES (10, 5, '8575422391', 'Os Segredos da Mente Milionária', 2, 'Administração', 'cultbook_2011-05-28_023018.jpg', '1995', NULL, 'Ativo');
INSERT INTO livro VALUES (11, 3, '9788575425114', 'Não Tenha Medo de Ser Chefe', 3, 'Administração', 'cultbook_2011-05-28_023603.jpg', '1999', NULL, 'Ativo');
INSERT INTO livro VALUES (12, 3, '856001800X', 'A Arte da Guerra - Os Treze Capítulos Originais', 99, 'Administração', 'cultbook_2011-05-28_023754.jpg', '1990', NULL, 'Ativo');
INSERT INTO livro VALUES (13, 2, '9788576766308', 'Quem Pensa Enriquece', 45, 'Administração', 'cultbook_2011-05-28_024112.jpg', '1997', NULL, 'Ativo');
INSERT INTO livro VALUES (14, 3, '8573124393', 'Casais Inteligentes Enriquecem Juntos', 12, 'Administração', 'cultbook_2011-05-28_024231.jpg', '2000', NULL, 'Ativo');
INSERT INTO livro VALUES (15, 7, '9788575424889', 'Por que os Homens Amam as Mulheres Poderosas?', 2, 'Autoajuda', 'cultbook_2011-05-28_024637.jpg', '2001', NULL, 'Inativo');
INSERT INTO livro VALUES (16, 3, '9788576656227', 'Mulheres Inteligentes, Relações Saudáveis', 41, 'Autoajuda', 'cultbook_2011-05-28_024838.jpg', '2005', NULL, 'Inativo');
INSERT INTO livro VALUES (17, 3, '9788579302350', 'Deixe Os Homens Aos Seus Pés - 2ª Ed. 2011', 6, 'Autoajuda', 'cultbook_2011-05-28_025024.jpg', '2011', NULL, 'Ativo');
INSERT INTO livro VALUES (18, 6, '8532604919', 'Minutos de Sabedoria (simples)', 3, 'Autoajuda', 'cultbook_2011-05-28_025158.jpg', '1992', NULL, 'Ativo');
INSERT INTO livro VALUES (19, 3, '9788537805169', 'O Gênio Em Todos Nós', 65, 'Ciências', 'cultbook_2011-05-28_025401.jpg', '2009', NULL, 'Ativo');
INSERT INTO livro VALUES (20, 6, '8572325840', 'A Origem das Espécies', 6, 'Ciências', 'cultbook_2011-05-28_025609.jpg', '1965', NULL, 'Ativo');
INSERT INTO livro VALUES (22, 5, '9788527710459', 'Biologia Celular e Molecular - 8ª Ed.', 3, 'Ciências', 'cultbook_2011-05-28_030326.jpg', '2005', NULL, 'Ativo');
INSERT INTO livro VALUES (23, 6, '9788586714313', 'Árvores Brasileiras - 5ª Ed. - Vol 1', 14, 'Ciências', 'cultbook_2011-05-28_030506.jpg', '2005', NULL, 'Inativo');
INSERT INTO livro VALUES (24, 7, '9788522459124', 'Manual de Contabilidade Societária', 5, 'Contabilidade', 'cultbook_2011-05-28_030659.jpg', '1977', NULL, 'Ativo');
INSERT INTO livro VALUES (26, 7, '9788589987295', 'Cartilha Caminho Suave - 130 ª Ed.', 22, 'Didáticos', 'cultbook_2011-05-28_030951.jpg', '1995', NULL, 'Ativo');
INSERT INTO livro VALUES (27, 7, '9788502084278', 'Atlas Geográfico Saraiva', 14, 'Didáticos', 'cultbook_2011-05-28_031100.jpg', '2009', NULL, 'Ativo');
INSERT INTO livro VALUES (28, 6, '9788502104518', 'Vade Mecum Saraiva 2011', 2, 'Direito', 'cultbook_2011-05-28_031233.jpg', '2011', NULL, 'Ativo');
INSERT INTO livro VALUES (29, 6, '9788530934439', 'Direito Administrativo Descomplicado', 10, 'Direito', 'cultbook_2011-05-28_031350.jpg', '2011', NULL, 'Ativo');
INSERT INTO livro VALUES (30, 5, '9788502071216', 'Oratória para Advogados e Estudantes de Direito - Conforme a Nova Ortografia', 4, 'Direito', 'cultbook_2011-05-28_031508.jpg', '2011', NULL, 'Ativo');
INSERT INTO livro VALUES (31, 7, '8500822570', 'Antologia Poetica', 12, 'Diversos', 'cultbook_2011-05-28_031640.jpg', '2011', NULL, 'Ativo');
INSERT INTO livro VALUES (32, 2, '8598333182', 'A Clandestina', 98, 'Diversos', 'cultbook_2011-05-28_031748.jpg', '2000', NULL, 'Ativo');
INSERT INTO livro VALUES (34, 7, '9788502067677', 'Fundamentos de Economia - 3ª Ed. 2008', 5, 'Economia', 'cultbook_2011-05-31_230452.jpg', '2008', NULL, 'Inativo');
INSERT INTO livro VALUES (35, 5, '8571930996', 'Fundamentos de Engenharia de Petróleo - 2ª Ed. 2004', 5, 'Engenharia', 'cultbook_2011-05-31_230640.jpg', '2008', NULL, 'Ativo');
INSERT INTO livro VALUES (2, 5, '9788534513425', 'Portal dos Deuses', 25, 'Religião', 'cultbook_2011-05-28_015534.png', '2010', NULL, 'Ativo');
INSERT INTO livro VALUES (21, 5, '9788536325446', 'Memória - 2ª Ed. - 2011', 120, 'Ciências', 'cultbook_2011-05-28_030217.jpg', '2010', NULL, 'Ativo');


--
-- TOC entry 1925 (class 0 OID 41688)
-- Dependencies: 1563
-- Data for Name: livro_autor; Type: TABLE DATA; Schema: public; Owner: cultbook
--

INSERT INTO livro_autor VALUES (3, 1);
INSERT INTO livro_autor VALUES (2, 1);
INSERT INTO livro_autor VALUES (12, 25);
INSERT INTO livro_autor VALUES (22, 33);
INSERT INTO livro_autor VALUES (19, 2);
INSERT INTO livro_autor VALUES (16, 2);
INSERT INTO livro_autor VALUES (6, 3);
INSERT INTO livro_autor VALUES (5, 4);
INSERT INTO livro_autor VALUES (15, 5);
INSERT INTO livro_autor VALUES (14, 5);
INSERT INTO livro_autor VALUES (14, 6);
INSERT INTO livro_autor VALUES (15, 7);
INSERT INTO livro_autor VALUES (9, 7);
INSERT INTO livro_autor VALUES (18, 8);
INSERT INTO livro_autor VALUES (10, 9);
INSERT INTO livro_autor VALUES (13, 9);
INSERT INTO livro_autor VALUES (3, 34);
INSERT INTO livro_autor VALUES (19, 34);
INSERT INTO livro_autor VALUES (17, 35);
INSERT INTO livro_autor VALUES (9, 35);
INSERT INTO livro_autor VALUES (10, 10);
INSERT INTO livro_autor VALUES (19, 10);
INSERT INTO livro_autor VALUES (11, 10);
INSERT INTO livro_autor VALUES (18, 11);
INSERT INTO livro_autor VALUES (19, 12);
INSERT INTO livro_autor VALUES (18, 13);
INSERT INTO livro_autor VALUES (13, 14);
INSERT INTO livro_autor VALUES (14, 14);
INSERT INTO livro_autor VALUES (17, 15);
INSERT INTO livro_autor VALUES (15, 16);
INSERT INTO livro_autor VALUES (18, 17);
INSERT INTO livro_autor VALUES (3, 18);
INSERT INTO livro_autor VALUES (17, 19);
INSERT INTO livro_autor VALUES (2, 20);
INSERT INTO livro_autor VALUES (9, 20);
INSERT INTO livro_autor VALUES (11, 22);
INSERT INTO livro_autor VALUES (3, 23);
INSERT INTO livro_autor VALUES (2, 23);
INSERT INTO livro_autor VALUES (6, 24);
INSERT INTO livro_autor VALUES (9, 26);
INSERT INTO livro_autor VALUES (17, 27);
INSERT INTO livro_autor VALUES (8, 28);
INSERT INTO livro_autor VALUES (16, 29);
INSERT INTO livro_autor VALUES (17, 30);
INSERT INTO livro_autor VALUES (17, 31);
INSERT INTO livro_autor VALUES (3, 32);
INSERT INTO livro_autor VALUES (3, 21);
INSERT INTO livro_autor VALUES (2, 21);


--
-- TOC entry 1926 (class 0 OID 41691)
-- Dependencies: 1564
-- Data for Name: niveldeacesso; Type: TABLE DATA; Schema: public; Owner: cultbook
--

INSERT INTO niveldeacesso VALUES (1, 'Administrador');
INSERT INTO niveldeacesso VALUES (2, 'Gerente');
INSERT INTO niveldeacesso VALUES (3, 'Funcionario');
INSERT INTO niveldeacesso VALUES (4, 'Cliente');


--
-- TOC entry 1927 (class 0 OID 41696)
-- Dependencies: 1566
-- Data for Name: pagamento; Type: TABLE DATA; Schema: public; Owner: cultbook
--

INSERT INTO pagamento VALUES (2, 2, 70.55);
INSERT INTO pagamento VALUES (4, 4, 165.6);
INSERT INTO pagamento VALUES (5, 5, 239.14);
INSERT INTO pagamento VALUES (1, 1, 78);
INSERT INTO pagamento VALUES (6, 6, 157.5);
INSERT INTO pagamento VALUES (7, 7, 30);
INSERT INTO pagamento VALUES (8, 8, 161.2);
INSERT INTO pagamento VALUES (9, 9, 30);
INSERT INTO pagamento VALUES (3, 3, 200.9);
INSERT INTO pagamento VALUES (10, 10, 199.7);
INSERT INTO pagamento VALUES (11, 11, 70.9);


--
-- TOC entry 1928 (class 0 OID 41704)
-- Dependencies: 1568
-- Data for Name: pagamentoboleto; Type: TABLE DATA; Schema: public; Owner: cultbook
--

INSERT INTO pagamentoboleto VALUES (2, '0123456789', '2011-06-13', 'Livraria CULTBOOK');
INSERT INTO pagamentoboleto VALUES (1, '0123456789', '2011-06-14', 'Livraria CULTBOOK');
INSERT INTO pagamentoboleto VALUES (7, '0123456789', '2011-06-15', 'Livraria CULTBOOK');
INSERT INTO pagamentoboleto VALUES (8, '0123456789', '2011-06-15', 'Livraria CULTBOOK');
INSERT INTO pagamentoboleto VALUES (9, '0123456789', '2011-06-15', 'Livraria CULTBOOK');
INSERT INTO pagamentoboleto VALUES (11, '0123456789', '2011-06-19', 'Livraria CULTBOOK');


--
-- TOC entry 1929 (class 0 OID 41707)
-- Dependencies: 1569
-- Data for Name: pagamentocartao; Type: TABLE DATA; Schema: public; Owner: cultbook
--

INSERT INTO pagamentocartao VALUES (4, 'antonio carlos', '022017', 'MasterCard', '238', 'antonio carlos', 4, 86.4);
INSERT INTO pagamentocartao VALUES (5, '123456987', '042014', 'MasterCard', '123', 'antonio carlos anders', 4, 71.035);
INSERT INTO pagamentocartao VALUES (6, '123456987', '022013', 'Dinners', '321', 'marcela anders', 1, 202.5);
INSERT INTO pagamentocartao VALUES (3, 'anders', '012013', 'MasterCard', '123', 'anders', 2, 100.5);
INSERT INTO pagamentocartao VALUES (10, '123456987', '052014', 'MasterCard', '171', 'marcela anders', 2, 114.85);


--
-- TOC entry 1930 (class 0 OID 41713)
-- Dependencies: 1570
-- Data for Name: pedido; Type: TABLE DATA; Schema: public; Owner: cultbook
--

INSERT INTO pedido VALUES (1, 14, 'cliente reclamou do produto com defeito', 'Devolvido', '2011-05-15');
INSERT INTO pedido VALUES (2, 3, 'Item vendido', 'Finalizado', '2001-05-25');
INSERT INTO pedido VALUES (4, 11, 'Item no setor de estoque', 'EnviadoTransportadora', '2011-04-20');
INSERT INTO pedido VALUES (5, 9, 'com a transportadora', 'EmTransito', '2011-03-15');
INSERT INTO pedido VALUES (6, 15, 'Item vendido', 'AguardandoConfirmacaoPagamento', '2011-05-31');
INSERT INTO pedido VALUES (7, 15, 'Item vendido', 'AguardandoConfirmacaoPagamento', '2011-05-31');
INSERT INTO pedido VALUES (8, 15, 'Item vendido', 'AguardandoConfirmacaoPagamento', '2011-05-31');
INSERT INTO pedido VALUES (9, 15, 'Cliente reportou agradecimento', 'Entregue', '2011-04-30');
INSERT INTO pedido VALUES (3, 7, 'Item vendido', 'Devolvido', '2011-05-25');
INSERT INTO pedido VALUES (10, 15, 'Item vendido', 'AguardandoConfirmacaoPagamento', '2011-06-04');
INSERT INTO pedido VALUES (11, 10, 'Item vendido', 'AguardandoConfirmacaoPagamento', '2011-06-04');


--
-- TOC entry 1931 (class 0 OID 41718)
-- Dependencies: 1572
-- Data for Name: produto; Type: TABLE DATA; Schema: public; Owner: cultbook
--

INSERT INTO produto VALUES (1, 'Uma longa aventura!', 'Em nossa cultura que tenta evitar o risco a todo custo, estimamos muito o valor da segurança. Mas, ao nos protegermos dos riscos, perdemos a grande aventura de viver a vida em todo seu potencial. Com exemplos pessoais, o Dr. Carson nos convida a enfrentar os riscos presentes em nossa própria vida. Você encontrará informações que o ajudarão a se livrar do medo de se arriscar para que seja capaz de sonhar alto, agir com confiança e colher recompensas que jamais imaginou. ', 25.2);
INSERT INTO produto VALUES (3, 'Uma breve descrição', 'Se você é um peregrino com problemas, aqui estão boas notícias: "A batalha não consiste em tentar não pecar, mas em tentar confiar. Nossa parte é desenvolver a confiança. A parte de Deus é efetuar a mudança." Com um estilo bem-humorado, o autor nos proporciona um GPS espiritual que nos mostra a maneira de triunfar sobre as dificuldades da vida cristã. Seus conselhos nos ajudam a aprofundar a confiança em Jesus e a dar a volta por cima quando falhamos. ', 32.65);
INSERT INTO produto VALUES (4, 'Uma breve descrição', 'Você está pronto para fazer uma oração radical, que revolucione sua vida e o deixe perplexo diante dos resultados? Uma oração à qual Deus responderá com um definido "sim"? Descubra a oração incrivelmente poderosa que permitirá Deus mudar o mundo - por seu intermédio. ', 12.2);
INSERT INTO produto VALUES (5, 'Uma breve descrição', 'Incapaz de controlar seu temperamento, havia algo em que Katya se superava: esquiar. Ela parecia voar sobre os esquis, e descobriu exatamente o que desejava da vida: medalhas de ouro. Certo dia, um esquiador lhe deu alguns livros cristãos para ler. Os professores comunistas de Katya ensinavam que a Bíblia era um mito e que Deus não existia. Finalmente ela aprendeu a verdade. Ao descobrir que não podia guardar o sábado e ao mesmo tempo continuar competindo, deparou-se com a decisão mais difícil de sua vida. ', 16.8);
INSERT INTO produto VALUES (6, 'Uma breve descrição', 'Quando lemos as histórias bíblicas, vemos Deus realizando grandes milagres e atuando de maneira espetacular. E hoje? Como receber o mesmo poder e vencer quando não restam mais forças? Qual é o segredo para obter a vitória sobre os desafios que encontramos no dia a dia? Descubra como usar o poder de Deus e supere desafios que parecem impossíveis de ser vencidos. Sonhe alto e transforme seus planos em realidade. Quando você acredita e age pela fé, permite que Deus opere milagres em sua vida. ', 18.9);
INSERT INTO produto VALUES (7, 'Uma breve descrição', 'Nas sombras da noite em Caldwell, Nova York, a guerra explode entre vampiros e seus assassinos. Há uma Irmandade secreta, sem igual, formada por seis guerreiros vampiros, defensores de sua raça. O coração gelado de um predador será aquecido mesmo contra a sua vontade... Destemido e brilhante, Vishous, filho de Bloodletter, possui uma maldição destrutiva e a capacidade assustadora de prever o futuro. Criado no campo de guerra de seu pai, ele sofreu maus tratos e abusos físicos e psicológicos. Membro da Irmandade, ele não se interessa por amor nem emoção, apenas pela batalha com a Sociedade Redutora. Mas quando uma lesão mortal faz com que fique sob os cuidados de uma cirurgiã humana, a Dra. Jane Whitcomb, ele é levado a revelar a dor que esconde e a experimentar o verdadeiro prazer de pela primeira vez... Até que o destino, que V. não escolheu, o leva para um futuro do qual Jane não faz parte.', 35.89);
INSERT INTO produto VALUES (8, 'O livro mais aguardado pelas fãs da série narra a história de Zsadist', 'O livro mais aguardado pelas fãs da série narra a história de Zsadist, o membro mais assustador da Irmandade da Adaga Negra. Zsadist foi por muito tempo um escravo de sangue, e por isso, ainda carrega as cicatrizes de um passado repleto de sofrimento e humilhação. Conhecido por uma fúria que não acaba e por atos sinistros, ele é um selvagem, temido igualmente por humanos e vampiros. A raiva é sua única companheira e o terror, sua única paixão... Até que resgata uma bela vampira das garras da maligna Sociedade Redutora. Bella sente-se imediatamente enfeitiçada pela ardente força que emana de Zsadist. Entretanto, mesmo quando o desejo de ambos começa a consumi-los, a sede de vingança de Zsadist contra os torturadores de Bella o leva à beira da loucura. Agora, Bella deve ajudar seu amante a superar as feridas de seu atormentado passado e vislumbrar um futuro ao lado dela...', 32.05);
INSERT INTO produto VALUES (10, 'Aprenda a Enriquecer Mudando seus Conceitos Sobre o Dinheiro ...', 'Se as suas finanças andam na corda bamba, talvez esteja na hora de você refletir sobre o que T. Harv Eker chama de "o seu modelo de dinheiro" - um conjunto de crenças que cada um de nós alimenta desde a infância e que molda o nosso destino financeiro, quase sempre nos levando para uma situação difícil. Neste livro, Eker mostra como substituir uma mentalidade destrutiva - que você talvez nem perceba que tem - pelos "arquivos de riqueza", 17 modos de pensar e agir que distinguem os ricos das demais pessoas. O autor também ensina um método eficiente de administrar o dinheiro. Você aprenderá a estabelecer sua remuneração pelos resultados que apresenta e não pelas horas que trabalha. Além disso, saberá como aumentar o seu patrimônio líquido - a verdadeira medida da riqueza.', 28);
INSERT INTO produto VALUES (9, 'Nesta história Leonard Hoffman, um famoso empresário...', 'Você está convidado a juntar-se a um grupo que durante uma semana vai estudar com um dos maiores especialistas em liderança dos Estados Unidos. Leonard Hoffman, um famoso empresário que abandonou sua brilhante carreira para se tornar monge em um mosteiro beneditino, é o personagem central desta envolvente história criada por James C. Hunter para ensinar de forma clara e agradável os princípios fundamentais dos verdadeiros líderes. Se você tem dificuldade em fazer com que sua equipe dê o melhor de si no trabalho e gostaria de se relacionar melhor com sua família e seus amigos, vai encontrar neste livro personagens, idéias e discussões que vão abrir um novo horizonte em sua forma de lidar com os outros. É impossível ler este livro sem sair transformado. "O Monge e o Executivo" é, sobretudo, uma lição sobre como se tornar uma pessoa melhor.', 14.9);
INSERT INTO produto VALUES (27, 'Não disponível. ', 'Não disponível. ', 20);
INSERT INTO produto VALUES (29, 'Direito Administrativo Descomplicado - Série Jurídica - 19ª Ed. - 2011', 'Os Professores Marcelo Alexandrino e Vicente Paulo têm a singular capacidade de transportar para a escrita a mesma didática que os caracteriza nas salas de aulas. A obra aborda todos os temas relevantes da disciplina, contemplando o conteúdo de editais dos principais concursos públicos, como também os programas das universidades do País, tudo com o rigor científico que exige a matéria. Complementarmente, apresenta extensa seleção de exercícios extraídos de provas de importantes bancas examinadoras, organizados por assunto, o que permite a eficaz fixação do conteúdo estudado.', 32);
INSERT INTO produto VALUES (11, 'Neste livro, você vai aprender a ver a função de chefia com outros olhos.', 'Não tenha medo de ser chefe aponta o maior problema das empresas hoje em dia - uma epidemia de subgerenciamento que afeta toda a escala de comando - e oferece um caminho para que os gerentes reassumam seu papel e se tornem líderes fortes de que suas equipes precisam.
Neste livro, você vai aprender a ver a função de chefia com outros olhos. Não como um fardo ou uma obrigação desgastante, e sim como uma oportunidade valiosa de ser útil, contribuir para aumentar a produtividade de sua equipe e ajudar seus subordinados a conseguir aquilo que querem e fazem por merecer.
Com uma sólida experiência em consultoria, Bruce Tulgan afirma que os chefes devem explicitar suas expectativas, dizer a cada pessoa exatamente o que fazer e como fazer, acompanhar e avaliar constantemente o desempenho de sua equipe, corrigir as falhas com rapidez e recompensar os sucessos ainda mais rapidamente.
Tulgan identifica as principais dificuldades enfrentadas pelos gerentes, relata casos reais e apresenta soluções simples e eficazes para lidar com os problemas do dia a dia. Com uma abordagem prática e positiva, ele destrói, um a um, os mitos que rondam o gerenciamento:
- O mito de que delegar poder é deixar as pessoas se virarem sozinhas e permitir que gerenciem a si mesmas.
- O mito de que ser justo é tratar todo mundo da mesma maneira.
- O mito de que o único jeito de ser firme é agir como um cretino e que ser um cara legal é deixar cada um fazer o que quiser.
- O mito de que evitar conversas difíceis é a melhor forma de não gerar confrontos com os funcionários.
- O mito de que é preciso ser um líder nato para gerenciar.
- O mito de que não há tempo suficiente para gerenciar pessoas.', 60);
INSERT INTO produto VALUES (12, 'A Arte da Guerra é um dos clássicos mais influentes do pensamento oriental sobre estratégia', 'O maior tratado de guerra de todos os tempos em sua versão completa em português. "A Arte da Guerra" é sem dúvida a Bíblia da estratégia, sendo hoje utilizada amplamente no mundo dos negócios, conquistando pessoas e mercados. Não nos surpreende vê-la citada em filmes como Wall Street (Oliver Stone, 1990) e constantemente aplicada para solucionar os mais recentes conflitos do nosso dia-a-dia. Conheça um dos maiores ícones da estratégia dos últimos 2500 anos.', 19);
INSERT INTO produto VALUES (13, 'Todos querem ficar ricos. Poucos conseguem. Qual será o segredo, a fórmula que cria milionário?', 'Todos querem ficar ricos. Poucos conseguem. Qual será o segredo, a fórmula que cria milionários?

Por vinte anos, Napoleon Hill, autor de Quem pensa enriquece, se dedicou a descobri-la. E conseguiu.

Ele acompanhou de perto a ascensão de 500 das maiores fortunas do mundo. Convivendo com mitos com Henry Ford, Theodore Roosevelt, King Gillette e John Rockefeller, o autor encontrou 15 características comuns a todos esses grandes vencedores.

Quem pensa enriquece, principal fruto das idéias de Napoleon Hill, é um dos maiores bestsellers do mercado editorial, com mais de 30 milhões de exemplares vendidos no mundo. Uma obra atemporal que vem ajudando pessoas comuns a se tornarem ricas e poderosas.

Nas suas mãos, está uma obra-prima com o poder de enriquecer sua vida. Aproveite.', 34);
INSERT INTO produto VALUES (14, 'Uma das principais causas das brigas entre o casal são as dificuldades financeiras.', 'Um dos maiores detonadores de brigas entre o casal são as dificuldades financeiras. Faltou dinheiro para pagar as contas? Para Gustavo Cerbasi, a causa desse desentendimento é a falta de conversa em família sobre dinheiro. Em geral o casal só fala sobre o assunto quando a bomba já estourou. E, como não discute a questão a dois, a maioria não faz um orçamento, não guarda dinheiro para atingir suas metas (ou, pior ainda, cada um tem seu objetivo, que o outro não conhece), não tem planos para a manutenção de seu padrão de vida no futuro, toma decisões de compra sem refletir, investe mal o dinheiro que eles suaram tanto para ganhar... Tem jeito? Sim, é possível mudar esse quadro se houver vontade e compromisso do casal, seja qual for seu orçamento. Com sugestões para casais em qualquer fase do relacionamento, dos namorados aos casais com filhos adultos, "Casais Inteligentes Enriquecem Juntos" mostra diferentes estratégias para formar uma parceria inteligente, ao longo da vida, na administração das finanças da família. Ele traz também testes que avaliam a capacidade do casal em construir riqueza.', 14.9);
INSERT INTO produto VALUES (15, 'Você cancela todos os seus planos esperando um possível telefonema de um homem que acabou de conhecer?', 'Você cancela todos os seus planos esperando um possível telefonema de um homem que acabou de conhecer? Tem a sensação de que, por mais que tente agradar seu parceiro, ele sempre parece distante ou desinteressado? Então, se você deseja construir um relacionamento estável, saudável e divertido - com esse homem ou com qualquer outro -, está na hora de mudar de postura. Não é que você não seja suficientemente boa. É que você é boazinha demais. E não há nada mais enfadonho para um homem do que uma mulher que passa o tempo todo se esforçando para agradá-lo. Se você se enquadra nesse padrão, não se desespere. Este livro pode ajudá-la a dar uma guinada em sua vida amorosa. Com um texto envolvente, Sherry Argov criou um verdadeiro manual que vai fazê-la entender de uma vez por todas por que os homens amam as mulheres poderosas.', 22);
INSERT INTO produto VALUES (16, 'O livro aborda tipos de mulheres, as observadoras, as analíticas, as mulheres casulos dentre outras', 'O livro aborda tipos de mulheres, as observadoras, as analíticas, as mulheres casulos dentre outras, trazendo o lado positivo e negativo de cada comportamento. O autor afirma que é necessária a identificação do tipo de mulher que cada uma é, permitindo uma reflexão ponderada e um trabalho de mudança, além da necessidade de ela reconhecer seu real valor, se valorizando, deixando a culpa e a autopunição de lado.
Cury aborda também outros temas muito importantes ao universo feminino: a ditadura do ciúme, a generalização da crítica, o excesso de trabalho, o medo da perda etc. Como identificar os erros? Como transformar as atitudes? Como manter uma relação saudável através do controle dos pensamentos e das emoções?
Neste livro, toda mulher se encontrará, identificando o tipo de mulher que é, podendo reconhecer também outras mulheres, como a mãe, a irmã e as amigas.
Ao final de cada capítulo os homens recebem um presente especial, frases-conselhos que os farão refletir sobre as mulheres de sua vida. E como não poderia deixar de ser, o autor encerra o livro com uma bela mensagem para as mulheres inteligentes.', 22);
INSERT INTO produto VALUES (28, 'A Editora Saraiva , líder no mercado de livros jurídicos e sempre atenta às necessidades dos estudantes', 'Constituição Federal e Emendas Constitucionais, Códigos, CLT, Estatutos, Legislação Complementar das áreas Administrativa, Ambiental, Civil, Comercial/Empresarial, Internacional, Penal, Previdenciária, Processual, Trabalhista e Tributária, Súmulas dos Tribunais Superiores e dos Juizados Especiais Federais, Orientações Jurisprudenciais, Precedentes Normativos e com novos Índices. Acompanha o volume, CD-ROM com Legislação Adicional, também, na versão para Palm Top e iPhone, Tutorial, Modelos de Peças Processuais e Dicionário de Expressões Latinas.
Destaque: composição das Súmulas com o inovador espaço reservado para uso de grampo, solução para provas e concursos. Atualizado semanal e gratuitamente pela internet com aviso por e-mail e SMS.', 22);
INSERT INTO produto VALUES (17, 'O livro Deixe os homens aos seus pés, de Marie Forleo, é mais do que um simples livro sobre relacionamentos amorosos.', 'O livro Deixe os homens aos seus pés, de Marie Forleo, é mais do que um simples livro sobre relacionamentos amorosos. É um guia de leitura fácil, divertida e otimista para qualquer mulher interessada em sentir-se bem e ter um melhor desempenho em todas as áreas da sua vida. Ele mostra às mulheres como parar de dar importância aos seus poucos pontos fracos e valorizar seus pontos fortes.
Para todas as mulheres que se interessam pela ideia de ser autêntica, expressiva e irresistível e desejam assumir um compromisso com um estilo de vida melhor, Deixe os homens aos seus pés foi feito para encorajar uma transformação completa, cheia de possibilidades jamais imaginadas no trabalho, no lazer, com a família e com os amigos. Ele mostra como desenvolver um relacionamento autêntico com o eu, pois este é o cerne dos bons relacionamentos com outras pessoas. Marie ensina as mulheres a se tornarem irresistivelmente atraentes, mostrando porque regras, joguinhos ou qualquer comportamento calculista não funcionam, permitindo-lhes desfrutar de relações saudáveis e prazerosas.
O recente sucesso editorial de livros como o best-seller Ele simplesmente não está a fim de você (e do filme baseado no livro) evidencia a grande demanda de leitoras ávidas pelo assunto. Depois do período da emancipação feminina, que legou um confronto constante entre homens e mulheres e ocasionou o engajamento das mulheres no mundo do trabalho, as mulheres estão cada vez mais procurando não apenas como obter sucesso profissional, mas valorizando suas características femininas como um modo de conciliar a vida profissional, familiar e amorosa.
Deixe os homens aos seus pés leva a leitora a uma viagem para descobrir um mundo novo de amor, relacionamento e parceria autêntica. Honesto e inteligente, este é um grande livro para todas as mulheres com uma mente aberta e disposição para ter um estilo de vida irresistível, quer estejam ou não se relacionando com alguém. Este é mais do que um livro sobre estratégias para se arrumar um encontro; é um livro sobre estratégias de como se conectar aos maiores tesouros da vida.', 65.3);
INSERT INTO produto VALUES (18, 'Carlos Torres Pastorinho traz Minutos de Sabedoria com reflexões, pensamentos', 'Este best seller de auto-ajuda, apresenta reflexões, pensamentos, conselhos curtos e penetrantes que auxiliam nas horas difíceis e, nos momentos leves, alegram e elevam a alma.', 2.5);
INSERT INTO produto VALUES (19, 'Por Que Tudo o Que Você Ouviu Falar Sobre Genética, Talento e Qi Está Errado', 'O que faz com que algumas pessoas se tornem excepcionais em uma atividade, enquanto a maioria alcança resultados medianos? Até recentemente, a resposta a essa pergunta poderia ser simplesmente nossa programação genética. Mas estudos científicos em diversos campos (como genética, biologia, psicologia e sociologia) apontam para uma mistura de aptidões naturais e estímulos externos como a causa de desempenhos notáveis. A mensagem de David Shenk em "O gênio em todos nós" é que não somos prisioneiros de nosso DNA. Está nas nossas mãos transformar nosso potencial, por meio do esforço, em grandes realizações.', 40);
INSERT INTO produto VALUES (20, 'A Origem das Espécies - Col. A Obra Prima de Cada Autor - Série Ouro', 'As idéias gerais da Teoria da Evolução das Espécies sofreram, aos poucos, alterações e aperfeiçoamentos. Todavia, as teses do evolucionismo subsistem até hoje, e o nome de Darwin ficou ligado a uma das mais notáveis concepções do espírito humano. Na base da teoria evolucionista de Darwin está a luta pela vida. Somente os mais fortes e os mais aptos conseguem sobreviver, e a própria natureza se incumbe de proceder a essa seleção natural. "A Origem das Espécies" (1859) pôs Darwin no centro das acirradas polêmicas e discussões fervorosas. Certamente é a mais importante obra sobre a Biologia jamais escrita.', 25);
INSERT INTO produto VALUES (22, 'Esta oitava edição de "Biologia Celular e Molecular", revista e atualizada', 'A primeira edição deste livro foi publicada em 1972 sob o título de Citologia Básica, visando complementar o outro livro didático dos autores, "Histologia Básica", no que se refere à estrutura e às atividades celulares. Nas edições seguintes, a quantidade de informações sobre as células no Histologia Básica foi muito aumentada, porém ainda continuam insuficientes para um conhecimento mais amplo das funções celulares, o que é necessário para o estudo dos tecidos e da estrutura dos órgãos. Ao mesmo tempo, os conhecimentos sobre as células aumentaram vertiginosamente, de tal modo que o termo Citologia deixou de ser usado pelos diversos autores, sendo mais apropriado para os livros dedicados ao estudo das células a designação, hoje usada por todos os autores, de Biologia Celular e Molecular, designação que foi também adotada pelos autores. Trinta e três anos depois da primeira edição, os desafios continuam os mesmos, porém aumentados. Foram estes os desafios que os autores tiveram de enfrentar: conciliar o enorme aumento dos conhecimentos sobre as funções celulares com o pouco tempo à disposição dos estudantes. Optaram por um meio-termo que pareceu razoável, incluindo as novidades moleculares essenciais à compreensão das funções celulares. Chega, portanto, às mãos dos estudantes, a oitava edição, revista e atualizada desta importante obra para o estudo da Biologia Celular e Molecular. Além da reformulação completa de alguns capítulos, esta edição foi enriquecida pela participação de uma nova colaboradora, a Dra. Chao Yun Irene Yan, do Laboratório de Biologia Molecular de Vertebrados do Instituto de Ciências Biomédicas da Universidade de São Paulo.', 14.9);
INSERT INTO produto VALUES (23, 'Uma breve descrição', 'Trata-se de um livro luxuoso contendo fotografias coloridas da árvore adulta, detalhe de um ramo florífero, fruto, semente, tronco e madeira, das 352 principais espécies de árvores existentes no país. Contém ainda informações escritas sobre as características de cada planta, sua fenologia, ocorrência, obtenção de sementes e mudas, etc. Totalmente impresso em papel couchê liso importado de 115g e encadernado em capa dura, é apresentada uma planta por página. De autoria do Eng. Agr. Harri Lorenzi, demorou 10 anos para ser produzido.', 3.65);
INSERT INTO produto VALUES (24, 'Em 1977, logo após a revolução contábil do século passado no Brasil trazida pela edição da Lei das S.A.', 'Em 1977, logo após a revolução contábil do século passado no Brasil trazida pela edição da Lei das S.A. (nº 6.404/76), a Fipecafi foi procurada pela CVM para editar o Manual de contabilidade das sociedades por ações, que visava orientar as empresas, os profissionais e o mercado em geral a respeito de tantas e importantes evoluções, já que praticamente tudo o que havia de novidade em matéria contábil nessa lei já vinha sendo pesquisado e ensinado no Departamento de Contabilidade e Atuária da FEA/USP. A partir principalmente de 1990, com a criação da Comissão Consultiva de Normas Contábeis da CVM (presença, além da CVM, da Fipecafi, do Ibracon, do CFC, da Apimec e da Abrasca), essa autarquia passou a emitir um grande conjunto de normas já convergentes às do IASB, dentro dos limites que a Lei permitia, e aquele Manual as foi incorporando ao longo de várias edições. Diversas evoluções outras foram também sendo inseridas. Com a edição das Leis nº 11.638/07 e 11.941/09 (esta transformando em lei a MP nº 449/08) e com a criação do CPC - Comitê de Pronunciamentos Contábeis - em 2005, produziu-se, durante 2008 e 2009, enorme conjunto de novas normas, aprovadas pela CVM e pelo CFC, agora com a convergência completa às normas internacionais de contabilidade (IASB). E essa está sendo a grande revolução contábil deste século no nosso país.', 5.6);
INSERT INTO produto VALUES (26, 'Não disponível. ', 'Não disponível. ', 29.9);
INSERT INTO produto VALUES (21, 'nova edição', 'Esta é a nova edição, revista e ampliada, do livro de Iván Izquierdo, um dos maiores especialistas mundiais em memória. ', 5.6);
INSERT INTO produto VALUES (30, 'Uma breve descrição', 'Com a leitura deste livro, você poderá se transformar em um advogado com oratória excepcional. Cada capítulo é uma aula que o Professor Reinaldo Polito ministra há décadas a profissionais de todas as áreas do Direito, preparando-os para falar de maneira correta e eficiente em audiências, na sustentação oral perante os tribunais superiores, nas teses de defesa ou de acusação no Tribunal do Júri, no relacionamento com clientes, nas reuniões com outros advogados no escritório e diante das platéias mais exigentes, em palestras e conferências.
Preservando seu estilo e suas características pessoais, você aprenderá passo a passo todas as técnicas para se transformar em um advogado naturalmente comunicativo, seguro e envolvente, estando pronto para fazer apresentações de sucesso.
Neste verdadeiro curso para o advogado falar em público, além de saber como usar a palavra em sua área específica do Direito, você aprenderá como conquistar a confiança dos ouvintes, combater o medo de falar em público, usar recursos audiovisuais, fazer homenagens, apresentar oradores, conceder entrevistas para emissoras de rádio e televisão e a usar com segurança a voz, o vocabulário e a expressão corporal.
Uma obra de referência para advogados que sabem que o pleno desenvolvimento da oratória é essencial para o sucesso da profissão.

3ª tiragem - 2010.', 4);
INSERT INTO produto VALUES (31, 'Não disponível. ', 'Não disponível. ', 18.9);
INSERT INTO produto VALUES (32, 'Uma breve descrição', '"Dentro de instantes se dará o embarque. A viagem, ou quem sabe a travessia, não tem definido o ponto de chegada. Chegar e sair são apenas detalhes; a diferença ou a substância se fará exatamente entre esses dois portos, Daisy Lucas o colocará frente a frente a um homem aparentemente livre, refém de uma ambição desmedida. visto pelo olhar aguçado, criterioso, fiel - e por ser fiel aparentemente resignado e submisso de uma clandestina, livre, mas presa pela afeição, pela gratidão. Afinal de contas em quanto tempo se paga uma vida? Um detalhe, um mistério, porém se encarregará de dar a justificativa a essa silenciosa atuação. " - Luíz Horácio - Jornalista e escritor', 21);
INSERT INTO produto VALUES (25, 'Uma breve descrição', 'A proposta desta obra é oferecer um trabalho didático e acessível, voltado especialmente aos que iniciam o estudo da Contabilidade. Ao lado de uma sólida iniciação teórica no assunto, o leitor encontrará atividades práticas que o levarão a um melhor desempenho e compreensão da matéria.', 1.65);
INSERT INTO produto VALUES (33, 'Breve Descrição', 'Da hiperinflação ao plano Real, passando pelos congelamentos, planos que não passavam de um verão e o confisco do governo Collor, Miriam Leitão mostra como os brasileiros sofreram até a estabilização da moeda. Um livro definitivo sobre a história econômica recente do país  já esquecida pelas novas gerações.
 Especialista em economia e negócios, Miriam Leitão, autora do volume de crônicas Convém Sonhar, está no dia a dia dos brasileiros através dos seguintes veículos de comunicação: O Globo, CBN, Globonews, TV Globo e, agora, também contribui para O Globo Online.
 Em 2007 ganhou o prêmio Jornalista Econômico, concedido pela Ordem dos Economistas do Brasil. Foi a segunda mulher brasileira a receber o Maria Moors Cabot Prize, em 2005. Nesse mesmo ano ganhou o prêmio Camélia da Liberdade.', 25.65);
INSERT INTO produto VALUES (34, 'Breve Descrição', 'Elaborada de forma a abordar os aspectos fundamentais da teoria econômica, esta obra, voltada mais especificamente aos cursos de direito e de ciências humanas de forma geral, vem preencher uma lacuna existente até o momento, ao oferecer um texto adequado aos que necessitam de uma visão abrangente da área, sem maior aprofundamento.
Inclui os seguintes tópicos:
- Introdução à Economia - conceito, problemas fundamentais, funcionamento de uma economia de mercado, inter-relação com outras áreas e divisão do estudo econômico;
- Breve retrospecto da evolução do pensamento econômico;
- Economia e direito;
- Introdução à Microeconomia e à Macroeconomia;
- Crescimento e desenvolvimento econômico.', 65);
INSERT INTO produto VALUES (35, 'Breve Descrição', 'Durante mais de 40 anos a Petrobras executou, com exclusividade, o monopólio do petróleo da união, instituído em 1953, detendo, por conseqüência, o monopólio do conhecimento no país das múltiplas especialidades envolvidas nesta atividade. Esta obra pretende colaborar com o desenvolvimento dos profissionais e estudantes envolvidos coma indústria do petróleo no Brasil.', 65.4);
INSERT INTO produto VALUES (2, 'Uma breve descrição', 'Inebriado com a vitória, o jovem príncipe babilônio observou o campo de batalha onde seu exército tinha derrotado os egípcios. Ele não sabia que seria escolhido por Deus como instrumento para castigar as nações por seus pecados. Não sabia que ele mesmo era o alvo da misericórdia divina e que Deus iria usar sonhos proféticos, quatro jovens judeus, uma fornalha ardente e, finalmente, a insanidade para conquistar seu coração. ', 30);


--
-- TOC entry 1864 (class 2606 OID 41737)
-- Dependencies: 1550 1550 1550
-- Name: acesso_nivel_pk; Type: CONSTRAINT; Schema: public; Owner: cultbook; Tablespace: 
--

ALTER TABLE ONLY acesso_nivel
    ADD CONSTRAINT acesso_nivel_pk PRIMARY KEY (codigoacesso, codigoniveldeacesso);


--
-- TOC entry 1862 (class 2606 OID 41739)
-- Dependencies: 1548 1548
-- Name: codigoacesso_pk; Type: CONSTRAINT; Schema: public; Owner: cultbook; Tablespace: 
--

ALTER TABLE ONLY acesso
    ADD CONSTRAINT codigoacesso_pk PRIMARY KEY (codigoacesso);


--
-- TOC entry 1866 (class 2606 OID 41741)
-- Dependencies: 1551 1551
-- Name: codigoautor_pk; Type: CONSTRAINT; Schema: public; Owner: cultbook; Tablespace: 
--

ALTER TABLE ONLY autor
    ADD CONSTRAINT codigoautor_pk PRIMARY KEY (codigoautor);


--
-- TOC entry 1870 (class 2606 OID 41743)
-- Dependencies: 1553 1553
-- Name: codigocliente_pk; Type: CONSTRAINT; Schema: public; Owner: cultbook; Tablespace: 
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT codigocliente_pk PRIMARY KEY (codigocliente);


--
-- TOC entry 1874 (class 2606 OID 41745)
-- Dependencies: 1555 1555
-- Name: codigoeditora_pk; Type: CONSTRAINT; Schema: public; Owner: cultbook; Tablespace: 
--

ALTER TABLE ONLY editora
    ADD CONSTRAINT codigoeditora_pk PRIMARY KEY (codigoeditora);


--
-- TOC entry 1880 (class 2606 OID 41747)
-- Dependencies: 1559 1559
-- Name: codigofuncionario_pk; Type: CONSTRAINT; Schema: public; Owner: cultbook; Tablespace: 
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT codigofuncionario_pk PRIMARY KEY (codigofuncionario);


--
-- TOC entry 1900 (class 2606 OID 41749)
-- Dependencies: 1572 1572
-- Name: codigoproduto_pk; Type: CONSTRAINT; Schema: public; Owner: cultbook; Tablespace: 
--

ALTER TABLE ONLY produto
    ADD CONSTRAINT codigoproduto_pk PRIMARY KEY (codigoproduto);


--
-- TOC entry 1878 (class 2606 OID 41751)
-- Dependencies: 1557 1557 1557
-- Name: entrega_pk; Type: CONSTRAINT; Schema: public; Owner: cultbook; Tablespace: 
--

ALTER TABLE ONLY entrega
    ADD CONSTRAINT entrega_pk PRIMARY KEY (codigoentrega, codigopedido);


--
-- TOC entry 1884 (class 2606 OID 41753)
-- Dependencies: 1561 1561 1561
-- Name: item_pedido_pk; Type: CONSTRAINT; Schema: public; Owner: cultbook; Tablespace: 
--

ALTER TABLE ONLY item_pedido
    ADD CONSTRAINT item_pedido_pk PRIMARY KEY (codigopedido, codigoproduto);


--
-- TOC entry 1888 (class 2606 OID 41755)
-- Dependencies: 1563 1563 1563
-- Name: livro_autor_pk; Type: CONSTRAINT; Schema: public; Owner: cultbook; Tablespace: 
--

ALTER TABLE ONLY livro_autor
    ADD CONSTRAINT livro_autor_pk PRIMARY KEY (codigoautor, codigoproduto);


--
-- TOC entry 1886 (class 2606 OID 41757)
-- Dependencies: 1562 1562
-- Name: livro_pk; Type: CONSTRAINT; Schema: public; Owner: cultbook; Tablespace: 
--

ALTER TABLE ONLY livro
    ADD CONSTRAINT livro_pk PRIMARY KEY (codigoproduto);


--
-- TOC entry 1890 (class 2606 OID 41759)
-- Dependencies: 1564 1564
-- Name: niveldeacesso_pk; Type: CONSTRAINT; Schema: public; Owner: cultbook; Tablespace: 
--

ALTER TABLE ONLY niveldeacesso
    ADD CONSTRAINT niveldeacesso_pk PRIMARY KEY (codigoniveldeacesso);


--
-- TOC entry 1892 (class 2606 OID 41761)
-- Dependencies: 1566 1566
-- Name: pagamento_pk; Type: CONSTRAINT; Schema: public; Owner: cultbook; Tablespace: 
--

ALTER TABLE ONLY pagamento
    ADD CONSTRAINT pagamento_pk PRIMARY KEY (codigopagamento);


--
-- TOC entry 1894 (class 2606 OID 41763)
-- Dependencies: 1568 1568
-- Name: pagamentoboleto_pk; Type: CONSTRAINT; Schema: public; Owner: cultbook; Tablespace: 
--

ALTER TABLE ONLY pagamentoboleto
    ADD CONSTRAINT pagamentoboleto_pk PRIMARY KEY (codigopagamento);


--
-- TOC entry 1896 (class 2606 OID 41765)
-- Dependencies: 1569 1569
-- Name: pagamentocartao_pk; Type: CONSTRAINT; Schema: public; Owner: cultbook; Tablespace: 
--

ALTER TABLE ONLY pagamentocartao
    ADD CONSTRAINT pagamentocartao_pk PRIMARY KEY (codigopagamento);


--
-- TOC entry 1898 (class 2606 OID 41767)
-- Dependencies: 1570 1570
-- Name: pedido_pk; Type: CONSTRAINT; Schema: public; Owner: cultbook; Tablespace: 
--

ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_pk PRIMARY KEY (codigopedido);


--
-- TOC entry 1876 (class 2606 OID 41769)
-- Dependencies: 1555 1555
-- Name: unique_cnpj_editora; Type: CONSTRAINT; Schema: public; Owner: cultbook; Tablespace: 
--

ALTER TABLE ONLY editora
    ADD CONSTRAINT unique_cnpj_editora UNIQUE (cnpj);


--
-- TOC entry 1868 (class 2606 OID 41771)
-- Dependencies: 1551 1551
-- Name: unique_cpf_autor; Type: CONSTRAINT; Schema: public; Owner: cultbook; Tablespace: 
--

ALTER TABLE ONLY autor
    ADD CONSTRAINT unique_cpf_autor UNIQUE (cpf);


--
-- TOC entry 1872 (class 2606 OID 41773)
-- Dependencies: 1553 1553
-- Name: unique_cpf_cliente; Type: CONSTRAINT; Schema: public; Owner: cultbook; Tablespace: 
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT unique_cpf_cliente UNIQUE (cpf);


--
-- TOC entry 1882 (class 2606 OID 41775)
-- Dependencies: 1559 1559
-- Name: unique_cpf_funcionario; Type: CONSTRAINT; Schema: public; Owner: cultbook; Tablespace: 
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT unique_cpf_funcionario UNIQUE (cpf);


--
-- TOC entry 1901 (class 2606 OID 41776)
-- Dependencies: 1550 1861 1548
-- Name: acesso_acesso_funcionario_fk; Type: FK CONSTRAINT; Schema: public; Owner: cultbook
--

ALTER TABLE ONLY acesso_nivel
    ADD CONSTRAINT acesso_acesso_funcionario_fk FOREIGN KEY (codigoacesso) REFERENCES acesso(codigoacesso);


--
-- TOC entry 1910 (class 2606 OID 41781)
-- Dependencies: 1865 1551 1563
-- Name: autor_livro_autor_fk; Type: FK CONSTRAINT; Schema: public; Owner: cultbook
--

ALTER TABLE ONLY livro_autor
    ADD CONSTRAINT autor_livro_autor_fk FOREIGN KEY (codigoautor) REFERENCES autor(codigoautor);


--
-- TOC entry 1915 (class 2606 OID 41786)
-- Dependencies: 1869 1570 1553
-- Name: cliente_pedido_fk; Type: FK CONSTRAINT; Schema: public; Owner: cultbook
--

ALTER TABLE ONLY pedido
    ADD CONSTRAINT cliente_pedido_fk FOREIGN KEY (codigocliente) REFERENCES cliente(codigocliente);


--
-- TOC entry 1908 (class 2606 OID 41791)
-- Dependencies: 1873 1555 1562
-- Name: editora_livro_fk; Type: FK CONSTRAINT; Schema: public; Owner: cultbook
--

ALTER TABLE ONLY livro
    ADD CONSTRAINT editora_livro_fk FOREIGN KEY (codigoeditora) REFERENCES editora(codigoeditora);


--
-- TOC entry 1911 (class 2606 OID 41796)
-- Dependencies: 1885 1563 1562
-- Name: livro_livro_autor_fk; Type: FK CONSTRAINT; Schema: public; Owner: cultbook
--

ALTER TABLE ONLY livro_autor
    ADD CONSTRAINT livro_livro_autor_fk FOREIGN KEY (codigoproduto) REFERENCES livro(codigoproduto);


--
-- TOC entry 1902 (class 2606 OID 41801)
-- Dependencies: 1564 1889 1550
-- Name: niveldeacesso_acesso_funcionario_fk; Type: FK CONSTRAINT; Schema: public; Owner: cultbook
--

ALTER TABLE ONLY acesso_nivel
    ADD CONSTRAINT niveldeacesso_acesso_funcionario_fk FOREIGN KEY (codigoniveldeacesso) REFERENCES niveldeacesso(codigoniveldeacesso);


--
-- TOC entry 1903 (class 2606 OID 41806)
-- Dependencies: 1553 1889 1564
-- Name: niveldeacesso_cliente_fk; Type: FK CONSTRAINT; Schema: public; Owner: cultbook
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT niveldeacesso_cliente_fk FOREIGN KEY (codigoniveldeacesso) REFERENCES niveldeacesso(codigoniveldeacesso);


--
-- TOC entry 1905 (class 2606 OID 41811)
-- Dependencies: 1559 1564 1889
-- Name: niveldeacesso_funcionario_fk; Type: FK CONSTRAINT; Schema: public; Owner: cultbook
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT niveldeacesso_funcionario_fk FOREIGN KEY (codigoniveldeacesso) REFERENCES niveldeacesso(codigoniveldeacesso);


--
-- TOC entry 1913 (class 2606 OID 41816)
-- Dependencies: 1568 1566 1891
-- Name: pagamento_pagamentoboleto_fk; Type: FK CONSTRAINT; Schema: public; Owner: cultbook
--

ALTER TABLE ONLY pagamentoboleto
    ADD CONSTRAINT pagamento_pagamentoboleto_fk FOREIGN KEY (codigopagamento) REFERENCES pagamento(codigopagamento);


--
-- TOC entry 1914 (class 2606 OID 41821)
-- Dependencies: 1569 1566 1891
-- Name: pagamento_pagamentocartao_fk; Type: FK CONSTRAINT; Schema: public; Owner: cultbook
--

ALTER TABLE ONLY pagamentocartao
    ADD CONSTRAINT pagamento_pagamentocartao_fk FOREIGN KEY (codigopagamento) REFERENCES pagamento(codigopagamento);


--
-- TOC entry 1904 (class 2606 OID 41826)
-- Dependencies: 1557 1570 1897
-- Name: pedido_entrega_fk; Type: FK CONSTRAINT; Schema: public; Owner: cultbook
--

ALTER TABLE ONLY entrega
    ADD CONSTRAINT pedido_entrega_fk FOREIGN KEY (codigopedido) REFERENCES pedido(codigopedido);


--
-- TOC entry 1906 (class 2606 OID 41831)
-- Dependencies: 1561 1897 1570
-- Name: pedido_item_fk; Type: FK CONSTRAINT; Schema: public; Owner: cultbook
--

ALTER TABLE ONLY item_pedido
    ADD CONSTRAINT pedido_item_fk FOREIGN KEY (codigopedido) REFERENCES pedido(codigopedido);


--
-- TOC entry 1912 (class 2606 OID 41836)
-- Dependencies: 1897 1570 1566
-- Name: pedido_pagamento_fk; Type: FK CONSTRAINT; Schema: public; Owner: cultbook
--

ALTER TABLE ONLY pagamento
    ADD CONSTRAINT pedido_pagamento_fk FOREIGN KEY (codigopedido) REFERENCES pedido(codigopedido);


--
-- TOC entry 1907 (class 2606 OID 41841)
-- Dependencies: 1899 1572 1561
-- Name: produto_item_fk; Type: FK CONSTRAINT; Schema: public; Owner: cultbook
--

ALTER TABLE ONLY item_pedido
    ADD CONSTRAINT produto_item_fk FOREIGN KEY (codigoproduto) REFERENCES produto(codigoproduto);


--
-- TOC entry 1909 (class 2606 OID 41846)
-- Dependencies: 1899 1572 1562
-- Name: produto_livro_fk; Type: FK CONSTRAINT; Schema: public; Owner: cultbook
--

ALTER TABLE ONLY livro
    ADD CONSTRAINT produto_livro_fk FOREIGN KEY (codigoproduto) REFERENCES produto(codigoproduto);


--
-- TOC entry 1936 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2011-06-04 22:50:19

--
-- PostgreSQL database dump complete
--

