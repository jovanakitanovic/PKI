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
              <div id="commentIntroText">
                <h3>Ostavite Vaš komentar</h3>
              </div>
              <div id="commentWrapContainer">
                <div class="commentText">
                  <div align="left">moj komentar</div>
                  <textarea cols="50" rows="10" class="form-control" v-model="komentar">
                  </textarea>
                </div>
                <div class="commentStars">
                  <div align="left">moja ocena</div>
                  <div class="row">
                    <div
                      class="col-1"
                      v-for="x in stars"
                      :key="x.id"
                      v-html="x.star"
                      @click="toggle(x.id)"
                      @mouseenter="toggleHoverIn(x.id)"
                      @mouseleave="toggleHoverOut(x.id)"
                    ></div>
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
                  <button class="btn btn-success" @click="potvrdi()">potvrdi</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>




<style>
#btnDanRec {
  margin-bottom: 2px;
}

.col-1 {
  width: 10px;
}

#recommendButtons {
  margin-top: 20px;
  padding-top: 2px;

  overflow-x: auto;
}

#commentIntroText {
  margin-top: 40%;
}

.form-control {
  border-radius: 20px;
}

.commentText,
.commentStars {
  width: 80%;
  padding-top: 10px;
}

.bi-star,
.bi-star-fill {
  color: orange;
}

#leftContainer2 {
  width: 100%;
}

#commentWrapContainer {
  padding: 2px 0 2px;
  background-color: #B3B3B3;
  border-radius: 20px;
  overflow-x: auto;
  margin-bottom: 10px;
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
}

@media (max-width: 767px) {
  #nums {
    text-align: center;
  }
  #commentIntroText {
    margin-top: 10%;
  }
}
</style>


<script>
import Details from "../components/Details.vue";
import allBooks from "../data/books"

export default {
  components: { Details },
  data() {
    return {
      id:-1,
      allBooks:[],
      numStars:0,
      komentar:"",
      id:-1,
      stars: [
        { id: 0, star: '<i class="bi bi-star"></i>' },
        { id: 1, star: '<i class="bi bi-star"></i>' },
        { id: 2, star: '<i class="bi bi-star"></i>' },
        { id: 3, star: '<i class="bi bi-star"></i>' },
        { id: 4, star: '<i class="bi bi-star"></i>' },
      ],
      fullstar: '<i class="bi bi-star-fill"></i>',
      emptystar: '<i class="bi bi-star"></i>',
      oldVal: [
        { id: 0, star: '<i class="bi bi-star"></i>' },
        { id: 1, star: '<i class="bi bi-star"></i>' },
        { id: 2, star: '<i class="bi bi-star"></i>' },
        { id: 3, star: '<i class="bi bi-star"></i>' },
        { id: 4, star: '<i class="bi bi-star"></i>' },
      ],
      clicked: -1,

      /*items: [true, true, true, true, true],
       one: false,
      two: false,
      three: false,
      four: false,
      five: false,*/
    };
  },
  methods: {
    toggle: function (i) {
      this.clicked = i;
      this.numStars=(i+1)
      this.toggleHoverIn(i);
    },
    toggleHoverIn: function (i) {
      //alert(this.clicked);

      if (i == 0) {
        this.stars[0].star = this.fullstar;
        this.stars[1].star = this.emptystar;
        this.stars[2].star = this.emptystar;
        this.stars[3].star = this.emptystar;
        this.stars[4].star = this.emptystar;
      }
      if (i == 1) {
        this.stars[0].star = this.fullstar;
        this.stars[1].star = this.fullstar;
        this.stars[2].star = this.emptystar;
        this.stars[3].star = this.emptystar;
        this.stars[4].star = this.emptystar;
      }
      if (i == 2) {
        this.stars[0].star = this.fullstar;
        this.stars[1].star = this.fullstar;
        this.stars[2].star = this.fullstar;
        this.stars[3].star = this.emptystar;
        this.stars[4].star = this.emptystar;
      }
      if (i == 3) {
        this.stars[0].star = this.fullstar;
        this.stars[1].star = this.fullstar;
        this.stars[2].star = this.fullstar;
        this.stars[3].star = this.fullstar;
        this.stars[4].star = this.emptystar;
      }
      if (i == 4) {
        this.stars[0].star = this.fullstar;
        this.stars[1].star = this.fullstar;
        this.stars[2].star = this.fullstar;
        this.stars[3].star = this.fullstar;
        this.stars[4].star = this.fullstar;
      }
    },
    toggleHoverOut: function (i) {
      if (this.clicked > -1) this.toggleHoverIn(this.clicked);
      else {
        this.stars[0].star = this.emptystar;
        this.stars[1].star = this.emptystar;
        this.stars[2].star = this.emptystar;
        this.stars[3].star = this.emptystar;
        this.stars[4].star = this.emptystar;
      }
    },
    potvrdi(){
      let book=this.allBooks.find(b=>b.id==this.id)

      if(this.numStars==0 && this.komentar=="")
      {
        alert("da biste potvrdili unos morate makar da dodate komentar ili ocenu")
        return;
      }
      book.comments.unshift({user:localStorage.getItem("loggedUser"),stars:this.numStars,comment:this.komentar})
      localStorage.setItem("allBooks",JSON.stringify(this.allBooks))
      this.$router.go(-1)
    }
  },
  mounted() {
    this.id = Number(this.$route.params.id);

    if (localStorage.getItem("allBooks") != null) {
      this.allBooks = JSON.parse(localStorage.getItem("allBooks"));
    } 
    else this.allBooks = allBooks;
  },
};
</script>
