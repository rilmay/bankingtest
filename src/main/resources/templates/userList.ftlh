<#import "parts/common.ftlh" as c>

<@c.page>
    List of all users
    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Role</th>
        </tr>
        </thead>
        <#list users as user>
            <tr>
                <td>${user.username}</td>
                <td><#list user.roles as role>${role}<#sep>,</#sep></#list></td>
                <td><a href="/user/${user.id}">edit</a></td>
            </tr>
        </#list>
    </table>
</@c.page>