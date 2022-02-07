<template>
  <div id="nav">
    <div class="container" id="navContainer">
      <div class="row">
        <div class="col-12 align-self-end" >
          <div class="card" id="cardNav">
           <img src="../assets/logo.png" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>



<style>
#routerlink {
  color: black;
  text-decoration: none;
}

#cardNav {
  background-color: #363940;
  border: none;
}

#preporuke {
  margin-bottom: 10px;
  margin-top: 10px;
}


#nav {
  background-color: #363940;
  color: white;
}

img {
  width: 70%;
  height: auto;
}
@media (max-width: 768px) {
  img {
    width: 70%;
    height: auto;
  }
  .col-md-4 {
    align-items: center;
  }
}

@media (max-width: 200px) {
  img {
    width: 101px;
    height: auto;
  }

}


</style>


<script>
export default {
  name: "NavigacijaLogin",
};
</script>
  
 
<script>
export default {
  data() {
    return {
      active: false,
    };
  },
  methods: {
    toggle() {
      this.active = !this.active;
    },
    logout() {
      localStorage.removeItem("in");
    },
  },
};
</script>