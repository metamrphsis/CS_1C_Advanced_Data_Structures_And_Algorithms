package week01_part01.resources;

import java.util.Objects;

public class Test {
    String name;

    public static void main(String[] args){
        String name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return Objects.equals(name, test.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
