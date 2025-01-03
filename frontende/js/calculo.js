const app = Vue.createApp({
    data() {
        return {
            equipamentoSelecionado: null, 
            equipamentos: [
                
            ],
            resultado: {} 
        };
    },
    mounted() {
        this.carregarEquipamentos();
    },
    methods: {
        async carregarEquipamentos() {
            try {
              // Aqui você faz a requisição para carregar os equipamentos da API
              const response = await axios.get('http://localhost:8080/equipamento/');
              console.log(response.data);
              // Caso a API retorne os dados, atualiza a lista de equipamentos
              this.equipamentos = response.data.map(equipamento => ({
                ...equipamento,
                expandido: false // Inicialmente os detalhes estão ocultos
              }));
            } catch (error) {
              console.error('Erro ao carregar equipamentos:', error);
            }
          },
        calcularEquipamento() {
            if (this.equipamentoSelecionado !== null) {
                // Busca o equipamento selecionado
                const equipamento = this.equipamentos.find(e => e.id === this.equipamentoSelecionado);
                if (equipamento) {
                    // Aqui você pode fazer o cálculo real. Para este exemplo, estamos apenas simulando um cálculo.
                    this.resultado = {
                        nome: equipamento.nome,
                        calculo: `Resultado do cálculo para ${equipamento.nome}`
                    };

                    // Exibir o modal com os resultados
                    const modal = new bootstrap.Modal(document.getElementById('resultadoModal'));
                    modal.show();
                }
            }
        }
    }
});

app.mount('#app');
