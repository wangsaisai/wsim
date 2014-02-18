package cn.edu.ustc.wsim.bean;

import java.util.Date;

/**
 * Message entity. @author MyEclipse Persistence Tools
 */

public class Message implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7343655894392736054L;
	private Integer id;
	private User receiver;
	private User sender;
	private String content;
	private Date time;
	private Boolean readed;

	// Constructors

	/** default constructor */
	public Message() {
	}
	
	public Message(Integer id) {
		this.id = id;
	}	

	/** full constructor */
	public Message(Integer id, User receiver, User sender,
			String content, Date time, Boolean readed) {
		this.id = id;
		this.receiver = receiver;
		this.sender = sender;
		this.content = content;
		this.time = time;
		this.readed = readed;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getReceiver() {
		return this.receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public User getSender() {
		return this.sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Boolean getReaded() {
		return this.readed;
	}

	public void setReaded(Boolean readed) {
		this.readed = readed;
	}

}