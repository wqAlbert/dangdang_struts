<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>生成订单 - 当当网</title>
		<link href="../css/login.css" rel="stylesheet" type="text/css" />
		<link href="../css/page_bottom.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<%@include file="../common/head1.jsp"%>
		<div class="login_step">
			生成订单骤:
			<span class="red_bold">1.确认订单</span> &gt; 2.填写送货地址 &gt; 3.订单成功
		</div>
		<div class="fill_message">

			<table class="tab_login">
				<tr>
					<td valign="top" class="w1" style="text-align: left">
						<b>序号</b>
					</td>
					<td valign="top" class="w1" style="text-align: left">
						<b>商品名称</b>
					</td>
					<td valign="top" class="w1" style="text-align: left">
						<b>商品单价</b>
					</td>
					<td valign="top" class="w1" style="text-align: left">
						<b>商品数量</b>
					</td>
					<td valign="top" class="w1" style="text-align: left">
						<b>小计</b>
					</td>
				</tr>

				<!-- 订单开始 -->
				<s:iterator value="order.items" var="item" status="status">
					<tr>
						<td valign="top">
							<s:property value="#status.index+1" />
						</td>
						<td valign="top">
							<s:property value="#item.productName" />
						</td>
						<td valign="top">
							<s:property value="#item.dangPrice" />
						</td>
						<td valign="top">
							<s:property value="#item.productNum" />
						</td>
						<td valign="top">
							<s:property value="#item.amount" />
						</td>
					</tr>
					</s:iterator>
					
				<!-- 订单结束 -->
				<tr>
					<td valign="top" class="w1" style="text-align: left" colspan="5">
						<b>总价￥${ order.totalPrice}</b>
					</td>
				</tr>
			</table>
			<br />
			<br />
			<br />
			<div class="login_in">

				<input id="btnClientRegister" class="button_1" name="submit"
					type="button" value="取消" onclick="location='../main/bookList.action'"/>
				<input id="btnClientRegister" class="button_1" name="submit"
					type="button" value="下一步"  onclick="location='toInputAddress.action';"/>
			</div>

		</div>
		<%@include file="../common/foot1.jsp"%>
	</body>
</html>

