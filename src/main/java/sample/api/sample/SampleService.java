package sample.api.sample;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sample.api.mybatis.SampleMapper;
import sample.api.sample.error.NotFoundException;

 
@Service
@Transactional
public class SampleService {
 
    private final SampleMapper mapper;
 
    @Autowired
    public SampleService(SampleMapper mapper) {
        this.mapper = mapper;
    }
 
    public SampleResource findById(int id) {
    	SampleResource result = mapper.findById(id);
    	if(result == null) {
    		String errorMessage = "対象データが存在しません";
    		throw new NotFoundException(errorMessage);
    	}
    	return result;
	}

    public List<SampleResource> findList(SampleResource form) {
        return mapper.findAll();
    }

    public int insert(SampleResource form) {
		return mapper.insert(form);
	}

    public int update(SampleResource form) {
		return mapper.update(form);
	}

    public int delete(int id) {
		return mapper.delete(id);
	}
}