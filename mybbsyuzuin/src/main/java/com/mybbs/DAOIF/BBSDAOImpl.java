package com.mybbs.DAOIF;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

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
		// TODO Auto-generated method stub
		return sqlSession.selectList(mapperQuery+".selectAll",pagenumber);
	}

}
