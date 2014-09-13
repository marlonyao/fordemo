package hello;

public class HelloHandler implements HelloService.Iface {
	public void ping() {
		System.out.println("ping");
	}

	public String hello(String name) {
		return "Hello, " + name + "!";
	}

    public String helloV2(Person person) {
        return "Hello, " + person.getLastName() + " " + person.getFirstName() + "!";
    }
}
