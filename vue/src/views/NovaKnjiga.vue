<template>
  <div id="home">
    <div id="mainContainer" class="container-xxl">
      <div id="innerContainer" class="container-xxl">
        <div id="changeDataWrapNewBook">
          <div id="changeDataNewBook">
            <div id="topTextBook"><h3>Nova knjiga</h3></div>

            <div class="row justify-content-center" align="center">
              <div class="col-8 justify-content-center">
                <input
                  type="text"
                  class="form-control"
                  id="formcontrolChangeNova"
                  placeholder="naziv knjige"
                  v-model="name"
                />
              </div>
              <div class="col-8">
                <div class="row justify-content-center">
                  <div class="col-12">
                    <input
                      type="text"
                      class="form-control"
                      id="formcontrolChangeNova"
                      placeholder="autor"
                      v-model="autor"
                    />
                  </div>
                </div>
              </div>
              <div class="col-8">
                <div class="row justify-content-center">
                  <div class="col-4">
                    <input
                      type="text"
                      class="form-control"
                      id="formcontrolChangeManje"
                      placeholder="godina izdanja"
                      v-model="year"
                    />
                  </div>
                  <div class="col-4 justify-content-center">
                    <input
                      type="text"
                      class="form-control"
                      id="formcontrolChangeManje"
                      placeholder="broj strana"
                      v-model="numOfPages"
                    />
                  </div>
                </div>
              </div>
              <div class="col-8">
                <textarea
                  cols="50"
                  rows="10"
                  class="form-control"
                  v-model="desctiption"
                  placeholder="opis"
                  id="formcontrolChangeNova"
                ></textarea>
              </div>
              <div class="col-8">
                <div class="input-group mb-3" id="formcontrolChangeNova">
                  <div class="input-group-append">
                    <span class="input-group-text"
                      ><button class="btn" id="buttonDoc" @click="openDoc()">
                        odaberite sliku
                      </button></span
                    >
                  </div>
                  <input type="text" class="form-control" v-model="image" />
                </div>
              </div>
            </div>
            <div class="row justify-content-center" id="recommendButtons">
              <div class="col-md-3 col-lg-1">
                <button class="btn btn-danger buttons" id="btnDanRec">
                  <a @click="odustani()">odustani</a>
                </button>
              </div>
              <div class="col-md-3 col-lg-1">
                <button class="btn btn-success buttons" @click="dodajKnjigu()">
                  potvrdi
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <modal name="newBook" :adaptive="true">
      <div id="topBorder">
        <i class="bi bi-x" @click="closeModal()"></i>
      </div>

      <div class="row justify-content-center" id="middleBorder">
        <div class="col-sm-2" align="center">
          <div class="card cardPopout" id="1" @click="odabirSlike(1)">
            <img class="card-img-top" src="../assets/kalendar maja.jpg" />
            <div>kalendar maja.jpg</div>
          </div>
        </div>
        <div class="col-sm-2" align="center">
          <div class="card cardPopout" id="2" @click="odabirSlike(2)">
            <img class="card-img-top" src="../assets/kuca.jpg" />
            <div>kuca.jpg</div>
          </div>
        </div>
        <div class="col-sm-2" align="center">
          <div class="card cardPopout" id="3" @click="odabirSlike(3)">
            <img class="card-img-top" src="../assets/lepsi kraj.jpg" />
            <div>lepsi kraj.jpg</div>
          </div>
        </div>
        <div class="col-sm-2" align="center">
          <div class="card cardPopout" id="4" @click="odabirSlike(4)">
            <img class="card-img-top" src="../assets/selidba.jpg" />
            <div>selidba.jpg</div>
          </div>
        </div>
        <div class="col-sm-2" align="center">
          <div class="card cardPopout" id="5" @click="odabirSlike(5)">
            <img class="card-img-top" src="../assets/uhvat zeca.jpg" />
            <div>uhvati zeca.jpg</div>
          </div>
        </div>
      </div>

      <div id="bottoBorder">
        <button class="btn btn-secondary" @click="potvrdaOdabira()">
          choose
        </button>
        &nbsp;
        <button class="btn btn-secondary" @click="cancelOdabir()">
          cancel
        </button>
      </div>
    </modal>
  </div>
</template>


<script>
import AllRecs from "../data/preporuke";
import allUsers from "../data/users";
import { bus } from "../main";
import AllBooks from "../data/books";

export default {
  data() {
    return {
      name: "",
      autor: "",
      year: "",
      numOfPages: "",
      desctiption: "",
      image: "",
      doc: "",
      allBooks: [],
    };
  },
  mounted() {
    bus.$emit("mojNalog", false);
    this.loggedUser = localStorage.getItem("loggedUser");

    if (localStorage.getItem("allUsers") != null)
      this.allUsers = JSON.parse(localStorage.getItem("allUsers"));
    else this.allUsers = allUsers;
  },
  methods: {
    openDoc() {
      this.$modal.show("newBook");
    },
    uploadedoc() {
      alert("uploaded doc");
    },
    odabirSlike(name) {
      if (this.doc != "") {
        document.getElementById(this.doc).style = " border-style: none;";
      }
      this.doc = name;
      document.getElementById(name).style = " border-style: solid;";
    },
    potvrdaOdabira() {
      if (this.doc != "") {
        if (this.doc == "1") this.image = "kalendar maja.jpg";
        if (this.doc == "2") this.image = "kuca.jpg";
        if (this.doc == "3") this.image = "lepsi kraj.jpg";
        if (this.doc == "4") this.image = "selidba.jpg";
        if (this.doc == "5") this.image = "uhvat zeca.jpg";
      }
      this.$modal.hide("newBook");
    },
    cancelOdabir() {
      this.doc = "";
      this.image = this.doc;
      this.$modal.hide("newBook");
    },
    closeModal() {
      this.$modal.hide("newBook");
    },
    odustani() {
      bus.$emit("nova", false);
      this.$router.go(-1);
    },
    dodajKnjigu() {
      if (
        this.name == "" ||
        this.autor == "" ||
        this.year == "" ||
        this.numOfPages == "" ||
        this.image == ""
      )
        alert("mrate uneti sve podatke");
      else {
        if (localStorage.getItem("allBooks") != null)
          this.allBooks = JSON.parse(localStorage.getItem("allBooks"));
        else this.allBooks = AllBooks;

        this.allBooks.push({
          autor: this.autor,
          comments: [],
          description: this.desctiption,
          id: this.allBooks.length + 1,
          name: this.name,
          numOfPages: this.numOfPages,
          promo: false,
          usersCommented: [],
          year: this.year,
          image: this.image,
        });

        localStorage.setItem("allBooks", JSON.stringify(this.allBooks));
        bus.$emit("homeZaposleni");
      }
    },
  },
};
</script>


<style>
#buttonDoc {
  color: black;
}
.input-group-text {
  color: black;
}

#topBorder {
  background-color: lightgray;
  text-align: right;
  padding-right: 2%;
}
#middleBorder {
  height: 65%;
  margin-top: 5%;
  overflow-y: auto;
}
#bottoBorder {
  padding-top:2% ;
  height: 20%;
  text-align: right;
  padding-right: 2%;
    overflow-y: auto;
}
.cardPopout {
  border-style: none;

  width: 100%;
  height: auto;
}
#findDoc {
  border-top-left-radius: 20px;
  border-bottom-left-radius: 20px;
}
.buttons {
  color: white;
  border-radius: 15px;
}
.form-control {
  width: 90%;

  border-radius: 20px;
}
#topTextBook {
  margin-bottom: 2%;
}

#btnDanRec {
  margin-bottom: 2px;
}
#formcontrolChangeNova {
  width: 80%;
  margin-bottom: 10px;
}

#formcontrolChangeManje {
  width:100%;
    margin-left: 5%;
  margin-right: 5%;
  margin-bottom: 10px;
}

#changeDataWrapNewBook {
  height: 90vh;
  position: relative;
  padding-top: 10%;
  align-self: center;
}

#changeDataNewBook {
  height: 80vh;

  margin-top: 2%;
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

@media (max-width: 576px) {
  .cardPopout {
    border-style: none;

    width: 50%;
    height: auto;
  }
}
@media (max-width: 1400px) {
}

@media (max-width: 767px) {
}

@media (max-width: 991px) {
}
</style>