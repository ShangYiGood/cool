package hwe.one.tour.hadoop.ready;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Reducer1 extends Reducer<Text, Text, Text, Text>{

	private Text outKey = new Text();
	private Text outValue = new Text();
	
	@Override
	protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		
		String columnValues = "";
		
		for (Text text : values) {
			columnValues += text + ";";
		}
		
		if(columnValues.endsWith(";")) {
			columnValues = columnValues.substring(0, columnValues.length() - 1);
		}
		
		outKey.set(key);
		outValue.set(columnValues);
		
		context.write(outKey, outValue);
		
	}
	
}
