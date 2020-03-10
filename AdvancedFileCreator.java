import java.util.*;
import java.io.*;
import java.awt.*;

public class AdvancedFileCreator {
   public static void main(String[] args) throws FileNotFoundException, InterruptedException, IOException {
      ArrayList<String> lines = new ArrayList<String>();
      Scanner console = new Scanner(System.in);
      classHeader(lines, console);
      System.out.println("s to stop at any point in program other than 'enter parameter'");
      imports(console, lines);
      String name = className(console, lines);
      mainMethod(console, lines);
      methods(console, lines);
      lines.add("}");
      File file = writeToFile(lines, name);
      openFile(file);
   }

   public static void classHeader(ArrayList<String> lines, Scanner console) {
      lines.add("// Mahin Arjhuna Goban");
      lines.add("// CSE 143");
      Date today = Calendar.getInstance().getTime();
      lines.add("// " + today.toString());
      lines.add("// TA: Eric Koegler");
      lines.add("// Assignment #");
      lines.add(" ");
   }
   
   public static void imports(Scanner console, ArrayList<String> lines) {
      String imports;
      System.out.println("* IMPORTS *");
      do {
         System.out.print("Library imports: ");
         imports = console.nextLine();
         lines.add("import java." + imports + ".*;");
      } while(!(imports.equals("s")));
      lines.remove("import java.s.*;");
      lines.add(" ");
   }
   
   public static String className(Scanner console, ArrayList<String> lines) {
      System.out.println("* CLASS NAME *");
      System.out.print("Class name: ");
      String name = console.nextLine();
      lines.add("public class " + name + " {");
      return name;
   }
   
   public static void mainMethod(Scanner console, ArrayList<String> lines) {
      String object;
      String name;
      String line;
      String arrays;
      String arrName;
      System.out.println("* MAIN METHOD *");
      lines.add("   public static void main(String[] args) {");
      do {
         System.out.print("Object to add: ");
         object = console.nextLine();
         System.out.print("Name of object: ");
         name = console.nextLine();
         line = "      " + object + " " + name + " = new " + object;
         if (object.equals("Scanner") || object.equals("File") || object.equals("PrintStream")) {
            line += "(";
            System.out.print("   Enter parameter: ");
            name = console.nextLine();
            line += name + ");";
         } else if (object.contains("[]")) {
            System.out.print("   Pre-defined / null (p/n): ");
            arrName = console.nextLine();
            if (arrName.equals("p")) {
               line = "      " + object + " " + name + " = ";
               line += "{";
               System.out.print("   Enter value: ");
               name = console.nextLine();
               if (!(name.equals("s"))) {
                  line += name;
               }
               while (!(name.equals("s"))) {
                  System.out.print("   Enter value: ");
                  name = console.nextLine();
                  if (!(name.equals("s"))) {
                     line += ", " + name;
                  }
               }
               line += "};";
            } else if (arrName.equals("n")) {
               arrays = object.substring(0, object.length() - 2);
               line = "      " + object + " " + name + " = new " + arrays;
               line += "[";
               System.out.print("   Array size: ");
               name = console.nextLine();
               line += name + "];";
            }
         } else {
            line += "();";
         }
         lines.add(line);
      } while (!(object.equals("s")) || !(name.equals("s")));
      lines.remove("      s s = new s();");
      lines.add("   }");
      lines.add(" ");
   }
   
   public static void methods(Scanner console, ArrayList<String> lines) {
      String name;
      String type;
      String line;
      String parameter;
      System.out.println("* METHODS *");
      do {
      System.out.print("Method name: ");
      name = console.nextLine();
      System.out.print("Return type: ");
      type = console.nextLine();
      line = "   public static " + type + " " + name + "(";
      System.out.print("   Enter parameter: ");
      parameter = console.nextLine();
      if (!(parameter.equals("s"))) {
         line += parameter;
      }
      while (!(parameter.equals("s"))) {
         System.out.print("   Enter parameter: ");
         parameter = console.nextLine();
         if (!(parameter.equals("s"))) {
            line += ", " + parameter;
         }
      }
      line += ") {";
      lines.add(line);
      lines.add("   }");
      lines.add(" ");
      } while (!(name.equals("s")) || !(type.equals("s")));
      for (int i = 0; i < 4; i++) {
         lines.remove(lines.size() - 1);
      }
   }
   
   public static File writeToFile(ArrayList<String> lines, String name) throws FileNotFoundException {
      name += ".java";
      File file = new File(name);
      PrintStream output = new PrintStream(file);
      for (int i = 0; i < lines.size() - 1; i++) {
         output.println(lines.get(i));
      }
      output.print(lines.get(lines.size() - 1));
      return file;
   }
   
   public static void openFile(File file) throws InterruptedException, IOException {
      System.out.println();
      System.out.print("Opening created file");
      Desktop.getDesktop().open(file);
   }
}