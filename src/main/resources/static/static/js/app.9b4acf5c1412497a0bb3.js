webpackJsonp([1],{NHnr:function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var s=n("7+uW"),a={render:function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"wrap"},[e("div",{staticClass:"test",attrs:{id:"app"}},[e("router-view")],1)])},staticRenderFns:[]};var r=n("VU/8")({name:"App"},a,!1,function(t){n("S3ii")},null,null).exports,i=n("/ocq"),o={name:"Converter",data:function(){return{selected:"KRW",rate:0,rstMsg:"",errMsg:"",options:[{value:"KRW",text:"한국(KRW)"},{value:"JPY",text:"일본(JPY)"},{value:"PHP",text:"필리핀(PHP)"}],posts:{}}},created:function(){this.greet()},methods:{greet:function(t){var e=this;this.$http.get("http://localhost:8080/getInfo.do").then(function(t){e.posts=t.data.quotes;var n="USD"+e.selected;e.rate=e.comma(Number(e.posts[n]).toFixed(2))})},validation:function(t){var e=new RegExp("^[0-9]+$"),n=t.target.value;e.test(n.replace(/,/gi,""))?this.errMsg="":this.errMsg="수취 금액에 숫자만 입력 가능합니다",t.target.value=this.comma(n.replace(/[^0-9]/gi,"")),console.log("입력값 : "+document.querySelector("input[name=rmt]").value)},submitData:function(){var t=document.querySelector("input[name=rmt]").value;if(console.log(t),!this.chk("수취금액",t))return!1},comma:function(t){return t.replace(/(\d)(?=(\d{3})+(?:\.\d+)?$)/g,"$1,")},chk:function(t,e){return!(!e||e.length<=0)||(this.errMsg=t+"값을 입력하지 않았습니다",!1)}}},l={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{attrs:{id:"converter"}},[n("h1",[t._v("환율 계산")]),t._v(" "),n("ul",[t._m(0),t._v(" "),n("li",[n("span",{staticClass:"tlt"},[t._v("수취국가 ")]),t._v(":\n      "),n("select",{directives:[{name:"model",rawName:"v-model",value:t.selected,expression:"selected"}],on:{change:[function(e){var n=Array.prototype.filter.call(e.target.options,function(t){return t.selected}).map(function(t){return"_value"in t?t._value:t.value});t.selected=e.target.multiple?n:n[0]},t.greet]}},t._l(t.options,function(e){return n("option",{key:e.id,domProps:{value:e.value}},[t._v(t._s(e.text))])}),0)]),t._v(" "),n("li",[n("span",{staticClass:"tlt"},[t._v("환율")]),t._v(": "+t._s(t.rate)+" "+t._s(t.selected)+"/USD\n    ")]),t._v(" "),n("li",[n("span",{staticClass:"tlt"},[t._v("송금액")]),t._v(": "),n("input",{attrs:{type:"text",name:"rmt"},on:{keyup:t.validation}}),t._v(" USD\n    ")]),t._v(" "),n("li",[n("button",{staticClass:"sbmBtn",attrs:{type:"button",id:"calcBtn"},on:{click:t.submitData}},[t._v("submit")])])]),t._v(" "),n("div",{staticClass:"errMsg"},[t._v("\n    "+t._s(t.errMsg)+"\n  ")]),t._v(" "),n("div",{staticClass:"rstMsg"},[t._v("\n    "+t._s(t.rstMsg)+"\n  ")])])},staticRenderFns:[function(){var t=this.$createElement,e=this._self._c||t;return e("li",[e("span",{staticClass:"tlt"},[this._v("송금국가")]),this._v(": 미국 (USD)\n    ")])}]};var c=n("VU/8")(o,l,!1,function(t){n("X+oE")},"data-v-46e848b2",null).exports;s.a.use(i.a);var u=new i.a({routes:[{path:"/",name:"Converter",component:c}]}),v=(n("O48a"),n("mtWM")),p=n.n(v);n("Rf8U");s.a.config.productionTip=!1,s.a.prototype.$http=p.a,new s.a({el:"#app",router:u,components:{App:r},template:"<App/>"})},S3ii:function(t,e){},"X+oE":function(t,e){}},["NHnr"]);
//# sourceMappingURL=app.9b4acf5c1412497a0bb3.js.map