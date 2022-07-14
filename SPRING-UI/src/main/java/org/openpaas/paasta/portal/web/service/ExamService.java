package org.openpaas.paasta.portal.web.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service
 * 해당 클래스가 Service라는 것을 명시한다.
 */
@Service
public class ExamService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExamService.class);

	@Autowired
	RestTemplate eurekaRest;

	public Map<String, Object> eurekaRest(String reqUrl, HttpMethod httpMethod, Object bodyObject) {

		String requestURL = "http://" + reqUrl;
		HttpHeaders reqHeaders = new HttpHeaders();
		HttpEntity<Object> reqEntity = new HttpEntity<>(bodyObject, reqHeaders);

		ResponseEntity<Map> resEntity = this.eurekaRest.exchange(requestURL, httpMethod, reqEntity, Map.class);
		Map<String, Object> resultMap = resEntity.getBody();

		ExamService.LOGGER.info("eurekaRest response Http status code :: {}", resEntity.getStatusCode());
		ExamService.LOGGER.info("eurekaRest response Http status code :: {}", resEntity.getBody());
		return resultMap;
	}

	public <T> Map<String, Object> zuulRest(String reqUrl, HttpMethod httpMethod, Object bodyObject) {

		RestTemplate restTemplate = new RestTemplate();
		String requestURL = "http://" + reqUrl;
		HttpHeaders reqHeaders = new HttpHeaders();
		HttpEntity<Object> reqEntity = new HttpEntity<>(bodyObject, reqHeaders);

		ResponseEntity<Map> resEntity = restTemplate.exchange(requestURL, httpMethod, reqEntity, Map.class);
		Map<String, Object> resultMap = resEntity.getBody();
		ExamService.LOGGER.info("zuulRest reqUrl :: {} || resultEntity type :: {}", requestURL, resEntity.getHeaders().getContentType());
		ExamService.LOGGER.info("zuulRest response Http status code :: {}", resEntity.getStatusCode());
		return resultMap;
	}
}
