package com.imvcc.actions.cart;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.json.annotations.JSON;

import com.imvcc.actions.BaseAction;
import com.imvcc.service.CartItem;
import com.imvcc.service.CartService;
import com.imvcc.service.ServiceFacotry;
import com.opensymphony.xwork2.ActionContext;
/**
 * ���ﳵ����Ϊ����
 * @author Mr.rong
 *
 */
public class CartAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3994297242665697527L;
	private List<CartItem> cartItems; //���ﳵ
	private double savePrice;
	private double totalPrice;
	
	private Integer id;
	private int count=1;
	
	private Logger log=Logger.getLogger(CartAction.class);
	
	private CartService cartService;
	
	@JSON(serialize=false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	@JSON(serialize=false)
	public List<CartItem> getCartItems() {
		return cartItems;
	}
	@JSON(serialize=false)
	public double getSavePrice() {
		return savePrice;
	}

	public void setSavePrice(double savePrice) {
		this.savePrice = savePrice;
	}
	@JSON(serialize=false)
	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	
	public String showItemList() {
		cartService = ServiceFacotry.getCartService(ActionContext.getContext().getSession());
		cartItems=cartService.productList();
		totalPrice=cartService.totalPrice();
		savePrice=cartService.savePrice();
		return "success";
	}
	public String addItem() throws InterruptedException {
		cartService = ServiceFacotry.getCartService(ActionContext.getContext().getSession());
		log.debug("Dang:�����Ʒ�����ﳵ��"+id);
		cartService.addCartItem(new CartItem(id,count));
		//ģ�������ӳ�
		Thread.sleep(500);
		log.debug("Dang:�����ɣ�����Ϊ"+count);
		return "success";
	}
	public String deleteItem() {
		cartService = ServiceFacotry.getCartService(ActionContext.getContext().getSession());
		log.debug("Dang:ɾ�����ﳵ�еĻ���"+id);
		cartService.deleteCartItem(new CartItem(id,count));
		return "success";
	}
	public String updateCount() {
		cartService = ServiceFacotry.getCartService(ActionContext.getContext().getSession());
		log.debug("Dang:���¹��ﳵ�еĻ���"+id+"Ϊ��"+count);
		cartService.updateCartItem(new CartItem(id,count));
		return "success";
	}
	public String recoveryItem() {
		cartService = ServiceFacotry.getCartService(ActionContext.getContext().getSession());
		log.debug("Dang:�ظ�ɾ���Ĺ��ﳵ�л���"+id);
		cartService.recoveyCartItem(new CartItem(id,count));
		return "success";
	}
	/**
	 * ��չ��ﳵ
	 * @return
	 */
	public String clearCart() {
		return "success";
	}
}
