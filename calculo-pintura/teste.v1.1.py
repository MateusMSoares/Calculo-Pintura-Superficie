import json
from entitys.Equipamento import Equipamento  # Importação corrigida
from entitys.tipos.Tanque import Tanque      # Importação corrigida
from services.Calculo import Calculo

def main():
    
    # Instanciando Calculo
    calculo = Calculo("calculo.v1.2.json")

    # Exibindo o menu
    escolha = calculo.exibir_menu()

    if escolha == 1:
        tanque = calculo.tanques[0]
        calculo.calcular_para_cilindro(tanque)
    elif escolha == 2:
        tanque = calculo.tanques[1]
        calculo.calcular_para_quadrado(tanque)
    else:
        print("Opção inválida!")

    # Salvando os resultados
    calculo.salvar_arquivo("calculo.v1.2.json")
    
    # Exibindo os resultados
    calculo.exibir_resultados()

if __name__ == "__main__":
    main()
