from entitys.Equipamento import Equipamento

class Tanque(Equipamento):
    def __init__(self, nome, tipo, geometria, formulas, propriedades):
        super().__init__(nome, tipo, geometria, formulas, propriedades)

    def calcular_propriedades(self, tipo_geometria):
        area = self.calcular_area(self.formulas)
        volume = self.calcular_volume(self.formulas)
        self.atualizar_propriedades(self.formulas, area, volume, tipo_geometria)
