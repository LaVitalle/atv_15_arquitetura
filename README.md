# Atividade 015 - Padrão Strategy

Refatoração do sistema de cálculo de frete utilizando o padrão **Strategy**, incluindo o desafio adicional da entrega econômica.

## Estrutura

| Classe | Papel |
|---|---|
| `ShippingStrategy` | Estratégia: abstração com `calculate(double weight)` |
| `StandardShipping` | R$ 10,00 + R$ 2,00 por kg |
| `ExpressShipping` | R$ 20,00 + R$ 5,00 por kg |
| `StorePickup` | Retirada na loja, sem frete |
| `EconomyShipping` | R$ 5,00 + R$ 1,50 por kg (desafio adicional) |
| `ShippingCalculator` | Contexto: recebe a estratégia pelo construtor, permite trocá-la com `setStrategy` e delega o cálculo |
| `Main` | Cliente: escolhe as estratégias e demonstra a troca na mesma calculadora |

Resultados para 3 kg: Standard R$ 16,00, Express R$ 35,00, Pickup R$ 0,00 e Economy R$ 9,50.

## Como executar

```bash
javac -d out src/*.java
java -cp out Main
```

## Questões para reflexão

**a) Qual problema existente no código inicial foi resolvido com o Strategy?**

A `ShippingCalculator` concentrava todas as fórmulas num `if/else` baseado em texto. Cada nova modalidade obrigava a alterar essa classe, as regras ficavam misturadas com a escolha e era possível passar um texto inválido. Agora cada fórmula fica na sua própria classe, a calculadora não tem condicionais e o cliente escolhe a modalidade passando um objeto, sem risco de errar o nome.

**b) Quais classes representam o contexto, a estratégia e as estratégias concretas na sua implementação?**

- Contexto: `ShippingCalculator`
- Estratégia: `ShippingStrategy`
- Estratégias concretas: `StandardShipping`, `ExpressShipping`, `StorePickup` e `EconomyShipping`

**c) Como a composição e a delegação permitem trocar o algoritmo durante a execução?**

A calculadora guarda uma referência para uma `ShippingStrategy` (composição) e, no `calculate`, apenas repassa a chamada para ela (delegação). Como a estratégia é só um atributo, basta chamar `setStrategy` com outro objeto para mudar o cálculo, sem criar uma nova calculadora.

**d) Qual é a responsabilidade do cliente na seleção da estratégia e por que essa escolha não deve ficar dentro da calculadora?**

O cliente decide qual modalidade usar e cria o objeto da estratégia correspondente. Se essa escolha ficasse na calculadora, ela voltaria a ter condicionais e a conhecer todas as modalidades, e teria de ser alterada sempre que surgisse uma nova. Deixando a escolha no cliente, a calculadora só sabe calcular, sem depender de nenhuma modalidade específica.

**e) Como a inclusão de uma nova modalidade de entrega se relaciona com o princípio Open/Closed (OCP)?**

Para adicionar a entrega econômica, bastou criar a classe `EconomyShipping`. A calculadora e as outras estratégias não foram alteradas. O sistema ficou aberto para extensão (novas modalidades) e fechado para modificação (o código existente continua igual), que é exatamente o que o OCP propõe.
