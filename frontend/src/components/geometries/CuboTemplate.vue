<template>
    <div>
      <label for="lado">Lado</label>
      <input
        type="number"
        id="lado"
        v-model="equipamento.lado"
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
  };
  </script>  