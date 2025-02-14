<template>
  <div class="cadastro-equipamento">
    <h2>Cadastro de Equipamento</h2>

    <form @submit.prevent="cadastrarEquipamento">
      <!-- Outros campos aqui -->
      
      <!-- Componente de Geometria Dinâmico -->
      <component
        :is="getGeometryComponent"
        :equipamento="equipamento"
        @updateEquipamento="handleUpdateEquipamento"  <!-- Aqui escutamos o evento -->
      ></component>

      <!-- Submeter -->
      <div class="form-group">
        <button type="submit">Cadastrar Equipamento</button>
      </div>
    </form>

    <!-- Exibição de status -->
    <div v-if="statusMessage" :class="statusClass">
      {{ statusMessage }}
    </div>
  </div>
</template>

<script>
import axios from "axios";
import CylinderGeometry from "@/components/geometries/CilindroTemplate.vue"; // Usando o cilindro apenas
const apiBaseUrl = process.env.VUE_APP_API_BASE_URL;

export default {
  data() {
    return {
      equipamento: {
        nome: "",
        tipo: "",
        diametro: null,
        altura: null,
        hasBV: false,
        hasPL: false,
        hasIsolamento: false,
        bv: { diametro: null, quantidade: null },
        pl: { raio: null, lado: null },
        isolamento: { altura: null },
      },
      tipos: [],
      statusMessage: "",
      statusClass: "",
    };
  },
  computed: {
    // Define o componente de geometria com base no tipo de equipamento
    getGeometryComponent() {
      switch (this.equipamento.tipo) {
        case "TORRE":
          return CylinderGeometry; // Pode ser um cilindro para "TORRE"
        case "TANQUE":
          return CylinderGeometry; // Também pode ser um cilindro para "TANQUE"
        default:
          return null; // Se não houver tipo, não exibe nenhum componente
      }
    },
  },
  mounted() {
    this.getTipos(); // Chama a função para buscar os tipos ao carregar a página
  },
  methods: {
    // Envia os dados do equipamento para o backend
    async cadastrarEquipamento() {
      // Limpa os campos caso não estejam habilitados
      if (!this.equipamento.hasBV) {
        this.equipamento.bv = null;
      }

      if (!this.equipamento.hasPL) {
        this.equipamento.pl = null;
      }

      if (!this.equipamento.hasIsolamento) {
        this.equipamento.isolamento = null;
      }

      // Exibe os dados no console antes de enviar para o backend
      console.log("equipamento:", JSON.stringify(this.equipamento, null, 2));

      try {
        // Envia os dados para o backend
        await axios.post(`${apiBaseUrl}/equipamento/`, this.equipamento);
        // Exibe mensagem de sucesso
        this.statusMessage = "Equipamento cadastrado com sucesso!";
        this.statusClass = "success";
      } catch (error) {
        // Exibe mensagem de erro
        this.statusMessage = "Erro ao cadastrar o equipamento.";
        this.statusClass = "error";
      }
    },

    // Busca os tipos do backend
    async getTipos() {
      try {
        const response = await axios.get(`${apiBaseUrl}/tipo/`);
        this.tipos = response.data;
        console.log("tipos:", this.tipos);
      } catch (error) {
        console.error(error);
      }
    },

    // Método para atualizar o equipamento quando o filho emitir o evento
    handleUpdateEquipamento({ key, value }) {
      // Aqui a mutação acontece no componente pai
      this.equipamento[key] = value;
    },
  },
};
</script>

<style scoped>
.cadastro-equipamento {
  width: 100%;
  max-width: 600px;
  margin: 0 auto;
  padding: 1rem;
  background-color: #f9f9f9;
  border-radius: 8px;
}

.form-group {
  margin-bottom: 1rem;
}

input[type="text"],
input[type="number"] {
  width: 100%;
  padding: 8px;
  margin-top: 5px;
  font-size: 1rem;
  border-radius: 4px;
  border: 1px solid #ccc;
}

button {
  background-color: #4CAF50;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #45a049;
}

.success {
  color: green;
}

.error {
  color: red;
}
</style>
