/**
 * Created by harry7 on 20/8/16.
 */
import assignment.ResultProto.Student;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class GenerateData {
    static ResultProto.Student addStudent(String data){
        ResultProto.Student.Builder student = ResultProto.Student.newBuilder();
        student.setName("Hemanth");
        student.setRollNum(201401145);
        return student.build();
    }
    static public void main(String[] args){
        ResultProto.Result.Builder result = ResultProto.Result.newBuilder();
        try{
            FileInputStream input = new FileInputStream("result_protobuf");
            result.mergeFrom(input);
            input.close();
        }
        catch (){}
        result.addStudent(addStudent("Hello"));
        try {
            FileOutputStream output = new FileOutputStream("result_protobuf");
            result.build().writeTo(output);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("quitting bye");
    }
}
