<%@page contentType="text/html;charset=utf-8"%>
<script type="text/javascript" src="../js/jquery-1.4.min.js"></script>
		<script type="text/javascript">
			$(function(){
				$("#logininfo").html('<img src="../images/load.gif" align="middle" width="14" heigth="14"/>正在产品加载登录信息...');
				$.get("../authorize/loginInfo.action?time="+(new Date()).getTime(),
						  function(data){
					  			$("#logininfo").html(data);
					  	}
				 ); 
			});
		</script>
<link href="../css/book_head090107.css" rel="stylesheet" type="text/css" />
<div class="head_container">
	<div class="head_welcome">
		
		<!--放在此处不会对"购物车"显示产生折行  -->
		<span id="logininfo" class="head_toutext" >
		</span>
		
		<div class="head_welcome_right">
			<span class="head_welcome_text"> </span>
			<span class="head_welcome_text"> <span class="little_n">
					| <a href="#" name="mydangdang" class="head_black12a">我的当当</a> | <a
					href="#" name="helpcenter" class="head_black12a" target="_blank">帮助</a>
					| </span> </span>
			<div class="cart gray4012">
				<a href="../cart/toCart.action">购物车</a>
			</div>
		</div>
		
	</div>
	<div class="head_head_list">
		<div class="head_head_list_left">
			<span class="head_logo"><a href="#" name="backtobook"><img
						src="../images/booksaleimg/book_logo.gif" /> </a> </span>
		</div>
		<div class="head_head_list_right">

			<div class="head_head_list_right_b">
			</div>
		</div>
	</div>
	<div class="head_search_div">

	</div>
	<div class="head_search_bg"></div>
</div>
