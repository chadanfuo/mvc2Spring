package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import model.BoardDataBean;
import mybatis.AbstractRepository;

@Component
public class MybatisBoardDaoMysql {
	private final String namespace = "mybatis.Board";
	
	@Autowired
	public AbstractRepository opendb;
	
	public int getArticleCount(String boardid) {
		SqlSession sqlSession =opendb.getSqlSessionFactory().openSession();
		try {
			String statement = namespace + ".getArticleCount";
			return sqlSession.selectOne(statement, boardid);
		} finally {			sqlSession.close();		}	}
	
	public List<BoardDataBean> getArticles(int startRow, int endRow, 
			String boardid) {
		
		startRow=startRow -1;
		endRow = endRow - startRow;
		
		Map map = new HashMap();
		map.put("boardid", boardid);
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		try {			String statement = namespace + ".getArticles";
			return sqlSession.selectList(statement, map);
		} finally {			sqlSession.close();		}	}
	
	public void insertArticle(BoardDataBean article) {
		System.out.println("insertArticle");
		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		Map map = new HashMap();
		int num = article.getNum();
		int ref = article.getRef();
		int re_step = article.getRe_step();
		int re_level = article.getRe_level();
		int number = 1;
		try {
			number = sqlSession.selectOne(namespace + ".insert_max");
		if (num != 0) {
		map.put("ref", ref);
		map.put("re_step", re_step);
		re_step = re_step + 1;
		re_level = re_level + 1;
		String statement = namespace + ".insert_update";
			sqlSession.update(statement, map);
			sqlSession.commit();
		} else {
				ref = number;
				re_step=0;
				re_level=0;	}
			
		article.setNum(number);	
		article.setRef(ref);
		article.setRe_level(re_level);
		article.setRe_step(re_step);
		sqlSession.insert(namespace + ".insert", article);
		sqlSession.commit();	
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {			sqlSession.close();		}	}
	
	public BoardDataBean getArticle(int num) {
		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		BoardDataBean article = null;
		try {
			
			sqlSession.update(namespace+".content_update", num);
			sqlSession.commit();
			
			article= sqlSession.selectOne(namespace+".getArticle", num);
			
		} catch (Exception ex) {			ex.printStackTrace();
		} finally {			sqlSession.close();
		}
		return article;
	}

	public BoardDataBean getUpdateArticle(int num) {
		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		BoardDataBean article = null;
		try {
			article=sqlSession.selectOne(namespace+".getArticle", num);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return article;
	}
	

	public int updateArticle(BoardDataBean article) throws Exception {
		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		String dbpasswd = "";		int x = -1;
		try {
			dbpasswd=sqlSession.selectOne(namespace+".getPasswd", article.getNum());
			if (dbpasswd.equals(article.getPasswd())) {
				   sqlSession.update(namespace+".update", article);
				   sqlSession.commit();		x = 1;
				} else {x = 0;}
		} catch (Exception ex) {	ex.printStackTrace();
		} finally {			sqlSession.close();	}
		return x;
	}

	public int deleteArticle(int num, String passwd) throws Exception {
		String dbpasswd=null;
		SqlSession sqlSession = opendb.getSqlSessionFactory().openSession();
		int x =-1;
		try {dbpasswd=sqlSession.selectOne(namespace+".getPasswd", num);
				if (dbpasswd.equals(passwd)) {
					sqlSession.delete(namespace+".delete", num);
					sqlSession.commit();
					x = 1;		} else {	x = 0;	}			
		} catch (Exception ex) {	ex.printStackTrace();
		} finally {		sqlSession.close();		}		return x;	}
}//class end







