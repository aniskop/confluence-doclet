{code:java}
<#include "method-signature.ftl">
{code}
${method.comment}

<#if method.parameters?size gt 0>
*Parameters*
    <#list method.parameters as e>
        * {{${e.name}}}<#if e.comment != ""> -- ${e.comment}</#if><#lt>
    </#list>
</#if>

<#if method.returnTypeName != "void" && method.returnComment != "">
*Returns*
${method.returnComment}
</#if>
