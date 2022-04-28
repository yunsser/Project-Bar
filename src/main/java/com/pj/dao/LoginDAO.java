package com.pj.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pj.mapper.LoginMapper;
import com.pj.vo.LoginVO;
import com.pj.vo.NoticeVO;
import com.pj.vo.Notice_attachVO;
import com.pj.vo.PagingVO;
import com.pj.vo.ProductVO;
import com.pj.vo.BoardVO;
import com.pj.vo.CommentVO;
import com.pj.vo.ImgVO;
import com.pj.vo.AttachVO;

@Repository
public class LoginDAO {
	@Autowired
	private LoginMapper loginMapper;

//	로그인
	public boolean login(LoginVO login) {
		// System.out.println("DAO"+login.getId());
		return loginMapper.login(login) != null;
	}

// 아이디 찾기, 중복확인
	public boolean checkId(LoginVO login) {
		// System.out.println("나와라"+login.getId());
		if (loginMapper.checkId(login.getId()) != null) {
			return false;
		}
		return true;
	}

//	회원추가
	public boolean addMember(LoginVO login) {
		return loginMapper.addMember(login) > 0;
	}

//  디테일
	public LoginVO selectById(String id) {
		return loginMapper.selectById(id);
	}

//	수정하기
	public boolean updateByMap(Map<String, String> pMap) {
		return loginMapper.updateByMap(pMap) > 0 ? true : false;
	}

//	삭제하기
	public boolean delete(LoginVO login) {
		return loginMapper.deleteMem(login) > 0;
	}

	public List<LoginVO> getList() {
		return loginMapper.getList();
	}
//	===========================================================================================

//	게시판
	public List<Map<String, String>> getMemMap() {
		return loginMapper.getMemMap();
	}

	public boolean addBoard(BoardVO board) {
		return loginMapper.addBoard(board) > 0;
	}

	public boolean addFileInfo(AttachVO attach) {
		return loginMapper.addFileInfo(attach) > 0;
	}

	public String getFilename(int num) {
		return loginMapper.getFilename(num);
	}

	public List<Map<String, Object>> detailNum(int num) {

		return loginMapper.detailNum(num);
	}
//	===========================================================================================

	public int countBoard() {
		return loginMapper.countBoard();
	}

	public List<BoardVO> selectBoard(PagingVO vo) {
		return loginMapper.selectBoard(vo);
	}
	
//	===========================================================================================

	public int boardDeleted(BoardVO board) {
		return loginMapper.boardDeleted(board);
	}

	public boolean deleteFileInfo(int num) {
		return loginMapper.deleteFileInfo(num)>0;
	}
	
	public boolean boardUpdate(BoardVO board) {
		return  loginMapper.boardUpdate(board)>0;
	}
//	===========================================================================================
	
	public List<LoginVO> masterList() {
		return loginMapper.masterList();
	}

	public int countMaster() {
		return loginMapper.countMaster();
	}

	public List<BoardVO> selectMaster(PagingVO vo) {
		return loginMapper.selectMaster(vo);
	}

	public boolean userDelete(int num) {
		return loginMapper.userDelete(num) > 0;
	}

//	===========================================================================================
	
	public List<CommentVO> commentListService(){
        return loginMapper.commentList();
    }
    
    public int commentInsertService(CommentVO comment){
        return loginMapper.commentInsert(comment);
    }
    
    public int commentUpdateService(CommentVO comment){
        return loginMapper.commentUpdate(comment);
    }
    
    public int commentDeleteService(int cno){
        return loginMapper.commentDelete(cno);
    }
    
    
    
//	===========================================================================================

	public int countNotice() {
		return loginMapper.countNotice();
	}

	public List<NoticeVO> selectNotice(PagingVO vo) {
		return loginMapper.selectNotice(vo);
	}
	
//	===========================================================================================

	public boolean noticeBoard(NoticeVO notice) {
		return loginMapper.noticeBoard(notice) > 0;
	}

	public boolean noticeUpdated(NoticeVO notice) {
		return loginMapper.noticeUpdated(notice)>0;
	}

	public boolean noticeDeleted(int num) {
		return loginMapper.noticeDeleted(num) > 0;
	}

	public List<Map<String, Object>> noticedetailNum(int num) {
		return loginMapper.noticedetailNum(num);
	}

	public Boolean noticedeleteFileInfo(int num) {
		return loginMapper.noticedeleteFileInfo(num)>0;
	}

	public boolean noticeFileInfo(Notice_attachVO attach) {
		return loginMapper.noticeFileInfo(attach) > 0;
	}

	public List<Map<String, String>> getNotice() {
		return loginMapper.getNotice();
	}

	public String getNoriceFilename(int num) {
		return loginMapper.getNoriceFilename(num);
	}

	
//	=======================================================================
	
	public int countShop() {
		return loginMapper.countShop();
	}

	public List<ProductVO> selectShop(PagingVO vo) {
		return loginMapper.selectShop(vo);
	}

	public boolean shopBoard(ProductVO product) {
		return loginMapper.shopBoard(product) > 0;
	}

	public boolean shopFileInfo(ImgVO img) {
		return loginMapper.shopFileInfo(img) > 0;
	}

	public List<Map<String, Object>> shopdetailNum(int num) {
		return loginMapper.shopdetailNum(num);
	}

	public boolean shopUpdated(ProductVO product) {
		return loginMapper.shopUpdated(product)>0;
	}

	public boolean shopDeleted(int num) {
		return loginMapper.shopDeleted(num) > 0;
	}

	public Boolean shopdeleteFileInfo(int num) {
		return loginMapper.shopdeleteFileInfo(num)>0;
	}

	public String getShopFilename(int num) {
		return loginMapper.getShopFilename(num);
	}
	
	public List<Map<String, String>> getShop() {
		return loginMapper.getShop();
	}
	
	public List<Map<String, String>> getImg() {
		return loginMapper.getImg();
	}

	public List<Map<String, String>> getimgname(int num) {
		return loginMapper.getimgname(num);
	}

	public List<Map<String, String>> getImgFind(int num) {
		return loginMapper.getImgFind(num);
	}

	public List<ProductVO> getCartProd(List<ProductVO> list) {
		return loginMapper.getCartProd(list);
	}

	public List<ProductVO> shopChoice(int[] list) {
		return loginMapper.shopChoice(list);
		
	}





}
