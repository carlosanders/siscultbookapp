select * from pagamento;
select * from entrega;
select * from pagamentoboleto;
select * from pagamentocartao;
select * from produto;
select * from pedido;
select * from item_pedido;


INSERT INTO pedito_item (codigopedido, codigoitem) VALUES (17, 15);
INSERT INTO pedito_item (codigopedido, codigoitem) VALUES (17, 16);

INSERT INTO item_pedido (codigopedido, quantidade, codigoproduto) VALUES (20, 2, 85);
INSERT INTO item_pedido (codigopedido, quantidade, codigoproduto) VALUES (20, 1, 84);


select *
from livro l, produto p, editora e
where l.codigoproduto = p.codigoproduto
and l.codigoeditora = e.codigoeditora
LIMIT 20
OFFSET(2 - 1) * 20;

