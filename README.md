# Escriba API

### Desafio Técnico para Escriba Informática Ltda

## Autor

- [Mateus Oliveira de Souza](https://github.com/mateuskih)

Repositório contendo o código fonte do projeto escriba-api

## Tecnologias

* Java 11
* JPA Hibernate
* Postgre SQL
* Spring Boot
* Swagger
* Eclipse

## Instalação em ambiente developer

Necessário [Eclipse](https://www.eclipse.org/downloads/)

```sh
Import project Eclipse
```

```
Configuração das variaveis ambientes disponíveis no arquivo application.example.properties
```

## Projeto
#### Projeto Java
#### Implemente um micro-serviço para uma API considerando
```
* Requisitos técnicos:
    Utilizar implementação em n-camadas
    Aplicação deverá rodar na porta 9564
    Aplicação dos migrations necessários utilizando Flyway
    Utilizar OO e demais conceitos arquiteturais que considerar relevante
    Utilizar OpenJDK 11
    Caso preferir, poderá utilizar as notações Lombok
    * Todos os cadastros deverão ter:
        Listagem de todos os registros com paginação de 10 registros por página, resultando apenas os campos Id e Nome
        Consulta individual de um registro por ID, resultando todos os campos do cadastro
        Inclusão de novo registro
        Alteração de registro por ID
        Exclusão de registro por ID
        * Validações necessárias
            Validar integridade referencial na exclusão e apresentar a mensagem “Registro utilizado em outro cadastro.” quando não for possível a sua exclusão, quando relevante. 
            Validar tentativa de inclusão e alteração de registro com nome duplicado, caso exista apresentar a mensagem “Nome já informado no registro com código {id}.”
            Quando ocorrer a tentativa de incluir um registro com id duplicado apresentar a mensagem “Registro já cadastrado”
```
### Criar um cadastro situações do cartório, onde:
```
* Campos necessários
    ID do tipo String possibilitando até 20 caracteres (obrigatório)
    Nome do tipo String possibilitando até 50 caracteres (obrigatório)
* Carga inicial
    Via migration, realizar as inclusões necessárias para inclusão das situações iniciais, “Ativo” e “Bloqueado”, com os respetivos IDs “SIT_ATIVO” e “SIT_BLOQUEADO”

```

### Criar um cadastro de atribuições do cartório, onde:
```
* Campos necessários:
    ID do tipo String possibilitando até 20 caracteres (obrigatório)
    Nome do tipo String possibilitando até 50 caracteres (obrigatório)
    Situação do tipo booleano com valor padrão TRUE (obrigatório)

```

### Criar um cadastro de cartórios, onde:
```
* Campos necessários
    ID do tipo Int, aceitando apenas maiores que zero (obrigatório)
    Nome do tipo String possibilitando até 150 caracteres (obrigatório)
    Observação tipo String possibilitando até 250 caracteres (não obrigatório)
    Situação do Cartório (obrigatório)
    Lista de atribuições do cartório (obrigatório pelo menos uma atribuição)

```


## Endpoints / Documentação
* Situação
![](https://i.imgur.com/bFiKbXe.png)
* Atribuição
![](https://i.imgur.com/JuN3g90.png)
* Cartorio
![](https://i.imgur.com/cVUzjYx.png)


## Conhecimentos de SQL

### Com base no diagrama em anexo, escreva o comando SQL que você usaria para:

### 1) Apresentar todos os processos cujo tipo de evento seja “Processo cível”:
    ```
    SELECT * FROM Processo P INNER JOIN TipoEvento TE ON P.Po_Evento = TE.Te_Id WHERE TE.Te_Descricao = 'Processo cível';
    ```
2) Apresentar todos os cartórios que possuem processos recebidos da “5ª vara da família”:
    ```
    SELECT DISTINCT Ca.* FROM Cartorio Ca INNER JOIN Processo P ON Ca.Ca_Id = P.Po_Cartorio INNER JOIN Vara V ON P.Po_Vara = V.Va_Id WHERE V.Va_Denominacao = '5ª vara da família';
    ```
3) Apresentar a soma do valor da causa de todos os processos em nome do réu “João da Silva”:
    ```
    SELECT SUM(Pc_ValorCausa) AS Total_Valor_Causa FROM Processo P INNER JOIN Reu R ON P.Pe_Reu = R.Re_Id WHERE R.Re_Nome = 'João da Silva';
    ```
4) Apresentar todos os réus cujo nome começa com “J”:
    ```
    SELECT * FROM Reu WHERE Re_Nome LIKE 'J%';
    ```
5) Apresentar todos os processos cujo nome do réu contenha a string “Pereira” em qualquer posição:
    ```
    SELECT * FROM Processo P INNER JOIN Reu R ON P.Pe_Reu = R.Re_Id WHERE R.Re_Nome LIKE '%Pereira%';
    ```
6) Apresentar o processo Pc_Id = 8 buscando o tipo do evento com LEFT JOIN:
    ```
		SELECT P.*, TE.Te_Descricao AS Tipo_Evento FROM Processo P LEFT JOIN TipoEvento TE ON P.Po_Evento = TE.Te_Id WHERE P.Pr_Id = 8;
    ```
7) Excluir todos os documentos do réu Re_Id = 8:
    ```
		DELETE FROM Documentos_Reu WHERE Rr_Reu = 8;
    ```
8) Salvar o telefone “555-5555” no cartório “1º Ofício”:
    ```
		UPDATE Cartorio SET Ca_Telefone = '555-5555' WHERE Ca_Denominacao = '1º Ofício';
    ```
9) Suponha que um novo cliente solicite o desenvolvimento de um sistema de cadastro de fornecedores. O novo programa precisará ter apenas duas janelas: um cadastro de fornecedores e uma lista de produtos usados pela empresa. O programa deverá permitir que um fornecedor esteja relacionado a mais do que um produto e, consequentemente, que um mesmo produto esteja relacionado a mais de um fornecedor. O sistema também deve ser capaz de informar o preço que determinado fornecedor cobra pela venda de um produto específico. Desenhe abaixo as tabelas de banco de dados que você criaria para salvar os dados do sistema.
![](https://i.imgur.com/icrQBEv.png)


## License

MIT

**Free Software, Hell Yeah!**


