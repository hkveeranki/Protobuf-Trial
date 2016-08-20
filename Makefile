all: src/GenerateProtobuf.java assignment/ResultProto.java
	javac -d bin/ -cp ".:lib/protobuf.jar" src/GenerateProtobuf.java assignment/ResultProto.java
assignment/ResultProto.java:Result.proto
	protoc --java_out=assignment Result.proto
clean:
	rm bin/*.class
	rm -r assignment
