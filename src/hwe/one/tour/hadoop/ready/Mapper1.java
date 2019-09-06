package hwe.one.tour.hadoop.ready;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Mapper1 extends Mapper<LongWritable, Text, Text, Text>{

	private Text outKey = new Text();
	private Text outValue = new Text();
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		
		
		String line[] = value.toString().split("\t");
		
		String rowLine = line[0].substring(1);
		String columns[] = line[1].split(";");
		
		for(int i = 0; i < columns.length; i++) {
			
			String columnNum = columns[i].split("_")[0];
			String columnValue = columns[i].split("_")[1];
			
			outKey.set(columnNum);
			outValue.set(rowLine + "_" + columnValue);
			
			context.write(outKey, outValue);
			
		}
		
	}

}
