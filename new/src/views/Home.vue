<template>
  <div class="dashboard-container">
    <div class="card-grid">
      <div class="card" v-for="card in cards" :key="card.title" :style="{ backgroundColor: card.color }"
        @click="navigateTo(card.link, card.menuId)">
        <div class="card-header">
          <span class="card-icon">{{ card.icon }}</span>
          <span class="card-title">{{ card.title }}</span>
        </div>
        <div class="card-body">
          <p>{{ card.description }}</p>
          <span class="card-more">更多</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  created() {
    this.loadCards();
  },
  data() {
    return {
      allCards: [
        { title: "课程管理", description: "课程管理说明", icon: "📚", color: "#ff8c00", link: "/course", menuId: "menu-course" },
        { title: "课程管理", description: "课程管理说明", icon: "📚", color: "#ff8c00", link: "/courseOfExe", menuId: "menu-courseOfExe" },
        { title: "选课管理", description: "选课管理说明", icon: "📚", color: "#ff8c00", link: "/courseOfStu", menuId: "menu-courseOfStu" },
        { title: "课程管理", description: "课程管理说明", icon: "📚", color: "#ff8c00", link: "/courseOfExe", menuId: "menu-courseOfExe" },
        { title: "报表管理", description: "报表管理说明", icon: "📊", color: "#ff69b4", link: "/report", menuId: "menu-report" },
        { title: "学生管理", description: "学生管理说明", icon: "🎓", color: "#32cd32", link: "/student", menuId: "menu-student" },
        { title: "执行人管理", description: "执行人管理说明", icon: "📝", color: "#ffc800", link: "/executor", menuId: "menu-executor" },
        { title: "讲师管理", description: "讲师管理说明", icon: "👨‍🏫", color: "#ff5500", link: "/teacher", menuId: "menu-teacher" },
        { title: "申请管理", description: "申请管理说明", icon: "📋", color: "#00c3ff", link: "/application", menuId: "menu-application" },
        { title: "报名管理", description: "报名管理说明", icon: "📋", color: "#00c3ff", link: "/enrollManage", menuId: "menu-enrollManage" },
        { title: "提交申请", description: "提交申请说明", icon: "📑", color: "#00c3ff", link: "/submitApplication", menuId: "menu-submitApplication" },
        { title: "发布通知", description: "发布培训通知说明", icon: "🔔", color: "#ff69b4", link: "/courseInfo", menuId: "menu-courseInfo" },
        { title: "课程公告", description: "课程公告说明", icon: "📢", color: "#ffc800", link: "/courseAnn", menuId: "menu-courseAnn" },
        { title: "签到管理", description: "签到管理说明", icon: "📋", color: "#32cd32", link: "/signIn", menuId: "menu-signIn" },
        { title: "课程评价", description: "课程评价说明", icon: "⭐️", color: "#32cd32", link: "/evaluation", menuId: "menu-evaluation" },
        { title: "课程缴费", description: "课程缴费说明", icon: "💳", color: "#00c3ff", link: "/payment", menuId: "menu-payment" },
        { title: "课程评价汇总", description: "执行人汇总课程评价", icon: "📈", color: "#00c3ff", link: "/evaSummary", menuId: "menu-evaSummary" },
        { title: "个人中心", description: "个人中心说明", icon: "👤", color: "#ffa500", link: "/personalCenter", menuId: "menu-personalCenter" },
      ],
      cards: []
    };
  },
  methods: {
    navigateTo(link, menuId) {
      document.getElementById(menuId).click();
    },
    loadCards() {
      this.menuData.forEach(menuItem => {
        const card = this.allCards.find(card => card.link === menuItem.path);
        if (card) {
          this.cards.push(card);
        }
        if (menuItem.children) {
          menuItem.children.forEach(subMenuItem => {
            const subCard = this.allCards.find(card => card.link === subMenuItem.path);
            if (subCard) {
              this.cards.push(subCard);
            }
          });
        }
      });
    }
  },
  computed: {
    menuData() {
      return this.$store.state.tab.menu;
    }
  }
};
</script>

<style scoped>
.dashboard-container {
  padding: 20px;
}

.card-grid {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
}

.card {
  flex: 1 1 calc(33.333% - 20px);
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  padding: 20px;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  min-height: 200px;
  cursor: pointer;
}

.card-header {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.card-icon {
  font-size: 24px;
  margin-right: 10px;
}

.card-title {
  font-size: 18px;
  font-weight: bold;
}

.card-body {
  flex-grow: 1;
}

.card-more {
  align-self: flex-end;
  color: #007bff;
  text-decoration: none;
  font-weight: bold;
  margin-top: 10px;
}

.card-more:hover {
  text-decoration: underline;
}
</style>
