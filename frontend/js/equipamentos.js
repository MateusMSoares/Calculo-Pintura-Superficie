const app = Vue.createApp({
    data() {
      return {
        equipamentos: [
          {
            id: 1,
            nome: "Equipamento A",
            tipo: "Tipo 1",
            geometria: "Quadrado",
            descricao: "Descrição do Equipamento A",
            dataCriacao: "2023-01-01",
            localizacao: "Local A",
            expandido: false // Detalhes inicialmente ocultos
          },
          {
            id: 2,
            nome: "Equipamento B",
            tipo: "Tipo 2",
            geometria: "Círculo",
            descricao: "Descrição do Equipamento B",
            dataCriacao: "2023-02-01",
            localizacao: "Local B",
            expandido: false // Detalhes inicialmente ocultos
          }
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
          const response = await axios.get('http://localhost:8080/api/equipamentos');
          
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
  