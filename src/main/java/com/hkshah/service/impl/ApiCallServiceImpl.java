package com.hkshah.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.hkshah.service.ApiCallService;

@Service
public class ApiCallServiceImpl implements ApiCallService {

	private static final Logger LOG = Logger.getLogger(ApiCallServiceImpl.class);

	@Scheduled(fixedDelay = 60000)
	@Async
	@Override
	public void callMcsApi() {

		LOG.info("Scheduler started");
		try {
			String url = new String(
					"https://rapidvaluedev-rvsmcsdomain.mobileenv.us2.oraclecloud.com:443/mobile/custom/PwC_TimeEntryQueue_Services/create/timeEntryExecution");

			HttpGet httpGet = new HttpGet(url.toString());
			httpGet.setHeader("Oracle-Mobile-Backend-Id", "7226a4ab-d57c-4f3e-9bb4-80653024f153");
			httpGet.setHeader("Authorization",
					"Basic UlZTTUNTRE9NQUlOX1JBUElEVkFMVUVERVZfTU9CSUxFX0FOT05ZTU9VU19BUFBJRDpGZHNqLndyYWs1MmdtcA==");
			httpGet.setHeader("Content-Type", "application/json");

			HttpClient httpClient = HttpClientBuilder.create().build();

			HttpResponse httpResponse = httpClient.execute(httpGet);

			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(httpResponse.getEntity().getContent()));

			String line = "";

			StringBuilder jsonStringBuilder = new StringBuilder();
			while ((line = bufferedReader.readLine()) != null) {
				jsonStringBuilder.append(line);
			}
			LOG.info("Response : " + jsonStringBuilder.toString());
		} catch (Exception e) {
			LOG.error("Exception : ", e);
		}
		LOG.info("Scheduler End");
	}
}