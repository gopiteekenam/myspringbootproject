package com.biz2tech.app.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayOutputStream;

import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.biz2tech.app.FragrancenetserviceApp;
import com.biz2tech.app.config.ApplicationProperties;
import com.biz2tech.app.config.Constants;
import com.biz2tech.app.domain.User;

import io.github.jhipster.config.JHipsterProperties;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FragrancenetserviceApp.class)
public class MailServiceIntTest {

  @Autowired
  private JHipsterProperties jHipsterProperties;

  @Autowired
  private ApplicationProperties applicationProperties;

  @Autowired
  private MessageSource messageSource;

  @Autowired
  private SpringTemplateEngine templateEngine;

  @Spy
  private JavaMailSenderImpl javaMailSender;

  @Captor
  private ArgumentCaptor messageCaptor;

  private MailService mailService;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    doNothing().when(javaMailSender).send(any(MimeMessage.class));
    mailService = new MailService(jHipsterProperties, javaMailSender, messageSource, templateEngine,
        applicationProperties);
  }

  @Test
  public void testSendEmail() throws Exception {
    mailService.sendEmail("john.doe@example.com", "testSubject", "testContent", false, false);
    verify(javaMailSender).send((MimeMessage) messageCaptor.capture());
    MimeMessage message = (MimeMessage) messageCaptor.getValue();
    assertThat(message.getSubject()).isEqualTo("testSubject");
    assertThat(message.getAllRecipients()[0].toString()).isEqualTo("john.doe@example.com");
    assertThat(message.getFrom()[0].toString()).isEqualTo("test@localhost");
    assertThat(message.getContent()).isInstanceOf(String.class);
    assertThat(message.getContent().toString()).isEqualTo("testContent");
    assertThat(message.getDataHandler().getContentType()).isEqualTo("text/plain; charset=UTF-8");
  }

  @Test
  public void testSendHtmlEmail() throws Exception {
    mailService.sendEmail("john.doe@example.com", "testSubject", "testContent", false, true);
    verify(javaMailSender).send((MimeMessage) messageCaptor.capture());
    MimeMessage message = (MimeMessage) messageCaptor.getValue();
    assertThat(message.getSubject()).isEqualTo("testSubject");
    assertThat(message.getAllRecipients()[0].toString()).isEqualTo("john.doe@example.com");
    assertThat(message.getFrom()[0].toString()).isEqualTo("test@localhost");
    assertThat(message.getContent()).isInstanceOf(String.class);
    assertThat(message.getContent().toString()).isEqualTo("testContent");
    assertThat(message.getDataHandler().getContentType()).isEqualTo("text/html;charset=UTF-8");
  }

  @Test
  public void testSendMultipartEmail() throws Exception {
    mailService.sendEmail("john.doe@example.com", "testSubject", "testContent", true, false);
    verify(javaMailSender).send((MimeMessage) messageCaptor.capture());
    MimeMessage message = (MimeMessage) messageCaptor.getValue();
    MimeMultipart mp = (MimeMultipart) message.getContent();
    MimeBodyPart part =
        (MimeBodyPart) ((MimeMultipart) mp.getBodyPart(0).getContent()).getBodyPart(0);
    ByteArrayOutputStream aos = new ByteArrayOutputStream();
    part.writeTo(aos);
    assertThat(message.getSubject()).isEqualTo("testSubject");
    assertThat(message.getAllRecipients()[0].toString()).isEqualTo("john.doe@example.com");
    assertThat(message.getFrom()[0].toString()).isEqualTo("test@localhost");
    assertThat(message.getContent()).isInstanceOf(Multipart.class);
    assertThat(aos.toString()).isEqualTo("\r\ntestContent");
    assertThat(part.getDataHandler().getContentType()).isEqualTo("text/plain; charset=UTF-8");
  }

  @Test
  public void testSendMultipartHtmlEmail() throws Exception {
    mailService.sendEmail("john.doe@example.com", "testSubject", "testContent", true, true);
    verify(javaMailSender).send((MimeMessage) messageCaptor.capture());
    MimeMessage message = (MimeMessage) messageCaptor.getValue();
    MimeMultipart mp = (MimeMultipart) message.getContent();
    MimeBodyPart part =
        (MimeBodyPart) ((MimeMultipart) mp.getBodyPart(0).getContent()).getBodyPart(0);
    ByteArrayOutputStream aos = new ByteArrayOutputStream();
    part.writeTo(aos);
    assertThat(message.getSubject()).isEqualTo("testSubject");
    assertThat(message.getAllRecipients()[0].toString()).isEqualTo("john.doe@example.com");
    assertThat(message.getFrom()[0].toString()).isEqualTo("test@localhost");
    assertThat(message.getContent()).isInstanceOf(Multipart.class);
    assertThat(aos.toString()).isEqualTo("\r\ntestContent");
    assertThat(part.getDataHandler().getContentType()).isEqualTo("text/html;charset=UTF-8");
  }

  @Test
  public void testSendEmailFromTemplate() throws Exception {
    User user = new User();
    user.setLogin("john");
    user.setEmail("john.doe@example.com");
    user.setLangKey("en");
    mailService.sendEmailFromTemplate(user, "testEmail", "email.test.title");
    verify(javaMailSender).send((MimeMessage) messageCaptor.capture());
    MimeMessage message = (MimeMessage) messageCaptor.getValue();
    assertThat(message.getSubject()).isEqualTo("test title");
    assertThat(message.getAllRecipients()[0].toString()).isEqualTo(user.getEmail());
    assertThat(message.getFrom()[0].toString()).isEqualTo("test@localhost");
    assertThat(message.getContent().toString())
        .isEqualTo("<html>test title, http://127.0.0.1:8080, john</html>\n");
    assertThat(message.getDataHandler().getContentType()).isEqualTo("text/html;charset=UTF-8");
  }

  @Test
  public void testSendActivationEmail() throws Exception {
    User user = new User();
    user.setLangKey(Constants.DEFAULT_LANGUAGE);
    user.setLogin("john");
    user.setEmail("john.doe@example.com");
    mailService.sendActivationEmail(user);
    verify(javaMailSender).send((MimeMessage) messageCaptor.capture());
    MimeMessage message = (MimeMessage) messageCaptor.getValue();
    assertThat(message.getAllRecipients()[0].toString()).isEqualTo(user.getEmail());
    assertThat(message.getFrom()[0].toString()).isEqualTo("test@localhost");
    assertThat(message.getContent().toString()).isNotEmpty();
    assertThat(message.getDataHandler().getContentType()).isEqualTo("text/html;charset=UTF-8");
  }

  @Test
  public void testCreationEmail() throws Exception {
    User user = new User();
    user.setLangKey(Constants.DEFAULT_LANGUAGE);
    user.setLogin("john");
    user.setEmail("john.doe@example.com");
    mailService.sendCreationEmail(user);
    verify(javaMailSender).send((MimeMessage) messageCaptor.capture());
    MimeMessage message = (MimeMessage) messageCaptor.getValue();
    assertThat(message.getAllRecipients()[0].toString()).isEqualTo(user.getEmail());
    assertThat(message.getFrom()[0].toString()).isEqualTo("test@localhost");
    assertThat(message.getContent().toString()).isNotEmpty();
    assertThat(message.getDataHandler().getContentType()).isEqualTo("text/html;charset=UTF-8");
  }

  @Test
  public void testSendPasswordResetMail() throws Exception {
    User user = new User();
    user.setLangKey(Constants.DEFAULT_LANGUAGE);
    user.setLogin("john");
    user.setEmail("john.doe@example.com");
    mailService.sendPasswordResetMail(user);
    verify(javaMailSender).send((MimeMessage) messageCaptor.capture());
    MimeMessage message = (MimeMessage) messageCaptor.getValue();
    assertThat(message.getAllRecipients()[0].toString()).isEqualTo(user.getEmail());
    assertThat(message.getFrom()[0].toString()).isEqualTo("test@localhost");
    assertThat(message.getContent().toString()).isNotEmpty();
    assertThat(message.getDataHandler().getContentType()).isEqualTo("text/html;charset=UTF-8");
  }

  @Test
  public void testSendEmailWithException() throws Exception {
    doThrow(MailSendException.class).when(javaMailSender).send(any(MimeMessage.class));
    mailService.sendEmail("john.doe@example.com", "testSubject", "testContent", false, false);
  }

}
