<#import "common.ftl" as c>

<@c.page>
<td>Cписок пользователей и используемых ими форм за последний час</td>
<table>
    <thead>
    <tr>
        <th>Пользователь</th>
        <th>Форма</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <#list formsUsedInLastHour as el>
    <tr>
        <td>${el.getUserId()}</td>
        <td>${el.getFormId()}</td>
    </tr>
    </#list>
    </tbody>
</table>
</@c.page>