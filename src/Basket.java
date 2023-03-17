import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Basket {
    private static ArrayList<Integer> prices;
    private static ArrayList<Integer> counts;
    private static ArrayList<String> products;
    private static int amount;

    Basket(){

    }

    Basket(ArrayList<Integer> prices, ArrayList<Integer> counts, ArrayList<String> products, int n) {
        this.products = new ArrayList<String>();
        this.prices = new ArrayList<Integer>();
        this.counts = new ArrayList<Integer>();
        this.amount = n;
        for (int i = 0; i < n; ++i) {
            this.products.add(products.get(i));
            this.prices.add(prices.get(i));
            this.counts.add(counts.get(i));
        }
    }

    public String getProduct(int num) {
        return products.get(num);
    }

    public static void setProduct(int num, String product) {
        if(num < amount) {
            products.set(num, product);
        }
        else{
            products.add(product);
            amount++;
        }
    }

    public static void setPrice(int num, int price) {
        if(num < amount) {
            prices.set(num, price);
        }
        else{
            prices.add(price);
        }
    }

    public static void setCount(int num, int count) {
        if(num < amount) {
            counts.set(num, count);
        }
        else{
            counts.add(count);
        }
    }
    public static void setAmount(int count) {
        amount = count;
    }

    public int getCount(int num) {
        return counts.get(num);
    }
    public int getSize(){return amount;}

    public int getPrice(int num) {
        return prices.get(num);
    }

    public void addToCart(int productNum, int amount) {
        counts.set(productNum, amount + counts.get(productNum));
    }

    public void printCart() {
        for (int i = 0; i < amount; ++i) {
            System.out.println(products.get(i) + " : " + counts.get(i) + "шт. по цене " + prices.get(i) + " за штуку.");
        }
    }

    public static Basket loadFromTxtFile(File textFile) {
        try {
            Scanner myReader = new Scanner(textFile);
            prices = new ArrayList<Integer>();
            counts = new ArrayList<Integer>();
            products = new ArrayList<String>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String arr[] = data.split(" ");
                amount = arr.length;
                for (int i = 0; i < arr.length; i++) {
                    products.add(arr[i]);
                }
                data = myReader.nextLine();
                arr = data.split(" ");
                for (int i = 0; i < arr.length; i++) {
                    counts.add(Integer.valueOf(arr[i]));
                }
                data = myReader.nextLine();
                arr = data.split(" ");
                for (int i = 0; i < arr.length; i++) {
                    prices.add(Integer.valueOf(arr[i]));
                }
            }
            myReader.close();
            System.out.println("Saved!");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return new Basket(prices, counts, products, amount);
    }

    public void saveTxt(File textFile) {
        try {
            PrintWriter out = new PrintWriter(textFile);
            for (int i = 0; i < amount; ++i) {
                out.print(products.get(i) + " ");
            }
            out.println();
            for (int i = 0; i < amount; ++i) {
                out.print(counts.get(i) + " ");
            }
            out.println();
            for (int i = 0; i < amount; ++i) {
                out.print(prices.get(i) + " ");
            }
            out.close();
            System.out.println("Saved!");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}