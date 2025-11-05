package mvc_jdbc_test.entity;

public class Customer {
    private String customerId;
    private String customerName;
    private int age;
    private String level;
    private String job;
    private int reward;     //적립금

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }
}
