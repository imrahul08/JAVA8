package org.rahul.streams.advanced;

import java.util.Optional;

import org.rahul.streams.Dish;

public class OptionalvsNull {

    public static void main(String... args) {

        Optional<Dish> dish = Dish.menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();

        if (dish.isPresent()) {
            System.out.println("" + dish.get().getName());
        }
        System.out.println("---");
        Dish.menu.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(d -> System.out.println("" + d.getName()));

    }

}
