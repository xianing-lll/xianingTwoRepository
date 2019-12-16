package com.example;

import com.alibaba.fastjson.JSON;
import com.example.domain.Jwt;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.UUID;


class SmsServerAuth2ApplicationTests {


	@Test
	void contextLoads() {
		System.out.println(UUID.randomUUID().toString());
	}
	@Test
	void test() throws IOException {
		String url = "http://localhost:9001/oauth/token?client_id=c1&client_secret=123456&grant_type=password&username=1003&password=123456";
		//请求内容{'username':'1003','password':'123456'}
		String JSONBody ="";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("Content-Type", "application/json");
		httpPost.setEntity(new StringEntity(JSONBody));
		CloseableHttpResponse response = httpClient.execute(httpPost);
		System.out.println(response.getStatusLine().getStatusCode() + "\n");
		HttpEntity entity = response.getEntity();
		String jwtSuccess = EntityUtils.toString(entity, "UTF-8");
		System.out.println(jwtSuccess);
		Jwt jwt= JSON.parseObject(jwtSuccess,Jwt.class);
		System.out.println(jwt.getAccess_token());
		System.out.println(jwt.getScope());
		response.close();
		httpClient.close();

	}

}
