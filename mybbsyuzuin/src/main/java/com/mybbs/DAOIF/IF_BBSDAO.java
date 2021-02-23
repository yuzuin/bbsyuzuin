package com.mybbs.DAOIF;

import java.util.List;

import com.mybbs.DTO.commentDTO;
import com.mybbs.DTO.memberDTO;
import com.mybbs.DTO.postDTO;
import com.mybbs.util.PageNumber;

public interface IF_BBSDAO {
	/* 이게 생긴 이상 기존에 쓰던 dao 클래스들은 버린다.*/
	/* 예전에는 selectAll을 모두 코딩 해줬지만 이제는 그럴 필요가 없다.
	 * bbsMapper.xml에서 mapping을 해줄 것이다. */
	public List<postDTO> selectAll(PageNumber pagenumber);
	
	//	코멘트 리스트
	public List<commentDTO> selectCommentAll(int postNum);
	//	코멘트 인서트
	public void insertComment(commentDTO c);
	//	상세보기
	public postDTO selectOne(int num);
	//	글쓰기
	public int insertPost(postDTO postDTO);
	//	글 all count
	public int allCount();
	//	글삭제
	public void delPost(int num);
	//	글수정
	public void modPost(postDTO postDTO);
	//	로그인
	public memberDTO login(memberDTO m);
	
}
