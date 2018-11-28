package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	public static Date stringToDate(String dataStr) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		Date data = null;
		try {
			data = dateFormat.parse(dataStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return data;
	}

}
