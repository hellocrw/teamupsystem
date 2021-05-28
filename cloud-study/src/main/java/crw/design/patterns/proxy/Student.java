package crw.design.patterns.proxy;

public class Student implements People {
    @Override
    public People work(String workName) {
        System.out.println("工作内容是： " + workName);
        return this;
    }

    @Override
    public String time() {
        return "2021-05-27";
    }
}
