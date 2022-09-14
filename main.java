import java.io.*;
import java.util.Scanner;
//engin bektaş 150118501
//mahmut salman 150118506
public class main {

    public static void main(String[] args) throws FileNotFoundException {

        flush(); //delete output.hex if it exists
        File file = new File("input.txt");
        Scanner fileReader = new Scanner(file);
        String nextLine = "";

        while (fileReader.hasNextLine()){ //read input
            nextLine = fileReader.nextLine(); //line by line
            String[] tokens = nextLine.split("[, ]" ); //tokenize
            String converted = convertLine(tokens); //binary number
            int decimal = Integer.parseInt(converted,2); //binary to integer as base 10
            String hexString = Integer.toString(decimal,16); //base 10 integer to hexadecimal
            write(hexString); //write output
        }
    }

    public static String convertLine(String[] tokens){
        String binaryLine = "";
        switch (tokens[0]){
            case "ADD":
                binaryLine += "0000"; //from table
                binaryLine += convertRegister(tokens[1]); //register dest
                binaryLine +=  convertRegister(tokens[2]); //register src
                binaryLine += "0"; // immediate or not
                binaryLine += "0"; // empty
                binaryLine += convertRegister(tokens[3]); //register src2
                break;

            case "ADDI":
                binaryLine += "0000";
                binaryLine += convertRegister(tokens[1]);
                binaryLine +=  convertRegister(tokens[2]);
                binaryLine += "1";
                binaryLine += immediateToBinary(tokens[3] , 5);
                break;

            case "AND":
                binaryLine += "0001";
                binaryLine += convertRegister(tokens[1]);
                binaryLine +=  convertRegister(tokens[2]);
                binaryLine += "0";
                binaryLine += "0";
                binaryLine += convertRegister(tokens[3]);
                break;

            case "ANDI":
                binaryLine += "0001";
                binaryLine += convertRegister(tokens[1]);
                binaryLine +=  convertRegister(tokens[2]);
                binaryLine += "1";
                binaryLine += immediateToBinary(tokens[3], 5);
                break;

            case"OR":
                binaryLine += "0010";
                binaryLine += convertRegister(tokens[1]);
                binaryLine +=  convertRegister(tokens[2]);
                binaryLine += "0";
                binaryLine += "0";
                binaryLine += immediateToBinary(tokens[3], 5);
                break;

            case"ORI":
                binaryLine += "0010";
                binaryLine += convertRegister(tokens[1]);
                binaryLine +=  convertRegister(tokens[2]);
                binaryLine += "1";
                binaryLine += immediateToBinary(tokens[3], 5);
                break;

            case "LD":
                binaryLine += "0100";
                binaryLine += convertRegister(tokens[1]);
                binaryLine += immediateToBinary(tokens[2], 10);
                break;

            case "ST":
                binaryLine += "0101";
                binaryLine += convertRegister(tokens[1]);
                binaryLine += immediateToBinary(tokens[2], 10);
                break;

            case "JUMP":
                binaryLine += "0110";
                binaryLine += immediateToBinary(tokens[1], 14);
                break;

            case "BEQ":
                binaryLine += "1000";
                binaryLine += convertRegister(tokens[1]);
                binaryLine += convertRegister(tokens[2]);
                binaryLine += immediateToBinary(tokens[3], 6);
                break;

            case "BLT":
                binaryLine += "1001";
                binaryLine += convertRegister(tokens[1]);
                binaryLine += convertRegister(tokens[2]);
                binaryLine += immediateToBinary(tokens[3], 6);
                break;

            case "BGT":
                binaryLine += "1010";
                binaryLine += convertRegister(tokens[1]);
                binaryLine += convertRegister(tokens[2]);
                binaryLine += immediateToBinary(tokens[3], 6);
                break;

            case "BLE":
                binaryLine += "1011";
                binaryLine += convertRegister(tokens[1]);
                binaryLine += convertRegister(tokens[2]);
                binaryLine += immediateToBinary(tokens[3], 6);
                break;

            case "BGE":
                binaryLine += "1100";
                binaryLine += convertRegister(tokens[1]);
                binaryLine += convertRegister(tokens[2]);
                binaryLine += immediateToBinary(tokens[3], 6);
                break;

            default :
                System.out.println("Input has errors.");
                break;
        }
        return binaryLine;
    }

    public static String convertRegister(String s){
        String bin = "";
        switch(s){
            case "R0":
                bin = "0000";
                break;
            case "R1":
                bin = "0001";
                break;
            case "R2":
                bin = "0010";
                break;
            case "R3":
                bin = "0011";
                break;
            case "R4":
                bin = "0100";
                break;
            case "R5":
                bin = "0101";
                break;
            case "R6":
                bin = "0110";
                break;
            case "R7":
                bin = "0111";
                break;
            case "R8":
                bin = "1000";
                break;
            case "R9":
                bin = "1001";
                break;
            case "R10":
                bin = "1010";
                break;
            case "R11":
                bin = "1011";
                break;
            case "R12":
                bin = "1100";
                break;
            case "R13":
                bin = "1101";
                break;
            case "R14":
                bin = "1110";
                break;
            case "R15":
                bin = "1111";
                break;
        }
        return bin;
    }

    public static String immediateToBinary(String s, int length){

        String result = "";
        String bin = Integer.toBinaryString(Integer.parseInt(s));
        //pad 1 to the left then return
        if (Integer.parseInt(s) < 0) {
            result = bin.substring(bin.length() - length);
            return result;
        }
        //pad 0 to the left, save final string to result
        for (int i = 0; i < length - bin.length(); i++) {
            if (Integer.parseInt(s) > 0){
                result += "0";
            }
        }
        result += bin;
        return result;
    }

    public static void write(String message) {

        File f = new File("output.hex");//Path for log file
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
            bw.append(message + " " +  "\n");
            System.out.println(message + " ");
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void flush() {
        File f= new File("output.hex");
        f.delete();
    }
}
