<template>
  <div id="nav">
    <div class="container" id="navContainer">
      <div class="row">
        <div class="col-md-8 align-self-end" align="left">
          <div class="card" id="cardNav" @click="homeStrZaposleni(false)">
            <img src="../assets/logo.png" />
          </div>
        </div>
        <div class="col-md-4 align-self-center" align="center">
          <div class="row" v-if="pretragaActive">
            <div class="col-12" id="searchField">
              <div class="input-group mb-3">
                <input
                  type="text"
                  class="form-control"
                  placeholder="naziv knjige ili ime autora"
                  v-model="pretragaKriterijum"
                  id="formControlZaposleni"
                />
                <div class="input-group-append">
                  <span class="input-group-text" id="basic-addon2"
                    ><button class="btn" @click="pretraga()">
                      <i class="bi bi-search"></i></button
                  ></span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="container" v-if="!promenaAkcije && !nova">
      <div class="row justify-content-center">
        <div class="col-sm-4">
          <div class="col align-self-end">
            <i class="bi bi-arrow-right-circle" @click="preuredjivanje()">
              PREUREĐIVANJE PROMOCIJA</i
            >
          </div>
        </div>
        <div class="col-sm-4">
          <div class="col align-self-end">
            <i class="bi bi-arrow-right-circle" @click="novaKnjiga()">
              NOVA KNJIGA</i
            >
          </div>
        </div>
        <div class="col-sm-4">
          <div class="meniZaposleni">
            <div id="menuButtonZaposleni">
              <i class="bi bi-list" v-if="!active" @click="toggle"> MENI</i>
              <i class="bi bi-x-lg" v-if="active" @click="toggle"> MENI</i>
            </div>
          </div>
          <div class="listZaposleni" v-if="active">
            <div class="row">
              <router-link id="routerlink" to="/mojnalogzaposleni"
                ><div
                  class="col-12 border-bottom border-dark"
                  id="mojNalogZaposleni"
                >
                  MOJ NALOG
                </div></router-link
              >
              <a id="routerlink" href="/"
                ><div
                  class="col-12"
                  id="odjavljivanjeZaposleni"
                  @click="logout()"
                >
                  ODJAVLJIVANJE
                </div></a
              >
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="container" v-if="promenaAkcije">
      <div class="row justify-content-end">
        <div class="col-lg-3">
          <div class="col align-self-end" align="center">
            <button class="btn btn-danger" id="btnDanRec" @click="odustani()">
              odustani
            </button>
            <button class="btn btn-success" id="btnDanRec" @click="potvrdi()">
              potvrdi
            </button>
          </div>
        </div>
        <div class="col-lg-1"></div>
      </div>
    </div>
  </div>
</template>




<style>
.form-control {
  width: 90%;

  border-radius: 20px;
}
#btnDanRec {
  margin-bottom: 2px;
  margin-right: 4px;
}
.btn {
  color: white;
  border-radius: 15px;
}

#searchField {
  width: 90%;
}

#meniBar {
  width: 100%;
  column-gap: 0px;
}

#routerlink {
  color: black;
  text-decoration: none;
}

#cardNav {
  background-color: #363940;
  border: none;
}

#mojNalogZaposleni {
  margin-bottom: 10px;
  margin-top: 10px;
}
#odjavljivanjeZaposleni {
  color: tomato;
  margin-bottom: 10px;
}

#menuButtonZaposleni {
  color: white;
}

.listZaposleni {
  background-color: white;
  border-radius: 20px;
  padding-left: 10px;
  padding-right: 10px;
  inline-size: 250px;
  margin-left: 20%;
}
.meniZaposlni {
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
import Details from "./Details.vue";
export default {
  components: { Details },
  name: "NavigacijaZaposleni",
};
</script>
  
 
<script>
import { bus } from "../main";

export default {
  data() {
    return {
      active: false,
      pretragaKriterijum: "",
      promenaAkcije: false,
      pretragaActive: true,
      nova: false,
    };
  },

  created() {
    bus.$on("mojNalog", (data) => {
      this.pretragaActive = data;
    });
    bus.$on("promenaDone", () => {
      this.promenaAkcije = false;
    });
    bus.$on("nova", (data) => {
      this.nova = data;
    });
    bus.$on("homeZaposleni",()=>{
      this.homeStrZaposleni(false)
      
    })
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
    pretraga() {
      bus.$emit("fireMyMethod", this.pretragaKriterijum);
    },
    preuredjivanje() {
      this.promenaAkcije = true;
      localStorage.setItem("uredi", true);
      bus.$emit("promenaPromocije",this.promenaAkcije);
      this.$router.push("/homezaposleni");
    },
    odustani() {
      localStorage.removeItem("uredi", true);
      this.promenaAkcije = false;
      bus.$emit("cancelPromene");
      bus.$emit("promenaPromocije",this.promenaAkcije);
      this.pretragaKriterijum = "";
    },
    potvrdi() {
      bus.$emit("potvrdiPromene");
      localStorage.removeItem("uredi", true);
    },
    novaKnjiga() {
      this.nova = !this.nova;
      this.$router.push("/novaknjiga");
    },
    homeStrZaposleni(statusAkcije) {
      this.nova = false;
      this.promenaAkcije=statusAkcije
      bus.$emit("promenaPromocije",this.promenaAkcije);
      this.$router.push("/homezaposleni");
    },
  },
};
</script>