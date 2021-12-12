package com.java.dao;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class MyPage_ModifyCheckDAO {

	  public static boolean regularID(String id) { //�������� String id1 =
			/*
			 * Pattern.compile("\\s").matcher(id).replaceAll(""); String id2 =
			 * id1.replaceAll(" ", "");
			 */
			if (Pattern.matches("^[a-zA-z0-9]{6,15}$", id)) {
				return true;

			} else {
				
				return false;
			}
		}

		public static boolean regularPW(String pw) {
			if (Pattern.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,15}$", pw)) {
				return true;
				
			} else {
					
				return false;
			}
		}

		public static boolean regularName(String name) {
			if (Pattern.matches("^[가-힣]{2,4}$", name)) {
				return true;

			} else {
				return false;
			}
		}

		/*
		 * public static int regularAge(String age) { if(Pattern.matches("/^[0-9]$/",
		 * age)) { return 1; }else{ return 0; } }
		 */

		public static boolean regularPhone(String phone) {
			if (Pattern.matches("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$", phone)) {
				return true;

			} else {
				return false;
			}
		}
		
		public static boolean regularEmail(String Email) {
			if (Pattern.matches("^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$", Email)) {
				return true;

			} else {
				return false;
			}
		}
	
}
