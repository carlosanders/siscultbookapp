# Projeto Final Faculdade Estácio Sá

Aplicação de venda de livros online.

## Tecnologias Utilizadas

### Preparação do ambiente

### Configurações
Para gerar os relatórios do projeto e especificar a localização das imagens do projeto tem alterar o arquivo: `web.xml`.
```xml
...
<servlet>
        <servlet-name>SisCultbookController</servlet-name>
        <servlet-class>br.com.siscultbook.controller.SisCultbookController</servlet-class>
        <init-param>
            <param-name>dir-imagens</param-name>
            <!-- Ambiente CASA  -->
            <!--<param-value>C:/Users/Carlos/Documents/NetBeansProjects/SisCultbookApp/web</param-value>-->
            <param-value>D:/01.desenvolvimento/java/projetos_olds/SisCultbookApp/web</param-value>
            <!-- Ambiente produção 
            <param-value>C:/Program Files/Apache Software Foundation/Tomcat 6.0/webapps/SisCultbookApp</param-value>
            -->
        </init-param>
        <init-param>
            <param-name>dir-relatorios</param-name>
            <!--  caminho casa -->
            <param-value>D:/01.desenvolvimento/java/projetos_olds/SisCultbookApp/build/web/WEB-INF/classes/br/com/siscultbook/relatorios</param-value>
            <!--  Ambiente de Produção 
            <param-value>C:/Program Files/Apache Software Foundation/Tomcat 6.0/webapps/SisCultbookApp/WEB-INF/classes/br/com/siscultbook/relatorios</param-value>
            -->
        </init-param>
    </servlet>
...    
```


### Dependências do Projeto
Lista de bibliotecas utilizadas pelo projeto

```
commons-beanutils-1.8.2.jar
commons-collections-3.2.1.jar
commons-digester-1.7.jar
commons-fileupload-1.2.2.jar
commons-io-2.0.1.jar
commons-javaflow-20060411.jar
commons-lang-2.4.jar
commons-logging-1.1.jar
commons-math-1.0.jar
commons-pool-1.3.jar
commons-vfs-1.0.jar
groovy-all-1.7.5.jar
iText-2.1.7.jar
jasperreports-4.0.2.jar
jasperreports-applet-4.0.2.jar
jasperreports-chart-themes-4.0.2.jar
jasperreports-extensions-3.5.3.jar
jasperreports-fonts-4.0.2.jar
jasperreports-javaflow-4.0.2.jar
log4j-1.2.15.jar
spring.jar
```
