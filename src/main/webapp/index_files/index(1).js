!function () {
    var t, n = {
        576436746: 1,
        2419216076: 1,
        1799790739: 1,
        3199788265: 1,
        171256861: 1,
        545102791: 1,
        2574006117: 1,
        403788015: 1,
        3220653981: 1,
        1936852660: 1,
        2226413336: 1,
        4089893501: 1,
        4203979479: 1,
        1925208379: 1,
        2999377761: 1,
        1672743503: 1,
        823545476: 1,
        1515796817: 1,
        2755299961: 1,
        2298621149: 1,
        2762029686: 1,
        27704178: 1,
        3464463979: 1,
        2090634341: 1,
        946468589: 1,
        48958199: 1,
        758128056: 1,
        2762029680: 1,
        1890341110: 1,
        2880605096: 1,
        2575140278: 1,
        642224045: 1,
        314599579: 1,
        2700198455: 1,
        2348326384: 1,
        583914009: 1,
        3473371156: 1,
        1883128869: 1,
        705811353: 1,
        359076285: 1,
        1941794629: 1,
        338472103: 1,
        1505998205: 1,
        4134415461: 1,
        4099994203: 1,
        1158600347: 1,
        1352959635: 1,
        4017610334: 1,
        758128058: 1,
        1485455796: 1,
        3223921582: 1,
        1585768309: 1,
        957533827: 1,
        2959951216: 1,
        1603531725: 1,
        2368671453: 1,
        926977419: 1,
        1074182719: 1,
        918727575: 1,
        2700198454: 1,
        1486379317: 1,
        696536657: 1,
        4128993471: 1,
        2401832426: 1,
        741323078: 1,
        2400922186: 1,
        1871037680: 1,
        1815769914: 1,
        3799465696: 1,
        609570126: 1,
        2847821237: 1,
        1762062136: 1,
        3801133992: 1,
        2383105812: 1,
        2194017693: 1,
        3712253428: 1,
        1870113321: 1,
        2478007756: 1,
        2499944574: 1,
        424003386: 1,
        3647633145: 1,
        2194017844: 1,
        3200266163: 1,
        24023159: 1,
        2689498227: 1,
        1870113199: 1,
        3691012346: 1
    }, r = "//pointman.alibaba.com", e = document._ab_sample, a = .001, o = "1.0.6", i = self, c = !!i.addEventListener;
    !function (t) {
        function n(t, n) {
            for (var r = 0, e = t.length; e > r && n(t[r], r) !== !1; r++);
        }

        function r(t, n) {
            for (var r in t)f.call(t, r) && n(t[r], r)
        }

        function e(t) {
            switch (t.tagName) {
                case"BASE":
                case"LINK":
                    return t.getAttribute("href");
                case"OBJECT":
                    var n = t.getAttribute("data");
                    if (n)return n;
                    for (var r = t.getElementsByTagName("PARAM"), e = 0; e < r.length; e++) {
                        var a = r[e];
                        if (/^src$|^movie$|^url$/i.test(a.name))return a.value
                    }
                    return null;
                default:
                    return t.getAttribute("src")
            }
        }

        function a(t) {
            var n = t.match(l);
            if (!n)return null;
            var r = n[1];
            return s.test(r) && (r = r.split("@").pop().split(":")[0]), r
        }

        function o(t) {
            return v.test(t)
        }

        function i(t) {
            if (o(t))return t;
            var n = d.test(t) ? -3 : -2, r = t.split(".");
            return r.slice(n).join(".")
        }

        function c(t) {
            for (var n = 0, r = 0, e = t.length; e > r; r++)n = (n << 5) - n + t.charCodeAt(r), n >>>= 0;
            return n
        }

        function u(t) {
            var n = [];
            return r(t, function (t, r) {
                n.push(r + "=" + encodeURIComponent(t))
            }), n.join("&")
        }

        t.ArrayEach = n;
        var f = Object.prototype.hasOwnProperty;
        t.MapEach = r, t.GetUrlAttr = e;
        var l = /^\s*(?:https?:|wss?:)?\/{2,}([^\/\?\#\\]+)/i, s = /[@:]/;
        t.GetHostNameFromUrl = a;
        var v = /^(?:\d+\.)+\d+$/;
        t.IsIPAddr = o;
        var d = /\.com\.cn$|\.com\.hk$/;
        t.GetRootDomain = i, t.StrHash = c, t.EncodeQuery = u
    }(t || (t = {}));
    var u;
    !function (t) {
        function n(t) {
            if (!(Math.random() >= a)) {
                var n = e * a, o = r + "/pv?p=" + n + "&uid=" + t;
                (new Image).src = o
            }
        }

        t.log = n
    }(u || (u = {}));
    var f;
    !function (n) {
        function r(n, r) {
            var e = document.querySelectorAll(n);
            t.ArrayEach(e, function (t) {
                var n = t.parentNode;
                n && (n.removeChild(t), f[r] || (f[r] = !0, u.log(r)))
            })
        }

        function e() {
            t.MapEach(c, function (n, e) {
                var a = i(n).split(",");
                t.ArrayEach(a, function (t) {
                    r(t, +e)
                })
            })
        }

        function a() {
            try {
                e()
            } catch (t) {
            }
        }

        function o() {
            i && setTimeout(a, 5e3)
        }

        var i = window.atob, c = {
            69: "Lmd3ZGFuZy1tYWluLCNnd2Rfd2lzaGxpc3RfZGl2",
            7: "Lmh1aS1zaG9wcGluZ3Rvb2wyLCNodWktcGx1Z2luMw==",
            21: "LnRiLXdyYXAgZGl2W3N0eWxlPSJtYXJnaW4tdG9wOjEwcHg7bWFyZ2luLWJvdHRvbTo1cHgiXQ==",
            22: "Lm1tel90b29sYmFyX2NvbnRhaW5lciwubW16X3Rvb2xiYXJfY29udHJvbF9zbWFsbCwubW16X2Nocm9tZV9pbnNlcnRfaW5mb19jb250YWluZXI="
        }, f = {};
        n.Init = o
    }(f || (f = {}));
    var l;
    !function (t) {
        function n(t, n, r) {
            switch (r.length) {
                case 0:
                    return n();
                case 1:
                    return n(r[0]);
                case 2:
                    return n(r[0], r[1]);
                default:
                    return n(r[0], r[2], r[3])
            }
        }

        function r(t, n) {
            switch (n.length) {
                case 0:
                    return new t;
                case 1:
                    return new t(n[0]);
                case 2:
                    return new t(n[0], n[1]);
                default:
                    return new t(n[0], n[2], n[3])
            }
        }

        function e(e, a, o, i) {
            return function () {
                var c, u = arguments;
                if (i)c = a(u, e); else try {
                    c = a(u, e)
                } catch (f) {
                    c = u
                }
                if (c) {
                    if (c === t.DISCARD)return;
                    u = c
                }
                return o ? r(e, u) : "apply" in e ? e.apply(this, u) : n(this, e, u)
            }
        }

        function a(t, n, r, a) {
            if (!o)return !1;
            var i = o(t, n);
            return i && i.set ? (i.set = e(i.set, r, !1, a), c || (i.get = function (t) {
                return function () {
                    return t.call(this)
                }
            }(i.get)), Object.defineProperty(t, n, i), !0) : !1
        }

        t.DISCARD = -1;
        var o = Object.getOwnPropertyDescriptor;
        t.Setter = a
    }(l || (l = {}));
    var s;
    !function (r) {
        function e(t) {
            var n = t.parentNode;
            n && n.removeChild(t)
        }

        function a(r) {
            if (r.tagName in v) {
                var a = t.GetUrlAttr(r);
                if (a) {
                    var o = t.GetHostNameFromUrl(a);
                    if (o) {
                        o = t.GetRootDomain(o);
                        var i = d[o];
                        i || (i = d[o] = t.StrHash(o)), i in n && (f[o] = !0, e(r), u.log(s))
                    }
                }
            }
        }

        function o(n) {
            t.ArrayEach(n, function (n) {
                var r = n.addedNodes;
                t.ArrayEach(r, function (t) {
                    1 === t.nodeType && a(t)
                })
            })
        }

        function i() {
            var t = new l(o);
            t.observe(document, {childList: !0, subtree: !0})
        }

        function c() {
            if (l && "http:" == location.protocol) {
                var t = "__blkset__";
                f = document[t], f || (f = document[t] = {}, f.__raw = n), i()
            }
        }

        var f, l = window.MutationObserver, s = 10, v = {SCRIPT: 1, IMG: 1, LINK: 1, IFRAME: 1}, d = {};
        r.Init = c
    }(s || (s = {}));
    var v, d = {
        2367135622: 1,
        2673897954: 1,
        3545176546: 1,
        3821767218: 1,
        3240373634: 1,
        100443169: 1,
        326224208: 1,
        114951401: 1,
        1685056633: 1,
        3007292783: 1
    };
    !function (n) {
        function a(t) {
            try {
                return localStorage[t]
            } catch (n) {
            }
        }

        function i(t, n) {
            try {
                localStorage[t] = n
            } catch (r) {
            }
        }

        function c(n) {
            var c = location.href, u = n + "@" + c;
            if (!a(u)) {
                i(u, 1);
                var f = {};
                f.type = 200, f.p_url = c.split("#")[0], f.r_url = n, f.ver = o, f.pvid = "0000000000000001", f.uid = 1, f.way = 1, f.p = e;
                var l = location.hash;
                l && (f.p_hash = l);
                var s = document.referrer;
                s && (f.refer = s);
                var v = r + "/log?" + t.EncodeQuery(f);
                (new Image).src = v
            }
        }

        function u() {
            var n = s.getEntries();
            t.ArrayEach(n, function (n) {
                var r = n.name;
                if (r && !(r in v)) {
                    v[r] = 1;
                    var e = t.GetHostNameFromUrl(r), a = t.GetRootDomain(e), o = t.StrHash(a);
                    o in d && c(r)
                }
            })
        }

        function f() {
            try {
                u()
            } catch (t) {
            }
        }

        function l() {
            s && s.getEntries && setInterval(f, 5e3)
        }

        var s = window.performance, v = {};
        n.Init = l
    }(v || (v = {}));
    var h;
    !function (t) {
        function n() {
            var t = /cedexis-cn\.gccdn\.net\/node\.js|cedexispub\.cdnetworks\.net\/node\.js/, n = window.HTMLScriptElement;
            n && l.Setter(n.prototype, "src", function (n) {
                var r = n[0];
                return t.test(r) ? l.DISCARD : void 0
            })
        }

        function r() {
            var t = location.hostname;
            /lazada/.test(t) && /zh/.test(navigator.language) && n()
        }

        t.Init = r
    }(h || (h = {}));
    var m;
    !function (t) {
        function n() {
            s.Init(), f.Init(), v.Init(), h.Init()
        }

        function r() {
            document[a] || (document[a] = !0, e && n())
        }

        var a = "__adblk__";
        r()
    }(m || (m = {}))
}();