{code:java}
package ${class.packageName};

${class.modifiers} ${class.name}()
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
