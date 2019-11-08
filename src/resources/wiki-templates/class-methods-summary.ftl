<#include "formatting-utils.ftl">
|| Return type || Method || Summary ||
<#list class.methods as method>
| ${method.returnType.name}<#if method.returnType.isGeneric()><#t>
                                 <<#list method.returnType.typeParams as t><#t>
                                     ${t.name}<#if t?has_next>, </#if><#t>
                                 </#list>><#t>
                             </#if><#lt>${method.returnType.dimension} | ${method.name} | ${formatComment(method.summary)} |
</#list>
