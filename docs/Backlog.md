# Backlog

Este documento centraliza as tarefas de desenvolvimento da API REST MyShelf. O fluxo de trabalho é sequencial, priorizando a base estrutural e a persistência de dados antes de expor os endpoints e aplicar a segurança.

## Pronto Para Execução

Esta seção contém as tarefas que devem ser executadas sequencialmente.

- [x] **Configuração Inicial e Conexão com o Banco**: Preparar o ambiente da aplicação para persistir dados e configurando o banco em memória (H2).
- [x] **Tratamento Global de Exceções**: Interceptar os erros da aplicação (como campos nulos ou registros não encontrados) e devolver um JSON padronizado e amigável para o cliente HTTP, ocultando a *stacktrace* interna do Java.
- [x] **Cadastro de Usuário**: Implementar a entidade de Usuário e a lógica de criação de contas, garantindo o encapsulamento e a segurança dos dados sensíveis (a senha não pode ser salva em texto puro).
- [x] **Autenticação Simples (Login com JWT via Cookies)**: Configurar o Spring Security para blindar a API e permitir a autenticação do usuário, gerando um token JWT assinado para as requisições subsequentes via cookies.
- [x] **Cadastro de Gêneros Literários**: Configurar as regras de negócios e estrutura básica com domínio e DTOs para persistir os gêneros literários no banco de dados.
- [x] **Cadastro de Editoras**: Configurar as regras de negócios e estrutura básica com domínio e DTOs para persistir as editoras no banco de dados.
- [x] **Cadastro de Autores**: Configurar as regras de negócios e estrutura básica com domínio e DTOs para persistir os autores no banco de dados.
- [x] **Cadastro de Livros**: Configurar as regras de negócios e estrutura básica com domínio e DTOs para persistir os livros no banco de dados.
- [x] **Listagem Paginada de Gêneros Literários**: Implementar endpoint GET para listar os gêneros cadastrados, garantindo paginação nativa e retorno otimizado via DTOs.
- [x] **Listagem Paginada de Editoras**: Implementar endpoint GET para listar as editoras, garantindo paginação nativa e retorno otimizado via DTOs.
- [ ] **Listagem Paginada de Autores**: Implementar endpoint GET para listar os autores, garantindo paginação nativa e retorno otimizado via DTOs.
- [ ] **Listagem Paginada de Livros**: Implementar endpoint GET para listar os livros, garantindo paginação nativa e retorno otimizado via DTOs para evitar o problema de N+1 queries.
- [ ] **Busca por Identificador de Editora**: Implementar endpoint GET para retornar os detalhes de uma única editora, garantindo a requisição minuciosa de um único registro.
- [ ] **Busca por Identificador de Autores**: Implementar endpoint GET para retornar os detalhes de um único autor, garantindo a requisição minuciosa de um único registro.
- [ ] **Busca por Identificador de Gêneros Literários**: Implementar endpoint GET para retornar os detalhes de um único gênero literário, garantindo a requisição minuciosa de um único registro.
- [ ] **Busca por Identificador de Livros**: Implementar endpoint GET para retornar os detalhes de um único livro, garantindo a requisição minuciosa de um único registro.

## Próximos Passos

Tarefas que serão puxadas para execução conforme a necessidade.

- **Edição de Registros**: Endpoints `PUT` focados na atualização integral (substituição) das informações de Autores, Editoras, Categorias e Livros.
- **Deleção Segura**: Endpoints `DELETE` contendo regras de negócio defensivas para impedir a exclusão de categorias ou autores que já possuam vínculos ativos com livros (integridade referencial).