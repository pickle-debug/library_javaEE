import user.Book;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/bookServlet")
public class BookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html,charset=utf-8");
        Conn db=new Conn();
        String sql="select * from book";
        Object[] o={};
        ResultSet rs=db.select(sql, o);
        ArrayList<Book> booklist=new ArrayList<>();
        try {
            while(rs.next()){
                Book book=new Book();
                book.setId(rs.getInt("ID"));
                book.setName(rs.getString("NAME"));
                book.setAuthor(rs.getString("AUTHOR"));
                book.setPress(rs.getString("PRESS"));
                book.setPrice(rs.getDouble("PRICE"));
                booklist.add(book);
            }

            HttpSession session=request.getSession();
            session.setAttribute("booklist", booklist);
            request.getRequestDispatcher("bookquery.jsp").forward(request, response);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}


