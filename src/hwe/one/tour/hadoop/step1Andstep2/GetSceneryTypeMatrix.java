package hwe.one.tour.hadoop.step1Andstep2;

import java.io.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.fabric.xmlrpc.base.Array;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * 
 * @author Administrator
 * 
 * 查询景点属性，获取矩阵，写入文件
 *
 */
public class GetSceneryTypeMatrix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			getSceneryType();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	//查询数据库景点属性,向指定文件添加记录。
	public static void getSceneryType() throws SQLException {
		
		System.out.println("进入SceneryType方法");
		
		String  sql = "SELECT scenery.s_id, scenery.s_type FROM scenery";
		String types[] = {"山岳", "湖泊", "河川", "瀑布", "森林", "岩溶", "火山", "人文风景", "海岛海滨"};
		
		Connection con = getConnection();
		
		PreparedStatement pre = (PreparedStatement) con.prepareStatement(sql);
		
		ResultSet rs = pre.executeQuery();
		
		String result = ""; //格式：FId tab 属性id_0/1
		//取出集合中的数据并输出到指定文件
		while(rs.next()) {
			String magix[] = {"1_0", "2_0", "3_0", "4_0", "5_0", "6_0", "7_0", "8_0", "9_0"};
			
			int scId =  rs.getInt("s_id");
			String scType = rs.getString("s_type");
			
			result = "F" + scId + "\t";
			
			String[] scTypes = scType.split(";");
			
			if(scTypes.length <= 1) {
				
				int index = indexOfStr(types, scTypes[0]);
				
				magix[index] = (index + 1) + "_1";
				
			}else {
				
				for(int i = 0; i < scTypes.length; i++) {
					
					int index = indexOfStr(types, scTypes[i]);
					
					magix[index] = (index + 1) + "_1";
				}
				
			}
			
			result += magixtoString(magix);
			
			addTextContent(result);
			
		}
		
	}
	
	//获取数据库连接对象
	public static Connection getConnection() {
		
		System.out.println("进入getConnection方法");
		
		Connection con = null;
		
		try {
			//加载数据驱动
			Class.forName("com.mysql.jdbc.Driver");
			
			//获取数据连接对象
			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/tour?useUnicode=true&characterEncoding=utf-8&useSSL=false", "root", "123456");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(con != null) {
			return con;
		}
		
		return null;
	}
	
	//判断一个字符串在一个字符串数组中的位置
	public static int indexOfStr(String str[], String test) {
		
		System.out.println("进入indexOfStr方法");
		
		for(int i = 0; i < str.length; i++) {
			
			if(str[i].equals(test)) {
				
				return i; //返回索引位置
				
			}
			
		}
		
		return -1;
	}
	
	//把magix字符串相加
	public static String magixtoString(String str[]) {
		
		System.out.println("进入magixtoString方法");
		
		String res = "";
		for(int i = 0; i < str.length; i++) {
			
			res = res + str[i] + ";";
			
		}
		
		if(res.endsWith(";")) {
			res = res.substring(0, (res.length() - 1));
		}
		
		return res;
	}

	//将字符串追加到txt文件
	public static void addTextContent(String str) {
		
		System.out.println("进入addTextContent方法，参数为：" + str);
		
		FileWriter fw = null;
		
		File file = new File("D:\\txtFile\\sceneryType.txt");
		
		try {
			
			fw = new FileWriter(file, true);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PrintWriter pw = new PrintWriter(fw);
		
		pw.println(str);
		
		pw.flush();
		
		try {
			fw.flush();
			pw.close();
			fw.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
