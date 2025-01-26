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
        <option v-for="tipo in tipos" :key="tipo.id" :value="tipo.id">{{ tipo.nome }}</option>
      </select>
    </div>
  
    <div class="mb-3">
      <label for="geometria" class="form-label">Geometria</label>
      <select class="form-select" id="geometria" v-model="equipamento.geometria" @change="carregarPropriedadesFundamentais">
        <option value="" disabled selected>Selecione uma geometria</option>
        <option v-for="geometria in geometrias" :key="geometria.id" :value="geometria.id">{{ geometria.nome }}</option>
      </select>
    </div>

    <div v-if="equipamento.geometria && equipamento.propriedades">
      <div v-for="(valor, chave) in equipamento.propriedades" :key="chave" class="mb-3">
        <label :for="chave" class="form-label">{{ chave.charAt(0).toUpperCase() + chave.slice(1) }}</label>
        <input type="number" class="form-control" :id="chave" v-model="equipamento.propriedades[chave]" :required="true">
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
          tipo: '',
          geometria: '',
          propriedades: {}
        },
        tipos: [],
        geometrias: [],
        equipamentoCriado: false
      };
    },
    computed: {
      equipamentoExibido() {
        const tipoSelecionado = this.tipos.find(tipo => tipo.id === this.equipamento.tipo);
        const geometriaSelecionada = this.geometrias.find(geometria => geometria.id === this.equipamento.geometria);

        return {
          nome: this.equipamento.nome,
          tipo: tipoSelecionado ? tipoSelecionado.nome : 'Não selecionado',
          geometria: geometriaSelecionada ? geometriaSelecionada.nome : 'Não selecionado'
        };
      },
      isFormInvalid() {
        return !this.equipamento.nome || !this.equipamento.tipo || !this.equipamento.geometria;
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
          tipoId: this.equipamento.tipo,
          geometriaId: this.equipamento.geometria,
          propriedades_fundamentais: { ...this.equipamento.propriedades }
        };

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
        const geometriaSelecionada = this.geometrias.find(geometria => geometria.id === this.equipamento.geometria);
        if (geometriaSelecionada) {
          // Aqui estamos garantindo que as propriedades estarão no objeto
          this.equipamento.propriedades = geometriaSelecionada.propriedades_fundamentais.reduce((acc, chave) => {
            if (geometriaSelecionada.propriedades[chave] !== undefined) {
              acc[chave] = geometriaSelecionada.propriedades[chave];
            } else {
              acc[chave] = 0;  // Valor padrão, caso a chave não tenha um valor inicial
            }
            return acc;
          }, {});
        } else {
          this.equipamento.propriedades = {};
        }
      }
    }
  };
</script>
