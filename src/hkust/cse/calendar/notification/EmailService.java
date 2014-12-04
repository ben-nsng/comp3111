package hkust.cse.calendar.notification;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class EmailService {
	
	private String username;
	private String address;
	private String message;
	private String subject;
	
	private final String USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36";
	
	public EmailService(String username, String address, String subject, String message) {
		this.username = username;
		this.address = address;
		this.message = message;
		this.subject = subject;
	}
	
	public void Send() throws IOException {


		String url = "http://notification.elp-spot.net/email";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
 
		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
 
		String urlParameters = "username=" + username + "&address=" + address + "&message=" + message + "&subject=" + subject;
 
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		
	}
}
