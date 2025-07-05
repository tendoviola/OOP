import java.io.*;

public class MedicalLogReader {
    public static void main(String[] args) {
        // Normal case: try reading the actual file if it exists
        System.out.println("=== Normal File Reading ===");
        readFile(new File("medical_log.txt"));

        // Force FileNotFoundException by passing a non-existent file
        System.out.println("\n=== File Not Found Case ===");
        readFile(new File("non_existent_file.txt"));

        // Force IOException during reading by using a custom Reader that throws IOException
        System.out.println("\n=== IOException During Reading ===");
        readFileWithFaultyReader();

        // Force IOException during closing by using a custom Reader that throws IOException on close
        System.out.println("\n=== IOException During Closing ===");
        readFileWithFaultyClose();
    }

    // Method to read a file normally and catch exceptions
    private static void readFile(File file) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                System.out.println("Error closing reader: " + e.getMessage());
            }
        }
    }

    // Simulate IOException during reading by using a Reader that throws on read()
    private static void readFileWithFaultyReader() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new Reader() {
                @Override
                public int read(char[] cbuf, int off, int len) throws IOException {
                    throw new IOException("Simulated read error");
                }
                @Override
                public void close() throws IOException {}
            });
            reader.readLine(); // Will throw IOException
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                System.out.println("Error closing reader: " + e.getMessage());
            }
        }
    }

    // Simulate IOException during closing by using a Reader that throws on close()
    private static void readFileWithFaultyClose() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new Reader() {
                private boolean readCalled = false;
                @Override
                public int read(char[] cbuf, int off, int len) throws IOException {
                    if (!readCalled) {
                        readCalled = true;
                        return -1; // simulate EOF immediately
                    }
                    return -1;
                }
                @Override
                public void close() throws IOException {
                    throw new IOException("Simulated close error");
                }
            });
            String line = reader.readLine(); // Should return null immediately (EOF)
            System.out.println("File read with no content.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                System.out.println("Error closing reader: " + e.getMessage());
            }
        }
    }
}