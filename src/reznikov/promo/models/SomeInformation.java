package reznikov.promo.models;

public class SomeInformation {
    private String s;
    private int a;

    public SomeInformation(String s, int a) {
        this.s = s;
        this.a = a;
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("equals!");
        return super.equals(obj);
    }
}
