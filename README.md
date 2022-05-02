# Read Me First

Assumptions:
1. Since the CoverArt URL has been permanently moved, I have not added any data manipulation logic.
2. Currently have assumed there will be no client API failure
3. No rate limit on API from client side

Improvement:

0. Adding Global exception handler to properly handle failure
1. Caching the client response, using Caffeine cache
2. Adding circuit breaker, to avoid calls to failing service, right now the calls are being made for Cover Art and 
getting response 301
3. Currently have not overridden config for the thread pool to support Asyn call, but we should do it to support high
 load and avoid thread pool starvation. 
4. Authentication using spring security
5. Rate limiting can be added as well 


