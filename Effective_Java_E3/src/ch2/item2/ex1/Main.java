package ch2.item2.ex1;
/**
 * @author Roy Kim
 */

//Subject : Builder Pattern
class Main {
    public static void main(String[] args) {

        NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8)
                .calories(100)
                .sodium(35)
                .carbohydrate(27)
                .build();

        System.out.println(cocaCola.toString());
    }
}