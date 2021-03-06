${method.modifiers} ${method.returnType.name}<#rt>
<#if method.returnType.isGeneric()>
    <<#list method.returnType.typeParams as t><#t>
        ${t.name}<#if t?has_next>, </#if><#t>
    </#list>><#t>
</#if>
${method.returnType.dimension} ${method.name}(<#rt>
<#if method.parameters?size gt 0>
    <#list method.parameters as p>
        <#if p.isPrimitive()>
            ${p.typeName} ${p.name}<#t>
        <#elseif p.isArray()>
            ${p.typeName}${p.dimension} ${p.name}<#t>
        <#elseif p.isGeneric()>
            ${p.typeName}<<#list p.paramTypes as t>${t.name}<#if t?has_next>, </#if></#list>> ${p.name}<#t>
        <#else>
            ${p.typeName} ${p.name}<#t>
        </#if>
        <#if p?has_next>, </#if><#t>
    </#list>
</#if>)
