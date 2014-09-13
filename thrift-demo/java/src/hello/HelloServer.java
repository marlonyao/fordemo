package hello;

import org.apache.thrift.TException;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;

public class HelloServer {
	public static void start() throws TException {
		TServerSocket serverTransport = new TServerSocket(9090);
		HelloService.Processor processor = new HelloService.Processor(new HelloHandler());
		// 默认使用TBinaryProtocol
		TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));
		System.out.println("Starting server...");
		server.serve();
	}

	public static void main(String[] args) throws Exception {
		start();
	}
}
