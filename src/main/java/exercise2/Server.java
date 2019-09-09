package exercise2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	static Message message;
	public final static int SERVER_PORT = 9669;
	static BufferedReader reader;
	protected Socket socket;

	private Server(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		InputStream in = null;
		OutputStream out = null;
	}

	public static void readFile() throws IOException {
		reader = new BufferedReader(
				new FileReader("C:\\Users\\FPT\\.eclipse\\JavaIO_Socket\\src\\main\\java\\exercise2\\request.txt"));
		String line = reader.readLine();
		while (line != null) {
			Message message = new Message();
			String[] input = line.split(" ");
			int cmdCode = Message.cmdCodeString(input[0]);
			System.out.println(input[0]);
			//System.out.println(cmdCode);
			
			message.setCmdCode(cmdCode);
//			System.out.println(message.getCmdCode());
			for(int i=1;i<input.length;i+=2) {
				System.out.println(input[i]);
				System.out.println(input[i].getBytes());
//				int tag = Message.LTV.tagString(input[i]);
			}
			line = reader.readLine();
		}
	}

	public static void main(String[] agrv) throws IOException {
		readFile();
	}
}
