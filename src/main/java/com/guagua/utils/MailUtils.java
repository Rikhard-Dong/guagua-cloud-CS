package com.guagua.utils;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * @author ride
 * @date 18-3-15 下午2:39
 * <p>
 * 发送邮件工具
 */
public class MailUtils implements Runnable {
    private String email;       // 接收邮箱
    private String subject;     // 主题
    private String content;     // 接收内容

    public MailUtils(String email, String subject, String content) {
        this.email = email;
        this.subject = subject;
        this.content = content;
    }

    public void run() {
        final String from = "1270458214@qq.com";
        String host = "smtp.qq.com";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.auth", "true");

        try {
            // qq 邮箱需要以下代码
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.socketFactory", sf);

            // 1. 获取session对象
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, "slzgtxgddysubadh");
                }
            });
            // 2. 创建邮件对象
            Message message = new MimeMessage(session);
            // 2.1 设置发件人
            message.setFrom(new InternetAddress(from));
            // 2.2 设置收件人
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            // 2.3 设置邮件主题
            message.setSubject(subject);
            // 2.4 设置邮件内容
            message.setContent(content, "text/html;charset=UTF-8");
            // 3. 发送邮件
            Transport.send(message);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
