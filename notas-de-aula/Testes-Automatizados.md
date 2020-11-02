# Testes Automatizados

Hoje em dia olhando para a industria de software, escrevemos testes apenas com o intuito de verificar se o código que desenvolvemos está correto e também para aumentar a confiabilidade do software e permitir que após uma mudança, aquilo que escrevemos anteriormente continua funcionando.

Já na acadêmia, quando colocamos um determinado código sobre testes, desejamos descobrir se naquele código existem bugs e essa é a principal diferença entre as abordagems.

Desenvolver uma forma sistematica de escrever testes com o objetivo de revelar bugs é um objetivo que devemos alcançar.

## Testes: Mitos x Realidade

Aplicar testes automatizados não é garantia de que o seu software será mais confiável e bugs serão revelados em tempo de desenvolvimento. 

Testes automatizados não aumentam a confiabilidade do código mas é uma aposta que faz sentido.

O papel do analista de QA na industria ainda é importante pois, é o profissional que consegue aplicar testes de forma sistemática no código em desenvolvimento.

Testes baseados apenas em cobertura de linhas de código geralmente não trazem muita efetividade no quesito confiabilidade e descoberta de bugs no código.

Escrever testes usando a técnica do TDD não é garantia de boa qualidade no código mas podemos utilzar como uma forma de planejamento antes do desenvolvimento da features pois, no TDD pensamos mais sobre um determinado problema que é o efeito obtido por fazer os testes antes de desenvolver.

Uma outra técnica que tem o mesmo resultado do TDD é começar o desenvolvimento pelas bordas ou seja, sempre na parte em que estamos recebendo os dados.

## Testes de Unidade

Testes de unidade é quando escolhemos uma unidade do nosso código e colocamos a mesma sobre testes e vamos definir como unidade, sempre o ponto de entrada para execução de alguma lógica nesse caso um método.

Testes integrados, são testes efetuados com integração com outos sistemas como por exemplo banco de dados. Nesse caso é indispensável que todos os sistema de integração estejam disponíveis para efetuarmos os testes.

Testes de sistemas, são os que mais se aproxima do uso real do software onde inserimos os dados de entrada e obtemos uma determinada saida esperada.

## Técnicas para desenvolver bons testes de unidade

### Técnica 1 - Specification Based Testes

Pensar nos possíveis casos de testes baseados apenas em uma especificação.

Passo necessários

1 - Identificar os parâmetros ou entradas do programa ex: os parâmetros da sua classe ou métodos recebidos
2 - Derivar as caracteristicas de cada um dos parâmetros
3 - Depois, realizar as combinações dos inputs do dados buscando sempre valores de equivalência.

### Técnica 2 - Boundary testing

Caso tenhamos uma condicional(if) devemos escrever casos de testes que entrem na condicional e casos que não entrem na condicional. No boundary testes definimos quais valores devemos utilizar para entra ou não nessa condição. 
in-points = são valores que fazem a condição ser verdadeira
of-poings = são valores que fazem a condição ser falsa

A literatura diz que os bugs são revelados com valores próximos as bordas dos valores de in-point.

### Técnica 3 - Structural testing

Técnica mais utilizada em times de desenvolvimento. A ideia dessa técnica é que a gente decida quais partes vamos testar analisando o código fonte. Exploramos cobertura por linhas de código, branches, condicionais, combinação de branches e condicionais.

#### Structural testing: Cobertua por linhas de código

Cobertura mais básica e ainda muito utilizada nas equipes. Possui algumas fragilidades pois a métrica por cobertura de linha de código não é muito eficiente e não garante uma confiabilidade do código testado.

JaCoCo é a ferramenta que dá uma visão persentual sobre a cobertura de código dos testes que estamos desenvolvendo

#### Structural testing: Cobertura por branches

Uma maneira um pouco mais robusta de cobrir código com teste é criar testes para todas branches de código. Com essa técnica desejamos garantir todos os pontos de tomada de decisão do nosso código

#### Structural testing: Cobertura por condicionais

O objetivo é que cada condicional(&&, ||, !=) dentro de uma branch seja testada de maneira isolada. A vantagem em relação a cobertura por branch que é garantimos que cada condição é testada, mas a desvantagem é que tais testes podem não pegar todas possíveis execuções do fluxo. 

#### Structural testing: Cobertura por branch + condicional

Combinação entre cobertura por branch + condicional dessa forma cobrimos os fluxos de execução criados por nós olhando para possíveis combinações das condicionais.

#### Structural testing: Cobertura por todos os caminhos(path coverage)

Nesta técnica primeiro, analisamos todas as possíveis combinações de condicionais dentro de uma branch e depois entramos no detalhe de como escolher as combinações mais interessantes.

MCDC = Modified Condition Decision Coverage. 

Baseado na quantidade de condicionais no teste a quantidade de testes necessárias para cobrir todo o path é N + 1 ou seja, se tivermos 4 condicionais teriamos que gerar 5 testes para cobrir todas as condições.

### Técnica 4 - Self testing

Técnica que tem como base o design by contact e é pouco explorada pelos códigos de produção. O self testing é uma proposta do seu código testar-se sem a necessidade de criar um teste automatizado.

Podemos inserir pré e pós condição em nossos métodos para validar principlamente os parâmetros recebidos e evitar erros como nullpointer.

Podemos inserir também a invariância que é um método que permite verificar no objeto se determidados atributos possuem valor e caso contrario deve lançar uma violação na execução.

Uma combinação de técnicas que vai dar poder para sua suite de testes e não a escolha por apenas uma técnica.

## A pirâmide de testes

A pirâmide de testes como o nome sugere é uma sequência de testes cujo na base nos temos os testes de unidade, depois testes de integração, testes de sistema e por último os testes manuais. A complexidade e o custo aumentam enquanto subimos a pirâmide dos testes.

## Testes de unidade o mais integrado possível

O objetivo é escrever testes de unidade mais próximos dos testes de integração mesmo sabendo que os testes de unidade estão na base da pirâmide de testes e por consequência mais distantes dos testes de integração mas utilizando algumas técnicas exite espaço para deixa-los mais conectados mantendo a facilidade de escrita e velocidade no desenvolvimento.

Evitar os mocks e utilizar as classes reais do projeto ou do framework utilizado é a melhor opção

## Mocks: como, quando e até onde usar

A ideia de imitar o comportamento de métodos de classes para facilitar a escrita dos testes de unidade é muito tentadora. Entretanto devemos ficar sempre alerta ao seguinte fato: Um teste de unidade já tem a tendência a se distanciar da realidade de execução, se você ainda exagerar no uso do mock, ele vai ficar mais distante ainda beirando a inutilidade.

A utilização de Mocks é uma carta que você tem na manga principalmente quando testar uma unidade que depende de um sistema externo. Mas a ideia é minimizar o uso da utilização das expectativas e minimizar ainda o uso dos matchers.

## Design de código voltado para testabilidade

O que podemos fazer no nosso código para que o teste de unidade seja mais facilmente escrito? 

Vamos definir sistematicamente uma maneira de diminuir a complexidade dos testes 

Para facilitar os testes, toda a classe que precisa ser testada (classe onde temos uma branch ou condicionais) deve receber os atributos de dependência pelo construtor como um controller ou um service. Evitamos nessas classe o @Autowired

É importante entender que classes de projetos\frameworks que não foram preparadas pensando na testabilidade seja evitado usar mocks

## Conclusão

O objetivo que deve ser traçado é elevar a régua dos testes de unidade automatizados, buscando escrever cada um de maneira sistemática. Em relação a cobertura a sugestão é focar literalmente em só cobrir com testes métodos que tenham branches escritas Ou seja, um método que tem apenas um fluxo, por esse olhar, pode ser negligenciado. A não ser que ele seja tocado por um teste de unidade em cima de uma  branch. Não adianta cobrir um monte de linha com testes de unidade enquanto que as "principais" linhas não estão tão bem cobertas 

A maioria dos bugs estão nas branchs e condicionais onde ocorre a quebra do fluxo do código então, todas as quebras de fluxo escritas pelo desenvolvedor devem ser cobertas por testes.

Buscar testes de unidade o mais integrados possível dessa forma garantimos que os testes de unidade fiquem mais próximos da realidade.

Também é preciso dominar a ferramenta de testes que possibilita a criação de mocks e asserts e com muito treino e utilizando as técnicas descritas, você consegue realizar os de maneira mais fácil fazendo valer a pena.