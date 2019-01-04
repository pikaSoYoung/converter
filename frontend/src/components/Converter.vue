<template>
  <div class="hello">
    <h1>환율 계산</h1>
    <ul>
      <li>
        <span>송금국가 : 미국 (USD)</span>
      </li>
      <li>
        <span>수취국가 : </span>
        <select v-on:change="greet" v-model="selected">
            <option value="KRW">한국(KRW)</option>
            <option value="JPN">일본(JPN)</option>
            <option value="PHP">필리핀(PHP)</option>
        </select>
      </li>
      <li>
        <span>환율 : {{rate}} {{selected}}/USD</span>
      </li>
      <li>
         <span>송금액 : </span><input type="text" name="rmt">
      </li>
      <li>
       <button type="button" id="calcBtn">변환</button>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'Converter',
  data () {
    return {
      selected: 'KRW',
      posts: []
    }
  },
  el: '#calcBtn',
  methods: {
    greet: function (event) {
      const baseURI = 'http://localhost:8080/getInfo.do'
      this.$http.get(`${baseURI}`).then((result) => {
        console.log(result.data.quotes)
        this.posts = result.data.quotes
      })
    }
  }
}

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: block;
  margin: 10px 0;
  text-align: left;
}
a {
  color: #42b983;
}
</style>
