import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "../components/Login";
import ManageHome from "../components/ManageHome";
import MarketHome from "../components/MarketHome";
import SupplyHome from "../components/SupplyHome";
import Register from "../components/Register";
import Infomation from "../components/Menu/Infomation";
import Welcome1 from "../components/Menu/Welcome1";
import Welcome2 from "../components/Menu/Welcome2";
import Welcome3 from "../components/Menu/Welcome3";
import Message1 from "../components/Menu/Message1";
import Message2 from "../components/Menu/Message2";
import Message3 from "../components/Menu/Message3";
import Allsup2 from "../components/Menu/Allsup2";
import Allsup1 from "../components/Menu/Allsup1";
import Black2 from "../components/Menu/Black2";
import Black1 from "../components/Menu/Black1";
import Read2 from "../components/Menu/Read2";
import Read1 from "../components/Menu/Read1";
import Addblack from "../components/Menu/Addblack";
import Readblack from "../components/Menu/Readblack";

Vue.use(VueRouter)

const routes = [
  {
    path:"/",
    redirect:"/login"
  },
  {
    path:"/login",
    component:Login
  },
  {
    path: "/manageHome",
    component: ManageHome,
    redirect: "/welcome1",
    children: [
      { path:"/welcome1", component: Welcome1},
      { path:"/message1", component: Message1},
      { path:"/allsup1", component: Allsup1},
      { path:"/black1", component: Black1},
      { path:"/read1", component: Read1},
      { path:"/readblack", component: Readblack},
    ],
  },
  {
    path: "/marketHome",
    component: MarketHome,
    redirect: "/welcome2",
    children: [
      { path:"/welcome2", component: Welcome2},
      { path:"/message2", component: Message2},
      { path:"/allsup2", component: Allsup2},
      { path:"/black2", component: Black2},
      { path:"/read2", component: Read2},
      { path:"/addblack", component: Addblack},
    ],
  },
  {
    path: "/supplyHome",
    component: SupplyHome,
    redirect: "/welcome3",
    children: [
      { path:"/welcome3", component: Welcome3},
      { path:"/infomation", component: Infomation},
      { path:"/message3", component: Message3},
    ],
  },
  {
    path: "/register",
    component: Register
  }
]

const router = new VueRouter({
  routes
})

router.beforeEach((to,from,next)=>{
  if(to.path=='/'||to.path=='/login'||to.path=='/register') return next();
  const userFlag=window.sessionStorage.getItem("user");
  if(!userFlag) return next('/login');
  next();
})

export default router
