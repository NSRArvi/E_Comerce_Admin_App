package com.example.e_comerce_admin.utils;

public class Constants {
    public static final String LOW = "Low";
    public static final String NORMAL = "Normal";
    public static final String HIGH = "High";
    public static final String REQUEST_KEY = "date_time_key";
    public static final String DATE_KEY = "date";
    public static final String TIME_KEY = "time";
    public static final String YEAR = "year";
    public static final String MONTH = "month";
    public static final String DAY = "day";
    public static final String HOUR = "hour";
    public static final String MINUTE = "minute";
    public static final String TAKA = "৳";
    public static class Item {
        public static final String ADD_PRODUCT = "Add Product";
        public static final String ADD_CATEGORY= "Add Category";
        public static final String VIEW_PRODUCT = "View Product";
        public static final String VIEW_ORDERS = "View Orders";
        public static final String VIEW_REPORTS = "View Reports";
        public static final String VIEW_USERS = "View Users";
        public static final String SETTINGS = "Settings";
    }
    public static class PaymentMethod {
        public static final String COD = "Cash on Delivery";
        public static final String ONLINE = "Online Payment";
    }

    public static class OrderStatus{
        public static final String PENDING = "Pending";
        public static final String DELIVERED = "Delivered";
        public static final String CANCELLED = "Cancelled";
    }

    public static class DbCollection{
        public static final String COLLECTION_PRODUCT = "Products";
        public static final String COLLECTION_PURCHASE = "Purchases";
        public static final String COLLECTION_CATEGORY = "Categories";
        public static final String COLLECTION_ORDERS = "Orders";
        public static final String COLLECTION_USERS = "Users";
        public static final String COLLECTION_CART = "Cart";
        public static final String COLLECTION_ORDER_DETAILS = "OrderDetails";
        public static final String COLLECTION_ORDER_SETTINGS = "OrderSettings";
        public static final String DOCUMENT_ORDER_SETTING = "Settings";
        public static final String TAKA = "৳";
    }
}
