package com.pj.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.pj.vo.LoginVO;
import com.pj.vo.NoticeVO;
import com.pj.vo.Notice_attachVO;
import com.pj.vo.PagingVO;
import com.pj.vo.ProductVO;
import com.pj.vo.BoardVO;
import com.pj.vo.CommentVO;
import com.pj.vo.ImgVO;
import com.pj.vo.AttachVO;


@Mapper
public interface LoginMapper {
	
//	================================================== login
	
	String login(LoginVO login);

	LoginVO checkId(String id);

	int addMember(LoginVO login);

	LoginVO selectById(String id);

	List<LoginVO> getList();

	int updateByMap(Map<String, String> pMap);

	int deleteMem(LoginVO login);
	
//	================================================== file
	
	List<Map<String, String>> getMemMap();

	int addBoard(BoardVO board);

	int addFileInfo(AttachVO attach);

	String getFilename(int num);

	/* List<BoardVO> getUserList(); */

	List<Map<String, Object>> detailNum(int num);
	
//	================================================== paging

	public int countBoard();
	
	public List<BoardVO> selectBoard(PagingVO vo);

	BoardVO getUserByNum(int num);

	int boardDeleted(BoardVO board);

	int deleteFileInfo(int num);

//	================================================== Master
	
	List<LoginVO> masterList();

	int countMaster();

	List<BoardVO> selectMaster(PagingVO vo);

	int boardUpdate(BoardVO board);

	int userDelete(int num);
	
//	================================================== Comment
	
	// 댓글 개수
	int commentCount();
	
	// 댓글 목록
	List<CommentVO> commentList();

	// 댓글 작성
	int commentInsert(CommentVO comment);

    // 댓글 수정
    int commentUpdate(CommentVO comment);
 
    // 댓글 삭제
    int commentDelete(int cno);

//	================================================== Notice

    int countNotice();

	List<NoticeVO> selectNotice(PagingVO vo);

	int noticeBoard(NoticeVO notice);

	int noticeUpdated(NoticeVO notice);

	int noticeDeleted(int num);

	List<Map<String, Object>> noticedetailNum(int num);

	int noticedeleteFileInfo(int num);

	int noticeFileInfo(Notice_attachVO attach);

	List<Map<String, String>> getNotice();

	String getNoriceFilename(int num);

//	================================================== product
	
	int countShop();

	List<ProductVO> selectShop(PagingVO vo);

	int shopBoard(ProductVO product);

	int shopFileInfo(ImgVO img);

	List<Map<String, Object>> shopdetailNum(int num);

	int shopUpdated(ProductVO product);

	int shopDeleted(int num);

	int shopdeleteFileInfo(int num);

	String getShopFilename(int num);

	List<Map<String, String>> getShop();
	
	List<Map<String, String>> getImg();
	
	List<Map<String, String>> getimgname(int num);

	List<Map<String, String>> getImgFind(int num);

	/* List<Map<String, Object>> cartList(); */

	List<ProductVO> getCartProd(List<ProductVO> list);

	List<ProductVO> shopChoice(int[] list);

//	================================================== 
	
	


	


	

}
