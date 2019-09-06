package hwe.one.tour.hadoop.step3;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Step3Main {
	
	private static String inPath = "/hadoopProject/step3/input3/sceneryType2.txt";
	private static String outPath = "/hadoopProject/step3/output3";
	
	private static String cache = "/hadoopProject/step3/input3/userSceneryScore.txt";
	
	private static String hdfs = "hdfs://192.168.202.129:9000";
	
	public int run() {
		try {
			Configuration conf = new Configuration();
			conf.set("fs.defaultFS", hdfs);
		
			Job job = Job.getInstance(conf, "step3"); //矩阵相乘
			
			job.addCacheFile(new URI(cache + "#matrix2"));
			
			job.setJarByClass(Step3Main.class);
			
			job.setMapperClass(RecommendMapper.class);
			job.setReducerClass(RecommendReducer.class);
			
			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(Text.class);
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(Text.class);
			
			FileSystem fs = FileSystem.get(conf);
			
			Path inputPath = new Path(inPath);
			if(fs.exists(inputPath)) {
				FileInputFormat.addInputPath(job, inputPath);
			}
			
			Path outputPath = new Path(outPath);
			fs.delete(outputPath, true);
			
			FileOutputFormat.setOutputPath(job, outputPath);
			
			return job.waitForCompletion(true) ? 1 : -1;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
		
	}
	
	public static void main(String args[]) {
		
		int b = new Step3Main().run();
		
		if(b == 1) {
			System.out.println("step3运行成功!/////////////////////////////////");
		}else {
			System.out.println("step3运行失败!/////////////////////////////////");
		}
		
	}

}
