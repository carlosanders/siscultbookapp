SELECT NOW();

/*padrao brasileiro sem os segundos*/
SELECT to_char(NOW(),'DD/MM/YYYY HH24:MI');


SELECT to_char(NOW(),'YYYY-MM-DD HH24:MI:SS');

select ultimoacesso from funcionario;

UPDATE funcionario 
SET datademissao = (SELECT NOW())
where codigofuncionario = 1;


SELECT CURRENT_TIMESTAMP;
SELECT CURRENT_TIME(0);
SELECT CURRENT_TIMESTAMP(0);

SELECT TO_TIMESTAMP(NOW(),'YYYY-MM-DD HH24:MI:SS');


SELECT ultimoacesso, (to_char(ultimoacesso,'YYYY-MM-DD HH24:MI:SS')) AS br
FROM funcionario;


UPDATE livro 
SET figura = ''
where codigoproduto = 1;

