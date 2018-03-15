import java.sql.Connection;
//import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
//import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class java_connect3
{
    //声明Connection对象
    static  Connection con;
    //驱动程序名
    static String driver = "com.mysql.jdbc.Driver";
    //URL指向要访问的数据库名mydata
    static String url = "jdbc:mysql://localhost:3306/user_cmx";
    //MySQL配置时的用户名
    static String user = "root";
    //MySQL配置时的密码
    static String password = "123456";
    
	public static void main(String[] args) throws SQLException, ParseException
	{
		// TODO Auto-generated method stub
//		package sqldemo;

		  	String name;
	        String id;
	     
	        PreparedStatement psql;
	        ResultSet res;
		    
		        //遍历查询结果集
		        try {
		            //加载驱动程序
		            Class.forName(driver);
		            //1.getConnection()方法，连接MySQL数据库！！
		            con = DriverManager.getConnection(url,user,password);
		            if(!con.isClosed())
		                System.out.println("Succeeded connecting to the Database!");
		            //2.创建statement类对象，用来执行SQL语句！！
		            Statement statement = con.createStatement();
		            //要执行的SQL语句
		            String sql = "select * from emp";
		            //3.ResultSet类，用来存放获取的结果集！！
		            ResultSet rs = statement.executeQuery(sql);
		            System.out.println("-----------------");
		            System.out.println("执行结果如下所示:");  
		            System.out.println("-----------------");  
		            System.out.println("工号" + "\t" + "姓名" + "\t" + "职称");  
		            System.out.println("-----------------");  
		             
		            String  str_empNO = null;
		           String  job = null;
		             id = null;
		            while(rs.next()){
		            		//获取stuname这列数据
		            	str_empNO = rs.getString("empno");
		                //获取stuname这列数据
		                job = rs.getString("job");
		                //获取stuid这列数据
		                id = rs.getString("ename");

		                //输出结果
		                System.out.println(str_empNO + "\t" + id + "\t" + job);
		            }
		          
		            //预处理添加数据，其中有两个参数--“？”
			        psql = con.prepareStatement("insert into emp (empno,ename,job,hiredate,sal) "
			                + "values(?,?,?,?,?)");
			        psql.setInt(1, 89);              //设置参数1，创建id为3212的数据
			        psql.setString(2, "xxx");      //设置参数2，name 为王刚
			        psql.setString(3, "yyy");
			         
		            
			        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
			        Date myDate2 =  dateFormat2.parse("2010-09-13");
			        psql.setDate(4,new java.sql.Date(myDate2.getTime()));
			        psql.setFloat(5, (float) 2000.3);
			        psql.executeUpdate();           //执行更新
			       
		            /*
			        //预处理更新（修改）数据，将王刚的sal改为5000.0
			        psql = con.prepareStatement("update emp set sal = ? where ena me =  ?");
			        psql.setInt(1, 5000);      
			        psql.setString(2,"abcd");             
			        psql.executeUpdate();
			        
			        //  PreparedStatement psql;
				      //预处理删除数据
				      psql = con.prepareStatement("delete from emp where sal = 12");
				      psql.setInt(1, 4500);
				      psql.executeUpdate();
				      psql.close();
				      */
		            rs.close();
		            con.close();
		        } catch(ClassNotFoundException e) {   
		            //数据库驱动类异常处理
		            System.out.println("Sorry,can`t find the Driver!");   
		            e.printStackTrace();   
		            } catch(SQLException e) {
		            //数据库连接失败异常处理
		            e.printStackTrace();  
		            }catch (Exception e) {
		            // TODO: handle exception
		            e.printStackTrace();
		        }finally{
		            System.out.println("数据库数据成功获取！！");
		        }
		        
		      
		      
		        
		        
		  
		        
		     
		        
		        
		        
		    }
	}


