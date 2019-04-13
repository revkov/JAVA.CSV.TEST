<#import "common.ftl" as c>

<@c.page>
<td>Составить ТОП – 5 самых используемых форм.</td>
<table>
    <thead>
    <tr>
        <th>Форма</th>
        <th>Количество использований</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <#list frequentlyUsedForms as el>
    <tr>
        <td>${el.getFormid()}</td>
        <td>${el.getCount()}</td>
    </tr>
    </#list>
    </tbody>
</table>
</@c.page>