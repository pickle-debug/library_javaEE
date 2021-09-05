import user.Book;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/updateServlet")
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html,charset=utf-8");
        int id=Integer.parseInt(request.getParameter("id"));
        String name=request.getParameter("name");
        String author=request.getParameter("author");
        String press=request.getParameter("press");
        double price=Double.parseDouble(request.getParameter("price"));

        Book book=new Book();
        book.setId(id);
        book.setName(name);
        book.setAuthor(author);
        book.setPress(press);
        book.setPrice(price);
        HttpSession session=request.getSession();
        session.setAttribute("book", book);
        request.getRequestDispatcher("update.jsp").forward(request, response);
    }
}


