!function e(t, n, r) {
    function a(i, s) {
        if (!n[i]) {
            if (!t[i]) {
                var u = "function" == typeof require && require;
                if (!s && u)return u(i, !0);
                if (o)return o(i, !0);
                throw new Error("Cannot find module '" + i + "'")
            }
            var l = n[i] = {exports: {}};
            t[i][0].call(l.exports, function (e) {
                var n = t[i][1][e];
                return a(n ? n : e)
            }, l, l.exports, e, t, n, r)
        }
        return n[i].exports
    }

    for (var o = "function" == typeof require && require, i = 0; i < r.length; i++)a(r[i]);
    return a
}({
    1: [function (e, t, n) {
        "use strict";
        function r(e, t) {
            return e + Math.floor(Math.random() * (t - e + 1))
        }

        function a(e, t) {
            var n = !1;
            try {
                var a = "0aAbBc1CdDeE2fFgGh3HiIjJ4kKlLm5MnNoO6pPqQr7RsStT8uUvVw9WxXyY+zZ", o = f.getCookie("cna") || "";
                if (o) {
                    var i = o.charAt(0), s = a.indexOf(i) / a.length;
                    n = e / t >= s
                } else n = r(1, t) <= e
            } catch (u) {
                n = r(1, t) <= e
            }
            return n
        }

        function o(e, t) {
            var n;
            for (n in t)t.hasOwnProperty(n) && (e[n] = t[n]);
            return e
        }

        function i(e, t) {
            return function (n) {
                return e.call(null, o(t, n || {}))
            }
        }

        function s(e) {
            if (e)try {
                var t = l.getElementsByTagName("script")[0], n = l.createElement("script");
                n.appendChild(l.createTextNode(e)), t.parentNode.insertBefore(n, t)
            } catch (r) {
                (c.execScript || function (e) {
                    c.eval.call(c, e)
                })(e)
            }
        }

        function u(e, t) {
            try {
                var n = [], r = h.get("APLUS_LS_KEY");
                if (r) {
                    var a = JSON.parse(r) || [];
                    if (a && a.length > 0)for (var o = new RegExp("^" + t), i = 0; i < a.length; i++)o.test(a[i]) ? n.push(a[i]) : h.remove(a[i])
                }
                n.push(e), h.set("APLUS_LS_KEY", JSON.stringify(n))
            } catch (s) {
            }
        }

        var l = document, c = window, p = navigator.userAgent, f = e("./util"), g = e("./hash"), v = function () {
        }, d = function (e) {
            return "function" == typeof e
        }, h = {
            set: function (e, t) {
                try {
                    return localStorage.setItem(e, t), !0
                } catch (n) {
                    return !1
                }
            }, get: function (e) {
                return localStorage.getItem(e)
            }, test: function () {
                var e = "grey_test_key";
                try {
                    return localStorage.setItem(e, 1), localStorage.removeItem(e), !0
                } catch (t) {
                    return !1
                }
            }, remove: function (e) {
                localStorage.removeItem(e)
            }
        }, _ = {base: 1e4}, m = {_config: _}, y = c.XDomainRequest, b = c.XMLHttpRequest && "withCredentials" in new c.XMLHttpRequest, j = function (e, t, n, r) {
            if ("function" != typeof r && (r = function () {
                }), !n && h.test() && (b || y)) {
                var a = t + g.hash(e), o = h.get(a);
                if (o)try {
                    s(o), r({from: "local"})
                } catch (i) {
                    f.addScript(e, r)
                } else f.request(e, p, function (e) {
                    h.set(a, e), u(a, t), s(e), r({from: "cors"})
                }, function () {
                    f.addScript(e, r)
                })
            } else f.addScript(e, r)
        };
        m.load = function (e) {
            e = o({
                APLUS_LS_KEY: "", isLoadDevVersion: function () {
                }, dev: "", stable: "", grey: "", base: _.base
            }, e);
            var t, n = {};
            return e.ratio >= e.base || a(e.ratio, e.base) ? (t = e.grey, n.type = "grey") : (t = e.stable, n.type = "stable"), d(e.isLoadDevVersion) && e.isLoadDevVersion() && (t = e.dev, n.type = "dev"), n.url = t, d(e.before) && e.before(n), e.after = d(e.after) ? i(e.after, n) : v, j(t, e.APLUS_LS_KEY, e.isDebug, e.after), this
        }, m.config = function (e) {
            return o(_, e || {}), this
        }, t.exports = m
    }, {"./hash": 2, "./util": 3}],
    2: [function (e, t, n) {
        "use strict";
        n.hash = function (e) {
            var t, n, r = 1315423911;
            for (t = e.length - 1; t >= 0; t--)n = e.charCodeAt(t), r ^= (r << 5) + n + (r >> 2);
            return (2147483647 & r).toString(16)
        }
    }, {}],
    3: [function (e, t, n) {
        "use strict";
        function r(e, t) {
            return e && e.getAttribute ? e.getAttribute(t) || "" : ""
        }

        function a(e) {
            return i = i || document.getElementsByTagName("head")[0], s && !e ? s : i ? s = i.getElementsByTagName("meta") : []
        }

        function o(e) {
            var t, n, o, i = a(), s = i.length;
            for (t = 0; s > t; t++)n = i[t], r(n, "name") === e && (o = r(n, "content"));
            return o || ""
        }

        var i, s;
        n.tryToGetAttribute = r, n.getMetaTags = a, n.getMetaCnt = o, n.indexof = function (e, t) {
            for (var n = 0; n < e.length; n++)if (e[n] === t)return n;
            return -1
        };
        var u = function (e, t) {
            return e += "", e.length < t && (e = "0" + e), e
        };
        n.getDateMin = function () {
            var e = new Date, t = "";
            return t += e.getFullYear(), t += u(e.getMonth() + 1, 2), t += u(e.getDate(), 2), t += u(e.getHours(), 2), t += u(e.getMinutes(), 2)
        }, n.isMobile = function (e) {
            var t = /iPhone|iPad|iPod/i.test(e), n = /Android/i.test(e), r = /Windows Phone/i.test(e) || /IEMobile/i.test(e) || /WPDesktop/i.test(e), a = /BlackBerry/i.test(e), o = /Opera Mini/i.test(e);
            return t || n || r || a || o
        };
        var l = function (e, t) {
            var n = document, r = n.getElementsByTagName("script")[0], a = n.getElementsByTagName("head")[0], o = n.createElement("script");
            o.type = "text/javascript", o.async = !0, o.src = e, r ? r.parentNode.insertBefore(o, r) : a && a.appendChild(o), "function" == typeof t && t({from: "script"})
        };
        n.addScript = l, n.loopAddScript = function (e, t) {
            try {
                if (t && t instanceof Array) {
                    var n = 0, r = function (a) {
                        a && l(e + "/" + a, function () {
                            r(t[++n])
                        })
                    };
                    r(t[n])
                }
            } catch (a) {
            }
        }, n.getCdnpath = function () {
            var e = document, t = e.getElementById("beacon-aplus") || e.getElementById("tb-beacon-aplus"), n = "//g.alicdn.com", r = ["//assets.alicdn.com/g", "//g-assets.daily.taobao.net"];
            if (t)for (var a = 0; a < r.length; a++) {
                var o = new RegExp(r[a]);
                if (o.test(t.src)) {
                    n = r[a];
                    break
                }
            }
            return n
        }, n.getCookie = function (e) {
            var t = document, n = t.cookie.match(new RegExp("(?:^|;)\\s*" + e + "=([^;]+)"));
            return n ? n[1] : ""
        };
        var c = {base: 1e4, timeout: 1e4};
        n.request = function (e, t, n, r) {
            if (/blitz/i.test(t))return void r();
            var a, o = "GET", i = function () {
                a.responseText ? n(a.responseText) : r()
            }, s = window.XMLHttpRequest && "withCredentials" in new XMLHttpRequest;
            s ? (a = new XMLHttpRequest, a.open(o, e, !0)) : (a = new XDomainRequest, a.open(o, e)), a.timeout = c.timeout, a.onload = i, a.onerror = r, a.ontimeout = r, a.send()
        }
    }, {}],
    4: [function (e, t, n) {
        "use strict";
        !function () {
            var t = window, n = "g_aplus_grey_launched";
            if (!t[n]) {
                t[n] = 1;
                var r = t.goldlog || (t.goldlog = {}), a = !1;
                try {
                    if (a = /aplusDebug=true/.test(decodeURIComponent(location.search)))window.localStorage.setItem("aplusDebug", "true"); else {
                        var o = "true" === window.localStorage.getItem("aplusDebug");
                        o && (a = o)
                    }
                } catch (i) {
                }
                var s = e("../grey/util"), u = e("./for_aplus_fns"), l = {
                    "aplus_o.js": {
                        stable_version: {value: "7.6.7"},
                        grey_version: {value: "7.6.9"},
                        dev_version: {}
                    },
                    "aplus_std.js": {stable_version: {value: "7.6.7"}, grey_version: {value: "7.6.9"}, dev_version: {}},
                    "aplus_int.js": {stable_version: {value: "7.6.7"}, grey_version: {value: "7.6.9"}, dev_version: {}},
                    "aplus_wap.js": {stable_version: {value: "7.6.7"}, grey_version: {value: "7.6.9"}, dev_version: {}}
                }, c = "APLUS_S_CORE_0.16.8_20170912203252_", p = e("../grey/grey_publish"), f = location.protocol;
                0 !== f.indexOf("http") && (f = "http:");
                var g = s.getCdnpath();
                r.getCdnPath = s.getCdnpath;
                var v = f + g + "/alilog", d = u.getAplusVersion("aplus_std.js"), h = 1e4, _ = [], m = {"aplus_std.js": [/^xxx\.ju\.taobao\.com/i]}, y = function () {
                    var e, t = l[d] || {}, n = t.dev_version || {};
                    if (d && n.value) {
                        var r, a = m[d] || [];
                        for (r = 0; r < a.length; r++)if ((location.hostname + location.pathname).match(a[r])) {
                            e = !0;
                            break
                        }
                    }
                    return e
                }, b = function () {
                    var e = "";
                    if (_ && _.length > 0)for (var t = s.getDateMin(), n = 0; n < _.length; n++) {
                        var r = _[n].key + "";
                        t >= r && (e = Math.floor(1e4 * _[n].value))
                    }
                    return e
                }, j = e("./utilPlugins"), w = function (e) {
                    var t, n = a ? [] : j.getFrontPlugins({
                        version: e,
                        fn: d
                    }), r = [["s", e, d].join("/")], o = a ? [] : j.getPlugins({version: e, fn: d});
                    try {
                        var i = [].concat(n, r, o);
                        t = v + "/??" + i.join(",")
                    } catch (s) {
                        t = v + "/??" + r.join(",")
                    }
                    return t
                }, S = function () {
                    var e, n = "aplus_grey_ratio";
                    "number" == typeof t[n] && (e = Math.floor(1e4 * t[n]));
                    var r = location.search.match(new RegExp("\\b" + n + "=([\\d\\.]+)"));
                    return r && (r = parseFloat(r[1]), isNaN(r) || (e = Math.floor(1e4 * r))), e
                }, E = b(), P = S();
                E && (h = E), P && (h = P), r.aplus_cplugin_ver = "0.1.3", r.record || (r.record = function (e, n, r, a) {
                    (t.goldlog_queue || (t.goldlog_queue = [])).push({
                        action: "goldlog.record",
                        arguments: [e, n, r, a]
                    })
                });
                var A = l[d];
                p.load({
                    APLUS_LS_KEY: c,
                    isDebug: a,
                    isLoadDevVersion: y,
                    dev: w(A.dev_version.value),
                    stable: w(A.stable_version.value),
                    grey: w(A.grey_version.value),
                    ratio: h,
                    before: function (e) {
                        switch (e.type) {
                            case"grey":
                                r.lver = A.grey_version.value;
                                break;
                            case"stable":
                                r.lver = A.stable_version.value;
                                break;
                            case"dev":
                                r.lver = A.dev_version.value
                        }
                        a && s.loopAddScript(v, j.getFrontPlugins({version: r.lver, fn: d}))
                    },
                    after: function () {
                        if (a) {
                            var e = 0, t = function () {
                                if (!(e >= 100)) {
                                    e++;
                                    var n = r._$ || {};
                                    window.setTimeout(function () {
                                        "complete" === n.status ? s.loopAddScript(v, j.getPlugins({
                                            version: r.lver,
                                            fn: d
                                        })) : t()
                                    }, 100)
                                }
                            };
                            t()
                        }
                    }
                })
            }
        }()
    }, {"../grey/grey_publish": 1, "../grey/util": 3, "./for_aplus_fns": 5, "./utilPlugins": 8}],
    5: [function (e, t, n) {
        "use strict";
        var r = e("./util"), a = function () {
            return ["aplus_o.js", "aplus_std.js", "aplus_int.js", "aplus_wap.js"]
        };
        n.getAplusFns = a;
        var o = function () {
            for (var e, t = [{
                version: "aplus_o.js",
                domains: [/^https?:\/\/(.*\.)?youku\.com/i, /^https?:\/\/(.*\.)?tudou\.com/i, /^https?:\/\/(.*\.)?soku\.com/i, /^https?:\/\/(.*\.)?laifeng\.com/i]
            }, {
                version: "aplus_int.js",
                domains: [/^https?:\/\/(.*\.)?scmp\.com/i, /^https?:\/\/(.*\.)?luxehomes\.com\.hk/i, /^https?:\/\/(.*\.)?ays\.com\.hk/i, /^https?:\/\/(.*\.)?cpjobs\.com/i, /^https?:\/\/(.*\.)?educationpost\.com\.hk/i, /^https?:\/\/(.*\.)?elle\.com\.hk/i, /^https?:\/\/(.*\.)?harpersbazaar\.com\.hk/i, /^https?:\/\/(.*\.)?esquirehk\.com/i]
            }], n = 0; n < t.length; n++)for (var r = t[n].domains, a = t[n].version, o = 0; o < r.length; o++)if (location.href.match(r[o]))return e = a;
            return e
        }, i = function () {
            var e = r.getMetaCnt("aplus-version");
            e && (e += ".js");
            var t = a();
            return r.indexof(t, e) > -1 ? e : null
        }, s = function () {
            try {
                for (var e = document, t = e.getElementsByTagName("script"), n = 0; n < t.length; n++) {
                    var r = t[n].getAttribute("src");
                    if (/alilog\/mlog\/aplus_\w+\.js/.test(r) || /alicdn\.com\/js\/aplus_\w+\.js/.test(r))return r
                }
                return ""
            } catch (a) {
                return ""
            }
        }, u = function () {
            var e = "";
            try {
                var t = document, n = t.getElementById("beacon-aplus") || t.getElementById("tb-beacon-aplus");
                if (n && (e = n.getAttribute("src")), e || (e = s()), e) {
                    var r = e.match(/aplus_\w+\.js/);
                    "object" == typeof r && r.length > 0 && (e = r[0])
                }
            } catch (a) {
                e = ""
            } finally {
                return e
            }
        };
        n.getAplusVersion = function (e) {
            var t;
            try {
                t = e;
                var n = u();
                n && (t = n);
                var r = o();
                r && (t = r);
                var s = i();
                s && (t = s);
                a();
                "aplus_v2.js" === t && (t = "aplus_std.js")
            } catch (l) {
                t = "aplus_std.js"
            } finally {
                return t
            }
        }
    }, {"./util": 7}],
    6: [function (e, t, n) {
        "use strict";
        var r = e("./util"), a = window.navigator.userAgent, o = /WindVane/i.test(a), i = /AliBaichuan/i.test(a), s = function (e) {
            return o && !window.WindVane && "aplus_o.js" !== e.fn
        }, u = function (e) {
            return (i || o && !window.WindVane) && "aplus_o.js" === e.fn
        }, l = function (e) {
            return "aplus_o.js" === e.fn
        }, c = function () {
            return /_a_ig_v=@aplus/.test(location.search)
        }, p = function (e) {
            return !1
        }, f = function (e) {
            return "aplus_o.js" !== e.fn && "aplus_wap.js" !== e.fn && !r.getCookie("cna")
        }, g = function (e) {
            return "aplus_std.js" === e.fn || "aplus_wap.js" === e.fn
        }, v = function (e) {
            return "aplus_o" !== e.fn
        };
        n.getFrontPlugins = function (e) {
            var t = "s/" + e.version + "/plugin", n = goldlog.aplus_cplugin_ver;
            return [{
                name: "aplus_windvane2",
                enable: s(e),
                path: [t, "aplus_windvane2.js"].join("/")
            }, {name: "aplus_bcbridge", enable: u(e), path: [t, "aplus_bcbridge.js"].join("/")}, {
                name: "aplus_client",
                enable: !0,
                path: [t, "aplus_client.js"].join("/")
            }, {
                name: "aplus_cplugin_monitor",
                enable: !0,
                path: ["aplus_cplugin", n, "monitor.js"].join("/")
            }, {
                name: "aplus_cplugin_lsparams_p",
                enable: p(e),
                path: ["aplus_cplugin", n, "lsparams_p.js"].join("/")
            }, {name: "aplus_cplugin_lscna_p", enable: f(e), path: ["aplus_cplugin", n, "lscna_p.js"].join("/")}]
        }, n.getPlugins = function (e) {
            var t = "s/" + e.version + "/plugin", n = goldlog.aplus_cplugin_ver;
            return [{
                name: "aplus_urchin2",
                enable: l(e),
                path: [t, "aplus_urchin2.js"].join("/")
            }, {name: "aplus_plugin_guide", enable: c(e), path: "aplus_plugin_guide/index.js"}, {
                name: "aplus_cplugin_aol",
                enable: g(e),
                path: ["aplus_cplugin", n, "aol.js"].join("/")
            }, {name: "aplus_spmact", enable: v(e), path: [t, "aplus_spmact.js"].join("/")}]
        }
    }, {"./util": 7}],
    7: [function (e, t, n) {
        t.exports = e(3)
    }, {}],
    8: [function (e, t, n) {
        "use strict";
        var r, a = e("./plugins"), o = document;
        try {
            r = o.getElementById("beacon-aplus") || o.getElementById("tb-beacon-aplus")
        } catch (i) {
        }
        var s = function (e) {
            var t = [];
            try {
                if (r) {
                    var n = r.getAttribute(e || t);
                    t = n.split(",")
                }
            } catch (a) {
                t = []
            } finally {
                return t
            }
        }, u = function (e) {
            var t = [];
            if (e)for (var n = 0; n < e.length; n++) {
                var r = e[n].enable, a = e[n].path;
                r === !0 ? t.push(a) : "function" == typeof r && r() && t.push(a)
            }
            return t
        };
        n.getPlugins = function (e) {
            var t = a.getPlugins(e);
            return [].concat(u(t), s("plugins"))
        }, n.getFrontPlugins = function (e) {
            var t = a.getFrontPlugins(e);
            return [].concat(u(t), s("frontPlugins"))
        }
    }, {"./plugins": 6}]
}, {}, [4]);