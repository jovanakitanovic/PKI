<template>
  <div id="home">
    <div id="mainContainer" class="container-xxl">
      <div id="innerContainer" class="container-xxl">
        <div class="row">
          <div id="left" class="col-md-6">
            <div class="Text"><h3>Moj nalog</h3></div>

            <div id="leftContainerMyAcc" class="container" align="center">
              <div id="MyDataContainerWrap">
                <div id="MyData">
                  <div class="row">
                    <div class="col-12">
                      <div class="zaglavlje">ime i prezime:</div>
                      <div><h3>{{imePrezime}}</h3></div>
                    </div>

                    <div class="col-12">
                      <div class="zaglavlje">broj telefona</div>
                      <div><h3>{{tel}}</h3></div>
                    </div>

                    <div class="col-12">
                      <div class="zaglavlje">adresa</div>
                      <div><h3>{{adr}}</h3></div>
                    </div>

                    <div class="col-12">
                      <div class="zaglavlje">koricničko ime</div>
                      <div><h3>{{korime}}</h3></div>
                    </div>
                    <div class="col-12">
                      <br />
                      <div
                        class="row justify-content-center"
                        id="recommendButtons"
                      >
                        <div class="col-md-4 col-lg-3">
                          <router-link to="/promenapodataka"><button class="btn btn-my" id="btnPaddUnderMy">
                            promeni podatke
                          </button></router-link>
                        </div>
                        <div class="col-md-4 col-lg-3">
                          <router-link to="/promenalozinke">  <button class="btn btn-my">promeni lozinku</button></router-link>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div id="right2" class="col-md-6">
            <div id="RedIntro"><h3>Preporučili su Vam</h3></div>
            <div id="rightContainerACC" class="container" align="center">
              <div class="row">
                <div class="col-12" v-for="r in allRecommendations" :key="r.id">
                  <div class="prepCard">
                    <div class="row">
                      <div class="col-lg-5">
                        <div class="cardPrep">
                          <router-link :to="'/oknjizi/' + r.book.id"
                            ><img class="card-img" :src="/img/ + r.book.image"
                          /></router-link>
                        </div>
                      </div>
                      <div class="col-lg-7 cardPrepText">
                        <h5>{{ r.book.name }}<br />{{ r.book.autor }}</h5>
                        <br />
                        preporučio vam je:
                        <br />
                        <h5>{{ r.userFrom }}</h5>
                      </div>
                    </div>
                  </div>
                </div>
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

export default {
  data() {
    return {
      allRecommendations: [],
      AllRecs: [],
      loggedUser: "",
      imePrezime: "",
      tel: "",
      adr: "",
      korime: "",
    };
  },
  mounted() {
    this.loggedUser = localStorage.getItem("loggedUser");

    let user=JSON.parse(localStorage.getItem("user"));
    this.imePrezime=user.name+" "+user.surname;
    this.tel=user.tel;
    this.adr=user.adress;
    this.korime=user.username;

    if (localStorage.getItem("allRecs") != null) {
      this.AllRecs = JSON.parse(localStorage.getItem("allRecs"));
    } else this.AllRecs = AllRecs;

    for (let i = 0; i < this.AllRecs.length; i++) {
      if (this.AllRecs[i].userTo == this.loggedUser)
        this.allRecommendations.push({
          id: i,
          userFrom: this.AllRecs[i].userFrom,
          book: this.AllRecs[i].book,
        });
    }
  },
};
</script>


<style>
#MyDataContainerWrap {
}

#btnPaddUnderMy {
  margin-bottom: 2px;
}
.btn-my {
  background-color: #8421db;
  color: white;
  border-radius: 15px;
}

#rightContainerACC {
  overflow-y: auto;

  height: 100vh;
  width: 85%;
}

#right2 {
  border-top-right-radius: 80px;
  /*height: 100vh;*/
}

.cardPrep {
  width: 60%;
  height: auto;
  margin-left: 30%;
}

.cardPrepText {
  padding-top: 10px;
}

.prepCard {
  background-color: #c7c5bf;
  border-radius: 20px;
  padding: 10px 0 10px;
}

.col-12 {
  padding-bottom: 5px;
}

#RedIntro {
  text-align: left;
  padding-top: 30px;
  padding-left: 10%;
}

#MyData {
  padding: 10% 0 10%;
  margin-top: 20%;
  border-radius: 20px;
  background-color: #B3B3B3;
  width: 90%;
}

#leftContainerMyAcc {
  width: 100%;
  height: 80vh;
}

.btn {
  color: white;
  border-radius: 15px;
}

@media (max-width: 200px) {
  .cardPrep {
    inline-size: min-content;
    width: 100px;
    height: auto;
  }
  .prepCard {
    overflow-x: auto;
    background-color: #c7c5bf;
    border-radius: 10px;
    padding-top: 20px;
  }
}
@media (max-width: 1400px) {
  #leftContainerMyAcc {
    overflow-y: auto;
  }
  #MyData {
    margin-top: 0%;
  }
}

@media (max-width: 767px) {
  #commentIntroText {
    margin-top: 10%;
  }
  #MyData {
    margin-top: 0%;
    overflow-x: auto;
  }
  #leftContainerMyAcc {
    overflow-y: auto;
    height: 50vh;
  }
}

@media (max-width: 991px) {
  .cardPrep {
    inline-size: min-content;
    width: 60%;
    height: auto;
    margin-left: 0%;
  }
}
</style>