# Desafio da Casa do Código

Neste desafio foi desenvolvido uma api para suportar parte do funcionamento da Livraria Casa do Código.

O objetivo é trabalhar o design do código, criando código que seja suficiente para a funcionalidade e questionar de maneira propositiva os padrões já estabelecidos no mercado.

## Requisitos

### Cadastro de um novo autor

+ Necessidades

        É necessário cadastrar um novo autor no sistema. Todo autor tem um nome, email e uma descrição. Também queremos saber o instante exato que ele foi registrado.

+ Restrições

        O instante não pode ser nulo
        O email é obrigatório
        O email tem que ter formato válido
        O nome é obrigatório
        A descrição é obrigatória e não pode passar de 400 caracteres

+ Resultado esperado

        Um novo autor criado e status 200 retornado

### Email do autor é único

+ Necessidades

        O email do autor precisa ser único no sistema

+ Resultado esperado

        Erro de validação no caso de email duplicado

### Cadastro de uma categoria

+ Necessidades

        Toda categoria precisa de um nome

+ Restrições

        O nome é obrigatório
        O nome não pode ser duplicado

+ resultado esperado

        Uma nova categoria cadastrada no sistema e status 200 retorno
        Caso alguma restrição não seja atendida, retorne 400 e um json informando os problemas de validação
