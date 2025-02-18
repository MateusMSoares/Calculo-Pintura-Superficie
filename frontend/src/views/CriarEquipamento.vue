<template>
  <div class="cadastro-equipamento">
    <h2>Cadastro de Equipamento</h2>

    <form @submit.prevent="cadastrarEquipamento">
      <div class="form-group">
        <label for="nome">Nome do Equipamento</label>
        <input
          type="text"
          id="nome"
          v-model="equipamento.nome"
          required
        />
      </div>

      <!-- Tipo -->
      <div class="form-group">
        <label for="tipo">Tipo do Equipamento</label>
        <select
          id="tipo"
          v-model="equipamento.tipo"
          required
        >
        <option value="Selecione um tipo" disabled selected>Selecione um tipo</option>
          <option v-for="tipo in tipos" :key="tipo" :value="tipo">
            {{ tipo }}
          </option>
        </select>
      </div>
      
      <!-- Componente de Geometria Dinâmico -->
      <component
        :is="getGeometryComponent"
        :equipamento="equipamento"
        @updateEquipamento="handleUpdateEquipamento"
      ></component>

      <!-- BV -->
      <div class="form-group">
        <label for="hasBV">Possui BV?</label>
        <input
          type="checkbox"
          id="hasBV"
          v-model="equipamento.hasBV"
        />
      </div>

      <!-- Campos BV -->
      <div v-if="equipamento.hasBV">
        <div class="form-group">
          <label for="bvDiametro">Diâmetro (BV)</label>
          <input
            type="number"
            id="bvDiametro"
            v-model="equipamento.bv.diametro"
            required
          />
        </div>
        <div class="form-group">
          <label for="bvQuantidade">Quantidade (BV)</label>
          <input
            type="number"
            id="bvQuantidade"
            v-model="equipamento.bv.quantidade"
            required
          />
        </div>
      </div>

      <!-- PL -->
      <div class="form-group">
        <label for="hasPL">Possui PL?</label>
        <input
          type="checkbox"
          id="hasPL"
          v-model="equipamento.hasPL"
        />
      </div>

      <!-- Campos PL -->
      <div v-if="equipamento.hasPL">
        <div class="form-group">
          <label for="plRaio">Raio (PL)</label>
          <input
            type="number"
            id="plRaio"
            v-model="equipamento.pl.raio"
            required
          />
        </div>
        <div class="form-group">
          <label for="plLado">Lado (PL)</label>
          <input
            type="number"
            id="plLado"
            v-model="equipamento.pl.lado"
            required
          />
        </div>
      </div>

      <!-- Isolamento -->
      <div class="form-group">
        <label for="hasIsolamento">Possui Isolamento?</label>
        <input
          type="checkbox"
          id="hasIsolamento"
          v-model="equipamento.hasIsolamento"
        />
      </div>

      <!-- Campos Isolamento -->
      <div v-if="equipamento.hasIsolamento">
        <div class="form-group">
          <label for="isolamentoAltura">Altura (Isolamento)</label>
          <input
            type="number"
            id="isolamentoAltura"
            v-model="equipamento.isolamento.altura"
            required
          />
        </div>
      </div>

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
