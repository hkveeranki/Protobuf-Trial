/**
 * Created by harry7 on 20/8/16.
 */

import assignment.ResultProto.CourseMarks;
import assignment.ResultProto.Student;
import assignment.ResultProto.Result;

import java.io.*;

public class GenerateProtobuf {

    static Student addStudent(String data) {
        PrintStream op = System.out;
        Student.Builder student = Student.newBuilder();
        String[] input_sets = data.split(":");
        String[] info = input_sets[0].split(",");
        student.setName(info[0]);
        student.setRollNum(Integer.valueOf(info[1]));
        for (int i = 1; i < input_sets.length; i++) {
            CourseMarks.Builder marks = CourseMarks.newBuilder();
            String[] marks_data = input_sets[i].split(",");
            marks.setName(marks_data[0]);
            marks.setScore(Integer.valueOf(marks_data[1]));
            student.addMarks(marks.build());
        }
        return student.build();
    }

    static public void main(String[] args) {
        PrintStream op = System.out;
        Result.Builder result = Result.newBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String line;
            while ((line = br.readLine()) != null) {
                result.addStudent(addStudent(line));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FileOutputStream output = new FileOutputStream("result_protobuf");
            result.build().writeTo(output);
            output.close();
        } catch (Exception e) {
            // Ignore
        }
    }
}