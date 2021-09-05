import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conn {
    static Connection conn=null;
    public Conn() {
        String url = "jdbc:mysql://localhost:3306/abc?useSSL=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        String driverName = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, "root", "12345678");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    PreparedStatement pstmt=null;
    int num=0;
    public int update(String sql,Object[] object){//增、删、改用的方法，返回一个整型数
        try {
            pstmt=conn.prepareStatement(sql);
            for(int i=0;i<object.length;i++){
                pstmt.setObject(i+1, object[i]);
            }
            num=pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    ResultSet rs=null;
    public ResultSet select(String sql,Object[] object){//查询用的方法，返回一个查询到的结果集
        try {
            pstmt=conn.prepareStatement(sql);
            for(int i=0;i<object.length;i++){
                pstmt.setObject(i+1,object[i]);
            }
            rs=pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void close(){
        if(pstmt!=null){
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(rs!=null){
            try {
                rs.close();
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


