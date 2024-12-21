class Equipamento:
    def __init__(self, nome, tipo, geometria, formulas, propriedades):
        self.nome = nome
        self.tipo = tipo
        self.geometria = geometria
        self.formulas = formulas
        self.propriedades = propriedades
    
    def calcular_formulas(self):
        try:
            for chave, formula in self.formulas.items():
                variaveis = self.propriedades
                resultado = eval(formula, {}, variaveis)
                self.propriedades[chave] = round(resultado, 2)
                print(f"Fórmula para {chave}: {formula} = {resultado}")
        except Exception as e:
            print(f"Erro ao calcular as fórmulas: {e}")

    def atualizar_propriedades(self, variaveis, area, volume, tipo_geometria):
        self.propriedades["area"] = area
        self.propriedades["volume"] = volume