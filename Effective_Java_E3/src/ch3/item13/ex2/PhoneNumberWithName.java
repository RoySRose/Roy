package ch3.item13.ex2;

import java.util.Objects;

//Subject : Override clone judiciously
//if super class clones by calling a constructor subclass result a failure
public final class PhoneNumberWithName extends PhoneNumber {
    private final String name;

    public PhoneNumberWithName(int areaCode, int prefix, int lineNum, String name) {
        super(areaCode, prefix, lineNum);
        this.name = name;
    }

    @Override
    public PhoneNumberWithName clone() {
        try {
            return (PhoneNumberWithName) super.clone();
        } catch (Exception e) {
            throw new AssertionError(e); // Can't happen
        }
    }

    @Override
    public String toString() {
        return "PhoneNumberWithName{" + super.toString() +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PhoneNumberWithName that = (PhoneNumberWithName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public static void main(String[] args) {
        PhoneNumberWithName clone1 = new PhoneNumberWithName(111, 222, 3333, "Roy");
        PhoneNumberWithName clone2 = clone1.clone();

        System.out.println(clone1);
        System.out.println(clone2);
        System.out.println(clone1 != clone2); //true
        System.out.println(clone1.getClass() == clone2.getClass()); //true
        System.out.println(clone1.equals(clone2)); //true

    }
}