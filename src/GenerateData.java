/**
 * Created by harry7 on 20/8/16.
 */

import assignment.ResultProto.Student;
import assignment.ResultProto.Result;

import java.io.*;

public class GenerateData {
    static Student addStudent(String data) {
        Student.Builder student = Student.newBuilder();
        student.setName("Hemanth");
        student.setRollNum(201401145);
        return student.build();
    }

    static public void main(String[] args) {
        PrintStream op = System.out;
        Result.Builder result = Result.newBuilder();
        try {
            FileInputStream input = new FileInputStream("result_protobuf");
            try{
                result.mergeFrom(input);
            }
            finally {
                try{
                    input.close();
                }
                catch (Throwable ignore){}
            }
        }
        catch (FileNotFoundException e){}
        result.addStudent(addStudent("Hello"));
        FileOutputStream output = new FileOutputStream("result_protobuf");
        try {
            result.build().writeTo(output);
        } finally {
            output.close();
        }
        System.out.println("quitting bye");
    }
}
