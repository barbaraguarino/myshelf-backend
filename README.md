# MyShelf

O **MyShelf** é uma API REST desenvolvida com **Spring Boot** para gerenciamento de um catálogo simples de livros.

O projeto foi criado como uma base de estudos para praticar **CRUD**, **autenticação simples**, organização de camadas e integração com diferentes tipos de frontend, como Android, Flutter, Angular, React e outros.


## Objetivo

O objetivo do projeto é servir como um backend simples, organizado e fácil de evoluir.

Inicialmente, o sistema trabalha com as principais entidades de um catálogo literário:

* Livro
* Autor
* Editora
* Categoria

A ideia é manter uma estrutura clara, que permita adicionar novas funcionalidades futuramente sem precisar recomeçar o projeto do zero.


## Funcionalidades

A proposta inicial do MyShelf inclui:

* Cadastro, listagem, atualização e remoção de livros
* Cadastro, listagem, atualização e remoção de autores
* Cadastro, listagem, atualização e remoção de editoras
* Cadastro, listagem, atualização e remoção de categorias
* Autenticação simples de usuários


## Tecnologias utilizadas

* Java
* Spring Boot
* Spring Web
* Spring Data JPA
* Spring Security
* Bean Validation
* Banco de dados relacional
* Maven

> As tecnologias podem ser ajustadas conforme a evolução do projeto.


## Estrutura do Projeto

O projeto segue uma organização em camadas, separando responsabilidades de forma simples e objetiva.

```text
br.com.myshelf.backend/
├── core/                  
└── modules/               
    ├── identity/          
    │   ├── api/           
    │   └── core/          
    │
    └── catalog/           
        ├── author/        
        │   ├── api/       
        │   ├── core/      
        │   └── data/      
        │
        ├── book/          
        │   ├── api/       
        │   ├── core/          
        │   └── data/        
        │
        └── genre/         
            ├── api/            
            └── core/             
```

## Como executar o projeto

### Pré-requisitos

Antes de iniciar, é necessário ter instalado:

* Java 25 ou superior
* Maven

### Clonar o repositório

```bash
git clone https://github.com/barbaraguarino/myshelf.git
cd myshelf
```


### Executar a aplicação

No Linux ou macOS:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

A aplicação será iniciada em:

```text
http://localhost:8080
```

## Autora

Desenvolvido por **Barbara Duarte**.

Este projeto faz parte dos meus estudos em desenvolvimento backend com Java e Spring Boot, servindo como base para praticar construção de APIs REST, autenticação, persistência de dados e integração com diferentes tecnologias frontend.

GitHub: [@barbaraguarino](https://github.com/barbaraguarino)



## Licença

Este projeto possui licença privada e todos os direitos são reservados.

O código-fonte está disponível apenas para fins de estudo, consulta e avaliação acadêmica, não sendo permitida a cópia, modificação, distribuição ou uso sem autorização prévia da autora.

Consulte o arquivo [LICENSE](./LICENSE) para mais detalhes.

