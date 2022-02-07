import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [{
        path: '/',
        name: 'Login',
        component: () =>
            import ('../views/Login.vue')
    }, {
        path: '/home',
        name: 'Home',
        component: Home
    },
    {
        path: '/homezaposleni',
        name: 'HomeZaposleni',
        component: () =>
            import ("../views/HomeZaposleni.vue")
    },
    {
        path: '/about',
        name: 'About',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () =>
            import ( /* webpackChunkName: "about" */ '../views/About.vue')
    }, {
        path: '/oknjizi/:id',
        name: 'OKnjizi',
        component: () =>
            import ( /* webpackChunkName: "about" */ '../views/OKnjizi.vue')
    },
    {
        path: '/oknjizizaposleni/:id',
        name: 'OKnjiziZaposleni',
        component: () =>
            import ( /* webpackChunkName: "about" */ '../views/OKnjiziZaposleni.vue')
    },
    {
        path: '/komentar/:id',
        name: 'Komentar',
        component: () =>
            import ('../views/Komentar.vue')
    },
    {
        path: '/preporuci/:id',
        name: 'Preporuci',
        component: () =>
            import ('../views/Preporuci.vue')
    }, {
        path: '/mojnalog',
        name: 'MojNalog',
        component: () =>
            import ('../views/MojNalog.vue')
    },
    {
        path: '/mojnalogzaposleni',
        name: 'MojNalogZaposleni',
        component: () =>
            import ('../views/MojNalogZaposleni.vue')
    }, {
        path: '/preporuke',
        name: 'Preporuke',
        component: () =>
            import ('../views/Preporuke.vue')
    }, {
        path: '/preporuke',
        name: 'Preporuke',
        component: () =>
            import ('../views/Preporuke.vue')
    }, {
        path: '/promenapodataka',
        name: 'PromenaPodataka',
        component: () =>
            import ('../views/PromenaPodataka.vue')
    }, {
        path: '/promenalozinke',
        name: 'PromenaLozinke',
        component: () =>
            import ('../views/PromenaLozinke.vue')
    }, {
        path: '/novaknjiga',
        name: 'NovaKnjiga',
        component: () =>
            import ('../views/NovaKnjiga.vue')
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router