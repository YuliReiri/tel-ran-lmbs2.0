import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class App {

    public String f4( String value ) {
        return value;
    }
    public static void main(String[] args) throws Exception {

        
        Function <Double, Long> fRound = (aDouble)  -> {
            Long res = Math.round(aDouble);
            System.out.println("round from " + aDouble +" to rounded to:  " + res );
            return res;
        };
        Function < Double, Double > m2 = x -> x*2.0; 
        Function <Long, Double > d2 = x -> x/3.0;

        Function<Double, Long> x1 = fRound.compose(m2);
        Function<Double, Double> x2 = fRound.andThen( d2 );

        
        
        x2.apply(1.1);
        
        
        System.out.println( x1.apply(5.5));
        System.out.println( x1 );


        
        System.out.println( fRound.compose(m2).andThen( d2 ).apply(11.2 ));

        Double x3 = 2.2;
        System.out.println( 
            fRound.compose( (Double x) -> x*2.0).andThen( 
                (x) -> x/3.0 ).apply(x3)); 


        Function< String, String>  f1 = s1 -> s1 +"1";
        Function< String, String > f2 = s1 -> s1 +"2";
        Function< String, String > f3 = s1 -> s1 +"3";
        Function< String, String > f4 = s1 -> s1 +"4";
        Function< String, String > f5 = s1 -> s1 +"5";

        String res1 = f1.andThen(f2).andThen(f3).andThen(f4).andThen(f5).apply(" andThen: ");
        String res2 = f1.compose(f2).compose(f3).compose(f4).compose(f5).apply(" compose: ");


        System.out.println( res1 );
        System.out.println( res2 );
 
        
        Function<Integer, Integer > f6 = Function.identity();
        //Function< String, String> f7 = t -> t;

        //stream().map( Function.identity() )
        
        System.out.println(f6.compose( (Integer x) -> x/2 ).apply(61));
        System.out.println(f6.andThen( (Integer x) -> x/2 ).apply(61));

       // System.out.println(f7.apply("test identity"));

       Function<Double, Double > f8 = Function.identity();
       Double x5 = 2.0;
        System.out.println( 
            f8.compose( (Double x) -> x*2).apply(x5)); 

        String testString = " this is test string ";

        Supplier<String> sf1 = () -> testString.substring(0, 4);
        Supplier<Double> sf2 = () -> Math.random();

        System.out.println( sf1.get() );
        System.out.println( sf2.get() );

        List<Integer> l1 = new ArrayList<Integer>();
        List<Integer> o2 = new ArrayList<Integer>();

        l1.add(1);
        l1.add(8);
        l1.add( 7);

        Consumer< List<Integer>> cf1 = xcf1 -> xcf1.forEach( a -> System.out.println( a ));
        
        cf1.andThen( (List<Integer> l2) -> { 
            l2.forEach( z -> o2.add(z/2));
        }  ).accept(l1);

        System.out.println("result list: ");
        o2.forEach( z -> System.out.println(z));
    }

}
