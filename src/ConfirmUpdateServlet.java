import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

@WebServlet("/confirmUpdateServlet")
public class ConfirmUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html,charset=utf-8");
        int id=Integer.parseInt(request.getParameter("id"));
        String hiddenname=request.getParameter("hiddenname");
        String name=request.getParameter("name");
        String author=request.getParameter("author");
        String press=request.getParameter("press");
        double price=Double.parseDouble(request.getParameter("price"));
        if(name==""||name==null){
            JOptionPane.showMessageDialog(null, "修改后的书名不能为空，请重新修改","标题【警告】" , JOptionPane.ERROR_MESSAGE);
            request.getRequestDispatcher("bookServlet").forward(request, response);
            return;
        }

        Conn db=new Conn();
        String sql="select NAME from book where NAME!=?";
        Object[] o={hiddenname};
        ResultSet rs=db.select(sql, o);
        try {
            int num=0;
            while(rs.next()){
                if(name.equals(rs.getString("NAME"))){
                    num=1;
                }
            }
            if(num==1){
                JOptionPane.showMessageDialog(null, "您修改后的书名已存在，请重新修改","标题【警告】" , JOptionPane.ERROR_MESSAGE);
                request.getRequestDispatcher("bookServlet").forward(request, response);
            }else{
                String sql1="update book set NAME=?,AUTHOR=?,PRESS=?,PRICE=? where ID=?";
                Object[] o1={name,author,press,price,id};
                int i=db.update(sql1, o1);
                if(i>0){
                    JOptionPane.showMessageDialog(null, "修改成功，即将跳转到图书查询页面","标题【成功】" , JOptionPane.ERROR_MESSAGE);
                    request.getRequestDispatcher("bookServlet").forward(request, response);
                }else{
                    JOptionPane.showMessageDialog(null, "修改失败，请确认信息后重新修改","标题【失败】" , JOptionPane.ERROR_MESSAGE);
                    request.getRequestDispatcher("bookServlet").forward(request, response);
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}


