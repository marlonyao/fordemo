namespace java hello

struct Person {
    2:string lastName;
    1:string firstName;
}

service HelloService {
    void ping();
    string hello(1:string name);
    string helloV2(1:Person person);
}
