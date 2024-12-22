from entitys.Equipamento import Equipamento

class Tanque(Equipamento):
    def __init__(self, nome, tipo, geometrias, formulas, propriedades):
        super().__init__(nome, tipo, geometrias, formulas, propriedades)

