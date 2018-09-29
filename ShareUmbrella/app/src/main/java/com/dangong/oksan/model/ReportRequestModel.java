package com.dangong.oksan.model;

/**
 * Created by Vinchan on 2018/9/28.
 */

public class ReportRequestModel {
    private int longUmbrellaLibrary; //伞仓长伞数目
    private int shorUmbrellaLibrary; //伞仓短伞数目
    private int alpenstockLibrary; //伞仓登山杖数目

    private int longUmbrellaTrough; //伞槽长伞数目
    private int shorUmbrellaTrough; //伞槽短伞数目
    private int alpenstockLTrough; //伞槽登山杖数目

    private String siteId; //站点Id
    private String employeeNo; //员工号

    public int getLongUmbrellaLibrary() {
        return longUmbrellaLibrary;
    }

    public void setLongUmbrellaLibrary(int longUmbrellaLibrary) {
        this.longUmbrellaLibrary = longUmbrellaLibrary;
    }

    public int getShorUmbrellaLibrary() {
        return shorUmbrellaLibrary;
    }

    public void setShorUmbrellaLibrary(int shorUmbrellaLibrary) {
        this.shorUmbrellaLibrary = shorUmbrellaLibrary;
    }

    public int getAlpenstockLibrary() {
        return alpenstockLibrary;
    }

    public void setAlpenstockLibrary(int alpenstockLibrary) {
        this.alpenstockLibrary = alpenstockLibrary;
    }

    public int getLongUmbrellaTrough() {
        return longUmbrellaTrough;
    }

    public void setLongUmbrellaTrough(int longUmbrellaTrough) {
        this.longUmbrellaTrough = longUmbrellaTrough;
    }

    public int getShorUmbrellaTrough() {
        return shorUmbrellaTrough;
    }

    public void setShorUmbrellaTrough(int shorUmbrellaTrough) {
        this.shorUmbrellaTrough = shorUmbrellaTrough;
    }

    public int getAlpenstockLTrough() {
        return alpenstockLTrough;
    }

    public void setAlpenstockLTrough(int alpenstockLTrough) {
        this.alpenstockLTrough = alpenstockLTrough;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }
}
