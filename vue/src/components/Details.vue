<template>
  <div id="leftContainer2" class="container">
    <div class="container-fluid">
      <div class="row" id="infoContainer">
        <div class="col-md-8" id="slika">
          <div class="card cardAboutBook">
            <img class="card-img-top" :src="'/img/' + book.image" />
            <div card="cardText">
              <h2>{{ book.autor }}</h2>
              <b
                ><h1>{{ book.name }}</h1></b
              >
            </div>
          </div>
        </div>
        <div class="col-md-4 my-auto" id="nums">
          broj strana
          <br />
          <b>{{ book.numOfPages }}</b>
          <br />
          godina izdanja
          <br />
          <b>{{ book.year }}</b>
          <br /><br />
          <router-link :to="/preporuci/ + book.id" v-if="show"
            ><button class="btn btn-my">preporuči</button></router-link
          >
          <div class="row" v-if="showany">
            <div class="col"><b>na promociji</b> <br /></div>
            <div class="col" align="center">
              <div class="form-check form-switch" >
                <input
                  class="form-check-input"
                  type="checkbox"
                  id="flexSwitchCheckDefault"
                  v-model="book.promo"
                  @click="promena(book.promo)"
                />
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col" id="about">
          {{ book.description }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import allBooks from "../data/books";
export default {
  name: "Details",
  data() {
    return {
      book: [],
      show: false,
      showany:false,
      allBooks: [],
      ID:-1
    };
  },
  mounted() {
    this.ID = Number(this.$route.params.id);
    let user=JSON.parse(localStorage.getItem("user"))

    if (this.$route.name == "OKnjizi") {
      
      this.show = true;
    }

    if(user.tip=="a"){
      this.showany=true;
    }

    if (localStorage.getItem("allBooks") != null)
      this.allBooks = JSON.parse(localStorage.getItem("allBooks"));
    else this.allBooks = allBooks;

    this.book = this.allBooks.find((b) => b.id == this.ID);
  },
  methods:{
    promena(status){
      let book=this.allBooks.find(b=>b.id==this.ID);
      book.promo=!book.promo;


      localStorage.setItem("allBooks",JSON.stringify(this.allBooks))
    }
  }
};
</script>