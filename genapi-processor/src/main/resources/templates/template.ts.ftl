//todo: create import statements for AbstractApi class
//todo: change data:object to typed arguments

class genericClassName extends AbstractApi {
<#list endpoints as endpoint >
    <#list methods as method >
    public ${method.name}${endpoint.pascalCaseName}(data: Object) {
        super.runPreActions(this.${method.name}${endpoint.pascalCaseName});
        var result = Genapi.core.instance.${method.name}("${endpoint.httpPath}", data);
        super.runPostActions(this.${method.name}${endpoint.pascalCaseName}, result);
    }
    </#list>
</#list>
}