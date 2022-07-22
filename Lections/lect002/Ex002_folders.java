import java.io.File;

public class Ex002_folders {
    public static void main(String[] args) {
        String pathProject = System.getProperty("user.dir");
        String pathDir = pathProject.concat("/MyDir");
        System.out.println(pathProject);
        File projDir = new File(pathProject);
        File dir = new File(pathDir);
        System.out.println(dir.getAbsolutePath());
        if (dir.mkdir()) {
            System.out.println("Folder created!");
        } else {
            System.out.println("Can't create. May be it's already created\nTry to find below:\n");
            for (String fname : projDir.list()) {
                System.out.println(fname);
            }
        }
        
    }
}
