package hwe.one.tour.hadoop.step4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;

public class CosMapper extends Mapper<LongWritable, Text, Text, Text>{

	private Text outKey = new Text();
	private Text outValue = new Text();

	private List<String> cacheList = new ArrayList<String>();

	@Override
	protected void setup(Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		super.setup(context);

		// 通过输入流将全局缓存中的数据读入Java容器中 List<String>
		FileReader fr = new FileReader("matrix2");
		BufferedReader br = new BufferedReader(fr);

		// 每一行的格式:行 tab 列_值，列_值，列_值，。。。。
		String line = null;
		while ((line = br.readLine()) != null) {
			cacheList.add(line);
		}

		fr.close();
		br.close();

	}
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		
		String rowNum_matrixLeft = value.toString().split("\t")[0];
		String columnNum_Value_array_matrixLeft[] = value.toString().split("\t")[1].split(";");
		DecimalFormat df = new DecimalFormat("0.00");
		
		//先算分子：
		for (String string2 : cacheList) {

			String rowNum_matrixRight = string2.split("\t")[0].substring(1);
			String column_value_array_matrixRight[] = string2.split("\t")[1].split(";");
			double molecule = 0;  //分子
			double denominator = 0;  //分母
			int denominator_left = 0;
			int denominator_right = 0;

			for (String string : columnNum_Value_array_matrixLeft) {

				String column_matrixLeft = string.split("_")[0];
				String value_matrixLeft = string.split("_")[1];
				

				for (String string3 : column_value_array_matrixRight) {

					String value_matrixRight = string3.split("_")[1];

					if (string3.startsWith(column_matrixLeft + "_")) {
						
						denominator_left += Math.pow(Integer.parseInt(value_matrixRight), 2);
						denominator_right += Math.pow(Integer.parseInt(value_matrixLeft), 2);
						
						molecule += Integer.parseInt(value_matrixRight) * Integer.parseInt(value_matrixLeft);
					}

				}

			}
			
			denominator = Math.sqrt(denominator_left) * Math.sqrt(denominator_right);
			
			outKey.set(rowNum_matrixLeft);
			outValue.set(rowNum_matrixRight + "_" + df.format(molecule / denominator));
			
			context.write(outKey, outValue);

		}
		
	}
}
