# Assignment: Expand the "Hello World" REST API

## Objective:
> - Build upon the existing "Hello World" REST API implemented in the tutorial. - Extend the API to include additional endpoints and functionality.

## Tasks:
> 1. Create a new endpoint `/greet/{name}` that accepts a path parameter representing a person's name and returns a personalized greeting. For example, a request to `/greet/John` should return "Hello, John!".
> 2. Implement error handling for invalid or empty name values. Return an appropriate error message and status code when the name parameter is missing or blank.
> 3. Add a new endpoint `/greet/{name}/{language}` that accepts both name and language parameters. Return a greeting message based on the specified language. For example, `/greet/John/es` should return "Hola, John!" for Spanish.
> 4. Implement support for handling HTTP POST requests. Create an endpoint `/greet` that accepts a JSON payload with a name field and returns a greeting message. Test this endpoint using tools like cURL or Postman.
> 5. Add unit tests to verify the behavior of the new endpoints. Write test cases to cover different scenarios, including valid and invalid inputs.

