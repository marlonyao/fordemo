namespace java hello

service HelloService {
	void ping();
	string hello(1:string name);
}
