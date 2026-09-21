1.	How was your Assignment #2 solution organized?
Assignment #2 was organized by having the ETL workflow written inside a single "main" method in the file "ETLPipleine.java". Responsibilities such as reading the CSV file, arithmetic calculations, and parsing rows were placed in a single loop.

2.	What design changes did you make for Assignment #3?
For Assignment #3, responsibilities were assigned to different classes, rather than one method completing everything. To follow OOP architecture, such as the Single-Responsibility Principle, the original "main" method from Assignment #2 was broken up.

3.	What classes or abstractions did you introduce and why?
Four classes were created: "Employee", "CsvReader", "CsvWriter", and "ETLPipeline."
"Employee" represents a single employee and holds data for the employee and performs the payroll calculations.
"CsvReader" reads the input CSV file and handles the responsibilities associated with it such as line tokenization, row validation, and skipping rules.
"CsvWriter" handles the writing responsibilities such as generating the header and "Employee" data into the output file.
"ETLPipeline" is the coordinator that connects the reader, handles processing the data, calls the writer, and handles printing the final summary counts.

4.	How did you divide responsibilities differently?
Responsibilities related to business logic and calculating data was moved to "Employee", thus it manages the pay rules and formatting. Responsibilities connected to the file operations such as reading and writing files were put in "CsvReader" and "CsvWriter", respectively, removing the low-level operations from "ETLPipeline".

5.	Why do you believe your Assignment #3 design is an improvement?
The design for Assignment #3 is an improvement as it's easier to read and less crowded now that all the responsibilities are not in "main". It provides better maintenance as specific business rules can be tested directly, such as via "Employee", without interfering with the reading or writing. Changes to the CSV structure only impacts the input/output later and not the business calculations, and "CsvReader" and "CsvWriter" have the potential to be reused for other operations.

AI and External Resources
Google Gemini was used for assistance with the refactoring and discussing the design.
Transcript Link: https://share.gemini.google/pQNsoJz1bz1m

External References were standard Java SE 8 Documentation for "java.io" and "java.math.BigDecimal".

