Based on the CRC responsibilities, "Library" maintains collection states, executes searches, and coordinates system 
rules. In order to perform said operations, "Library" delegates state transitions to individual "Book" instances. "Book" 
is an independent entity and its only responsibility is to store its own state and validate its own availability and thus 
a "Book" has no need to interact with the "Library" container that holds it. 

"Library" also enforces a three-book borrowing limit as the limit is a system-wide business rule that governs aggregate
lending capacity over the entire institution instead of an inherent limitation of a single book. Individual "Book"
objects manage their own local availability flag ("available"), enforcing a global ceiling needs a centralized
coordinator ("Library") to track cumulative borrowing counts safely and to reject requests should capacity exceed.