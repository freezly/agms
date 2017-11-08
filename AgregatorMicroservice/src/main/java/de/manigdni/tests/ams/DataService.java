package de.manigdni.tests.ams;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.client.Client;
@Service
public class DataService {
	public List<Data1> getData1() {
		return get("http://localhost:8082/hello-world", Data1[].class);
	}

	public List<Data2> getData2() {
		return get("http://localhost:8083/hello-world", Data2[].class);
	}

	private <T> List<T> get(String url, Class<T[]> clazz) {
		T[] clientResponse = Client.create().resource(url).get(clazz);
		System.out.println(clientResponse.toString());

		return Arrays.asList(clientResponse);
	}
}
