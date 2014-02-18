package cn.edu.ustc.wsim.enumerates;

//枚举，判断用户间关系
public enum UserRelation {
	NOTFRIEND,	//非好友
	YN,			//YN单项好友，2是1的好友，1不是2的好友
	NY,			//单向好友，
	FRIEND		//互为好友
}
