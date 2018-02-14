package org.rahul.streams.advanced;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class PuttingIntoPractice {

    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // Query 1: Find all transactions from year 2011 and sort them by value (small to high).
        
        transactions.stream().filter(t->t.getYear()==2011).sorted(comparing(Transaction::getValue));
       
        // Query 2: What are all the unique cities where the traders work?
        
        transactions.stream().map(t->t.getTrader().getCity()).distinct();
        
        // Query 3: Find all traders from Cambridge and sort them by name.
        transactions.stream().filter(t->t.getTrader().getCity().equals("Cambridge")).map(t->t.getTrader()).distinct().sorted(comparing(Trader::getName));
        
        // Query 4: Return a string of all traders’ names sorted alphabetically.
        //transactions.stream().map(t->t.getTrader().getName()).sorted();
        transactions.stream().map(t->t.getTrader().getName()).sorted().reduce("",(n1,n2)->n1+n2);
        
        // Query 5: Are there any trader based in Milan?
        transactions.stream().anyMatch(t->t.getTrader().getName().equals("Milan"));
        
        // Query 6: Update all transactions so that the traders from Milan are set to Cambridge.
        transactions.stream().filter(t->t.getTrader().getName().equals("Milan")).forEach(t->t.getTrader().setCity("Cambridge"));
        // Query 7: What's the highest value in all the transactions?
        Integer i = transactions.stream().map(t->t.getValue()).reduce(0, Integer::max);
        
    }
}
