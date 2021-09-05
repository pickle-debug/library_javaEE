import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html,charset=utf-8");
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String birthday=request.getParameter("birthday");
        String gender=request.getParameter("gender");
        if(username.equals("")||username.equals(null)||password.equals("")||password.equals(null)){
            JOptionPane.showMessageDialog(null, "用户名或密码不能为空","标题【警告】" , JOptionPane.ERROR_MESSAGE);
            response.sendRedirect("register.html");
            return;
        }

        Conn db=new Conn();
        String sql="select * from user where username=?";
        Object[] o={username};
        ResultSet rs=db.select(sql, o);
        try {
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "该用户已存在","标题【警告】" , JOptionPane.ERROR_MESSAGE);
                response.sendRedirect("register.html");
            }else{
                String sql1="insert into user(USERNAME,PASSWORD,BIRTHDAY,GENDER) values(?,?,?,?)";
                Object[] o2={username,password,birthday,gender};
                db.update(sql1, o2);
                JOptionPane.showMessageDialog(null, "注册成功，即将跳转到登录页面","标题【成功】" , JOptionPane.ERROR_MESSAGE);
                response.sendRedirect("login.html");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


