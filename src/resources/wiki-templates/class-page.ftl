{code:java}
package ${class.packageName};

${class.modifiers} ${class.name}<#t>
<#if class.isGeneric()>
    <<#list class.typeParams as t>${t.name}<#if t?has_next>, </#if></#list>><#t>
</#if><#t>()
<#if (class.superclass)??>
  <#if class.qualifiedSuperclass != "java.lang.Object" >
extends ${class.superclass}
  </#if>
</#if>
{code}
${class.comment}

h1. Methods
<#include "class-methods-summary.ftl">

<#list class.methods as method>
h2. ${method.name}
<#include "method.ftl">
</#list>
