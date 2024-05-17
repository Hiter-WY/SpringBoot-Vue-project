<template>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <div class="container" style="padding-left: 55px; padding-right: 55px;">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <router-link class="navbar-nav me-auto mb-2 mb-lg-0 nav-link" active-class="active" :to="{ name: 'pk_index' }"> King Of Bots</router-link>
        <div class="collapse navbar-collapse" id="navbarText">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <router-link class="nav-link" active-class="active" :to="{ name: 'pk_index' }"> 对战</router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav-link" active-class="active" :to="{ name: 'record_index' }"> 对局列表</router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav-link" active-class="active" :to="{ name: 'ranklist_index' }"> 排行榜</router-link>
            </li>
          </ul>
          <ul class="navbar-nav">
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                Wa1ess
              </a>
              <ul class="dropdown-menu navbar-dark bg-dark">
                <router-link class="nav-link" active-class="active" :to="{ name: 'user_Bot_index' }"> 我的Bot</router-link>
                <router-link class="nav-link" active-class="active" :to="{ name: 'pk_index' }"> 退出</router-link>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </nav>

</template>



<script>

</script>



<style scoped>



</style>