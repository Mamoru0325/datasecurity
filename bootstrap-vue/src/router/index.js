import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    beforeEnter: [checkAuth,cleandata],
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    beforeEnter: [checkAuth,cleandata],
    component: () => import('../views/AboutView.vue')
  },
  {
    path: '/login',
    beforeEnter: [checkAuth,cleandata],
    component: () => import('../views/Login/LoginView.vue'),
    // beforeEnter: checkLogin
  },
  {
    path: '/register/user',
    beforeEnter: [checkAuth,cleandata],
    component: () => import('../views/Login/RegisterView.vue')
  },
  {
    path: '/register/admin',
    beforeEnter: [checkAuth,cleandata],
    component: () => import('../views/Login/AdminRegisterView.vue')
  },
  {
    path: '/admin/',
    children: [
      {
        path: '',
        name: 'adminhome',
        beforeEnter: [requireAuth,requireAdminAuth],
        component: HomeView
      }, {
        path: 'admin',
        name: 'adminad',
        beforeEnter: [requireAuth,requireAdminAuth],
        component: () => import('../views/admin/AddadminView.vue')
      },
      {
        path: 'cizar',
        name: 'admincizar',
        beforeEnter: [requireAuth,requireAdminAuth],
        component: () => import('../views/CizarView.vue')
      },
      {
        path: 'vigenere',
        name: 'adminvigenere',
        beforeEnter: [requireAuth,requireAdminAuth],
        component: () => import('../views/VigenereView.vue')
      },
      {
        path: 'permutation',
        name: 'adminpermutation',
        beforeEnter: [requireAuth,requireAdminAuth],
        component: () => import('../views/PermutationView.vue')
      }, {
        path: 'feedback',
        name: 'adminfeedback',
        beforeEnter: [requireAuth,requireAdminAuth],
        component: () => import('../views/admin/FeedbackView.vue')
      }, {
        path: 'question',
        children: [
          {
            path: '',
            name: 'adminquestion',
            beforeEnter: [requireAuth,requireAdminAuth],
            component: () => import('../views/admin/AdminQuestionView.vue')
          }, {
            path: 'type/:type',
            name: 'typequestion',
            beforeEnter: [requireAuth,requireAdminAuth],
            component: () => import('../views/admin/TypeQuestionView.vue')
          }, {
            path: 'add/:type',
            name: 'Addquestion',
            beforeEnter: [requireAuth,requireAdminAuth],
            component: () => import('../views/admin/CreateQuestionView.vue')
          }, {
            path: 'edit/:type',
            name: 'Editquestion',
            beforeEnter: [requireAuth,requireAdminAuth],
            component: () => import('../views/admin/EditQuestionView.vue')
          }
        ]
      }

    ]
  },
  {
    path: '/user/',
    children: [
      {
        path: '',
        name: 'userhome',
        beforeEnter: [requireAuth, cleandata,requireUserAuth],
        component: HomeView
      },
      {
        path: 'timeslow/:no',
        name: 'timeslow',
        beforeEnter: [requireAuth,requireUserAuth],
        component: () => import('../views/TimeslowView.vue')
      },
      {
        path: 'scorepoint',
        name: 'scorepoint',
        beforeEnter: [requireAuth,requireUserAuth],
        component: () => import('../views/ScorePointView.vue')
      },
      {
        path: 'about',
        name: 'aboutuser',
        beforeEnter: [requireAuth, cleandata,requireUserAuth],
        component: () => import('../views/AboutView.vue')
      },
      {
        path: 'game',
        name: 'game',
        beforeEnter:[requireAuth, cleandata,requireUserAuth],
        component: () => import('../views/GameView.vue')
      },
      {
        path: 'score',
        name: 'score',
        beforeEnter: [requireAuth, cleandata,requireUserAuth],
        component: () => import('../views/ScoreView.vue')
      },
      {
        path: 'cizar',
        name: 'cizar',
        beforeEnter: [requireAuth, cleandata,requireUserAuth],
        component: () => import('../views/CizarView.vue')
      },
      {
        path: 'vigenere',
        name: 'vigenere',
        beforeEnter: [requireAuth, cleandata,requireUserAuth],
        component: () => import('../views/VigenereView.vue')
      },
      {
        path: 'permutation',
        name: 'permutation',
        beforeEnter: [requireAuth, cleandata,requireUserAuth],
        component: () => import('../views/PermutationView.vue')
      },
      {
        path: 'feedback',
        name: 'feedback',
        beforeEnter: [requireAuth, cleandata,requireUserAuth],
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


  if (authRequired && !loggedIn) {
    next('/');
  } else {
    next();
  }
});

function checkAuth(to, from, next) {
  const loggedIn = JSON.parse(localStorage.getItem('user'));
  if (loggedIn) {
    if (loggedIn.roles[0] === 'ROLE_User')
      next(
        "/user"
      );
    if (loggedIn.roles[0] === 'ROLE_Admin')
      next(
        "/admin"
      );
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
function requireAdminAuth(to, from, next) {
  const loggedIn = JSON.parse(localStorage.getItem('user'));
  if (loggedIn.roles[0] != 'ROLE_Admin') {
    next({
      path: "/user",
    });
  } else {
    next();
  }
}
function requireUserAuth(to, from, next) {
  const loggedIn = JSON.parse(localStorage.getItem('user'));
  if (loggedIn.roles[0] != 'ROLE_User') {
    next({
      path: "/admin",
    });
  } else {
    next();
  }
}
function cleandata() {
  localStorage.removeItem("datas");
  localStorage.removeItem("no");
  localStorage.removeItem("timer");
  localStorage.removeItem("point");
}
export default router
