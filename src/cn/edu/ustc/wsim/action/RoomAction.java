package cn.edu.ustc.wsim.action;

import cn.edu.ustc.wsim.websocket.room.Room;
import cn.edu.ustc.wsim.websocket.room.RoomManager;

import com.opensymphony.xwork2.ActionSupport;

public class RoomAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2830347711506739832L;
	
	private Integer id;
	private String name;
	
	private String errorMsg;
	
	private Room room;



	public String createRoom() {
		room = RoomManager.createRoom();
		id = room.getId();
		return "createSuccess";
	}

	
	public String joinRoom() {
		room = RoomManager.getRoom(id);
		if(room == null) {
			errorMsg = "无此聊天室";
			return "noSuchRoom";
		}
		
		if(room.getUsers().contains(name)) {
			errorMsg = "该用户名已被使用，请重新输入";
			return "joinRoomError";
		}
		
		room.addUser(name);
		return "joinRoomSuccess";
			
	}
	
	
	public String quitRoom() {
		RoomManager.quitRoom(name, id);
		return "quitRoomSuccess";
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}


	public Room getRoom() {
		return room;
	}


	public void setRoom(Room room) {
		this.room = room;
	}
	
	
}
