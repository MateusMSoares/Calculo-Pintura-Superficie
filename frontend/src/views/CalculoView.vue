<template>
  <div class="container mt-5">
    <h1>Calculo</h1>

    <!-- Dropdown para selecionar equipamento -->
    <div class="mb-3">
      <label for="equipamentoSelect" class="form-label">Selecione o Equipamento</label>
      <select class="form-select" v-model="equipamentoSelecionado">
        <option v-for="equipamento in equipamentos" :key="equipamento.id" :value="equipamento">
          {{ equipamento.nome }}
        </option>
      </select>
    </div>

    <!-- Botão para Calcular -->
    <button class="btn btn-primary" @click="calcularEquipamento" :disabled="!equipamentoSelecionado">Calcular</button>

    <!-- Modal de Resultado -->
    <div class="modal fade" id="resultadoModal" tabindex="-1" aria-labelledby="resultadoModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-lg"> <!-- Modal Largo -->
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="resultadoModalLabel">Resultado do Cálculo</h5>
            <button type="button" class="btn-close" @click="closeModal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <p class="detalhes"><strong>Equipamento Selecionado:</strong> <strong>{{ equipamentoSelecionado?.nome }}</strong></p>
            <p class="detalhes">Geometria: <strong>{{ geometriaNome }}</strong></p>
            <p class="detalhes">Tipo: <strong>{{ tipoNome }}</strong></p>


            <!-- Layout lado a lado com mais controle e espaçamento -->
            <div class="d-flex">
              <div class="modal-column">
                <p class="section-title">Resultado do Cálculo:</p>
                <ul>
                  <li v-for="(valor, chave) in resultado" :key="chave">
                    <span class="key">{{ chave }}:</span> <span class="value">{{ valor + ' metros.'}}</span>
                  </li>
                </ul>
              </div>
              <div class="modal-column">
                <p class="section-title">Fórmula com Valores:</p>
                <ul>
                  <li v-for="(valor, chave) in formulaComValores" :key="chave">
                    <span class="key">{{ chave }}:</span> <span class="value">{{ valor }}</span>
                  </li>
                </ul>
              </div>
              <div class="modal-column">
                <p class="section-title">Fórmula Original:</p>
                <ul>
                  <li v-for="(valor, chave) in formulaOriginal" :key="chave">
                    <span class="key">{{ chave }}:</span> <span class="value">{{ valor }}</span>
                  </li>
                </ul>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" @click="closeModal">Fechar</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
/* Estilos gerais do modal */
.modal-dialog.modal-lg {
  max-width: 95%; /* Modal maior com largura ainda mais ampla */
  max-height: 80vh; /* Define uma altura máxima para o modal */
  margin-top: 10vh; /* Distância do topo da tela */
}

.modal-content {
  padding: 30px;
}

.modal-title {
  font-size: 3rem;
  font-weight: 700;
  align-content: center;
}

.detalhes {
  font-size: 1.2rem;
}

/* Controle preciso do layout dentro do modal */
.d-flex {
  display: flex;
  justify-content: space-between;
  gap: 3rem; /* Aumenta o espaçamento entre as colunas */
}

/* Estilos das colunas dentro do modal */
.modal-column {
  flex: 1; /* Faz cada coluna ter o mesmo tamanho */
  margin-right: 1rem;
}

.modal-column:last-child {
  margin-right: 0;
}

/* Estilo das seções dentro de cada coluna */
.section-title {
  font-size: 1.3rem;
  font-weight: 700;
  margin-bottom: 1rem;
  color: #343a40; /* Cor do título */
}

/* Personalização dos itens de listas */
ul {
  list-style-type: none;
  padding-left: 0;
  font-size: 20px;
}

ul li {
  margin-bottom: 1rem; /* Maior espaço entre os itens */
}

.key {
  font-weight: bold;
  color: #28a745; /* Cor verde para as chaves, agora mais vibrante */
}

.value {
  color: #495057; /* Cor mais escura para os valores */
  font-style: italic;
}

/* Ajuste de espaço entre os itens da lista */
ul li {
  margin-bottom: 1.5rem; /* Aumenta o espaço entre os itens */
}
</style>

<script>
  import axios from 'axios';

  export default {
    data() {
      return {
        equipamentoSelecionado: null, // Aqui armazenamos o objeto completo
        equipamentos: [],
        resultado: {},
        formulaComValores: {},
        formulaOriginal: {},
        geometriaNome: '',
        tipoNome: ''
      };
    },
    mounted() {
      this.carregarEquipamentos();
    },
    methods: {
      async carregarEquipamentos() {
        try {
          const response = await axios.get('http://localhost:8080/equipamento/');
          this.equipamentos = response.data.map(equipamento => ({
            ...equipamento,
            expandido: false
          }));
        } catch (error) {
          console.error('Erro ao carregar equipamentos:', error);
        }
      },
      async calcularEquipamento() {
        try {
          if (this.equipamentoSelecionado !== null) {
            const response = await axios.post('http://localhost:8080/equipamento/calcular', this.equipamentoSelecionado);

            if (response.data) {
              this.resultado = this.normalizarObjeto(response.data.resultado);
              this.formulaComValores = this.normalizarObjeto(response.data.formulas_com_valores);
              this.formulaOriginal = this.normalizarObjeto(response.data.formulas_originais);
              await this.carregarGeometria();
              await this.carregarTipo();
              this.showModal();
            }
          } else {
            console.log('Nenhum equipamento selecionado');
          }
        } catch (error) {
          console.error('Erro ao calcular equipamento:', error);
        }
      },
      async carregarGeometria() {
        try {
          // Verifique se a geometria está disponível
          if (this.equipamentoSelecionado && this.equipamentoSelecionado.geometria) {
            console.log('ID da Geometria:', this.equipamentoSelecionado.geometria);

            // Realizando a requisição corretamente com a interpolação
            const response = await axios.get(`http://localhost:8080/geometria/${this.equipamentoSelecionado.geometria}`);

            this.geometriaNome = response.data.nome;
            console.log('Nome da Geometria:', this.geometriaNome);
          } else {
            console.error('Equipamento selecionado não tem geometria.');
          }
        } catch (error) {
          console.error('Erro ao carregar geometrias:', error);
        }
      },
      async carregarTipo() {
        try {
          // Verifique se o tipo está disponível
          if (this.equipamentoSelecionado && this.equipamentoSelecionado.tipo) {
            console.log('ID do Tipo:', this.equipamentoSelecionado.tipo);

            // Realizando a requisição corretamente com a interpolação
            const response = await axios.get(`http://localhost:8080/tipo/${this.equipamentoSelecionado.tipo}`);

            this.tipoNome = response.data.nome;
            console.log('Nome do Tipo:', this.tipoNome);
          } else {
            console.error('Equipamento selecionado não tem tipo.');
          }
        } catch (error) {
          console.error('Erro ao carregar tipos:', error);
        }
      },
      showModal() {
        const modal = new window.bootstrap.Modal(document.getElementById('resultadoModal'));
        modal.show();
      },
      closeModal() {
        const modal = window.bootstrap.Modal.getInstance(document.getElementById('resultadoModal'));
        modal.hide();
      },
      normalizarObjeto(obj) {
          const novoObj = {};
          for (const chave in obj) {
              if (Object.prototype.hasOwnProperty.call(obj, chave)) {
                  const chaveNormalizada = this.normalizarTexto(chave);
                  novoObj[chaveNormalizada] = obj[chave];
              }
          }
          return novoObj;
      },
      normalizarTexto(texto) {
          return texto
              .replace(/_/g, ' ')  
              .replace(/\b\w/g, letra => letra.toUpperCase())  
      }
    }
  };
</script>