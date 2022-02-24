package sample.api.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import sample.api.sample.SampleResource;

@Mapper
public interface SampleMapper {
	List<SampleResource> findAll();
	
	SampleResource findById(int id);
	
	int insert(SampleResource resource);
	
	int update(SampleResource resource);
	
	int delete(int id);
}
