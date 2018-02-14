package org.rahul.completablefuture;

import static org.rahul.completablefuture.Util.delay;
import static org.rahul.completablefuture.Util.format;
import static org.rahul.completablefuture.Util.randomDelay;


public class Discount {

    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscountRemoteService(Quote quote) {
        return quote.getShopName() + " price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }
    private static double apply(double price, Code code) {
        delay();
        return format(price * (100 - code.percentage) / 100);
    }
    
    public static String applyDiscountRemoteServiceRandomDelay(Quote quote) {
        return quote.getShopName() + " price is " + Discount.applyRandomDelay(quote.getPrice(), quote.getDiscountCode());
    }
    private static double applyRandomDelay(double price, Code code) {
        randomDelay();
        return format(price * (100 - code.percentage) / 100);
    }
}
