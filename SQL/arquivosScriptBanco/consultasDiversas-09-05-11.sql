SELECT * FROM acesso AS a JOIN acesso_nivel AS an 
ON a.codigoacesso = an.codigoacesso 
AND a.comando not in (Select ac.comando from acesso AS ac WHERE ac.comando LIKE 'cadastrar%')
AND a.comando not in (Select ac.comando from acesso AS ac WHERE ac.comando LIKE 'excluir%')
AND a.comando not in (Select ac.comando from acesso AS ac WHERE ac.comando LIKE 'atualizar%')
AND an.codigoniveldeacesso = '1' 
ORDER BY a.comando DESC;

SELECT * FROM acesso AS a JOIN acesso_nivel AS an 
ON a.codigoacesso = an.codigoacesso 
AND a.comando not in (Select ac.comando from acesso AS ac WHERE ac.comando LIKE 'cadastrar%')
AND a.comando not in (Select ac.comando from acesso AS ac WHERE ac.comando LIKE 'excluir%')
AND a.comando not in (Select ac.comando from acesso AS ac WHERE ac.comando LIKE 'atualizar%')
AND a.comando not in (Select ac.comando from acesso AS ac WHERE ac.comando LIKE 'autorizar%')
AND an.codigoniveldeacesso = '1' 
ORDER BY a.codigoacesso ASC;


SELECT * FROM acesso AS a JOIN acesso_nivel AS an 
ON a.codigoacesso = an.codigoacesso 
AND a.comando ILIKE 'editar%'
AND a.comando ILIKE 'consultar%'
AND an.codigoniveldeacesso = '1' 
ORDER BY an.codigoniveldeacesso;

SELECT * FROM acesso AS a JOIN acesso_nivel AS an ON a.codigoacesso = an.codigoacesso AND an.codigoniveldeacesso = '1' ORDER BY an.codigoniveldeacesso

Select ac.comando 
from acesso AS ac 
WHERE ac.comando LIKE 'cadastrar%' 
and ac.comando not in (Select ad.comando from acesso AS ad WHERE ad.comando LIKE 'excluir%');

INSERT INTO acesso (comando, descricao) VALUES ('editarAcesso', 'Cadastrar Nível Acesso');
INSERT INTO acesso (comando, descricao) VALUES ('atualizarAcesso', 'Listar Nível Acessos');

select * from acesso;

SELECT * FROM acesso AS a LEFT JOIN acesso_nivel AS an
ON a.codigoacesso = an.codigoacesso
AND an.codigoniveldeacesso = 3
ORDER BY a.codigoacesso ASC;



