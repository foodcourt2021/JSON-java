* Milestone3 methods and tests are in the commit "b846c3de54bc17efdc71de2c87b1eb3df51d08bd"

* In a README file, comment on the performance implications of doing this inside the library vs. doing it in client code, as you did in Milestone 1.

* Doing this inside the library can improve the performance. Because when doing it in client code,
  I have to do it after the entire JSON object has been built, then I need to traverse the entire
  JSON object. While inside the library, I can do it on the fly.
