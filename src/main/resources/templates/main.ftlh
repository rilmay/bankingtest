<#import "parts/common.ftlh" as c>

<@c.page>
    <div>
        <a href="/user">User list</a>
    </div>
    <div>
        <form action="/logout" method="post">
            <input type="submit" value="Sign Out">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>
    </div>
    <div>
        <form method="post" enctype="multipart/form-data">
            <input type="text" name="text" placeholder="Введитее сообщение">
            <input type="text" name="tag" placeholder="Тэг">
            <input type="file" name="file">
            <button type="submit">Добавить</button>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>
    </div>
    <div>
        Список сообщений
    </div>
    <form method="get" action="main">
        <input id="filterInput" type="text" name="filter" value="${filter!""}"/>
        <button type="submit">Найти</button>
    </form>
    <#list messages as message>
        <div>
            <b>${message.id}</b>
            <span>${message.text}</span>
            <i>${message.tag}</i>
            <strong>${message.authorUserName}</strong>
            <div>
                <#if message.fileName??>
                    <img src="/img/${message.fileName}">
                </#if>
            </div>
        </div>
        <#else>
        No messages
    </#list>
    <script type="text/javascript" src="/js/util.js"></script>
</@c.page>