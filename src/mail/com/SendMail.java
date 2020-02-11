package mail.com;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class SendMail extends Thread{
	private String USER = "2492736825@qq.com";
	private String PWD = "pptydenishiweajc";
	private String targetAddress;
	
    private String subject = "标题";
    private String content = "内容";
    
    @Override
    public void run() {
        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.qq.com");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        /* 使用JavaMail发送邮件的5个步骤 */
        try{
            /* 1、创建session */
            Session session = Session.getInstance(prop);
            /* 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态 */
            session.setDebug(true);
            /* 2、通过session得到transport对象 */
            Transport ts = session.getTransport();
            /* 3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人 */
            ts.connect("smtp.qq.com", USER, PWD); /* qq的密码需要开启授权码，把授权码放到这里就行 */
            /* 4、创建邮件 */
            Message message = createSimpleMail(session);
            /* 5、发送邮件 */
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();
        }catch (Exception e){
        	JOptionPane.showMessageDialog(null, "找回失败");
            e.printStackTrace();
        }
    }

    public  MimeMessage createSimpleMail(Session session) throws Exception {
        /* 创建邮件对象 */
        MimeMessage message = new MimeMessage(session);
        /* 指明邮件的发件人 */
        message.setFrom(new InternetAddress(USER));
        /* 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发 */
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(targetAddress));
        /* 邮件的标题 */
        message.setSubject(subject);
        /* 邮件的文本内容 */
        message.setContent(content, "text/html;charset=UTF-8");
        /* 返回创建好的邮件对象 */
        return message;
    }
    
    public void setTargetAddress(String targetAddress) {
        this.targetAddress = targetAddress;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
}
