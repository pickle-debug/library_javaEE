import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

@WebServlet("/addbookServlet")
public class AddbookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html,charset=utf-8");
        String name=request.getParameter("name");
        String author=request.getParameter("author");
        String press=request.getParameter("press");
        String price1=request.getParameter("price");
        if(name.equals("")||name.equals(null)||author.equals("")||author.equals(null)||press.equals("")||press.equals(null)||price1.equals("")||price1.equals(null)){
            JOptionPane.showMessageDialog(null, "输入的信息不能为空","标题【警告】" , JOptionPane.ERROR_MESSAGE);
            response.sendRedirect("addbook.html");
            return;
        }
        double price=Double.parseDouble(price1);//要通过上面的if语句确定price1不为空才能对其转型

        Conn db=new Conn();
        String sql="select NAME from book where NAME=?";
        Object[] o={name};
        ResultSet rs=db.select(sql, o);
        try {
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "该书本已存在","标题【警告】" , JOptionPane.ERROR_MESSAGE);
                response.sendRedirect("addbook.html");
            }else{
                String sql1="insert into book(NAME,AUTHOR,PRESS,PRICE) values(?,?,?,?)";
                Object[] o1={name,author,press,price};
                int i=db.update(sql1, o1);
                if(i>0){
                    JOptionPane.showMessageDialog(null, "添加成功","标题【成功】" , JOptionPane.ERROR_MESSAGE);
                    response.sendRedirect("addbook.html");
                }else{
                    JOptionPane.showMessageDialog(null, "添加失败","标题【失败】" , JOptionPane.ERROR_MESSAGE);
                    response.sendRedirect("addbook.html");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


