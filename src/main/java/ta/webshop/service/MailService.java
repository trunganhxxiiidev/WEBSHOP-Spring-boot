package ta.webshop.service;



import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ta.webshop.jpa.entity.User;

public interface MailService {
	void queue(Mail mail);
	default void sendProduct(String email,Integer id) {
		String url="http://localhost:8080/product/detail/"+id;
		String subject="Thông tin sản phẩm ";
		String textString=
				"<a href='"+url+"'>Xem chi tiết</a>";
		Mail mail =new Mail(email,subject,textString);
		this.queue(mail);
		System.out.println(email);
	}
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class Mail{
		String from;
		String to;
		String subject;
		String text;
		String cc;
		String bcc;
		String attachments;
		
		public Mail(String to, String subject, String text) {
			this("trunganh <trungaa2001@gmail.com>", to, subject, text, null, null, null);
		}
		
	}
	public void sendWelcome(User user);
}
