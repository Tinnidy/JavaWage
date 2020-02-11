package other.com;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import interfacefile.com.Constant;

public class IfRange {
	public static int ifRange_integer(String min, String max) {
		if (min.isEmpty() && max.isEmpty()) {
			return Constant.LEFTRIGHT;
		} else if (min.isEmpty() && JudgeString.isNumeric_integer(max)){
			return Constant.LEFT;
		} else if (JudgeString.isNumeric_integer(min) && max.isEmpty()){
			return Constant.RIGHT;
		} else {
			if (!JudgeString.isNumeric_integer(min) || !JudgeString.isNumeric_integer(max)) {
				return Constant.ERROR;
			}
			if (Integer.parseInt(min) > Integer.parseInt(max)) {
				return Constant.ERROR;
			}
			return Constant.CORRRECT;
		}
	}
	
	public static int ifRange(String min, String max) {
		if (min.isEmpty() && max.isEmpty()) {
			return Constant.LEFTRIGHT;
		} else if (min.isEmpty() && JudgeString.isNumeric(max)){
			return Constant.LEFT;
		} else if (JudgeString.isNumeric(min) && max.isEmpty()){
			return Constant.RIGHT;
		} else {
			if (!JudgeString.isNumeric(min) || !JudgeString.isNumeric(max)) {
				System.out.println("abc");
				return Constant.ERROR;
			}
			if (Float.parseFloat(min) > Float.parseFloat(max)) {
				System.out.println("123");
				return Constant.ERROR;
			}
			return Constant.CORRRECT;
		}
	}
	
	public static boolean compare_date(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return true;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }
	
	public static int ifData(String min, String max) {
		if (min.isEmpty() && max.isEmpty()) {
			return Constant.LEFTRIGHT;
		} else if (min.isEmpty() && JudgeString.isValidDate(max)){
			return Constant.LEFT;
		} else if (JudgeString.isValidDate(min) && max.isEmpty()){
			return Constant.RIGHT;
		} else {
			if (!JudgeString.isValidDate(min) || !JudgeString.isValidDate(max)) {
				return Constant.ERROR;
			}
			if (compare_date(min, max)) {
				return Constant.ERROR;
			}
			return Constant.CORRRECT;
		}
	}
	
	public static String addCondition(int i, String variable, String min, String max) {
		String condition = null;
		if (i == Constant.CORRRECT) {
			condition = " and " + variable + ">='" + min + "'" + " and " + variable + "<='" + max + "'";
		} else if (i == Constant.LEFT) {
			condition = " and " + variable + "<='" + max + "'";
		} else if (i == Constant.RIGHT) {
			condition = " and " + variable + ">='" + min + "'";
		} else if (i == Constant.LEFTRIGHT) {
			condition = " ";
		}
		return condition;
	}
}
