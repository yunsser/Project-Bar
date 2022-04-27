package com.pj.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.pj.dao.LoginDAO;
import com.pj.mapper.LoginMapper;
import com.pj.svc.LoginSVC;
import com.pj.vo.BoardVO;
import com.pj.vo.CommentVO;
import com.pj.vo.ImgVO;
import com.pj.vo.LoginVO;
import com.pj.vo.NoticeVO;
import com.pj.vo.PagingVO;
import com.pj.vo.ProductVO;

@Controller
@RequestMapping("/bar")
@SessionAttributes({ "id", "cart" })
public class LoginController {

	@Autowired
	private LoginSVC svc;

	@Autowired
	private LoginDAO dao;

	@Autowired
	ResourceLoader resourceLoader;

//	========================================================================================================

//	홈페이지 메인
	@GetMapping("/home") // localhost/bar/home
	public String home(Model model) {
		List<ProductVO> cart = new ArrayList<>();
		model.addAttribute("cart", cart);
		
		List<Map<String, String>> list = dao.getShop();
		List<Map<String, String>> list2 = dao.getImg();
		model.addAttribute("list", list);
		model.addAttribute("list2", list2);

		return "login/home";
	}

//  로그인 화면
	@GetMapping("/login") // localhost/bar/home
	public String login() {
		return "login/login";
	}

	/*
	 * @GetMapping("/login") // localhost/bar/login public String
	 * loginForm(@SessionAttribute(name="id", required=false) String id, Model
	 * model) { if(id==null) { return "redirect:/mb/login"; }else { List<LoginVO>
	 * list = dao.getList(); model.addAttribute("list", list); return "login/login";
	 * } }
	 */

	@PostMapping("/login")
	@ResponseBody
	public Map<String, Boolean> login(LoginVO login, Model model) {
		// System.out.println("con"+login.getId());
		Map<String, Boolean> map = new HashMap<>();
		boolean ok = dao.login(login);
		if (ok) {
			model.addAttribute("id", login.getId());
		}
		map.put("ok", ok);
		return map;
	}

//	로그아웃
	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:/bar/home";
	}

//	아이디체크
	@PostMapping("/idcheck")
	@ResponseBody
	public Map<String, Boolean> idcheck(LoginVO login) {
		// System.out.println("나와라콘솔"+login.getId());
		Map<String, Boolean> map = new HashMap<>();
		map.put("checked", dao.checkId(login));
		return map;
	}

//  화원가입
	@GetMapping("/signup") // localhost/bar/add
	public String add() {
		return "login/signup";
	}

	@PostMapping("/signup")
	@ResponseBody
	public Map<String, Boolean> addMemder(LoginVO login) {
		// System.out.println("아이디확인"+login.getName());
		boolean add = dao.addMember(login);
		Map<String, Boolean> map = new HashMap<>();
		map.put("add", add);
		return map;
	}

//	수정화면보기
	@GetMapping("/detail")
	public String detail(@SessionAttribute(value = "id", required = false) String id, Model model) { // localhost/bar/detail
		LoginVO login = dao.selectById(id);
		// System.out.println("성별"+login.getGender());
		model.addAttribute("login", login);
		return "login/detail";
	}

	@GetMapping("/edit")
	public String edit(@SessionAttribute(value = "id", required = false) String id, Model model) {
		LoginVO login = dao.selectById(id);
		model.addAttribute("login", login);
		return "login/edit";
	}

//	수정하기
	@PostMapping("/update")
	@ResponseBody
	public Map<String, Boolean> update(LoginVO login) {
		Map<String, String> pMap = new HashMap<>();
		pMap.put("id", login.getId());
		pMap.put("pw", login.getPw());
		pMap.put("phone", login.getPhone());
		pMap.put("email", login.getEmail());
		boolean updated = dao.updateByMap(pMap);
		Map<String, Boolean> map = new HashMap<>();
		map.put("updated", updated);
		return map;

	}

//	삭제하기
	@PostMapping("/delete")
	@ResponseBody
	public Map<String, Boolean> deleted(LoginVO login) {
		Map<String, Boolean> map = new HashMap<>();
		// System.out.println("확인"+login.getId());
		map.put("deleted", dao.delete(login));
		return map;
	}

//	========================================================================================================
	/*
	 * @GetMapping("/list") // http://localhost/bar/list public String
	 * list(@SessionAttribute(name = "id", required = false) String id, Model model)
	 * { if (id == null) { return "redirect:/bar/home"; // 로그인 폼으로... } else {
	 * List<Map<String, String>> list = dao.getMemMap(); model.addAttribute("list",
	 * list); return "list/list"; } }
	 */

//	게시판 리스트 + 페이징
	@GetMapping("/list")
	public String boardList(@SessionAttribute(name = "id", required = false) String id, PagingVO vo, Model model,
			@RequestParam(value = "nowPage", required = false) String nowPage,
			@RequestParam(value = "cntPerPage", required = false) String cntPerPage) {

		if (id == null) {
			return "redirect:/bar/login";
		} else {
			int total = svc.countBoard();
			if (nowPage == null && cntPerPage == null) {
				nowPage = "1";
				cntPerPage = "15";
			} else if (nowPage == null) {
				nowPage = "1";
			} else if (cntPerPage == null) {
				cntPerPage = "15";
			}
			List<Map<String, String>> list = dao.getMemMap();
			model.addAttribute("list", list);
			vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
			model.addAttribute("paging", vo);
			model.addAttribute("viewAll", svc.selectBoard(vo));
			return "list/list";
		}

	}

//	게시판 글쓰기
	@GetMapping("/board")
	public String board() {

		return "list/board";

	}

	@PostMapping("/board")
	@ResponseBody
	public Map<String, Boolean> add(
			@SessionAttribute(name = "id", required = false) @RequestParam(name = "files", required = false) MultipartFile[] mfiles,
			HttpServletRequest request, BoardVO board) {
		Map<String, Boolean> map = new HashMap<>();
		boolean added = svc.addBoard(mfiles, request, board);
		map.put("added", added);
		return map;

	}

//	파일 다운로드
	@GetMapping("/download/{filename}")
	public ResponseEntity<Resource> download(HttpServletRequest request, @PathVariable String filename) {
		Resource resource = (Resource) resourceLoader.getResource("upload/" + filename);
		System.out.println("파일명:" + resource.getFilename());
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

	@GetMapping("/file/download/{num}")
	public ResponseEntity<Resource> fileDownload(@PathVariable int num, HttpServletRequest request) {
		// attach 테이블에서 att_num 번호를 이용하여 파일명을 구하여 위의 방법을 사용
		String filename = svc.getFilename(num);
		Resource resource = (Resource) resourceLoader.getResource("upload/" + filename);
		// System.out.println("파일명:"+resource.getFilename());
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

//	게시판 수정화면보기

	@GetMapping("/boardDetail") // http://localhost/bbs/detail
	public String detailBoard(@SessionAttribute(name = "id", required = false) String id, @RequestParam int num,
			Model model) { // 일치시켜주면 // 들어감
		BoardVO board = svc.detailNum(num);

		model.addAttribute("board", board);
		return "list/board_detail";
	}

	@GetMapping("/boardEdit")
	public String detailedit(@SessionAttribute(name = "id", required = false) @RequestParam int num, Model model) {
		BoardVO board = svc.detailNum(num);
		model.addAttribute("board", board);
		return "list/board_edit";
	}

//	@GetMapping("/boardEdit")
//	public String detailedit(@SessionAttribute(name = "id", required = false) @RequestParam int num, Model model) {
//		BoardVO board = svc.getUserByNum(num);
//		model.addAttribute("board", board);
//		return "list/board_edit";
//	}

//	게시판 수정하기
	@PostMapping("/boardUpdate")
	@ResponseBody
	public Map<String, Boolean> updateBoard(
			@SessionAttribute(name = "id", required = false) @RequestParam(name = "files", required = false) MultipartFile[] mfiles,
			HttpServletRequest request, BoardVO board, @RequestParam("delfiles") List<String> delfiles, Model model) {
		// System.out.println("삭제할파일수" + delfiles.size());
		Map<String, Boolean> map = new HashMap<>();
		boolean boardUpdated = svc.boardUpdated(request, board, mfiles, delfiles);
		map.put("boardUpdated", boardUpdated);
		return map;
	}

	@PostMapping("/boardDelete")
	@ResponseBody
	public Map<String, Boolean> deleted(BoardVO board) {
		Map<String, Boolean> map = new HashMap<>();
		map.put("boardDeleted", svc.boardDeleted(board) > 0);
		return map;
	}

	@PostMapping("/file/delete")
	@ResponseBody
	public Map<String, Boolean> deleteFileInfo(@RequestParam List<String> delfiles) {
		boolean deleteFileInfo = svc.deleteFileInfo(delfiles);
		Map<String, Boolean> map = new HashMap<>();
		map.put("deleteFileInfo", deleteFileInfo);
		return map;
	}

//	@PostMapping("/file/delete")
//	@ResponseBody
//	public Map<String, Boolean> deleteFileInfo(@RequestParam int num) {
//		boolean deleteFileInfo = svc.deleteFileInfo(num, resourceLoader);
//		Map<String, Boolean> map = new HashMap<>();
//		map.put("deleteFileInfo", deleteFileInfo);
//		return map;
//	}

//	==========================================================================================================================

	@PostMapping("/comment/list") // 댓글 리스트
	@ResponseBody
	private List<CommentVO> mCommentServiceList(@SessionAttribute(value = "id", required = false) Model model) {
		return dao.commentListService();
	}

	@PostMapping("/comment/insert") // 댓글 작성
	@ResponseBody
	private int mCommentServiceInsert(@SessionAttribute(name = "id", required = false) String id, @RequestParam int bno,
			@RequestParam String content) {
		CommentVO comment = new CommentVO();
		comment.setBno(bno);
		comment.setContent(content);
		comment.setWriter(id);
		return dao.commentInsertService(comment);
	}

	@PostMapping("/comment/update") // 댓글 수정
	@ResponseBody
	private int mCommentServiceUpdateProc(@SessionAttribute(name = "id", required = false) @RequestParam int cno,
			@RequestParam String content, Model model) {
		CommentVO comment = new CommentVO();
		comment.setCno(cno);
		comment.setContent(content);
		return dao.commentUpdateService(comment);
	}

	@PostMapping("/comment/delete/{cno}") // 댓글 삭제
	@ResponseBody
	private int mCommentServiceDelete(@SessionAttribute(name = "id", required = false) @PathVariable int cno,
			Model model) {
		CommentVO comment = new CommentVO();
		return dao.commentDeleteService(cno);
	}

//	==========================================================================================================================

	@GetMapping("/master/list")
	public String masterList(@SessionAttribute(name = "id", required = false) String id, PagingVO vo, Model model,
			@RequestParam(value = "nowPage", required = false) String nowPage,
			@RequestParam(value = "cntPerPage", required = false) String cntPerPage) {
		if (id == null) {
			return "redirect:/bar/login";
		} else {
			int total = svc.countMaster();
			if (nowPage == null && cntPerPage == null) {
				nowPage = "1";
				cntPerPage = "15";
			} else if (nowPage == null) {
				nowPage = "1";
			} else if (cntPerPage == null) {
				cntPerPage = "15";
			}
			List<Map<String, String>> list = dao.getMemMap();
			model.addAttribute("list", list);
			vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
			model.addAttribute("paging", vo);
			model.addAttribute("viewAll", svc.selectMaster(vo));
			return "master/listMaster";
		}

	}

//	마스터 삭제기능
	@PostMapping("/master/userDelete")
	@ResponseBody
	public Map<String, Boolean> userDelete(@RequestParam(value = "chbox[]") int[] nums) {
		// System.out.println(nums.length+"확인");
		Map<String, Boolean> map = new HashMap<>();
		map.put("userDelete", svc.userDelete(nums));
		return map;
	}

//	마스터 수정화면
	@GetMapping("master/userEdit")
	public String userEdit(@SessionAttribute(value = "id", required = false) String id, Model model) {
		LoginVO login = dao.selectById(id);
		model.addAttribute("login", login);
		return "master/editMaster";
	}

//	==========================================================================================================================

//	공지사항 게시판 리스트 + 페이징
	@GetMapping("/notice/noticelist")
	public String noticeList(@SessionAttribute(name = "id", required = false) String id, PagingVO vo, Model model,
			@RequestParam(value = "nowPage", required = false) String nowPage,
			@RequestParam(value = "cntPerPage", required = false) String cntPerPage) {

		if (id == null) {
			return "redirect:/bar/login";
		} else {
			int total = svc.countNotice();
			if (nowPage == null && cntPerPage == null) {
				nowPage = "1";
				cntPerPage = "15";
			} else if (nowPage == null) {
				nowPage = "1";
			} else if (cntPerPage == null) {
				cntPerPage = "15";
			}
			List<Map<String, String>> list = dao.getNotice();
			model.addAttribute("list", list);
			vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
			model.addAttribute("paging", vo);
			model.addAttribute("viewAll", svc.selectNotice(vo));
			return "notice/noticelist";
		}

	}

//	게시판 글쓰기
	@GetMapping("/notice/noticeboard")
	public String noticeboard() {
		return "notice/noticeboard";

	}

	@PostMapping("/notice/noticeboard")
	@ResponseBody
	public Map<String, Boolean> notice(
			@SessionAttribute(name = "id", required = false) @RequestParam(name = "files", required = false) MultipartFile[] mfiles,
			HttpServletRequest request, NoticeVO notice) {
		Map<String, Boolean> map = new HashMap<>();
		boolean added = svc.noticeBoard(mfiles, request, notice);
		map.put("added", added);
		return map;

	}

//	파일 다운로드
	@GetMapping("/notice/download/{filename}")
	public ResponseEntity<Resource> noticedownload(HttpServletRequest request, @PathVariable String filename) {
		Resource resource = (Resource) resourceLoader.getResource("upload/" + filename);
		System.out.println("파일명:" + resource.getFilename());
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

	@GetMapping("/notice/file/download/{num}")
	public ResponseEntity<Resource> noticefileDownload(@PathVariable int num, HttpServletRequest request) {
		// attach 테이블에서 att_num 번호를 이용하여 파일명을 구하여 위의 방법을 사용
		String filename = svc.getNoriceFilename(num);
		Resource resource = (Resource) resourceLoader.getResource("upload/" + filename);
		// System.out.println("파일명:"+resource.getFilename());
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

//	게시판 수정화면보기

	@GetMapping("/notice/boardDetail")
	public String noticedetailBoard(@SessionAttribute(name = "id", required = false) @RequestParam int num,
			Model model) { // 일치시켜주면 // 들어감
		NoticeVO notice = svc.noticedetailNum(num);
		model.addAttribute("notice", notice);
		return "notice/noticedetail";
	}

	@GetMapping("/notice/boardEdit")
	public String noticedetailedit(@SessionAttribute(name = "id", required = false) @RequestParam int num,
			Model model) {
		NoticeVO notice = svc.noticedetailNum(num);
		model.addAttribute("notice", notice);
		return "notice/noticeedit";
	}

//	게시판 수정하기
	@PostMapping("/notice/boardUpdate")
	@ResponseBody
	public Map<String, Boolean> noticeupdateBoard(
			@SessionAttribute(name = "id", required = false) @RequestParam(name = "files", required = false) MultipartFile[] mfiles,
			HttpServletRequest request, NoticeVO notice, @RequestParam("delfiles") List<String> delfiles, Model model) {
		Map<String, Boolean> map = new HashMap<>();
		boolean noticeUpdated = svc.noticeUpdated(request, notice, mfiles, delfiles);
		map.put("noticeUpdated", noticeUpdated);
		return map;
	}

//	게시판 삭제
	@PostMapping("/notice/boardDelete")
	@ResponseBody
	public Map<String, Boolean> noticedeleted(@RequestParam int num) {
		Map<String, Boolean> map = new HashMap<>();
		map.put("noticeDeleted", svc.noticeDeleted(num));
		return map;
	}

	@PostMapping("/notice/file/delete")
	@ResponseBody
	public Map<String, Boolean> noticedeleteFileInfo(@RequestParam List<String> delfiles) {
		boolean noticedeleteFileInfo = svc.noticedeleteFileInfo(delfiles);
		Map<String, Boolean> map = new HashMap<>();
		map.put("noticedeleteFileInfo", noticedeleteFileInfo);
		return map;
	}

//	==========================================================================================================================shop

	@GetMapping("/shop/list")
	public String shopList(@SessionAttribute(name = "id", required = false) String id, PagingVO vo, Model model,
			@RequestParam(value = "nowPage", required = false) String nowPage,
			@RequestParam(value = "cntPerPage", required = false) String cntPerPage,
			@SessionAttribute(value = "cart", required = false) List<ProductVO> cart) {
		if (cart == null) {
			// System.out.println("세션에 카트생성");
			cart = new ArrayList<>();
			model.addAttribute("cart", cart);
		}

		if (id == null) {
			return "redirect:/bar/login";
		} else {
			int total = svc.countShop();
			if (nowPage == null && cntPerPage == null) {
				nowPage = "1";
				cntPerPage = "15";
			} else if (nowPage == null) {
				nowPage = "1";
			} else if (cntPerPage == null) {
				cntPerPage = "15";
			}

			List<Map<String, String>> list = dao.getShop();
			List<Map<String, String>> list2 = dao.getImg();
			model.addAttribute("list", list);
			model.addAttribute("list2", list2);
			vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
			model.addAttribute("paging", vo);
			model.addAttribute("viewAll", svc.selectShop(vo));
			return "shop/shoplist";
		}

	}

//	게시판 글쓰기
	@GetMapping("/shop/board")
	public String shopboard() {
		return "shop/shopboard";

	}

	@PostMapping("/shop/board")
	@ResponseBody
	public Map<String, Boolean> shop(
			@SessionAttribute(name = "id", required = false) @RequestParam(name = "files", required = false) MultipartFile[] mfiles,
			HttpServletRequest request, ProductVO product, ImgVO img, Model model) {
		Map<String, Boolean> map = new HashMap<>();
		boolean added = svc.shopBoard(mfiles, request, product);
		model.addAttribute("img", img);
		map.put("added", added);
		return map;

	}

//	파일 다운로드
	@GetMapping("/shop/download/{filename}")
	public ResponseEntity<Resource> shopdownload(HttpServletRequest request, @PathVariable String filename) {
		Resource resource = (Resource) resourceLoader.getResource("upload/" + filename);
		System.out.println("파일명:" + resource.getFilename());
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

	@GetMapping("/shop/file/download/{num}")
	public ResponseEntity<Resource> shopfileDownload(@PathVariable int num, HttpServletRequest request) {
		// attach 테이블에서 att_num 번호를 이용하여 파일명을 구하여 위의 방법을 사용
		String filename = svc.getNoriceFilename(num);
		Resource resource = (Resource) resourceLoader.getResource("upload/" + filename);
		// System.out.println("파일명:"+resource.getFilename());
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

//	게시판 수정화면보기

	@GetMapping("/shop/detail")
	public String shopdetailBoard(@SessionAttribute(name = "id", required = false) String id, @RequestParam int num,
			Model model, @SessionAttribute(value = "cart", required = false) List<ProductVO> cart) {
		// System.out.println(id+"사용자아이디");
		ProductVO product = svc.shopdetailNum(num);
		model.addAttribute("product", product);
		// System.out.println(product.getImg().get(0).getImgname()+"확인");
		if (cart == null) // 아직 세션에 cart 가 생성되어 있지 않은 경우에만 cart를 생성한다
		{
			model.addAttribute("cart", new ArrayList<ProductVO>());
		}
		return "shop/shopdetail";
	}

	@GetMapping("/shop/edit")
	public String shopdetailedit(@SessionAttribute(name = "id", required = false) @RequestParam int num, Model model) {
		ProductVO product = svc.shopdetailNum(num);
		model.addAttribute("product", product);
		return "shop/shopedit";
	}

//	게시판 수정하기
	@PostMapping("/shop/update")
	@ResponseBody
	public Map<String, Boolean> shopupdateBoard(
			@SessionAttribute(name = "id", required = false) @RequestParam(name = "files", required = false) MultipartFile[] mfiles,
			HttpServletRequest request, ProductVO product, @RequestParam("delfiles") List<String> delfiles,
			Model model) {
		Map<String, Boolean> map = new HashMap<>();
		boolean noticeUpdated = svc.shopUpdated(request, product, mfiles, delfiles);
		map.put("noticeUpdated", noticeUpdated);
		return map;
	}

//	게시판 삭제
	@PostMapping("/shop/delete")
	@ResponseBody
	public Map<String, Boolean> shopdeleted(@RequestParam int num) {
		Map<String, Boolean> map = new HashMap<>();
		map.put("noticeDeleted", svc.shopDeleted(num));
		return map;
	}

	@PostMapping("/shop/file/delete")
	@ResponseBody
	public Map<String, Boolean> shopdeleteFileInfo(@RequestParam List<String> delfiles) {
		boolean noticedeleteFileInfo = svc.shopdeleteFileInfo(delfiles);
		Map<String, Boolean> map = new HashMap<>();
		map.put("noticedeleteFileInfo", noticedeleteFileInfo);
		return map;
	}

//	===========================================================================================================

//	상품 목록 체크값 넘기기
	@PostMapping("/shop/shopChoice")
	@ResponseBody
	public Map<String, Boolean> shopChoice(@RequestParam(value = "chbox[]") int[] list,
			@SessionAttribute(value = "cart", required = false) List<ProductVO> cart) {
		Map<String, Boolean> map = new HashMap<>();
		map.put("shopChoice", svc.shopChoice(list, cart));
		return map;
	}

//	===========================================================================================================

	@PostMapping("/cart/add")
	@ResponseBody
	public Map<String, Boolean> cartAdd(ProductVO product,
			@SessionAttribute(value = "cart", required = false) List<ProductVO> cart) {
		boolean added = svc.addItem(product, cart);
		Map<String, Boolean> map = new HashMap<>();
		map.put("added", added);
		return map;
	}

	@GetMapping("/cart/list")
	public String showCart(Model model, @SessionAttribute(value = "cart", required = false) List<ProductVO> cart) {
		if (cart.size() == 0) {
			// System.out.println("세션에 카트생성");
//			cart = new ArrayList<>();
			model.addAttribute("list", cart);
			return "/cart/cartlist";
		} else {
			model.addAttribute("list", dao.getCartProd(cart));
			model.addAttribute("total", svc.getTotalPrice(cart));

		}
		return "/cart/cartlist";
	}

	@PostMapping("/cart/order")
	@ResponseBody
	public Map<String, Boolean> orderBook(@SessionAttribute(value = "cart", required = false) List<ProductVO> cart) {
		boolean ordered = svc.order(cart);
		Map<String, Boolean> map = new HashMap<>();
		map.put("ordered", ordered);
		return map;
	}

	@PostMapping("/cart/delete")
	@ResponseBody
	public Map<String, Boolean> cartDeleted(Model model, @RequestParam(value = "chbox[]") int[] nums,
			@SessionAttribute(value = "cart", required = false) List<ProductVO> cart) {
		System.out.println(nums.length + "c" + cart.size());
		Map<String, Boolean> map = new HashMap<>();
		boolean deleted = svc.cartDeleted(cart, nums);
		map.put("deleted", deleted);
		return map;
	}

}
