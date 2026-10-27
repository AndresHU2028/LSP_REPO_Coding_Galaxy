An improved OO design for this system would be as follows,

Class: Student
- Responsibilities: Store student identification, name, and contact details.
- Collaborators: None

Class: Course
- Responsibilities: Hold course code, title, and credit hour details.
- **Collaborators: None

Class: TuitionCalculator
- Responsibilities: Calculate baseline tuition and apply credit-load discount rules.
- Collaborators: Course

Class: RegistrationRepository
- Responsibilities: Handle data persistence (saving records to local files or databases).
- Collaborators: None

Class: NotificationService
- Responsibilities: Send out confirmation notices via email or SMS text message.
- Collaborators: Student

Class: CourseRegistrationManager
- Responsibilities: Coordinate registration workflow across domain objects, calculator, persistence, and notifications.
- Collaborators: Student, Course, TuitionCalculator, RegistrationRepository, NotificationService
