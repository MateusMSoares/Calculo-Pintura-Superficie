<template>
    <div class="container mt-5">
      <h1>Calculo</h1>
  
      <!-- Dropdown para selecionar equipamento -->
      <div class="mb-3">
        <label for="equipamentoSelect" class="form-label">Selecione o Equipamento</label>
        <select class="form-select" v-model="equipamentoSelecionado">
          <option v-for="equipamento in equipamentos" :key="equipamento.id" :value="equipamento.id">
            {{ equipamento.nome }}
          </option>
        </select>
      </div>
  
      <!-- Botão para Calcular -->
      <button class="btn btn-primary" @click="calcularEquipamento" :disabled="equipamentoSelecionado === null">Calcular</button>
  
      <!-- Modal de Resultado -->
      <div class="modal fade" id="resultadoModal" tabindex="-1" aria-labelledby="resultadoModalLabel" aria-hidden="true" v-if="showModal">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="resultadoModalLabel">Resultado do Cálculo</h5>
              <button type="button" class="btn-close" @click="showModal = false" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <p><strong>Equipamento Selecionado:</strong> {{ resultado.nome }}</p>
              <p><strong>Resultado do Cálculo:</strong> {{ resultado.calculo }}</p>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" @click="showModal = false">Fechar</button>
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
        equipamentoSelecionado: null, 
        equipamentos: [],
        resultado: {},
        showModal: false, // Controle de exibição do modal
      };
    },
    mounted() {
      this.carregarEquipamentos();
    },
    methods: {
      async carregarEquipamentos() {
        try {
          // Aqui você faz a requisição para carregar os equipamentos da API
          const response = await axios.get('http://localhost:8080/equipamento/');
          this.equipamentos = response.data.map(equipamento => ({
            ...equipamento,
            expandido: false // Inicialmente os detalhes estão ocultos
          }));
        } catch (error) {
          console.error('Erro ao carregar equipamentos:', error);
        }
      },
      calcularEquipamento() {
        if (this.equipamentoSelecionado !== null) {
          // Busca o equipamento selecionado
          const equipamento = this.equipamentos.find(e => e.id === this.equipamentoSelecionado);
          if (equipamento) {
            // Aqui você pode fazer o cálculo real. Para este exemplo, estamos apenas simulando um cálculo.
            this.resultado = {
              nome: equipamento.nome,
              calculo: `Resultado do cálculo para ${equipamento.nome}`
            };
  
            // Exibir o modal com os resultados
            this.showModal = true;
          }
        }
      }
    }
  };
  </script>
  s
  