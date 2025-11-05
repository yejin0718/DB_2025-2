package mvc_jdbc_test.view;

import mvc_jdbc_test.entity.Order;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderView {
    public String title = "주문 정보";
    public void printOrder(Order order) {
        Date shippingOrderDate = order.getOrderDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        String orderDate = dateFormat.format(shippingOrderDate);
        System.out.printf("%s\t%s\t%s\t%d\t%s\t%s\n", order.getOrderId(), order.getCustomerId(), order.getProductName(), order.getQuantity(), order.address, orderDate);
        System.out.println("------------------------");
    }

    public void printHead() {
        System.out.println("------------------------");
        System.out.println("---------" + title + "-------");
        System.out.println("------------------------");
    }

    public void printFooter() {
        System.out.println("------------------------");
        System.out.println("------ Print Done ------");
        System.out.println("------------------------");
    }
}
