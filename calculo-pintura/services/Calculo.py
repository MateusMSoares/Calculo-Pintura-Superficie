import json
from entitys.tipos.Tanque import Tanque

class Calculo:
    def __init__(self, arquivo_json):
        self.dados_json = self.carregar_arquivo(arquivo_json)
        self.tanques = self.criar_tanques()

    def carregar_arquivo(self, arquivo_json):
        with open(arquivo_json, "r") as file:
            return json.load(file)

    def salvar_arquivo(self, arquivo_json):
        with open(arquivo_json, "w") as file:
            json.dump(self.dados_json, file, indent=4)

    def criar_tanques(self):
        tanques = []
        for equipamento in self.dados_json["equipamentos"]:
            if equipamento["tipo"] == "Tanque":
                tanque = Tanque(
                    nome=equipamento["nome"],
                    tipo=equipamento["tipo"],
                    geometrias=equipamento["geometria"],
                    formulas=equipamento["formulas"],
                    propriedades=equipamento["propriedades"]
                )
                tanques.append(tanque)
        return tanques

    def exibir_menu(self):
        print("Escolha o tipo de geometria:")
        print("1. Cilindro")
        print("2. Quadrado")
        return int(input("Digite 1 ou 2: "))

    def calcular_para_cilindro(self, tanque):
        print(f"\n{tanque.nome} - Digite as informações para o Cilindro:")
        tanque.propriedades["diametro"] = float(input("Diametro: "))
        tanque.propriedades["altura"] = float(input("Altura: "))
        tanque.sobrepor_variaveis()
        tanque.calcular_formulas()
        
    def exibir_resultados(self):
        print("\n----------------------- Especificações -----------------------:")
        for tanque in self.tanques:
            # Exibindo as propriedades calculadas
            for chave, valor in tanque.propriedades.items():
                print(f"{chave}: {valor}")
            # Exibindo as fórmulas calculadas
            for chave, formula in tanque.formulas.items():
                print(f"Fórmula para {chave}: {formula}")

        print("\n----------------------- Fórmula Original -----------------------:")
        for chave, formula in tanque.geometrias.formulas.items():
            print(f"Fórmula para {chave}: {formula}")
        
            
