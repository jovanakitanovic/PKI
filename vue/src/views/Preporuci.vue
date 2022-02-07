<template>
  <div id="home">
    <div id="mainContainer" class="container-xxl">
      <div id="innerContainer" class="container-xxl">
        <div class="row">
          <div id="left" class="col-md-6" align="center">
            <Details></Details>
          </div>
          <div class="col-md-6" align="center">
            <div class="container">
              <div id="resommendationText">
                <h3>Odaberite korisnike i preporučite</h3>
              </div>
              <div id="recommendationWrapContainer">
                <div class="recommendationContainer">
                  <div v-for="u in allUsers" :key="u.user" @click="toggle(u)">
                    <div class="userToRecommend" v-if="!u.active">
                      {{ u.user }}
                    </div>
                    <div class="userToRecommendActive" v-if="u.active">
                      {{ u.user }}
                    </div>
                  </div>
                </div>
              </div>
              <div class="row justify-content-center" id="recommendButtons">
                <div class="col-md-4 col-lg-3">
                  <button class="btn btn-danger" id="btnDanRec">
                    <a @click="$router.go(-1)">odustani</a>
                  </button>
                </div>
                <div class="col-md-4 col-lg-3">
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
  </div>
</template>

<script>
import Details from "../components/Details.vue";
import Allusers from "../data/users";
import AllBook from "../data/books";
import AllRecs from "../data/preporuke";

export default {
  components: { Details },
  data() {
    return {
      active: false,
      allUsers: [],
      recommendTo: [],
      book: {},
      allRecommendations: [],
      loggedUser: "",
      allBooks:[],
    };
  },
  methods: {

    inGroup(u){
      for (let i=0;i<this.allRecommendations.length;i++)
      {
      if(this.allRecommendations[i].userTo==u.user && this.allRecommendations[i].userFrom == this.loggedUser && this.allRecommendations[i].book.id == this.book.id)
      return true
      }

      return false
    },
    toggle(u) {

      //alert(u.user)

     // alert(this.inGroup(u))

      if(!this.inGroup(u)){
        //alert(u.user)
      u.active = !u.active;

      if (u.active) {
        this.recommendTo.push({
          userFrom: this.loggedUser,
          book: this.book,
          userTo: u.user,
          allUsersBaza: [],
        });
      } else {
        this.recommendTo = this.recommendTo.filter(
          (rec) => rec.userTo != u.user
        );
      }
      }
    },
    potvrdi() {
      for (let i = 0; i < this.recommendTo.length; i++) {
        this.allRecommendations.push(this.recommendTo[i]);
      }

      localStorage.setItem("allRecs", JSON.stringify(this.allRecommendations));

      this.$router.go(-1);
    },
  },
  mounted() {
    this.loggedUser = localStorage.getItem("loggedUser");

    if (localStorage.getItem("allRecs") != null) {
      //alert("lala")
      this.allRecommendations = JSON.parse(localStorage.getItem("allRecs"));
    } else {
      this.allRecommendations = AllRecs;
    }

    if (localStorage.getItem("allUsers") != null)
      this.allUsersBaza = JSON.parse(localStorage.getItem("allUsers"));
    else this.allUsersBaza = Allusers;

    if (localStorage.getItem("allBooks") != null) {
      this.allBooks = JSON.parse(localStorage.getItem("allBooks"));
    } else this.allBooks = AllBook;

    let id = Number(this.$route.params.id);
    this.book = this.allBooks.find((b) => b.id == id);

    for (let i = 0; i < this.allUsersBaza.length; i++) {
      if (
        this.allUsersBaza[i].tip == "o" &&
        this.allUsersBaza[i].username != this.loggedUser
      )
        this.allUsers.push({
          user: this.allUsersBaza[i].username,
          active: false,
        });
    }

    for (let i = 0; i < this.allRecommendations.length; i++) {
      if (
        this.allRecommendations[i].userFrom == this.loggedUser &&
        this.allRecommendations[i].book.id == id
      ) {
        /*this.recommendTo.push({
          userFrom: this.loggedUser,
          book: this.book,
          userTo: this.allRecommendations[i].userTo,
        });*/

        let userUpdate = this.allUsers.find(
          (u) => u.user == this.allRecommendations[i].userTo
        );
        userUpdate.active = true;
      }
    }

    /*this.allRecommendations = this.allRecommendations.filter(
      (rec) => rec.userFrom != this.loggedUser || rec.book.id != id
    );*/
  },
};
</script>


<style>
#btnDanRec {
  margin-bottom: 2px;
}

#recommendButtons {
  margin-top: 20px;
  overflow-x: auto;
}

#recommendationWrapContainer {
  padding: 2px 0 2px;
  background-color: #B3B3B3;
  border-radius: 20px;
}

.recommendationContainer {
  max-height: 80vh;
  overflow-y: auto;
  padding: 10px 0 10px;
  overflow-x: auto;
}

#resommendationText {
  padding-top: 20%;
  text-align: left;
  overflow-wrap: break-word;
  padding-left: 20px;
}

.userToRecommend,
.userToRecommendActive {
  padding: 10px 0 10px;
  width: 90%;
  border-radius: 20px;
  margin-top: 5px;
}

.userToRecommend {
  background-color: white;
}

.userToRecommendActive {
  background-color: #c3a7db;
}

#leftContainer2 {
  width: 100%;
}

.col-12 {
  padding-bottom: 5px;
}

#about {
  margin-top: 20px;
  height: 50vh;
  overflow-y: auto;
}
.cardText {
  overflow-wrap: break-word;
}

.cardAboutBook {
  width: 80%;
  height: auto;
}

#nums {
  text-align: left;
  font-size: 20px;
}

#infoContainer {
  padding-top: 20px;

  overflow-x: auto;
}

.btn {
  color: white;
  border-radius: 15px;
}

@media (max-width: 200px) {
  .cardAboutBook {
    width: 100px;
    height: auto;
  }
  .userToRecommend,
  .userToRecommendActive {
    overflow-wrap: break-word;
  }
}

@media (max-width: 767px) {
  #nums {
    text-align: center;
  }
}
</style>


