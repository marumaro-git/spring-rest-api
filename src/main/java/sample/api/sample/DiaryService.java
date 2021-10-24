package sample.api.sample;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sample.api.sample.error.NotFoundException;

import org.springframework.context.MessageSource;
 
@Service
@Transactional
public class DiaryService {
 
    private final ISampleDao dao;
 
    @Autowired
    public DiaryService(ISampleDao dao) {
        this.dao = dao;
    }
 
    public SampleResource findById(int id) {
    	try {
    		return dao.findById(id);
    	} catch(IncorrectResultSizeDataAccessException e) {
    		String errorMessage = "対象データが存在しません";
    		throw new NotFoundException(errorMessage);
    	}
	}

    public List<SampleResource> findList(SampleResource form) {
        return dao.findList(form);
    }

    public int insert(SampleResource form) {
		return dao.insert(form);
	}

    public int update(SampleResource form) {
		return dao.update(form);
	}

    public int delete(int id) {
		return dao.delete(id);
	}
    
}