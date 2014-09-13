package hello;

import org.apache.thrift.TException;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TBinaryProtocol;

public class HelloClient {

	public static void main(String[] args) throws TException {
		TSocket transport = new TSocket("localhost", 9090);
		TProtocol protocol = new TBinaryProtocol(transport);
		HelloService.Client client = new HelloService.Client(protocol);
		transport.open();
		String result = client.hello("World");
		System.out.println(result);

		System.out.println(client.helloV2(new Person()));
	}
}
