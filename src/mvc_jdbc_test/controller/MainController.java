package mvc_jdbc_test.controller;

import jdbc_test.JDBCConnector;
import mvc_jdbc_test.entity.Customer;
import mvc_jdbc_test.entity.Order;
import mvc_jdbc_test.view.CustomerView;
import mvc_jdbc_test.view.EditCustomerInfoView;
import mvc_jdbc_test.view.InputCustomerInfoView;
import mvc_jdbc_test.view.OrderView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainController {
    public static void main(String[] args) {
        Connection conn = JDBCConnector.getConnection();
//        customerListAndView(conn);
//        orderListAndView(conn);
//        inputCustomerAndView(conn);
//        editCustomerAndView(conn);
        delCustomer(conn);
    }

    public static void orderListAndView(Connection conn) {
        ArrayList<Order> orderList = new ArrayList<Order>();
        try {
            String sql = "select 주문번호, 주문고객, 고객아이디, 배송지, 수량, 주문일자, 주문제품\n" +
                    "from 주문\n" +
                    "inner join 고객\n" +
                    "on 주문.주문고객 = 고객.고객아이디";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Order order = null;

            while (rs.next()) {
                order = new Order();
                order.setOrderId(rs.getString("주문번호"));
                order.setCustomerName(rs.getString("주문고객"));
                order.setCustomerId(rs.getString("고객아이디"));
                order.setAddress(rs.getString("배송지"));
                order.setQuantity(rs.getInt("수량"));
                order.setOrderDate(rs.getDate("주문일자"));
                order.setProductName(rs.getString("주문제품"));
                orderList.add(order);
            }
        } catch (SQLException e) {
            System.out.println("Statement or SQL Error");
        }

        OrderView orderView = new OrderView();
        orderView.printHead();
        for(Order order : orderList) {
            orderView.printOrder(order);
        }
        orderView.printFooter();
    }

    public static void customerListAndView(Connection conn) {
        ArrayList<Customer> customerList = new ArrayList<Customer>();
        try {
            String sql = "select * from 고객";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            Customer customer = null;

            while (rs.next()) {
                customer = new Customer();
                customer.setCustomerId(rs.getString("고객아이디"));
                customer.setCustomerName(rs.getString("고객이름"));
                customer.setAge(rs.getInt("나이"));
                customer.setLevel(rs.getString("등급"));
                customer.setJob(rs.getString("직업"));
                customer.setReward(rs.getInt("적립금"));
                customerList.add(customer);
            }
        } catch (SQLException e) {
            System.out.println("Statement or SQL Error");
        }
//       CustomerView를 사용해서 customerList에 저장된 Customer Entity의 정보들을 출력해 보세요.
        CustomerView customerView = new CustomerView();
        customerView.printHead();
        for(int i=0; i<customerList.size(); i++){
            customerView.printCustomer(customerList.get(i));
        }
        customerView.printFooter();
    }

    public static void inputCustomerAndView(Connection conn) {
        Scanner sc = new Scanner(System.in);
        InputCustomerInfoView inputCustomer = new InputCustomerInfoView();
        while (true) {
            Customer customer = inputCustomer.inputCustomerInfo();
            CustomerView customerView = new CustomerView();
            customerView.printHead();
            customerView.printCustomer(customer);
            customerView.printFooter();

            String sql = "update 고객 set 고객 values(?,?,?,?,?,?)";

            try{
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, customer.getCustomerId());
                pstmt.setString(2, customer.getCustomerName());
                pstmt.setInt(3, customer.getAge());
                pstmt.setString(4, customer.getLevel());
                pstmt.setString(5, customer.getJob());
                pstmt.executeUpdate();
                pstmt.close();
            } catch (SQLException e) {
                System.out.println("Statement or SQL Error");
            }
            System.out.println("프로그램 종료를 원하면 e를 입력");

            String input = sc.nextLine();

            if(input.equals("e")){
                break;
            }
        }
        System.out.println("프로그램이 종료 되었습니다.");
    }

    public static void editCustomerAndView(Connection conn) {
        Scanner sc = new Scanner(System.in);
        //수정할 고객정보 입력
        String customerId = sc.nextLine();

        //수정할 고객정보 조회
        viewCustomer(conn, customerId);

        //고객정보 수정
        EditCustomerInfoView editCustomer = new EditCustomerInfoView();
        CustomerView customerView = new CustomerView();
        Customer customer = editCustomer.EditCustomerInfo();
        customerView.printHead();
        customerView.printCustomer(customer);
        customerView.printFooter();

        String sql = "update 고객 \n" +
                    "set 고객이름=?, 나이=?, 등급=?, 직업=?, 적립금=?\n" +
                    "where 고객아이디=?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, customer.getCustomerName());
            pstmt.setInt(2, customer.getAge());
            pstmt.setString(3, customer.getLevel());
            pstmt.setString(4, customer.getJob());
            pstmt.setInt(5, customer.getReward());
            pstmt.setString(6, customerId);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Statement or SQL Error");
        }

        //수정된 고객정보 조회
        viewCustomer(conn, customerId);
    }

    public static void viewCustomer(Connection conn, String customerId) {
        Customer customer = null;
        try {
            String sql = "select * from 고객 where 고객아이디 = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, customerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                customer = new Customer();
                customer.setCustomerId(rs.getString("고객아이디"));
                customer.setCustomerName(rs.getString("고객이름"));
                customer.setAge(rs.getInt("나이"));
                customer.setLevel(rs.getString("등급"));
                customer.setJob(rs.getString("직업"));
                customer.setReward(rs.getInt("적립금"));
            }
        } catch (SQLException e) {
            System.out.println("Statement or SQL Error");
        }
        CustomerView customerView = new CustomerView();
        customerView.printHead();
        customerView.printCustomer(customer);
        customerView.printFooter();
    }

    public static void delCustomer(Connection conn) {
        Scanner sc = new Scanner(System.in);
        //삭제할 고객정보 입력
        String customerId = sc.nextLine();

        //삭제할 고객정보 조회
        viewCustomer(conn, customerId);

        System.out.println("정말로 삭제하시겠습니까?(y/n)");
        String delYn = sc.nextLine();

        if(delYn.equals("y")){
            try {
                String sql = "delete from 고객 where 고객아이디 = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, customerId);
                ResultSet rs = ps.executeQuery();
            } catch (Exception e) {
                System.out.println("Statement or SQL Error");
            }

            System.out.println("삭제가 완료되었습니다.");
        }
    }

}
