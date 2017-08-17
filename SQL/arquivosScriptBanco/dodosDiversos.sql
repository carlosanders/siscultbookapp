CREATE SEQUENCE public.item_codigoitem_seq;

CREATE TABLE public.Item (
                codigoItem INTEGER NOT NULL DEFAULT nextval('public.item_codigoitem_seq'),
                quantidade INTEGER NOT NULL,
                codigoProduto INTEGER NOT NULL,
                CONSTRAINT item_pk PRIMARY KEY (codigoItem)
);


ALTER SEQUENCE public.item_codigoitem_seq OWNED BY public.Item.codigoItem;

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
                valorTotal NUMERIC NOT NULL,
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
