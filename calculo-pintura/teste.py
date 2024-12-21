import json

# Função para calcular a fórmula
def calcular_com_formula(formula, variaveis):
    try:
        return eval(formula, {}, variaveis)
    except Exception as e:
        print(f"Erro ao calcular a fórmula: {e}")
        return None

# Função principal
def main():
    # Ler o JSON do arquivo
    with open("calculo.v1.2.json", "r") as file:
        dados_json = json.load(file)

    # Exibindo menu para o usuário escolher entre Cilindro ou Quadrado
    print("Escolha o tipo de geometria:")
    print("1. Cilindro")
    print("2. Quadrado")
    escolha = int(input("Digite 1 ou 2: "))

    if escolha == 1:
        # Escolha de um cilindro
        tanque = dados_json["equipamentos"][0]  # Selecionando o primeiro tanque (Tanque 001 T001)
        
        # Recebe as entradas do usuário para o cilindro
        print(f"\n{tanque['nome']} - Digite as informações para o Cilindro:")
        diametro = float(input("Diametro: "))
        altura = float(input("Altura: "))

        # Definindo as variáveis para calcular a área e volume
        variaveis = {
            'd': diametro,
            'altura': altura
        }

        # Calculando área e volume usando as fórmulas do JSON
        area = calcular_com_formula(tanque["formulas"]["area"], variaveis)
        volume = calcular_com_formula(tanque["formulas"]["volume"], variaveis)
        
        # Atualiza as propriedades no JSON
        tanque["propriedades"]["diametro"] = diametro
        tanque["propriedades"]["altura"] = altura
        tanque["propriedades"]["area"] = area
        tanque["propriedades"]["volume"] = volume
        
        # Calculando outras propriedades, como fundo, teto e isolamento
        variaveis['area_planificada_costado'] = area  # Supomos que a área do costado seja igual à área calculada
        tanque["propriedades"]["area_fundo"] = area / 2  # Exemplo de cálculo de fundo
        tanque["propriedades"]["area_teto"] = area / 2  # Exemplo de cálculo de teto
        tanque["propriedades"]["volume_isolamento"] = calcular_com_formula(tanque["formulas"]["isolamento"], variaveis)  # Exemplo de cálculo de isolamento

    elif escolha == 2:
        # Escolha de um quadrado
        tanque = dados_json["equipamentos"][1]  # Selecionando o segundo tanque (Tanque 002 T002)

        # Recebe as entradas do usuário para o quadrado
        print(f"\n{tanque['nome']} - Digite as informações para o Quadrado:")
        lado = float(input("Lado: "))
        altura = float(input("Altura: "))

        # Definindo as variáveis para calcular a área e volume
        variaveis = {
            'l': lado,
            'altura': altura
        }

        # Calculando área e volume usando as fórmulas do JSON
        area = calcular_com_formula(tanque["formulas"]["area"], variaveis)
        volume = calcular_com_formula(tanque["formulas"]["volume"], variaveis)

        # Atualiza as propriedades no JSON
        tanque["propriedades"]["lado"] = lado
        tanque["propriedades"]["altura"] = altura
        tanque["propriedades"]["area"] = area
        tanque["propriedades"]["volume"] = volume
        tanque["propriedades"]["volume_isolamento"] = calcular_com_formula(tanque["formulas"]["isolamento"], variaveis)  # Exemplo de cálculo de isolamento

    else:
        print("Opção inválida!")

    # Escrever os resultados no arquivo JSON atualizado
    with open("calculo.v1.2.json", "w") as file:
        json.dump(dados_json, file, indent=4)

    # Exibe os resultados atualizados no terminal
    print("\nResultados Calculados:")
    result = []

    for equipamento in dados_json["equipamentos"]:
        if equipamento["tipo"] == "Tanque" and equipamento["geometria"] == "Cilindro":
            # Adiciona apenas as informações desejadas do tanque
            tanque_info = {
                "nome": equipamento["nome"],
                "tipo": equipamento["tipo"],
                "geometria": equipamento["geometria"],
                "propriedades": equipamento["propriedades"]
            }
            result.append(tanque_info)

    # Exibe o resultado filtrado
    print(json.dumps(result, indent=4))

# Chama a função principal
if __name__ == "__main__":
    main()
