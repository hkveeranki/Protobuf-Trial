import assignment.ResultProto.CourseMarks;
import assignment.ResultProto.Student;
import assignment.ResultProto.Result;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by harry7 on 20/8/16.
 */
public class DeserializeProtobuf {

    static void Deserialize(Result result) {
        try {
            PrintWriter writer = new PrintWriter("output_protobuf.txt", "UTF-8");
            for (Student student : result.getStudentList()) {
                String to_write="";
                to_write+=(student.getName()+","+student.getRollNum());
                for(CourseMarks mark:student.getMarksList()){
                    to_write+=":";
                    to_write+=(mark.getName()+","+mark.getScore());
                }
                writer.println(to_write);
                System.out.println("Writing "+to_write);
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static public void main(String[] args) {
        try {
            Result result = Result.parseFrom(new FileInputStream(args[0]));
            Deserialize(result);
        } catch (FileNotFoundException e) {
            System.err.println("File Not Found");
        } catch (IOException ignore) {
        }
    }

}
