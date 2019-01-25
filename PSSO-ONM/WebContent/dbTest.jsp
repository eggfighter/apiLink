<%@ page import="java.sql.*"%>
<%@ page import="com.microsoft.sqlserver.jdbc.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link href="http://api.econovation.co.kr/css/common/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<%

	try{
	      String connectionUrl = "jdbc:sqlserver://192.168.151.97:1433;" +
		           "databaseName=PSSO;username=PSSO;password=PSSODBPASS$;";
		       Connection con;
			
			   con = DriverManager.getConnection(connectionUrl);
			   String SQL = "SELECT * FROM CLIENTKEY where SEQ=2";
		        Statement stmt;
			
				stmt = con.createStatement();
			
				ResultSet rs = stmt.executeQuery(SQL);
				
				while (rs.next()) {
					System.out.println(rs.getString("DOMAIN"));
				}
				rs.close();
			    stmt.close();
			    con.close();
			
	}catch(SQLException e){
		// TODO Auto-generated catch block
		e.printStackTrace();	
	}finally
	{
		System.out.println("aaaa");
	}
%>
</body>
</html>

 