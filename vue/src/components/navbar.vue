<template>
  <div id="nav">
    <div class="container" id="navContainer">
      <div class="row">
        <div class="col-md-8 align-self-end" align="left">
          <div class="card" id="cardNav">
            <router-link to="/home"
              ><img src="../assets/logo.png"
            /></router-link>
          </div>
        </div>
        <div class="col-md-4 align-self-end" align="center">
          <div class="meni">
            <div id="menuButton">
              <div class="row">
                <div class="col-6 align-self-end">
                  <i class="bi bi-list" v-if="!active" @click="toggle"></i>
                  <i class="bi bi-x-lg" v-if="active" @click="toggle"></i>
                </div>
                <div class="col-6 align-self-end" @click="toggle">
                  <h3>MENI</h3>
                </div>
              </div>
            </div>
            <div class="list" v-if="active">
              <div class="row">
                <router-link id="routerlink" to="/mojnalog"
                  ><div class="col-12 border-bottom border-dark" id="mojNalog">
                    MOJ NALOG
                  </div></router-link
                >
                <router-link id="routerlink" to="/preporuke">
                  <div class="col-12 border-bottom border-dark" id="preporuke">
                    PREPORUKE
                  </div></router-link
                >
                <a id="routerlink" href="/"
                  ><div class="col-12" id="odjavljivanje" @click="logout()">
                    ODJAVLJIVANJE
                  </div></a
                >
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>



<style>
#routerlink {
  color: black;
  text-decoration: none;
}

#cardNav {
  background-color: #363940;
  border: none;
}

#preporuke {
  margin-bottom: 10px;
  margin-top: 10px;
}

#mojNalog {
  margin-top: 10px;
}
#odjavljivanje {
  color: tomato;
  margin-bottom: 10px;
}

#menuButton {
  inline-size: min-content;
  color: white;
  font-size: xx-large;
}

.list {
  background-color: white;
  border-radius: 20px;
  padding-left: 10px;
  padding-right: 10px;
  inline-size: 250px;
}
.meni {
  position: relative;
  z-index: 10;
  color: black;
}

#nav {
  background-color: #363940;
  color: white;
}

img {
  width: 80%;
  height: auto;
}
@media (max-width: 768px) {
  img {
    width: 100%;
    height: auto;
  }
  .col-md-4 {
    align-items: center;
  }
}
</style>

<script>
export default {
  name: "Navigacija",
};
</script>
  
 
<script>
export default {
  data() {
    return {
      active: false,
    };
  },
  methods: {
    toggle() {
      this.active = !this.active;
    },
    logout() {
      localStorage.removeItem("loggedUser");
      localStorage.removeItem("user");
      localStorage.removeItem("in");
    },
  },
};
</script>