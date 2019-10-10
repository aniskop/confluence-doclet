${method.modifiers} ${method.returnTypeName}${method.returnTypeDimension} ${method.name}(<#rt>
<#if method.parameters?size gt 0>
    <#list method.parameters as e>
        <#if e.isPrimitive()>
            ${e.typeName} ${e.name}<#t>
        <#elseif e.isArray()>
            ${e.typeName}${e.dimension} ${e.name}<#t>
        <#elseif e.isGeneric()>
            ${e.typeName}<<#list e.paramTypes as t>${t.name}<#if t?has_next>, </#if></#list>> ${e.name}<#t>
        <#else>
            ${e.typeName} ${e.name}<#t>
        </#if>
        <#if e?has_next>, </#if><#t>
    </#list>
</#if>)
