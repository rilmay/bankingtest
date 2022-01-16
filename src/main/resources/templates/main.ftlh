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
    <#include "parts/messageEdit.ftlh" />
    <#include "parts/messageList.ftlh" />

    <script type="text/javascript" src="/js/util.js"></script>
</@c.page>