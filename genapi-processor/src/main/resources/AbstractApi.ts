export abstract class AbstractApi {
    protected preActionMappings: {[key: string]: Array<Function>} = {};
    protected postActionMappings: {[key: string]: Array<Function>} = {};

    // Pre actions
    public registerPreAction(method: Function, action: Function) {
        var methodString = method.toString();

        if(!(methodString in this.preActionMappings))
            this.preActionMappings[methodString] = new Array<Function>();

        this.preActionMappings[methodString].push(action);
    }

    protected runPreActions(method: Function) {
        var methodString = method.toString();

        if(!(methodString in this.preActionMappings))
            return false;

        for (var action of this.preActionMappings[methodString]) {
            action();
        }
    }

    // Post actions

    public registerPostAction(method: Function, action: Function) {
        var methodString = method.toString();

        if(!(methodString in this.postActionMappings))
            this.postActionMappings[methodString] = new Array<Function>();

        this.postActionMappings[methodString].push(action);
    }

    protected runPostActions(method: Function, data: Object) {
        var methodString = method.toString();

        if(!(methodString in this.postActionMappings))
            return false;

        for (var action of this.postActionMappings[methodString]) {
            action(data);
        }
    }
}