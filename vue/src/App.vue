<template>
  <div id="app">
    <Navigacija v-if="loggedinUser"></Navigacija>
    <NavigacijaLogin  v-if="!loggedin"></NavigacijaLogin>
    <NavigacijaZaposleni v-if="loggedinAdmin"></NavigacijaZaposleni>
    <router-view />
  </div>
</template>



<script>
import Navigacija from "./components/navbar.vue";
import NavigacijaLogin from "./components/navbarLogin.vue";
import NavigacijaZaposleni from "./components/navbarZaposleni.vue"

export default {
  name: "App",
  components: {
    Navigacija,
    NavigacijaLogin,
    NavigacijaZaposleni
  },
  data() {
    return {
      loggedinUser: false,
      loggedinAdmin:false,
      loggedin:false
    };
  },
  mounted() {
    if (localStorage.getItem("in")) {
      this.loggedin = true;
      let user=JSON.parse(localStorage.getItem("user"))
     if(user.tip=="o")
        this.loggedinUser=true;
      else
        this.loggedinAdmin=true;  
    }
    else this.loggedin = false;
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>


