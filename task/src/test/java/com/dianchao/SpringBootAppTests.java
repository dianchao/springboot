package com.dianchao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBootAppTests {

    @Autowired
    JavaMailSenderImpl mailSender;

    @Test
    public void contextLoads(){
        SimpleMailMessage message = new SimpleMailMessage();
        //邮件设置
        message.setSubject("通知-今晚开会");
        message.setText("今晚7:30开会");
        message.setTo("zdc152419@163.com");
        message.setFrom("947389057@qq.com");
        mailSender.send(message);
    }

    @Test
    public void test02() throws MessagingException {
        //创建一个复杂的消息邮件
        MimeMessage mimeMailMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);

        mimeMessageHelper.setSubject("通知-今晚开会");
        mimeMessageHelper.setText("<b style='color:red'>今晚7:30开会</b>", true);
        mimeMessageHelper.setTo("zdc152419@163.com");
        mimeMessageHelper.setFrom("947389057@qq.com");

        //上传附件
        mimeMessageHelper.addAttachment("1.txt", new File("C:\\Users\\zdc\\Desktop\\1.txt"));
        mimeMessageHelper.addAttachment("2.txt", new File("C:\\Users\\zdc\\Desktop\\2.txt"));

        mailSender.send(mimeMailMessage);
    }
}
