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
|| Return type || Method || Summary ||
<#list class.methods as method>
| ${method.returnType} | ${method.name} | ${method.summary} |
</#list>

<#list class.methods as method>
h2. ${method.name}
{code:java}
${method.name}
{code}
${method.summary}
*Parameters:*
</#list>
