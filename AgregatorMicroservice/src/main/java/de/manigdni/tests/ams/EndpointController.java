package de.manigdni.tests.ams;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netflix.hystrix.HystrixCommand;

@Controller
@RequestMapping("/hello-world")
public class EndpointController {

	private DataService dataService;

	@Autowired
	public EndpointController(DataService dataService) {
		this.dataService = dataService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<AggregatedData> doSomething() {
		HystrixCommand<List<Data1>> data1Hystrix = new Data1Hystrix(dataService);
		HystrixCommand<List<Data2>> data2Hystrix = new Data2Hystrix(dataService);

		List<Data1> data1 = data1Hystrix.execute();
		List<Data2> data2 = data2Hystrix.execute();

		List<AggregatedData> aData = data1.stream()
				.flatMap(d1 -> data2.stream().filter(d2 -> d1.getKey() == d2.getKey()).map(d2 -> tuple(d1, d2)))
				.collect(Collectors.toList());

		return aData;
	}

	private AggregatedData tuple(Data1 d1, Data2 d2) {
		return new AggregatedData(d1, d2);
	}

}
