package de.manigdni.tests.ams;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

@Service
public class Data2Hystrix extends HystrixCommand<List<Data2>> {
	private DataService dataService;

	@Autowired
	public Data2Hystrix(DataService dataService) {
		super(HystrixCommandGroupKey.Factory.asKey("Data2"));
		this.dataService = dataService;
	}

	@Override
	protected List<Data2> run() throws Exception {
		// potentiell unsicherer Aufruf
		return dataService.getData2();
	}

	@Override
	protected List<Data2> getFallback() {
		return new LinkedList<Data2>();
	}
}