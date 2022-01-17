<#import "parts/common.ftlh" as c>

<@c.page>
    <h3>${userChannel.username}</h3>
    <#if !isCurrentUser>
        <#if isSubscriber>
            <form action="/user/unsubscribe" method="post">
                <input type="hidden" name="user" value="${userChannel.id}">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button class="btn btn-info" type="submit">Unsubscribe</button>
            </form>
        <#else>
            <form action="/user/subscribe" method="post">
                <input type="hidden" name="user" value="${userChannel.id}">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button class="btn btn-info" type="submit">Subscribe</button>
            </form>
        </#if>
    </#if>

    <div class="container">
        <div class="row">
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">Subscriptions</div>
                        <div class="card-text">
                            <a href="/user/subscriptions/${userChannel.id}">${subscriptionsCount}</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">Subscribers</div>
                        <div class="card-text">
                            <a href="/user/subscribers/${userChannel.id}">${subscribersCount}</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <#if isCurrentUser>
        <#include "parts/messageEdit.ftlh" />
    </#if>
    <#include "parts/messageList.ftlh" />
</@c.page>