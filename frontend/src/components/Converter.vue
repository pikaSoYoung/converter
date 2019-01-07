<template>
  <div id="converter">
    <h1>환율 계산</h1>
    <ul>
      <li>
        <span class="tlt">송금국가</span>: 미국 (USD)
      </li>
      <li>
        <span class="tlt">수취국가 </span>:
        <select v-model="selected" @change="greet">
            <option v-for="option in options" :key="option.id" v-bind:value="option.value">{{option.text}}</option>
        </select>
      </li>
      <li>
        <span class="tlt">환율</span>: {{rate}} {{selected}}/USD
      </li>
      <li>
         <span class="tlt">송금액</span>: <input type="text" @keyup="validation" name="rmt"> USD
      </li>
      <li>
       <button type="button" @click="submitData" class="sbmBtn">submit</button>
      </li>
    </ul>
    <div class="errMsg">
      {{errMsg}}
    </div>
    <div class="rstMsg">
      {{rstMsg}}
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
      rstMsg: '',
      errMsg: '',
      options: [
        { value: 'KRW', text: '한국(KRW)' },
        { value: 'JPY', text: '일본(JPY)' },
        { value: 'PHP', text: '필리핀(PHP)' }
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
      const reg = new RegExp('^[0-9]+$')
      const res = /[^0-9]/gi
      let numStr = event.target.value
      reg.test(numStr.replace(/,/gi, '')) ? this.errMsg = '' : this.errMsg = '수취 금액에 숫자만 입력 가능합니다'
      document.querySelector('input[name=rmt]').value = this.comma(numStr.replace(res, ''))
    },
    submitData: function () {
      let rmt = document.querySelector('input[name=rmt]').value
      console.log(rmt)
      if (!this.chk('수취금액', rmt)) {
        return false
      }
    },
    comma: function (num) {
      return num.replace(/(\d)(?=(\d{3})+(?:\.\d+)?$)/g, '$1,')
    },
    chk: function (key, value) {
      if (!value || value.length <= 0) {
        this.errMsg = `${key}값을 입력하지 않았습니다`
        return false
      } else {
        return true
      }
    }
  }
}

</script>
<!--
  const baseURI = 'http://localhost:8080/getData.do'
      let form = new FormData()
      form.append('info', this.posts[`USD${this.selected}`])
      form.append('rmt', this.rmt.replace(/,/gi, ''))
      this.$http.post(`${baseURI}`, form).then((result) => {
        this.rstMsg = `수취금액은 ${this.comma(Number(result.data.msg).toFixed(2))} ${this.selected} 입니다`
      })
-->
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1 {
  font-weight: normal;
  font-size: 25px;
  margin-top: 5px;
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
button.sbmBtn {
  border: 1px solid #363650;
  background-color: #133571;
  border-radius: 3px;
  width: 100%;
  line-height: 40px;
  text-align: center;
  color: #fff;
  font-size: 16px;
}
.errMsg {
  margin-top: 20px;
  line-height: 160%;
  color: #f64d86;
  font-size: 14px;
  font-weight: 600;
  text-align: center;
}
.tlt {
  width: 25%;
  display: inline-block;
}
</style>
