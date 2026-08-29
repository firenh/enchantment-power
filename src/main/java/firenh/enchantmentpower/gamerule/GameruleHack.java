package firenh.enchantmentpower.gamerule;

public class GameruleHack<T> {
    public static final GameruleHack<Boolean> reroll_enchantments = new GameruleHack<>(true);

    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public GameruleHack(T value) {
        this.value = value;
    }
}
