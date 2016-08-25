/**
 * Created by harry7 on 24/8/16.
 */

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ListIterator;

public class DeserializeJSON {
    static long time_taken;
    static long data_converted;
    static void Deserialize(Object obj) {
        try {
            PrintWriter writer = new PrintWriter("output_json.txt");
            long startTime = System.currentTimeMillis();
            JSONArray arr = (JSONArray) obj;
            data_converted = arr.toString().length();
            time_taken += (System.currentTimeMillis() - startTime);
            for (ListIterator itr = arr.listIterator(); itr.hasNext();) {
                startTime = System.currentTimeMillis();
                String to_write = "";
                JSONObject student = (JSONObject) itr.next();
                to_write += student.get("Name") + "," + student.get("RollNo");
                for (ListIterator marks_list = ((JSONArray) student.get("CourseMarks")).listIterator(); marks_list.hasNext(); ) {
                    JSONObject marks = (JSONObject) marks_list.next();
                    to_write += ":";
                    to_write += marks.get("CourseName") + "," + marks.get("CourseScore");
                }
                time_taken += (System.currentTimeMillis() - startTime);
                writer.println(to_write);
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static public void main(String[] args) {
        time_taken = 0;
        data_converted = 0;
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(args[0]));
            Deserialize(obj);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(args.length == 2 && args[1].equals("STAT")){
            System.out.println("Time taken for Deserialization: " + time_taken + " ms");
            System.out.println("Rate of Deserilization: " + ((double)data_converted) / (time_taken) + " KBps");
        }
    }
}