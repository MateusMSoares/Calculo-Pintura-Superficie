<template>
  <div class="container mt-5">
    <h1>Equipamentos</h1>

   </div>
</template>

<script>
import axios from "axios";
//import CylinderGeometry from "@/components/geometries/CilindroTemplate.vue";
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