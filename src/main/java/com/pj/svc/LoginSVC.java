package com.pj.svc;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.jasper.tagplugins.jstl.core.Remove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mysql.cj.protocol.x.Notice;
import com.pj.dao.LoginDAO;
import com.pj.vo.AttachVO;
import com.pj.vo.BoardVO;
import com.pj.vo.CommentVO;
import com.pj.vo.ImgVO;
import com.pj.vo.LoginVO;
import com.pj.vo.NoticeVO;
import com.pj.vo.PagingVO;
import com.pj.vo.ProductVO;
import com.pj.vo.Notice_attachVO;

@Service
public class LoginSVC {

	@Autowired
	private LoginDAO dao;

	public boolean login(LoginVO login) {
		// System.out.println("svc"+login.getId());
		return dao.login(login);
	}

	public boolean addMember(LoginVO login) {
		return dao.addMember(login);
	}

	public boolean addBoard(BoardVO board) {

		return dao.addBoard(board);
	}

	public boolean addBoard(MultipartFile[] mfiles, HttpServletRequest request, BoardVO board) {
		boolean saved = addBoard(board); // 글 저장
		int num = board.getNum(); // 글 저장시 자동증가 필드
		// System.out.println(num + "확인");
		if (!saved) {
			System.out.println("글 저장 실패");
			return false;
		}

		ServletContext context = request.getServletContext();
		String savePath = context.getRealPath("/upload");
		int fileCnt = mfiles.length;
		int saveCnt = 0;
		boolean fSaved = false;

		if (!mfiles[0].isEmpty()) {
			try {
				for (int i = 0; i < mfiles.length; i++) {
					String filename = mfiles[i].getOriginalFilename();
					mfiles[i].transferTo(new File(savePath + "/" + System.currentTimeMillis() / 100000 + filename)); // 서버측
																														// 디스크
					AttachVO attach = new AttachVO();
					attach.setBoard_num(num);
					attach.setFilename(System.currentTimeMillis() / 100000 + filename);
					attach.setFilesize(mfiles[i].getSize());
					fSaved = dao.addFileInfo(attach); // attach 테이블에 파일정보 저장
					if (fSaved)
						saveCnt++;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return fileCnt == saveCnt ? true : false;// 고치기
		}
		return saved;

	}

//	============================================================================================================

	public String getFilename(int num) {
		return dao.getFilename(num);
	}
//	============================================================================================================

	public int countBoard() {
		return dao.countBoard();
	}

	public List<BoardVO> selectBoard(PagingVO vo) {
		return dao.selectBoard(vo);
	}

//	============================================================================================================

	public int countNotice() {
		return dao.countNotice();
	}

	public List<NoticeVO> selectNotice(PagingVO vo) {
		return dao.selectNotice(vo);
	}

//	============================================================================================================

	public BoardVO detailNum(int num) {

		List<Map<String, Object>> list = dao.detailNum(num);
		BoardVO board = new BoardVO();
		int size = list.size();
		for (int i = 0; i < size; i++) {
			Map<String, Object> map = list.get(i);
			if (i == 0) {
				board.setNum((int) map.get("num"));
				board.setTitle((String) map.get("title"));
				board.setAuthor((String) map.get("author"));
				board.setDate((java.sql.Date) map.get("date"));
				board.setContents((String) map.get("contents"));
			}
			Object obj = map.get("filename");
			if (obj != null) {
				AttachVO att = new AttachVO();
				att.setAtt_num((int) map.get("att_num"));
				att.setBoard_num((int) map.get("board_num"));
				att.setFilename((String) map.get("filename"));
				att.setFilesize((int) map.get("filesize"));
				board.attach.add(att);
			}
		}
		return board;
	}

	public BoardVO boardDeleted(int num) {

		List<Map<String, Object>> list = dao.detailNum(num);
		BoardVO board = new BoardVO();
		int size = list.size();
		for (int i = 0; i < size; i++) {
			Map<String, Object> map = list.get(i);
			if (i == 0) {
				board.setNum((int) map.get("num"));
				board.setTitle((String) map.get("title"));
				board.setAuthor((String) map.get("author"));
				board.setDate((java.sql.Date) map.get("date"));
				board.setContents((String) map.get("contents"));
			}
			Object obj = map.get("filename");
			if (obj != null) {
				AttachVO att = new AttachVO();
				att.setAtt_num((int) map.get("att_num"));
				att.setBoard_num((int) map.get("board_num"));
				att.setFilename((String) map.get("filename"));
				att.setFilesize((int) map.get("filesize"));
				board.attach.add(att);
			}
		}
		return board;
	}

	public int boardDeleted(BoardVO board) {
		return dao.boardDeleted(board);
	}

//	파일삭제 ===================================================================================

	public boolean deleteFileInfo(List<String> delfiles) {
		List<Boolean> ret = new ArrayList<>();
		for (int i = 0; i < delfiles.size(); i++) {
			Boolean res = dao.deleteFileInfo(Integer.parseInt(delfiles.get(i)));
			if (res)
				ret.add(res);
		}
		return delfiles.size() == ret.size();
	}

	public boolean boardUpdate(BoardVO board) {
		return dao.boardUpdate(board);
	}

	public boolean boardUpdated(HttpServletRequest request, BoardVO board, MultipartFile[] mfiles,
			List<String> delfiles) {
		boolean deleteFileInfo = deleteFileInfo(delfiles);
		// System.out.println(deleteFileInfo);

		boolean saved = boardUpdate(board); // 글 저장
		int board_num = board.getNum(); // 글 저장시 자동증가 필드
		if (!saved) {
			System.out.println("글 저장 실패");
			return false;
		}
		ServletContext context = request.getServletContext();
		String savePath = context.getRealPath("/upload");
		int fileCnt = mfiles.length;
		int saveCnt = 0;
		boolean fSaved = false;

		if (!mfiles[0].isEmpty()) {
			try {
				for (int i = 0; i < mfiles.length; i++) {
					String filename = mfiles[i].getOriginalFilename();

					mfiles[i].transferTo(new File(savePath + "/" + System.currentTimeMillis() / 100000 + filename)); // 서버측
																														// 디스크

					AttachVO attach = new AttachVO();
					attach.setBoard_num(board_num);
					attach.setFilename(System.currentTimeMillis() / 100000 + filename);
					attach.setFilesize(mfiles[i].getSize());
					fSaved = dao.addFileInfo(attach);
					if (fSaved)
						saveCnt++;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return fileCnt == saveCnt ? true : false;

		}

		return saved;

	}

//	============================================================================================================

	public List<LoginVO> masterList() {
		return dao.masterList();
	}

	public int countMaster() {
		return dao.countMaster();
	}

	public List<BoardVO> selectMaster(PagingVO vo) {
		return dao.selectMaster(vo);
	}

	public boolean userDelete(int[] nums) {
		int delCnt = 0;
		for (int i = 0; i < nums.length; i++) {
			if (dao.userDelete(nums[i]))
				delCnt++; // 숫자 1개
		}
		return delCnt == nums.length;
	}

//	============================================================================================================ notice

	// 글업로드
	public boolean noticeBoard(NoticeVO notice) {
		return dao.noticeBoard(notice);
	}

	public boolean noticeBoard(MultipartFile[] mfiles, HttpServletRequest request, NoticeVO notice) {
		boolean saved = noticeBoard(notice); // 글 저장
		int num = notice.getNum_no(); // 글 저장시 자동증가 필드
		// System.out.println(num + "확인");
		if (!saved) {
			System.out.println("글 저장 실패");
			return false;
		}

		ServletContext context = request.getServletContext();
		String savePath = context.getRealPath("/upload");
		int fileCnt = mfiles.length;
		int saveCnt = 0;
		boolean fSaved = false;

		if (!mfiles[0].isEmpty()) {
			try {
				for (int i = 0; i < mfiles.length; i++) {
					String filename = mfiles[i].getOriginalFilename();
					mfiles[i].transferTo(new File(savePath + "/" + System.currentTimeMillis() / 100000 + filename)); // 서버측
																														// 디스크
					Notice_attachVO notice_attach = new Notice_attachVO();
					notice_attach.setNotice_num(num);
					notice_attach.setNo_filename(System.currentTimeMillis() / 100000 + filename);
					notice_attach.setNo_filesize((int) mfiles[i].getSize());
					fSaved = dao.noticeFileInfo(notice_attach); // attach 테이블에 파일정보 저장
					if (fSaved)
						saveCnt++;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return fileCnt == saveCnt ? true : false;// 고치기
		}
		return saved;
	}

	// 게시글디테일
	public NoticeVO noticedetailNum(int num) {
		List<Map<String, Object>> list = dao.noticedetailNum(num);
		NoticeVO notice = new NoticeVO();
		int size = list.size();
		for (int i = 0; i < size; i++) {
			Map<String, Object> map = list.get(i);
			if (i == 0) {
				notice.setNum_no((int) map.get("num_no"));
				notice.setTitle_no((String) map.get("title_no"));
				notice.setMaster((String) map.get("master"));
				notice.setContent_no((String) map.get("content_no"));
				notice.setNdate((java.sql.Date) map.get("ndate"));
			}
			Object obj = map.get("no_filename");
			if (obj != null) {
				Notice_attachVO att = new Notice_attachVO();
				att.setNo_att_num((int) map.get("no_att_num"));
				/* att.setNotice_num((int) map.get("notice_num")); */
				att.setNo_filename((String) map.get("no_filename"));
				att.setNo_filesize((int) map.get("no_filesize"));
				notice.notice_attach.add(att);
			}
		}
		return notice;
	}

	// 글업데이트
	public boolean noticeUpdated(NoticeVO notice) {
		return dao.noticeUpdated(notice);
	}

	public boolean noticeUpdated(HttpServletRequest request, NoticeVO notice, MultipartFile[] mfiles,
			List<String> delfiles) {
		boolean noticedeleteFileInfo = noticedeleteFileInfo(delfiles);
		
		boolean saved = noticeUpdated(notice); // 글 저장
		int notice_num = notice.getNum_no(); // 글 저장시 자동증가 필드
		if (!saved) {
			System.out.println("글 저장 실패");
			return false;
		}
		ServletContext context = request.getServletContext();
		String savePath = context.getRealPath("/upload");
		int fileCnt = mfiles.length;
		int saveCnt = 0;
		boolean fSaved = false;

		if (!mfiles[0].isEmpty()) {
			try {
				for (int i = 0; i < mfiles.length; i++) {
					String filename = mfiles[i].getOriginalFilename();

					mfiles[i].transferTo(new File(savePath + "/" + System.currentTimeMillis() / 100000 + filename)); // 서버측
																														// 디스크

					Notice_attachVO attach = new Notice_attachVO();
					attach.setNotice_num(notice_num);
					attach.setNo_filename(System.currentTimeMillis() / 100000 + filename);
					attach.setNo_filesize((int) mfiles[i].getSize());
					fSaved = dao.noticeFileInfo(attach);
					if (fSaved)
						saveCnt++;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return fileCnt == saveCnt ? true : false;

		}

		return saved;
	}

	// 글삭제
	public boolean noticeDeleted(int num) {
		return dao.noticeDeleted(num);
	}

	// 파일삭제
	public boolean noticedeleteFileInfo(List<String> delfiles) {
		List<Boolean> ret = new ArrayList<>();
		for (int i = 0; i < delfiles.size(); i++) {
			Boolean res = dao.noticedeleteFileInfo(Integer.parseInt(delfiles.get(i)));
			if (res)
				ret.add(res);
		}
		return delfiles.size() == ret.size();
	}

	public String getNoriceFilename(int num) {
		return dao.getNoriceFilename(num);
	}

//	============================================================================================================

	public int countShop() {
		return dao.countShop();
	}

	public Object selectShop(PagingVO vo) {
		return dao.selectShop(vo);
	}

	// 글업로드
	public boolean shopBoard(ProductVO product) {
		return dao.shopBoard(product);
	}

	public boolean shopBoard(MultipartFile[] mfiles, HttpServletRequest request, ProductVO product) {
		boolean saved = shopBoard(product); // 글 저장
		int num = product.getNum_pr(); // 글 저장시 자동증가 필드
		// System.out.println(num + "확인");
		if (!saved) {
			System.out.println("글 저장 실패");
			return false;
		}

		ServletContext context = request.getServletContext();
		String savePath = context.getRealPath("/upload");
		int fileCnt = mfiles.length;
		int saveCnt = 0;
		boolean fSaved = false;

		if (!mfiles[0].isEmpty()) {
			try {
				for (int i = 0; i < mfiles.length; i++) {
					String filename = mfiles[i].getOriginalFilename();
					mfiles[i].transferTo(new File(savePath + "/" + System.currentTimeMillis() / 100000 + filename)); // 서버측
																														// 디스크
					ImgVO img = new ImgVO();
					img.setPrd_num(num);
					img.setImgname(System.currentTimeMillis() / 100000 + filename);
					img.setImgsize((int) mfiles[i].getSize());
					fSaved = dao.shopFileInfo(img); // attach 테이블에 파일정보 저장
					if (fSaved)
						saveCnt++;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return fileCnt == saveCnt ? true : false;// 고치기
		}
		return saved;
	}

	public ProductVO shopdetailNum(int num) {
		List<Map<String, Object>> list = dao.shopdetailNum(num);
		ProductVO product = new ProductVO();
		int size = list.size();
		for (int i = 0; i < size; i++) {
			Map<String, Object> map = list.get(i);
			if (i == 0) {
				product.setNum_pr((int) map.get("num_pr"));
				product.setName((String) map.get("name"));
				product.setPrice((int) map.get("price"));
				product.setDescription((String) map.get("description"));
				product.setDate_da((java.sql.Date) map.get("date_da"));
			}
			String imgname = (String) map.get("imgname");
			if (imgname != null) {
				ImgVO img = new ImgVO();
//				img.setImg_num((int) map.get("img_num"));
//				/* att.setNotice_num((int) map.get("notice_num")); */
//				img.setImgname((String) map.get("imgname"));
//				img.setImgsize((int) map.get("imgsize"));
				product.setImgname(imgname);
			}
		}
		return product;
	}

	// 글업데이트
	public boolean shopUpdated(ProductVO product) {
		return dao.shopUpdated(product);
	}

	public boolean shopUpdated(HttpServletRequest request, ProductVO product, MultipartFile[] mfiles,
			List<String> delfiles) {
		boolean saved = shopUpdated(product); // 글 저장
		int num = product.getNum_pr(); // 글 저장시 자동증가 필드
		if (!saved) {
			System.out.println("글 저장 실패");
			return false;
		}
		ServletContext context = request.getServletContext();
		String savePath = context.getRealPath("/upload");
		int fileCnt = mfiles.length;
		int saveCnt = 0;
		boolean fSaved = false;

		if (!mfiles[0].isEmpty()) {
			try {
				for (int i = 0; i < mfiles.length; i++) {
					String filename = mfiles[i].getOriginalFilename();

					mfiles[i].transferTo(new File(savePath + "/" + System.currentTimeMillis() / 100000 + filename)); // 서버측
																														// 디스크

					ImgVO img = new ImgVO();
					img.setImg_num(num);
					img.setImgname(System.currentTimeMillis() / 100000 + filename);
					img.setImgsize((int) mfiles[i].getSize());
					fSaved = dao.shopFileInfo(img);
					if (fSaved)
						saveCnt++;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return fileCnt == saveCnt ? true : false;

		}

		return saved;
	}

	public boolean shopDeleted(int num) {
		return dao.shopDeleted(num);
	}

	public boolean shopdeleteFileInfo(List<String> delfiles) {
		List<Boolean> ret = new ArrayList<>();
		for (int i = 0; i < delfiles.size(); i++) {
			Boolean res = dao.shopdeleteFileInfo(Integer.parseInt(delfiles.get(i)));
			if (res)
				ret.add(res);
		}
		return delfiles.size() == ret.size();
	}

	public String getShopFilename(int num) {
		return dao.getShopFilename(num);
	}

//	========================================================================================================================

	// 상품 목록 체크값 넘기기
	public boolean shopChoice(int[] nums, List<ProductVO> cart) {
		List<ProductVO> list = dao.shopChoice(nums);
		boolean added = cart.addAll(list);
		return added;
	}

//	========================================================================================================================

	public boolean addItem(ProductVO product, List<ProductVO> cart) {
		// 동일 아이템이 이미 장바구니에 들어 있는지 확인
		if (cart.contains(product)) {
			int idx = cart.indexOf(product);
			cart.get(idx).setQty(cart.get(idx).getQty() + product.getQty());
			return true;
		}
		cart.add(product);
		// System.out.println(product.getNum_pr()+"확인");
		return true;
	}

	public int getTotalPrice(List<ProductVO> cart) {
		int total = 0;
		for (int i = 0; i < cart.size(); i++) {
			total += cart.get(i).getPrice() * cart.get(i).getQty();
		}
		return total;
	}

	@Transactional(rollbackFor = { Exception.class })
	public boolean order(List<ProductVO> cart) {
		for (int i = 0; i < cart.size(); i++) {
			// dao.save(cart.get(i));
		}
		return true;
	}

	public boolean cartDeleted(List<ProductVO> cart, int[] nums) {
		for (int k = 0; k < cart.size(); k++) {
			for (int i = 0; i < nums.length; i++) {
				if (cart.get(k).getNum_pr() == nums[i]) {
					cart.remove(k);
				}
			}
		}
		return true;
	}
	
//	========================================================================================================================

	
	
	

//	public List<ProductVO> cartList() {
//		List<Map<String, Object>> list = dao.cartList();
//		List<ProductVO> list2 = new ArrayList<>();
//		// Map에 저장된 게시글 한 행의 정보를 BoardVO 객체 한개로 표현한다
//
//		for (int i = 0; i < list.size(); i++) {
//			Map<String, Object> m = list.get(i);
//			ProductVO product = new ProductVO();
//			product.setNum_pr((Integer) m.get("num_pr"));
//			product.setName((String) m.get("name"));
//			product.setPrice((Integer) m.get("price"));
//
//
//			if (m.get("fnames") != null) // 첨부파일을 가진 글이라면...
//			{
//				String nums = (String) m.get("nums");
//				String[] num = nums.split("\\|");
//
//				String fnames = (String) m.get("fnames");
//				String[] fn = fnames.split("\\|");
//
//				String fsizes = (String) m.get("sizes");
//				String[] size = fsizes.split("\\|");
//
//				for (int j = 0; j < fn.length; j++) {
//					ImgVO img = new ImgVO();
//					img.setImg_num(Integer.parseInt(num[j]));
//					img.setImgname(fn[j]);
//					img.setImgsize(Integer.parseInt(size[j]));
//					product.img.add(img);
//				}
//			}
//			list2.add(product);
//		} // end of for()
//		return list2;
//	}

}
