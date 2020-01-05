package resttemplate.demo;

import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class RestTemplateDemo {
	
	public static void main(String[] args){
		String url = "https://www.baidu.com/s";
		RestTemplate template = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		// 表单提交
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String>  multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("wd", "chat");
		multiValueMap.add("id", "UTF-8");
		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(multiValueMap, headers );
		ResponseEntity<String> respongse = template.exchange(url, HttpMethod.GET, httpEntity, String.class);
		System.out.println(respongse.getBody());
	}
}
