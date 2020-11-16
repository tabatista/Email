package util;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

public class Email {

	private Message message;
	private Session session;
	private Multipart multipart;
	private BodyPart messageBodyPart;

	public Email(String username, String password, String hostSMTP) {
		super();
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", hostSMTP);
		props.put("mail.smtp.port", "587");

		session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		message = new MimeMessage(session);
		multipart = new MimeMultipart();
	}

	public Email(String destinario, String remetente, String username, String password, String hostSMTP)
			throws AddressException, MessagingException {
		super();
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", hostSMTP);
		props.put("mail.smtp.port", "587");

		session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		message = new MimeMessage(session);
		message.setFrom(new InternetAddress(remetente));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinario));
		multipart = new MimeMultipart();

	}

	/**
	 * PARA
	 * 
	 * @param destinario
	 *            email
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void setarDestinario(String destinario) throws AddressException, MessagingException {

		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinario));
	}

	/**
	 * DE
	 * 
	 * @param remetente
	 *            email
	 * @throws AddressException
	 * @throws MessagingException
	 */

	public void setarRemetente(String remetente) throws AddressException, MessagingException {
		message.setFrom(new InternetAddress(remetente));
	}

	/**
	 * Assunto
	 * 
	 * @param titulo
	 * @throws MessagingException
	 */
	public void setarTitulo(String titulo) throws MessagingException {

		message.setSubject(titulo);
	}

	/**
	 * Mensagem
	 * 
	 * @param mensagem
	 * @throws MessagingException
	 */
	public void escreverCorpoEmail(String mensagem) throws MessagingException {
		messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText(mensagem);

		multipart.addBodyPart(messageBodyPart);

	}

	/**
	 * Anexos - arquivo
	 * 
	 * @param nomeArquivo
	 * @param caminhoArquivo
	 * @throws MessagingException
	 */
	public void adicionarAnexo(String nomeArquivo, String caminhoArquivo) throws MessagingException {
		messageBodyPart = new MimeBodyPart();
		DataSource source = new FileDataSource(caminhoArquivo + nomeArquivo);
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(nomeArquivo);
		multipart.addBodyPart(messageBodyPart);
	}

	/**
	 * Okay
	 * 
	 * @throws MessagingException
	 */
	public void enviarEmail() throws MessagingException {

		message.setContent(multipart);
		Transport.send(message);
	}

}
