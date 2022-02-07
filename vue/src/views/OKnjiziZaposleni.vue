<template>
  <div id="home">
    <div id="mainContainer" class="container-xxl">
      <div id="innerContainer" class="container-xxl">
        <div class="row">
          <div id="left" class="col-md-6" align="center">
            <Details></Details>
          </div>
          <div class="col-md-6" align="center">
            <div id="rightContainer2" class="container">
              <div id="misle">
                <h3>Šta naši čitaoci misle o knjizi</h3>
              </div>
              <div id="misle" v-if="comments.length == 0">
                <br />
                <br />
                <h5>Trenutno nema komentara</h5>
              </div>
              <div id="commentsZaposleni" class="row">
                <div class="col-12" v-for="c in comments" :key="c.user">
                  <div class="commentCard">
                    {{ c.user }}
                    <div class="commentBody">
                      <div class="row">
                        <div
                          class="col-1"
                          v-for="cs in c.stars"
                          :key="cs.id"
                          v-html="cs.star"
                        ></div>
                      </div>
                      {{ c.comment }}
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
import allBooks from "../data/books";
import Details from "../components/Details.vue";
import { bus } from "../main";

export default {
  data() {
    return {
      active: false,
      book: [],
      comments: [],
      allBooks: [],
      noComment: false,
      stars0: [
        { id: 1, star: '<i class="bi bi-star"></i>' },
        { id: 2, star: '<i class="bi bi-star"></i>' },
        { id: 3, star: '<i class="bi bi-star"></i>' },
        { id: 4, star: '<i class="bi bi-star"></i>' },
        { id: 5, star: '<i class="bi bi-star"></i>' },
      ],
      stars1: [
        { id: 1, star: '<i class="bi bi-star-fill"></i>' },
        { id: 2, star: '<i class="bi bi-star"></i>' },
        { id: 3, star: '<i class="bi bi-star"></i>' },
        { id: 4, star: '<i class="bi bi-star"></i>' },
        { id: 5, star: '<i class="bi bi-star"></i>' },
      ],
      stars2: [
        { id: 1, star: '<i class="bi bi-star-fill"></i>' },
        { id: 2, star: '<i class="bi bi-star-fill"></i>' },
        { id: 3, star: '<i class="bi bi-star"></i>' },
        { id: 4, star: '<i class="bi bi-star"></i>' },
        { id: 5, star: '<i class="bi bi-star"></i>' },
      ],
      stars3: [
        { id: 1, star: '<i class="bi bi-star-fill"></i>' },
        { id: 2, star: '<i class="bi bi-star-fill"></i>' },
        { id: 3, star: '<i class="bi bi-star-fill"></i>' },
        { id: 4, star: '<i class="bi bi-star"></i>' },
        { id: 5, star: '<i class="bi bi-star"></i>' },
      ],
      stars4: [
        { id: 1, star: '<i class="bi bi-star-fill"></i>' },
        { id: 2, star: '<i class="bi bi-star-fill"></i>' },
        { id: 3, star: '<i class="bi bi-star-fill"></i>' },
        { id: 4, star: '<i class="bi bi-star-fill"></i>' },
        { id: 5, star: '<i class="bi bi-star"></i>' },
      ],
      stars5: [
        { id: 1, star: '<i class="bi bi-star-fill"></i>' },
        { id: 2, star: '<i class="bi bi-star-fill"></i>' },
        { id: 3, star: '<i class="bi bi-star-fill"></i>' },
        { id: 4, star: '<i class="bi bi-star-fill"></i>' },
        { id: 5, star: '<i class="bi bi-star-fill"></i>' },
      ],
    };
  },
  components: {
    Details,
  },
  mounted() {
    bus.$emit("mojNalog", false);

    let id = Number(this.$route.params.id);

    if (localStorage.getItem("allBooks") != null) {
      this.allBooks = JSON.parse(localStorage.getItem("allBooks"));
    } else this.allBooks = allBooks;

    this.book = this.allBooks.find((b) => b.id == id);

    if (
      this.book.comments.find(
        (u) => u.user == localStorage.getItem("loggedUser")
      )
    )
      this.noComment = true;

    for (let i = 0; i < this.book.comments.length; i++) {
      let com = this.book.comments[i];

      if (com.stars == 0) {
        this.comments.push({
          user: com.user,
          stars: this.stars0,
          comment: com.comment,
        });
        continue;
      }
      if (com.stars == 1) {
        this.comments.push({
          user: com.user,
          stars: this.stars1,
          comment: com.comment,
        });
        continue;
      }
      if (com.stars == 2) {
        this.comments.push({
          user: com.user,
          stars: this.stars2,
          comment: com.comment,
        });
        continue;
      }
      if (com.stars == 3) {
        this.comments.push({
          user: com.user,
          stars: this.stars3,
          comment: com.comment,
        });
        continue;
      }
      if (com.stars == 4) {
        this.comments.push({
          user: com.user,
          stars: this.stars4,
          comment: com.comment,
        });
        continue;
      }
      if (com.stars == 5) {
        this.comments.push({
          user: com.user,
          stars: this.stars5,
          comment: com.comment,
        });
        continue;
      }
    }
  },
  methods: {
    toggle() {
      this.active = !this.active;
    },
  },
};
</script>

<style>
#rightContainer2 {
  display: flex;
  flex-flow: column;
  height: 100%;
}

.col-1 {
  width: 10px;
}

#leaveComment {
  margin-top: 5%;

  padding: 10% 0 10%;
  flex-grow: 1;
  width: 100%;
  border-top-right-radius: 30px;
  border-top-left-radius: 30px;
  background-color: #B3B3B3;
}
.col-1 {
  width: 10px;
}

#leaveCommentQuestion {
  padding-left: 20px;
  text-align: center;
}

.bi-star,
.bi-star-fill {
  color: orange;
}

#leftContainer2 {
  width: 100%;
}
#misle {
  padding-top: 20px;
  text-align: left;
  overflow-wrap: normal;
  padding-left: 20px;
}
.commentCard {
  overflow-wrap: break-word;
  background-color: #c7c5bf;
  border-radius: 20px;
  padding-bottom: 10px;
}

.col-12 {
  padding-bottom: 5px;
}

.commentBody {
  width: 95%;
  background-color: white;
  overflow-wrap: break-word;
  border-radius: 20px;
  text-align: left;
  padding-left: 15px;
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

#commentsZaposleni {
  padding-top: 20px;
  width: 100%;
  max-height: 100vh;
  overflow-y: auto;
}

.btn-my {
  background-color: #8421db;
  color: white;
  border-radius: 15px;
}

@media (max-width: 200px) {
  .commentCard {
    inline-size: 150px;
  }
  .cardAboutBook {
    width: 100px;
    height: auto;
  }
  #leaveComment {
    overflow-x: auto;
  }
}

@media (max-width: 767px) {
  #nums {
    text-align: center;
  }
}
</style>

 
