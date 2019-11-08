{code:java}
<#include "method-signature.ftl">
{code}
${method.comment}

<#if method.parameters?size gt 0>
*Parameters*
    <#list method.parameters as p>
        * {{${p.name}}}<#if p.comment != ""> -- ${p.comment}</#if><#lt>
    </#list>
</#if>

<#if method.returnType.name != "void" && method.returnComment != "">
*Returns*
${method.returnComment}
</#if>
