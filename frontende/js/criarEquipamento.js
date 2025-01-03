const app = Vue.createApp({
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
        const response = await axios.post('http://localhost:8080/equipamento/', equipamentoParaEnviar);
        this.equipamentoCriado = true;
      } catch (error) {
        console.error('Erro ao criar equipamento:', error);
      }
    },
    async carregarTipos() {
      try {
        const response = await axios.get('http://localhost:8080/tipo/');
        this.tipos = response.data;
      } catch (error) {
        console.error('Erro ao carregar tipos:', error);
      }
    },
    async carregarGeometrias() {
      try {
        const response = await axios.get('http://localhost:8080/geometria/');
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
});

app.mount('#app');
