# Introdução Cognitive Driven Development

O objetivo dessa teoria é você desenvolver o seu código pensando sempre em facilitar o entendimento em cada uma das classes implementadas juntando a teoria de carga cognitiva com o código.

+ Primeiro artigo oficial sobre CDD

https://github.com/asouza/pilares-design-codigo/blob/master/ICSME-2020-cognitive-driven-development.pdf

+ Ebook que contém um capítulo com o artigo traduzido para o português

https://drive.google.com/file/d/1jJjf3JL-IXBEKVEVXKp0E9tlhKW2f2mC/view

## Sugestão de métrica derivada do CDD

Métricas que vamos utilizar para pontuar o nível de entendimento de um classe.

A ideia é limitar a complexidade deixando em níveis baixos baseada na teoria da carga coginitva.

### Esquema de contagem

Tentar identificar itens dentro do seu código que contam pontos de complexidade de entendimento.

- Pré-requisitos
As pessoas devem ter conhecimento das tecnologias que são transversais(ex: classes da propria linguagem ou do framework)

1 ponto - cada acoplamento com classes específicas do projeto que você encontrar
1 ponto - para cada branch de código encontrada (if, else, ternário, catch, try, loops) 
1 ponto - para cada função como argumento

Limites:

Classes que possuem atributos de dependência(atributos que não são manipulaveis) - limite máxima de 7 pontos
Classes que possuem atributos de estado - limite máximo de 9 pontos

## Derivando métricas do CDD para outros cenários

Na métrica proposta não são incluidos as classes transversais(classes da própria linguagem ou do framework). Podemos em um outro cenário incluir essas classes na contagem dos pontos, baixando a régua de entrada no projeto pois assumimos que as pessoas não precisam já ter conhecimento prévio das API´s da linguagem ou do framework mas mantendo os mesmos números dos níveis de complexidade do código.

### CDD aplicado para cenários de código legado

Quando vamos aplicar o CDD em código legado já teremos os pontos de complexidade definidos e apartir dele vamos trabalhar em sua redução o que pode ser muito difícil, então o ideal é trabalhar com percentual de redução da complexidade ao invés de trabalhar com números em absoluto.

### Design escalável e sustentável

O CDD traz uma maneira sistemática de você olhar para o código e saber quando precisa dividir responsabilidade ou não. Ter uma forma sistemática de resolver algo permite que isso seja aplicável para softwares e equipes de qualquer tamanho!

Isso lhe dá maneiras de controlar a complexidade do código a curto, médio e longo prazo.

### A relação do CDD com tudo que você já conhece

As métricas derivadas do CDD limitam a complexidade, não impõem uma forma de programar. Podemos continuar utilizando as arquiteturas de software que já definimos nos projetos.  

### Conclusão

O CDD lhe proporcioma balizes para você diminuir a complexidade do seu código você pode usar qualquer arquitetura ou padrão dentro dessas balizes.