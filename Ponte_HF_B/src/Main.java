
public class Main {

    //NOTE: There is no way of them knowing that the cashier multiplied the prices of the items instead of adding them
    //based on the price alone, since it is the same in both cases + if they are so smart they should have known that it is correct
    //and these two information implies that they don't have enough money anyway
    public static void main(String[] args) {
        double price = 65.52;
        double previousResult = 0.0;
        double secondPrice = 0.0;
        int resultCount = 0;
        for (double i = 1; i < price/2; i += 0.01) {
            double j = Math.round(i*100.0)/100.0;
            double result = Math.round(((price - j) * (-1) - Math.sqrt(((price - j) * (price - j)) - (4 * (-1 * (price / j)) * (-1)))) / (2 * (-1)) * 100.0) / 100.0;
            if (previousResult != result) {
                secondPrice = Math.round(((price - j) - result) * 100.0) / 100.0;
                if (secondPrice > 0) {
                    double multiplied = Math.round((j * secondPrice * result) * 100.0) / 100.0;
                    double added = Math.round((j + secondPrice + result) * 100.0) / 100.0;
                    if (added == 65.52 && multiplied == 65.52) {
                        resultCount++;
                        System.out.println(j + " " + result + " " + secondPrice);
                    }
                }
            }
        }
        System.out.println("There is " + resultCount + " combinations");
    }
}
