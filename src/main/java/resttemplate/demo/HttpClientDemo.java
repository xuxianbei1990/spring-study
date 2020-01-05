package resttemplate.demo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.util.EntityUtils;

public class HttpClientDemo {

	// http get 
	public void testHttpGet() throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(
				"http://192.168.150.97:8080/data-center/service/homePage/collectinfo?sdyf=201709");
		CloseableHttpResponse response = httpclient.execute(httpget);
		try {
			HttpEntity entity = response.getEntity();
			
			entity = new BufferedHttpEntity(entity);
			System.out.println(entity.getContentType().getValue());
			//解析内容
			System.out.println(EntityUtils.toString(entity));
			
			InputStream in = entity.getContent();
			byte[] bs = new byte[(int)entity.getContentLength()];
			in.mark(bs.length);
			in.read(bs);
			System.out.println("Bytes " + new String(bs));
			Charset charset = ContentType.get(entity).getCharset();
			InputStreamReader readIn = new InputStreamReader(in, charset);
			StringBuilder sb = new StringBuilder();
			in.reset();
			int read = readIn.read();
			while ( read != -1) {
				sb.append((char) read);
				read = readIn.read();
			}
			System.out.println(sb);
			in.close();
			// do your self
		} finally {
			response.close();
		}
	}

	/*
	 * HttpClient支持HTTP/1.1这个版本定义的所有Http方法：GET,HEAD,POST,PUT,DELETE,
	 * TRACE和OPTIONS。对于每一种http方法，HttpClient都定义了一个相应的类：HttpGet, HttpHead,
	 * HttpPost, HttpPut, HttpDelete, HttpTrace和HttpOpquertions。
	 */
	// https://www.baidu.com/baidu?&ie=utf-8&word=qq

	// 拼接url
	void testUrl() throws URISyntaxException {
		URI uri = new URIBuilder()
				// 协议
				.setScheme("http").setHost("www.baidu.com").setPath("/baidu").setParameter("ie", "utf-8")
				.setParameter("word", "qq").build();
		HttpGet httpget = new HttpGet(uri);
		System.out.println(httpget.getURI());
	}

	// http响应
	void testResponse() {
		HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");

		System.out.println(response.getProtocolVersion());
		System.out.println(response.getStatusLine().getStatusCode());
		System.out.println(response.getStatusLine().getReasonPhrase());
		System.out.println(response.getStatusLine().toString());
	}

	// 消息头
	void testResponse2() {
		HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
		response.addHeader("Set-Cookie", "c1=a; path=/; domain=localhost");
		response.addHeader("Set-Cookie", "c2=b; path=\"/\", c3=c; domain=\"localhost\"");
		Header h1 = response.getFirstHeader("Set-Cookie");
		System.out.println(h1);
		Header h2 = response.getLastHeader("Set-Cookie");
		System.out.println(h2);
		Header[] hs = response.getHeaders("Set-Cookie");
		System.out.println(hs.length);

		System.out.println("----------迭代器---------");
		HeaderIterator it = response.headerIterator("Set-Cookie");
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		System.out.println("----------详细迭代器---------");
		HeaderElementIterator it1 = new BasicHeaderElementIterator(response.headerIterator("Set-Cookie"));

		while (it1.hasNext()) {
			HeaderElement elem = it1.nextElement();
			System.out.println(elem.getName() + " = " + elem.getValue());
			NameValuePair[] params = elem.getParameters();
			for (int i = 0; i < params.length; i++) {
				System.out.println(" " + params[i]);
			}
		}

	}

	// http 实体
	void testEntity() throws ParseException, IOException {
		StringEntity myEntity = new StringEntity("important message", ContentType.create("text/plain", "UTF-8"));

		System.out.println(myEntity.getContentType());
		System.out.println(myEntity.getContentLength());
		System.out.println(EntityUtils.toString(myEntity));
		System.out.println(EntityUtils.toByteArray(myEntity).length);
	}

	// http 获取实体示例
	void testEntityExample() throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://www.baidu.com");
		CloseableHttpResponse response = httpclient.execute(httpGet);
		try {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream in = entity.getContent();
				try {

				} finally {
					in.close();
				}
			}
			// 可以让实体重复读取
			if (entity != null) {
				entity = new BufferedHttpEntity(entity);
			}

			// 如果目标服务器是可信任的
			if (entity != null) {
				long len = entity.getContentLength();
				if (len != -1 && len < 2048) {
					System.out.println(EntityUtils.toString(entity));
				} else {
					// Stream content out
				}
			}

		} finally {
			response.close();
		}
	}

	// Http 创建实体
	void testCreateHttpEntity() {
		/*
		 * HttpClient提供了一些类，这些类可以通过http连接高效地输出Http实体内容。
		 * HttpClient提供的这几个类涵盖的常见的数据类型。如String，byte数组，输入流，和文件类型：StringEntity,
		 * ByteArrayEntity,InputStreamEntity,FileEntity。
		 */

		File file = new File("somefile.txt");
		FileEntity entity = new FileEntity(file, ContentType.create("text/plain", "UTF-8"));

		HttpPost httppost = new HttpPost("http://localhost/action.do");
		httppost.setEntity(entity);
	}

	public static void main(String[] args) throws ClientProtocolException, IOException, URISyntaxException {
		HttpClientDemo hcd = new HttpClientDemo();
		// hcd.testHttpGet();
		// hcd.testUrl();
		hcd.testHttpGet();
	}
}
