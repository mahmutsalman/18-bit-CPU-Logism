This is a project about designing a 18 bit CPU using logisim. It can do arithmetic operations, store, load, jump and branch instructions.

How to play around with the CPU?
1. Firstly, you need a set of instructions. Then the hexadecimal output of the instructions. This conversion can be made 
by de java code "main.java". You can generate instructions by looking our instruction set architecture file. Remember that 
the ISA can be unique for each design. Further instructions about it are in the report file.
2. Then you will need to open our cpu file "CPU.circ" in logisim.
3. Insert your assembled instructions (in hexadecimal format) in the Instruction Memory (ROM).
4. You can set the system clock 0 and 1. On each clock edge, instructions will be processed by the control unit.

Here is an example instruction set:

ADDI R10,R10,3
ADDI R14,R14,1
ADDI R15,R13,0
ADDI R13,R14,0
ADD R14,R13,R15
BEQ R9,R10,8
ADDI R9,R9,1
JUMP -5
ST R15,0
LD R1,0

the hexadecimal output of it:

06a83 
07b81 
07f40 
07780 
03b7c 
2a688 
06641 
3c3fb 
37c00 
20400

This instructure set basically calculates the 3rd element of fibonacci series, stores it in the memory address 0 in RAM, then loads it from the RAM to the 1st register.