///<reference path="genapi-core.ts"/>
import {AbstractApi} from "./AbstractApi";

// let user create pre and post actions
// if pre action returns false, do not continue.
// send result to post action

class UserServiceApi extends AbstractApi {

    public getName(data: Object) {
        super.runPreActions(this.getName);
        var result = Genapi.core.instance.get("/name", data);
        super.runPostActions(this.getName, result);
    }

    public postName(data: Object) {
        super.runPreActions(this.postName);
        var result = Genapi.core.instance.post("/name", data);
        super.runPostActions(this.getName, result);
    }

    public deleteName(data: Object) {
        super.runPreActions(this.deleteName);
        var result = Genapi.core.instance.delete("/name", data);
        super.runPostActions(this.getName, result);
    }
}