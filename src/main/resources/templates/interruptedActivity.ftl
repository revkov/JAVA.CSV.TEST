<#import "common.ftl" as c>
<#import "pagination.ftl" as p />

<@c.page>
<td>Вывести список пользователей, которые начали активность на форме и не дошли до конца</td>
<table>
    <thead>
    <tr>
        <th>Пользователь</th>
        <th>Этап</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <#list page.getPageList() as el>
    <tr>
        <td>${el.getActivityMonitoring()}</td>
        <td>${el.getStep()}</td>
    </tr>
    </#list>
    </tbody>
</table>
</@c.page>
<@p.pager url page />