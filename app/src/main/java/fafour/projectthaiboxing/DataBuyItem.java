package fafour.projectthaiboxing;

/**
 * Created by Fafour on 29/1/2560.
 */

public class DataBuyItem {
    public int type;
    public String accessoriesName;
    public String accessoriesSize;
    public int accessoriesStock;
    public double accessoriesPrice;
    public String accessoriesImg;
    public int accessoriesNum;

    public double accessoriesSale;
    public int accessoriesSaleData;

    public DataBuyItem (){
    }
    public DataBuyItem (int type,String accessoriesName,String accessoriesSize,double accessoriesPrice ,
                        String accessoriesImg , int accessoriesNum , double accessoriesSale ,
                        int accessoriesSaleData,int accessoriesStock){
        this.setType(type);
        this.setAccessoriesImg(accessoriesImg);
        this.setAccessoriesName(accessoriesName);
        this.setAccessoriesSize(accessoriesSize);
        this.setAccessoriesPrice(accessoriesPrice);
        this.setAccessoriesNum(accessoriesNum);
        this.setAccessoriesSale(accessoriesSale);
        this.setAccessoriesSaleData(accessoriesSaleData);
        this.setAccessoriesStock(accessoriesStock);
    }




    public String getAccessoriesName() {
        return accessoriesName;
    }

    public void setAccessoriesName(String accessoriesName) {
        this.accessoriesName = accessoriesName;
    }

    public double getAccessoriesPrice() {
        return accessoriesPrice;
    }

    public void setAccessoriesPrice(double accessoriesPrice) {
        this.accessoriesPrice = accessoriesPrice;
    }

    public String getAccessoriesImg() {
        return accessoriesImg;
    }

    public void setAccessoriesImg(String accessoriesImg) {
        this.accessoriesImg = accessoriesImg;
    }

    public int getAccessoriesNum() {
        return accessoriesNum;
    }

    public void setAccessoriesNum(int accessoriesNum) {
        this.accessoriesNum = accessoriesNum;
    }

    public double getAccessoriesSale() {
        return accessoriesSale;
    }

    public void setAccessoriesSale(double accessoriesSale) {
        this.accessoriesSale = accessoriesSale;
    }

    public int getAccessoriesSaleData() {
        return accessoriesSaleData;
    }

    public void setAccessoriesSaleData(int accessoriesSaleData) {
        this.accessoriesSaleData = accessoriesSaleData;
    }

    public String getAccessoriesSize() {
        return accessoriesSize;
    }

    public void setAccessoriesSize(String accessoriesSize) {
        this.accessoriesSize = accessoriesSize;
    }

    public int getAccessoriesStock() {
        return accessoriesStock;
    }

    public void setAccessoriesStock(int accessoriesStock) {
        this.accessoriesStock = accessoriesStock;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
