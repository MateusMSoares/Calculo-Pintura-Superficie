<template>
    <div class="container mt-5">
      <h1>Equipamentos</h1>
  
      <div class="list-group">
        <div
          v-for="equipamento in equipamentos"
          :key="equipamento.id"
          class="list-group-item"
          @click="toggleDetalhes(equipamento.id)"
        >
          <h5>{{ equipamento.nome }}</h5>
          <p><strong>Tipo:</strong> {{ equipamento.tipo.nome }}</p>
          <p><strong>Geometria:</strong> {{ equipamento.geometria.nome}}</p>
  
          <div v-if="equipamento.expandido">
            <p><strong>Propriedades:</strong></p>
            <ul>
              <li v-for="(valor, chave) in equipamento.geometria.propriedades" :key="chave">
                <strong>{{ chave }}:</strong> {{ valor }}
              </li>
              <li v-for="(valor, chave) in equipamento.tipo.propriedades" :key="chave">
                <strong>{{ chave }}:</strong> {{ valor }}
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';

  const apiBaseURL = process.env.VUE_APP_API_BASE_URL;
  console.log("teste: ", apiBaseURL);
  
  export default {
    data() {
      return {
        equipamentos: [],
      };
    },
    mounted() {
      this.carregarEquipamentos();
    },
    methods: {
      async carregarEquipamentos() {
        try {
          const response = await axios.get(`${apiBaseURL}/equipamento/`);
          this.equipamentos = response.data.map((equipamento) => ({
            ...equipamento,
            expandido: false,
          }));
          console.log("TESTANDO: ", this.equipamentos);
        } catch (error) {
          console.error('Erro ao carregar equipamentos:', error);
        }
      },
  
      // Função para alternar a visualização dos detalhes do equipamento
      toggleDetalhes(id) {
        const equipamento = this.equipamentos.find((e) => e.id === id);
        if (equipamento) {
          equipamento.expandido = !equipamento.expandido; // Alterna o estado de expansão
        }
      },
    },
  };
  </script>
  