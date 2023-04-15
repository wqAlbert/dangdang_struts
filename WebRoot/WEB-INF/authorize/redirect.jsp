<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>用户注册 - 当当网</title>
		<link href="../css/login.css" rel="stylesheet" type="text/css" />
		<link href="../css/page_bottom.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../js/jquery-1.4.min.js"></script>
		<script type="text/javascript">
		var second=5;
		$(function() {
			$("#secondSpan").text(second+"");
			var fuc = function(){
				second-=1;
				$("#secondSpan").text(second+"");
				if(second==0) {
					location="${url }";
				}else{
					setTimeout(fuc,1000);
				}
			};
			
			fuc();//开始倒计时
			
		});
		</script>
	</head>
<body>
	<%@include file="../common/head1.jsp"%>
	<div class="login_success">
		<div class="login_bj">
			<h5>
				<img src="../images/label3.gif" width="20" height="20"
					align="absmiddle" />&nbsp;
				<s:property
					value="#session['session.com.imvcc.action.user.authorize'].nickname" />
				已经成功的
				<s:if test="type==1">
				  登录
				  </s:if>
				<s:else>
				   激活账户
				   </s:else>
			</h5>
			<h6>&nbsp;&nbsp;</h6>
			<h6>
				页面在<span id="secondSpan"></span>秒后将重新转回 <a href="${url }">${url
					}</a>。如果转入失败，请单击<a href="${url }">这里</a>。
			</h6>
		</div>
	</div>

	<%@include file="../common/foot1.jsp"%>
</body>
</html>

