As based on Arthur Riel's design heuristics there are a few OO design issues that violate said heuristics.

1. Public Field Exposure & Encapsulation Violations (Heuristic H2.1):
   - Issue: Fields such as "studentID", "studentName", "email", "courseCode", and "creditHours are declared "public". 
   - Explanation: According to Riel's heuristic H2.1 (*All data should be hidden within its class*), exposing internal 	
   data attributes directly strips the object of proper encapsulation. This leaves internal state completely unprotected, 
   making it impossible to enforce data invariants or validate modifications when accessed or altered by external 
   callers.

2. Violation of the Single Responsibility Principle (SRP) & Mixed Abstractions (Heuristic H2.8):
   - Issue: The class is burdened with multiple distinct responsibilities: business logic (tuition calculation and 
   discounts), file I/O operations ("FileWriter"), external communication (simulated email notifications), and system 
   logging/timestamps.
   - Explanation: Riel’s heuristic H2.8 states that *a class should capture one and only one key abstraction*. 
   Furthermore, heuristics H2.3 and H2.6 dictate minimizing operations in the interface and avoiding cluttering the 
   public interface with things callers shouldn't manage. Mixing student records, tuition rules, and disk writing 
   together violates these principles.

3. High Coupling, Low Cohesion, and Rigid Infrastructure Binding (Heuristic H3.3):
   - Issue: Because data handling, formatting, persistence, and output are tightly bound into a single monolithic method 
   ("registerStudent()"), any change to storage mechanisms or notification channels requires modifying core execution 
   code.
   - Explanation: This reflects a failure in responsibility distribution akin to Riel's H3.3 warnings ("What am I doing 
   with the information, and why doesn't the object do it for itself?"). Instead of delegating tasks to dedicated 
   components, the class manually reaches out to hardcoded file writers and print streams, resulting in tight coupling 
   and brittle code.

4. God Class Anti-Pattern & Poor Distribution of System Intelligence (Heuristics H3.1 & H3.2):
   - Issue: The class acts as an all-knowing, centralized controller that holds all execution logic while other classes 
   (or data structures) remain passive containers.
   - Explanation: Riel's heuristics H3.1 (*Distribute system intelligence horizontally as uniformly as possible*) and 
   H3.2 (*Do not create god classes/objects*) warn against this exact pattern. If developers avoid touching this class 
   because it concentrates too much control in one place, it is a clear symptom of poor responsibility distribution 
   across the system architecture.