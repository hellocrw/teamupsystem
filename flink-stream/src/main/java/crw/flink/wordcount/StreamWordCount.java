package crw.flink.wordcount;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class StreamWordCount {
    public static void main(String[] args) throws Exception {
        // 创建流处理环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(8);  // 并行数
        // 文件读取
//        String inputPath = "D:\\project\\temp\\teamupsystem\\flink-stream\\src\\main\\resources\\hello.txt";
//        DataStream<String> dataStream = env.readTextFile(inputPath);

        // parameter tool工具从启动参数中提取配置项
        String host = "192.168.92.135";
        int port = 7777;

        // 从socket文本流读取数据
        DataStream<String> dataStream = env.socketTextStream(host, port);
        // 基于数据流进行转换计算
        DataStream<Tuple2<String, Integer>> resultStream = dataStream.flatMap(new WordCount.MyFlatMapper())
                .keyBy(0)
                .sum(1);
        resultStream.print();
        // 执行任务
        env.execute();
    }
}
