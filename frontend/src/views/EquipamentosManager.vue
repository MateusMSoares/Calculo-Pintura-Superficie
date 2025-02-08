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
        <p><strong>Diâmetro:</strong> {{ equipamento.diametro }}</p>
        <p><strong>Altura:</strong> {{ equipamento.altura }}</p>
        <p><strong>Área:</strong> {{ equipamento.area }}</p>
        <p><strong>Resultado:</strong> {{ equipamento.resultado }}</p>        
        <p><strong>Quantidade:</strong> {{ equipamento.quantidade }}</p>
        <p><strong>Volume:</strong> {{ equipamento.volume }}</p>

        <!-- Exibir detalhes do BV se 'hasBV' for verdadeiro -->
        <div v-if="equipamento.hasBV">
          <p><strong>Diâmetro (BV):</strong> {{ equipamento.bv.diametro }}</p>
          <p><strong>Quantidade (BV):</strong> {{ equipamento.bv.quantidade }}</p>
          <p><strong>Resultado (BV):</strong> {{ equipamento.bv.resultado }}</p>
        </div>

        <!-- Exibir detalhes adicionais quando expandido -->
        <div v-if="equipamento.expandido">
          <p><strong>Propriedades:</strong></p>
          <ul>
            <p><strong>Data de Criação:</strong> {{ formatDate(equipamento.dataHoraCriacao.value) }}</p>
            <p><strong>Has BV:</strong> {{ equipamento.hasBV }}</p>
            <p><strong>Has Isolamento:</strong> {{ equipamento.hasIsolamento }}</p>
            <p><strong>Has PL:</strong> {{ equipamento.hasPL }}</p>
            <p><strong>Volume:</strong> {{ equipamento.volume }}</p>
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
      equipamentos: [], // Lista de equipamentos
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
