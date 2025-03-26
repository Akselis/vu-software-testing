# Greeting weary traveler!🥵
If you've come following the trail of our software testing presentation, you're in the right place.
## How to test YOUR headless performance
I used IntelliJ IDEA to write and run my tests, so I'll provide a guide for how to run it this way.
1. Tasks 3.2 and 5 are implemented in src/test/java/tests/JUnitTests.java
2. Both tests are run 20 times each, calculating the time for each test to be performed, calculating the total time and average afterwards
3. By default the tests run in headful mode. You can switch to headless in the 35th line by commenting `options.addArguments("--headless=new");`
4. Run tests regularly or with the profiler if u want to analyze CPU times ![idea64_TuwKX8hUAo](https://github.com/user-attachments/assets/dc1d821f-6460-450a-b4ed-52744e2cd984)
5. You will find output results: `input.txt` for task 3.2 and `email.txt` for task 5, profile 3, in the root directory.

