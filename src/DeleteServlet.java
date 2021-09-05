import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

@WebServlet("/deleteServlet")
public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html,charset=utf-8");
        int id=Integer.parseInt(request.getParameter("id"));
        Conn db=new Conn();
        String sql="delete from book where ID=?";
        Object[] o={id};
        int i=db.update(sql, o);
        if(i>0){
            JOptionPane.showMessageDialog(null, "删除成功","标题【成功】" , JOptionPane.ERROR_MESSAGE);
            //request.getRequestDispatcher("bookquery.jsp").forward(request, response);
            response.sendRedirect("bookServlet");
        }else{
            JOptionPane.showMessageDialog(null, "删除失败","标题【失败】" , JOptionPane.ERROR_MESSAGE);
            response.sendRedirect("bookServlet");
        }
    }
}


