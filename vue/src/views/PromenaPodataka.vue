<template>
  <div id="home">
    <div id="mainContainer" class="container-xxl">
      <div id="innerContainer" class="container-xxl">
        <div id="changeDataWrap">
          <div id="changeDataData">
            <div id="topText"><h3>Promena podataka</h3></div>

            <div class="row justify-content-center" align="center">
              <div class="col-8 justify-content-center">
                <input
                  type="text"
                  class="form-control"
                  id="formcontrolChange"
                  placeholder="ime"
                  v-model="ime"
                />
              </div>
              <div class="col-8">
                <input
                  type="text"
                  class="form-control"
                  id="formcontrolChange"
                  placeholder="prezime"
                  v-model="prezime"
                />
              </div>
              <div class="col-8">
                <input
                  type="text"
                  class="form-control"
                  id="formcontrolChange"
                  placeholder="telefon"
                  v-model="tel"
                />
              </div>
              <div class="col-8">
                <input
                  type="text"
                  class="form-control"
                  id="formcontrolChange"
                  placeholder="adresa"
                  v-model="adr"
                />
              </div>
              <div class="col-8">
                <input
                  type="text"
                  class="form-control"
                  id="formcontrolChange"
                  placeholder="korime"
                  v-model="korime"
                  disabled
                />
              </div>
            </div>
            <div class="row justify-content-center" id="recommendButtons">
              <div class="col-md-3 col-lg-1">
                <button class="btn btn-danger" id="btnDanRec">
                  <a @click="$router.go(-1)">odustani</a>
                </button>
              </div>
              <div class="col-md-3 col-lg-1">
                <button class="btn btn-success" @click="potvrdi()">
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
import AllRecs from "../data/preporuke";
import allUsers from "../data/users"

export default {
  data() {
    return {
      loggedUser: "",
      ime: "",
      prezime: "",
      tel: "",
      adr: "",
      korime: "",
      allUsers:[]
    };
  },
  mounted() {
    this.loggedUser = localStorage.getItem("loggedUser");

    let user = JSON.parse(localStorage.getItem("user"));
    this.ime = user.name;
    this.prezime = user.surname;
    this.tel = user.tel;
    this.adr = user.adress;
    this.korime = user.username;

    if (localStorage.getItem("allUsers") != null)
      this.allUsers = JSON.parse(localStorage.getItem("allUsers"));
    else this.allUsers = allUsers;

  },
  methods: {
    potvrdi() {
      if (
        this.ime == "" ||
        this.prezime == "" ||
        this.tel == "" ||
        this.adr == ""
      )
        alert("morate popuniti sva polja");
      else {
        let user=this.allUsers.find(u=>u.username==this.korime)

        user.name=this.ime;
        user.surname=this.prezime;
        user.tel=this.tel;
        user.adress=this.adr;

        localStorage.setItem("allUsers",JSON.stringify(this.allUsers))
        localStorage.setItem("user",JSON.stringify(user))

        this.$router.go(-1)
      }
    },
  },
};
</script>


<style>
.form-control {
  width: 90%;

  border-radius: 20px;
}
#topText{
  margin-bottom: 5%;
}

#btnDanRec {
  margin-bottom: 2px;
}
#formcontrolChange {
  width: 50%;
  margin-bottom: 10px;
}

#changeDataWrap {
  height: 80vh;
  position: relative;
  padding-top: 10%;
  align-self: center;
}

#changeDataData {
  height: 60vh;

  background-color: #B3B3B3;
  border-radius: 20px;
  padding: 5% 0 5%;
  overflow-x: hidden;
  overflow-y: auto;
  width: 100%;

  position: absolute;
  top: 50%;
  -ms-transform: translateY(-50%);
  transform: translateY(-50%);
}

@media (max-width: 200px) {
}
@media (max-width: 1400px) {
}

@media (max-width: 767px) {
}

@media (max-width: 991px) {
}
</style>