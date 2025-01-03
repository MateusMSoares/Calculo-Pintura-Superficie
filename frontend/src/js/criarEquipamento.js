// Importando Vue e Axios no início do seu arquivo
import { createApp } from 'vue';  // Para usar o Vue
import axios from 'axios';         // Para usar o Axios

const app = createApp({
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
      const propriedadesPreenchidas = Object.keys(this.equipamento.propriedades).length > 0;
      return !this.equipamento.nome || !this.equipamento.tipo || !this.equipamento.geometria || !propriedadesPreenchidas;
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
        console.log('Equipamento criado com sucesso:', response.data);
      } catch (error) {
        console.error('Erro ao criar equipamento:', error);
        alert('Erro ao criar equipamento. Por favor, tente novamente.');
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
      if (geometriaSelecionada && Array.isArray(geometriaSelecionada.propriedades_fundamentais)) {
        this.equipamento.propriedades = geometriaSelecionada.propriedades_fundamentais.reduce((acc, chave) => {
          acc[chave] = geometriaSelecionada.propriedades[chave] !== undefined ? geometriaSelecionada.propriedades[chave] : 0;
          return acc;
        }, {});
      } else {
        this.equipamento.propriedades = {};
      }
    }
  }    
});

// Monta a aplicação Vue no elemento com o id "app"
app.mount('#app');
