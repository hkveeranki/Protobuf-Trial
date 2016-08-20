all: src/GenerateData.java assignment/ResultProto.java
	javac -d bin/ -cp ".:lib/protobuf.jar" src/GenerateData.java assignment/ResultProto.java
ResultProto.java:Result.proto
	protoc --java_out=. Result.proto
clean:
	rm bin/*.class
	rm -r assignment
