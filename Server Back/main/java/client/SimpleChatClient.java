package client;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JOptionPane;

import org.springframework.web.client.RestTemplate;

public class SimpleChatClient {
	private String email;
	private RestTemplate restTemplate;
	private String url;
	private SimpleDateFormat formatter;
	
	public SimpleChatClient() {
		this.restTemplate = new RestTemplate();
		this.url = "http://localhost:8087";
		this.formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	}
	
	
	public SimpleChatClient register () {
		this.email = JOptionPane.showInputDialog("please identify using email:");
		return this;
	}
	
	public SimpleChatClient sendMessage() {
//		JOptionPane.showOptionDialog(null, "do you want to send a message?", "Chatroom", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, initialValue)
		if (JOptionPane.showConfirmDialog(null, "do you want to send a message?") == JOptionPane.YES_OPTION) {
			String message = JOptionPane.showInputDialog("type your message please: ");
			
			Map<String, Object> messageBoundary = new HashMap<>();
			messageBoundary.put("important", true);
			messageBoundary.put("type", "text");
			
			Map<String, Object> content = new HashMap<>();
			content.put("message", message);
			content.put("chatUser", this.email);
			messageBoundary.put("content", content);
			
			Map<String, Object> rv =
				this.restTemplate
					.postForObject(this.url + "/hello", messageBoundary, Map.class);
			JOptionPane.showMessageDialog(null, "your message received by the server:" + 
				((Map<String, Object>)rv.get("content")).get("message"));
		}			
		return this;
	}
	
	public SimpleChatClient getMessagesFromServer() {
		JOptionPane.showMessageDialog(null, 
		
			Stream.of(
				this.restTemplate
					.getForObject(this.url + "/all", Map[].class)
			)
			.filter(msg->
				msg.get("content") != null &&
				((Map<String, Object>)msg.get("content")).get("chatUser") != null 
//				&&	!((Map<String, Object>)msg.get("content")).get("chatUser").toString().equals(this.email)
				&&	!((Map<String, Object>)msg.get("content")).get("chatUser").toString().isEmpty()
			)
			
			.map(msg->{
				try {
					Map<String, Object> rv= new HashMap<>(((Map<String, Object>)msg.get("content")));
					rv.put("generated", formatter.parse(msg.get("generated").toString()));
					return rv;
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			})
			.map(content->"" + 
					content.get("chatUser") + 
					"> " + content.get("message")
					+ " (" + content.get("generated")+ ")")
	//		.forEach(line->System.out.println(line));
			
//			.map(msg->msg.get("id"))
			.map(str->str + "\n")
			.collect(Collectors.toList())
			.toArray(new String[0]));
		
		return this;
	}
	
	public static void main(String[] args) {
		SimpleChatClient client = new SimpleChatClient()
			.register();
		
		do {
			client
			.sendMessage()
			.getMessagesFromServer();
		}while (JOptionPane.showConfirmDialog(null, "continue?") == JOptionPane.YES_OPTION);
	
	}
}
