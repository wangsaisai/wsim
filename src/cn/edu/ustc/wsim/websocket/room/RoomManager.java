package cn.edu.ustc.wsim.websocket.room;

import java.util.HashMap;
import java.util.Map;

public class RoomManager {
	
	private static final Map<Integer, Room> rooms = new HashMap<Integer, Room>();
	
	
	//创建聊天室
	public static Room createRoom() {
		//随机生成roomId
		Integer roomId = (int) (Math.random() * 100000000);
		while(rooms.containsKey(roomId))
			roomId = (int) (Math.random() * 100000000);
		Room room = new Room(roomId);
		rooms.put(roomId, room);
		return room;
	}
	
	
	//销毁聊天室
	public static void destoryRoom(Integer roomId) {
		if(rooms.containsKey(roomId))
			rooms.remove(roomId);
	}
	
	
	//room添加成员
//	public static boolean joinRoom(String user, Integer roomId) {
//		Room room = rooms.get(roomId);
//		if(room == null)
//			return false;
//		else {
//			room.addUser(user);
//			return true;
//		}
//	}
	
	
	//room删除成员
	public static void quitRoom(String user, Integer roomId) {
		Room room = rooms.get(roomId);
		if(room == null)
			return;
		room.rmUser(user);
		if(room.getUsers().size() == 0)
			destoryRoom(roomId);
	}
	
	
//	public static Set<String> getAllUsersOfRoom(Integer roomId) {
//		Room room = rooms.get(roomId);
//		if(room == null)
//			return null;
//		else
//			return room.getUsers();
//	}
	
	
	public static Room getRoom(Integer roomId) {
		return rooms.get(roomId);
	}
	
	
	public static Integer countRoom() {
		return rooms.size();
	}

}
