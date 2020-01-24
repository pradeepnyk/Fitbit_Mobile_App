package pageObjects;
import utilities.AndroidAppElemnts;

public class AndroidContentSelection implements ContentSelection{

	public String get_xpath(String element_name) {		
		return AndroidAppElemnts.map.get(element_name);
		
	}

}
