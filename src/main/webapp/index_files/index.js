(function () {
    var e = document.getElementsByTagName("head")[0];

    function a(a) {
        var n = document.createElement("script");
        n.src = a;
        e.appendChild(n);
        return n
    }

    var n = 1;
    if (Math.random() < n) {
        document["_ab_sample"] = 1;
        a("//g.alicdn.com/secdev/adblk/index.js?v=0728")
    }
    var d = .2;
    var t;
    if (Math.random() < d) {
        t = a("//g.alicdn.com/secdev/sufei_data/3.1.1/index.js")
    } else {
        t = a("//g.alicdn.com/secdev/sufei_data/3.0.9/index.js")
    }
    t.id = "aplus-sufei";
    var i = .001;
    if (Math.random() < i) {
        setTimeout(function () {
            document["_linkstat_sample"] = i;
            a("//g.alicdn.com/secdev/linkstat/index.js?ver=52")
        }, 1e3)
    }
})();