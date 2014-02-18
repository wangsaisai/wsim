package cn.edu.ustc.wsim.datastructure;

public class GlobalFinal {
	
	private static String serverIP;
	
	private static String defaultFriendGroupName;

	public static String getServerIP() {
		return serverIP;
	}

	public static void setServerIP(String serverIP) {
		GlobalFinal.serverIP = serverIP;
	}

	public static String getDefaultFriendGroupName() {
		return defaultFriendGroupName;
	}

	public static void setDefaultFriendGroupName(String defaultFriendGroupName) {
		GlobalFinal.defaultFriendGroupName = defaultFriendGroupName;
	}
	
	

}
