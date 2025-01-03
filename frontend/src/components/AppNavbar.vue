<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">Meu Site</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item">
            <router-link class="nav-link" aria-current="page" to="/">Home</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/criar-equipamento">Criar Equipamento</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/equipamentos">Equipamentos</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/calculo">Calculo</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/sobre">Sobre</router-link>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  name: 'AppNavbar',
};
</script>

