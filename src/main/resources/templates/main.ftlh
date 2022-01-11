<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="main">
                <input id="filterInput" type="text" name="filter" value="${filter!""}" placeholder="Search"/>
                <button class="btn btn-primary ml-2" type="submit">Найти</button>
            </form>
        </div>
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
    <div class="card-columns">

        <#list messages as message>
            <div class="card my-3">
                <#if message.fileName??>
                    <img class="card-img-top" src="/img/${message.fileName}">
                </#if>
                <div class="m-2">
                    <span>${message.text}</span>
                    <i>${message.tag}</i>
                </div>
                <div class="card-footer text-muted">
                    ${message.authorUserName}
                </div>
            </div>
        <#else>
            No messages
        </#list>
    </div>

    <script type="text/javascript" src="/js/util.js"></script>
</@c.page>