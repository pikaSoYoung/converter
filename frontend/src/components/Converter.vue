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
         <span class="tlt">송금액</span>: <input type="text" @input="validation" v-model="rmt"> USD
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
      rmt: '',
      options: [
        { value: 'KRW', text: '한국(KRW)' },
        { value: 'JPY', text: '일본(JPY)' },
        { value: 'PHP', text: '필리핀(PHP)' }
      ],
      posts: {},
      rmtStr: '송금액'
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
        this.rate = this.comma(this.fixed(this.posts[selectedOp], 2))
      })
    },
    validation: function (event) {
      this.rstMsg = ''
      const reg = new RegExp('^[0-9]+$')
      const res = /[^0-9]/gi
      let numStr = event.target.value
      numStr.length <= 0 || reg.test(this.commaDel(numStr)) ? this.errMsg = '' : this.errMsg = this.errMsgSet(this.rmtStr, 'default')
      event.target.value = this.comma(numStr.replace(res, ''))
      this.rmt = event.target.value
    },
    submitData: function () {
      this.rstMsg = ''
      this.errMsg = ''
      let rmtData = this.commaDel(this.rmt)
      if (this.chk(this.rmtStr, rmtData)) {
        const baseURI = 'http://localhost:8080/getData.do'
        let form = new FormData()
        form.append('info', this.posts[`USD${this.selected}`])
        form.append('rmt', rmtData)
        this.$http.post(`${baseURI}`, form).then((result) => {
          if (!result.data.err) {
            this.rstMsg = `수취금액은 ${this.comma(this.fixed(result.data.msg, 2))} ${this.selected} 입니다`
          } else {
            this.errMsg = this.errMsgSet(this.rmtStr, 'err')
          }
        })
      }
    },
    comma: function (num) {
      return num.replace(/(\d)(?=(\d{3})+(?:\.\d+)?$)/g, '$1,')
    },
    commaDel: function (num) {
      return num.replace(/,/gi, '')
    },
    fixed: function (num, idx) {
      return Number(num).toFixed(idx)
    },
    chk: function (key, value) {
      if (!value || value.length <= 0) {
        this.errMsg = this.errMsgSet(key, 'null')
        return false
      } else if (value > 10000) {
        this.errMsg = this.errMsgSet(key, 'err')
        return false
      } else {
        return true
      }
    },
    errMsgSet: function (key, state) {
      let msg = ''
      switch (state) {
        case 'null' : {
          msg = `${key}을 입력하지 않았습니다.`
          break
        }
        case 'err' : {
          msg = `${key}이 올바르지 않습니다.`
          break
        }
        default : {
          msg = `${key}에 숫자만 입력 가능합니다.`
          break
        }
      }
      return msg
    }
  }
}

</script>
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
