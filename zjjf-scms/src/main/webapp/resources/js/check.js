function isTrueName(n){var t=/^[a-zA-Z]{1,30}$/;return!!t.exec(n)}function isPasswd(n){var t=/^(\w){6,20}$/;return!!t.exec(n)}function isTel(n){var t=/^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;return!!t.exec(n)}function isMobil(n){var t=/^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;return!!t.exec(n)}function isPostalCode(n){var t=/^[a-zA-Z0-9 ]{3,12}$/;return!!t.exec(n)}function isSearch(n){var t=/^[^`~!@#$%^&*()+=|\\\][\]\{\}:;\'\,.<>\/?]{1}[^`~!@$%^&()+=|\\\][\]\{\}:;\'\,.<>?]{0,19}$/;return!!t.exec(n)}function isIP(n){var t=/^[0-9.]{1,20}$/;return!!t.exec(n)}function isBetween(n,t,e){return!(n<t||n>e)}function isDate(n){var t=n.indexOf("-"),e=n.lastIndexOf("-");if(t==e)return!1;var r=n.substring(0,t),i=n.substring(t+1,e),s=n.substring(e+1,n.length),u=31;return 0!=isInt(i)&&0!=isInt(s)&&0!=isInt(r)&&(!(r.length<4)&&(!!isBetween(i,1,12)&&(4==i||6==i||9==i||11==i?u=30:2==i&&(u=r%4>0?28:r%100==0&&r%400>0?28:29),0!=isBetween(s,1,u))))}function isEuDate(n){if(0==isBetween(n.length,8,10))return!1;var t=n.indexOf("/"),e=n.lastIndexOf("/");if(t==e)return!1;var r=n.substring(t+1,e),i=n.substring(0,t),s=n.substring(e+1,n.length),u=31;return 0!=isInt(r)&&0!=isInt(i)&&0!=isInt(s)&&(!(s.length<4)&&(0!=isBetween(r,1,12)&&(4==r||6==r||9==r||11==r?u=30:2==r&&(u=s%4>0?28:s%100==0&&s%400>0?28:29),0!=isBetween(i,1,u))))}function isComdate(n,t){if(!isDate(n))return!1;if(!isDate(t))return!1;var e=n.indexOf("-"),r=n.lastIndexOf("-"),i=t.indexOf("-"),s=t.lastIndexOf("-"),u=n.substrin(0,e),a=n.substring(e+1,r),f=n.substring(r+1,n.length),l=t.substring(0,i),o=t.substring(i+1,s),c=t.substring(s+1,t.length),g=new Date(u,a,f),d=new Date(l,o,c);return!(g>d)}function isEmpty(n){return null==n||0==n.length}function isInt(n){var t=!0;if(isEmpty(n))t=!1;else for(var e=0;e<n.length;e++)if(0==isDigit(n.substring(e,e+1))){t=!1;break}return t}function isReal(n,t){var e=n.indexOf("."),r=n.lastIndexOf(".");if(isEmpty(n))return!1;if(e==-1)return!!isInt(n);if(e!=r)return!1;if(0==e)return!1;var i=n.substring(0,e),s=n.substring(r+1);return!(s.length>t)&&(!(!isInt(i)||!isInt(s))&&!isEmpty(s))}function isEmail(n){var t=n.indexOf("@"),e=n.indexOf(".",t);return theSub=n.substring(0,e+1),!(t<1||t!=n.lastIndexOf("@")||e<t+2||n.length<=theSub.length)}function newWindow(n,t,e,r,i){var s="_blank";opt+="scrollbars="+r+",",opt+="resizable="+i+",",opt+="width="+e+",",opt+="height="+t,winHandle=window.open(n,s,opt)}function DecimalFormat(n){var t=parseInt(n),e=parseFloat(n)-t;return str="",0==e||null==e?str+=t+".00":str+=t+e,str}var check={isDigit:function(n){var t=/^[0-9]{1,20}$/;return!!t.exec(n)},isRegisterUserName:function(n){var t=/^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){4,19}$/;return!!t.exec(n)}};