# Read Me First

Improvement:

0. Adding Global exception handler to properly handle failure
1. Caching the client response, using Caffeine cache
2. Adding circuit breaker, to avoid calls to failing service, right now the calls are being made for Cover Art and 
getting response 301
3. Currently have not overridden config for the thread pool to support Asyn call, but we should do it to support high
 load and avoid thread pool starvation. 
4. Authentication using spring security
5. Rate limiting can be added as well 


