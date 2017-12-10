package version1;

import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

public class EmailCtrl implements Serializable{
  public static final String HOST = "smtp.qq.com";
  public static final String PROTOCOL = "smtp";
  public static final String PORT = "465";
  private static final String FROM = "2218234907@qq.com";
  public static final String PWD = "bkuxxumcjjnudjda";

  private static Session getSession() {
    MailSSLSocketFactory sf;
    Properties props = System.getProperties();
    try {
      sf = new MailSSLSocketFactory();

      sf.setTrustAllHosts(true);
      props.put("mail.smtp.ssl.socketFactory", sf);
    } catch (GeneralSecurityException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    props.setProperty("mail.debug", "true");
    props.put("mail.smtp.ssl.enable", "true");

    props.setProperty("mail.smtp.host", HOST);
    props.setProperty("mail.smtp.port", PORT);
    props.setProperty("mail.smtp.socketFactory.port", PORT);
    props.setProperty("mail.smtp.auth", "true");

    Authenticator authenticator = new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(FROM, PWD);
      }

    };
    Session session = Session.getDefaultInstance(props, authenticator);

    return session;
  }

  public static void sendAccountActivateEmail(String email, String uuid) {
    Session session;
    try {
      session = getSession();
      MimeMessage message = new MimeMessage(session);
      message.setSubject("激活你的账户，点击如下连接：");
      message.setSentDate(new Date());
      message.setFrom(new InternetAddress(FROM));
      message.setRecipient(RecipientType.TO, new InternetAddress(email));
      message.setContent("<a  target='_BLANK' href=''>" + generateActivateLink(uuid) + "</a>",
          "text/html;charset=utf-8");
      message.saveChanges();
      Transport.send(message);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static String generateActivateLink(String uuid) {
    return "http://tomato.applinzi.com/Active.servlet?checkcode=" + uuid;
  }

}
