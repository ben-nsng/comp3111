package hkust.cse.calendar.notification;

import java.io.*;
import java.net.*;

public class SmsService {

	private String username;
	private String address;
	private String message;
	
	private final String USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36";
	
	public SmsService(String username, String address, String message) {
		this.username = username;
		this.address = address;
		this.message = message;
	}
	
	public void Send() throws IOException {
		if(this.address.equals("")) return;

		
		String url = "http://notification.elp-spot.net/sms";
		String urlParameters = "username=" + username + "&address=" + address + "&message=" + message;
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con.setRequestProperty("Content-Length", String.valueOf(urlParameters.getBytes().length));
 
		
 
		// Send post request
		con.setUseCaches (false);
	    con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		
		StringBuffer response = new StringBuffer();
		int responseCode = con.getResponseCode();
		
		con.disconnect();
		
	}
}
