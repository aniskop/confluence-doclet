${package.comment}

h1. Interfaces
<#if package.interfaces?size gt 0>
|| Interface || Description ||
<#list package.interfaces as i>
| ${i.name} | ${i.comment}
</#list>
<#else>
None.
</#if>

h1. Classes
<#if package.classes?size gt 0>
|| Interface || Description ||
<#list package.interfaces as c>
| ${c.name} | ${c.comment}
</#list>
<#else>
None.
</#if>
