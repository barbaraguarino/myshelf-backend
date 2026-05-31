# Backlog

Este documento centraliza as tarefas de desenvolvimento da API REST MyShelf. O fluxo de trabalho é sequencial, priorizando a base estrutural e a persistência de dados antes de expor os endpoints e aplicar a segurança.

## Pronto Para Execução

Esta seção contém as tarefas que devem ser executadas sequencialmente.

- [x] **Configuração Inicial e Conexão com o Banco**: Preparar o ambiente da aplicação para persistir dados e configurando o banco em memória (H2).
- [ ] **Tratamento Global de Exceções**: Interceptar os erros da aplicação (como campos nulos ou registros não encontrados) e devolver um JSON padronizado e amigável para o cliente HTTP, ocultando a *stacktrace* interna do Java.
- [ ] **Cadastro de Usuário**: Implementar a entidade de Usuário e a lógica de criação de contas, garantindo o encapsulamento e a segurança dos dados sensíveis (a senha não pode ser salva em texto puro).
- [ ] **Autenticação Simples (Login com JWT via Cookies)**: Configurar o Spring Security para blindar a API e permitir a autenticação do usuário, gerando um token JWT assinado para as requisições subsequentes via cookies.
- [ ] **Cadastro de Gêneros Literários**: Configurar as regras de negócios e estrutura básica com domínio e DTOs para persistir os gêneros literários no banco de dados.
- [ ] **Cadastro de Editoras**: Configurar as regras de negócios e estrutura básica com domínio e DTOs para persistir as editoras no banco de dados.
- [ ] **Cadastro de Autores**: Configurar as regras de negócios e estrutura básica com domínio e DTOs para persistir os autores no banco de dados.
- [ ] **Cadastro de Livros**: Configurar as regras de negócios e estrutura básica com domínio e DTOs para persistir os livros no banco de dados.


## Próximos Passos

Tarefas que serão puxadas para execução conforme a necessidade.

- **Listagem Paginada**: Implementação de endpoints `GET` para listar Autores, Editoras, Categorias e Livros. O foco será garantir paginação nativa e retorno otimizado via DTOs para evitar o problema de N+1 queries.
- **Busca por Identificador**: Endpoints `GET` específicos para retornar os detalhes minuciosos de um único registro.
- **Edição de Registros**: Endpoints `PUT` focados na atualização integral (substituição) das informações de Autores, Editoras, Categorias e Livros.
- **Deleção Segura**: Endpoints `DELETE` contendo regras de negócio defensivas para impedir a exclusão de categorias ou autores que já possuam vínculos ativos com livros (integridade referencial).