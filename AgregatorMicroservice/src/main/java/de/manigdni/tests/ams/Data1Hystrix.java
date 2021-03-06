package de.manigdni.tests.ams;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

@Service
public class Data1Hystrix extends HystrixCommand<List<Data1>> {
	private DataService dataService;

	@Autowired
	public Data1Hystrix(DataService dataService) {
		super(HystrixCommandGroupKey.Factory.asKey("Data1"));
		this.dataService = dataService;
	}

	@Override
	protected List<Data1> run() throws Exception {
		// potentiell unsicherer Aufruf
		return dataService.getData1();
	}

	@Override
	protected List<Data1> getFallback() {
		return new LinkedList<Data1>();
	}
}