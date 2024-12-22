import json
class Equipamento:
    def __init__(self, nome=None, tipo=None, geometrias=None, formulas=None, propriedades=None):
        self.nome = nome
        self.tipo = tipo
        self.geometrias = geometrias
        self.formulas = formulas
        self.propriedades = propriedades

    def getAllEquipamentos(self):
        equipamentos = self.carregar_arquivo()
        return equipamentos

    def carregar_arquivo(self):
        with open("equipamentos.json", "r") as file:
            return json.load(file)
    
    def calcular_formulas(self):
        propriedade = self.propriedades
        try:
            for chave, formula in propriedade.items():
                resultado = eval(formula, {}, propriedade)
                self.propriedades[chave] = round(resultado, 2)
                print(f"Fórmula para {chave}: {formula} = {resultado}")
        except Exception as e:
            print(f"Erro ao calcular as fórmulas: {e}")

    def sobrepor_variaveis(self):
        geometrias = self.geometrias
        propriedades = self.propriedades
        formulas = self.formulas
        try:
            if self.tipo in geometrias.nome:
                for chave, valor in geometrias.formulas.items():
                    for var, valor in propriedades.items():
                        formula = formula.replace(var, str(valor))
                    formulas[chave] = formula
                self.formulas
                
        except Exception as e:
            print(f"Erro ao sobrepor as variáveis: {e}")