package demo.hermesfuxi.java.base.commons.strings;

import net.moznion.random.string.RandomStringGenerator;

public class RandomStringDemo {
    public static void main(String[] args) {
        RandomStringGenerator generator = new RandomStringGenerator();

// generates random string (e.g. "a5B123 18X")
        for (int i = 0; i < 5; i++){
            String randomString = generator.generateByRegex("(?<=phone\":\").*?(?=\")");
            System.out.println(randomString);
        }

    }
}
