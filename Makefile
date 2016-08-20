all: GenerateData.java ResultProto.java
	javac -cp ".:protobuf.jar" GenerateData.java ResultProto.java
ResultProto.java:Result.proto
	protoc --java_out=. Result.proto
