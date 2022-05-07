import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.nio.file.Files.readAllLines;
import static java.nio.file.Paths.get;

public class Main {
    public static void main(String[] args) throws IOException {
        final Set<String> primes =
                Arrays.stream(readAllLines(get("src/main/resources/primes.csv"))
                                .stream()
                                .reduce(String::concat)
                                .get()
                                .split(","))
                        .collect(Collectors.toSet());

        final List<String> doubleTruncatable = new ArrayList<>();
        primes.forEach(prime -> {
            boolean leftTrunctable = true;
            for (int digitIndex = 1; digitIndex < prime.length(); digitIndex++) {
                final String substring = prime.substring(digitIndex);
                if (!primes.contains(substring)) {
                    leftTrunctable = false;
                    break;
                }
            }

            if (leftTrunctable) {
                boolean rightTruncatable = true;
                for (int digitIndex = 1; digitIndex < prime.length(); digitIndex++) {
                    if (!primes.contains(prime.substring(0, digitIndex))) {
                        rightTruncatable = false;
                        break;
                    }
                }

                if (rightTruncatable) {
                    if (prime.length() > 1) {
                        doubleTruncatable.add(prime);
                    }
                }
            }
        });

        System.out.println(doubleTruncatable.size());
        System.out.println(doubleTruncatable
                .stream()
                .map(Integer::parseInt)
                .reduce(Integer::sum));
    }
}
