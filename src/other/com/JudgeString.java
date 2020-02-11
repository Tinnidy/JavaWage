package other.com;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JudgeString {
	final static Map<Integer, String> zoneNum = new HashMap<Integer, String>();
    static {
        zoneNum.put(11, "北京");
        zoneNum.put(12, "天津");
        zoneNum.put(13, "河北");
        zoneNum.put(14, "山西");
        zoneNum.put(15, "内蒙古");
        zoneNum.put(21, "辽宁");
        zoneNum.put(22, "吉林");
        zoneNum.put(23, "黑龙江");
        zoneNum.put(31, "上海");
        zoneNum.put(32, "江苏");
        zoneNum.put(33, "浙江");
        zoneNum.put(34, "安徽");
        zoneNum.put(35, "福建");
        zoneNum.put(36, "江西");
        zoneNum.put(37, "山东");
        zoneNum.put(41, "河南");
        zoneNum.put(42, "湖北");
        zoneNum.put(43, "湖南");
        zoneNum.put(44, "广东");
        zoneNum.put(45, "广西");
        zoneNum.put(46, "海南");
        zoneNum.put(50, "重庆");
        zoneNum.put(51, "四川");
        zoneNum.put(52, "贵州");
        zoneNum.put(53, "云南");
        zoneNum.put(54, "西藏");
        zoneNum.put(61, "陕西");
        zoneNum.put(62, "甘肃");
        zoneNum.put(63, "青海");
        zoneNum.put(64, "新疆");
        zoneNum.put(71, "台湾");
        zoneNum.put(81, "香港");
        zoneNum.put(82, "澳门");
        zoneNum.put(91, "外国");
    }
     
    final static int[] PARITYBIT = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
    final static int[] POWER_LIST = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
	
	/* 纯整数字 */
	public static boolean isNumeric_integer(String str){
		for (int i = str.length();--i>=0;){  
			if (!Character.isDigit(str.charAt(i))){
				return false;
			}
		}
		return true;
	}
	
	/* 数字，包括整数，小数*/
	public static boolean isNumeric(String str) {
		String res=String.valueOf(str);
        return res.matches("[\\+-]?[0-9]*(\\.[0-9])?([eE][\\+-]?[0-9]+)?");
	}
	
	/* 是否包含中文 */
	public static boolean isChinese(String str) {
		String regEx = "[\u4e00-\u9fa5]";
		Pattern pat = Pattern.compile(regEx);
		Matcher matcher = pat.matcher(str);
		boolean flg = false;
		if (matcher.find())
			flg = true;
		return flg;
	}
	
	/* 纯字母 */
	public static boolean isEnglish(String str){
		return str.matches("^[a-zA-Z]*");
	}
	
	/* 日期 */
	public static boolean isValidDate(String str) {
		boolean convertSuccess=true;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			format.setLenient(false);
			format.parse(str);
		} catch (ParseException e) {
			convertSuccess=false;
		}
		return convertSuccess;
	}
	/* qq邮箱 */
    public static boolean isEmailAddress(String str) {
        boolean isEmail_matcher = str.matches("[1-9]\\d{2,11}+@qq.com");
        if (isEmail_matcher)
            return true;
        return false;
    }
	/* 手机号 */
	public static boolean isPhoneNumber(String str) {
        boolean isPhoneNum_matcher = str.matches("1[34578]\\d{9}");
        if (isPhoneNum_matcher)
            return true;
        return false;
    }
	
    /* 身份证 */
    public static boolean isIDCard(String certNo){
        if(certNo == null || (certNo.length() != 15 && certNo.length() != 18))
            return false;
        final char[] cs = certNo.toUpperCase().toCharArray();
        //校验位数
        int power = 0;
        for(int i=0; i<cs.length; i++){
            if(i==cs.length-1 && cs[i] == 'X')
                break;//最后一位可以 是X或x
            if(cs[i]<'0' || cs[i]>'9')
                return false;
            if(i < cs.length -1){
                power += (cs[i] - '0') * POWER_LIST[i];
            }
        }
         
        //校验区位码
        if(!zoneNum.containsKey(Integer.valueOf(certNo.substring(0,2)))){
            return false;
        }
         
        //校验年份
        String year = certNo.length() == 15 ? getIdcardCalendar() + certNo.substring(6,8) :certNo.substring(6,10);
         
        final int iyear = Integer.parseInt(year);
        if(iyear < 1900 || iyear > Calendar.getInstance().get(Calendar.YEAR))
            return false;//1900年的PASS，超过今年的PASS
         
        //校验月份
        String month = certNo.length() == 15 ? certNo.substring(8, 10) : certNo.substring(10,12);
        final int imonth = Integer.parseInt(month);
        if(imonth < 1 || imonth > 12){
            return false;
        }
         
        //校验天数      
        String day = certNo.length() ==15 ? certNo.substring(10, 12) : certNo.substring(12, 14);
        final int iday = Integer.parseInt(day);
        if(iday < 1 || iday > 31)
            return false;       
         
        //校验"校验码"
        if(certNo.length() == 15)
            return true;
        return cs[cs.length -1] == PARITYBIT[power % 11];
    }
     
    private static int getIdcardCalendar() {        
         GregorianCalendar curDay = new GregorianCalendar();
         int curYear = curDay.get(Calendar.YEAR);
         int year2bit = Integer.parseInt(String.valueOf(curYear).substring(2));          
         return  year2bit;
    }
    
    /* 银行卡卡号 */
    public static boolean isBankCard(String bankCard) {
    	if(bankCard.length() < 15 || bankCard.length() > 19) {
    		return false;
    	}
    	char bit = getBankCardCheckCode(bankCard.substring(0, bankCard.length() - 1));
    	if(bit == 'N'){
    		return false;
    	}
    	return bankCard.charAt(bankCard.length() - 1) == bit;    
   }
       
   public static char getBankCardCheckCode(String nonCheckCodeBankCard) {
	   if(nonCheckCodeBankCard == null || nonCheckCodeBankCard.trim().length() == 0 || !nonCheckCodeBankCard.matches("\\d+")) {
           //如果传的不是数据返回N
           return 'N';
	   }
       char[] chs = nonCheckCodeBankCard.trim().toCharArray();
       int luhmSum = 0;
       for(int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
           int k = chs[i] - '0';    
           if(j % 2 == 0) {    
               k *= 2;    
               k = k / 10 + k % 10;    
           }
           luhmSum += k;               
       }
       return (luhmSum % 10 == 0) ? '0' : (char)((10 - luhmSum % 10) + '0');
   }
   
   /**
    * 用户身份证号码的打码隐藏加星号加*
    * <p>18位和非18位身份证处理均可成功处理</p>
    * <p>参数异常直接返回null</p>
    *
    * @param idCardNum 身份证号码
    * @param front     需要显示前几位
    * @param end       需要显示末几位
    * @return 处理完成的身份证
    */
   public static String idMask(String idCardNum, int front, int end) {
       //身份证不能为空
       if (idCardNum.isEmpty()) {
           return null;
       }
       //需要截取的长度不能大于身份证号长度
       if ((front + end) > idCardNum.length()) {
           return null;
       }
       //需要截取的不能小于0
       if (front < 0 || end < 0) {
           return null;
       }
       //计算*的数量
       int asteriskCount = idCardNum.length() - (front + end);
       StringBuffer asteriskStr = new StringBuffer();
       for (int i = 0; i < asteriskCount; i++) {
           asteriskStr.append("*");
       }
       String regex = "(\\w{" + String.valueOf(front) + "})(\\w+)(\\w{" + String.valueOf(end) + "})";
       return idCardNum.replaceAll(regex, "$1" + asteriskStr + "$3");
   }
   
   /* 判断一个数是否小于一个数 */
   public static boolean isComparativeSize_integer(String constant, String a) {
	   if (isNumeric_integer(constant) && isNumeric_integer(a)) {
		   if (Integer.parseInt(constant) >= Integer.parseInt(a)) {
			   return true;
		   }
	   }
	   return false;
   }
}
