package cn.edu.ustc.wsim.action;

import java.util.Date;
import java.util.List;

import cn.edu.ustc.wsim.bean.Message;
import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.service.MessageService;
import cn.edu.ustc.wsim.util.DateUtil;
import cn.edu.ustc.wsim.util.page.Page;
import cn.edu.ustc.wsim.util.page.Result;

import com.opensymphony.xwork2.ActionSupport;

public class MessageAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2370322886063605958L;
	private Integer id;
	private Integer receiverId;
	private Integer senderId;
	private String content;
	private Date time;
	private Boolean readed;
	
	private Date beginTime;
	private Date endTime;
	
	private Integer otherId;
	
	private List<Message> messages;
	


	private MessageService messageService;
	
	private String errorMsg;
	
	private int currentPage;
	private Page page;
	
	private String msg;
	
	
	public String addMessage() {
		User sender = messageService.getLoginUser();
		User receiver = new User(receiverId);
		
		Message message = new Message();
		message.setSender(sender);
		message.setReceiver(receiver);
		message.setTime(new Date());
		message.setContent(content);
		//对离线消息和即使消息做区分
//		message.setReaded(false);
		
		if(messageService.add(message))
			return "addSuccess";
		else {
			errorMsg = "add Message error";
			return "addError";
		}
				
	}
	
	public String delMessage() {
		int intId = id;
		if(messageService.del(intId))
			return "delSuccess";
		else {
			errorMsg = "del error";
			return "delError";
		}
	}
	
	public String readMessage() {
		
		Message message = (Message) messageService.get(id);
		message.setReaded(true);
		
		if(messageService.update(message)) {
			return "readMessageSuccess";
		} else {
			errorMsg = "update Error";
			return "readMessageError";
		}
	}
	
	
	public String searchMessage() {
		User other = new User(otherId);
		this.messages = messageService.getMessages(messageService.getLoginUser(), other);
		if(messages == null || messages.size() == 0)
			return "noResult";
		else
			return "showResult";
	}
	
	public String searchMessageByTime() {
		
		User other = new User(otherId);
		this.page = this.pageInfo();
		Result result = messageService.getMessagesByTime(messageService.getLoginUser(), other, beginTime, endTime, page);
		page = result.getPage();
		messages = result.getList();
		String style = "yy-MM-dd";
		msg = "&otherId=" + otherId + "&beginTime=" + DateUtil.parseToString(beginTime, style) + "&endTime=" + DateUtil.parseToString(endTime, style);
		if(messages == null || messages.size() == 0)
			return "noResult";
		else
			return "showResult";
	}
	
	
	public Page pageInfo(){
		Page page = new Page();
		page.setEveryPage(10);
		page.setCurrentPage(currentPage);
		return page;
	}
	
	
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		if (currentPage <= 0)
			this.currentPage = 1;
		else
			this.currentPage = currentPage;
	}
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}

	public Integer getSenderId() {
		return senderId;
	}

	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Boolean getReaded() {
		return readed;
	}

	public void setReaded(Boolean readed) {
		this.readed = readed;
	}

	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Integer getOtherId() {
		return otherId;
	}

	public void setOtherId(Integer otherId) {
		this.otherId = otherId;
	}
	
	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
