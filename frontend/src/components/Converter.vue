<template>
  <div id="converter">
    <h1>환율 계산</h1>
    <ul>
      <li>
        <span>송금국가 : 미국 (USD)</span>
      </li>
      <li>
        <span>수취국가 : </span>
        <select v-model="selected" @change="greet">
            <option v-for="option in options" :key="option.id" v-bind:value="option.value">{{option.text}}</option>
        </select>
      </li>
      <li>
        <span>환율 : {{rate}} {{selected}}/USD</span>
      </li>
      <li>
         <span>송금액 : </span><input type="text" name="rmt" @keyup="validation" v-model="rmt"> USD
      </li>
      <li>
       <button type="button" id="calcBtn" @click="submitData">변환</button>
      </li>
    </ul>
    <div>
      <p>{{rstMsg}}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Converter',
  data () {
    return {
      selected: 'KRW',
      rate: 0,
      rmt: '',
      rstMsg: '',
      options: [
        { value: 'KRW', text: '한국' },
        { value: 'JPY', text: '일본' },
        { value: 'PHP', text: '필리핀' }
      ],
      posts: {}
    }
  },
  created: function () {
    this.greet()
  },
  methods: {
    greet: function (event) {
      const baseURI = 'http://localhost:8080/getInfo.do'
      this.$http.get(`${baseURI}`).then((result) => {
        this.posts = result.data.quotes
        let selectedOp = `USD${this.selected}`
        this.rate = this.comma(Number(this.posts[selectedOp]).toFixed(2))
      })
    },
    validation: function (event) {
      const res = /[^0-9]/g
      this.rmt = this.comma(event.target.value.replace(res, ''))
    },
    submitData: function () {
      const baseURI = 'http://localhost:8080/getData.do'
      const res = /[^0-9]/g
      let form = new FormData()
      form.append('info', this.posts[`USD${this.selected}`])
      form.append('rmt', this.rmt.replace(res, ''))
      this.$http.post(`${baseURI}`, form).then((result) => {
        console.log()
        this.rstMsg = `수취금액은 ${this.comma(Number(result.data.msg).toFixed(2))} ${this.selected} 입니다`
      })
    },
    comma: function (num) {
      return num.replace(/(\d)(?=(\d{3})+(?:\.\d+)?$)/g, '$1,')
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
