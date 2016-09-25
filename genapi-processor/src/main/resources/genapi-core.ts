module Genapi {

    export class core {
        private request = null;
        public static instance = new core("");

        public constructor($request) {
            this.request = $request;
        }

        protected sendRequest(method: string, path: string, data: string = null): Object {
            return {}
        }

        public get(path: string, data: Object = null): Object {
            return this.sendRequest("get", path, data.toString())
        }

        public post(path: string, data: Object = null): Object {
            return this.sendRequest("post", path, data.toString())
        }

        public put(path: string, data: Object = null): Object {
            return this.sendRequest("put", path, data.toString())
        }

        public delete(path: string, data: Object = null): Object {
            return this.sendRequest("delete", path, data.toString())
        }
    }
}