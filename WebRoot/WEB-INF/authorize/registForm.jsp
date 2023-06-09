<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>用户注册 - 当当网</title>
		<link href="../css/login.css" rel="stylesheet" type="text/css" />
		<link href="../css/page_bottom.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../js/jquery-1.4.min.js"></script>
		
		<!-- 自定义javascript代码 -->
		<script type="text/javascript" src="../js/verify.js"></script>
		<script type="text/javascript" src="../js/registFormValidate.js"></script>
  </head>
  
  <body>
		<%@include file="../common/head1.jsp"%>
		<div class="login_step">
			注册步骤:
			<span class="red_bold">1.填写信息</span> &gt; 2.验证邮箱 &gt; 3.注册成功
		</div>
		<div class="fill_message">
			<s:form name="ctl00" method="post" action="regist" id="f" theme="simple">
			<s:token />
				<h2>
					以下均为必填项
				</h2>
				<table class="tab_login" >
					<tr>
						<td valign="top" class="w1">
							请填写您的Email地址：
						</td>
						<td>
							<s:textfield name="user.email" id="email" cssClass="text_input"></s:textfield>
							<div class="text_left" id="emailValidMsg">
								<p>
									请填写有效的Email地址，在下一步中您将用此邮箱接收验证邮件。
								</p>
									<span id="emailInfo" style="color:red">
									<s:property value="errors['user.email']"/>&nbsp;
									</span>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							设置您在当当网的昵称：
						</td>
						<td>
							<s:textfield name="user.nickname" id="txtNickName"  cssClass="text_input"></s:textfield>
							<div class="text_left" id="nickNameValidMsg">
								<p>
									您的昵称可以由小写英文字母、中文、数字组成，
								</p>
								<p>
									长度4－20个字符，一个汉字为两个字符。
								</p>
								<span id="nameInfo" style="color:red">
								<s:property value="errors['user.nickname']"/>&nbsp;
								</span>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							设置密码：
						</td>
						<td>
							<s:password name="user.password"  id="txtPassword" cssClass="text_input"></s:password>
							<div class="text_left" id="passwordValidMsg">
								<p>
									您的密码可以由大小写英文字母、数字组成，长度6－20位。
								</p>
								<span id="passwordInfo" style="color:red">
								<s:property value="errors['user.password']"/>&nbsp;
								</span>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							再次输入您设置的密码：
						</td>
						<td>
							<s:password name="password1" id="txtRepeatPass" cssClass="text_input"></s:password>
							<div class="text_left" id="repeatPassValidMsg">
							<span id="password1Info" style="color:red">
							<s:property value="errors['password1']"/>&nbsp;
							</span>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							验证码：
						</td>
						<td>
							<img class="yzm_img" align="middle" id="validateImage" src="imageCode.action" width="150" height="40" onclick="this.src='imageCode.action?time-'+(new Date()).getTime();"/>
							<s:textfield name="validateCode"  id="validateCode" cssClass="yzm_input"></s:textfield>
							<div class="text_left t1">
								<p class="t1">
									<span id="vcodeValidMsg">请输入图片中的四个字母。</span>
									<a href="javascript:" onclick="document.getElementById('validateImage').src='imageCode.action?time-'+(new Date()).getTime();">看不清，再换一张</a>
								</p>
								<span id="numberInfo" style="color:red">
									<s:property value="errors['validateCode']"/>&nbsp;
								</span>
							</div>
						</td>
					</tr>
				</table>

				<div class="login_in">
					<s:submit id="btnClientRegister" cssClass="button_1" value="注册"></s:submit>
				</div>
			</s:form>
		</div>
		<%@include file="../common/foot1.jsp"%>
	</body>
</html>

