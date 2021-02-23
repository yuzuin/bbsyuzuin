package com.mybbs.Service;

import com.mybbs.DTO.postDTO;

public interface IF_BBSservice {
	/* 유저의 업무를 처리하기 위한 인터페이스 */
	
	public postDTO viewBBS(int no); //	클라이언트가 요청한 글보기
}
