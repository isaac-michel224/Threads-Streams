package threads;

public class ImmutableRGB {
    final private int red;
    final private int green;
    final private int blue;
    final private String name;

    private void check(int red, int green, int blue) {
        if (red < 0 || red > 255
                || green < 0 || green > 255
                || blue < 0 || blue > 255) {
            throw new IllegalArgumentException();
        }
    }

    public ImmutableRGB ((int red, int green, int blue, String name) {
        check(red, green, blue);
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.name = name;
    }

    public getRGB() {

    }

    public String getName() {
        return name;
    }

    public ImmutableRGB invert{
        return new ImmutableRGB(red: 255-red, green: 255-
    green, blue: 255-blue, name: "Inverse of " + name);
    }
}
