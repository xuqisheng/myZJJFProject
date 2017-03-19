Validator={elementErrorClass:"error",language:"fo",languages:{en:{textbox:{required:"此处不能为空",min:"请至少输入 {characters} 位",max:"输入字符不能超过 {characters} 位",email:"改邮箱账号无效",url:"该网址无效",number:"请输入数字",digits:"请输入数字",intNumber:"请输入大于0的正整数",deNumber:"请输入正确价格"},password:{required:"此处不能为空",min:"请至少输入 {characters} 位",max:"输入字符不能超过 {characters} 位",match:"密码输入不一致"},radio:{},checkbox:{required:"此处不能为空"},select:{required:"请选择其中一项"},textarea:{required:"此处不能为空",min:"请至少输入 {characters} 位",max:"输入字符不能超过 {characters} 位",url:"该网址无效"}}},showError:function(a,t){if(!$(a).hasClass(Validator.elementErrorClass)){var r=document.createElement("span");if($(r).addClass("validator-error").html(t),void 0==$(a).attr("data-error-position")){var i="after";$(this).is("input")&&"checkbox"==$(this).attr("type")&&(i="after label")}else i=$(a).attr("data-error-position");var e,s=i.split(" ");e=void 0==s[1]?a:$(a).closest(s[1])[0],"after"==s[0]?$(e).after(r):"after"==s[0]&&$(e).after(r),$(e).addClass(Validator.elementErrorClass),void 0!=$(a).attr("data-match")&&$("#"+$(a).attr("data-match")).addClass(Validator.elementErrorClass)}},validate:function(a){var t=!1,r=null;return Validator.removeErrors(a),$(a).find("input, select, textarea").each(function(){var a=null;if($(this).is("input")&&("text"==$(this).attr("type")||void 0==$(this).attr("type"))&&(void 0!=$(this).attr("data-required")&&""==$(this).val()&&void 0==$(this).attr("data-required-if")&&(Validator.showError(this,Validator.languages[Validator.language].textbox.required),t=!0),void 0!=$(this).attr("data-required-if")&&""==$(this).val()&&(void 0==$(this).attr("data-required-if-value")&&$("#"+$(this).attr("data-required-if")).is(":checked")||void 0!=$(this).attr("data-required-if-value")&&$("#"+$(this).attr("data-required-if")).val()==$(this).attr("data-required-if-value"))&&(Validator.showError(this,Validator.languages[Validator.language].textbox.required),t=!0),void 0!=$(this).attr("data-min")&&$(this).val().length<parseFloat($(this).attr("data-min"))&&0!=$(this).val().length&&(Validator.showError(this,Validator.languages[Validator.language].textbox.min.replace("{characters}",$(this).attr("data-min"))),t=!0),void 0!=$(this).attr("data-max")&&$(this).val().length>parseFloat($(this).attr("data-max"))&&(Validator.showError(this,Validator.languages[Validator.language].textbox.max.replace("{characters}",$(this).attr("data-min"))),t=!0),void 0!=$(this).attr("data-type")))switch($(this).attr("data-type")){case"email":a=/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,a.test($(this).val())||""==$(this).val()||(Validator.showError(this,Validator.languages[Validator.language].textbox.email),t=!0);break;case"url":a=/^(https?:\/\/)?((([a-z\d]([a-z\d-]*[a-z\d])*)\.)+[a-z]{2,}|((\d{1,3}\.){3}\d{1,3}))(\:\d+)?(\/[-a-z\d%_.~+]*)*(\?[;&a-z\d%_{},.~+=-]*)?(\#[-a-z\d_]*)?$/i,a.test($(this).val().replace("_",""))||""==$(this).val()||(Validator.showError(this,Validator.languages[Validator.language].textbox.url),t=!0);break;case"number":a=/^\s*(\+|-)?((\d+([\.,]\d+)?)|([\.,]\d+))\s*$/,a.test($(this).val().trim())||""==$(this).val()||(Validator.showError(this,Validator.languages[Validator.language].textbox.number),t=!0);break;case"intNumber":a=/^\+?[1-9]\d*$/,a.test($(this).val().trim())||""==$(this).val()||(Validator.showError(this,Validator.languages[Validator.language].textbox.intNumber),t=!0);break;case"deNumber":a=/^\d+(\.\d{1,2})?$/,a.test($(this).val().trim())||""==$(this).val()||(Validator.showError(this,Validator.languages[Validator.language].textbox.deNumber),t=!0);break;case"digits":a=/^\s*\d+\s*$/,a.test($(this).val())||""==$(this).val()||(Validator.showError(this,Validator.languages[Validator.language].textbox.digits),t=!0)}if($(this).is("input")&&"password"==$(this).attr("type")&&(void 0!=$(this).attr("data-required")&&""==$(this).val()&&void 0==$(this).attr("data-required-if")&&(Validator.showError(this,Validator.languages[Validator.language].password.required),t=!0),void 0!=$(this).attr("data-required-if")&&""==$(this).val()&&(void 0==$(this).attr("data-required-if-value")&&$("#"+$(this).attr("data-required-if")).is(":checked")||void 0!=$(this).attr("data-required-if-value")&&$("#"+$(this).attr("data-required-if")).val()==$(this).attr("data-required-if-value"))&&(Validator.showError(this,Validator.languages[Validator.language].password.required),t=!0),void 0!=$(this).attr("data-min")&&$(this).val().length<parseFloat($(this).attr("data-min"))&&0!=$(this).val().length&&(Validator.showError(this,Validator.languages[Validator.language].password.min.replace("{characters}",$(this).attr("data-min"))),t=!0),void 0!=$(this).attr("data-max")&&$(this).val().length>parseFloat($(this).attr("data-max"))&&(Validator.showError(this,Validator.languages[Validator.language].password.max.replace("{characters}",$(this).attr("data-min"))),t=!0),void 0!=$(this).attr("data-match")&&$(this).val()!=$("#"+$(this).attr("data-match")).val()&&(Validator.showError(this,Validator.languages[Validator.language].password.match),t=!0)),$(this).is("input")&&"radio"==$(this).attr("type"),$(this).is("input")&&"checkbox"==$(this).attr("type")&&(void 0==$(this).attr("data-required")||$(this).is(":checked")||void 0!=$(this).attr("data-required-if")||(Validator.showError(this,Validator.languages[Validator.language].checkbox.required),t=!0),void 0!=$(this).attr("data-required-if")&&""==$(this).val()&&(void 0==$(this).attr("data-required-if-value")&&$("#"+$(this).attr("data-required-if")).is(":checked")||void 0!=$(this).attr("data-required-if-value")&&$("#"+$(this).attr("data-required-if")).val()==$(this).attr("data-required-if-value"))&&(Validator.showError(this,Validator.languages[Validator.language].checkbox.required),t=!0)),$(this).is("select")&&(void 0!=$(this).attr("data-required")&&""==$(this).val()&&void 0==$(this).attr("data-required-if")&&(Validator.showError(this,Validator.languages[Validator.language].select.required),t=!0),void 0!=$(this).attr("data-required-if")&&""==$(this).val()&&(void 0==$(this).attr("data-required-if-value")&&$("#"+$(this).attr("data-required-if")).is(":checked")||void 0!=$(this).attr("data-required-if-value")&&$("#"+$(this).attr("data-required-if")).val()==$(this).attr("data-required-if-value"))&&(Validator.showError(this,Validator.languages[Validator.language].select.required),t=!0)),$(this).is("textarea")&&(void 0!=$(this).attr("data-required")&&""==$(this).val()&&void 0==$(this).attr("data-required-if")&&(Validator.showError(this,Validator.languages[Validator.language].textarea.required),t=!0),void 0!=$(this).attr("data-required-if")&&""==$(this).val()&&(void 0==$(this).attr("data-required-if-value")&&$("#"+$(this).attr("data-required-if")).is(":checked")||void 0!=$(this).attr("data-required-if-value")&&$("#"+$(this).attr("data-required-if")).val()==$(this).attr("data-required-if-value"))&&(Validator.showError(this,Validator.languages[Validator.language].textarea.required),t=!0),void 0!=$(this).attr("data-min")&&$(this).val().length<parseFloat($(this).attr("data-min"))&&0!=$(this).val().length&&(Validator.showError(this,Validator.languages[Validator.language].textarea.min.replace("{characters}",$(this).attr("data-min"))),t=!0),void 0!=$(this).attr("data-max")&&$(this).val().length>parseFloat($(this).attr("data-max"))&&(Validator.showError(this,Validator.languages[Validator.language].textarea.max.replace("{characters}",$(this).attr("data-min"))),t=!0),void 0!=$(this).attr("data-type")))switch($(this).attr("data-type")){case"url":a=/^(https?:\/\/)?((([a-z\d]([a-z\d-]*[a-z\d])*)\.)+[a-z]{2,}|((\d{1,3}\.){3}\d{1,3}))(\:\d+)?(\/[-a-z\d%_.~+]*)*(\?[;&a-z\d%_{},.~+=-]*)?(\#[-a-z\d_]*)?$/i,a.test($(this).val())||""==$(this).val()||(Validator.showError(this,Validator.languages[Validator.language].textarea.url),t=!0)}t&&null==r&&(r=this,$(this).focus())}),!t},removeErrors:function(a){$(a).find(".validator-error").each(function(){$(this).remove()}),$(a).find(".error").each(function(){$(this).removeClass("error")}),$(a).find("input[type=text], input[type=password], input[type=radio], input[type=checkbox], select, textarea").each(function(){"radio"==$(this).attr("type")||"checkbox"==$(this).attr("type")?$(this).closest("label").removeClass(Validator.elementErrorClass):$(this).removeClass(Validator.elementErrorClass)})}},$(function(){$("form.validator").each(function(){$(this).submit(function(){return Validator.validate(this)})})});