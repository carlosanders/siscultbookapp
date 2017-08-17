SELECT * 
FROM cliente AS c
WHERE cpf = '07504022705'
and c.status = 'Ativo';

SELECT * 
FROM funcionario AS f
WHERE f.cpf = '07504022706'
and f.status = 'Ativo';


 SELECT * FROM funcionario WHERE cpf = '07504022706' AND status = 'Ativo';