package sample.api.sample;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

//import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/sample")
public class SampleRestController {

	
	private final SampleService service;
	
	@Autowired
	public SampleRestController(SampleService service) {
		this.service = service;
	}

//	@GetMapping
//	public List<SampleResource> getSamples(@RequestParam Map<String,String> allParams) throws IllegalAccessException, InvocationTargetException {
//		SampleResource resource = new SampleResource();
//		BeanUtils.populate(resource, allParams);
//		return service.findList(resource);
//	}
	
	@GetMapping
	public List<SampleResource> getList(SampleResource resource) {
		return service.findList(resource);
	}
	
	@GetMapping("{id}")
	public SampleResource getSample(@PathVariable("id") int id) {
		return service.findById(id);
	}
	
	@PostMapping
	public int insert(@RequestBody @Validated SampleResource resource) {
		return service.insert(resource);
	}
	
	@PutMapping
	public int update(@RequestBody @Validated SampleResource resource) {
		return service.update(resource);
	}
	
	@DeleteMapping("{id}")
	public int delete(@PathVariable("id") int id) {
		return service.delete(id);
	}
	
}
