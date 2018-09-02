package cc.peihan.vancleef.util;

import cc.peihan.vancleef.config.Config;
import cc.peihan.vancleef.exception.OperateFailedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

import java.io.*;

public class YamlUtil {

    private static final Logger logger = LogManager.getLogger();

    public static Config getConfig() throws OperateFailedException {
        String path = "/Users/tancky/NewCentury/vancleef/src/main/resources/config.yml";
        Yaml yaml = new Yaml();
        Config config = null;
        try {
            config = yaml.loadAs(new FileInputStream(new File(path)), Config.class);
        } catch (FileNotFoundException e) {
            logger.error("path:{},exception{}", path, e);
            throw new OperateFailedException("read config yaml error");
        }
        return config;
    }


    public static void testReadFile() throws IOException {
        System.out.println("classpath路径： " + YamlUtil.class.getClassLoader().getResource("").getPath());
        //获取当前类的加载路径
        System.out.println("当前类加载路径： " + YamlUtil.class.getResource("").getPath());

        //String path = YamlUtil.class.getResource("/test.txt").getPath();

        String path = YamlUtil.class.getClassLoader().getResource("test.txt").getPath();


        System.out.println("path:" + path);
        readTxt(path);

    }

    public static void readTxt(String path) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
    }


    public static void main(String[] args) throws IOException {
        testReadFile();
    }

}
