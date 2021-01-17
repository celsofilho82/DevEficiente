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

### Adicione uma opinião sobre um produto

Um usuário logado pode opinar sobre um produto. Claro que o melhor era que isso só pudesse ser feito depois da compra, mas podemos lidar com isso depois.

+ Necessidade

        Tem uma nota que vai de 1 a 5
        Tem um título. Ex: espetacular, horrível...
        Tem uma descrição
        O usuário logado que fez a pergunta
        O produto que para o qual a pergunta foi direcionada

+ Restrições

        A restrição óbvia é que a nota é no mínimo 1 e no máximo 5
        Título é obrigatório
        Descrição é obrigatório e tem no máximo 500 caracteres
        Usuário é obrigatório
        O produto relacionado é obrigatório

+ Resultado esperado

        Uma nova opinião é criada e status 200 é retornado
        Em caso de erro de validação, retorne 400 e o json com erros.

### Faça uma pergunta ao vendedor(a)

Um usuário logado pode fazer uma pergunta sobre o produto

+ Necessidade

        A pergunta tem um título
        Tem instante de criação
        O usuário que fez a pergunta
        O produto relacionado a pergunta
        O vendedor recebe um email com a pergunta nova e o link para a página de visualização do produto(ainda vai existir)
        O email não precisa ser de verdade. Pode ser apenas um print no console do servidor com o corpo.

+ Restrições

        O título é obrigatório
        O produto é obrigatório
        O usuário é obrigatório

+ Resultado esperado

        Uma nova pergunta é criada e a lista de perguntas, com a nova pergunta adicionada, é retornada. Status 200
        Em caso de erro de validação, retorne 400 e o json com erros.

### Montar uma página de detalhes para o produto

O frontend precisa montar essa página => <https://produto.mercadolivre.com.br/MLB-1279370191-bebedouro-bomba-eletrica-p-garrafo-galo-agua-recarregavel-_JM?quantity=1&variation=49037204722&onAttributesExp=true>

Não temos todas as informações, mas já temos bastante coisa. Faça, do jeito que achar melhor o código necessário para que o endpoint retorne as informações para que o front monte a página.

+ Resultado esperado

        Links para imagens
        Nome do produto
        Preço do produto
        Características do produto
        Des​crição do produto
        Média de notas do produto
        Número total de notas do produto
        Opiniões sobre o produto
        Perguntas para aquele produto

### Finalizando a compra de um produto - Parte 1

Aqui a gente vai simular uma integração com um gateway como paypal, pagseguro etc. O fluxo geralmente é o seguinte:

    O sistema registra uma nova compra e gera um identificador de compra que pode ser passado como argumento para o gateway.
    O cliente efetua o pagamento no gateway
    O gateway invoca uma url do sistema passando o identificador de compra do próprio sistema e as informações relativas a transação em si.

Então essa é a parte 1 do processo de finalização de compra. Onde apenas geramos a compra no sistema. Não precisamos da noção de um carrinho compra. Apenas temos o usuário logado comprando um produto.

+ Necessidades

  + A pessoa pode escolher a quantidade de itens daquele produto que ela quer comprar

      O estoque do produto é abatido

      Um email é enviado para a pessoa que é dona(o) do produto informando que um usuário realmente disse que queria comprar seu produto.
  + Uma compra é gerada informando o status INICIADA e com as seguintes informações:

      Gateway escolhido para pagamento

      Produto escolhido

      Quantidade

  + Comprador(a)
  + Suponha que o cliente pode escolher entre pagar com o Paypal ou Pagseguro.

+ Restrições

    A quantidade é obrigatória
    A quantidade é positiva
    Precisa ter estoque para realizar a compra​

+ Resultado esperado

  + Caso a pessoa escolha o paypal seu endpoint deve gerar o seguinte redirect(302):
      Retorne o endereço da seguinte maneira: paypal.com/{idGeradoDaCompra}?redirectUrl={urlRetornoAppPosPagamento}
  + Caso a pessoa escolha o pagseguro o seu endpoint deve gerar o seguinte redirect(302):
      Retorne o endereço da seguinte maneira: pagseguro.com?returnId={idGeradoDaCompra}&redirectUrl={urlRetornoAppPosPagamento}
  + Caso aconteça alguma restrição retorne um status 400 informando os problemas. 
