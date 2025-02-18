<template>
  <div class="form-group">
    <label for="diametro">Diâmetro</label>
    <input
      type="number"
      id="diametro"
      :value="equipamento.diametro"
      @input="updateEquipamento('diametro', $event.target.value)"
      required
    />
    
    <label for="altura">Altura</label>
    <input
      type="number"
      id="altura"
      :value="equipamento.altura"
      @input="updateEquipamento('altura', $event.target.value)"
      required
    />
  </div>
</template>

<script>
export default {
  props: {
    equipamento: {
      type: Object,
      required: true,
    },
  },
  methods: {
    // Emite um evento para o componente pai atualizar o valor
    updateEquipamento(key, value) {
      this.$emit('updateEquipamento', { key, value });
    },
  },
};
</script>

<style scoped>
.form-group {
  margin-bottom: 1rem;
}

input[type="text"],
input[type="number"] {
  width: 100%;
  padding: 8px;
  margin-top: 5px;
  font-size: 1rem;
  border-radius: 4px;
  border: 1px solid #ccc;
}
</style>
