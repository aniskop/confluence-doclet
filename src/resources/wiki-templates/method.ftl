{code:java}
<#include "method-signature.ftl">
{code}
${method.description}

*Parameters:*
<#if method.parameters?size gt 0>
    <#list method.parameters as p>
        * {{${p.name}}} -- ${p.comment}<#lt>
    </#list>
</#if>

*Returns:*
