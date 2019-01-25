<%
session.invalidate(); 

%>
<script language="javascript">
	alert("잘못된 경로로 접근하셨습니다.");
	top.location.href ="/";
</script>