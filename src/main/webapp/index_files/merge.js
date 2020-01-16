function FastClick(e, t) {
    function n(e, t) {
        return function () {
            return e.apply(t, arguments)
        }
    }

    var i, t = t || {};
    if (this.trackingClick = !1, this.trackingClickStart = 0, this.targetElement = null, this.lastTouchIdentifier = this.touchStartY = this.touchStartX = 0, this.touchBoundary = t.touchBoundary || 10, this.layer = e, this.tapDelay = t.tapDelay || 200, !FastClick.notNeeded(e)) {
        for (var r = "onMouse onClick onTouchStart onTouchMove onTouchEnd onTouchCancel".split(" "), o = 0, a = r.length; a > o; o++)this[r[o]] = n(this[r[o]], this);
        deviceIsAndroid && (e.addEventListener("mouseover", this.onMouse, !0), e.addEventListener("mousedown", this.onMouse, !0), e.addEventListener("mouseup", this.onMouse, !0)), e.addEventListener("click", this.onClick, !0), e.addEventListener("touchstart", this.onTouchStart, !1), e.addEventListener("touchmove", this.onTouchMove, !1), e.addEventListener("touchend", this.onTouchEnd, !1), e.addEventListener("touchcancel", this.onTouchCancel, !1), Event.prototype.stopImmediatePropagation || (e.removeEventListener = function (t, n, i) {
            var r = Node.prototype.removeEventListener;
            "click" === t ? r.call(e, t, n.hijacked || n, i) : r.call(e, t, n, i)
        }, e.addEventListener = function (t, n, i) {
            var r = Node.prototype.addEventListener;
            "click" === t ? r.call(e, t, n.hijacked || (n.hijacked = function (e) {
                    e.propagationStopped || n(e)
                }), i) : r.call(e, t, n, i)
        }), "function" == typeof e.onclick && (i = e.onclick, e.addEventListener("click", function (e) {
            i(e)
        }, !1), e.onclick = null)
    }
}
window.freeLogin = function (e) {
    var t = function (e) {
        var t = [], n = new RegExp("(^| )" + e + "=([^;]*)(;|$)");
        return (t = document.cookie.match(n)) ? t[2] : null
    }, n = function (e) {
        for (var t, n, i, r, o = [-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1], a = 0, c = e.length, s = ""; a < c;) {
            do t = o[255 & e.charCodeAt(a++)]; while (a < c && t == -1);
            if (t == -1)break;
            do n = o[255 & e.charCodeAt(a++)]; while (a < c && n == -1);
            if (n == -1)break;
            s += String.fromCharCode(t << 2 | (48 & n) >> 4);
            do {
                if (i = 255 & e.charCodeAt(a++), 61 == i)return s;
                i = o[i]
            } while (a < c && i == -1);
            if (i == -1)break;
            s += String.fromCharCode((15 & n) << 4 | (60 & i) >> 2);
            do {
                if (r = 255 & e.charCodeAt(a++), 61 == r)return s;
                r = o[r]
            } while (a < c && r == -1);
            if (r == -1)break;
            s += String.fromCharCode((3 & i) << 6 | r)
        }
        return s
    }, i = function (e) {
        var i = t(e);
        return !(!i || "" == i || "undefined" == typeof i) && (i = n(i), JSON.parse(i))
    };
    return new Promise(function (t, n) {
        try {
            var r = i("SM_TOKEN")
        } catch (o) {
            return void n(o)
        }
        r && r.corpId && r.corpId == e && t(!0), t(!1)
    })
}, window.genAvatar = function (e, t) {
    var n = {
        getAvatar: function (e) {
            var t = ["#17C295", "#4DA9EB", "#F7B55E", "#F2725E", "#568AAD", "#B38979", "#8A8A8A"], n = t.length, i = function (e) {
                return null !== e.match(/^([a-zA-Z]|\s|,|\.)+$/)
            }, r = function (e) {
                var t = e || "", n = [];
                return i(t) ? (t = t.replace(/,|\./g, " ").replace(/\s+/g, " "), n = t.split(" "), 1 === n.length ? t.slice(0, 2) : n[0].slice(0, 1) + n[1].slice(0, 1)) : t.replace(/,|\.|\s+/g, "").slice(-2)
            }, o = function (e) {
                var i = 0;
                for (var r in e)i += e.charCodeAt(r);
                return t[i % n]
            }, a = r(e), c = o(e);
            return {name: a, color: c}
        }, canvas: function (e, t, n) {
            var i = document.createElement("canvas");
            i.width = i.height = t;
            var r = i.getContext("2d");
            return r.rect(0, 0, t, t), r.fillStyle = e, r.fill(), r.font = "26px 微软雅黑", r.textBaseline = "middle", r.textAlign = "center", r.fillStyle = "#fff", r.fillText(n, 50, 50), i.toDataURL()
        }
    };
    if (!e) {
        var i = n.getAvatar(t);
        e = n.canvas(i.color, 100, i.name)
    }
    return e
}, function (e) {
    if ("object" == typeof exports && "undefined" != typeof module)module.exports = e(); else if ("function" == typeof define && define.amd)define([], e); else {
        var t;
        t = "undefined" != typeof window ? window : "undefined" != typeof global ? global : "undefined" != typeof self ? self : this, t.store = e()
    }
}(function () {
    return function e(t, n, i) {
        function r(a, c) {
            if (!n[a]) {
                if (!t[a]) {
                    var s = "function" == typeof require && require;
                    if (!c && s)return s(a, !0);
                    if (o)return o(a, !0);
                    var l = new Error("Cannot find module '" + a + "'");
                    throw l.code = "MODULE_NOT_FOUND", l
                }
                var u = n[a] = {exports: {}};
                t[a][0].call(u.exports, function (e) {
                    var n = t[a][1][e];
                    return r(n ? n : e)
                }, u, u.exports, e, t, n, i)
            }
            return n[a].exports
        }

        for (var o = "function" == typeof require && require, a = 0; a < i.length; a++)r(i[a]);
        return r
    }({
        1: [function (e, t, n) {
            (function (e) {
                "use strict";
                t.exports = function () {
                    function t() {
                        try {
                            return a in r && r[a]
                        } catch (e) {
                            return !1
                        }
                    }

                    var n, i = {}, r = "undefined" != typeof window ? window : e, o = r.document, a = "localStorage", c = "script";
                    if (i.disabled = !1, i.version = "1.3.20", i.set = function (e, t) {
                        }, i.get = function (e, t) {
                        }, i.has = function (e) {
                            return void 0 !== i.get(e)
                        }, i.remove = function (e) {
                        }, i.clear = function () {
                        }, i.transact = function (e, t, n) {
                            null == n && (n = t, t = null), null == t && (t = {});
                            var r = i.get(e, t);
                            n(r), i.set(e, r)
                        }, i.getAll = function () {
                        }, i.forEach = function () {
                        }, i.serialize = function (e) {
                            return JSON.stringify(e)
                        }, i.deserialize = function (e) {
                            if ("string" == typeof e)try {
                                return JSON.parse(e)
                            } catch (t) {
                                return e || void 0
                            }
                        }, t())n = r[a], i.set = function (e, t) {
                        return void 0 === t ? i.remove(e) : (n.setItem(e, i.serialize(t)), t)
                    }, i.get = function (e, t) {
                        var r = i.deserialize(n.getItem(e));
                        return void 0 === r ? t : r
                    }, i.remove = function (e) {
                        n.removeItem(e)
                    }, i.clear = function () {
                        n.clear()
                    }, i.getAll = function () {
                        var e = {};
                        return i.forEach(function (t, n) {
                            e[t] = n
                        }), e
                    }, i.forEach = function (e) {
                        for (var t = 0; t < n.length; t++) {
                            var r = n.key(t);
                            e(r, i.get(r))
                        }
                    }; else if (o && o.documentElement.addBehavior) {
                        var s, l;
                        try {
                            l = new ActiveXObject("htmlfile"), l.open(), l.write("<" + c + ">document.w=window</" + c + '><iframe src="/favicon.ico"></iframe>'), l.close(), s = l.w.frames[0].document, n = s.createElement("div")
                        } catch (u) {
                            n = o.createElement("div"), s = o.body
                        }
                        var d = function (e) {
                            return function () {
                                var t = Array.prototype.slice.call(arguments, 0);
                                t.unshift(n), s.appendChild(n), n.addBehavior("#default#userData"), n.load(a);
                                var r = e.apply(i, t);
                                return s.removeChild(n), r
                            }
                        }, f = new RegExp("[!\"#$%&'()*+,/\\\\:;<=>?@[\\]^`{|}~]", "g"), h = function (e) {
                            return e.replace(/^d/, "___$&").replace(f, "___")
                        };
                        i.set = d(function (e, t, n) {
                            return t = h(t), void 0 === n ? i.remove(t) : (e.setAttribute(t, i.serialize(n)), e.save(a), n)
                        }), i.get = d(function (e, t, n) {
                            t = h(t);
                            var r = i.deserialize(e.getAttribute(t));
                            return void 0 === r ? n : r
                        }), i.remove = d(function (e, t) {
                            t = h(t), e.removeAttribute(t), e.save(a)
                        }), i.clear = d(function (e) {
                            var t = e.XMLDocument.documentElement.attributes;
                            e.load(a);
                            for (var n = t.length - 1; n >= 0; n--)e.removeAttribute(t[n].name);
                            e.save(a)
                        }), i.getAll = function (e) {
                            var t = {};
                            return i.forEach(function (e, n) {
                                t[e] = n
                            }), t
                        }, i.forEach = d(function (e, t) {
                            for (var n, r = e.XMLDocument.documentElement.attributes, o = 0; n = r[o]; ++o)t(n.name, i.deserialize(e.getAttribute(n.name)))
                        })
                    }
                    try {
                        var v = "__storejs__";
                        i.set(v, v), i.get(v) != v && (i.disabled = !0), i.remove(v)
                    } catch (u) {
                        i.disabled = !0
                    }
                    return i.enabled = !i.disabled, i
                }()
            }).call(this, "undefined" != typeof global ? global : "undefined" != typeof self ? self : "undefined" != typeof window ? window : {})
        }, {}]
    }, {}, [1])(1)
});
var deviceIsAndroid = 0 < navigator.userAgent.indexOf("Android"), deviceIsIOS = /iP(ad|hone|od)/.test(navigator.userAgent), deviceIsIOS4 = deviceIsIOS && /OS 4_\d(_\d)?/.test(navigator.userAgent), deviceIsIOSWithBadTarget = deviceIsIOS && /OS ([6-9]|\d{2})_\d/.test(navigator.userAgent);
FastClick.prototype.needsClick = function (e) {
    switch (e.nodeName.toLowerCase()) {
        case"button":
        case"select":
        case"textarea":
            if (e.disabled)return !0;
            break;
        case"input":
            if (deviceIsIOS && "file" === e.type || e.disabled)return !0;
            break;
        case"label":
        case"video":
            return !0
    }
    return /\bneedsclick\b/.test(e.className)
}, FastClick.prototype.needsFocus = function (e) {
    switch (e.nodeName.toLowerCase()) {
        case"textarea":
            return !0;
        case"select":
            return !deviceIsAndroid;
        case"input":
            switch (e.type) {
                case"button":
                case"checkbox":
                case"file":
                case"image":
                case"radio":
                case"submit":
                    return !1
            }
            return !e.disabled && !e.readOnly;
        default:
            return /\bneedsfocus\b/.test(e.className)
    }
}, FastClick.prototype.sendClick = function (e, t) {
    var n, i;
    document.activeElement && document.activeElement !== e && document.activeElement.blur(), i = t.changedTouches[0], n = document.createEvent("MouseEvents"), n.initMouseEvent(this.determineEventType(e), !0, !0, window, 1, i.screenX, i.screenY, i.clientX, i.clientY, !1, !1, !1, !1, 0, null), n.forwardedTouchEvent = !0, e.dispatchEvent(n)
}, FastClick.prototype.determineEventType = function (e) {
    return deviceIsAndroid && "select" === e.tagName.toLowerCase() ? "mousedown" : "click"
}, FastClick.prototype.focus = function (e) {
    var t;
    deviceIsIOS && e.setSelectionRange && 0 !== e.type.indexOf("date") && "time" !== e.type ? (t = e.value.length, e.setSelectionRange(t, t)) : e.focus()
}, FastClick.prototype.updateScrollParent = function (e) {
    var t, n;
    if (t = e.fastClickScrollParent, !t || !t.contains(e)) {
        n = e;
        do {
            if (n.scrollHeight > n.offsetHeight) {
                t = n, e.fastClickScrollParent = n;
                break
            }
            n = n.parentElement
        } while (n)
    }
    t && (t.fastClickLastScrollTop = t.scrollTop)
}, FastClick.prototype.getTargetElementFromEventTarget = function (e) {
    return e.nodeType === Node.TEXT_NODE ? e.parentNode : e
}, FastClick.prototype.onTouchStart = function (e) {
    var t, n, i;
    if (1 < e.targetTouches.length)return !0;
    if (t = this.getTargetElementFromEventTarget(e.target), n = e.targetTouches[0], deviceIsIOS) {
        if (i = window.getSelection(), i.rangeCount && !i.isCollapsed)return !0;
        if (!deviceIsIOS4) {
            if (n.identifier === this.lastTouchIdentifier)return e.preventDefault(), !1;
            this.lastTouchIdentifier = n.identifier, this.updateScrollParent(t)
        }
    }
    return this.trackingClick = !0, this.trackingClickStart = e.timeStamp, this.targetElement = t, this.touchStartX = n.pageX, this.touchStartY = n.pageY, e.timeStamp - this.lastClickTime < this.tapDelay && e.preventDefault(), !0
}, FastClick.prototype.touchHasMoved = function (e) {
    var e = e.changedTouches[0], t = this.touchBoundary;
    return Math.abs(e.pageX - this.touchStartX) > t || Math.abs(e.pageY - this.touchStartY) > t
}, FastClick.prototype.onTouchMove = function (e) {
    return !this.trackingClick || ((this.targetElement !== this.getTargetElementFromEventTarget(e.target) || this.touchHasMoved(e)) && (this.trackingClick = !1, this.targetElement = null), !0)
}, FastClick.prototype.findControl = function (e) {
    return void 0 !== e.control ? e.control : e.htmlFor ? document.getElementById(e.htmlFor) : e.querySelector("button, input:not([type=hidden]), keygen, meter, output, progress, select, textarea")
}, FastClick.prototype.onTouchEnd = function (e) {
    var t, n, i = this.targetElement;
    if (!this.trackingClick)return !0;
    if (e.timeStamp - this.lastClickTime < this.tapDelay)return this.cancelNextClick = !0;
    if (this.cancelNextClick = !1, this.lastClickTime = e.timeStamp, t = this.trackingClickStart, this.trackingClick = !1, this.trackingClickStart = 0, deviceIsIOSWithBadTarget && (n = e.changedTouches[0], i = document.elementFromPoint(n.pageX - window.pageXOffset, n.pageY - window.pageYOffset) || i, i.fastClickScrollParent = this.targetElement.fastClickScrollParent), n = i.tagName.toLowerCase(), "label" === n) {
        if (t = this.findControl(i)) {
            if (this.focus(i), deviceIsAndroid)return !1;
            i = t
        }
    } else if (this.needsFocus(i))return 100 < e.timeStamp - t || deviceIsIOS && window.top !== window && "input" === n ? (this.targetElement = null, !1) : (this.focus(i), this.sendClick(i, e), deviceIsIOS && "select" === n || (this.targetElement = null, e.preventDefault()), !1);
    return !(!deviceIsIOS || deviceIsIOS4 || !(t = i.fastClickScrollParent) || t.fastClickLastScrollTop === t.scrollTop) || (this.needsClick(i) || (e.preventDefault(), this.sendClick(i, e)), !1)
}, FastClick.prototype.onTouchCancel = function () {
    this.trackingClick = !1, this.targetElement = null
}, FastClick.prototype.onMouse = function (e) {
    return !(this.targetElement && !e.forwardedTouchEvent && e.cancelable && (!this.needsClick(this.targetElement) || this.cancelNextClick)) || (e.stopImmediatePropagation ? e.stopImmediatePropagation() : e.propagationStopped = !0, e.stopPropagation(), e.preventDefault(), !1)
}, FastClick.prototype.onClick = function (e) {
    return this.trackingClick ? (this.targetElement = null, this.trackingClick = !1, !0) : "submit" === e.target.type && 0 === e.detail || (e = this.onMouse(e), e || (this.targetElement = null), e)
}, FastClick.prototype.destroy = function () {
    var e = this.layer;
    deviceIsAndroid && (e.removeEventListener("mouseover", this.onMouse, !0), e.removeEventListener("mousedown", this.onMouse, !0), e.removeEventListener("mouseup", this.onMouse, !0)), e.removeEventListener("click", this.onClick, !0), e.removeEventListener("touchstart", this.onTouchStart, !1), e.removeEventListener("touchmove", this.onTouchMove, !1), e.removeEventListener("touchend", this.onTouchEnd, !1), e.removeEventListener("touchcancel", this.onTouchCancel, !1)
}, FastClick.notNeeded = function (e) {
    var t, n;
    if ("undefined" == typeof window.ontouchstart)return !0;
    if (n = +(/Chrome\/([0-9]+)/.exec(navigator.userAgent) || [, 0])[1]) {
        if (!deviceIsAndroid)return !0;
        if ((t = document.querySelector("meta[name=viewport]")) && (-1 !== t.content.indexOf("user-scalable=no") || n > 31 && document.documentElement.scrollWidth <= window.outerWidth))return !0
    }
    return "none" === e.style.msTouchAction
}, FastClick.attach = function (e, t) {
    return new FastClick(e, t)
}, "undefined" != typeof define && define.amd ? define("page/minisite/fastclick", [], function () {
    return FastClick
}) : "undefined" != typeof module && module.exports ? (module.exports = FastClick.attach, module.exports.FastClick = FastClick) : window.FastClick = FastClick;