var Genapi;
(function (Genapi) {
    var core = (function () {
        function core($request) {
            this.request = null;
            this.request = $request;
        }
        core.prototype.sendRequest = function (method, path, data) {
            if (data === void 0) { data = null; }
            return {};
        };
        core.prototype.get = function (path, data) {
            if (data === void 0) { data = null; }
            return this.sendRequest("get", path, data.toString());
        };
        core.prototype.post = function (path, data) {
            if (data === void 0) { data = null; }
            return this.sendRequest("post", path, data.toString());
        };
        core.prototype.put = function (path, data) {
            if (data === void 0) { data = null; }
            return this.sendRequest("put", path, data.toString());
        };
        core.prototype.delete = function (path, data) {
            if (data === void 0) { data = null; }
            return this.sendRequest("delete", path, data.toString());
        };
        core.instance = new core("");
        return core;
    }());
    Genapi.core = core;
})(Genapi || (Genapi = {}));
//# sourceMappingURL=genapi-core.js.map