/* 2017-03-21 11:21:42 */
!function (e, n) {
    function t(e) {
        var t = n.createElement("iframe");
        return t.style.cssText = "width:0;height:0;display:none", t.src = e, t.id = s, n.body.appendChild(t), t
    }

    function o(e) {
        var t = new RegExp("(^| )" + e + "=([^;]*)(;|$)"), o = n.cookie.match(t);
        return o ? o[2] || "" : ""
    }

    function a() {
        return navigator.userAgent.indexOf("Windows NT 5.1") >= 0 && (navigator.userAgent.indexOf("MSIE 6") >= 0 || navigator.userAgent.indexOf("MSIE 7") >= 0 || navigator.userAgent.indexOf("MSIE 8") >= 0) && "https:" === location.protocol
    }

    function i() {
        var e = navigator.userAgent.toLowerCase();
        return e.indexOf("android") >= 0 || e.indexOf("iphone") >= 0 || e.indexOf("ipad") >= 0 || e.indexOf("ipod") >= 0 || e.indexOf("mobile") >= 0
    }

    function c(e, n, t) {
        var o = !!e.attachEvent, a = "attachEvent", i = "addEventListener", c = o ? a : i;
        e[c]((o ? "on" : "") + n, t)
    }

    function r(e, n, t) {
        var o = !!e.detachEvent, a = "detachEvent", i = "removeEventListener", c = o ? a : i;
        e[c]((o ? "on" : "") + n, t)
    }

    function d() {
        function a(e) {
            if (!(g++ > 6))try {
                n.getElementById(s).contentWindow.postMessage("opl_ms", "*")
            } catch (e) {
            }
        }

        function i(t) {
            try {
                t.data && ("opl_attach" == t.data ? c(n, "mousemove", a) : "opl_detach" == t.data ? r(n, "mousemove", a) : t.data.match(/^opl_quit/) && r(e, "message", i))
            } catch (t) {
            }
        }

        function m() {
            return null != e.location.host.match(/(ditu.amap.com|sycm.taobao.com)/i)
        }

        var f = (new Date).getTime();
        if (8e3 > f - u) {
            var v = o("cna"), l = o("nickname");
            if (v || l) {
                var g = 0;
                m() && c(e, "message", i);
                t("//g.alicdn.com/alilog/oneplus/blk.html#coid=" + encodeURIComponent(v) + "&noid=" + encodeURIComponent(l) + "&grd=" + (m() ? "y" : "n"))
            } else e.setTimeout(function () {
                d()
            }, 300)
        }
    }

    try {
        var s = "_oid_ifr_", u = (new Date).getTime();
        a() || i() || d()
    } catch (m) {
    }
}(window, document);