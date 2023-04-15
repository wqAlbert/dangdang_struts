<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<h2>
	<span class="more"><a href="#" target="_blank">更多&gt;&gt;</a> </span>编辑推荐
</h2>
<div id=__bianjituijian/danpin>
	<div class=second_c_02>
	<s:iterator value="newBooks" var="book">
		<div class=second_c_02_b1>
			<div class=second_c_02_b1_1>
				<a href='product.action?id=${book.id }' target='_blank'><img class="dang_img" src="../productImages/${book.productPic }" width=70 border=0 /> </a>
			</div>
			<div class=second_c_02_b1_2>
				<h3>
					<a href='product.action?id=${book.id }' target='_blank'>${book.productName }</a>
				</h3>
				<h4>
					作者：${book.author } 著
					<br />
					出版社：${book.publishing }&nbsp;&nbsp;&nbsp;&nbsp;出版时间：<s:date name="book.datePublishDate" format="yyyy年MM月dd"/>
				</h4>
				<h5>
					${book.description }
				</h5>
				<h6>
					定价：￥${book.fixedPrice}&nbsp;&nbsp;当当价：￥${book.dangPrice }
				</h6>
				<div class=line_xx></div>
			</div>
		</div>
	</s:iterator>	
		
		
	</div>
</div>
