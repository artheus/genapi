"use strict";
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
///<reference path="genapi-core.ts"/>
var AbstractApi_1 = require("./AbstractApi");
// let user create pre and post actions
// if pre action returns false, do not continue.
// send result to post action
var UserServiceApi = (function (_super) {
    __extends(UserServiceApi, _super);
    function UserServiceApi() {
        _super.apply(this, arguments);
    }
    UserServiceApi.prototype.getName = function (data) {
        _super.prototype.runPreActions.call(this, this.getName);
        var result = Genapi.core.instance.get("/name", data);
        _super.prototype.runPostActions.call(this, this.getName, result);
    };
    UserServiceApi.prototype.postName = function (data) {
        _super.prototype.runPreActions.call(this, this.postName);
        var result = Genapi.core.instance.post("/name", data);
        _super.prototype.runPostActions.call(this, this.getName, result);
    };
    UserServiceApi.prototype.deleteName = function (data) {
        _super.prototype.runPreActions.call(this, this.deleteName);
        var result = Genapi.core.instance.delete("/name", data);
        _super.prototype.runPostActions.call(this, this.getName, result);
    };
    return UserServiceApi;
}(AbstractApi_1.AbstractApi));
//# sourceMappingURL=example.js.map