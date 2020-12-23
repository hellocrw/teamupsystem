package crw.flink.wordcount;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

/**
 * 批处理word count
 */
public class WordCount {
    public static void main(String[] args) throws Exception {
        // 创建执行环境
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        // 从文件中读取数据
        String inputPath = "D:\\project\\temp\\teamupsystem\\flink-stream\\src\\main\\resources\\hello.txt";
        DataSet<String> dataSet = env.readTextFile(inputPath);
        // 对数据集处理
        DataSet<Tuple2<String, Integer>> result = dataSet.flatMap(new MyFlatMapper())
                .groupBy(0)  // 按第一位置分组
                .sum(1);// 第二个位置求和
        result.print();
    }

    public static class MyFlatMapper implements FlatMapFunction<String , Tuple2<String , Integer>> {

        @Override
        public void flatMap(String value, Collector<Tuple2<String, Integer>> collector) throws Exception {
            // 按空格分词
            String[] split = value.split(" ");
            for(String word : split) {
                collector.collect(new Tuple2<>(word, 1));
            }
        }
    }
}
