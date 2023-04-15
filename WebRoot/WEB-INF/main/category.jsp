<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="book_l_border1" id="__FenLeiLiuLan">
	<div class="book_sort_tushu">
		<h2>
			分类浏览
		</h2>
		
		<!--2级分类开始-->
		<s:iterator value="listCats" var="c2">
			
			<div class="bg_old" onmouseover="this.className = 'bg_white';"
				onmouseout="this.className = 'bg_old';">
				<h3>
					[
					<a href='bookList.action?sc=${c2.id }'>${c2.name }</a>]
				</h3>
				<ul class="ul_left_list">
					
						<!--3级分类开始-->
						<s:iterator value="#c2.subCats" var="c3">
						<%--<s:iterator value="c2.subCats" var="c3"> 注意:value不加#拿不到数据--%>
						<li>
							<a href='bookList.action?sc=${c2.id }&ssc=${c3.id}'>${c3.name }</a>
						</li>
						</s:iterator>
						<!--3级分类结束-->
				</ul>
				<div class="empty_left">
				</div>
			</div>

			<div class="more2">
			</div>
			<!--2级分类结束-->
		</s:iterator>
		<div class="bg_old">
			<h3>
				&nbsp;
			</h3>
		</div>
	</div>
</div>
