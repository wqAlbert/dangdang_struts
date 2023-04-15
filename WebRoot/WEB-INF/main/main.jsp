<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>当当图书 – 全球最大的中文网上书店</title>
		<link href="../css/book.css" rel="stylesheet" type="text/css" />
		<link href="../css/second.css" rel="stylesheet" type="text/css" />
		<link href="../css/secBook_Show.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../js/jquery-1.4.min.js"></script>
		<script type="text/javascript">
		  $(function(){//放大镜
			var x = 10;
			var y = 20;
			$("img.dang_img").mouseover(function(e){
				
				this.myTitle = this.title;
				this.title = "";	
				var imgTitle = this.myTitle? "<br/>" + this.myTitle : "";
				var tooltip = "<div id='tooltip'><img height='200px' width='150px' src='"+ this.src +"' alt='产品预览图'/>"+imgTitle+"<\/div>"; //创建 div 元素
				$("body").append(tooltip);	//把它追加到文档中		
					 
				$("#tooltip").css({
						"top": (e.pageY+y) + "px",
						"left":  (e.pageX+x)  + "px"
					}).show("fast");	  //设置x坐标和y坐标，并且显示
		    	}).mouseout(function(){
					this.title = this.myTitle;	
					$("#tooltip").remove();	 //移除 
		    	}).mousemove(function(e){
					$("#tooltip")
						.css({
							"top": (e.pageY+y) + "px",
							"left":  (e.pageX+x)  + "px"
						});
				});
			})
			</script>
		<!-- 加载左栏数据 -->
		<script type="text/javascript">
			//左侧栏目列表
			$(function(){
				$("#left").html('<img src="../images/window_loading.gif" align="middle"/>正在加载...');
				$.get("category.action",
						  function(data){
					  			$("#left").html(data);
					  	}
				 ); 
			});
	
		</script>
	</head>
	<body>
		&nbsp;
		<!-- 头部开始 -->
		<%@include file="../common/head.jsp"%>
		<!-- 头部结束 -->
		<div style="width: 962px; margin: auto;">
			<a href="#" target="_blank"><img
					src="../images/default/book_banner_081203.jpg" border="0" /> </a>
		</div>

		<div class="book">

			<!--左栏开始-->
			<div id="left" class="book_left">
				<!--加载category/category.jsp中内容-->
			</div>
			<!--左栏结束-->

			
			<!--中栏开始-->
			<div class="book_center">

				<!--推荐图书开始-->
				<div class=second_c_border1 id="recommend">
					<s:action name="recommend" executeResult="true" namespace="/main"></s:action>
				</div>

				<!--推荐图书结束-->

				<!--热销图书开始-->
				<div class="book_c_border2" id="hot">
				<s:action name="hot" executeResult="true" namespace="/main"></s:action>
				</div>
				<!--热销图书结束-->

				<!--最新上架图书开始-->
				<div class="book_c_border2" id="new">
				<s:action name="new" executeResult="true" namespace="/main"></s:action>
				</div>
				<!--最新上架图书结束-->

				<div class="clear">
				</div>
			</div>
			<!--中栏结束-->



			<!--右栏开始-->
			<div class="book_right">
				<div class="book_r_border2" id="__XinShuReMai">
					<div class="book_r_b2_1x" id="hotboard">
						<s:action name="hotboard" executeResult="true" namespace="/main"></s:action>
					</div>
				</div>
			</div>
			<!--右栏结束-->
			<div class="clear"></div>
		</div>

		<!--页尾开始 -->
		<%@include file="../common/foot.jsp"%>
		<!--页尾结束 -->
	</body>

	
</html>