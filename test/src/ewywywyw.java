import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ewywywyw {
	
	public static void main(String[] args) {
		
		
		System.out.println("HHHHHHHHHHHHH");
		
		String u = "http://www.ergew.com";
		String u0 = "http://www.ergew.com?ghewhw=1";
		String u1 = "https://www.ergew.com?ghewhw=1";
		
		System.out.println(Pattern.matches("http[s]{0,1}://[^?]*", u));
		System.out.println(Pattern.matches("http[s]{0,1}://[^?]*", u0));
		System.out.println(Pattern.matches("http[s]{0,1}://[^?]*", u1));
		System.out.println(Pattern.matches("http[s]{0,1}://[^?]*\\?[^?]*", u0));
		
		Pattern p = Pattern.compile("http[s]{0,1}://[^?]*");
		Matcher m = p.matcher(u);
		System.out.println(m);
		System.out.println(Pattern.quote("http[s]{0,1}://[^?]*"));
		if(m.find())
			System.out.println(m.group(0));
	}

}
