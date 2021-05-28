package crw.design.patterns.staticProxy;

public class BuyHouseProxy implements BuyHouse {

    private BuyHouse buyHouse;

    public BuyHouseProxy(final  BuyHouse buyHouse){
        this.buyHouse = buyHouse;
    }

    @Override
    public void buyHouse() {
        System.out.println("before buy house");
        buyHouse.buyHouse();
        System.out.println("after buy house");
    }
}
