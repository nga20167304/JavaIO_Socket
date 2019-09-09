package exercise2;

import java.util.List;

public class Message {
	int lengthOfMessage;
	static int cmdCode;
	int version = 0;
	public int getCmdCode() {
		return cmdCode;
	}

	public void setCmdCode(int cmdCode) {
		Message.cmdCode = cmdCode;
	}

	
	List<TLV> listTLV;

	public class TLV {
		int tag;
		int length;
		String value;

		public TLV(int tag, int length, String value) {
			super();
			this.tag = tag;
			this.length = length;
			this.value = value;
		}

	}
		public int tagString(String tagString) {
			int tag;
			switch(tagString) {
			case "Key":
				tag=0;
				break;
			case "PhoneNumber":
				tag = 1;
				break;
			case "Name":
				tag=2;
				break;
			case "ResultCode":
				tag=3;
				break;
			default:
				tag=-1;
				break;
			}
			return tag;
		}


	public static int cmdCodeString(String cmdCodeString) {
		switch(cmdCodeString) {
		case "AUTHEN":
			cmdCode=0;
			break;
		case "INSERT":
			cmdCode=1;
			break;
		case "COMMIT":
			cmdCode=2;
			break;
		case "SELECT":
			cmdCode=3;
			break;
		default:
			cmdCode=-1;
			break;
		}
		return cmdCode;
	}
}
