<template>
  <div class="container mt-5">
    <h1>Calculo</h1>

    <!-- Dropdown para selecionar equipamento -->
    <div class="mb-3">
      <label for="equipamentoSelect" class="form-label">Selecione o Equipamento</label>
      <select class="form-select" v-model="equipamentoSelecionado">
        <option v-for="equipamento in equipamentos" :key="equipamento.id" :value="equipamento">
          {{ equipamento.nome }}
        </option>
      </select>
    </div>

    <!-- Botão para Calcular -->
    <button class="btn btn-primary" @click="calcularEquipamento" :disabled="!equipamentoSelecionado">Calcular</button>

    <!-- Modal de Resultado -->
    <div class="modal fade" id="resultadoModal" tabindex="-1" aria-labelledby="resultadoModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="resultadoModalLabel">Resultado do Cálculo</h5>
            <button type="button" class="btn-close" @click="closeModal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <!-- Equipamento Selecionado -->
            <p><strong>Equipamento Selecionado:</strong> {{ equipamentoSelecionado?.nome }}</p>
            
            <!-- Resultado do Cálculo -->
            <p><strong>Resultado do Cálculo:</strong></p>
            <ul>
              <li v-for="(valor, chave) in resultado" :key="chave">{{ chave }}: {{ valor }}</li>
            </ul>
            
            <!-- Exibindo a fórmula com valores -->
            <p><strong>Fórmula com Valores:</strong></p>
            <ul>
              <li v-for="(valor, chave) in formulaComValores" :key="chave">{{ chave }}: {{ valor }}</li>
            </ul>
            
            <!-- Exibindo a fórmula original -->
            <p><strong>Fórmula Original:</strong></p>
            <ul>
              <li v-for="(valor, chave) in formulaOriginal" :key="chave">{{ chave }}: {{ valor }}</li>
            </ul>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" @click="closeModal">Fechar</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios';

  export default {
    data() {
      return {
        equipamentoSelecionado: null, // Aqui armazenamos o objeto completo
        equipamentos: [],
        resultado: {},
        formulaComValores: {},
        formulaOriginal: {}
      };
    },
    mounted() {
      this.carregarEquipamentos();
    },
    methods: {
      async carregarEquipamentos() {
        try {
          const response = await axios.get('http://localhost:8080/equipamento/');
          this.equipamentos = response.data.map(equipamento => ({
            ...equipamento,
            expandido: false
          }));
        } catch (error) {
          console.error('Erro ao carregar equipamentos:', error);
        }
      },
      async calcularEquipamento() {
        try {
          if (this.equipamentoSelecionado !== null) {
            const response = await axios.post('http://localhost:8080/equipamento/calcular', this.equipamentoSelecionado);

            if (response.data) {
              this.resultado = response.data.resultado;
              this.formulaComValores = response.data.formulas_com_valores;
              this.formulaOriginal = response.data.formulas_originais;
              this.showModal(); // Exibe o modal com o resultado
              console.log('Resultado do cálculo:', this.resultado);
              console.log('Fórmula com valores:', this.formulaComValores);
              console.log('Fórmula original:', this.formulaOriginal);
            }
          } else {
            console.log('Nenhum equipamento selecionado');
          }
        } catch (error) {
          console.error('Erro ao calcular equipamento:', error);
        }
      },
      showModal() {
        const modal = new window.bootstrap.Modal(document.getElementById('resultadoModal'));
        modal.show();
      },
      closeModal() {
        const modal = window.bootstrap.Modal.getInstance(document.getElementById('resultadoModal'));
        modal.hide();
      }
    }
  };
</script>
