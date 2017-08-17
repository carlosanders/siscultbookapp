SELECT * 
FROM funcionario AS f
where f.codigofuncionario 
not in (Select ff.codigofuncionario from funcionario AS ff where ff.codigofuncionario = 1);