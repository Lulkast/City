import java.util.Collection;
import java.util.Set;
import java.util.stream.Stream;

final class Calc {

    private Calc (){}

    public static double getArea(Collection<? extends Areable>... areables) {
        return Stream.of(areables)
                .flatMap(Collection::stream)
                .mapToDouble(c -> ((Areable) c).getWidth() * ((Areable) c).getLength())
                .sum();
    }

    public static double getDamage(Collection<? extends Areable>... areables) {
        return Stream.of(areables)
                .flatMap(Collection::stream)
                .map(c -> ((Areable) c).getDamag())
                .flatMap(Collection::stream)
                .mapToDouble(d -> d.getDamagWidth() * d.getDamagLength())
                .sum();
    }

    public static double getLength(Set<? extends Areable>... areables) {
        return Stream.of(areables)
                .flatMap(Collection::stream)
                .mapToDouble(c -> ((Areable) c).getLength())
                .sum();
    }
}

