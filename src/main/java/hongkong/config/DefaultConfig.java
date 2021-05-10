package hongkong.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;


@Configuration
public class DefaultConfig {

	@Autowired
	private ApplicationContext applicationContext;
	
	//MyBatis설정하기위한 config
	//SqlSessionFactory: 애플리케이션을 실행하는 동안 여러차례 빌드하지 않고 지속적으로 존재 --> datasource참조하여 MyBatis와 MySql서버 연동
	//SqlSessionFactoryBean: SqlSessionFactory빌드
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
		//System.out.println("dataSource :"+dataSource);
		bean.setDataSource(dataSource);
		
		//applicationContext: 스프링의 빈을 생성하고 관리하는 컨테이너
		Resource[] mapperLocations=applicationContext.getResources("classpath:/mapper/**.xml");
		bean.setMapperLocations(mapperLocations);
		
		return bean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
