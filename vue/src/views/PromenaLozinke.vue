<template>
  <div id="home">
    <div id="mainContainer" class="container-xxl">
      <div id="innerContainer" class="container-xxl">
        <div id="changeDataWrap">
          <div id="changeData">
            <div id="topText"><h3>Promena podataka</h3></div>

            <div class="row justify-content-center" align="center">
              <div class="col-8 justify-content-center">
                <input
                  type="password"
                  class="form-control"
                  id="formcontrolChange"
                  placeholder="stara lozinka"
                  v-model="stara"
                />
              </div>
              <div class="col-8">
                <input
                  type="password"
                  class="form-control"
                  id="formcontrolChange"
                  placeholder="nova lozinka"
                  v-model="nova"
                />
              </div>
              <div class="col-8">
                <input
                  type="password"
                  class="form-control"
                  id="formcontrolChange"
                  placeholder="ponovljena nova lozina"
                  v-model="nova2"
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
import allUsers from "../data/users";

export default {
  data() {
    return {
      loggedUser: "",
      stara: "",
      nova: "",
      nova2: "",
      allUsers: [],
    };
  },
  mounted() {
    this.loggedUser = localStorage.getItem("loggedUser");

    if (localStorage.getItem("allUsers") != null)
      this.allUsers = JSON.parse(localStorage.getItem("allUsers"));
    else this.allUsers = allUsers;
  },
  methods: {
    potvrdi() {
      if (this.stara == "" || this.nova == "" || this.nova2 == "")
        alert("morate popuniti sva polja");
      else {
        let user = this.allUsers.find((u) => u.username == this.loggedUser);

        if (user.pass != this.stara) {
          alert("stara lozinka i trenutna lzinka se ne poklapaju!");
          this.stara = "";
          this.nova = "";
          this.nova2 = "";
        } else {
          if (this.nova2 != this.nova) {
            alert("nova lozinka i ponovljena nova lozinka se ne poklapaju!");
            this.nova = "";
            this.nova2 = "";
          } else {
            user.pass = this.nova;
            localStorage.setItem("allUsers", JSON.stringify(this.allUsers));
            localStorage.setItem("user", JSON.stringify(user));

            this.$router.go(-1);
          }
        }
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
#topText {
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

#changeData {
  height: 60vh;

  background-color: #B3B3B3;
  border-radius: 20px;
  padding: 10% 0 10%;
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