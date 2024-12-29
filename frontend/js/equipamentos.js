const app = Vue.createApp({
    data() {
      return {
        equipamentos: [
        ]
      };
    },
    created() {
      // Descomente a linha abaixo se quiser carregar da API
      this.carregarEquipamentos();
    },
    methods: {
      async carregarEquipamentos() {
        try {
          // Aqui você faz a requisição para carregar os equipamentos da API
          const response = await axios.get('http://localhost:8080/equipamento/');
          
          // Caso a API retorne os dados, atualiza a lista de equipamentos
          this.equipamentos = response.data.map(equipamento => ({
            ...equipamento,
            expandido: false // Inicialmente os detalhes estão ocultos
          }));
        } catch (error) {
          console.error('Erro ao carregar equipamentos:', error);
        }
      },
  
      // Função para alternar a visualização dos detalhes do equipamento
      toggleDetalhes(id) {
        const equipamento = this.equipamentos.find(e => e.id === id);
        if (equipamento) {
          equipamento.expandido = !equipamento.expandido; // Alterna o estado de expansão
        }
      }
    }
  });
  
  app.mount('#app');
  