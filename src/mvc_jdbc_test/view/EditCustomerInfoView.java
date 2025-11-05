package mvc_jdbc_test.view;

import mvc_jdbc_test.entity.Customer;

import java.util.Scanner;


public class EditCustomerInfoView {
    public Customer EditCustomerInfo() {
        Customer customer = new Customer();
        Scanner s1 = new Scanner(System.in);
        Scanner s2 = new Scanner(System.in);
        System.out.println("======== 고객 정보 수정 ========\n");
        System.out.print("고객이름 입력:");
        String customerName = s1.nextLine();
        System.out.print("고객나이 입력:");
        int customerAge = s2.nextInt();
        System.out.print("고객등급 입력:");
        String customerLevel = s1.nextLine();
        System.out.print("고객직업 입력:");
        String customerJob = s1.nextLine();
        System.out.print("고객적립금 입력:");
        int customerReward = s2.nextInt();

        customer.setCustomerName(customerName);
        customer.setAge(customerAge);
        customer.setLevel(customerLevel);
        customer.setJob(customerJob);
        customer.setReward(customerReward);

        return customer;
    }
}
