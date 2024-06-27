<template>
  <div class="container">
    <div class="row">
      <div class="col-3">
        <div class="card" style="margin-top: 20px">
          <div class="card-body">
            <img :src="user.photo" alt="User Avatar" style="width: 100%" />
          </div>
        </div>
      </div>
      <div class="col-9">
        <div class="card" style="margin-top: 20px">
          <div class="card-header">
            <span style="font-size: 150%"> 用户信息 </span>
            <button
              type="button"
              class="btn btn-primary float-end"
              @click="showAvatarModal"
            >
              更换头像
            </button>
          </div>
          <div class="card-body">
            <div class="profile-header">
              <h2 class="username">{{ user.username }}</h2>
            </div>
            <div class="profile-details">
              <div class="detail-item">
                <span class="label">Rating:</span>
                <span class="value">{{ user.rating }}</span>
              </div>
              <div class="detail-item">
                <span class="label">Email:</span>
                <span class="value">{{ user.email }}</span>
              </div>
            </div>
            <div class="profile-actions">
              <button class="btn btn-secondary" @click="showPasswordModal">
                更换密码
              </button>
              <button class="btn btn-secondary" @click="showEmailModal">
                更换邮箱
              </button>
              <button class="btn btn-danger" @click="logout">登出</button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Modal for changing avatar -->
    <div
      class="modal fade"
      id="avatarModal"
      tabindex="-1"
      aria-labelledby="avatarModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="avatarModalLabel">更换头像</h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body">
            <input
              type="text"
              v-model="newPhotoUrl"
              placeholder="输入新的头像URL"
              class="form-control"
            />
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
            >
              关闭
            </button>
            <button type="button" class="btn btn-primary" @click="changeAvatar">
              保存
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- Modal for changing password -->
    <div
      class="modal fade"
      id="passwordModal"
      tabindex="-1"
      aria-labelledby="passwordModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="passwordModalLabel">更换密码</h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body">
            <input
              type="password"
              v-model="oldPassword"
              placeholder="输入旧密码"
              class="form-control mb-2"
            />
            <input
              type="password"
              v-model="newPassword"
              placeholder="输入新密码"
              class="form-control"
            />
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
            >
              关闭
            </button>
            <button
              type="button"
              class="btn btn-primary"
              @click="updatePassword"
            >
              保存
            </button>
          </div>
        </div>
      </div>
    </div>
    <!-- Modal for changing email -->
    <div
      class="modal fade"
      id="emailModal"
      tabindex="-1"
      aria-labelledby="emailModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="emailModalLabel">更换邮箱</h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body">
            <input
              type="email"
              v-model="newEmail"
              placeholder="输入新的邮箱"
              class="form-control"
            />
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
            >
              关闭
            </button>
            <button type="button" class="btn btn-primary" @click="changeEmail">
              保存
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, onMounted } from "vue";
import { useStore } from "vuex";
import $ from "jquery";
import { Modal } from "bootstrap";
import router from "@/router/index";
export default {
  setup() {
    const store = useStore();
    // const r
    const user = ref({
      id: "",
      username: "",
      photo: "",
      rating: 0,
      email: "",
    });
    const newPhotoUrl = ref("");
    const oldPassword = ref("");
    const newPassword = ref("");
    const newEmail = ref("");
    let avatarModal = null;
    let passwordModal = null;
    let emailModal = null;

    const getUserInfo = () => {
      $.ajax({
        url: `http://127.0.0.1:3000/user/account/info/`,
        type: "GET",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(data) {
          user.value = data;
        },
        error(error) {
          console.log(error);
        },
      });
    };

    const changeAvatar = () => {
      const userId = store.state.user.id;
      $.ajax({
        url: "http://127.0.0.1:3000/user/account/update/photo/",
        type: "POST",
        contentType: "application/json",
        data: {
          userId,
          photoUrl: newPhotoUrl.value,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(data) {
          if (data) {
            user.value.photo = newPhotoUrl.value;
            avatarModal.hide();
          } else {
            alert("Failed to update photo");
          }
        },
      });
    };

    const updatePassword = () => {
      const userId = user.value.id;
      const data = {
        userId: userId,
        oldPassword: oldPassword.value,
        newPassword: newPassword.value,
      };
      console.log("updatePassword data:", data);
      $.ajax({
        url: "http://127.0.0.1:3000/user/account/update/password/",
        type: "POST",
        contentType: "application/json",
        data: {
          userId: userId,
          oldPassword: oldPassword.value,
          newPassword: newPassword.value,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(data) {
          if (data) {
            passwordModal.hide();
            oldPassword.value = "";
            newPassword.value = "";
          } else {
            alert("Failed to update password");
          }
        },
        error(error) {
          console.log(error);
        },
      });
    };
    const changeEmail = () => {
      const userId = store.state.user.id;
      console.log("changeEmail userId:", userId);
      console.log("changeEmail newEmail:", newEmail.value);
      $.ajax({
        url: "http://127.0.0.1:3000/user/account/update/email/",
        type: "POST",
        data: {
          userId,
          newEmail: newEmail.value,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(data) {
          if (data) {
            user.value.email = newEmail.value;
            emailModal.hide();
          } else {
            alert("更新邮箱失败");
          }
        },
      });
    };

    const showAvatarModal = () => {
      avatarModal.show();
    };

    const showPasswordModal = () => {
      passwordModal.show();
    };

    const showEmailModal = () => {
      emailModal.show();
    };

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

    onMounted(() => {
      getUserInfo();
      avatarModal = new Modal(document.getElementById("avatarModal"));
      passwordModal = new Modal(document.getElementById("passwordModal"));
      emailModal = new Modal(document.getElementById("emailModal")); // 初始化邮箱模态框
    });

    return {
      user,
      newPhotoUrl,
      oldPassword,
      newPassword,
      newEmail,
      changeAvatar,
      updatePassword,
      changeEmail,
      showAvatarModal,
      showPasswordModal,
      showEmailModal,
      logout,
    };
  },
};
</script>

<style scoped>
.user-profile {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  text-align: center;
}

.profile-header {
  text-align: center;
}

.avatar {
  width: 100%;
  border-radius: 50%;
  object-fit: cover;
  cursor: pointer;
}

.username {
  margin-top: 10px;
  font-size: 24px;
  font-weight: bold;
}

.profile-details {
  margin-top: 20px;
}

.detail-item {
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
}

.label {
  font-weight: bold;
}

.value {
  color: #555;
}

.profile-actions {
  margin-top: 20px;
  display: flex;
  justify-content: space-around;
}

.profile-actions button {
  padding: 10px 20px;
  font-size: 16px;
  cursor: pointer;
  border: none;
  border-radius: 4px;
  background-color: #007bff;
  color: #fff;
}

.profile-actions button:hover {
  background-color: #0056b3;
}

/* Modal Styles */
.modal-content {
  border-radius: 8px;
  text-align: center;
}
</style>
