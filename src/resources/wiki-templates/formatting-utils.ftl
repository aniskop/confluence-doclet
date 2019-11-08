<#-- Format <code> HTML tag -->
<#function formatHtmlCodeTag text>
    <#assign tmp = text?replace("<code>", "{{")?replace("</code>", "}}")>
    <#return tmp>
</#function>

<#-- Format all HTML tags in a raw comment text -->
<#function formatHtmlTags text>
    <#assign tmp = formatHtmlCodeTag(text)>
    <#return tmp>
</#function>

<#-- Format all tags in a raw comment text. -->
<#function formatComment text>
    <#assign tmp = formatHtmlTags(text)>
    <#return tmp>
</#function>