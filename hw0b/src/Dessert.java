public class Dessert {
    public int flavor;
    public int price;
    public static int numDesserts = 0;

    public Dessert(int w,int v){
        flavor=w;
        price=v;
        numDesserts++;
    }

    public void printDessert(){
        System.out.println(flavor+" "+price+" "+numDesserts);
    }

    public static void main(String[] args){
        System.out.println("I love dessert!");
    }
}
