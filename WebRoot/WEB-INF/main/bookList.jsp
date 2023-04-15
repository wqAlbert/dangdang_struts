<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>当当图书 – 全球最大的中文网上书店</title>

		<link href="../css/book.css" rel="stylesheet" type="text/css" />
		<link href="../css/second.css" rel="stylesheet" type="text/css" />
		<link href="../css/secBook_Show.css" rel="stylesheet" type="text/css" />
		<link href="../css/list.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript"  src="../js/jquery-1.4.min.js"></script>
		<script type="text/javascript">
		
		  $(function(){
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
		
		
		
		
			function addProduct(img,productId) {
				$(img).css("display","none");
				$("#cartInfo_"+productId).html('<img align="bottom" src="../images/load.gif" width="14" height="14" style="display:inline" align="bottom"/>&nbsp;购买中...');
				$.getJSON("../cart/addItem.action",
						{ id: productId, time: (new Date()).getTime() }, 
						function(json){
					  		if(json.count==0) {//说明count属性发送了过来
						  		//没有添加成功
						  		$(img).css("display","block");
					  			$("#cartInfo_"+productId).html('<img align="bottom" src="../images/wrong.gif" width="14" height="14" style="display:inline" align="bottom"/>&nbsp;<span style="color:red">购买失败</span>');
					  		}
					  		else {
						  		//添加成功
						  		$("#cartInfo_"+productId).html('<img align="bottom" src="../images/right.gif" width="14" height="14" style="display:inline" align="bottom"/>&nbsp;购买成功');
						  		var timeId=setTimeout(function(){
							  		clearTimeout(timeId);
							  		$(img).css("display","block");
							  		$("#cartInfo_"+productId).html("");
							  	}, 2000);
					  		}
						}
					); 
			}
		</script>
		
	</head>
	<body>

		<!-- 头部开始 -->
		<%@include file="../common/head.jsp"%>
		<!-- 头部结束 -->
					
		<div style="width: 962px; margin: auto;">
			<a href="#"><img src="../images/default/book_banner_081203.jpg"
					border="0" /> </a>
		</div>
		<div class='your_position'>
			您现在的位置:&nbsp;
			<a href='../main/toMain.action'>当当图书</a> &gt;&gt;
			<font style='color: #cc3300'>
			<s:if test="ssc==null">
			<strong>小说</strong>
			</s:if>
			<s:else>
			<strong><a href="bookList.action?sc=${sc}">${category.name }</a></strong>
			&gt;&gt;
			${category.name }
			</s:else>
			 </font>
		</div>

		<div class="book">

			<!--左栏开始-->
			<div id="left" class="book_left">
				<div id="__fenleiliulan">
					<div class=second_l_border2>
						<h2>
							分类浏览
						</h2>
						<ul>
							<li>
								<div>
									<s:if test="ssc==null">
										<div class=second_fenlei3>
										全部(${category.productCount })
										</div>
									</s:if>
									<s:else>
										<div class=second_fenlei>
										<a href="bookList.action?sc=${sc}">全部(${category.productCount })</a>
										</div>
									</s:else>
								
								</div>
							</li>
							<div class="clear"></div>
							<!--三级分类开始-->
							<s:iterator value="category.subCats" var="c">
								<li>
									<div>
												<s:if test="#c.id==ssc">
												<div class="second_fenlei">
														&middot;
												</div>
												<div class="second_fenlei3">
													${c.name }(${c.productCount })
												</div>
												</s:if>
												<s:else>
													<div class="second_fenlei">
													<div class="second_fenlei">
														&middot;
													</div>
													<a href="bookList.action?sc=${sc}&ssc=${c.id}">${c.name }(${c.productCount })</a>
													</div>
												</s:else>
												
									</div>
								</li>
								
								<div class="clear">
								</div>
								</s:iterator>
								<!--三级分类结束-->
							
							<li>
								<div></div>
							</li>
						</ul>
					</div>
				</div>
			</div>

			<!--左栏结束-->

			<!--中栏开始-->
			<div class="book_center">

				<!--图书列表开始-->
				<div id="divRight" class="list_right">

					<div id="book_list" class="list_r_title">
						<div class="list_r_title_text">
							排序方式
						</div>
						<select onchange='' name='select_order' size='1'
							class='list_r_title_ml'>
							<option value="">
								按上架时间 降序
							</option>
						</select>
						<div id="divTopPageNavi" class="list_r_title_text3">

							<!--分页导航开始-->
								<s:if test="page <= 1">
									<div class='list_r_title_text3a'>
										<img src='../images/page_up_gray.gif' />
									</div>
								</s:if>
								<s:else>
									<div class='list_r_title_text3a'>
										<s:if test="ssc==null">
										<a name=link_page_next
											href="bookList.action?sc=${sc}&page=${page-1}">
											<img src='../images/page_up.gif' /> </a>
										</s:if>
										<s:else>
										<a name=link_page_next
											href="bookList.action?sc=${sc}&ssc=${ssc}&page=${page-1}">
											<img src='../images/page_up.gif' /> </a>
										</s:else>
									</div>
								</s:else>
									
							
							<div class='list_r_title_text3b'>
								第${page }页/共${totalPages }页
							</div>
				
									<s:if test="page>=totalPages">
										<div class='list_r_title_text3a'>
										<img src='../images/page_down_gray.gif' />
									</div>
									</s:if>
									<s:else>							
									<div class='list_r_title_text3a'>
										<s:if test="ssc==null">
											<a name=link_page_next
											href="bookList.action?sc=${sc}&page=${page+1}">
											<img src='../images/page_down.gif' /> </a>
										</s:if>
										<s:else>
											<a name=link_page_next
											href="bookList.action?sc=${sc}&ssc=${ssc}&page=${page+1}">
											<img src='../images/page_down.gif' /> </a>
										</s:else>
									</div>
									</s:else>
									
							
							<!--分页导航结束-->
						</div>
					</div>
					
					<!--商品条目开始-->
					<s:iterator value="books" var="book">
						<div class="list_r_line"></div>
						<div class="clear"></div>

						<div class="list_r_list">
							<span class="list_r_list_book"><a  target='_blank' name="link_prd_img" href='product.action?id=${book.id }'>
								<img title="${book.productName }" class="dang_img" src='../productImages/${book.productPic }' /> </a> </span>
							<h2>
								<a  target='_blank' name="link_prd_name" href='product.action?id=${book.id }'>${book.productName }</a>
							</h2>
							<h3>
								顾客评分：100
							</h3>
							<h4 class="list_r_list_h4">
								作 者:
								<a href='#' name='作者'>${book.author }</a>
							</h4>
							<h4>
								出版社：
								<a href='#' name='出版社'>${book.publishing }</a>
							</h4>
							<h4>
								出版日期：${book.publishDt }
							</h4>
							<h5> 
								${book.description }
							</h5>
							<div class="clear"></div>
							<h6>
								<span class="del">${book.fixedPrice}</span>
								<span class="red">${book.dangPrice }</span>
								节省：￥${book.fixedPrice-book.dangPrice }
							</h6>
							<span class="list_r_list_button"> 
									<img align="top" src='../images/buttom_goumai.gif' onclick="addProduct(this,${book.id})"/>


							</span>
							<span id="cartInfo_${book.id}" ></span>
						</div>
						<div class="clear"></div>
						</s:iterator>
						<!--商品条目结束-->
					

					<div class="clear"></div>
					<div id="divBottomPageNavi" class="fanye_bottom">
					</div>

				</div>
				<!--图书列表结束-->

			</div>
			<!--中栏结束-->
			<div class="clear"></div>
		</div>

		<!--页尾开始 -->
		<%@include file="../common/foot.jsp"%>
		<!--页尾结束 -->
	</body>
</html>
