import user.User;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html,charset=utf-8");
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        if(username==null||username.equals("")||password==null||password.equals("")){
            JOptionPane.showMessageDialog(null, "账号或密码有误","标题【出错啦】" , JOptionPane.ERROR_MESSAGE);
            response.sendRedirect("login.html");
            return;
        }

        Conn db=new Conn();
        String sql="select * from user where USERNAME=? and PASSWORD=?";
        Object[] o={username,password};
        ResultSet rs=db.select(sql, o);
        try {
            if(rs.next()){
                User user=new User();
                user.setUsername(username);
                user.setPassword(password);
                request.setAttribute("user", user);
                request.getRequestDispatcher("book.jsp").forward(request, response);
            }else{
                JOptionPane.showMessageDialog(null, "账号或密码错误","标题【出错啦】" , JOptionPane.ERROR_MESSAGE);
                response.sendRedirect("login.html");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


