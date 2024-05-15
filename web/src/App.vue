<template>
  <div>
    <NavBar></NavBar>
    <router-view/>
  </div>
</template>

<script>
import NavBar from './components/NavBar.vue'

export default {
  components: {
    NavBar
  }
}
</script>

<style>
body {
  background-image: url("@/assets/1.jpeg");
  background-size: cover;
}
</style>
