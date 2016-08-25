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
    static long time_taken = 0;
    static long data_converted = 0;

    static void Deserialize(Result result) {
        try {
            PrintWriter writer = new PrintWriter("output_protobuf.txt");
            for (Student student : result.getStudentList()) {
                long startTime = System.currentTimeMillis();
                String to_write = "";
                to_write += (student.getName() + "," + student.getRollNum());
                for (CourseMarks mark : student.getMarksList()) {
                    to_write += ":";
                    to_write += (mark.getName() + "," + mark.getScore());
                }
                time_taken += System.currentTimeMillis() - startTime;
                writer.println(to_write);
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static public void main(String[] args) {
        try {
            FileInputStream stream = new FileInputStream(args[0]);
            long startTime = System.currentTimeMillis();
            Result result = Result.parseFrom(stream);
            time_taken += System.currentTimeMillis() - startTime;
            Deserialize(result);
            data_converted += result.getSerializedSize();
        } catch (IOException ignore) {
            ignore.printStackTrace();
        }
        if (args.length == 2 && args[1].equals("STAT")) {
            System.out.println("Time taken for Deserialization: " + time_taken + " ms");
            System.out.println("Rate of Deserilization: " + ((double)data_converted) / (time_taken) + " KBps");
        }
    }

}
