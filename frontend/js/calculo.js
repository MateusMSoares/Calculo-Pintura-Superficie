const app = Vue.createApp({
    data() {
        return {
            equipamentoSelecionado: null, // Equipamento selecionado
            equipamentos: [
                {
                    id: 1,
                    nome: "Equipamento A",
                    tipo: "Tipo 1",
                    geometria: "Quadrado",
                    descricao: "Descrição do Equipamento A",
                    dataCriacao: "2023-01-01",
                    localizacao: "Local A"
                },
                {
                    id: 2,
                    nome: "Equipamento B",
                    tipo: "Tipo 2",
                    geometria: "Círculo",
                    descricao: "Descrição do Equipamento B",
                    dataCriacao: "2023-02-01",
                    localizacao: "Local B"
                }
            ],
            resultado: {} // Armazena o resultado do cálculo
        };
    },
    methods: {
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
