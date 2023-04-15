
/*product_name(书名) 
  description(商品的描述)
  fixed_price(固定价格)
  dang_price(当当价格)
	add_time(上架时间)
  product_pic(图片名称)
*/
  
select * from d_product;

/*author(作者)
  publishing(出版社)
  publish_time(出版时间) */
select * from d_book;

/*第一步分模块1*/
select product_name,description,fixed_price,dang_price,add_time,product_pic,
       author,publishing,publish_time
from d_product left JOIN d_book
on d_product.id=d_book.id
where publishing like '%清华%' 
limit 2;

/*第一部分模块3*/
select product_name,product_pic,fixed_price,dang_price, add_time
from d_product
order by add_time desc
limit 8;

/*第一部分模块2*/
/*order_id(订单编号,外键字段,连接d_order中的id字段)
  product_id(外键字段连接d_product表的id字段)
  product_name(商品名称)
  dang_price(dangdang价格)
  product_num(销售数量)
  amount(销售额)
*/
select * from d_item;

select product_id,product_name,sum(product_num) salenum
from d_item
GROUP BY product_id
order by salenum desc
limit 6;

/*按需求显示数据*/
select product_id,i.product_name,sum(product_num) salenum,
       product_pic,fixed_price,p.dang_price
from d_item i LEFT JOIN d_product p
on i.product_id=p.id
GROUP BY product_id
order by salenum desc
limit 6;











