package com.mybbs.Service;

import java.util.List;

import com.mybbs.DTO.commentDTO;
import com.mybbs.DTO.memberDTO;
import com.mybbs.DTO.postDTO;
import com.mybbs.util.PageNumber;

public interface IF_BBSservice {
	/* 유저의 업무를 처리하기 위한 인터페이스 */
	
	public postDTO viewBBS(int no); //	클라이언트가 요청한 글보기
	public int insertPost(postDTO postdto);
	public int allCount();
	public List<postDTO> selectAll(PageNumber pagenumber);
	public List<commentDTO> selectCommentAll(int num);
	public void delPost(int num);
	public void modPost(postDTO postdto);
	public void insertComment(commentDTO commentdto);
	public memberDTO login(memberDTO memberdto);
	public void hits(int postNum);
}
