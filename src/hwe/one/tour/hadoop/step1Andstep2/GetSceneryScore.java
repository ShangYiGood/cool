package hwe.one.tour.hadoop.step1Andstep2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import sun.awt.datatransfer.DataTransferer.ReencodingInputStream;


/**
 * 
 * @author Administrator
 *
 *	从数据库读取信息，格式化输入文件
 */
public class GetSceneryScore {
	
	public static void main(String[] args) {
		
		try {
			getSceneroyScoreMagix();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//获取用户评分，并以矩阵形式输出到txt文件 格式：Uid tab 景点id_评分
	public static void getSceneroyScoreMagix() throws SQLException, IOException {
		
		String sqlScenery = "select * from scenery";
		String sqlScore = "select * from score";
		String userCountSql = "SELECT DISTINCT score.`sc_u_id` FROM score";
		
		PreparedStatement pre = null;
		ResultSet res;
		int userCount = 0;
		int sceneryCount = 0;
		Map<Integer, String[]> mapSceneryScore = new HashMap<>();
		
		//获得横向量数量：有多少个用户参与评分
		pre = getPre(userCountSql);
		res = pre.executeQuery();
		userCount = getResultSetCount(res);
		System.out.println("有" + userCount + "个用户参与了评论!");
		
		//获得景点表数量:一共有多少个景点
		pre = getPre(sqlScenery);
		res = pre.executeQuery();
		sceneryCount = getResultSetCount(res);
		System.out.println("一共有" + sceneryCount + "景点！");
		
		//查询评分：并将其加入初始化评分表
		pre = getPre(sqlScore);
		res = pre.executeQuery();
		
		String[] strSceneryScore = null;
		res.beforeFirst();
		while(res.next()) {
			
			int userId = res.getInt("sc_u_id");
			
			strSceneryScore = mapSceneryScore.get(userId);
			
			if(strSceneryScore == null) {
				
				mapSceneryScore.put(userId, getInitSceneryScore(sceneryCount));
				
			}else {
				
				int sceneryIndex = res.getInt("sc_s_id") - 1;
				
				System.out.println("改变的列：" + res.getInt("sc_s_id"));
				
				strSceneryScore[sceneryIndex] = (sceneryIndex + 1) + "_" + res.getInt("score");
				
				mapSceneryScore.put(userId, strSceneryScore);
				
			}
		}
		
		//获取评论的用户的id
		pre = getPre(userCountSql);
		res = pre.executeQuery();
		
		String addMagex = null;
		while(res.next()) {
			
			int userId = res.getInt("sc_u_id");
			strSceneryScore = mapSceneryScore.get(userId);
			
			addMagex ="U" + userId + "\t" + formatStrSceneryScore(strSceneryScore);
			
			addTxtContent(addMagex);
			
		}
		
	}
	
	//添加到txt文件
	public static void addTxtContent(String str) throws IOException {
		
		FileWriter fw = null;
		
		File file = new File("D:\\txtFile\\userSceneryScore.txt");
		
		fw = new FileWriter(file, true);
		
		PrintWriter  pw = new PrintWriter(fw);
		
		pw.println(str);
		
		pw.flush();
		fw.flush();
		pw.close();
		fw.close();
	}
	
	//格式化行评分矩阵
	public static String formatStrSceneryScore(String str[]) {
		
		String result = "";
		
		for(int i = 0; i < str.length; i++) {
			
			result += str[i] + ";";
			
		}
		
		if(result.endsWith(";")) {
			
			result = result.substring(0, (result.length() - 1));
			
		}
		
		System.out.println("formatStrSceneryScore方法中：" + result);
		
		return result;
	}
	
	//得到初始化景点评分
	public static String[] getInitSceneryScore(int sceneryCount) {
		
		String result[] = new String[sceneryCount];
		for(int i = 0; i < sceneryCount; i++) {
			
			result[i] = ( i + 1) + "_" + 0;
			
		}
		
		System.out.println("初始化方法：" + Arrays.toString(result));
		
		
		return result;
		
	}
	
	public static PreparedStatement getPre(String sql) throws SQLException {
		
		Connection con = GetSceneryTypeMatrix.getConnection();
		
		PreparedStatement pre = (PreparedStatement) con.prepareStatement(sql);
		
		return pre;
		
	}
	
	public static int getResultSetCount(ResultSet res) throws SQLException {
		
		int count = 0;
		
		if(res == null) {
			
			System.out.println("ResultSet集合中没有数据!");
			
			return 0;
			
		}
		
		
		while(res.next()) {
			
			count++;
		}
		
		return count;
		
	}

}
