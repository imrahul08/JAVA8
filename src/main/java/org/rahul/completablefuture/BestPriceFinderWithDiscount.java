package org.rahul.completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BestPriceFinderWithDiscount {

    private final List<ShopWithDiscount> shops = Arrays.asList(new ShopWithDiscount("BestPrice"),
                                                   new ShopWithDiscount("LetsSaveBig"),
                                                   new ShopWithDiscount("MyFavoriteShop"),
                                                   new ShopWithDiscount("BuyItAll"),
                                                   new ShopWithDiscount("ShopEasy"));

    private final Executor executor = Executors.newFixedThreadPool(shops.size(), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    });

    public List<String> findPricesSequential(String product) {
        return shops.stream()
                .map(shop -> shop.getPriceRemoteService(product))
                .map(Quote::parse)
                .map(Discount::applyDiscountRemoteService)
                .collect(Collectors.toList());
    }

    public List<String> findPricesParallel(String product) {
        return shops.parallelStream()
                .map(shop -> shop.getPriceRemoteService(product))
                .map(Quote::parse)
                .map(Discount::applyDiscountRemoteService)
                .collect(Collectors.toList());
    }

    public List<String> findPricesFuture(String product) {
        return null;
    }

    

    public void printPricesStream(String product) {
        long start = System.nanoTime();
        
        
        System.out.println("All shops have now responded in " + ((System.nanoTime() - start) / 1_000_000) + " msecs");
    }

}
