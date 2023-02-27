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

* Replace comments with expressive code

* Uniform formatting across the team

## Code smell and proper way to write the code 

**Escape the Primitive Obsession by creating Micro-Types**
![PrimitiveObsession](https://user-images.githubusercontent.com/84851340/221503250-b6eb6f31-f799-4eed-a234-080590add1aa.png)
________________________________________________________________
**Separate Data from the logic**

>* Put more logic inside the class which leads to OOP
>* Don't put logic inside the DTO's 
>* When the complexity is get higher use the OOP
![DataClasses](https://user-images.githubusercontent.com/84851340/221506430-b8e428a9-c95e-4c20-9f37-dd965ed58859.png)

_______________________________________________________________

**Why identify more classes ?**

> **Simplify the code.**               
>>* Fewer method parameter              
>>* Feature Envy -> Spread the logic(oop)           
>>* Safer -> by using constraints           
>>* Shrink large entities -> decomposition into the smaller parts    
_________________________________________________________________

**Loop & Excessive chaining**
> Loop should also follow SRP.
> Many Dev belive that loop are the expensive operation so they put heavy ops inside single loop so no need for multiple iteration.
> Solution: Split the loop (But what about the performance ?
![loop](https://user-images.githubusercontent.com/84851340/221512124-a2b1fd2f-f9e8-411c-a655-777b791ad6a9.png)

> Method ideally should be few lines(5-10).
> If we are doing the excessive chaining we should split the code into the various parts and extract variables.
![ExcessiveChaining](https://user-images.githubusercontent.com/84851340/221512151-8eee1dd2-ea3d-40d3-827c-0849c1b97078.png)

________________________________________________________________________

**Temporal Coupling**
> Avoid recycling of the variable.
> Recycle plastic not the varibles :)
> ![TemporalCoupling](https://user-images.githubusercontent.com/84851340/221513852-60802b7f-f02e-4344-9353-1bf672867ae5.png)

