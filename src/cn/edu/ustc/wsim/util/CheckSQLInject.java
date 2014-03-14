package cn.edu.ustc.wsim.util;

public class CheckSQLInject {

	public static boolean isSQLInject(String str) {
		String inj_str = "'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+|,";
		String inj_stra[] = inj_str.split("\\|");
		for (int i = 0; i < inj_stra.length; i++) {
			if (str.indexOf(inj_stra[i]) >= 0) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(isSQLInject("afdasfs an 1==1"));

	}
}
