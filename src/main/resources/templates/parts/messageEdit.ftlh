<div>
    <form method="post" enctype="multipart/form-data">
        <#if textError??>
            ${textError}
        </#if>
        <input type="text" name="text" value="<#if message??>${message.text!""}</#if>" placeholder="Введитее сообщение">
        <#if tagError??>
            ${tagError}
        </#if>
        <input type="text" value="<#if message??>${message.tag!""}</#if>"  name="tag" placeholder="Тэг">
        <input type="file" name="file">
        <button type="submit">Save</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="hidden" name="id" value="<#if message??>${message.id!""}</#if>"/>
    </form>
    </form>
</div>