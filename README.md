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

The algorithm starts by initializing a stack (Stack<Character) with the initial symbol S, representing the starting point of the derivation. In addition, it uses a List<String[]> rowsTable)list to record each step of the process, allowing to visualize how the derivation evolves from the initial sentential form to its possible acceptance. The input string is displayed before starting the table, handling the special case of an empty string by representing it with ε.

String processing occurs inside a while loop, which continues to execute as long as the stack is not empty. At each iteration, the symbol at the top of the stack is extracted (Stack.pop()) and its value is evaluated to determine which production rule to apply. Four derivation rules have been defined:

Rule 1: S → aX
If the symbol at the top of the stack is S and the first character of the string is a, S is replaced by aX, removing a from the string and stacking X. This step is recorded in the table.

Rule 2: X → aXB
If the top is X and the chain starts with a, X is replaced by aXB, removing a from the chain and stacking X and B.

Rule 3: X → b
If the top is X and the chain starts with b, X is replaced by b and moves forward in the chain without stacking new symbols.

Rule 4: B → b
If the top is B and the next character is b, B is replaced by b, and the string is advanced in the chain without stacking new symbols.

The process continues until the stack is empty or no further valid derivation can be performed. If at the end the stack and the string are empty, the input is considered to be accepted and the table is printed with the steps followed, using the method printTable List<String[]> rows.

Finally, if the string has been accepted, a confirmation message is displayed. Otherwise, a rejection message is displayed.
