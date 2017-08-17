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
