package sample.api.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;

@Configuration
@PropertySource("classpath:application.properties")
@MapperScan("sample.api.mybatis")
public class AppConfig {
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${spring.datasource.url}")
	private String url;
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		ResourcePatternResolver resolver =
				ResourcePatternUtils.getResourcePatternResolver(new DefaultResourceLoader());
		sessionFactory.setMapperLocations(resolver.getResource("classpath:sample/mybatis/SampleMapper.xml"));
		return sessionFactory.getObject();
	}
	
	@Bean
	public DataSource dataSource() {
		PGSimpleDataSource p = new PGSimpleDataSource();
		p.setUrl(url);
		p.setUser(username);
		p.setPassword(password);
		return p;
	}

}
