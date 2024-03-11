# 4차 미니프로젝트 : Shopping mall 구조 Spring mvc 구조로 리팩토링 

### 🛠️ Tech Stack
[![Java](https://img.shields.io/badge/-Java-007396?style=flat-square&logo=Java&logoColor=white)](https://www.java.com/)
[![Spring](https://img.shields.io/badge/-Spring-6DB33F?style=flat-square&logo=Spring&logoColor=white)](https://spring.io/)
[![Spring Tool Suite](https://img.shields.io/badge/-Spring_Tool_Suite-6DB33F?style=flat-square&logo=Eclipse&logoColor=white)](https://spring.io/tools)
[![MyBatis](https://img.shields.io/badge/-MyBatis-1F262D?style=flat-square&logo=MyBatis&logoColor=blue)](https://mybatis.org/)
[![JDBC](https://img.shields.io/badge/-JDBC-007396?style=flat-square&logo=Java&logoColor=white)](https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/)

<br>

### 📝 프로젝트 소개
: 기존의 쇼핑몰을 MVC패턴으로 구성해뒀던 것을 sts를 활용하여 스프링 구조로 변경하여 코드의 유지보수성을 향상 시키고자 함. 

<br>

### 💾 프로젝트 상세내용 

#### 변경된 부분

- 기존의 JDBC 방식을 Mybatis로 변경함. 
- 기존 Servlet을 Spring의 @어노테이션을 활용하여 Spring MVC구조로 변경함.

<br>

#### 구조 변경 계기 및 필요성

- 코드의 유지보수성 향상 
- 의존성 주입 
- 트렌젝션을 활용 
- 비즈니스 로직 구현에 집중

<br>

### 🤼 역할분담

- 🙋‍♀️ 강민희(본인) 
1. ProductController
2. OrderController
3. MyPageController
4. CartController
   <br>
   
- 박경리
1. QnaController
2. AdminQnaController
3. AdminOrderController
   <br>
  
- 이경승
1. MemberController
2. AdminMemberController
3. AdminProductController
 
<br>

### 변경된 아키텍처 및 구조

- 변경 전 > <br>
![mvc 구조](https://github.com/kyungseung/Bootcamp_project04_shopping/assets/81572693/637878b6-6bc0-46d5-9378-29353a38327c)

<br>

- 변경 후 > <br>
![spring 구조](https://github.com/kyungseung/Bootcamp_project04_shopping/assets/81572693/bbb84d5e-84ac-4414-9232-36ba88474d81)

<br>

### 의존성 주입 및 IoC 컨테이너
- Java 기반 설정
: Java 클래스를 사용하여 빈들을 정의하고 구성함. <br>

```java
@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	/** 장바구니 목록 보기 **/
	@RequestMapping(value = "/cartList", method = RequestMethod.GET)
	public String cartList(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
	
		if (loginUser == null) {
			return "redirect:/members/login_form.do";
		} else {
			model.addAttribute("cartList",  cartService.getCartListForUser(loginUser));
		}
		return "mypage/cartList";
	}
```

 <br>

 ### Spring MVC 구조로 변경
 : 기존 Servlet을 Spring의 @어노테이션을 활용하여 Spring MVC구조로 변경함. <br>

- 변경 전 <br>
```java
 private void doHandle(HttpServletRequest request, HttpServletResponse response) 
								throws ServletException, IOException {
	String url = "";
		
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html; charset=utf-8");
		
	String action = request.getPathInfo();
	System.out.println("action:" + action);
		
	if(action.equals("/contract.do")) {					// 개인정보동의 페이지
		url = "/member/contract.jsp";
			
		request.getRequestDispatcher(url).forward(request, response);
		
	}
	else if (action.equals("/join_form.do")) {			// 개인정보 동의 유무를 넘겨줌
		url = "/member/join.jsp";
	
		request.getRequestDispatcher(url).forward(request, response);
	}
```
<br>

- 변경 후 > <br>
```java
@Controller
@RequestMapping("/members")
public class MemberController {
	
	@Autowired
	private MemberService memberService;

	/** 개인정보동의 페이지 **/
	@RequestMapping(value = "/contract", method = RequestMethod.GET)
	public String contract() throws Exception {
		return "/member/contract";
	}
	
	/** 개인정보 동의 유무를 넘겨줌 **/
	@RequestMapping(value = "/join_form", method = RequestMethod.POST)
	public String joinForm() throws Exception {
		return "/member/join";
	}
}
```

<br>

#### 📈 결과 및 성과
- Spring을 활용하여 구조를 변경하자 유지보수가 용이해짐. 
- 객체 생성과 같은 일을 스프링에게 위임하여 이번 프로젝트에서는 비즈니스 로직에 조금 더 초점을 맞춰 개발할 수 있게 됨. 
- 코드 가독성이 향상 됨.

#### 👩‍🎓 느낀 점 
- Spring 을 본격적으로 활용하기 시작하면서 편리한 라이브러리들을 경험하고 구현해볼 수 있어서 매우 흥미로웠고 프레임워크가 주는 편리함을 한 번 더 느낄 수 있었다. 
- Spring MVC 의 편리함을 깨닫게 됨.
  -  앞단에 프론트 컨트롤러를 두어 요청을 핸들링하게 함으로써 비즈니스 로직을 서비스 코드에 집중해서 작성할 수 있어 조금 더 가독성이 높고 유지보수가 간편한 코드를 작성할 수 있었다.
- 새로운 데이터베이스 접근 방식은 Mybatis 를 활용한 경험을 함.
  - JDBC 에서는 결과 집합을 가져와서 자바 객체로 수동으로 매핑해야했지만 Mybatis를 사용함으로써 SQL쿼리와 결과를 자동으로 매핑할 수 있어 편리했다.
  - SQL 분리 : SQL 쿼리를 XML 파일을 통해 분리해 가독성 높은 코드를 구성할 수 있었으며 쿼리 유지보수가 편리했다.
  - 조건문을 사용하여 동적으로 쿼리를 생성할 수 있어 편리했다. 자바 코드에 문자열 조합으로 작성했을 경우 띄어쓰기 하나로 오류가 났었지만 Mybatis를 사용하며 쿼리문 오류에 소비되는 시간을 줄일 수 있었다.
  - SqlSession 을 활용하여 트랜잭션 관리를 보다 편리하게 할 수 있었다.
