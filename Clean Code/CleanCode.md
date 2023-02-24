# Clean code notes on Victor Rentea

> Detail Video is available on the youtube
> https://www.youtube.com/watch?v=6va1hAyh-M8

## Change happen in developement

* Refactoring and Unit testing is the part of the task.     

* CI pipeline is running eg. Sonar + IDE plugins(SonarLint)       
> Sonar will help you in clean code rather than wasting time in the code review on every step           
> Also Don't push garbage code on the git           
    
* Code review and Pair Programming             
> Pair programming will sky rocket the learning
* Languages and Framework evolved         

## Obvious clean code Ideas 

* Don't copy-paste code(DRY Principle)
> DRY Principle - "Don't repeat yourself"
>> "Don't repeat yourself" is a principle of software development aimed at reducing repetition of software patterns, replacing it with abstractions or using data normalization to avoid redundancy."       

* Method size < 1 screen      

* Boolean in signature can violate SRP               
> SRP : "Single Responsibility Principle"           
>> every class, module, or function in a program should have one responsibility/purpose in a program. As a commonly used definition, "every class should have only one reason to change". The class above violates the single responsibility principle.             
    
* Uniform formatting across the team

