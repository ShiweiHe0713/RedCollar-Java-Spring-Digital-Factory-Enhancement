package com.company.model;
import java.io.Serializable;

public class OrderReceiving implements Serializable{
    // Questions: Is every process instance a new order receiving? Is every new order receiving a new process instance?
    // Is every process instance a process for the user's order
    // Distinguish process and order, what's the difference
    // process start after the order
    // rabbit mq listens to the order, then the process is created after the order created
    // replay agent similar, publisher agent fills the queue, subscriber agent read the new orders
    // subcriber starts the JBPM process
    // putting new data in both rabbitmq and influx db
    
    private Long processId; // jBPM process ID for the new order receiving
    private String processName; // jBPM process name for the new order receiving
    private String processStatus; // jBPM process status for the new order receiving

    // Variables for the Order Receiving task
    private Long orderId;
    private String orderNumber;
    private String orderDate; 
    private String orderStatus; 
    private String orderType; 
    private String orderAmount; 
    private String orderCustomer; 
    private String orderCustomerEmail; 
    private String orderCustomerPhone; 
    private String orderCustomerAddress; 
    private String orderCustomerCity; 
    private String orderCustomerZip; 
    private String orderCustomerState;
    private String orderProduct; 
    private String orderProductQuantity; 
    private String orderProductPrice; 
    private String orderProductTotal; 
    private String orderShippingDate; 
   
    public Long getProcessId() { // new order receiving ID (provided by new process instance ID)
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public String getProcessName() { // new order receiving name (provided by new process instance name)
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getProcessStatus() { // new order receiving status (provided by new process instance status)
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderCustomer() {
        return orderCustomer;
    }

    public void setOrderCustomer(String orderCustomer) {
        this.orderCustomer = orderCustomer;
    }

    public String getOrderCustomerEmail() {
        return orderCustomerEmail;
    }

    public void setOrderCustomerEmail(String orderCustomerEmail) {
        this.orderCustomerEmail = orderCustomerEmail;
    }
}
