<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>

<%--知识点：
 (1)如果有登录，session会有绑定
 (2)ajax动态加载与struts2-if标签的配合使用
 (3)利用jsp页面发送html文档片段
 --%>
<s:if test="#session['session.com.imvcc.action.user.authorize']==null">
[&nbsp;<a href="../authorize/toLogin.action" class="b">登录</a>|<a
			href="../authorize/toRegist.action" class="b">注册</a>&nbsp;]
</s:if>
<s:else>
<b>您好 <font color="orange"><s:property value="#session['session.com.imvcc.action.user.authorize'].nickname"/></font>，欢迎光临当当网</b>
		[&nbsp;<a href="../authorize/logout.action" class="b">登出</a>&nbsp;]
</s:else>