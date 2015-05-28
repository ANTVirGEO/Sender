package sendermain;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class EMAIL {
    public void Sender(List emails){
        System.out.println(emails);
        for (Object vah : emails)
            try {
                String SMTP_AUTH_USER = "ssrs@alfamed-nsk.ru";
                String SMTP_AUTH_PWD = "vahvah123";
                Properties props = new Properties();
                props.put("mail.transport.protocol", "smtps");
                props.put("mail.smtps.host", SMTP_AUTH_USER);
                props.put("mail.smtps.auth", "true");
                props.put("mail.smtp.sendpartial", "true");
                props.put("mail.mime.charset", "UTF-8");
                Session session = Session.getDefaultInstance(props);
                session.setDebug(true);
                Transport transport = session.getTransport();
                transport.connect("smtp.gmail.com", 465, SMTP_AUTH_USER, SMTP_AUTH_PWD);
                MimeMessage message = new MimeMessage(session);
                message.setSubject("Ежедневный отчет по оборотам ООО ЛДЦ \"АльфаМед\"");
                MimeBodyPart p1 = new MimeBodyPart();
                p1.setText("Файл PDF в прикреплении. Если у вас нет программы чтения PDF файлов\n" +
                        ", скачайте бесплатную программу здесь - http://www.stdutility.com/download/stduviewer.exe\n" +
                        ".Хорошего дня!");
                MimeBodyPart p2 = new MimeBodyPart();
                FileDataSource fds = new FileDataSource("\\\\Ws35000\\общая\\report_income.pdf");
                p2.setDataHandler(new DataHandler(fds));
                p2.setFileName(fds.getName());
                Multipart mp = new MimeMultipart();
                mp.addBodyPart(p1);
                mp.addBodyPart(p2);
                message.setContent(mp);
                String hav = vah.toString();
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(hav));
                message.setSentDate(new Date());
                transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            } catch (Exception ignored) {
            }
    }
}
