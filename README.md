# Cálculo do Gasto para Pintura de Superfícies

## Dimensões da Área em m²

1. **Cone:**
   - Fórmula: `π * (diâmetro / 2)²`
   - Considerar a altura do cone para cálculos mais avançados se necessário.

2. **Círculos:**
   - Fórmula: `π * r²`
   - Onde `r` é o raio do círculo.

3. **Quadrado:**
   - Fórmula: `L * L`
   - Onde `L` é o comprimento do lado do quadrado.

4. **Retângulo:**
   - Fórmula: `H * L`
   - Onde `H` é a altura e `L` é o comprimento.

5. Fator de multiplicidade: O equipamento pode ter teto e fundo com as mesmas dimensões.

## Tipo de Tinta

- Diversas opções disponíveis.
- Preço unitário por m².

## Resultado da Área

- Área calculada com base nas dimensões fornecidas.

## Cálculo do Valor

- **Valor Total** = `(Área * Preço por m² do Tipo de Tinta) * multiplicador`

## Tecnologias Utilizadas

- **Frontend:** React com JavaScript
- **Backend:** Node.js
- **Hospedagem:** Firebase Hosting

## Requisitos Funcionais

| Requisito                     | Descrição                                                     |
|-------------------------------|---------------------------------------------------------------|
| **Seleção de superfícies**          | Receber dimensões das superfícies a serem pintadas.           |
| **Cálculo da Área**           | Calcular a área das superfícies com base nas fórmulas fornecidas. |
| **Multiplicidade**           | Poder multiplicar o número de peças a serem pintadas. |
| **Escolha de Tinta**          | Permitir seleção do tipo de tinta e seu preço por m².         |
| **Cadastro e gerenciamento de tintas**          | Permitir que seja criado, alterado e deletado as informações das tintas.         |
| **Cálculo do Valor Total**    | Calcular o custo total baseado na área e no preço da tinta.    |
| **Interface de Usuário**      | Fornecer uma interface amigável para entrada de dados e exibição dos resultados. |
| **Persistência de Dados**     | Armazenar as informações das tintas     |
| **Deploy e Hospedagem**       | Hospedar o aplicativo na internet.                            |

Site: [Cálculo do Gasto para Pintura de Superfícies](https://calculo-pintura-superficie.web.app/)
