package net.rbv.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import net.rbv.model.Answer;
import net.rbv.model.LikeAnswer;
import net.rbv.model.Question;
import net.rbv.model.User;
import net.rbv.model.VoteQuestion;

@Configuration
@EnableTransactionManagement
public class DBConfig {

	@SuppressWarnings("rawtypes")
	@Bean
	public SessionFactory sessionFactory(){
		LocalSessionFactoryBuilder lsf= new LocalSessionFactoryBuilder(getDataSource());
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		lsf.addProperties(hibernateProperties);
		Class classes[]=new Class[]{User.class,Question.class,Answer.class,VoteQuestion.class,LikeAnswer.class};
		return lsf.addAnnotatedClasses(classes).buildSessionFactory();
	}
	
	@Bean
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		dataSource.setUsername("onlineforum");
		dataSource.setPassword("onlineforum");
		return dataSource;
	}
	
	@Bean
	public HibernateTransactionManager hibTransManagement(){
		
		return new HibernateTransactionManager(sessionFactory());
	}
}
