package fafour.projectthaiboxing;

/**
 * Created by Fafour on 29/1/2560.
 */

public class DataBuyItem {
    public String accessoriesName;
    public int accessoriesPrice;
    public int accessoriesImg;
    public int accessoriesNum;

    public DataBuyItem (String accessoriesName,int accessoriesPrice , int accessoriesImg , int accessoriesNum){
        this.setAccessoriesImg(accessoriesImg);
        this.setAccessoriesName(accessoriesName);
        this.setAccessoriesPrice(accessoriesPrice);
        this.setAccessoriesNum(accessoriesNum);
    }
    public DataBuyItem (){
    }

    public String getAccessoriesName() {
        return accessoriesName;
    }

    public void setAccessoriesName(String accessoriesName) {
        this.accessoriesName = accessoriesName;
    }

    public int getAccessoriesPrice() {
        return accessoriesPrice;
    }

    public void setAccessoriesPrice(int accessoriesPrice) {
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
}
