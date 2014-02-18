package cn.edu.ustc.wsim.util;

import java.util.regex.Pattern;

public class CheckGroupNumber {

	public boolean isGroupNumber(String str) {
		String regemail ="[0-9]{1,8}";
		if(Pattern.compile(regemail).matcher(str).matches())
			return true;
		else
			return false;
	}
	
	public static void main(String[] args) {
		CheckGroupNumber cn = new CheckGroupNumber();
		System.out.print(cn.isGroupNumber("11"));
	}
}
