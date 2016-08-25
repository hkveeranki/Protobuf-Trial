/**
 * Created by harry7 on 20/8/16.
 */

import assignment.ResultProto.CourseMarks;
import assignment.ResultProto.Student;
import assignment.ResultProto.Result;

import java.io.*;

public class GenerateProtobuf {
    static long time_taken = 0;
    static long data_converted = 0;

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
        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            String line;
            while ((line = br.readLine()) != null) {
                long startTime = System.currentTimeMillis();
                result.addStudent(addStudent(line));
                data_converted += line.length();
                time_taken += System.currentTimeMillis() - startTime;
            }
            long startTime = System.currentTimeMillis();
            Result final_result = result.build();
            time_taken += System.currentTimeMillis() - startTime;
            FileOutputStream output = new FileOutputStream("result_protobuf");
            final_result.writeTo(output);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (args.length == 2 && args[1].equals("STAT")) {
            System.out.println("Time taken for Serialization: " + time_taken + " ms");
            System.out.println("Rate of Serilization: " + ((double)data_converted) / ((double)time_taken) + " KBps");
        }
    }
}