select *
from livro AS l, produto AS p, editora AS e
where p.codigoproduto = l.codigoproduto
and l.codigoeditora = e.codigoeditora
and l.assunto = 'Ciências'
order by l.codigoproduto ASC;

select *
from livro AS l, produto AS p, editora AS e
where p.codigoproduto = l.codigoproduto
and l.codigoeditora = e.codigoeditora
and l.isbn = 'ISBN'
order by l.codigoproduto ASC;

select *
from livro AS l, produto AS p, editora AS e
where p.codigoproduto = l.codigoproduto
and l.codigoeditora = e.codigoeditora
and UPPER(l.isbn) LIKE UPPER('%is%')
order by l.codigoproduto ASC;

select *
from livro AS l, produto AS p, editora AS e
where p.codigoproduto = l.codigoproduto
and l.codigoeditora = e.codigoeditora
and l.isbn LIKE '%IS%'
order by l.codigoproduto ASC;

select *
from livro AS l, produto AS p, editora AS e
where p.codigoproduto = l.codigoproduto
and l.codigoeditora = e.codigoeditora
and l.titulo LIKE '%R%'
order by l.codigoproduto ASC;

