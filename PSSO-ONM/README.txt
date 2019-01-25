1. ONM 프로젝트 소개
 - 파트너SSO O&M은 파트너SSO 시스템의 고객정보 조회와 이벤트 통계 조회, 정보변경이력조회 등을 제공한다.

2. 사용한 프레임워크, 라이브러리
 - Spring Framework : 스프링의 webmvc, jdbc, tx(트랜잭션) 등을 사용한다.
 - Spring Security : O&M 로그인 및 세션관리를 위해 사용한다.
 - Log4J : O&M 로깅(logging)은 Log4J를 이용한다. VM 인수(-Dlog4j.logDir=D:/psso-logs)와 설정파일(log4j.xml)을 통해 설정한다.
 - MyBatis : SQL Mapper로 MyBatis(iBatis의 새버전)을 사용한다. 모든 SQL은 com.kt.psso.db.sql 패키지 아래 xml 파일에서 찾을 수 있다. 
 - Quartz : 이벤트 로그 수집, 외부연동 상태확인, 임시 데이터 삭제 등을 위한 스케줄러로 Quartz(쿼츠) 스케줄러를 사용한다.
 - Tiles, Velocity : O&M의 뷰(view)를 작성하기 위한 템플릿 프래임워크로 Tiles와 Velocity를 사용한다.

3. 설정파일
 * web.xml
  - 웹어플리케이션의 기술자(descriptor)라 불리는 파일이다. 필터 매핑, 서블릿 매핑, 세션 타임아웃과 같은 설정이 들어있다.
  - encoding 필터 : 스프링에서 제공하는 CharacterEncodingFilter이며, utf-8로 설정되어 있다.
  - scFilter 필터 : SpecialCharacterFilter 필터는 파라미터에 들어있는 특수문자(&, #, <. >, ...)로 인해 발생할 수 있는 보안이슈를 해결하기 위해 작성되었다.
  - action 서블릿 : 스프링에서 제공하는 DispatcherServlet이며, do 요청(/**/*.do)을 전담한다. 스프링은 요청을 Controller 전달한다.
                                   예를 들어 /customer/list.do라는 요청이 들어오면 스프링은 CustomerController 클래스의 list(req, res, searchVo) 메서드를 실행하는데,
                                   이는 개발자가 list() 메서드에 @RequestMapping("/customer/list.do") Annotation을 추가했기 때문이다.
  - velocity 서블릿 : html 요청(/**/*.html)을 처리하는 Velocity에서 제공하는 서블릿이다. 
 * Spring
  - applicationContext.xml : web.xml의 contextConfigLocation 파라미터에 지정된 스프링 설정파일이다. 웹어플리케이션이 시작될 때 전달되어 스프링 객체를 생성하는데 사용된다.
                                                   프파퍼티 (property) 객체들과 KTFCryptoUtil 등의 설정을 담고 있다.
  - action-servlet.xml : action 서블릿의 설정파일이다. 스프링이 서블릿이름-context.xml 파일을 자동으로 찾는다. component-scan, annotation-config 태그가 지정되어 있다.
                         base-package로 지정된 패키지 하위의 클래스에서 Annotation(@Controller, @RequestMapping, @Service, @Repository)을 스캔하여 스프링 객체를 생성한다.  
  - mybatis-context.xml : MyBatis를 사용하기 위한 스프링 설정파일이다. JNDI DataSource를 사용한다. Tomcat의 server.xml에 있는 Resource인 jdbc/pssodb를 사용한다.
  - scheduler-context.xml : Quartz(쿼츠) 스케줄러를 사용하기 위한 스프링 설정파일이다.
  - transaction-context.xml : 선언적 트랜잭션을 사용하기 위한 스프링 설정파일이다. 이벤트 로그를 수집하는 부분 (LineCollector.collectLines() 메서드)에 트랜잭션을 적용하여
                              Log 파일의 라인처리시 Exception이 발생하면 전체 Line에 대한 처리가 롤백(rollback)되도록 하였다.
 * Spring Security
  - applicationContext-security.xml : Spring Security 설정파일이다. OnmUserDetailsService, OnmUserDetail, OnmAuthenticationProvider 를 구현하여
	                                  Spring Security가 로그인을 처리할 수 있도록 설정하였다. <intercept-url> 태그는 URL 패턴 별로 권한(ROLE)을 지정한다.
 * Log4J
  - log4j.xml : 다섯 개의 DailyRollingFileAppender Appender를 정의하였다.
     -> 위치: D:\psso-logs, catalina.bat 파일에 VM 인수(-Dlog4j.logDir=D:/psso-logs)로 지정
     -> 백업정책: Daily 백업, ex) psso-onm.log.yyyy-MM-dd
     -> SQL로깅: psso-sql.log는 debug 이하의 레벨에서만 기록됨. 보안상 필요시에만 변경
     ------------------------------------------------------------
     로그파일명            로그레벨    설명
     ------------------------------------------------------------
     psso-onm.log         debug        O&M프로그램 로그
     psso-scheduler.log  info           스케줄러 로그
     psso-sql.log           info           SQL문 로그
     psso-etc.log          info/warn    라이브러리 로그
     velocity.log            info           라이브러리 로그
     ------------------------------------------------------------
 * MyBatis
  - mybatis-config.xml : MyBatis 자체 설정파일이다.
     -> MyBatis의 setting(cache 등)을 지정한다.
     -> typeAlias를 지정하여 XML 작성 시, SearchVo와 같은 타입을 기술할 때, 패키지를 포함하지 않고 간결하게 작성할 수 있다.
     -> XML 파일을 mapper로 지정해야만 MyBatis가 인식한다. 
 * Quartz
  - quartz.properties : Quartz 스케줄러의 설정파일이다. 실행 Thread Pool의 크기를 5로 지정하였다.
 * Tiles
  - tiles-config.xml : Tiles의 설정파일이다. 여러 페이지에 사용되는 공통 템플릿들 (header.html, left.html, navibar.html)을 frame.jsp에 포함하도록 설정하였다.
                       Controller의 각 메서드가 리턴하는 ModelAndView의 viewName과 탬플릿(html)을 연결한다. 
 * Velocity
  - velocity.properties : velocity 자체 설정파일이다. log4j를 사용하여 logging하도록 설정하였다.
  - velocity-toolbox.xml : velocity 탬플릿 파일(html)에서 사용할 툴을 정의하는 설정파일이다.
 * O&M
  - psso.properties : O&M에서 사용되는 각종 Property를 담고있는 설정파일이다.
  - menu.properties : 선택된 메뉴에 해당하는 상단 메뉴 파일번호와 Navibar의 내용을 담고 있는 설정파일이다.

4. 스프링 webmvc 흐름
 - do 요청(/**/*.do)이 들어오면 스프링의 Dispatcher으로 전달된다.
 - Dispatcher 서블릿은 Annotation(@Controller, @RequestMapping)을 통해 Controller 클래스의 메서드를 찾는다.
 - 찾은 메서드의 파라미터 객체(request, response, SearchVo 등)를 자동으로 요청 파라미터로 채운다.
 - 찾은 메서드를 호출한다. 
 - Controller의 메서드는 Service 객체를 Service는 DAO 객체를 이용하여 데이터 객체를 만든다.
 - Controller의 메서드는 ModelAndView 객체를 생성한다.
 - Controller의 메서드는 ModelAndView 객체에 viewName을 지정하고, 데이터 객체를 추가한 후 리턴한다.
 - Dispatcher 서블릿은 리턴된 viewName을 tiles에 전달한다.
 - tiles는 viewName에 맞는 템플릿을 찾아 데이터 객체와 함께 파싱하여 결과를 리턴한다.
 - tiles에 의해 리턴된 결과가 Dispatcher에 의해 클라이언트로 전달된다.  

5. View의 구성
 * 요청의 흐름
  - 브라우저의 요청 -> index.jsp -> _mainFrame (/customer/list.do) -> Controller가 viewName (customer.list) 리턴 -> tiles-config.xml에서 탬플릿 검색
 * tiles-config.xml 설정 요약
    <definition name="layout.frame" template="/WEB-INF/view/layout/frame.jsp">
        <put-attribute name="header" value="/WEB-INF/view/layout/header.html"/>
        <put-attribute name="left" value="/WEB-INF/view/layout/left.html"/>
        <put-attribute name="navibar" value="/WEB-INF/view/layout/navibar.html"/>
    </definition>
    <definition name="customer.list" extends="layout.frame">
        <put-attribute name="body" value="/WEB-INF/view/customer/list.html"/>
    </definition>
 * tiles 조합 결과
  - layout/frame.jsp - header (layout/header.html)
                     - left (layout/left.html)
                     - navibar (layout/navibar.html)
                     - body (customer/list.html)

6. 암호화
 - KTFCryptoUtil은 KT에서 제공하는 공통암호모듈을 사용하는 객체이다.
 - KTFCryptoUtil의 사용법은 KTFCryptoUtilTest.java에서 확인할 수 있다.
 - O&M에서는 두 가지 Hash 알고리즘을 사용해야 하기 때문에, applicationContext.xml에서 KTFCryptoUtil 객체를 두 개 생성하였다.

7. 스케줄러
 - 스케줄러 설정 파일(scheduler-context.xml)에 아래와 같은 작업이 설정되어 있다.
   ------------------------------------------------------------------------------------------------------------- 
   TRIGGER                         JOB                      TARGET OBJECT             설명
   ------------------------------------------------------------------------------------------------------------- 
   loginLogTrigger1                loginLogJob1             loginLogCollector1        로그인 로그수집
   loginLogTrigger2                loginLogJob2             loginLogCollector2        로그인 로그수집
   joinLogTrigger1                 joinLogJob1              joinLogCollector1         회원가입 로그수집
   joinLogTrigger2                 joinLogJob2              joinLogCollector2         회원가입 로그수집
   withdrawLogTrigger1             withdrawLogJob1          withdrawLogCollector1     회원탈퇴 로그수집
   withdrawLogTrigger2             withdrawLogJob2          withdrawLogCollector2     회원탈퇴 로그수집
   updateLogTrigger1               updateLogJob1            updateLogCollector1       정보변경 로그수집
   updateLogTrigger2               updateLogJob2            updateLogCollector2       정보변경 로그수집
   lastLogTrigger1                 lastLogJob1              lastLogCollector1         마지막접속일 업데이트
   lastLogTrigger2                 lastLogJob2              lastLogCollector2         마지막접속일 업데이트
   realnameTrigger                 realnameJob              realnameHealthChecker     외부연동(실명인증)
   ipinTrigger                     ipinJob                  ipinHealthChecker         외부연동(I-PIN)
   holderTrigger                   holderJob                holderHealthChecker       외부연동(핸드폰본인확인)
   dataCleanerTrigger              dataCleanerJob           dataCleaner               보안문자, 인증번호 삭제
   dataCleaner2Trigger             dataCleaner2Job          dataCleaner2              임시가입 삭제
   eventStatTimeCreatorTrigger     eventStatTimeCreatorJob  eventStatTimeCreator      통계 시간 생성
   ------------------------------------------------------------------------------------------------------------- 
 - 스케줄러 예약시간은 Trigger 객체의 cronExpression 속성에 지정되어 있다.
 - 설정파일 뿐만 아니라 O&M의 시스템관리-스케줄러설정 메뉴에서 현재 설정된 값을 확인할 수 있다.
 - Job 예제
    ------------------------------------------------------------------------------------------------------------
 	<bean id="lastLogJob1" class="org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean">
		<property name="group" value="LOG_COLLECTOR" />
		<property name="targetObject" ref="lastLogCollector1" />
		<property name="targetMethod" value="collect" />
		<property name="concurrent" value="false" />
		<property name="arguments">
			<array>
				<value>-1</value>
			</array>
		</property>
	</bean>
	------------------------------------------------------------------------------------------------------------
 - group 속성은 Job들을 그룹화한다.
 - 예약시간에 targetObject의 targetMethod가 호출된다.
 - concurrent 속성은 이전 스케줄러 시간에 시작된 Job이 끝나야만 스케줄러가 실행되도록 false로 지정한다.  
 - arguments 속성은 targetMethod 호출시 넘겨줄 메서드 인수를 지정한다.
 
8. 세션관리
 * 세션관리에 대한 설정은 web.xml과 applicationContext-security.xml에서 확인할 수 있다.
  - web.xml의 세션타임아웃 설정
  -------------------------------------------
    <session-config>
        <session-timeout>5</session-timeout>
    </session-config>
  -------------------------------------------

 * <intercept-url> 태그
  ---------------------------------------------------------------------------------------------
  <intercept-url pattern="/in/**" access="hasRole('ROLE_SUPERVISOR')" requires-channel="any" />
  ---------------------------------------------------------------------------------------------
  - 지정한 URL 패턴에 대하여 접근 권한을 설정한다. http, https 채널을 지정할 수도 있다.

 * <form-login> 태그
  ---------------------------------------------------------------------------------------------
  <form-login login-page="/login.do"
              default-target-url="/customer/list.do"
              authentication-failure-url="/login.do?error=true" />
  ---------------------------------------------------------------------------------------------
  - 로그인 페이지 URL을 설정한다.
  - 로그인 후 이동할 기본 페이지는 고객조회 메뉴로 설정하였다.
  - 로그인 실패 페이지 URL을 설정하였다. 

 * <logout> 태그
  ---------------------------------------------------------------------------------------------
  <logout logout-url="/logout.do" success-handler-ref="onmLogoutSuccessHandler" />
  ---------------------------------------------------------------------------------------------
  - 로그아웃 페이지  URL을 설정한다.
  - 로그아웃 할 때 로그를 남기기 위해서 success-handler를 구현하였다.

 * <concurrency-control> 태그
  ------------------------------------------------------------------------------------------------------
  <session-management>
      <concurrency-control max-sessions="1" error-if-maximum-exceeded="false" expired-url="/login.do" />
  </session-management>
  ------------------------------------------------------------------------------------------------------
  - 같은 계정으로 여러 PC에서 동시접속할 수 있는 최대값을 설정한다.
  - 같은 계정으로 오직 하나의 세션만이 허용되고, 두번쨰 PC에서 로그인이 성공하면 첫번째 세션은 제거된다.

 * <authentication-provider> 태그
  ------------------------------------------------------------------------------------------------------
  <authentication-manager alias="authenticationManager">
      <authentication-provider user-service-ref="userDetailsService" ref="onmAuthenticationProvider"/>
  </authentication-manager>
  ------------------------------------------------------------------------------------------------------
  - Spring-security의 로그인 처리에서 여기에 등록된 onmAuthenticationProvider를 사용하도록 한다.
 
 * onmAuthenticationProvider 객체
  - additionalAuthenticationChecks() 메서드를 구현하여 패스워드 오류 5회 검사, 접근 IP확인, 패스워드 변경주기 검사 등을 적용하였다.
  - retrieveUser() 메서드를 구현하여 Spring-security에게 UserDetails를 제공한다.
  - createSuccessAuthentication() 메서드는 커넥션 로그를 기록하기 위해 구현하였다.

 * userDetailsService 객체
  - Spring-security에게 UserDetails를 제공하는 서비스 객체이다.

 * onmLogoutSuccessHandler 객체
  - 로그아웃 할 때 로그를 남기기 위해서 구현하였다.
