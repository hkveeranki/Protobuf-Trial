/**
 * Created by harry7 on 20/8/16.
 */

import org.json.simple.JSONArray;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.LinkedHashMap;

public class GenerateJSON {
    static String[] input_sets;
    static String[] info;
    static String[] marks_data;
    static JSONArray arr;
    static LinkedHashMap generateObject(String data) {
        input_sets = data.split(":");
        info = input_sets[0].split(",");
        LinkedHashMap map1  = new LinkedHashMap();
        map1.put("Name",info[0]);
        arr = new JSONArray();
        for (int i = 1; i < input_sets.length; i++) {
            marks_data = input_sets[i].split(",");
            LinkedHashMap map2 = new LinkedHashMap();
            map2.put("CourseScore", Long.valueOf(marks_data[1]));
            map2.put("CourseName", marks_data[0]);
            arr.add(map2);
        }
        map1.put("CourseMarks", arr);
        map1.put("RollNo", Long.valueOf(info[1]));
        return map1;
    }

    static public void main(String[] args) {
        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            PrintWriter writer = new PrintWriter("sampleresult.json");
            JSONArray arr = new JSONArray();
            while ((line = br.readLine()) != null) {
                arr.add(generateObject(line));
            }
            writer.print(arr.toJSONString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}