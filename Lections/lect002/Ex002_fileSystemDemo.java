// package Lesson_02;
import java.io.File;

public class Ex002_fileSystemDemo {
    public static void main(String[] args) {
        String pathProject = System.getProperty("user.dir"); // "home/covchegg/Code/JCode"
        String pathFile = pathProject.concat("/file.txt"); // "home/covchegg/Code/JCode/file.txt"
        File f3 = new File(pathFile); // "home/covchegg/Code/JCode/file.txt"
        System.out.println(pathProject); 
        System.out.println(f3.getAbsolutePath());

        System.out.println(pathFile);
        File f1 = new File("file.txt");
        File f2 = new File("/Users/sk/vscode/java_projects/file.txt");
        System.out.println(f1.getAbsolutePath());
        System.out.println(f2.getAbsolutePath());
    }
}