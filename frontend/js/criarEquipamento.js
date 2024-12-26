const app = Vue.createApp({
    data() {
      return {
        equipamento: {
          nome: '',
          tipo: '',
          geometria: ''
        },
        tipos: [
          { id: 1, nome: 'Tanque' },
          { id: 2, nome: 'Retentor' }
        ],
        geometrias: [
          { id: 1, nome: 'Cubico' },
          { id: 2, nome: 'Esfera' }
        ],
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
    methods: {
      async criarEquipamento() {
        const equipamentoParaEnviar = {
          nome: this.equipamento.nome,
          tipoId: this.equipamento.tipo,
          geometriaId: this.equipamento.geometria
        };
      
        try {
          const response = await axios.post('http://localhost:8080/equipamento/', equipamentoParaEnviar); // Enviando os dados mapeados
          console.log('Equipamento criado:', response.data);
          this.equipamentoCriado = true;
        } catch (error) {
          console.error('Erro ao criar equipamento:', error);
        }
      }      
    }
  });
  
  app.mount('#app');
  