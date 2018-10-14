import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PermutationWrapper {
    public static void main(String[] args) throws IOException {
        FileInputStream is = new FileInputStream(new File(args[2]));
        System.setIn(is);
        Permutation.main(args);
    }
}