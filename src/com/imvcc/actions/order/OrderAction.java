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
 * Action for ��������
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
	 * ת�붩������ҳ�棬ȷ��
	 * @return
	 */
	public String toOrder() {
		cartService=ServiceFacotry.getCartService(ActionContext.getContext().getSession());
		
		log.debug("Dang:ת�붩��ȷ��ҳ�棬��ȡ�����Ķ�����Ϣ");
		if(session.containsKey(SessionConstant.SESS_REDIRECT)) {
			log.debug("Dang:ת�������URL,����SESS_redirect:"+SessionConstant.SESS_REDIRECT);
			session.remove(SessionConstant.SESS_REDIRECT);
		}
		order=orderService.createOrder(orderService.createOrderItems(cartService), cartService);
		
		return "success";
	}
	/**
	 * ת�������ͻ���ַ
	 * @return
	 */
	public String toInputAddress() {
		log.debug("Dang:׼�������û���ַ�б���selectʹ�ã������ж��û���¼");
		user=getUerFromSession(session);
		if(user==null) {
			log.debug("Dang:�û�û�е�¼��ת���¼����");
			session.put(SessionConstant.SESS_REDIRECT, request.getRequestURL().toString()+"$login");
			System.out.println(request.getRequestURL().toString()+"$login");
			return "user.login";
		}
		else if("N".equals(user.getIsEmailVerify())){
			log.debug("Dang:�û�û�м��ת�뼤�����");
			session.put(SessionConstant.SESS_REDIRECT, request.getRequestURL().toString()+"$verify");
			System.out.println(request.getRequestURL().toString()+"$verify"); //uri����
			return "user.active";
		}
		mapAddress=orderService.getReceiveAddressList(user.getId());
		log.debug("Dang:��ɵ�ַ�б�����");
		return "success";
	}
	/**
	 * ���ɶ���
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
		
	
		log.debug("Dang:׼��������д�����ݿ�,�����жϱ���Ϣ�Ĵ�����....");
		//user=userService.getUerFromSession(session);
		order=orderService.createOrder(orderService.createOrderItems(cartService), cartService);
		if(order==null || user==null || address==null) {
			log.debug("Dang��������Ϣ�����ڻ��߲��������������û�����ַ���߶���û������ ");
			return "error";
		}
		orderService.submitOrder(user,address,order);
		log.debug("Dang:�����Ѿ�д�����ݿ⣬��չ��ﳵ");
		cartService.clearCart();
		
		try {
			payUrl = OnlinePayUtil.makeOrderAlipayUrl(ServletActionContext.getRequest(),order);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "success";
	}
	/**
	 * ��֤��ַ�������Ч��
	 * @return
	 */
	private boolean validateProduceOrder() {
		boolean result=true;
		log.debug("Dang����֤�����������룬����Ϊ�գ�����2-20");
		if(!ValidatorUtil.requiredString(address.getReceiveName(), true)) {
			log.debug("Dang:����Ϊ��");
			this.addError("address.name", "��������Ϊ��,����д��");
			result=false;
		}
		else if(0!=ValidatorUtil.stringLength(address.getReceiveName(), FormConstant.MIN_NAME_LENGTH, FormConstant.MAX_NAME_LENGTH, true)) {
			log.debug("Dang:�û������ȣ�����");
			this.addError("address.name", "�������ȴ���Ҫ�󳤶���"+FormConstant.MIN_NAME_LENGTH+"-"+FormConstant.MAX_NAME_LENGTH+"֮��");
			result=false;
		}
		
		log.debug("Dang:����ͨ������֤��ַ������Ч�ԣ�����Ϊ��");
		if(!ValidatorUtil.requiredString(address.getFullAddress(), true)) {
			log.debug("Dang:��ַΪ��");
			this.addError("address.fullAddress","��ַ����Ϊ�գ�����д��");
			result=false;
		}
		else if(0!=ValidatorUtil.stringLength(address.getFullAddress(), FormConstant.MIN_ADDRESS_LENGTH, FormConstant.MAX_ADDRESS_LENGTH, true)) {
			log.debug("Dang:��ַ��������");
			this.addError("address.fullAddress", "��ַ���ȴ���Ҫ�󳤶���"+FormConstant.MIN_ADDRESS_LENGTH+"-"+FormConstant.MAX_ADDRESS_LENGTH+"֮��");
			return false;
		}
		
		log.debug("Dang:ͨ����ַ��֤����֤�����������Ч�ԣ������й��� ");
		if(!ValidatorUtil.requiredString(address.getPostalCode(), true)) {
			log.debug("Dang:�ʱ�Ϊ��");
			this.addError("address.postalCode", "�������벻��Ϊ�գ�����д��");
			result=false;
		}
		else if(!ValidatorUtil.postalCode(address.getPostalCode())) {
			log.debug("Dang:�ʱ��ʽ����");
			this.addError("address.postalCode", "�ʱ��ʽ����");
			result=false;
		}
		
		log.debug("Dang:�ʱ�ͨ����֤����֤�绰����Ч�ԣ�����Ϊ�գ�����");
		if(!ValidatorUtil.requiredString(address.getPhone(), true) && !ValidatorUtil.requiredString(address.getMobile(), true)) {
			log.debug("Dang:�绰���ֻ�һ��Ҳûд");
			this.addError("address.phone", "�绰���ֻ�������д���е�һ��");
			result=false;
		}
		else {
			if(!ValidatorUtil.phone(address.getPhone())) {
				log.debug("Dang:�绰��ʽ����");
				this.addError("address.phone", "�绰����ĸ�ʽ����");
				result=false;
			}
			if(!ValidatorUtil.mobile(address.getMobile())) {
				log.debug("Dang:�ֻ�����ĸ�ʽ����");
				this.addError("address.mobile", "�ֻ�����ĸ�ʽ����");
				result=false;
			}
		}
		log.debug("Dang:��֤���");
		return result;
	}
	
	/**
	 * ��ȡSession�е��û���Ϣ
	 */
	public User getUerFromSession(Map<String, Object> session) {
		User user = (User) session.get(SessionConstant.SESS_USER_AUTHORIZE);
		return user;
	}

}
