"use strict";
var AbstractApi = (function () {
    function AbstractApi() {
        this.preActionMappings = {};
        this.postActionMappings = {};
    }
    // Pre actions
    AbstractApi.prototype.registerPreAction = function (method, action) {
        var methodString = method.toString();
        if (!(methodString in this.preActionMappings))
            this.preActionMappings[methodString] = new Array();
        this.preActionMappings[methodString].push(action);
    };
    AbstractApi.prototype.runPreActions = function (method) {
        var methodString = method.toString();
        if (!(methodString in this.preActionMappings))
            return false;
        for (var _i = 0, _a = this.preActionMappings[methodString]; _i < _a.length; _i++) {
            var action = _a[_i];
            action();
        }
    };
    // Post actions
    AbstractApi.prototype.registerPostAction = function (method, action) {
        var methodString = method.toString();
        if (!(methodString in this.postActionMappings))
            this.postActionMappings[methodString] = new Array();
        this.postActionMappings[methodString].push(action);
    };
    AbstractApi.prototype.runPostActions = function (method, data) {
        var methodString = method.toString();
        if (!(methodString in this.postActionMappings))
            return false;
        for (var _i = 0, _a = this.postActionMappings[methodString]; _i < _a.length; _i++) {
            var action = _a[_i];
            action(data);
        }
    };
    return AbstractApi;
}());
exports.AbstractApi = AbstractApi;
//# sourceMappingURL=AbstractApi.js.map