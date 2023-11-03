import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import store from '@/store/index.js';

const routes = [
  {
    path: '/',
    name: 'home',
    beforeEnter: checkAuth,
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    beforeEnter: checkAuth,
    component: () => import('../views/AboutView.vue')
  },
  {
    path: '/profile',
    name: 'MyProfile',
    component: () => import('../components/Profile.vue')
  },
  {
    path: '/login',
    beforeEnter: checkAuth,
    component: () => import('../views/Login/LoginView.vue'),
    // beforeEnter: checkLogin
  },
  {
    path: '/register/user',
    beforeEnter: checkAuth,
    component: () => import('../views/Login/RegisterView.vue')
  },
  {
    path: '/register/admin',
    beforeEnter: checkAuth,
    component: () => import('../views/Login/AdminRegisterView.vue')
  },
  {
    path: '/admin/',
    children: [
      {
        path: '',
        name: 'adminhome',
        beforeEnter: requireAuth,
        component: HomeView
      }, {
        path: 'admin',
        name: 'adminad',
        beforeEnter: requireAuth,
        component: () => import('../views/admin/AddadminView.vue')
      }, {
        path: 'feedback',
        name: 'adminfeedback',
        beforeEnter: requireAuth,
        component: () => import('../views/admin/FeedbackView.vue')
      }, {
        path: 'question',
        children: [
          {
            path: '',
            name: 'adminquestion',
            beforeEnter: requireAuth,
            component: () => import('../views/admin/AdminQuestionView.vue')
          }, {
            path: 'type/:type',
            name: 'typequestion',
            beforeEnter: requireAuth,
            component: () => import('../views/admin/TypeQuestionView.vue')
          }, {
            path: 'add/:type',
            name: 'Addquestion',
            beforeEnter: requireAuth,
            component: () => import('../views/admin/CreateQuestionView.vue')
          }, {
            path: 'edit/:type',
            name: 'Editquestion',
            beforeEnter: requireAuth,
            component: () => import('../views/admin/EditQuestionView.vue')
          }
        ]
      }

    ]
  },
  {
    path: '/user/',
    //beforeEnter: [requireAuth,cleandata],
    children: [
      {
        path: '',
        name: 'userhome',
        beforeEnter: [requireAuth, cleandata],
        component: HomeView
      },
      {
        path: 'timeslow/:no',
        name: 'timeslow',
        beforeEnter: requireAuth,
        component: () => import('../views/TimeslowView.vue')
      },
      {
        path: 'scorepoint',
        name: 'scorepoint',
        beforeEnter: requireAuth,
        component: () => import('../views/ScorePointView.vue')
      },
      {
        path: 'about',
        name: 'aboutuser',
        beforeEnter: [requireAuth, cleandata],
        component: () => import('../views/AboutView.vue')
      },
      {
        path: 'game',
        name: 'game',
        beforeEnter: [requireAuth, cleandata],
        component: () => import('../views/GameView.vue')
      },
      {
        path: 'score',
        name: 'score',
        beforeEnter: [requireAuth, cleandata],
        component: () => import('../views/ScoreView.vue')
      },
      {
        path: 'cizar',
        name: 'cizar',
        beforeEnter: [requireAuth, cleandata],
        component: () => import('../views/CizarView.vue')
      },
      {
        path: 'vigenere',
        name: 'vigenere',
        beforeEnter: [requireAuth, cleandata],
        component: () => import('../views/VigenereView.vue')
      },
      {
        path: 'permutation',
        name: 'permutation',
        beforeEnter: [requireAuth, cleandata],
        component: () => import('../views/PermutationView.vue')
      },
      {
        path: 'feedback',
        name: 'feedback',
        beforeEnter: [requireAuth, cleandata],
        component: () => import('../views/FeedbackView.vue')
      }]
  }, { path: '/:pathMatch(.*)*', component: () => import('../components/Error404.vue') }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

router.beforeEach((to, from, next) => {
  const publicPages = ['/login', '/register/user', '/', '/about', '/register/admin'];
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
    if (store.state.auth.user.roles[0] === 'ROLE_User')
      next({
        path: "/user",
      });
    if (store.state.auth.user.roles[0] === 'ROLE_Admin')
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
function cleandata() {
  localStorage.removeItem("datas");
  localStorage.removeItem("no");
  localStorage.removeItem("timer");
}
export default router
