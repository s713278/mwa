package net.mwa.common.utils;

public class Constants {

	public static enum Pagenames{
		INDEX("index"),
		HOME("home");
		
		private String  pageName ;
		Pagenames(String s){
			pageName = s;
		}
		
		
	}
	
	
	public static enum Roles{
		ADMIN("ADMIN"),
		MEMBER("MEMBER");
		
		private String  role ;
		Roles(String s){
			role = s;
		}
		
	}
}
