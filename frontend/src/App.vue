<template>
  <div class="wrap">
    <div id="app" class="test">
      <router-view/>
    </div>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
body, html {
  margin: 0;
  padding: 0;
}
.wrap {
  position:absolute;
  height:100%;
  width:100%;
  background: #eff2fc;
  margin: 0 auto;
}
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
  width: 320px;
  position: relative;
  margin: 20px auto;
  top:20%;
  background-color: #fff;
  border-radius: 10px;
  -webkit-box-shadow: 0 0 5px rgba(54,54,80,0.2);
  -moz-box-shadow: 0 0 5px rgba(54,54,80,0.2);
  box-shadow: 0 0 5px rgba(54,54,80,0.2);
  padding:20px;
}
select {
  height: 30px;
  padding: 0 0 0 3px;
  line-height: 33px;
  font-size: 15px;
  border: 1px solid #d1d7e7;
  vertical-align: middle;
}
select, input {
  border-radius: 3px;
}
input {
  height: 25px;
  border: 1px solid #d1d7e7;
}
</style>
