//package com.appsdeveloperblog.app.ws;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.regions.Regions;
//import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
//import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceAsync;
//import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
//
//@Configuration
//public class AppConfig {
//
//	@Value("${AWS_ACCESS_KEY_ID}")
//	private String AWS_ACCESS_KEY_ID;
//
//	@Value("${AWS_SECRET_KEY}")
//	private String AWS_SECRET_KEY;
//
//	@Bean
//	public AmazonSimpleEmailServiceAsync amazonSimpleEmailService() {
//		BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials("AKIAIMO3ZOAMP3JG36TQ",
//				"x3E7Z3liMkyVJktfFRth4fKggDaRZgMIOWTX+tR");
//
//		AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard()
//				.withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials)).withRegion(Regions.EU_WEST_1)
//				.build();
//
//		return (AmazonSimpleEmailServiceAsync) client;
////		return AmazonSimpleEmailServiceAsyncClient.asyncBuilder()
////				.withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials)).withRegion(Regions.EU_WEST_1)
////				.build();
//	}
//
//}
