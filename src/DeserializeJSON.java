
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ListIterator;

/**
 * Created by harry7 on 24/8/16.
 */
public class DeserializeJSON {

    static void Deserialize(Object obj){
        try {
            PrintWriter writer = new PrintWriter("output_json.txt");
            JSONArray arr = (JSONArray) obj;
            ListIterator itr = arr.listIterator();
            while (itr.hasNext()) {
                String to_write = "";
                JSONObject student = (JSONObject) itr.next();
                to_write += student.get("Name") + "," + student.get("RollNo");
                ListIterator marks_list = ((JSONArray) student.get("CourseMarks")).listIterator();
                while (marks_list.hasNext()) {
                    JSONObject marks = (JSONObject) marks_list.next();
                    to_write += ":";
                    to_write += marks.get("CourseName")+","+marks.get("CourseScore");
                }
                writer.println(to_write);
            }
            writer.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    static public void main(String[] args) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(args[0]));
            Deserialize(obj);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
