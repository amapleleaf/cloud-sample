package com.sample.common.model;

public class SysMenu {
    private Long menuId;

    private String menuName;

    private String menuType;

    private String ico;

    private String actionUrl;

    private Long parentId;

    private Long orderNo;

    private String available;

    private String menuCode;

    public SysMenu(Long menuId, String menuName, String menuType, String ico, String actionUrl, Long parentId, Long orderNo, String available, String menuCode) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuType = menuType;
        this.ico = ico;
        this.actionUrl = actionUrl;
        this.parentId = parentId;
        this.orderNo = orderNo;
        this.available = available;
        this.menuCode = menuCode;
    }

    public SysMenu() {
        super();
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType == null ? null : menuType.trim();
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico == null ? null : ico.trim();
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl == null ? null : actionUrl.trim();
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available == null ? null : available.trim();
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode == null ? null : menuCode.trim();
    }
}