package utilities;

import java.util.HashMap;
import java.util.Map;

public class IOSAppElements {

	public static Map<String, String> map = new HashMap<String, String>();

	public IOSAppElements() {
		map.put("loginButton_xpath", "//android.widget.Button[@text='Log in']");
		map.put("username_xpath", "");
		map.put("password_xpath", "");
		map.put("login_submit_xpath", "");

	}

}
