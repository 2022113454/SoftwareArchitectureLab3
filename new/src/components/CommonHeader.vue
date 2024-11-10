<template>
  <div class="header-container">
    <div class="l-content">
      <el-button
          style="margin-right: 20px"
          @click="handleMenu"
          icon="el-icon-menu"
          size="mini"
      ></el-button>
      <p class="text">{{ activePath }}</p>
    </div>
    <div class="r-content">
      <el-dropdown @command="handleClick">
        <span class="el-dropdown-link">
          <img v-if="this.$store.state.tab.userType === 'manager'" src="../assets/images/manager.jpeg"  class="user" />
         <img v-else-if="this.$store.state.tab.userType === 'student'" src="../assets/images/student.jpeg" class="user"/>
          <img v-else-if="this.$store.state.tab.userType === 'executor'" src="../assets/images/executor.jpeg" class="user"/>
          <img v-else-if="this.$store.state.tab.userType === 'staff'" src="../assets/images/staff.jpg" class="user"/>
          <img v-else-if="this.$store.state.tab.userType === 'company'" src="../assets/images/company.jpeg" class="user"/>
          <img v-else src="../assets/logo.png" class="logo"/>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="center">个人中心</el-dropdown-item>
          <el-dropdown-item command="cancel">退出</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>
<script>
import Cookie from "js-cookie";
import { mapState, mapActions } from "vuex";
export default {
  data() {
    return {
      tab: "",
    };
  },
  methods: {
    handleMenu() {
      this.$store.commit("collapseMenu");
      console.log(this.activePath);
    },
    ...mapActions(["setActivePath"]),
    handleClick(command) {
      if (command === "cancel") {
        //清除cookie中的token
        Cookie.remove("token");
        // 清除cookie中的menu
        Cookie.remove("menu");
        this.$router.push("/login");
      }
      if (command === "center") {
        if (this.$route.path !== "/personalCenter")
          this.$router.push("/personalCenter");
      }
    },
  },
  computed: {
    activePath() {
      if (this.$route.path === "/personalCenter") return "个人中心";
      else return this.$store.state.tab.activePath;
    },
    tags() {
      return this.$store.state.tab.tabsList;
    },
  },
};
</script>
<style lang="less" scoped>
.header-container {
  background-color: #333;
  height: 50px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;

  .text {
    color: #fff;
    font-size: 14px;
    margin-left: 10px;
  }

  .logo {
    width: 40px; /* 图像宽度 */
    height: 40px; /* 图像高度 */
  }
  .user {
    width: 75px; /* 图像宽度 */
    height: 43px; /* 图像高度 */
    border: 5px solid #ccc; /* 添加边框 */
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 添加阴影 */
    border-radius: 15px; /* 圆角效果 */
    object-fit: cover; /* 保持图像的宽高比 */
    transition: transform 0.3s; /* 添加过渡效果 */
  }
  .user:hover {
    transform: scale(1.05); /* 鼠标悬停时放大 */
  }

  .l-content {
    display: flex;
    align-items: center;

    /deep/.el-breadcrumb__item {
      .el-breadcrumb__inner {
        font-weight: normal;

        &.is-link {
          color: #666;
        }
      }

      &:last-child {
        .el-breadcrumb__inner {
          color: #fff;
        }
      }
    }
  }
}
</style>
