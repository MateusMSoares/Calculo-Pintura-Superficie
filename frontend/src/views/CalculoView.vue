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
            <p><strong>Equipamento Selecionado:</strong> {{ equipamentoSelecionado?.nome }}</p>
            <p>Geometria: {{ geometriaNome }}</p>
            <p>Tipo: {{ tipoNome }}</p>
            
            <p><strong>Resultado do Cálculo:</strong></p>
            <ul>
              <li v-for="(valor, chave) in resultado" :key="chave">{{ chave }}: {{ valor }}</li>
            </ul>

            <p><strong>Fórmula com Valores:</strong></p>
            <ul>
              <li v-for="(valor, chave) in formulaComValores" :key="chave">{{ chave }}: {{ valor }}</li>
            </ul>

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
        formulaOriginal: {},
        geometriaNome: '',
        tipoNome: ''
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
              await this.carregarGeometria();
              await this.carregarTipo();
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
      async carregarGeometria() {
        try {
          // Verifique se a geometria está disponível
          if (this.equipamentoSelecionado && this.equipamentoSelecionado.geometria) {
            console.log('ID da Geometria:', this.equipamentoSelecionado.geometria);

            // Realizando a requisição corretamente com a interpolação
            const response = await axios.get(`http://localhost:8080/geometria/${this.equipamentoSelecionado.geometria}`);

            this.geometriaNome = response.data.nome;
            console.log('Nome da Geometria:', this.geometriaNome);
          } else {
            console.error('Equipamento selecionado não tem geometria.');
          }
        } catch (error) {
          console.error('Erro ao carregar geometrias:', error);
        }
      },
      async carregarTipo() {
        try {
          // Verifique se o tipo está disponível
          if (this.equipamentoSelecionado && this.equipamentoSelecionado.tipo) {
            console.log('ID do Tipo:', this.equipamentoSelecionado.tipo);

            // Realizando a requisição corretamente com a interpolação
            const response = await axios.get(`http://localhost:8080/tipo/${this.equipamentoSelecionado.tipo}`);

            this.tipoNome = response.data.nome;
            console.log('Nome do Tipo:', this.tipoNome);
          } else {
            console.error('Equipamento selecionado não tem tipo.');
          }
        } catch (error) {
          console.error('Erro ao carregar tipos:', error);
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