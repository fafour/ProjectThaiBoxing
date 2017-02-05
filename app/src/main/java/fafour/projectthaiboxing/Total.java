package fafour.projectthaiboxing;

/**
 * Created by Fafour on 5/2/2560.
 */

public class Total {
    public static  int totalBuyItem(){
        int total = 0;
        for(int count=0; count < MainActivity.listBuy.size(); count++){
            total = total + (MainActivity.listBuy.get(count).accessoriesNum *
                    MainActivity.listBuy.get(count).accessoriesSale );
        }

        return total;
    }
}
