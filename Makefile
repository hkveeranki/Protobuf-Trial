all: SerializeProtobuf DeserializeProtobuf SerializeJSON DeserializeJSON

Protofile:Result.proto
	protoc --java_out=. Result.proto

SerializeProtobuf: src/GenerateProtobuf.java Protofile
	javac -d bin/ -cp ".:lib/protobuf.jar" src/GenerateProtobuf.java assignment/ResultProto.java

DeserializeProtobuf:src/DeserializeProtobuf.java Protofile
	javac -d bin/ -cp ".:lib/protobuf.jar" src/DeserializeProtobuf.java assignment/ResultProto.java

SerializeJSON: src/GenerateJSON.java
	javac -d bin/ -cp ".:lib/json-simple-1.1.1.jar" src/GenerateJSON.java

DeserializeJSON: src/DeserializeJSON.java
	javac -d bin/ -cp ".:lib/json-simple-1.1.1.jar" src/DeserializeJSON.java

clean:
	rm bin/*.class
	rm -r assignment
