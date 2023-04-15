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
	/* �ύ�Ĳ������ݣ�
	 * ��sc����second_category
	 * ��ssc����sub_second_category*/
	private Integer sc;//2��Ŀ¼
	private Integer ssc;//3��Ŀ¼
	
	/* ��ҳ��ѯʱ����Ҫ��2������*/
	private Integer page=1;//ҳ��
	private int pageSize;//�������ļ�����ע��
	
    
	private Category category;//2��Ŀ¼

	/* ��ҳ��ѯʱ������Ҫ������*/	
	private List<Book> books;//��ѯ�����������б�
	private int totalPages;//���ݲ�ѯ�������ܼ�¼��������ҳ��
	
	//��������
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
		Category curCat=null;//��ǰĿ¼����ѯ����
		log.debug("Dang:��ȡ����Ŀ¼");
		
		//���Ȼ��Ŀ¼����!
		category=catService.findCatById(sc);//�϶���2��Ŀ¼
		
		//��û�е�¼�͹�������,��д�궩�������ɻ���ַ��,���·��ص���ҳ��
		if(category==null) {
			log.debug("Dang:�޷���ȡ����Ŀ¼���Ƿ�����");
			return "main";
		}
		
		if(ssc==null){//��ǰĿ¼��2��Ŀ¼
			log.debug("�������2��Ŀ¼������2��Ŀ¼����ͼ��");
			catId=sc;
			curCat=category;
		}else{//��ǰĿ¼��3��Ŀ¼
			log.debug("�������3��Ŀ¼������3��Ŀ¼�µ�����ͼ��");
			for(Category cat:category.getSubCats()){
				if(cat.getId().equals(ssc)){
					catId=ssc;
					curCat=cat;
					break;
				}
			}
		}
		//����BookService����Ե�ǰĿ¼ʵʩ��ҳ��ѯ
		books=bookService.findBookByCatId(catId, page, pageSize); //��
		totalResults=curCat.getProductCount();
		
		//������ҳ��
		this.setTotalPages(totalResults/pageSize+(totalResults%pageSize==0?0:1));//��
		
		return "success";
	}
}
