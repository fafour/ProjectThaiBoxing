package fafour.projectthaiboxing;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fafour.projectthaiboxing.util.IabHelper;
import fafour.projectthaiboxing.util.IabResult;
import fafour.projectthaiboxing.util.Inventory;
import fafour.projectthaiboxing.util.Purchase;

public class ScrollingLowerPartActivity extends AppCompatActivity  implements Skill1Adapter.CallbackInterface {
    private RecyclerView mRVList;
    private Skill1Adapter mAdapter;
    private static final int MY_REQUEST = 1001;;
    private IabHelper mHelper;
    String ITEM_SKU1 = "fafour.projectthaiboxing.buttonclick_1";
    String ITEM_SKU2 = "fafour.projectthaiboxing.buttonclick_2";
    String ITEM_SKU3 = "fafour.projectthaiboxing.buttonclick_3";
    String ITEM_SKU4 = "fafour.projectthaiboxing.buttonclick_4";
    String ITEM_SKU5 = "fafour.projectthaiboxing.buttonclick_5";
    String ITEM_SKU6 = "fafour.projectthaiboxing.buttonclick_6";
    List<DataSkill> data =new ArrayList<>();;
    int x = 0;
    int sku1 = 0,sku2 = 0,sku3 = 0,sku4 = 0,sku5 = 0,sku6 = 0;
    int sku1_1 =  R.drawable.icn_pass,
            sku2_1 =  R.drawable.icn_pass,
            sku3_1 =  R.drawable.icn_pass,
            sku4_1 =  R.drawable.icn_pass,
            sku5_1 =  R.drawable.icn_pass,
            sku6_1 =  R.drawable.icn_pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling_lower_part);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorStatus));
        }

        File mediaStorageDir1 = new File(Environment.getExternalStorageDirectory(),
                "Muay_thai/fafour.projectthaiboxing/data/ITEM_SKU1");

        if (!mediaStorageDir1.exists()) {
        }else {
            sku1 =1;
        }

        File mediaStorageDir2 = new File(Environment.getExternalStorageDirectory(),
                "Muay_thai/fafour.projectthaiboxing/data/ITEM_SKU2");

        if (!mediaStorageDir2.exists()) {
        }else {
            sku2 =1;
        }

        File mediaStorageDir3 = new File(Environment.getExternalStorageDirectory(),
                "Muay_thai/fafour.projectthaiboxing/data/ITEM_SKU3");

        if (!mediaStorageDir3.exists()) {
        }else {
            sku3 =1;
        }

        File mediaStorageDir4 = new File(Environment.getExternalStorageDirectory(),
                "Muay_thai/fafour.projectthaiboxing/data/ITEM_SKU4");

        if (!mediaStorageDir4.exists()) {
        }else {
            sku4 =1;
        }

        File mediaStorageDir5 = new File(Environment.getExternalStorageDirectory(),
                "Muay_thai/fafour.projectthaiboxing/data/ITEM_SKU5");

        if (!mediaStorageDir5.exists()) {
        }else {
            sku5 =1;
        }

        File mediaStorageDir6 = new File(Environment.getExternalStorageDirectory(),
                "Muay_thai/fafour.projectthaiboxing/data/ITEM_SKU6");

        if (!mediaStorageDir6.exists()) {
        }else {
            sku6 =1;
        }


        String base64EncodedPublicKey =
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiu0BwWezHyln+bx/U/g9PZSiLLQYVZnQfIybG8/1vQp8Oiok84S8w15Td5wly4+CIVrM6klUajsVc8RozyIBVFrMeK19NrvHB/sjxQONfVGLPHh0zr7xhBO/667wF+ez+ZtYvB9XDiGnE9x2zHUc8CcDRro112q0JC7bh6GCXyYHKZi+i/BZQC5HXG6Ik5gYLtRv7DL2RC0bx3YJcYx6CC7JMQThjxKppLfPLWurIko29uEBqcfFFviUAgt8SWcXbM03u3LwLs5zaZ2xbn9j/3UZSHtmhstZl/GPZJ8dADvKmU6pWn2FNpoyATaXdNClSCWxPMwDIOb8k9CJlyhPzwIDAQAB";


        mHelper = new IabHelper(this, base64EncodedPublicKey);

        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result ) {
                if (!result.isSuccess()) {

                }else{
                    mHelper.queryInventoryAsync(mGotInventoryListener);
                }

            }
        });



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(mHelper != null && !mHelper.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    //----------------------------------------------------------------------------------------------------------------------------

    private IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
            Log.d("PAY", "Query inventory finished.");

            // Have we been disposed of in the meantime? If so, quit.
            if (mHelper == null) return;

            if (sku1 == 1) {
                sku1_1 =  R.drawable.next_icon;

            }else {

            }

            if (sku2 == 1) {
                sku2_1 =  R.drawable.next_icon;

            }else {

            }

            if (sku3 == 1) {;
                sku3_1 =  R.drawable.next_icon;

            }else {

            }

            if (sku4 == 1) {;
                sku4_1 =  R.drawable.next_icon;

            }else {

            }

            if (sku5 == 1) {;
                sku5_1 =  R.drawable.next_icon;

            }else {

            }

            if (sku6 == 1) {;
                sku6_1 =  R.drawable.next_icon;

            }else {


            }


            int [] imgs  = {
                    R.drawable.next_icon,
                    R.drawable.next_icon,
                    R.drawable.next_icon,
                    R.drawable.next_icon,
                    R.drawable.next_icon,
                    R.drawable.next_icon,
                    sku1_1,
                    sku2_1,
                    sku3_1,
                    sku4_1,
                    sku5_1,
                    sku6_1,
            };

            String[] SkillName = {
                    "Bid Hnang Na-ka",
                    "Ai-Nhao Tank Krit",
                    "Yok Kao Pa-su-main",
                    "Mon Yan Lhak",
                    "Jara-kay Fad-hnag",
                    "Wi-run Hok Kap",
                    "Hak Kor Ae-ra-wan",
                    "Mod Ba-dan",
                    "Tey Kwad Lan",
                    "Pra-jao-ta Nang Tan",
                    "Kwang Leaw Lang",
                    "Ba-ta Loop Pak"

            };

            int [] SkillImg  = {
                    R.drawable.img_bid_hnang_na_ka,
                    R.drawable.img_ai_nhao_tank_krit,
                    R.drawable.img_yok_kao_pa_su_main,
                    R.drawable.img_mon_yan_lhak,
                    R.drawable.img_jara_kay_fad_hnag,
                    R.drawable.img_wi_run_hok_kap,
                    R.drawable.img_hak_ko_ae_ra_wan,
                    R.drawable.img_mod_ba_dan,
                    R.drawable.img_tey_kwad_lan,
                    R.drawable.img_pra_jao_ta_nang_tan,
                    R.drawable.img_kwang_leaw_lang,
                    R.drawable.img_ba_ta_loop_pak
            };


            for(int count=0; count < SkillName.length; count++){
                DataSkill skillData = new DataSkill();
                skillData.skillName = SkillName[count];
                skillData.skillImg = SkillImg[count];
                skillData.imgLock = imgs[count];
                data.add(skillData);

            }

            mRVList = (RecyclerView) findViewById(R.id.VideoList);
            mAdapter = new Skill1Adapter(ScrollingLowerPartActivity.this, data);
            mRVList.setAdapter(mAdapter);
            mRVList.setLayoutManager(new LinearLayoutManager(ScrollingLowerPartActivity.this));
        }
    };

    //----------------------------------------------------------------------------------------------------------------------------
    private  IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener1
            = new IabHelper.OnIabPurchaseFinishedListener() {
        public void onIabPurchaseFinished(IabResult result,
                                          Purchase purchase)
        {
            if (result.isFailure()) {
                // Handle error
                return;
            }
            if (purchase.getSku().equals(ITEM_SKU1)) {
                consumeItem();
//                buyButton.setEnabled(false);
            }
            if (purchase.getSku().equals(ITEM_SKU2)) {
                consumeItem();
//                buyButton.setEnabled(false);
            }

            if (purchase.getSku().equals(ITEM_SKU3)) {
                consumeItem();
//                buyButton.setEnabled(false);
            }

            if (purchase.getSku().equals(ITEM_SKU4)) {
                consumeItem();
//                buyButton.setEnabled(false);
            }

            if (purchase.getSku().equals(ITEM_SKU5)) {
                consumeItem();
//                buyButton.setEnabled(false);
            }

            if (purchase.getSku().equals(ITEM_SKU6)) {
                consumeItem();
//                buyButton.setEnabled(false);
            }


        }
    };




    //------------------------------------------------------------------------------------------------------------------------------

    private  void consumeItem() {
        mHelper.queryInventoryAsync(mReceivedInventoryListener);
    }


    //------------------------------------------------------------------------------------------------------------------------------

    private IabHelper.QueryInventoryFinishedListener mReceivedInventoryListener
            = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result,
                                             Inventory inventory) {

            if (result.isFailure()) {

            } else {
                if (inventory.hasPurchase(ITEM_SKU1)) {
                    mHelper.consumeAsync(inventory.getPurchase(ITEM_SKU1),
                            mConsumeFinishedListener);


                }
                if(inventory.hasPurchase(ITEM_SKU2)){
                    mHelper.consumeAsync(inventory.getPurchase(ITEM_SKU2),
                            mConsumeFinishedListener);
                }
                if(inventory.hasPurchase(ITEM_SKU3)){
                    mHelper.consumeAsync(inventory.getPurchase(ITEM_SKU3),
                            mConsumeFinishedListener);
                }

                if(inventory.hasPurchase(ITEM_SKU4)){
                    mHelper.consumeAsync(inventory.getPurchase(ITEM_SKU4),
                            mConsumeFinishedListener);
                }

                if(inventory.hasPurchase(ITEM_SKU5)){
                    mHelper.consumeAsync(inventory.getPurchase(ITEM_SKU5),
                            mConsumeFinishedListener);
                }

                if(inventory.hasPurchase(ITEM_SKU6)){
                    mHelper.consumeAsync(inventory.getPurchase(ITEM_SKU6),
                            mConsumeFinishedListener);
                }


            }
        }
    };

    //-----------------------------------------------------------------------------------------------------------------------------
    IabHelper.OnConsumeFinishedListener mConsumeFinishedListener =
            new IabHelper.OnConsumeFinishedListener() {
                public void onConsumeFinished(Purchase purchase,
                                              IabResult result) {

                    if (result.isSuccess()) {
                        if(purchase.getSku().equals(ITEM_SKU1)){
                            int gif = R.drawable.gif_hak_ko_ae_ra_wan;
                            int mp3 = R.raw.hugkorerawan_thai;
                            intentData("Hak Kor Ae-ra-wan",gif,mp3);
                            updateData(6,"Hak Kor Ae-ra-wan",R.drawable.img_hak_ko_ae_ra_wan);
                            sku1 = 1;

                            File mediaStorageDir = new File(Environment.getExternalStorageDirectory(),
                                    "Muay_thai/fafour.projectthaiboxing/data/ITEM_SKU1");
                            if (!mediaStorageDir.exists()) {
                                if (!mediaStorageDir.mkdirs()) {

                                }
                            }

                        }
                        if(purchase.getSku().equals(ITEM_SKU2)){
                            int gif = R.drawable.gif_mod_ba_dan;
                            int mp3 = R.raw.modbadan_thai;
                            intentData("Mod Ba-dan",gif,mp3);
                            updateData(7,"Mod Ba-dan",R.drawable.img_mod_ba_dan);
                            sku2 = 1;

                            File mediaStorageDir = new File(Environment.getExternalStorageDirectory(),
                                    "Muay_thai/fafour.projectthaiboxing/data/ITEM_SKU2");
                            if (!mediaStorageDir.exists()) {
                                if (!mediaStorageDir.mkdirs()) {

                                }
                            }

                        }
                        if(purchase.getSku().equals(ITEM_SKU3)){
                            int gif = R.drawable.gif_tey_kwad_lan;
                            int mp3 = R.raw.taykwadlan_thai;
                            intentData("Tey Kwad Lan",gif,mp3);
                            updateData(8,"Tey Kwad Lan",R.drawable.img_tey_kwad_lan);
                            sku3 = 1;

                            File mediaStorageDir = new File(Environment.getExternalStorageDirectory(),
                                    "Muay_thai/fafour.projectthaiboxing/data/ITEM_SKU3");
                            if (!mediaStorageDir.exists()) {
                                if (!mediaStorageDir.mkdirs()) {

                                }
                            }

                        }
                        if(purchase.getSku().equals(ITEM_SKU4)){
                            int gif = R.drawable.gif_pra_jao_ta_nang_tan;
                            int mp3 = R.raw.prajaotanangtan_thai;
                            intentData("Pra-jao-ta Nang Tan",gif,mp3);
                            updateData(9,"Pra-jao-ta Nang Tan",R.drawable.img_pra_jao_ta_nang_tan);
                            sku4 = 1;

                            File mediaStorageDir = new File(Environment.getExternalStorageDirectory(),
                                    "Muay_thai/fafour.projectthaiboxing/data/ITEM_SKU4");
                            if (!mediaStorageDir.exists()) {
                                if (!mediaStorageDir.mkdirs()) {

                                }
                            }

                        }
                        if(purchase.getSku().equals(ITEM_SKU5)){
                            int gif = R.drawable.gif_kwang_leaw_lang;
                            int mp3 = R.raw.kwangleawlang_thai;
                            intentData("Kwang Leaw Lang",gif,mp3);
                            updateData(10,"Kwang Leaw Lang",R.drawable.img_kwang_leaw_lang);
                            sku5 = 1;

                            File mediaStorageDir = new File(Environment.getExternalStorageDirectory(),
                                    "Muay_thai/fafour.projectthaiboxing/data/ITEM_SKU5");
                            if (!mediaStorageDir.exists()) {
                                if (!mediaStorageDir.mkdirs()) {

                                }
                            }


                        }
                        if(purchase.getSku().equals(ITEM_SKU6)){
                            int gif = R.drawable.gif_ba_ta_loop_pak;
                            int mp3 = R.raw.batalooppak_thai;
                            intentData("Ba-ta Loop Pak",gif,mp3);
                            updateData(11,"Ba-ta Loop Pak",R.drawable.img_ba_ta_loop_pak);
                            sku6 = 1;

                            File mediaStorageDir = new File(Environment.getExternalStorageDirectory(),
                                    "Muay_thai/fafour.projectthaiboxing/data/ITEM_SKU6");
                            if (!mediaStorageDir.exists()) {
                                if (!mediaStorageDir.mkdirs()) {

                                }
                            }


                        }


                    } else {
                        // handle error
                    }
                }
            };

    //------------------------------------------------------------------------------------------------------------------------------

    @Override
    public void onHandleSelection(int position, String textName ,int img) {
        if(position == 0){
            int gif = R.drawable.gif_bid_hnang_na_ka;
            int mp3 = R.raw.bidhangnaka_thai;
            intentData(textName,gif,mp3);
        }
        else if(position == 1){
            int gif = R.drawable.gif_ai_nhao_tank_krit;
            int mp3 = R.raw.ainaotankkrich_thai;
            intentData(textName,gif,mp3);
        }
        else if(position == 2){
            int gif = R.drawable.gif_yok_kao_pa_su_main;
            int mp3 = R.raw.yokkaoprasumain_thai;
            intentData(textName,gif,mp3);
        }
        else if(position == 3){
            int gif = R.drawable.gif_mon_yan_lhak;
            int mp3 = R.raw.monyanlak_thai;
            intentData(textName,gif,mp3);
        }
        else if(position == 4){
            int gif = R.drawable.gif_jara_kay_fad_hnag;
            int mp3 = R.raw.jerakeyfadhang_thai;
            intentData(textName,gif,mp3);
        }
        else if(position == 5){
            int gif = R.drawable.gif_wi_run_hok_kap;
            int mp3 = R.raw.viroonhogkab_thai;
            intentData(textName,gif,mp3);
        }
        else if(position == 6) {
            if(sku1 != 0 ){
                int gif = R.drawable.gif_hak_ko_ae_ra_wan;
                int mp3 = R.raw.hugkorerawan_thai;
                intentData("Hak Kor Ae-ra-wan",gif,mp3);
            }else {
                mHelper.launchPurchaseFlow(this, ITEM_SKU1, 1002,
                        mPurchaseFinishedListener1, "mypurchasetoken1");
            }
        }
        else if(position == 7) {
            if(sku2 != 0 ){
                int gif = R.drawable.gif_mod_ba_dan;
                int mp3 = R.raw.modbadan_thai;
                intentData("Mod Ba-dan",gif,mp3);
            }else {
                mHelper.launchPurchaseFlow(this, ITEM_SKU2, 1002,
                        mPurchaseFinishedListener1, "mypurchasetoken2");

            }
        }
        else if(position == 8) {
            if(sku3 != 0 ){
                int gif = R.drawable.gif_tey_kwad_lan;
                int mp3 = R.raw.taykwadlan_thai;
                intentData("Tey Kwad Lan",gif,mp3);
            }else {
                mHelper.launchPurchaseFlow(this, ITEM_SKU3, 1002,
                        mPurchaseFinishedListener1, "mypurchasetoken3");
            }
        }
        else if(position == 9) {
            if(sku4 != 0 ){
                int gif = R.drawable.gif_pra_jao_ta_nang_tan;
                int mp3 = R.raw.prajaotanangtan_thai;
                intentData("Pra-jao-ta Nang Tan",gif,mp3);
            }else {
                mHelper.launchPurchaseFlow(this, ITEM_SKU4, 1002,
                        mPurchaseFinishedListener1, "mypurchasetoken4");

            }
        }
        else if(position == 10) {
            if(sku5 != 0 ){
                int gif = R.drawable.gif_kwang_leaw_lang;
                int mp3 = R.raw.kwangleawlang_thai;
                intentData("Kwang Leaw Lang",gif,mp3);
            }else {
                mHelper.launchPurchaseFlow(this, ITEM_SKU5, 1002,
                        mPurchaseFinishedListener1, "mypurchasetoken5");
            }
        }
        else if(position == 11) {
            if(sku6 != 0 ){
                int gif = R.drawable.gif_ba_ta_loop_pak;
                int mp3 = R.raw.batalooppak_thai;
                intentData("Ba-ta Loop Pak",gif,mp3);
            }else {
                mHelper.launchPurchaseFlow(this, ITEM_SKU6, 1002,
                        mPurchaseFinishedListener1, "mypurchasetoken6");
            }
        }


    }

    //----------------------------------------------------------------------------------------------------------------
    public void intentData(String name, int gif, int mp3){
        Intent secondActivity = new Intent(ScrollingLowerPartActivity.this, ShowSkillActivity.class);
        secondActivity.putExtra("name", name);
        secondActivity.putExtra("gif", gif);
        secondActivity.putExtra("mp3", mp3);
        startActivityForResult(secondActivity, MY_REQUEST);

    }
    public void updateData(int position, String textName ,int img ){
        DataSkill skillData = new DataSkill();
        skillData.skillName = textName;
        skillData.skillImg = img;
        skillData.imgLock = R.drawable.next_icon;
        data.set(position,skillData);
        mAdapter = new Skill1Adapter(ScrollingLowerPartActivity.this, data);
        mRVList.setAdapter(mAdapter);
        mRVList.scrollToPosition(position);
        mAdapter.notifyDataSetChanged();
    }

}
