package utilities;

import java.util.HashMap;
import java.util.Map;

public class AndroidAppElemnts {
	
	public static Map<String, String> map = new HashMap<String, String>();
	
	public AndroidAppElemnts() {
		map.put("loginButton_xpath", "//android.widget.Button[@resource-id='com.fitbit.FitbitMobile:id/btn_log_in']");
		map.put("username_xpath", "//android.widget.EditText[@resource-id='com.fitbit.FitbitMobile:id/login_email']");
		map.put("password_xpath", "//android.widget.EditText[@resource-id='com.fitbit.FitbitMobile:id/login_password']");
		map.put("login_submit_xpath", "//android.widget.Button[@resource-id='com.fitbit.FitbitMobile:id/login_button']");
		
		map.put("homepage", "//android.widget.ImageButton[@resource-id='com.fitbit.FitbitMobile:id/userAvatar']");
		map.put("homepage_check", "//android.widget.Button[@text='TODAY']");
		
		map.put("location_permission_screen", "//android.widget.TextView[@text='Location permission required to sync']");
		map.put("close_button", "//android.widget.ImageButton");
		
		map.put("error_msg", "//*[@resource-id='android:id/message']");
		map.put("error_message_ok_btn", "//android.widget.Button[@resource-id='android:id/button1']");
		
	}
	
}
