<#import "parts/common.ftlh" as c>

<@c.page>
    <div>
        <form action="/logout" method="post">
            <input type="submit" value="Sign Out">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>
    </div>
    <div>
        <form method="post">
            <input type="text" name="text" placeholder="Введитее сообщение">
            <input type="text" name="tag" placeholder="Тэг">
            <button type="submit">Добавить</button>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>
    </div>
    <div>
        Список сообщений
    </div>
    <form method="post" action="filter">
        <input type="text" name="tag">
        <button type="submit">Найти</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    </form>
    <#list messages as message>
        <div>
            <b>${message.id}</b>
            <span>${message.text}</span>
            <i>${message.tag}</i>
            <strong>${message.authorUserName}</strong>
        </div>
        <#else>
        No messages
    </#list>
</@c.page>