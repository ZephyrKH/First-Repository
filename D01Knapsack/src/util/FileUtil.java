package util;

import entity.ItemGroup;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件工具类：读取数据、保存结果
 */
public class FileUtil {

    /**
     * 从txt读取D0-1背包数据
     */
    public static List<ItemGroup> readData(String path) throws Exception {
        List<ItemGroup> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        br.readLine(); // 跳过标题行

        while ((line = br.readLine()) != null) {
            String[] arr = line.trim().split("\\s+");
            double w1 = Double.parseDouble(arr[0]);
            double v1 = Double.parseDouble(arr[1]);
            double w2 = Double.parseDouble(arr[2]);
            double v2 = Double.parseDouble(arr[3]);
            double w3 = Double.parseDouble(arr[4]);
            double v3 = Double.parseDouble(arr[5]);
            list.add(new ItemGroup(w1, v1, w2, v2, w3, v3));
        }
        br.close();
        return list;
    }

    /**
     * 保存结果到txt
     */
    public static void saveResult(String path, String content) throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter(path));
        bw.write(content);
        bw.close();
    }
}