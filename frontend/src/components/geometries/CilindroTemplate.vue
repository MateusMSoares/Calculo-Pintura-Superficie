<template>
  <div>
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
