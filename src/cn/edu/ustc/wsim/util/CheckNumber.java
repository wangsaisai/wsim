package cn.edu.ustc.wsim.util;

import java.util.regex.Pattern;

public class CheckNumber {
	
	public static boolean isNumber(String str) {
		String regNumber = "[0-9]+";
		return Pattern.compile(regNumber).matcher(str).matches();
	}

	public static boolean isGroupNumber(String str) {
		String regGroupNumber ="[0-9]{1,8}";
		if(Pattern.compile(regGroupNumber).matcher(str).matches())
			return true;
		else
			return false;
	}
	
	public static void main(String[] args) {
		isNumber("11111");
		System.out.println(isNumber("011"));
	}
}
