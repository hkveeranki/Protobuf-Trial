	case "$1" in
	'-c')
		make
		;;
	'-s')
		case "$2" in
			'-j')
				echo "Serialize json"
				;;
			'-p')
				java -cp ".:lib/protobuf.jar:bin" GenerateProtobuf $3
				;;
		esac
		;;
	'-d')
		case "$2" in
			'-j')
				echo "Deserialize json"
				;;
			'-p')
				java -cp ".:lib/protobuf.jar:bin" DeserializeProtobuf $3
				;;
		esac
		;;
	'-t')
		case "$2" in
			'-j')
				echo "Deserialize json"
				;;
			'-p')
				java -cp ".:lib/protobuf.jar:bin" GenerateProtobuf $3
				;;
		esac
		;;
esac
