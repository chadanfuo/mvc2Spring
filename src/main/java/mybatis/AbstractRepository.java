package mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public abstract class AbstractRepository {
	private String resource;
	private String dbname = "";
	private SqlSessionFactory sqlSessionFactory;
	

	private void setSqlSessionFactory() {
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	protected SqlSessionFactory getSqlSessionFactory() {
		setSqlSessionFactory();
		return sqlSessionFactory;
	}	

	public void setDbname(String dbname) {
		this.resource = "mybatis/mybatis-config" + dbname + ".xml";
		this.dbname = dbname;
	}

	public String getDbname() {
		return dbname;
	}

}
