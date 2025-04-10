package com.meetup.meetup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;
@SpringBootApplication
public class MeetupApplication {
	public static void main(String[] args) {
		SpringApplication.run(MeetupApplication.class, args); // 이게 내장 톰캣 구동
	}


	@Bean
	public FilterRegistrationBean<HiddenHttpMethodFilter> hiddenHttpMethodFilter() {
		FilterRegistrationBean<HiddenHttpMethodFilter> filterRegistrationBean = new FilterRegistrationBean<>(new HiddenHttpMethodFilter());
		filterRegistrationBean.setOrder(1);
		return filterRegistrationBean;
	}

}

