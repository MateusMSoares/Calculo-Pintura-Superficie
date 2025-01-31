<template>
  <div class="container mt-5">
    <h1>Criar Equipamento</h1>

    <div class="mb-3">
      <label for="nome" class="form-label">Nome</label>
      <input type="text" class="form-control" id="nome" v-model="equipamento.nome" required>
    </div>

    <div class="mb-3">
      <label for="tipo" class="form-label">Tipo</label>
      <select class="form-select" id="tipo" v-model="equipamento.tipo">
        <option value="" disabled selected>Selecione um tipo</option>
        <option v-for="tipo in tipos" :key="tipo.id" :value="tipo">{{ tipo.nome }}</option>
      </select>
    </div>

    <div class="mb-3">
      <label for="geometria" class="form-label">Geometria</label>
      <select class="form-select" id="geometria" v-model="equipamento.geometria" @change="carregarPropriedadesFundamentais">
        <option value="" disabled selected>Selecione uma geometria</option>
        <option v-for="geometria in geometrias" :key="geometria.id" :value="geometria">{{ geometria.nome }}</option>
      </select>
    </div>

    <div v-if="equipamento.geometria.propriedades">
      <div v-for="(valor, chave) in equipamento.geometria.propriedades" :key="chave" class="mb-3">
        <label :for="chave" class="form-label">{{ chave.charAt(0).toUpperCase() + chave.slice(1) }}</label>
        <input type="number" class="form-control" :id="chave" v-model="equipamento.geometria.propriedades[chave]" :required="true">
      </div>
    </div>

    <button class="btn btn-success mt-3" @click="criarEquipamento" :disabled="isFormInvalid">Criar Equipamento</button>

    <div v-if="equipamentoCriado" class="mt-4">
      <h3>Equipamento Criado:</h3>
      <pre>{{ equipamentoExibido }}</pre>
    </div>
  </div>
</template>

<script>
  import axios from 'axios';
  const apiBaseUrl = process.env.VUE_APP_API_BASE_URL;

  export default {
    data() {
      return {
        equipamento: {
          nome: '',
          tipo: {
            nome: '',
            propriedades: []
          },
          geometria: {
            id: '',
            nome: '',
            propriedades_fundamentais: [],
            propriedades: {}
          }
        },
        tipos: [],
        geometrias: [],
        equipamentoCriado: false
      };
    },
    computed: {
      equipamentoExibido() {
        const tipoSelecionado = this.equipamento.tipo ? this.equipamento.tipo.nome : 'Não selecionado';
        const geometriaSelecionada = this.equipamento.geometria ? this.equipamento.geometria.nome : 'Não selecionado';

        return {
          nome: this.equipamento.nome,
          tipo: tipoSelecionado,
          geometria: geometriaSelecionada
        };
      },
      isFormInvalid() {
        return !this.equipamento.nome || !this.equipamento.tipo.id || !this.equipamento.geometria.id;
      }
    },
    mounted() {
      this.carregarTipos();
      this.carregarGeometrias();
    },
    methods: {
      async criarEquipamento() {
        const equipamentoParaEnviar = {
          nome: this.equipamento.nome,
          tipo: JSON.parse(JSON.stringify(this.equipamento.tipo)), 
          geometria: JSON.parse(JSON.stringify(this.equipamento.geometria)) 
        };
        console.log('Equipamento para enviar:', equipamentoParaEnviar);
        try {
          await axios.post(`${apiBaseUrl}/equipamento/`, equipamentoParaEnviar);
          this.equipamentoCriado = true;
        } catch (error) {
          console.error('Erro ao criar equipamento:', error);
        }
      },
      async carregarTipos() {
        try {
          const response = await axios.get(`${apiBaseUrl}/tipo/`);
          this.tipos = response.data;
        } catch (error) {
          console.error('Erro ao carregar tipos:', error);
        }
      },
      async carregarGeometrias() {
        try {
          const response = await axios.get(`${apiBaseUrl}/geometria/`);
          this.geometrias = response.data;
        } catch (error) {
          console.error('Erro ao carregar geometrias:', error);
        }
      },
      carregarPropriedadesFundamentais() {
        if (this.equipamento.geometria && this.equipamento.geometria.propriedades_fundamentais) {
          this.equipamento.geometria.propriedades = this.equipamento.geometria.propriedades_fundamentais.reduce((acc, chave) => {
            acc[chave] = 0; // Valor inicial das propriedades
            return acc;
          }, {});
        } else {
          this.equipamento.geometria.propriedades = {};
        }
      }
    }
  };
</script>
