{code:java}
<#include "method-signature.ftl">
{code}
${method.comment}

*Parameters*
<#if method.parameters?size gt 0>
    <#list method.parameters as p>
        * {{${p.name}}} -- ${p.comment}<#lt>
    </#list>
</#if>

<#if method.returnTypeName != "void" && method.returnComment != "">
*Returns*
${method.returnComment}
</#if>
