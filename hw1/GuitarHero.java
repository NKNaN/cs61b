import es.datastructur.synthesizer.GuitarString;
public class GuitarHero {
    private static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static void main(String[] args) {
        GuitarString[] strings = new GuitarString[KEYBOARD.length()];
        for (int i = 0; i < KEYBOARD.length(); i++) {
            strings[i] = new GuitarString(440.0 * Math.pow(2, (i - 24.0) / 12.0));
        }
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = KEYBOARD.indexOf(key);
                if (index == -1) {
                    continue;
                }
                strings[index].pluck();
            }
            double sample = 0;
            for (int i = 0; i < KEYBOARD.length(); i++) {
                sample += strings[i].sample();
            }
            System.out.println(sample);
            StdAudio.play(sample);
            for (int i = 0; i < KEYBOARD.length(); i++) {
                strings[i].tic();
            }
        }
    }
}
