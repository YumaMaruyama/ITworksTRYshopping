package com.example.demo;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class WebConfig {

	@Bean
	public MessageSource messageSource() {

		ReloadableResourceBundleMessageSource bean = new ReloadableResourceBundleMessageSource();

		// メッセージのプロパティファイル名（デフォルト）を指定
		// 下記ではmessages.properticeファイルがセット
		bean.setBasename("classpath:messages");
		bean.setDefaultEncoding("UTF-8");

		return bean;

	}

	@Bean
	public LocalValidatorFactoryBean localValidatorFactoryBean() {

		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.setValidationMessageSource(messageSource());

		return localValidatorFactoryBean;
	}
}
