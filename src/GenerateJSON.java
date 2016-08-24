import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * Created by harry7 on 20/8/16.
 */
public class GenerateJSON {
    static JSONObject generateObject(String data) {
        JSONObject obj = new JSONObject();

        String[] input_sets = data.split(":");
        String[] info = input_sets[0].split(",");
        obj.put("Name", info[0]);
        JSONArray arr = new JSONArray();
        for (int i = 1; i < input_sets.length; i++) {
            String[] marks_data = input_sets[i].split(",");
            JSONObject tmp = new JSONObject();
            tmp.put("CourseScore", marks_data[1]);
            tmp.put("CourseName", marks_data[0]);
            arr.add(tmp);
        }
        obj.put("CourseMarks", arr);
        return obj;
    }

    static public void main(String[] args) {
        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            PrintWriter writer = new PrintWriter("sampleresult.json");
            JSONArray arr = new JSONArray();
            while ((line = br.readLine()) != null) {
                JSONObject obj = generateObject(line);
                arr.add(obj);
            }
            System.out.print(arr.toJSONString());
            writer.print(arr.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
