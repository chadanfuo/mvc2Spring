package mybatis;


import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Component;

import model.BoardDataBean;



@Component
public class MybatisBoardDBBean extends AbstractRepository{
	private final String namespace = "mybatis.board";
	
	public List<BoardDataBean> selectBoard() {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		System.out.println("selectboard");
		try {
			return sqlSession.selectList(namespace + ".boardList");
		} finally {
			sqlSession.close();
		}
	}

	public int getArticleCount(String boardid)  {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		System.out.println("getArticleCount===old");
		try {
			return sqlSession.selectOne(namespace + ".getArticleCount", boardid);
		} finally {
			sqlSession.close();
		}
	}

	public List getArticles(int start, int end, String boardid)  {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		System.out.println("getArticles===old");
		HashMap map = new HashMap();
		map.put("boardid", boardid);
		map.put("start", start);
		map.put("end", end);
		System.out.println(map);
		try {
			return sqlSession.selectList(namespace + ".getArticles", map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			sqlSession.close();
		}
	}

	public void insertArticle(BoardDataBean article)  {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int num = article.getNum();
		int ref = article.getRef();
		int re_step = article.getRe_step();
		int re_level = article.getRe_level();
		try {
			HashMap map = new HashMap();
			int number = sqlSession.selectOne(namespace + ".insertArticle_new");
			if (number != 0)
				number = number + 1;
			else
				number = 1;
			if (num != 0) {
				map.put("ref", ref);
				map.put("re_step", re_step);
				sqlSession.update(namespace + ".insertArticle_update", map);
				sqlSession.commit();
				re_step = re_step + 1;
				re_level = re_level + 1;
			} else {
				ref = number;
				re_step = 0;
				re_level = 0;
			}
			article.setNum(number);
			article.setRef(ref);
			article.setRe_step(re_step);
			article.setRe_level(re_level);
			System.out.println("insert:" + article);
			int result = sqlSession.insert(namespace + ".insertArticle_insert", article);
			System.out.println("insert  0k:" + result);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
	}

	public BoardDataBean getArticle(int num)  {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		HashMap map = new HashMap();
		map.put("num", num);
		BoardDataBean article = new BoardDataBean();
		try {
			int result = sqlSession.update(namespace + ".update_readcount", map);
			article = (BoardDataBean) sqlSession.selectOne(namespace + ".update_form", map);
		}  catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			sqlSession.commit();
			sqlSession.close();
			return article;
		}

	}

	public BoardDataBean updateGetArticle(int num) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		HashMap map = new HashMap();
		map.put("num", num);
		BoardDataBean article = new BoardDataBean();
		try {
			article = (BoardDataBean) sqlSession.selectOne(namespace + ".update_form", map);
			System.out.println(":::" + article);
		}  catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			sqlSession.close();
			return article;
		}
	}

	public int updateArticle(BoardDataBean article) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		HashMap map = new HashMap();
		map.put("num", article.getNum());
		int x = -1;
		try {
			String dbpasswd = (String) sqlSession.selectOne(namespace + ".update_passwd", map);
			if (dbpasswd.equals(article.getPasswd())) {
				x = sqlSession.update(namespace + ".update_update", article);
			}
		}  catch (Exception e) {
			e.printStackTrace();
		
		}  finally {
			sqlSession.commit();
			sqlSession.close();
		}
		return x;
	}

	public int deleteArticle(int num, String passwd) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		HashMap map = new HashMap();
		map.put("num", num);
		int x = -1;
		try {
			String dbpasswd = (String) sqlSession.selectOne(namespace + ".update_passwd", map);
			if (dbpasswd.equals(passwd)) {
				x = sqlSession.delete(namespace + ".delete", map);
			}
		}  catch (Exception e) {
			e.printStackTrace();
		
		}	finally {
			sqlSession.commit();
			sqlSession.close();
		}
		return x;
	}

}