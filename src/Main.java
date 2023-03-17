import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int N = 6;
        ArrayList<String> products = new ArrayList<String>();
        products.add("Хлеб");
        products.add("Мясо");
        products.add("Сыр");
        products.add("Яйцо");
        products.add("Рыба");
        products.add("Яблоко");
        ArrayList<Integer> prices = new ArrayList<>();
        prices.add(20);
        prices.add(150);
        prices.add(100);
        prices.add(80);
        prices.add(200);
        prices.add(100);
        ArrayList<Integer> counts = new ArrayList<Integer>();
        counts.add(3);
        counts.add(4);
        counts.add(5);
        counts.add(10);
        counts.add(2);
        counts.add(6);
        boolean mask[] = {true, true, true};
        File textFile = new File("Basket.txt");
        Basket basket;
        if (textFile.exists()) {
            basket = new Basket();
            basket = basket.loadFromTxtFile(textFile);
            System.out.println(basket.getSize());
            for (int i = 0; i < N; ++i) {
                boolean f = true;
                for (int j = 0; j < basket.getSize(); ++j) {
                    if (products.get(i).equals(basket.getProduct(j))) {
                        basket.addToCart(j, counts.get(i));
                        f = false;
                        break;
                    }
                }
                if (f) {
                    basket.setProduct(basket.getSize(), products.get(i));
                    basket.setPrice(basket.getSize(), prices.get(i));
                    basket.setCount(basket.getSize(), counts.get(i));
                }
            }
            basket.saveTxt(textFile);
        } else {
            basket = new Basket(prices, counts, products, N);
            basket.saveTxt(textFile);
        }
        basket.printCart();
    }
}