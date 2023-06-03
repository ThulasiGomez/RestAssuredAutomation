package Bdd;

public class MethodChaining
{
    public static void main(String[] args) {
        MethodChaining m=new MethodChaining();
        a1().a2().a3();
    }

    public static MethodChaining a1(){
        System.out.println("a1 calling");
        return new MethodChaining();
    }
    public static MethodChaining a2(){
        System.out.println("a2 calling");
        return new MethodChaining();
    }

    public MethodChaining a3(){
        System.out.println("a3 calling");
        return this;
    }
}
