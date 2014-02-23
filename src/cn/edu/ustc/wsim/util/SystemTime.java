package cn.edu.ustc.wsim.util;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemTime {
	
	public static String getSystemTime() {
		Date date = new Date();
		Format fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return fm.format(date);
	}

}
