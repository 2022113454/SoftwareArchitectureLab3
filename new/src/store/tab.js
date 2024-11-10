import permission from "@/api/mockServeData/permission";
import Cookie from "js-cookie";
export default {
  state: {
    isCollapse: false, // 控制菜单的展开和收起
    activePath: "首页", // 当前激活的tab
    userName: "",// 用户名
    userType: "", // 用户类型
    role: "", // 角色
    menu: [],
    permissions: {
      // 权限数据
      manager: [
        // 经理
        {
          path: "/home",
          name: "home",
          label: "首页",
          icon: "s-home",
          url: "Home.vue",
        },
        {
          path: "/course",
          name: "course",
          label: "课程管理",
          icon: "video-play",
          url: "Course.vue",
        },
        {
          path: "/application",
          name: "application",
          label: "申请管理",
          icon: "message-solid",
          url: "Application.vue",
        },
        {
          label: "员工管理",
          icon: "user",
          children: [
            {
              path: "/executor",
              name: "executor",
              label: "执行人管理",
              icon: "office-building",
              url: "Executor.vue",
            },
            {
              path: "/teacher",
              name: "teacher",
              label: "讲师管理",
              icon: "notebook-2",
              url: "Teacher.vue",
            },
          ],
        },
        {
          path: "/report",
          name: "report",
          label: "报表管理",
          icon: "data-analysis",
          url: "Report.vue",
        },
        {
          path: "/courseAnn",
          name: "courseAnn",
          label: "课程公告",
          icon: "postcard",
          url: "CourseAnn.vue",
        }
      ],
      executor: [
        // 执行者
        {
          path: "/home",
          name: "home",
          label: "首页",
          icon: "s-home",
          url: "Home.vue",
        },
        {
          path: "/courseOfExe",
          name: "courseOfExe",
          label: "课程管理",
          icon: "video-play",
          url: "CourseOfExe.vue",
        },
        {
          path: "/student",
          name: "student",
          label: "学生管理",
          icon: "collection",
          url: "Student.vue",
        },
        {
          path: "/teacher",
          name: "teacher",
          label: "讲师管理",
          icon: "notebook-2",
          url: "Teacher.vue",
        },
        {
          path: "/enrollManage",
          name: "enrollManage",
          label: "报名管理",
          icon: "message-solid",
          url: "EnrollManage.vue",
        },
        {
          path: "/courseInfo",
          name: "courseInfo",
          label: "发布通知",
          icon: "s-promotion",
          url: "CourseInfo.vue",
        },
        {
          path: "/courseAnn",
          name: "courseAnn",
          label: "课程公告",
          icon: "postcard",
          url: "CourseAnn.vue",
        },
        {
          path: "/evaSummary",
          name: "evaSummary",
          label: "课程评价汇总",
          icon: "data-analysis",
          url: "EvaSummary.vue",
        }
      ],
      company: [
        // 软件公司
        {
          path: "/home",
          name: "home",
          label: "首页",
          icon: "s-home",
          url: "Home.vue",
        },
        {
          path: "/submitApplication",
          name: "submitApplication",
          label: "提交申请",
          icon: "s-promotion",
          url: "SubmitApplication.vue",
        },
        {
          path: "/courseAnn",
          name: "courseAnn",
          label: "课程公告",
          icon: "postcard",
          url: "CourseAnn.vue",
        }
      ],
      student: [
        // 学员
        {
          path: "/home",
          name: "home",
          label: "首页",
          icon: "s-home",
          url: "Home.vue",
        },
        {
          path: "/courseAnn",
          name: "courseAnn",
          label: "课程公告",
          icon: "postcard",
          url: "CourseAnn.vue",
        },
        {
          path: "/courseOfStu",
          name: "courseOfStu",
          label: "选课管理",
          icon: "video-play",
          url: "CourseOfStu.vue",
        },
        {
          path: "/evaluation",
          name: "evaluation",
          label: "课程评价",
          icon: "edit",
          url: "Evaluation.vue",
        },
        {
          path: "/payment",
          name: "payment",
          label: "课程缴费",
          icon: "wallet",
          url: "Payment.vue",
        },
        {
          path: "/personalCenter",
          name: "personalCenter",
          label: "个人中心",
          icon: "user-solid",
          url: "PersonalCenter.vue",
        }
      ],
      staff: [
        // 现场工作人员
        {
          path: "/home",
          name: "home",
          label: "首页",
          icon: "s-home",
          url: "Home.vue",
        },
        {
          path: "/signIn",
          name: "signIn",
          label: "签到管理",
          icon: "s-check",
          url: "SignIn.vue",
        },
        {
          path: "/evaSummary",
          name: "evaSummary",
          label: "课程评价汇总",
          icon: "data-analysis",
          url: "EvaSummary.vue",
        }
      ],
    },
  },
  mutations: {
    setUserName(state, val) {
      state.userName = val;
    },
    setUserType(state, val) {
      state.userType = val;
    },
    // 修改菜单展开收起的方法
    collapseMenu(state) {
      state.isCollapse = !state.isCollapse;
    },
    // 设置当前激活的tab
    setActivePath(state, path) {
      state.activePath = path;
      console.log(state.activePath);
    },
    // 设置menu的数据
    setMenu(state, val) {
      state.menu = state.permissions[val];
      Cookie.set("menu", JSON.stringify(state.permissions[val]));
    },
    setRole(state, val) {
      state.role = val;
    },
    // 动态注册路由
    addMenu(state, router) {
      // 判断缓存中是否有数据
      if (!Cookie.get("menu")) return;
      const menu = JSON.parse(Cookie.get("menu"));
      state.menu = menu;
      // 组装动态路由的数据
      const menuArray = [];
      menu.forEach((item) => {
        if (item.children) {
          item.children = item.children.map((item) => {
            item.component = () => import(`../views/${item.url}`);
            return item;
          });
          menuArray.push(...item.children);
        } else {
          item.component = () => import(`../views/${item.url}`);
          menuArray.push(item);
        }
      });
      // 路由的动态添加
      console.log(menuArray);
      menuArray.forEach((item) => {
        router.addRoute("Main", item);
      });
    },
  },
};
