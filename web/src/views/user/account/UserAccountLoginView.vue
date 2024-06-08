<template>
  <!-- <ContentField> -->
  <div class="login-wrapper">
    <div class="login-container">
      <form @submit.prevent="login" class="login-form">
        <h3 class="login-title">Login</h3>
        <div class="input-group">
          <input
            v-model="username"
            type="text"
            class="form-control"
            id="username"
            placeholder="username"
          />
        </div>
        <div class="input-group">
          <input
            v-model="password"
            type="password"
            class="form-control"
            id="password"
            placeholder="password"
          />
        </div>
        <div class="error-message">{{ error_message }}</div>
        <button type="submit" class="btn btn-primary">Login</button>
        <div class="signup-link">
          <span
            >Don't have an account?
            <router-link to="/user/account/register/"
              >Sign up</router-link
            ></span
          >
        </div>
      </form>
    </div>
  </div>
  <!-- </ContentField> -->
</template>

<script>
// import ContentField from "../../../components/ContentField.vue";
import { useStore } from "vuex";
import { ref } from "vue";
import router from "@/router/index";
export default {
  components: {
    // ContentField,
  },
  setup() {
    const store = useStore();
    let username = ref("");
    let password = ref("");
    let error_message = ref("");

    const jwt_token = localStorage.getItem("jwt_token");
    if (jwt_token) {
      store.commit("updateToken", jwt_token);
      store.dispatch("getinfo", {
        success() {
          router.push({ name: "home" });
        },
        error() {},
      });
    }

    const login = () => {
      error_message.value = "";
      store.dispatch("login", {
        username: username.value,
        password: password.value,
        success() {
          store.dispatch("getinfo", {
            success() {
              router.push({ name: "home" });
            },
          });
        },
        error() {
          error_message.value = "用户名或密码错误";
        },
      });
    };

    return {
      username,
      password,
      error_message,
      login,
    };
  },
};
</script>

<style scoped>
body {
  background: linear-gradient(to right, #e0c3fc, #8ec5fc);
  height: 100vh;
  margin: 0;
  display: flex;
  justify-content: center;
  align-items: center;
}

.login-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  width: 100vw;
}

.login-container {
  background: #fff;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  padding: 40px;
  width: 500px;
  height: 500px;
}

.login-form {
  display: flex;
  flex-direction: column;
  height: 100%;
  justify-content: space-between;
}

.login-title {
  text-align: center;
  font-size: 28px;
  margin-bottom: 30px;
  font-weight: bold;
}

.input-group {
  margin-bottom: 20px;
}

.form-control {
  border: none;
  border-bottom: 1px solid #ccc;
  border-radius: 0;
  padding: 10px;
  box-shadow: none;
}

.form-control:focus {
  border-bottom: 1px solid #007bff;
  box-shadow: none;
}

.btn-primary {
  background: linear-gradient(to right, #74ebd5, #acb6e5);
  border: none;
  color: white;
  padding: 12px;
  cursor: pointer;
  transition: background 0.3s;
  font-size: 16px;
}

.btn-primary:hover {
  background: linear-gradient(to right, #acb6e5, #74ebd5);
}

.error-message {
  color: red;
  margin-bottom: 15px;
  text-align: center;
}

.signup-link {
  text-align: center;
  margin-top: 15px;
}

.signup-link a {
  color: #007bff;
  text-decoration: none;
}

.signup-link a:hover {
  text-decoration: underline;
}
</style>
