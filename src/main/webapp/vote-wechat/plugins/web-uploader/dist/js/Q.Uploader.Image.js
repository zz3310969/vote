//devin87@qq.com
//build:2017/03/21 10:20:44
!function(e,t){"use strict";function i(e,t){e.className+=" "+t}function a(e,t){e&&(e.innerHTML=t||"")}function r(t,i){var a=e.URL||e.webkitURL;if(a)return i(a.createObjectURL(t));if(e.FileReader){var r=new FileReader;r.onload=function(e){i(e.target.result)},r.readAsDataURL(t)}else t.readAsDataURL&&i(t.readAsDataURL())}function n(e,i,a){var n=i.input,o=i.file||(n.files?n.files[0]:t);if(o)r(o,function(t){t&&(e.innerHTML='<img src="'+t+'" />'),a&&a(t)});else if(n){var s=n.value;if(s&&!/^\w:\\fakepath/.test(s)||(n.select(),parent.document.body.focus(),document.selection&&(s=document.selection.createRange().text)),s){e.innerHTML='<img src="'+s+'" />';try{p>6&&(e.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src='"+s+"')")}catch(l){}}a&&a(s)}}function o(e){var t=e.slice(1);return"image/"+("jpg"==t?"jpeg":t)}function s(e,t){for(var i=e.split(","),a=atob(i[1]),r=[],n=0,o=a.length;o>n;n++)r[n]=a.charCodeAt(n);if(g)return new g([new Uint8Array(r)],{type:t});var s=new h;return s.append(r),s.getBlob(t)}function l(e,t,i,a){var r=new Image;r.src=e,r.onload=function(){var e=r.width,n=r.height,o=i.maxWidth,s=i.maxHeight,l=o&&e>o,c=s&&n>s,d=l||c;if(!d)return a&&a(!1);l&&(e=o,n=Math.floor(r.height*e/r.width)),c&&(n=s,e=Math.floor(r.width*n/r.height));var u=document.createElement("canvas"),f=u.getContext("2d");u.width=e,u.height=n,f.drawImage(r,0,0,e,n),a&&a(u.toDataURL(t),t)}}var c=Q.getFirst,d=Q.getNext,u=Q.createEle,f=Q.setOpacity,p=Q.ie,v=Q.Uploader,g=e.Blob||e.WebkitBlob||e.MozBlob,h=e.WebKitBlobBuilder||e.MozBlobBuilder,m=function(){if(!e.FileReader||!e.atob||!g&&!h)return!1;var t=document.createElement("canvas");return!!t.getContext&&!!t.getContext("2d")}(),b=".jpg,.png,.gif,.bmp,.webp,.tif,.tiff",w=".jpg";v.support.imageScale=m,v.previewImage=n,v.scaleImage=l,v.extend({init:function(){var e=this.ops,t=e.boxView;e.allows||(e.allows=b),t&&i(t,this.html5?"html5":"html4")},supportScale:function(e){if(!m)return!1;".jpeg"==e&&(e=".jpg");var t=(this.ops.scale||{}).types||w,i=-1!=b.indexOf(e);return i?"*"==t||-1!=t.indexOf(e):!1},previewImage:function(e,t,i){var a=this,r=t.scale||i.scale,c=r&&a.supportScale(t.ext);return c&&(t.skip=!0),n(e,t,function(e){a.fire("preview",{task:t,src:e}),e&&c&&l(e,o(t.ext),r,function(e,i){if(e){var r=s(e,i);t.blob=r,a.fire("scale",{task:t,base64:e,type:i,blob:r})}t.skip=!1,a.list.push(t),a.auto&&a.start()})}),a},draw:function(e){var t=this,i=t.ops,a=i.view;if(a){var r=e.name,n='<div class="u-img"></div><div class="u-progress-bar"><div class="u-progress"></div></div><div class="u-detail"></div><div class="u-name" title="'+r+'">'+r+"</div>",o=e.id,s=u("div","u-item",n);s.taskId=o;var l=c(s),p=d(l),v=c(p),g=d(p);f(p,.3),f(v,.5),e.box=s,e.boxProgress=v,e.boxDetail=g,a.appendChild(s),t.previewImage(l,e,i).update(e)}},update:function(e){if(e&&e.box){var i=this,r=e.total||e.size,n=e.loaded,o=e.state,s=e.boxProgress,l=e.boxDetail,c=v.getStatusText(o);if(i.html5&&n!=t&&n>=0){var d;if(o==v.PROCESSING){var u=Math.min(100*n/r,100);d=u.toFixed(1),"100.0"==d&&(d="99.9")}else o==v.COMPLETE&&(d="100");d&&(d+="%",c+=" "+d,s.style.width=d)}a(l,c)}},over:function(e){e&&e.box&&i(e.box,"u-over")}})}(window);