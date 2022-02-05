package com.example.e_comerce_admin.Models;


import com.example.e_comerce_admin.R;
import com.example.e_comerce_admin.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class DashboardItem {
    private int icon;
    private String itemName;

    public DashboardItem(int icon, String itemName) {
        this.icon = icon;
        this.itemName = itemName;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public static List<DashboardItem> getDashboardItems() {
        final ArrayList<DashboardItem> items = new ArrayList<>();
        items.add(new DashboardItem(R.drawable.ic_baseline_add_circle_outline_24, Constants.Item.ADD_PRODUCT));
        items.add(new DashboardItem(R.drawable.ic_baseline_category_24, Constants.Item.ADD_CATEGORY));
        items.add(new DashboardItem(R.drawable.ic_order, Constants.Item.VIEW_ORDERS));
        items.add(new DashboardItem(R.drawable.ic_product, Constants.Item.VIEW_PRODUCT));
        items.add(new DashboardItem(R.drawable.ic_user, Constants.Item.VIEW_USERS));
        items.add(new DashboardItem(R.drawable.ic_settings, Constants.Item.SETTINGS));
        items.add(new DashboardItem(R.drawable.ic_report, Constants.Item.VIEW_REPORTS));
        return items;
    }
}
