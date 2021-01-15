# Desafio do Mercado Livre

Neste desafio foi desenvolvido uma api para suportar parte do funcionamento do Mercado Livre.

## Requisitos

### Cadastro de novo usuário

+ Necessidades

        Precisamos saber o instante do cadastro, login e senha.

+ Restrições

        O instante não pode ser nulo e não pode ser no futuro
        O login não pode ser em branco ou nula
        O login precisa ter o formato do email
        A senha não pode ser branca ou nula
        A senha precisa ter no mínimo 6 caracteres
        A senha deve ser guardada usando algum algoritmo de hash da sua escolha.

+ Resultado esperado

        O usuário precisa estar criado no sistema
        O cliente que fez a requisição precisa saber que o usuário foi criado. Apenas um retorno com status 200 está suficente.

### Não podemos ter dois usuários com o mesmo email

+ Necessidades

        Só pode existir um usuário com o mesmo email.

+ Resultado esperado

        Status 400 informando que não foi possível realizar um cadastro com este email.

### Cadastro de categorias

+ Necessidades

No mercado livre você pode criar hierarquias de categorias livres. Ex: Tecnologia -> Celulares -> Smartphones -> Android,Ios etc. Perceba que o sistema precisa ser flexível o suficiente para que essas sequências sejam criadas.

        Toda categoria tem um nome
        A categoria pode ter uma categoria mãe

+ Restrições

        O nome da categoria é obrigatório
        O nome da categoria precisa ser único

+ Resultado esperado

        categoria criada e status 200 retornado pelo endpoint.
        caso exista erros de validação, o endpoint deve retornar 400 e o json dos erros.

### Autenticação

+ Necessidades

        Você precisa configurar um mecanismo de autenticação via token para permitir o login.

### Usuário logado cadastra novo produto

Deve permitir o cadastro de um produto por usuário logado.

+ Necessidades

        Tem um nome
        Tem um valor
        Tem quantidade disponível
        Características(cada produto pode ter um conjunto de características diferente)
        Da uma olhada nos detalhes de produtos diferentes do mercado livre.
        Tem uma descrição que vai ser feita usando Markdown
        Pertence a uma categoria
        Instante de cadastro

+ Restrições

        Nome é obrigatório
        Valor é obrigatório e maior que zero
        Quantidade é obrigatório e >= 0
        O produto possui pelo menos três características
        Descrição é obrigatória e tem máximo de 1000 caracteres.
        A categoria é obrigatória

+ Resultado esperado

        Um novo produto criado e status 200 retornado
        Caso dê erro de validação retorne 400 e o json dos erros

### Usuário logado adiciona imagem no seu produto

Com um produto cadastrado, um usuário logado pode adicionar imagens ao seu produto. Não precisa salvar a imagem em algum cloud ou no próprio sistema de arquivos. Cada arquivo de imagem pode virar um link ficticio que pode ser adicionado ao produto.

+ Necessidades

        Adicionar uma ou mais imagens a um determinado produto do próprio usuário

+ Restrições

        Tem uma ou mais fotos
        Só pode adicionar fotos ao produto que pertence ao próprio usuário

+ Resultado esperado

        Imagens adicionadas e 200 como retorno
        Caso dê erro de validação retorne 400 e o json dos erros
        Caso tente adicionar imagens a um produto que não é seu retorne 403.
