package com.biz2tech.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import io.github.jhipster.config.JHipsterDefaults;

/**
 * Properties specific to Fragrancenetservice.
 * <p>
 * Properties are configured in the application.yml file. See
 * {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {
  private final Mail mail = new Mail();

  public Mail getMail() {
    return mail;
  }

  public static class Mail {
    private String baseUrlUi = JHipsterDefaults.Mail.baseUrlUi;

    public String getBaseUrlUi() {
      return baseUrlUi;
    }

    public void setBaseUrlUi(String baseUrlUi) {
      this.baseUrlUi = baseUrlUi;
    }


  }
}
