package fafour.projectthaiboxing;

/**
 * Created by Fafour on 29/1/2560.
 */

public class DataBuyItem {
    public String accessoriesName;
    public double accessoriesPrice;
    public int accessoriesImg;
    public int accessoriesNum;

    public double accessoriesSale;
    public int accessoriesSaleData;


    public DataBuyItem (String accessoriesName,double accessoriesPrice , int accessoriesImg ,
                        int accessoriesNum , double accessoriesSale , int accessoriesSaleData){
        this.setAccessoriesImg(accessoriesImg);
        this.setAccessoriesName(accessoriesName);
        this.setAccessoriesPrice(accessoriesPrice);
        this.setAccessoriesNum(accessoriesNum);
        this.setAccessoriesSale(accessoriesSale);
        this.setAccessoriesSaleData(accessoriesSaleData);
    }
    public DataBuyItem (){
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

    public int getAccessoriesImg() {
        return accessoriesImg;
    }

    public void setAccessoriesImg(int accessoriesImg) {
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
}
