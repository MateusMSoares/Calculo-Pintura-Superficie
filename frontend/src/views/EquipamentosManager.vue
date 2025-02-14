<template>
  <div class="container mt-5">
    <h1>Equipamentos</h1>

    <!-- Lista de Equipamentos -->
    <div class="list-group">
      <div
        v-for="equipamento in equipamentos"
        :key="equipamento.id"
        class="list-group-item"
        @click="toggleDetalhes(equipamento.id)"
      >
        <h5>{{ equipamento.nome }}</h5>
        <p><strong>Tipo:</strong> {{ equipamento.tipo }}</p>
        <p><strong>Data de Criação:</strong> {{ formatDate(equipamento.dataHoraCriacao) }}</p>
        <p><strong>Diâmetro:</strong> {{ equipamento.diametro }}</p>
        <p><strong>Altura:</strong> {{ equipamento.altura }}</p>
        <p><strong>Área:</strong> {{ equipamento.area }}</p>
        <p><strong>Resultado:</strong> {{ equipamento.resultado }}</p>        
        <p><strong>Volume:</strong> {{ equipamento.volume }}</p>

        <!-- Exibir detalhes adicionais quando expandido -->
        <div v-if="equipamento.expandido">
          <ul>
              <div v-if="equipamento.hasBV">
            <p><strong>BV:</strong></p>
            <ul>
              <li><strong>Diâmetro (BV):</strong> {{ equipamento.bv.diametro }}</li>
              <li><strong>Quantidade (BV):</strong> {{ equipamento.bv.quantidade }}</li>
              <li><strong>Resultado (BV):</strong> {{ equipamento.bv.resultado }}</li>
            </ul>
          </div>
          <div v-else>
            <p><strong>BV:</strong> N/A</p>
          </div>

          <!-- Exibir Detalhes de PL -->
          <div v-if="equipamento.hasPL">
            <p><strong>PL:</strong></p>
            <ul>
              <li><strong>Valor:</strong> {{ equipamento.pl.valor }}</li>
              <li><strong>Descrição:</strong> {{ equipamento.pl.descricao }}</li>
            </ul>
          </div>
          <div v-else>
            <p><strong>PL:</strong> N/A</p>
          </div>

          <!-- Exibir Detalhes de Isolamento -->
          <div v-if="equipamento.hasIsolamento">
            <p><strong>Isolamento:</strong></p>
            <ul>
              <li><strong>Detalhes:</strong> {{ equipamento.isolamento }}</li>
            </ul>
          </div>
          <div v-else>
            <p><strong>Isolamento:</strong> N/A</p>
          </div>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

const apiBaseURL = process.env.VUE_APP_API_BASE_URL;

export default {
  data() {
    return {
      equipamentos: [],
      
    };
  },
  mounted() {
    this.carregarEquipamentos(); // Carrega os equipamentos assim que o componente for montado
  },
  methods: {
    // Função para carregar equipamentos da API
    async carregarEquipamentos() {
      try {
        const response = await axios.get(`${apiBaseURL}/equipamento/`);
        this.equipamentos = response.data.map((equipamento) => ({
          ...equipamento,
          expandido: false, // Inicializa a chave 'expandido' como 'false'
        }));
        console.log("equipamento: " + JSON.stringify(this.equipamentos));
      } catch (error) {
        console.error("Erro ao carregar equipamentos:", error);
      }
    },

    // Função para alternar a visualização dos detalhes do equipamento
    toggleDetalhes(id) {
      const equipamento = this.equipamentos.find((e) => e.id === id);
      if (equipamento) {
        equipamento.expandido = !equipamento.expandido; // Alterna o estado de expansão
      }
    },

    // Função para formatar a data de criação
    formatDate(timestamp) {
      const date = new Date(timestamp);
      return date.toLocaleString(); // Formata a data no formato local
    },
  },
};
</script>

<style scoped>
.container {
  max-width: 900px;
}

.list-group-item {
  cursor: pointer;
}

.list-group-item h5 {
  font-size: 1.2em;
}

.list-group-item p,
.list-group-item li {
  margin: 5px 0;
}
</style>