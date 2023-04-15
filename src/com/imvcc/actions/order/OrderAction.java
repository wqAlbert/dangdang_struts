package com.imvcc.actions.order;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.imvcc.actions.BaseAction;
import com.imvcc.common.FormConstant;
import com.imvcc.common.SessionConstant;
import com.imvcc.domain.Order;
import com.imvcc.domain.ReceiveAddress;
import com.imvcc.domain.User;
import com.imvcc.service.CartService;
import com.imvcc.service.OrderService;
import com.imvcc.service.ServiceFacotry;
import com.imvcc.service.UserService;
import com.imvcc.util.OnlinePayUtil;
import com.imvcc.util.ValidatorUtil;
import com.opensymphony.xwork2.ActionContext;
/**
 * Action for 订单生成
 * @author Mr.Rong
 *
 */
public class OrderAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4516012098167254788L;
	private	 Order order;
	private Map<Integer,ReceiveAddress> mapAddress;
	private ReceiveAddress address;
	
	private Map<String,String> errors=new HashMap<String,String>();
	
	private User user;

	
	private OrderService orderService=ServiceFacotry.getOrderService();
	private CartService cartService;
	private UserService userService=ServiceFacotry.getUserService();
	
	
	private Logger log=Logger.getLogger(OrderAction.class);
	
	private String payUrl;
	
	public String getPayUrl() {
		return payUrl;
	}
	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}
	public Map<String, String> getErrors() {
		return errors;
	}
	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
	
	public void addError(String key,String error) {
		errors.put(key, error);	
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}

	public Map<Integer, ReceiveAddress> getMapAddress() {
		return mapAddress;
	}
	public void setMapAddress(Map<Integer, ReceiveAddress> mapAddress) {
		this.mapAddress = mapAddress;
	}
	
	public ReceiveAddress getAddress() {
		return address;
	}
	public void setAddress(ReceiveAddress address) {
		this.address = address;
	}
	/**
	 * 转入订单生成页面，确认
	 * @return
	 */
	public String toOrder() {
		cartService=ServiceFacotry.getCartService(ActionContext.getContext().getSession());
		
		log.debug("Dang:转入订单确认页面，获取基本的订单信息");
		if(session.containsKey(SessionConstant.SESS_REDIRECT)) {
			log.debug("Dang:转入过来的URL,销毁SESS_redirect:"+SessionConstant.SESS_REDIRECT);
			session.remove(SessionConstant.SESS_REDIRECT);
		}
		order=orderService.createOrder(orderService.createOrderItems(cartService), cartService);
		
		return "success";
	}
	/**
	 * 转入输入送货地址
	 * @return
	 */
	public String toInputAddress() {
		log.debug("Dang:准备生成用户地址列表，给select使用，首先判断用户登录");
		user=getUerFromSession(session);
		if(user==null) {
			log.debug("Dang:用户没有登录，转入登录界面");
			session.put(SessionConstant.SESS_REDIRECT, request.getRequestURL().toString()+"$login");
			System.out.println(request.getRequestURL().toString()+"$login");
			return "user.login";
		}
		else if("N".equals(user.getIsEmailVerify())){
			log.debug("Dang:用户没有激活，转入激活界面");
			session.put(SessionConstant.SESS_REDIRECT, request.getRequestURL().toString()+"$verify");
			System.out.println(request.getRequestURL().toString()+"$verify"); //uri即可
			return "user.active";
		}
		mapAddress=orderService.getReceiveAddressList(user.getId());
		log.debug("Dang:完成地址列表生成");
		return "success";
	}
	/**
	 * 生成订单
	 * @return
	 */
	public String produceOrder() {
		cartService=ServiceFacotry.getCartService(ActionContext.getContext().getSession());
		user=getUerFromSession(session);
		if(!validateProduceOrder()) {
			//user=userService.getUerFromSession(session);
			mapAddress=orderService.getReceiveAddressList(user.getId());
			return "input";
		}
		
	
		log.debug("Dang:准备将订单写入数据库,首先判断表单信息的存在性....");
		//user=userService.getUerFromSession(session);
		order=orderService.createOrder(orderService.createOrderItems(cartService), cartService);
		if(order==null || user==null || address==null) {
			log.debug("Dang：订单信息不存在或者不完整，可能是用户，地址或者订单没有生成 ");
			return "error";
		}
		orderService.submitOrder(user,address,order);
		log.debug("Dang:订单已经写入数据库，清空购物车");
		cartService.clearCart();
		
		try {
			payUrl = OnlinePayUtil.makeOrderAlipayUrl(ServletActionContext.getRequest(),order);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "success";
	}
	/**
	 * 验证地址输入的有效性
	 * @return
	 */
	private boolean validateProduceOrder() {
		boolean result=true;
		log.debug("Dang：验证姓名表单的输入，不能为空，长度2-20");
		if(!ValidatorUtil.requiredString(address.getReceiveName(), true)) {
			log.debug("Dang:不能为空");
			this.addError("address.name", "姓名不能为空,请填写！");
			result=false;
		}
		else if(0!=ValidatorUtil.stringLength(address.getReceiveName(), FormConstant.MIN_NAME_LENGTH, FormConstant.MAX_NAME_LENGTH, true)) {
			log.debug("Dang:用户名长度，错误");
			this.addError("address.name", "姓名长度错误，要求长度在"+FormConstant.MIN_NAME_LENGTH+"-"+FormConstant.MAX_NAME_LENGTH+"之间");
			result=false;
		}
		
		log.debug("Dang:姓名通过，验证地址表单的有效性，不能为空");
		if(!ValidatorUtil.requiredString(address.getFullAddress(), true)) {
			log.debug("Dang:地址为空");
			this.addError("address.fullAddress","地址不能为空，请填写！");
			result=false;
		}
		else if(0!=ValidatorUtil.stringLength(address.getFullAddress(), FormConstant.MIN_ADDRESS_LENGTH, FormConstant.MAX_ADDRESS_LENGTH, true)) {
			log.debug("Dang:地址长度限制");
			this.addError("address.fullAddress", "地址长度错误，要求长度在"+FormConstant.MIN_ADDRESS_LENGTH+"-"+FormConstant.MAX_ADDRESS_LENGTH+"之间");
			return false;
		}
		
		log.debug("Dang:通过地址验证，验证邮政编码的有效性，符合中国的 ");
		if(!ValidatorUtil.requiredString(address.getPostalCode(), true)) {
			log.debug("Dang:邮编为空");
			this.addError("address.postalCode", "邮政编码不能为空，请填写！");
			result=false;
		}
		else if(!ValidatorUtil.postalCode(address.getPostalCode())) {
			log.debug("Dang:邮编格式错误");
			this.addError("address.postalCode", "邮编格式错误");
			result=false;
		}
		
		log.debug("Dang:邮编通过验证，验证电话的有效性，不能为空，正则");
		if(!ValidatorUtil.requiredString(address.getPhone(), true) && !ValidatorUtil.requiredString(address.getMobile(), true)) {
			log.debug("Dang:电话和手机一个也没写");
			this.addError("address.phone", "电话和手机必须填写其中的一个");
			result=false;
		}
		else {
			if(!ValidatorUtil.phone(address.getPhone())) {
				log.debug("Dang:电话格式错误");
				this.addError("address.phone", "电话号码的格式错误");
				result=false;
			}
			if(!ValidatorUtil.mobile(address.getMobile())) {
				log.debug("Dang:手机号码的格式错误");
				this.addError("address.mobile", "手机号码的格式错误");
				result=false;
			}
		}
		log.debug("Dang:验证完成");
		return result;
	}
	
	/**
	 * 获取Session中的用户信息
	 */
	public User getUerFromSession(Map<String, Object> session) {
		User user = (User) session.get(SessionConstant.SESS_USER_AUTHORIZE);
		return user;
	}

}
