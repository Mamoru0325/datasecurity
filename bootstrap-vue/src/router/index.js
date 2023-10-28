import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import store from '@/store/index.js';

const routes = [
  {
    path: '/',
    name: 'home',
    beforeEnter:checkAuth,
    component: HomeView
  }, {
        path: 'timeslow/:no/:time/:point',
        name: 'timeslow',
        beforeEnter: requireAuth,
        component: () => import('../views/TimeslowView.vue')
      },
  {
    path: '/about',
    name: 'about',
    beforeEnter:checkAuth,
    component: () => import('../views/AboutView.vue')
  },
  {
    path: '/profile',
    name: 'MyProfile',
    component: () => import('../components/Profile.vue')
  },
  {
    path: '/login',
    beforeEnter:checkAuth,
    component: () => import('../views/Login/LoginView.vue'),
    // beforeEnter: checkLogin
  },
  {
    path: '/register',
    beforeEnter:checkAuth,
    component: () => import('../views/Login/RegisterView.vue')
  },
  {
    path: '/user/', 
    children: [
      {
        path: '',
        name: 'userhome',
        beforeEnter: requireAuth,
        component: HomeView
      },
      {
        path: 'about',
        name: 'aboutuser',
        beforeEnter: requireAuth,
        component: () => import('../views/AboutView.vue')
      },
      {
        path: 'game',
        name: 'game',
        beforeEnter: requireAuth,
        component: () => import('../views/GameView.vue')
      },
      {
        path: 'score',
        name: 'score',
        beforeEnter: requireAuth,
        component: () => import('../views/ScoreView.vue')
      },
      {
        path: 'cizar',
        name: 'cizar',
        beforeEnter: requireAuth,
        component: () => import('../views/CizarView.vue')
      },
      {
        path: 'vigenere',
        name: 'vigenere',
        beforeEnter: requireAuth,
        component: () => import('../views/VigenereView.vue')
      },
      {
        path: 'permutation',
        name: 'permutation',
        beforeEnter: requireAuth,
        component: () => import('../views/PermutationView.vue')
      },
      {
        path: 'feedback',
        name: 'feedback',
        beforeEnter: requireAuth,
        component: () => import('../views/FeedbackView.vue')
      }]
  },{ path: '/:pathMatch(.*)*', component: () => import('../components/Error404.vue')}
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

router.beforeEach((to, from, next) => {
  const publicPages = ['/login', '/register', '/','/about'];
  const authRequired = !publicPages.includes(to.path);
  const loggedIn = localStorage.getItem('user');

  // trying to access a restricted page + not logged in
  // redirect to login page
  if (authRequired && !loggedIn) {
    next('/');
  } else {
    next();
  }
});

function checkAuth(to, from, next) {
  const loggedIn = localStorage.getItem('user');
  if (loggedIn) {
    if(store.state.auth.user.roles[0]  === 'ROLE_User')
    next({
      path: "/user",
    });
    if(store.state.auth.user.roles[0]  === 'ROLE_Admin')
    next({
      path: "/admin",
    });
  } else {
    next();
  }
}
function requireAuth(to, from, next) {
  const loggedIn = localStorage.getItem('user');
  if (!loggedIn) {
    next({
      path: "/login",
      query: { redirect: to.fullPath }
    });
  } else {
    next();
  }
}
export default router
