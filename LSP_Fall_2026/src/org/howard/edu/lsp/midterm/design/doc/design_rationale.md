To support alternative storage options (local file vs. database) and multiple notification channels (email vs. text 
message) without modifying core business logic, we introduce interfaces and polymorphism into the original system. 
There'd be Persistence Abstraction as we define an interface "RegistrationRepository" implemented by 
"FileRegistrationRepository" and "DatabaseRegistrationRepository". The manager class depends solely on the interface, 
allowing seamless switching based on configuration. Theres also Notification Abstraction as similarly, we define a 
"NotificationService" interface implemented by "EmailNotificationService" and "SmsNotificationService". For 
Responsibility Ownership the "CourseRegistrationManager" owns the orchestration responsibility because coordinating 
workflows across independent domain components should be decoupled from the data structures (Student/Course) and low-
level I/O operations.