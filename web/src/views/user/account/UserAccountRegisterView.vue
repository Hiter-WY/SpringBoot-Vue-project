<template>
  <div class="register-wrapper">
    <div class="register-container">
      <form @submit.prevent="register" class="register-form">
        <h3 class="register-title">Register</h3>
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
        <div class="input-group">
          <input
            v-model="confirmedPassword"
            type="password"
            class="form-control"
            id="confirmedPassword"
            placeholder="confirm password"
          />
        </div>
        <div class="error-message">{{ error_message }}</div>
        <button type="submit" class="btn btn-primary">Register</button>
      </form>
    </div>
  </div>
</template>
<script>
import { ref } from "vue";
import router from "@/router/index";
import $ from "jquery";

export default {
  setup() {
    let username = ref("");
    let password = ref("");
    let confirmedPassword = ref("");
    let error_message = ref("");

    const register = () => {
      $.ajax({
        url: "http://127.0.0.1:3000/user/account/register/",
        type: "post",
        data: {
          username: username.value,
          password: password.value,
          confirmedPassword: confirmedPassword.value,
        },
        success(resp) {
          if (resp.error_message === "success") {
            router.push({ name: "user_account_login" });
          } else {
            error_message.value = resp.error_message;
          }
        },
      });
    };

    return {
      username,
      password,
      confirmedPassword,
      error_message,
      register,
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

.register-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  width: 100vw;
}

.register-container {
  background: #fff;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  padding: 40px;
  width: 500px;
  height: 500px;
}

.register-form {
  display: flex;
  flex-direction: column;
  height: 100%;
  justify-content: space-between;
}

.register-title {
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
</style>
