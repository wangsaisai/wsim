package cn.edu.ustc.wsim.util;

import java.util.regex.Pattern;

public class CheckEmail {

	public boolean isEmail(String str) {
		String regemail ="[a-zA-Z0-9_]+@[a-zA-Z0-9]+(\\.[a-zA-Z]+)+";
		if(Pattern.compile(regemail).matcher(str).matches())
			return true;
		else
			return false;
	}
	
}
