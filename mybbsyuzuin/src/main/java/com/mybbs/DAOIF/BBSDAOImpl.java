package com.mybbs.DAOIF;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mybbs.DTO.commentDTO;
import com.mybbs.DTO.imgDTO;
import com.mybbs.DTO.memberDTO;
import com.mybbs.DTO.postDTO;
import com.mybbs.util.PageNumber;

/* 구현해주기 */
@Repository		//	퍼시스턴스 레이어, 영속성을 가지는 속성(파일, 데이터베이스)
public class BBSDAOImpl implements IF_BBSDAO{
	private static String mapperQuery = "com.mybbs.DAOIF.IF_BBSDAO";
	
	@Inject	//	Spring 컨테이너에서 18번 라인에 설정된 객체를 자동으로 주입 받아라. 이미 컨테이너에서 객체를 만들어 놓음. 설정 : root-context.xml
	private SqlSession sqlSession;
	
	@Override
	public List<postDTO> selectAll(PageNumber pagenumber) {
		return sqlSession.selectList(mapperQuery+".selectAll",pagenumber);
	}

	@Override
	public List<commentDTO> selectCommentAll(int postNum) {
		return sqlSession.selectList(mapperQuery+".selectCommentAll",postNum);
	}

	@Override
	public postDTO selectOne(int num) {
		return sqlSession.selectOne(mapperQuery+".selectOne",num);
	}

	@Override
	public int insertPost(postDTO post) {
		int ok = sqlSession.insert(mapperQuery+".insertPost",post);
		System.out.println("인서트완");
//		if(ok>0) {
//			sqlSession.commit();
//		}else {
//			System.out.println("인서트실패");
//			sqlSession.rollback();
//		}
		return ok;
	}

	@Override
	public int allCount() {
		//	파라미터가 없을 시, selectOne을 사용
		return sqlSession.selectOne(mapperQuery+".allCount");
	}

	@Override
	public void delPost(int num) {
		sqlSession.delete(mapperQuery+".delPost",num);
	}

	@Override
	public void modPost(postDTO postDTO) {
		sqlSession.update(mapperQuery+".modPost",postDTO);
	}

	@Override
	public void insertComment(commentDTO c) {
		sqlSession.insert(mapperQuery+".insertComment",c);
	}

	@Override
	public memberDTO login(memberDTO m) {
		return sqlSession.selectOne(mapperQuery+".login",m);
	}

	@Override
	public void hits(int postNum) {
		sqlSession.update(mapperQuery+".hits",postNum);
	}

	@Override
	public int lastPostNum() {
		return sqlSession.selectOne(mapperQuery+".lastPostNum");
	}

	@Override
	public void insertImg(imgDTO dto) {
		sqlSession.insert(mapperQuery+".insertImg",dto);
	}

	@Override
	public List<imgDTO> imagesView(int postNum) {
		return sqlSession.selectList(mapperQuery+".imagesView",postNum);
	}

}
