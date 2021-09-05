<%@ page import="user.Book" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<%
    Book book=(Book)session.getAttribute("book");
    int id=book.getId();
    String name=book.getName();
    String author=book.getAuthor();
    String press=book.getPress();
    double price=book.getPrice();
%>
<center>
<h3>更改信息：</h3><a href="bookServlet"><button>返回上一层</button></a><br>
<form action="confirmUpdateServlet">
    <input type="hidden" value=<%=id %> name="id">
    编&nbsp;&nbsp;&nbsp;号：<span><%=id %></span><br>
    <input type="hidden" name="hiddenname" value=<%=name %>>
    书&nbsp;&nbsp;&nbsp;名：<input type="text" name="name" value=<%=name %>><br>
    作&nbsp;&nbsp;&nbsp;者：<input type="text" name="author" value=<%=author %>><br>
    出版社：<select name="press">
    <option value="人民教育出版社" <%="人民教育出版社".equals(press)? "selected":"" %>>人民教育出版社</option>
    <option value="清华出版社" <%="清华出版社".equals(press)? "selected":"" %>>清华出版社</option>
    <option value="北大出版社" <%="北大出版社".equals(press)? "selected":"" %>>北大出版社</option>
    <option value="智游出版社" <%="智游出版社".equals(press)? "selected":"" %>>智游出版社</option>
</select><br>
    价&nbsp;&nbsp;&nbsp;格：<input type="number" name="price" value=<%=price %>><br>
    <input type="submit" value="确认更改">
</form>
<a href="bookServlet"><button>取消修改</button></a>
</center>
</body>
</html>

