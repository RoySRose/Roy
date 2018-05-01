package ch2.item9;

import java.io.*;

//Subject : Prefer try-with-resources to try-finally
class Main {

    public static final int BUFFER_SIZE = 100;

    // try-with-resources - the best way to close resources!
    static String firstLineOfFile(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            return br.readLine();
        }
    }

    // try-with-resources on multiple resources - short and sweet
    static void copy(String src, String dst) throws IOException {
        try (InputStream in = new FileInputStream(src);
             OutputStream out = new FileOutputStream(dst)){
            byte[] buf = new byte[BUFFER_SIZE];
            int n;
            while ((n= in.read(buf)) >= 0)
                out.write(buf,0,n);
        }
    }


    public static void main(String[] args) throws Exception{

        String current = new java.io.File( "." ).getCanonicalPath();
        System.out.println("Current dir:"+current);
        String currentDir = System.getProperty("user.dir");
        System.out.println("Current dir using System:" +currentDir);

        String text = firstLineOfFile("Effective_Java_E3/src/ch2/item9/in");

        System.out.println(text);

        copy("Effective_Java_E3/src/ch2/item9/in", "Effective_Java_E3/src/ch2/item9/out");
    }
}