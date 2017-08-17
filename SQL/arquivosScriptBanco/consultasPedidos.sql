select * 
from livro l, produto p, editora e
where l.codigoproduto = 85
and p.codigoproduto = 85
and l.codigoeditora = e.codigoeditora;

select * 
from livro l
where l.isbn = 'ISBN-123456';

select * 
from livro l
where l.titulo LIKE '%R%';

select * 
from livro l
where l.anopublicacao LIKE '%2%';

select * from pagamento;
select * from entrega;
select * from pagamentoboleto;
select * from pagamentocartao;
select * from produto;
select * from pedido;
select * from item_pedido;
/*seleciono todos os pedidos e os itens do pedido por cliente*/
select * 
from pedido AS p, cliente AS c, item_pedido AS ip
where p.codigocliente = c.codigocliente and
p.codigopedido = ip.codigopedido
order by p.codigopedido ASC;

/*seleciono todos os pedidos referente ao cliente*/
select * 
from pedido AS p
where p.codigocliente = 1
order by p.codigopedido ASC;

/*seleciono todos os itens de um pedido do cliente */
select * 
from pedido AS p, produto AS pr, livro AS li
where p.codigocliente = 1 and
p.codigopedido = 21 and
pr.codigoproduto = li.codigoproduto
order by p.codigopedido ASC;


select * 
from pedido AS p, item_pedido AS it, produto AS pr, livro AS li
where p.codigopedido = it.codigopedido 
and it.codigoproduto = pr.codigoproduto
and li.codigoproduto = pr.codigoproduto
order by p.codigopedido ASC;


select * 
from pedido AS p, item_pedido AS it, produto AS pr, livro AS li
where p.codigopedido = it.codigopedido
and  p.codigopedido = 21
and it.codigoproduto = pr.codigoproduto
and li.codigoproduto = pr.codigoproduto
order by p.codigopedido ASC;

select * 
from pedido AS p, item_pedido AS it, livro AS li, produto AS pr
where p.codigopedido = it.codigopedido
and li.codigoproduto = it.codigoproduto
and pr.codigoproduto = it.codigoproduto
and  p.codigopedido = 20


order by p.codigopedido ASC;

select * 
from pedido AS p
where p.codigopedido = 21
order by p.codigopedido ASC;

select * 
from pagamentoboleto AS pb, pagamento AS p
where p.codigopedido = 21
and p.codigopagamento = pb.codigopagamento
order by p.codigopagamento ASC;

select * 
from pagamentocartao AS pb, pagamento AS p
where p.codigopedido = 38
and p.codigopagamento = pb.codigopagamento
order by p.codigopagamento ASC;

select * 
from entrega
where codigopedido = 21
order by codigoentrega ASC;
