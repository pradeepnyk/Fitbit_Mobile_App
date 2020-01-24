package pageObjects;

import utilities.IOSAppElements;

public class IOSContentSelection implements ContentSelection {

	public String get_xpath(String element_name) {

		System.out.println("xpath" + element_name);
		return IOSAppElements.map.get(element_name);

	}
}
