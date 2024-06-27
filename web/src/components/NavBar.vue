<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container" style="padding-left: 55px; padding-right: 55px">
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarText"
        aria-controls="navbarText"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <router-link
        class="navbar-nav me-auto mb-2 mb-lg-0 nav-link"
        active-class="active"
        :to="{ name: 'pk_index' }"
      >
        King Of Bots</router-link
      >
      <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <router-link
              class="nav-link"
              active-class="active"
              :to="{ name: 'pk_index' }"
            >
              对战</router-link
            >
          </li>
          <li class="nav-item">
            <router-link
              class="nav-link"
              active-class="active"
              :to="{ name: 'record_index' }"
            >
              对局列表</router-link
            >
          </li>
          <li class="nav-item">
            <router-link
              class="nav-link"
              active-class="active"
              :to="{ name: 'ranklist_index' }"
            >
              排行榜</router-link
            >
          </li>
        </ul>
        <ul class="navbar-nav" v-if="$store.state.user.is_login">
          <li class="nav-item dropdown">
            <!-- <a
              class="nav-link dropdown-toggle"
              href="#"
              role="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              {{ $store.state.user.username }}
            </a>
            <ul class="dropdown-menu navbar-dark bg-dark">
              <router-link
                class="nav-link"
                active-class="active"
                :to="{ name: 'user_Bot_index' }"
              >
                我的Bot</router-link
              >
              <router-link
                class="nav-link"
                active-class="active"
                :to="{ name: 'user_account_info' }"
              >
                我的信息</router-link
              >
              <router-link
                class="nav-link"
                active-class="active"
                @click="logout"
                :to="{ name: 'pk_index' }"
              >
                退出</router-link
              >
            </ul> -->

            <ul class="route-menu navbar-dark bg-dark">
              <!-- <li>{{ $store.state.user.username }}</li> -->
              <li>
                <router-link
                  class="nav-link"
                  active-class="active"
                  :to="{ name: 'user_Bot_index' }"
                >
                  我的Bot</router-link
                >
              </li>
              <li>
                <router-link
                  class="nav-link"
                  active-class="active"
                  :to="{ name: 'user_account_info' }"
                >
                  我的信息</router-link
                >
              </li>
              <li>
                <router-link
                  class="nav-link"
                  active-class="active"
                  @click="logout"
                  :to="{ name: 'pk_index' }"
                >
                  退出</router-link
                >
              </li>
            </ul>
          </li>
        </ul>
        <ul class="navbar-nav" v-else>
          <li class="nav-item">
            <router-link
              class="nav-link"
              :to="{ name: 'user_account_login' }"
              role="button"
            >
              登录
            </router-link>
          </li>
          <li class="nav-item">
            <router-link
              class="nav-link"
              :to="{ name: 'user_account_register' }"
              role="button"
            >
              注册
            </router-link>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>
<script>
import { useRoute } from "vue-router";
import { computed } from "vue";
import { useStore } from "vuex";
import router from "@/router/index";
export default {
  setup() {
    const store = useStore();
    const route = useRoute();
    let route_name = computed(() => route.name);

    const logout = () => {
      store
        .dispatch("logout")
        .then(() => {
          // 登出操作完成后，导航到登录页面
          router.push({ name: "user_account_login" });
        })
        .catch((error) => {
          // 处理可能发生的错误
          console.error("Logout failed: ", error);
        });
    };
    return {
      route_name,
      logout,
    };
  },
};
</script>

<style scoped>
.route-menu {
  color: #fff;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
}

.route-menu li {
  list-style-type: none;
}
</style>
