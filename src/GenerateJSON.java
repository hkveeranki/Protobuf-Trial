/**
 * Created by harry7 on 20/8/16.
 */

import org.json.simple.JSONArray;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.StringJoiner;

public class GenerateJSON {
    static String[] input_sets;
    static String[] info;
    static String[] marks_data;
    static JSONArray arr;
    static long time_taken;
    static long data_converted;

    static LinkedHashMap<String, Object> generateObject(String data) {
        input_sets = data.split(":");
        info = input_sets[0].split(",");
        LinkedHashMap<String, Object> map1 = new LinkedHashMap<>();
        map1.put("Name", info[0]);
        arr = new JSONArray();
        for (int i = 1; i < input_sets.length; i++) {
            marks_data = input_sets[i].split(",");
            LinkedHashMap<String, Object> map2 = new LinkedHashMap<>();
            map2.put("CourseScore", Long.valueOf(marks_data[1]));
            map2.put("CourseName", marks_data[0]);
            arr.add(map2);
        }
        map1.put("CourseMarks", arr);
        map1.put("RollNo", Long.valueOf(info[1]));
        return map1;
    }
    
    static public void main(String[] args) {
        time_taken = 0;
        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            PrintWriter writer = new PrintWriter("sampleresult.json");
            JSONArray arr = new JSONArray();
            while ((line = br.readLine()) != null) {
                long startTime = System.currentTimeMillis();
                LinkedHashMap<String, Object> obj = generateObject(line);
                data_converted += line.length();
                arr.add(obj);
                time_taken += (System.currentTimeMillis() - startTime);
            }
            long startTime = System.currentTimeMillis();
            time_taken += (System.currentTimeMillis() - startTime);
            writer.print(arr.toJSONString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (args.length == 2 && args[1].equals("STAT")) {
            System.out.println("Time taken for Serialization: " + time_taken + " ms");
            System.out.println("Rate of Serilization: " + ((double)data_converted) / (time_taken) + " KBps");
        }
    }
}