import jdk.jfr.consumer.RecordedStackTrace;

@RequestParam
// Example with a required request parameter
@GetMapping("/greet")
public String greetUser(@RequestParam String name) {
    return "Hello, " + name + "!";
}

/*
how it will work
GET Request with Required Parameter:
Method: GET /api/greet
Parameter: name (required)
url will be like: GET http://localhost:8080/api/greet?name=John
Response: Hello, John!
 */

//@PathVariable :
@GetMapping("/users/{id}")
public String getUser(@PathVariable Integer id) {}

//@RequestBody : It converts the JSON or XML data in the request body into a Java object.

//REST:
//RESTful APIs use standard HTTP methods (GET, POST, PUT, DELETE) and status codes.
//Statelessness allows RESTful services to scale horizontally.stateless refers to the principle where each request from a client to a server must contain all the information needed to understand and process the request. The server does not retain any information or state about previous requests.
//HTTPS (Hypertext Transfer Protocol Secure)
//HTTPS encrypts the data transmitted between the client and server, which protects sensitive information such as login credentials, personal details, and payment information from being intercepted or read by third parties.

//PATCH
//The PATCH method allows clients to send only the data that needs to be updated, rather than sending the entire resource. This is efficient for making incremental changes.

