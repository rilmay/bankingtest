<#include "security.ftlh">
<#import "pager.ftlh" as p>

<@p.pager url page/>
<div>
    Список сообщений
</div>
<div class="card-columns" id="message-list">
    <#if page.content??>
        <#list page.content as message>
            <div class="card my-3">
                <#if message.fileName??>
                    <img class="card-img-top" src="/img/${message.fileName}">
                </#if>
                <div class="m-2">
                    <span>${message.text}</span><br/>
                    <i>#${message.tag}</i>
                </div>
                <div class="card-footer text-muted container">
                    <div class="row">
                        <a class="col align-self-center" href="/user-messages/${message.author.id}">${message.authorUserName}</a>
                        <a class="col align-self-center" href="">
                            <#if true>
                                <i class="fas fa-heart"></i>
                            <#else>
                                <i class="far fa-heart"></i>
                            </#if>
                        </a>
                        <#if user??>
                            <#if message.author.id == user.id>
                                <a class="col btn btn-primary" href="/user-messages/${message.author.id}?message=${message.id}">Edit</a>
                            </#if>
                        </#if>
                    </div>
                </div>
            </div>
        <#else>
            No messages
        </#list>
    </#if>
</div>
<@p.pager url page/>
