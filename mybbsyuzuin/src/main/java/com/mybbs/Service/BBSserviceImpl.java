package com.mybbs.Service;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.mybbs.DAOIF.IF_BBSDAO;
import com.mybbs.DTO.commentDTO;
import com.mybbs.DTO.memberDTO;
import com.mybbs.DTO.postDTO;
import com.mybbs.util.PageNumber;

@Service	//	주입 받을 수 있게 Service단이라고 선언
public class BBSserviceImpl implements IF_BBSservice {

	/* 컨트롤러에서 하지 않고 여기서 해줌 */
	@Inject
	private IF_BBSDAO bbsdao2;// 인터페이스는 객체를 만들 수 없다. 인터페이스를 구현 받은 객체만 생성이 가능. mybatis 에서는 인터페이스를
	// 한개의 클래스만 구성할 수 있다.
	// @Inject는 컨테이너에서 객체를 주입받는다. 그러므로 IF_BBSDAO 객체가 컨테이너에 있어야 한다.
	// root-context.xml을 수정해야 합니다.
	// <context:component-scan
	// base-package="com.human.DAOIF"></context:component-scan>

	@Override
	public postDTO viewBBS(int num) {
		//bbsDAO cnt어쩌구..
		hits(num);	//	조회수
		return bbsdao2.selectOne(num);
	}

	@Override
	public int insertPost(postDTO postdto) {
		return bbsdao2.insertPost(postdto);
	}

	@Override
	public int allCount() {
		return bbsdao2.allCount();
	}

	@Override
	public List<postDTO> selectAll(PageNumber pagenumber) {
		return bbsdao2.selectAll(pagenumber);
	}

	@Override
	public List<commentDTO> selectCommentAll(int num) {
		return bbsdao2.selectCommentAll(num);
	}

	@Override
	public void delPost(int num) {
		bbsdao2.delPost(num);
	}

	@Override
	public void modPost(postDTO postdto) {
		bbsdao2.modPost(postdto);
	}

	@Override
	public void insertComment(commentDTO commentdto) {
		bbsdao2.insertComment(commentdto);
		
	}

	@Override
	public memberDTO login(memberDTO memberdto) {
		return bbsdao2.login(memberdto);
	}

	@Override
	public void hits(int postNum) {
		bbsdao2.hits(postNum);
	}

}
