package com.imvcc.actions.main;

import java.util.List;

import org.apache.log4j.Logger;

import com.imvcc.actions.BaseAction;
import com.imvcc.common.SessionConstant;
import com.imvcc.domain.Book;
import com.imvcc.domain.Category;
import com.imvcc.service.BookService;
import com.imvcc.service.CategoryService;
import com.imvcc.service.ServiceFacotry;
/**
 * The action for book list page 
 * @author Mr.rong
 *
 */
public class BookListAction extends BaseAction{
	/* 提交的参数数据：
	 * ①sc代表second_category
	 * ②ssc代表sub_second_category*/
	private Integer sc;//2级目录
	private Integer ssc;//3级目录
	
	/* 分页查询时，需要的2个参数*/
	private Integer page=1;//页号
	private int pageSize;//由配置文件进行注入
	
    
	private Category category;//2级目录

	/* 分页查询时，所需要的数据*/	
	private List<Book> books;//查询出来的数据列表
	private int totalPages;//根据查询出来的总记录数计算总页数
	
	//依赖属性
	private CategoryService catService=ServiceFacotry.getCategoryService();
	private BookService bookService=ServiceFacotry.getBookService();
	
	private Logger log=Logger.getLogger(BookListAction.class);
	
	///////////////////////////////////////////////////////////////////
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public Integer getSc() {
		return sc;
	}

	public void setSc(Integer sc) {
		this.sc = sc;
	}

	public Integer getSsc() {
		return ssc;
	}

	public void setSsc(Integer ssc) {
		this.ssc = ssc;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}


	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
    
	public String execute() {
		int totalResults=0;
		int catId=0;
		Category curCat=null;//当前目录，查询条件
		log.debug("Dang:获取二级目录");
		
		//首先获得目录对象!
		category=catService.findCatById(sc);//肯定有2级目录
		
		//在没有登录就购物的情况,填写完订单及其派货地址后,重新返回到主页面
		if(category==null) {
			log.debug("Dang:无法获取二级目录，非法输入");
			return "main";
		}
		
		if(ssc==null){//当前目录是2级目录
			log.debug("点击的是2级目录，搜索2级目录所有图书");
			catId=sc;
			curCat=category;
		}else{//当前目录是3级目录
			log.debug("点击的是3级目录，搜索3级目录下的所有图书");
			for(Category cat:category.getSubCats()){
				if(cat.getId().equals(ssc)){
					catId=ssc;
					curCat=cat;
					break;
				}
			}
		}
		//调用BookService，针对当前目录实施分页查询
		books=bookService.findBookByCatId(catId, page, pageSize); //②
		totalResults=curCat.getProductCount();
		
		//计算总页数
		this.setTotalPages(totalResults/pageSize+(totalResults%pageSize==0?0:1));//③
		
		return "success";
	}
}
