<template>
  <div>
    <NavBar></NavBar>
    <router-view/>
  </div>
</template>

<script>
import NavBar from './components/NavBar.vue'
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap"
// import $ from 'jquery'
export default {
  components: {
    NavBar
  },
  setup(){
    // $.ajax({
    //   url: "http://localhost:3000/user/account/token/",
    //   type: "post",
    //   data:{
    //     username:'wy',
    //     password:'wy',
    //   },
    //   success(resp){
    //     console.log(resp);
    //   },
    //   error(resp){
    //     console.log(resp);
    //   }
    // })

    // $.ajax({
    //   url: "http://localhost:3000/user/account/info/",
    //   type:"get",
    //   headers:{
    //     Authorization:"Bearer "+"eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI3MTc1NDM5ZWE0MDA0MmE2YmY3NmQ3NzUxZDg0MGQ1NSIsInN1YiI6IjEiLCJpc3MiOiJzZyIsImlhdCI6MTcxNjA3NzkwMCwiZXhwIjoxNzE3Mjg3NTAwfQ.ujL3tN5UZ7ixNkQBQXSMS92navtUtqePBoTz7fqry1o"
    //   },
    //   success(resp){
    //     console.log(resp);
    //   },
    //   error(resp){
    //     console.log(resp);
    //   }
    // })

    // $.ajax({
    //   url:"http://localhost:3000/user/account/register/",
    //   type:"post",
    //   data:{
    //     username:"111",
    //     password:"123",
    //     confirmedPassword:"123",
    //   },
    //   success(resp){
    //     console.log(resp);
    //   },
    //   error(resp){
    //     console.log(resp);
    //   }
    // })
  }
}
</script>

<style>
body {
  background-image: url("@/assets/images/1.jpeg");
  background-size: cover;
}
</style>
