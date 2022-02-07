<template>
  <div id="home">
    <div id="mainContainer" class="container-xxl">
      <div id="innerContainer" class="container-xxl">
        <div class="row">
          <div id="left" class="col-md-7">
            <div class="Text"><h3>Kod nas možete pronaći</h3></div>
            <div id="leftContainer" class="container" align="center">
              <div class="row">
                <div
                  class="col-xl-3 col-md-4 col-sm-6"
                  v-for="b in noPromo"
                  :key="b.id"

                  >
                  <router-link id="routerlink" :to="/oknjizi/+b.id">
                    <div class="card">
                      <img class="card-img-top" :src="'/img/' + b.image" />
                      <div>
                        {{ b.name }}
                        <h6>{{ b.autor }}</h6>
                      </div>
                    </div></router-link
                  >
                </div>
              </div>
            </div>
          </div>
          <div id="right" class="col-md-5">
            <div class="Text"><h3>Knjige na promociji</h3></div>
            <div id="rightContainer" class="container" align="center">
              <div class="row">
                <div class="col-xl-4 col-sm-6" v-for="b in promo" :key="b.id">
                  <router-link id="routerlink" :to="/oknjizi/+b.id"><div class="card">
                    <img class="card-img-top"  :src="'/img/' + b.image" />
                    <div>
                      {{b.name}}
                      <h6>{{b.autor}}</h6>
                    </div>
                  </div>
                  </router-link>
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
// @ is an alias to /src
import allBooks from "../data/books";

export default {
  name: "Home",
  components: {},
  data() {
    return {
      books: allBooks,
      noPromo:[],
      promo:[]
    };
  },
  mounted(){
    for(let i=0;i<this.books.length;i++){
      if(this.books[i].promo==0)
        this.noPromo.push(this.books[i])
      else
        this.promo.push(this.books[i])  
    }
  }
};
</script>


<style>


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

#rightContainer {
  overflow-y: auto;

  height: 80vh;
}

#leftContainer {
  overflow-y: auto;
  height: 80vh;
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



