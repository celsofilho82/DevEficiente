# Jornada Dev

## Teoria da Carga Cognitiva

Estabelece que podemos aprender no melhor dos casos até 5 +/- 2 coisas ao mesmo tempo na nossa mémoria de trabalho(memória que utilizamos no nosso processo de aprendizado). Não adianta você forçar a barra achando que vai aprender milhões de coisas ao mesmo tempo. É importante limitar o número de coisas que se deseja aprender em um periódo de tempo para evitar que o processo de aprendizado seja frustrantes e que eu possa me sentir motivado a avançar cada vez mais no processo.

## Carga Intriseca

Antes de iniciar uma atividade tentar enteder quais os requisitos que você precisa ter para concluir aquela tarefa.
Exemplo: O que preciso saber para poder regar o jardim da minha casa?
1 - Preciso saber colocar a manqueira na torneira
2 - Preciso saber ligar a torneira.
Neste exemplo, demostramos uma atividade que tem uma carga intriseca baixa. Podemos estabelecer níveis de caraga intriseca para cada atividade que vamos desenvolver

## Divisão de Responsabilidade no Código

Com auxilio da Teoria da Carga Cognitiva e a Carga Intriseca vou utilizar essa técnica no código para desenvolvolver funcionalidade que possam ser melhor entendidas por pessoas que vão ler o meu código e dar manutenções futuras no código que vou implementar.

## Critérios para divisão de responsabilidades no código

### Acoplamentos com classes que são especificas do projeto

Conta como acoplamentos contextual as classes que foram criadas pelo próprio sistema. Classes do runtime da linguagem, frameworks etc devem ser conhecidas a priori

### Número de branchs que existem no código

Basicamente é um bloco de código que está dentro de estruturas como: if, else, for, switch, while e etc. Essa contagem de branch é conhecida como complexidade ciclomatica que é uma das primeiras métricas utilizadas para definir o nível de complexidade de entendimento do código.s

### Passando funções como argumentos

Passar funções como argumento também conta como pontos de carga intriseca na divisão de responsabilidades do código. Um exemplo que posso utilizar é a API de Streams que recebe muitas funções como argumento na sua utilização.

### Critérios de contagem de pontos:

Acoplamentos contextual: 1 ponto
Quantidade de Branchs: 1 ponto
Funções como parâmetros: 1 ponto

Podemos trabalhar com 7 +/- 2 como contagem máxima de carga intriseca do código que estou trabalhando para decidir dividir a responsabilidade.

## DDD - Domain Drive Design

Livro que aborda como podemos melhor desenvolver uma aplicação sobre o conceito de entendimento do código.

O entendimento é que desenvolver software é você automatizar o desejo de uma pessoa ou um grupo de pessoas e devemos entender esse desejo antes de começar a criar o código que vai automatizar esse desejo.

Link para artigos baseados no desenvolvimento de código guiado pela teoria da carga cognitiva:

+ [DDD para aplicações web modernas](https://medium.com/@albertosouza_47783/ddd-para-aplica%C3%A7%C3%B5es-web-modernas-2be654932497)
+ [Um outro olhar sobre complexidade de código](https://medium.com/@albertosouza_47783/um-outro-olhar-sobre-complexidade-de-c%C3%B3digo-16370f9f9c80)
+ [DDD da massa](https://github.com/asouza/pilares-design-codigo/blob/master/ddd-da-massa.md)

0 código ele deve ser entendido
1 - Pelo computador pra que seja funcional.
2 - Pelas pessoas que vão dar manutenção nele.

## Arquitetura x Design

Arquiteturas são genéricas e definem a forma estrutural de como as aplicações serão construidas ex: Padrão MVC
Design de código já é especifico e está atrelado a lógica que vamos utilziar para escrever as features da nossa aplicação. Escrever o código sobre a perspectiva de um bem design dividindo as responsabilidades e trabalhando para diminuir a carga intriseca é o grande desafio do programador.
Decisões de arquitetura ficam na maioria das vezes atreladas as framework de desenvolvimento escolhido para desenvolver o projeto.
Pensar no design do código é essêncial!!!


## Sugestão de pontuação limite para tipos de classes diferentes no sistema

Tipos de classes dentro de uma aplicação

+ Controllers 
+ Services
+ Classes que representam requests e responses
+ Entidades
+ Value objects
+ Classes de configuração
+ Classes que isolam código comum a toda aplicação

Cada uma das classes listada acima aparecem em todo tipo de sistema. O problema é que com esse nível de granularidade fina, você sempre vai ter classes diferentes. Um jeito mais fácil de olhar é da seguinte forma:

    Classes com atributos de dados(estado inteligente)​
    Classes com atributos de dependência(sem estado inteligente)
    Classes sem nenhum atributo(apenas métodos estáticos mesmo, basicamente são funções)

Classes com estado inteligente, geralmente têm manipulação de estado e são ​passíveis de terem mais lógica. Por isso a sugestão é deixá-las com um limite de 9 pontos. 

Classes sem estado inteligente, geralmente cuidam mais de fluxos, então a sugestão é limitar a 7 pontos. 

Voltando para uma aplicação web, temos a seguinte sugestão:

    Controllers com no máximo 7 pontos
    Services com no máximo 7 pontos
    Entidades com no máximo 9 pontos
    Classes de entrada e saída de dados com no máximo 9 pontos
    Classes de configuração e que isolam código para toda aplicação mais liberadas, já que são pouco mexidas. 


Classes inteligentes: São classes que possuem métodos que operam sobre os atributos da classe. Muitas vezes essas classes possuem mapeamentos para um banco de dados. Outro exeplo de classes inteligentes são as classes de formulário que são as classes que recebem parâmetros de uma requisição e realizam a conversão para o modelo de dominio da aplicação.

Classes que não tem estado inteligente: básicamente são classes que possuem somente funções que operam sobre determinada entrada de dados. Um exemplo de classe que não possui estado inteligente são os controllers

+ Relacionando as classes do Spring Framework com o DDD

@Service = classe que fala com o dominio da aplicação que é inspirada no Domain Service do DDD

@RestController = essa classe é inspirada do Aplication Service do DDD

@Entity = essa classe é inspirado nas classes Entity do DDD

@Embeddable = essa classe é inspirado nos Value Objects do DDD

## Pattern 1: Controllers 100% coesos

Coesão: siginifica o quanto dos atributos você está usando em cada um dos métodos que você tem na sua classe. Exemplo: se você possui uma classe com 3 atributos cada um dos métodos dessa classe devem fazer uso dos 3 atributos.

Classes com estado inteligente dificilmente são 100% coesas.

Um controller é apenas um recipiente de rotas que representam os endpoints expostos nas aplicações. Ele não mantém estado da aplicação e seus atributos deveriam ser, na verdade, variáveis locais de seus métodos. Uma rota é uma função, que recebe uma entrada e gera uma saída. 

Todo método de um controller deve se utilizar de stodos os atributos declarados.

Obter 100% do coesão nos controllers permite dividir o controller em mais de um arquivo para garantir que todos os atributos serão utilizados nos métodos desse controller.

Dividir responsabilidade pelo olhar da carga cognitiva

## Pattern 2: Services 100% coesos

A ideia é que todo service, por ser uma classe sem estado inteligente(atributos de dependência), seja 100% coeso. Todos os atributos sejam utilizados por todos os métodos que são configurados para lidar com requisições para a aplicação.

Não tem nada a ver com ter apenas um método. Você pode ter quantos quiser, portanto que respeite a restrição da coesão + limite de pontuação máxima para classes sem estado inteligente.

Service 100% coesos geralmente aparece em duas situações:

O limite de 7 pontos do controller foi estourado e você precisa dividir a responsabilidade. Geralmente essa classe nova tende a ser um service do DDD

A entrada de dados do sistema não é feita apenas pela web e agora você mais de um ponto de entrada. O código pode ser isolado para ser reaproveitado. Geralmente essa classe também vai ser um Service do DDD.

Número de linhas em um arquivo é uma forma equivocada de definir a complexidade do código daquele arquivo.

O que você vai mover para um service é menos importante do que a identificação do que você tem que mover.

Os Services fazem parte do seu domain model pois eles executam uma parte da lógica do seu dominio mas eles não são controllers e nem entidades pois não possuem estado inteligente.

## Pattern 3: Form value object

São classes que representam as requisiões de informação enviadas nos requests do protocolo HTTP e por motivos de segurança e de fragilidade de contrato com as aplicações clientes, preferimos criar classes para representarem essas requisições.

Essas classes(também chamadas de DTO) se enquadram na definição de classes inteligentes (aquelas que tem atributos de estado). Como elas são inteligentes, faz todo sentido tirarmos proveito da base orientação a objetos que é juntar comportamento com estado.

Geralmente usamos uma outra classe converter que obtem os dados da classe Form Object e convert esses dados para um objeto do seu dominio

Classes Form value object são extremamente especificas porque precisamos criar uma nova classe especifica para trabalhar sobre os dados dessa classe se tudo que precisamos já está presente nela?

Podemos criar uma método dentro da classe Form object para fazer o mapeamento e a persistência dos nossos dados dentro do modelo do nosso dominio

## O papel dos testes automatizados no nosso código

Os testes devem funcionar como revelador de bugs em sua aplicação garantindo comportamentos. Devemos ter 100% de cobertura em partes do nosso código onde existem condicionais e branchs. Devemos ter cuidado com os mocks para garantir que o retorno não seja tão genérico e sendo o mais especifico possível para não invalidar algumas condições no nosso teste.

