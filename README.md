## BE Code Challenge

## Notes

This application was done using SpringBoot to speed up implementation but running SpringBoot as a command line
tool generated some unexpected issue and added some more complexity to what should be a simple application.
Nonetheless, it makes for a more robust and flexible solution

TaskRunnable is the component that grabs input, executes validation and the business logic and then shows results.
This is an abstraction that allows for the code to be reused with other types of interfaces (ex. Rest).
However, during tests ApplicationContext is initialized and runs the command line creating an error (input is empty).
Therefore, it was necessary to force build tasks and tests to use "test" profile (set in gradle) and use a dummy bean
(TaskRunnableDummy).

Business logic goes to Clinic Application where input (Patients + Medicin) is ClinicInput and output is ClinicStatus.
Validation and Transform beans handle the input and were created to avoid filling TaskRunnable with business logic. 
Decided not to create interfaces in order to keep things simpler.

Flying Spaghetti logic random logic was implemented using a PostProcessor.
To avoid random behaviour in tests this condition is set to false with test profile and tested in isolation
in PostProcessorTest. I'm aware this could have been done using a simple flag in application.properties.

There was an example with input "F P" without comma, so that syntax is also supported.

##

Armando Marques
ajcmarques@icloud.com