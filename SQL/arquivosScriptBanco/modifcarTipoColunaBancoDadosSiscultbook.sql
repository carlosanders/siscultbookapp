ALTER TABLE cliente ALTER COLUMN telefone TYPE VARCHAR(25);

ALTER TABLE livro ALTER COLUMN anopublicacao TYPE VARCHAR(4);


ALTER TABLE cliente ALTER COLUMN ultimoacesso TYPE timestamp;


select *
from livro;

ALTER TABLE livro ADD COLUMN pagPrincipal CHAR(3);

select *
from livro l, produto p, editora e
where l.codigoproduto = p.codigoproduto
and l.codigoeditora = e.codigoeditora
and pagprincipal = 'sim';


/* passando para unico o campo cpf do cliente */
ALTER TABLE cliente ADD CONSTRAINT unique_cpf_cliente UNIQUE (cpf);
ALTER TABLE funcionario ADD CONSTRAINT unique_cpf_funcionario UNIQUE (cpf);
ALTER TABLE autor ADD CONSTRAINT unique_cpf_autor UNIQUE (cpf);
ALTER TABLE editora ADD CONSTRAINT unique_cnpj_editora UNIQUE (cnpj);