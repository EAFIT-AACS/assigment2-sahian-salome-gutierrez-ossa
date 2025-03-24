# Assignment 2 PDA

# Student Information
Full Name: Sahian Salome Gutierrez Ossa

# Class Number: SI2002-2 (7309)
Development Environment
Operating System:Cross-platform (compatible with Windows, Linux, macOS, and more via the JVM) or Cloud (Replit Online IDE)
Programming Language: Java
Development Platform: Visual Studio Code

# Execution Instructions

# Local Environment
Ensure Java is installed.
Use any Java-compatible IDE (VSCode, IntelliJ IDEA) or a text editor.
Create a folder for the project
Load the 3 files with the algorithms and main
Compile and run the program (In the terminal use the instruction java Main.java) 

# Online Environment
Open Replit at https://replit.com/ and create a new Java project.
Load the 3 files with the algorithms and main
Run the program and wait for the output.

# Tools Used
1. Programming Language:
Java. Version: Java 11 recommended.
Paradigm: Object-oriented programming.

2. Development Environment (IDE) / Editors:
Visual Studio Code (VSCode)
Extensions used: Extension Pack for Java

JDK (Java Development Kit):
Required to compile and run the .java files.

4.Terminal / Console:
Terminal integrated in Visual Studio Code.

Configured to support Unicode characters and ANSI colors, allowing to display tables and emojis correctly.
Alternative: View it in replit

5.ANSI Escape Codes:

Used for: Coloring text 
Draw tables with borders using Unicode characters.


# Documentation

The program I have developed simulates a formal language based on strings that have an equal number of 'a' symbols followed by the same number of 'b' symbols, and works with a stack automaton (PDA) to process them and generate derivation trees for those accepted strings. It is divided into three main algorithms, each with a specific function:

# Algorithm 1: Generation of valid and invalid strings
Its main function is to create examples of valid and invalid strings that will later be the input of the automaton (here their acceptance or rejection will be evaluated).

How does it work?

Method generateStrings()
This is the main method of the algorithm. Inside it:

Two lists are initialized:
stringsValid: to store valid strings.
stringsInvalid: to store invalid strings.

Generation of valid strings:
A loop is used that generates 5 valid strings.
A random number n between 0 and 7 is chosen.
A string is created that has exactly n letters 'a' followed by n letters 'b'.
The string is added to validStrings.

Generation of invalid strings:
Here also 5 strings are generated.
A sequence of 'a' and 'b' of length between 1 and 10 is randomly generated.
Before saving it, it is checked for validity using the method isValidString(String string string):
This method counts how many 'a's and 'b's there are.It checks that the 'a's go first, that there are the same number of 'a's and 'b's, and that a 'b' does not appear before an 'a'.
If the string does not meet these rules, it is stored in stringsInvalid.

Result return:
The method returns an array containing both lists (valid and invalid).

# Algorithm 2
It takes each string (valid and invalid) and checks if it belongs to the language, using a stack automaton.

How does it work?
Method processStrings(List<String> strings)
This method processes all strings (valid and invalid) using an automaton.

For each string:
An empty stack is initialized.
Each character in the string is traversed:
If it is an 'a', it is stacked (push).
If it is a 'b':
If the stack is currently empty, it means that there is no matching 'a', then the string is rejected.
Otherwise, a symbol is unstacked (pop).

On termination:
If the stack is empty and there were no errors, the string is accepted.
If there is anything left on the stack, the string is rejected.

Results:
The accepted and rejected strings are printed.
A list of accepted strings is returned to be used by the next algorithm (tree construction).

# Algorithm 3
Before explaining the algorithm, it is worth mentioning that we have to make the productions and transitions of the Stack Automaton (PDA) in this form, we have the following structure:
1. S → aX   (q,a,S)→(q,X)
2. X → aXB  (q,a,X)→(q,XB)   
3. X → b    (q,b,X)→(q,ε)
4. B → b    (q,b,B)→(q,ε)
5. S-> ε    (q,ε,ε)→(q,ε)    (Empty string: it is allowed because S is the inicial symbol)

S is the initial symbol.
X and B are non-terminal symbols.
a and b are terminal symbols (the only ones that can appear in the input string).
ε represents the empty string.

Initialization of the algorithm

-A Stack is created that initially contains only the symbol S.
-A list (rowsTable) is used to record each step of the process.
-The input string is displayed before starting.


Bypass processing using the stack

-Processing occurs inside a while loop, which is executed as long as the stack is not empty. At each iteration:
-The symbol at the top of the stack is popped (Stack.pop()).
-Check which production to apply based on the extracted symbol and the first character of the input string.
-The stack and the input string are updated according to the applied rule.
-The step is recorded in the table.
   
Key Definitions

Sentential Form: Represents the evolution of the string according to the grammar's production rules.

Automaton Configuration (M): Represents the state of the automaton at any given time, in the format:

(𝑞, remaining input,stack)

Where:
q → Current state
remaining input → The symbols still to be processed in the string.
stack → The symbols on the stack.

# Rule 1:
S→aX
If the stack has S at the top and the first symbol in the input string is a, we apply this rule.

Sentential Form: S is replaced by aX, which means that the string being built now starts with a and the rest will be derived from X.

Automaton Configuration:
Before:
(𝑞,𝑎α,𝑆) S is at the top and the input is a followed by more symbols (𝛼).
After:
(𝑞,α,𝑋) We consume a and S is replaced by X on the stack.

# Rule 2:
X → aXB
If X is at the top and the input string starts with a, we apply this rule.

Sentential Form:
X is replaced by aXB, indicating that the string's structure will require a b in the future to balance the a we just consumed.

Automaton Configuration:
Before: (q, aα, X) X is on the stack and a is the next symbol in the input.
After: (q, α, XB) a is consumed from the input and XB is pushed onto the stack.

# Rule 3:
X → b
If X is at the top of the stack and the input string has b, we apply this rule.

Sentential Form:

X is replaced by b, which means we have reached the transition point where the as have ended and the bs begin.

Automaton Configuration:
Before: (q, bα, X)  X is on the stack and the first symbol in the input string is b.
After: (q, α, ε) We consume b from the input and remove X from the stack.

# Rule 4:
B → b
When B is at the top of the stack and the input string has b, we apply this rule.

Sentential Form:
B is replaced by b, ensuring that each B on the stack is removed when we encounter its corresponding b in the input.

Automaton Configuration:
Before: (q, bα, B)
B is on the stack and b is the first symbol in the input.
After: (q, α, ε)
We consume b from the input and remove B from the stack.

# Rule 5
If the stack and input string are simultaneously empty, this rule is applied to mark the string as valid.

Sentential Form:
The string is considered ε (empty), indicating a complete derivation.

Automaton Configuration:
Before: (q, ε, ε)
Both the input and the stack are empty.
After: (q, ε, ε)
The string is accepted.



Finally, if the string is accepted, a confirmation message is displayed. Otherwise, a rejection message is shown.

The function imprimirTabla(filasTabla) is called, generating a table with three columns:

-Applied Rule

-Sentential Form (how the string evolves)

-Automaton Configuration (q, input, stack)

# Execution example

![image](https://github.com/user-attachments/assets/0b5895b1-6ecc-4379-9b68-22562e8a47e6)
![image](https://github.com/user-attachments/assets/0b65c3b8-a0c6-48e5-b8be-fcb7b3ae7329)
![image](https://github.com/user-attachments/assets/2eda9d48-71b0-4fd4-acd2-821dd54d46cc)



