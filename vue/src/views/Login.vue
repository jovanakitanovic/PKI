<template>
  <div id="login">
    <div id="mainContainer" class="container-xxl">
      <div id="innerContainer" class="container-xxl">
        <div class="row">
          <div id="left" class="col-md-7">
            <div class="Text"><h3>Kod nas možete pronaći</h3></div>
            <div id="leftContainerLogin" class="container" align="center">
              <div class="row">
                <div
                  class="col-xl-3 col-md-4 col-sm-6"
                  v-for="b in books"
                  :key="b.id"
                >
                  <div class="card">
                    <img class="card-img-top" :src="'/img/' + b.image" />
                    <div>
                      {{ b.name }}
                      <h6>{{ b.autor }}</h6>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div id="right" class="col-md-5">
            <div>
              <a
                id="routerlink"
                href="#"
                @click="noviKorisnik()"
                v-if="logovanje"
                >napravite nalog
              </a>
              <a
                id="routerlink"
                href="#"
                @click="noviKorisnik()"
                v-if="!logovanje"
                >logovanje </a
              >
            </div>
            <div
              id="rightContainerLogin"
              class="container"
              align="center"
              v-if="logovanje"
            >
              <div id="parolaKorisnikLogin">
                <h1>
                  Ulogujte se i uživajte <br />u našem šarenom svetu knjige!
                </h1>
              </div>
              <div>
                <input
                  type="text"
                  class="form-control"
                  placeholder="korisničko ime"
                  v-model="korime"
                />
              </div>

              <div>
                <input
                  type="password"
                  class="form-control"
                  placeholder="lozinka"
                  v-model="lozinka"
                />
              </div>
              <div>
                <button class="btn btn-my" @click="login()">uloguj se</button>
              </div>
            </div>

            <div
              id="rightContainerLogin"
              class="container"
              align="center"
              v-if="!logovanje"
            >
              <h1>Drago nam je što želite da postanete deo našeg sveta!</h1>
              <div>
                <input
                  type="text"
                  class="form-control"
                  placeholder="korisničko ime"
                  v-model="korime"
                />
              </div>

              <div>
                <input
                  type="password"
                  class="form-control"
                  placeholder="lozinka"
                  v-model="lozinka"
                />
              </div>

              <div>
                <input
                  type="text"
                  class="form-control"
                  placeholder="ime"
                  v-model="ime"
                />
              </div>

              <div>
                <input
                  type="text"
                  class="form-control"
                  placeholder="prezime"
                  v-model="prezime"
                />
              </div>

              <div>
                <input
                  type="text"
                  class="form-control"
                  placeholder="adresa"
                  v-model="adresa"
                />
              </div>

              <div>
                <input
                  type="text"
                  class="form-control"
                  placeholder="telefon"
                  v-model="telefon"
                />
              </div>

              <div>
                <button class="btn btn-my" @click="napraviNalog()">
                  potvrdi
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import allBooks from "../data/books";
import allUsers from "../data/users";

export default {
  name: "Login",
  components: {},
  data() {
    return {
      korime: "",
      lozinka: "",
      ime: "",
      prezime: "",
      adresa: "",
      telefon: "",
      books: allBooks,
      allUsers: [],
      logovanje: true,
    };
  },
  methods: {
    login() {
      if (this.korime == "" || this.lozinka == "")
        alert("niste popunili sva polja!");
      else {
        let myUser = this.allUsers.find(
          (user) => user.username == this.korime && user.pass == this.lozinka
        );
        if (myUser != undefined) {
          localStorage.setItem("in", true);
          localStorage.setItem("inn", true);
          localStorage.setItem("loggedUser", this.korime);
          localStorage.setItem("user", JSON.stringify(myUser));
          if (myUser.tip == "o") this.$router.push("/home");
          if(myUser.tip=="a")this.$router.push("/homezaposleni")
        } else alert("korisnik sa unetim kredencijalima ne postoji u sisemu!");
        this.korime = "";
        this.lozinka = "";
      }
    },
    noviKorisnik() {
      this.logovanje = !this.logovanje;
      this.korime = "";
      this.lozinka = "";
    },
    napraviNalog() {
      if (
        this.korime == "" ||
        this.lozinka == "" ||
        this.ime == "" ||
        this.prezime == "" ||
        this.adresa == "" ||
        this.telefon == ""
      )
        alert("morate popuniti sva polja!");
      else {
        if (this.allUsers.find((k) => k.username == this.korime) == undefined) {
          this.allUsers.push({
            username: this.korime,
            name: this.ime,
            surname: this.prezime,
            pass: this.lozinka,
            tel: this.telefon,
            adress: this.adresa,
            tip:"o"
          });
          localStorage.setItem("allUsers", JSON.stringify(this.allUsers));
          this.logovanje=true;
        } else {
          this.korime = "";
          alert("korisničko ime već postoji u sistemu");
        }
      }
    },
  },
  mounted() {
    if (localStorage.getItem("allUsers") != null)
      this.allUsers = JSON.parse(localStorage.getItem("allUsers"));
    else this.allUsers = allUsers;
  },
};
</script>


<style>
#parolaKorisnikLogin {
  margin-bottom: 20%;
}
input {
  margin-bottom: 10px;
}

.form-control {
  width: 90%;

  border-radius: 20px;
}

.btn-my {
  background-color: #8421db;
  color: white;
  border-radius: 15px;
}
.naziv {
  font-size: 24px;
}
.autor {
  font-size: 2vw;
}

.card {
  margin-bottom: 30px;
  width: 100%;
}

#mainContainer {
  transform: scale(1, 1);
  height: 100%;
}
#innerContainer {
  height: 100%;
}

#left {
  color: black;
  /* height:100vh;*/
  margin-bottom: 20px;
}

#right {
  background-color: #B3B3B3;
  border-top-right-radius: 80px;
  /*height: 100vh;*/
}

#rightContainerLogin {
  margin-top: 20%;
}

#leftContainerLogin {
  overflow-y: auto;
  height: 60vh;
}

.Text {
  text-align: left;
  padding-top: 30px;
  padding-left: 30px;
  padding-bottom: 30px;
  color: black;
}

h3 {
  font-weight: lighter;
  font-stretch: expanded;
}

#innerContainer {
  background-color: white;
  border-top-left-radius: 80px;
  border-top-right-radius: 80px;
}

@media (max-width: 767px) {
  .Text {
    padding-bottom: 20px;
  }
  #innerContainer {
    border-top-left-radius: 70px;
    border-top-right-radius: 70px;
  }
  #right {
    border-top-left-radius: 70px;
    border-top-right-radius: 70px;
  }

  .card {
    width: 80%;
  }
}

@media (max-width: 576px) {
  #mainContainer {
    padding-left: 5%;
    padding-right: 5%;
  }
  .Text {
    padding-left: 0px;
    padding-bottom: 20px;
    padding-top: 20px;
  }
  #innerContainer {
    border-top-left-radius: 30px;
    border-top-right-radius: 30px;
  }
  #right {
    border-top-left-radius: 30px;
    border-top-right-radius: 30px;
  }

  .card {
    width: 60%;
    align-content: center;
  }
}

@media (max-width: 200px) {
  #innerContainer {
    width: 100vh;
  }
  .card {
    width: 60px;
  }
}
</style>



