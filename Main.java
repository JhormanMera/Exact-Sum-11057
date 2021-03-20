import java.io.*;import java.util.*;

public class Main {

    private static final String FILE_SEPARATOR = "\\ ";
    private static ArrayList<Integer> cash = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Initialize(br, bw);
        br.close();
        bw.close();
    }

    public static void Initialize(BufferedReader br, BufferedWriter bw) throws IOException {

        ArrayList<int[]> instance = new ArrayList<>();
        String size = br.readLine();

        while (size != null) {
            int extent = Integer.parseInt(size);
            int[] array = new int[extent];
            String line = br.readLine();
            String[] parts = line.split(FILE_SEPARATOR);
            for (int i = 0; i < extent; i++) {
                array[i] = Integer.parseInt(parts[i]);
            }
            Arrays.sort(array);
            instance.add(array);

            cash.add(Integer.parseInt(br.readLine()));
            br.readLine();      
            size = br.readLine();
        }

        for (int i = 0; i < instance.size(); i++) {
            bw.write(getInfo(instance.get(i), cash.get(i)));
            bw.flush();
        }

    }

    public static String getInfo(int[] array, int output) {
        int book1 = 0;
        int book2 = 10000000;
        for (int i = 0; i < array.length; i++) {
            int price1 = array[i];
            int price2 = binarySearch(i + 1, (array.length - 1), (output - price1), array);
            if (price2 > -1) {
                if ((price2 - price1) < (book2 - book1)) {
                    book1 = price1;
                    book2 = price2;
                }
            }
        }
        return "Peter should buy books whose prices are " + book1 + " and " + book2 + "."+ "\n\n";
    }

    public static int binarySearch(int min, int max, int gap, int[] array) {
        if (min > max) {
            return -1;
        }
        int m = (min + max) / 2;
        if (array[m] > gap) {
            return binarySearch(min, m - 1, gap, array);
        } else if (array[m] < gap) {
            return binarySearch(m + 1, max, gap, array);
        } else {
            return gap;
        }
    }
}
