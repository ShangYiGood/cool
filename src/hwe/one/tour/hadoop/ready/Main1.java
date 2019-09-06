package hwe.one.tour.hadoop.ready;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/*
 * 将右矩阵转置，准备相乘
 */

public class Main1 {
	
	String path = "hdfs://192.168.202.129:9000/hadoopProject/rady/input/sceneryType.txt";
	
	private int run() {
		
		try {
			Configuration conf = new Configuration();
			Job job = Job.getInstance(conf, "matrixTest");
			

			job.setJarByClass(Main1.class);
			

			job.setMapperClass(Mapper1.class);
			job.setReducerClass(Reducer1.class);
			

			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(Text.class);
			

			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(Text.class);
			
			FileSystem fs = FileSystem.get(conf);
			
			
			FileInputFormat.setInputPaths(job, new Path(path));
			
			Path outPath = new Path("hdfs://192.168.202.129:9000/hadoopProject/rady/output");
			fs.delete(outPath, true);
			FileOutputFormat.setOutputPath(job, outPath);
			
			return job.waitForCompletion(true) ? 1 : -1;
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return -1;
		
	}
	
	public static void main(String[] args) {
		
		int b = new Main1().run();
		
		if(b == 1) {
			System.out.println("运行成功！///////////////////////////////////////////////");
		}else {
			System.out.println("运行失败！///////////////////////////////////////////////");
		}
		
	}

}
