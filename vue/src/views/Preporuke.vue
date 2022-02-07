<template>
  <div id="home">
    <div id="mainContainer" class="container-xxl">
      <div id="innerContainer" class="container-xxl">
        <div id="RedIntro"><h3>Preporučili su Vam</h3></div>

        <div id="allRecomendationsWrap">
          <div id="allRecomendationsWrapinner">
            <div class="row justify-content-center">
              <div
                class="col-md-5 my-auto"
                id="allRedCard"
                v-for="r in allRecommendations"
                :key="r.id"
              >
                <div class="allPrepCard">
                  <div class="row " >
                    <div class="col-lg-5 justify-content-center" align="center">
                      <div class="allCardPrep">
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
</template>


<script>
import AllRecs from "../data/preporuke";

export default {
  data() {
    return {
      allRecommendations: [],
      AllRecs: [],
      loggedUser: "",
    };
  },
  mounted() {
    this.loggedUser = localStorage.getItem("loggedUser");

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
#allRedCard{
    padding-top: 10px;
}
#allRecomendationsWrap {
  height: 100%;

}

#allRecomendationsWrapinner {
  height: 80vh;
  overflow-x: hidden;
  overflow-y: auto;
}

.allCardPrep {
  width: 60%;
  height: auto;
  margin-left: 30%;
}

.cardPrepText {
  padding-top: 10px;
}

.allPrepCard {
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

@media (max-width: 200px) {
  .allCardPrep {
    inline-size: min-content;
    width: 100px;
    height: auto;
  }
  .allPrepCard {
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
}

@media (max-width: 767px) {
  #commentIntroText {
    margin-top: 10%;
  }
}

@media (max-width: 991px) {
  .allCardPrep {
    inline-size: min-content;
    width: 60%;
    height: auto;
    margin-left: 0%;
  }
}
</style>