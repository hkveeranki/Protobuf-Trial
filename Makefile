all: SerializeProtobuf DeserializeProtobuf
SerializeProtobuf: src/GenerateProtobuf.java Protofile
	javac -d bin/ -cp ".:lib/protobuf.jar" src/GenerateProtobuf.java assignment/ResultProto.java
DeserializeProtobuf:src/DeserializeProtobuf.java
	javac -d bin/ -cp ".:lib/protobuf.jar" src/DeserializeProtobuf.java assignment/ResultProto.java
Protofile:Result.proto
	protoc --java_out=. Result.proto
clean:
	rm bin/*.class
	rm -r assignment
