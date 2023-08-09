Para rodar o aplicativo, basta abrir o terminal dentro da pasta docker localizada neste repositorio e rodar o comando "docker-compose up -d" ou rodar o bat com o
comando que ja esta na pasta, tendo o docker instalado em sua maquina. Ele subira um container contendo o banco de dados Postgres e a aplição .jar rodando na porta 8080.
O banco é estruturado utilizando o Liquibase, entao sua criação sobe junto da aplicação.
Gostaria de ter feito mais testes unitarios, principalmente da classe Message para validação das exceptions, porem devido a jornada de trabalho estou com pouco 
tempo livre.
A collection do postman se encontra na pasta Postman deste repositorio, nela vai encontrar as requisições com exemplos ja prontos do JSON a ser trafegado.

Tambem é possivel rodar a aplicação diretamente pela IDE, basta mudar a url do JDBC e apontar para o seu banco.