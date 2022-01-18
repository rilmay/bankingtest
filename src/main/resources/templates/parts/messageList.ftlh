<#include "security.ftlh">
<div>
    Список сообщений
</div>
<div class="card-columns" id="message-list">
    <#if messages??>
        <#list messages as message>
            <div class="card my-3">
                <#if message.fileName??>
                    <img class="card-img-top" src="/img/${message.fileName}">
                </#if>
                <div class="m-2">
                    <span>${message.text}</span><br/>
                    <i>#${message.tag}</i>
                </div>
                <div class="card-footer text-muted">
                    <a href="/user-messages/${message.author.id}">${message.authorUserName}</a>
                    <#if user??>
                        <#if message.author.id == user.id>
                            <a class="btn btn-primary" href="/user-messages/${message.author.id}?message=${message.id}">Edit</a>
                        </#if>
                    </#if>
                </div>
            </div>
        <#else>
            No messages
        </#list>
    </#if>
</div>