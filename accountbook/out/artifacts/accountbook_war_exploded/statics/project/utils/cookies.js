/**
 * Created by Administrator on 2017/3/21.
 */

var passKey = "zhouyuan";  //cookies 秘钥

function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + encrypt(escape(cvalue), passKey) + "; " +expires;
}

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
        if (c.indexOf(name) != -1){
            var cnameValue = c.substring(name.length, c.length);
            return unescape(decrypt(cnameValue, passKey));
        }
    }
    return "";
}

function clearCookie(cname) {
    setCookie(cname, "", -1);
}
