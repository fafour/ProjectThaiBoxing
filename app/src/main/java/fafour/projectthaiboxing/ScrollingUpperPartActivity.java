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

public class ScrollingUpperPartActivity extends AppCompatActivity implements SkillAdapter.CallbackInterface {
    private RecyclerView mRVList;
    private SkillAdapter mAdapter;
    private static final int MY_REQUEST = 1001;
    private IabHelper mHelper;
    String ITEM_SKU7 = "fafour.projectthaiboxing.buttonclick_7";
    String ITEM_SKU8 = "fafour.projectthaiboxing.buttonclick_8";
    String ITEM_SKU9 = "fafour.projectthaiboxing.buttonclick_9";
    String ITEM_SKU10 = "fafour.projectthaiboxing.buttonclick_10";
    String ITEM_SKU11 = "fafour.projectthaiboxing.buttonclick_11";
    String ITEM_SKU12 = "fafour.projectthaiboxing.buttonclick_12";
    String ITEM_SKU13 = "fafour.projectthaiboxing.buttonclick_13";
    String ITEM_SKU14 = "fafour.projectthaiboxing.buttonclick_14";
    String ITEM_SKU15 = "fafour.projectthaiboxing.buttonclick_15";
    List<DataSkill> data=new ArrayList<>();
    int sku7 = 0,sku8 = 0,sku9 = 0,sku10 = 0,sku11 = 0,sku12 = 0,sku13 = 0,sku14 = 0,sku15 = 0;
    int sku7_1 =  R.drawable.icn_pass,
            sku8_1 =  R.drawable.icn_pass,
            sku9_1 =  R.drawable.icn_pass,
            sku10_1 =  R.drawable.icn_pass,
            sku11_1 =  R.drawable.icn_pass,
            sku12_1 =  R.drawable.icn_pass,
            sku13_1 =  R.drawable.icn_pass,
            sku14_1 =  R.drawable.icn_pass,
            sku15_1 =  R.drawable.icn_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling_upper_part);
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

        File mediaStorageDir7 = new File(Environment.getExternalStorageDirectory(),
                "Muay_thai/fafour.projectthaiboxing/data/ITEM_SKU7");

        if (!mediaStorageDir7.exists()) {
        }else {
            sku7 =1;
        }

        File mediaStorageDir8 = new File(Environment.getExternalStorageDirectory(),
                "Muay_thai/fafour.projectthaiboxing/data/ITEM_SKU8");

        if (!mediaStorageDir8.exists()) {
        }else {
            sku8 =1;
        }

        File mediaStorageDir9 = new File(Environment.getExternalStorageDirectory(),
                "Muay_thai/fafour.projectthaiboxing/data/ITEM_SKU9");

        if (!mediaStorageDir9.exists()) {
        }else {
            sku9 =1;
        }

        File mediaStorageDir10 = new File(Environment.getExternalStorageDirectory(),
                "Muay_thai/fafour.projectthaiboxing/data/ITEM_SKU10");

        if (!mediaStorageDir10.exists()) {
        }else {
            sku10 =1;
        }

        File mediaStorageDir11 = new File(Environment.getExternalStorageDirectory(),
                "Muay_thai/fafour.projectthaiboxing/data/ITEM_SKU11");

        if (!mediaStorageDir11.exists()) {
        }else {
            sku11 =1;
        }

        File mediaStorageDir12 = new File(Environment.getExternalStorageDirectory(),
                "Muay_thai/fafour.projectthaiboxing/data/ITEM_SKU12");

        if (!mediaStorageDir12.exists()) {
        }else {
            sku12 =1;
        }


        File mediaStorageDir13 = new File(Environment.getExternalStorageDirectory(),
                "Muay_thai/fafour.projectthaiboxing/data/ITEM_SKU13");

        if (!mediaStorageDir13.exists()) {
        }else {
            sku13 =1;
        }


        File mediaStorageDir14 = new File(Environment.getExternalStorageDirectory(),
                "Muay_thai/fafour.projectthaiboxing/data/ITEM_SKU14");

        if (!mediaStorageDir14.exists()) {
        }else {
            sku14 =1;
        }


        File mediaStorageDir15 = new File(Environment.getExternalStorageDirectory(),
                "Muay_thai/fafour.projectthaiboxing/data/ITEM_SKU15");

        if (!mediaStorageDir15.exists()) {
        }else {
            sku15 =1;
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
        if (!mHelper.handleActivityResult(requestCode,
                resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    //----------------------------------------------------------------------------------------------------------------------------

    private IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
            Log.d("PAY", "Query inventory finished.");

            // Have we been disposed of in the meantime? If so, quit.
            if (mHelper == null) return;

            if (sku7 == 1) {
                sku7_1 =  R.drawable.next_icon;

            }else {

            }

            if (sku8 == 1) {
                sku8_1 =  R.drawable.next_icon;

            }else {

            }

            if (sku9 == 1) {;
                sku9_1 =  R.drawable.next_icon;

            }else {

            }

            if (sku10 == 1) {;
                sku10_1 =  R.drawable.next_icon;

            }else {

            }

            if (sku11 == 1) {;
                sku11_1 =  R.drawable.next_icon;

            }else {

            }

            if (sku12 == 1) {;
                sku12_1 =  R.drawable.next_icon;

            }else {

            }

            if (sku13 == 1) {;
                sku13_1 =  R.drawable.next_icon;

            }else {

            }

            if (sku14 == 1) {;
                sku14_1 =  R.drawable.next_icon;

            }else {

            }

            if (sku15 == 1) {;
                sku15_1 =  R.drawable.next_icon;

            }else {

            }


            String[] SkillName = {
                    "Sa Lap Fun Pla",
                    "Cha-Wa Sad Hok",
                    "Tad Ma-la",
                    "Pak Look-Toy",
                    "Hak Ngong Ai-ra",
                    "Pak Sa Weg Rang",
                    "Dub Cha-la-wa",
                    "Kun Yak Jab Ling",
                    "Mon-to Nang Tan",
                    "Ha-nu-man Ta-waii Van",
                    "Taii Kao Pra-su-main",
                    "Ae-ra-wan Soey Nga",
                    "Fan Look Boub",
                    "Sup Hua Mad-cha",
                    "Su-creep Ton Tan Rang",
                    "Ka-mee Kum Sao",
                    "Ta Tay Kum Fak",
                    "Bhuma Rum Kwan"
            };


            int [] SkillImg  = {
                    R.drawable.img_sa_lap_fun_pla,
                    R.drawable.img_cha_wa_sad_hok,
                    R.drawable.img_tad_ma_la,
                    R.drawable.img_pak_look_toy,
                    R.drawable.img_hak_ngong_ai_ra,
                    R.drawable.img_pak_sa_weg_rang,
                    R.drawable.img_dub_cha_la_wa,
                    R.drawable.img_kun_yak_jab_ling,
                    R.drawable.img_mon_to_nang_tan,
                    R.drawable.img_ha_nu_man_ta_waii_van,
                    R.drawable.img_taii_kao_pra_su_main,
                    R.drawable.img_ae_ra_wan_soey_nga,
                    R.drawable.img_fan_look_boub,
                    R.drawable.img_sup_hua_mad_cha,
                    R.drawable.img_su_creep_ton_tan_rang,
                    R.drawable.img_ka_mee_kum_sao,
                    R.drawable.img_ta_tay_kum_fak,
                    R.drawable.img_bhuma_rum_kwan
            };


            int [] img  = {
                    R.drawable.next_icon,
                    R.drawable.next_icon,
                    R.drawable.next_icon,
                    R.drawable.next_icon,
                    R.drawable.next_icon,
                    R.drawable.next_icon,
                    R.drawable.next_icon,
                    R.drawable.next_icon,
                    R.drawable.next_icon,
                    sku7_1 ,
                    sku8_1 ,
                    sku9_1 ,
                    sku10_1 ,
                    sku11_1 ,
                    sku12_1 ,
                    sku13_1 ,
                    sku14_1 ,
                    sku15_1
            };



            for(int count=0; count < SkillName.length; count++){
                DataSkill skillData = new DataSkill();

                skillData.skillName = SkillName[count];
                skillData.skillImg = SkillImg[count];
                skillData.imgLock = img[count];
                data.add(skillData);

            }

            // Setup and Handover data to recyclerview
            mRVList = (RecyclerView) findViewById(R.id.VideoList);
            mAdapter = new SkillAdapter(ScrollingUpperPartActivity.this, data);
            mRVList.setAdapter(mAdapter);
            mRVList.setLayoutManager(new LinearLayoutManager(ScrollingUpperPartActivity.this));
        }
    };


    private  IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener1
            = new IabHelper.OnIabPurchaseFinishedListener() {
        public void onIabPurchaseFinished(IabResult result,
                                          Purchase purchase)
        {
            if (result.isFailure()) {
                // Handle error
                return;
            }
            else if (purchase.getSku().equals(ITEM_SKU7)) {
                consumeItem();
//                buyButton.setEnabled(false);
            }
            else if (purchase.getSku().equals(ITEM_SKU8)) {
                consumeItem();
//                buyButton.setEnabled(false);
            }
            else if (purchase.getSku().equals(ITEM_SKU9)) {
                consumeItem();
//                buyButton.setEnabled(false);
            }
            else if (purchase.getSku().equals(ITEM_SKU10)) {
                consumeItem();
//                buyButton.setEnabled(false);
            }
            else if (purchase.getSku().equals(ITEM_SKU11)) {
                consumeItem();
//                buyButton.setEnabled(false);
            }
            else if (purchase.getSku().equals(ITEM_SKU12)) {
                consumeItem();
//                buyButton.setEnabled(false);
            }
            else if (purchase.getSku().equals(ITEM_SKU13)) {
                consumeItem();
//                buyButton.setEnabled(false);
            }
            else if (purchase.getSku().equals(ITEM_SKU14)) {
                consumeItem();
//                buyButton.setEnabled(false);
            }
            else if (purchase.getSku().equals(ITEM_SKU15)) {
                consumeItem();
//                buyButton.setEnabled(false);
            }

        }
    };
    public void consumeItem() {
        mHelper.queryInventoryAsync(mReceivedInventoryListener);
    }

    private  IabHelper.QueryInventoryFinishedListener mReceivedInventoryListener
            = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result,
                                             Inventory inventory) {

            if (result.isFailure()) {
                // Handle failure
            } else  if(inventory.hasPurchase(ITEM_SKU7)){
                mHelper.consumeAsync(inventory.getPurchase(ITEM_SKU7),
                        mConsumeFinishedListener);
            } else  if(inventory.hasPurchase(ITEM_SKU8)){
                mHelper.consumeAsync(inventory.getPurchase(ITEM_SKU8),
                        mConsumeFinishedListener);
            } else  if(inventory.hasPurchase(ITEM_SKU9)){
                mHelper.consumeAsync(inventory.getPurchase(ITEM_SKU9),
                        mConsumeFinishedListener);
            } else  if(inventory.hasPurchase(ITEM_SKU10)){
                mHelper.consumeAsync(inventory.getPurchase(ITEM_SKU10),
                        mConsumeFinishedListener);
            } else  if(inventory.hasPurchase(ITEM_SKU11)){
                mHelper.consumeAsync(inventory.getPurchase(ITEM_SKU11),
                        mConsumeFinishedListener);
            } else  if(inventory.hasPurchase(ITEM_SKU12)){
                mHelper.consumeAsync(inventory.getPurchase(ITEM_SKU12),
                        mConsumeFinishedListener);
            } else  if(inventory.hasPurchase(ITEM_SKU13)){
                mHelper.consumeAsync(inventory.getPurchase(ITEM_SKU13),
                        mConsumeFinishedListener);
            } else  if(inventory.hasPurchase(ITEM_SKU14)){
                mHelper.consumeAsync(inventory.getPurchase(ITEM_SKU14),
                        mConsumeFinishedListener);
            } else  if(inventory.hasPurchase(ITEM_SKU15)){
                mHelper.consumeAsync(inventory.getPurchase(ITEM_SKU15),
                        mConsumeFinishedListener);
            }

        }
    };

    private IabHelper.OnConsumeFinishedListener mConsumeFinishedListener =
            new IabHelper.OnConsumeFinishedListener() {
                public void onConsumeFinished(Purchase purchase,
                                              IabResult result) {

                    if (result.isSuccess()) {
                        if(purchase.getSku().equals(ITEM_SKU7)){
                            int gif = R.drawable.gif_ha_nu_man_ta_waii_van;
                            int mp3 = R.raw.hanumantawaiivan_thai;
                            intentData("Ha-nu-man Ta-waii Van",gif,mp3);
                            updateData(9,"Ha-nu-man Ta-waii Van",R.drawable.img_ha_nu_man_ta_waii_van);

                            sku7 = 1;

                            File mediaStorageDir = new File(Environment.getExternalStorageDirectory(),
                                    "Muay_thai/fafour.projectthaiboxing/data/ITEM_SKU7");
                            if (!mediaStorageDir.exists()) {
                                if (!mediaStorageDir.mkdirs()) {

                                }
                            }


                        } else if(purchase.getSku().equals(ITEM_SKU8)){
                            int gif = R.drawable.gif_taii_kao_pra_su_main;
                            int mp3 = R.raw.taiikaoprasumain_thai;
                            intentData("Taii Kao Pra-su-main",gif,mp3);
                            updateData(10,"Taii Kao Pra-su-main",R.drawable.img_taii_kao_pra_su_main);

                            sku8 = 1;

                            File mediaStorageDir = new File(Environment.getExternalStorageDirectory(),
                                    "Muay_thai/fafour.projectthaiboxing/data/ITEM_SKU8");
                            if (!mediaStorageDir.exists()) {
                                if (!mediaStorageDir.mkdirs()) {

                                }
                            }

                        } else if(purchase.getSku().equals(ITEM_SKU9)){
                            int gif = R.drawable.gif_ae_ra_wan_soey_nga;
                            int mp3 = R.raw.aerawansoeynga_thai;
                            intentData("Ae-ra-wan Soey Nga",gif,mp3);
                            updateData(11,"Ae-ra-wan Soey Nga",R.drawable.img_ae_ra_wan_soey_nga);

                            sku9 = 1;

                            File mediaStorageDir = new File(Environment.getExternalStorageDirectory(),
                                    "Muay_thai/fafour.projectthaiboxing/data/ITEM_SKU9");
                            if (!mediaStorageDir.exists()) {
                                if (!mediaStorageDir.mkdirs()) {

                                }
                            }


                        } else if(purchase.getSku().equals(ITEM_SKU10)){
                            int gif = R.drawable.gif_fan_look_boub;
                            int mp3 = R.raw.fanlookboub_thai;
                            intentData("Fan Look Boub",gif,mp3);
                            updateData(12,"Fan Look Boub",R.drawable.img_fan_look_boub);

                            sku10 = 1;

                            File mediaStorageDir = new File(Environment.getExternalStorageDirectory(),
                                    "Muay_thai/fafour.projectthaiboxing/data/ITEM_SKU10");
                            if (!mediaStorageDir.exists()) {
                                if (!mediaStorageDir.mkdirs()) {

                                }
                            }

                        } else if(purchase.getSku().equals(ITEM_SKU11)){
                            int gif = R.drawable.gif_sup_hua_mad_cha;
                            int mp3 = R.raw.subhuamadcha_thai;
                            intentData("Sup Hua Mad-cha",gif,mp3);
                            updateData(13,"Sup Hua Mad-cha",R.drawable.img_sup_hua_mad_cha);

                            sku11 = 1;

                            File mediaStorageDir = new File(Environment.getExternalStorageDirectory(),
                                    "Muay_thai/fafour.projectthaiboxing/data/ITEM_SKU11");
                            if (!mediaStorageDir.exists()) {
                                if (!mediaStorageDir.mkdirs()) {

                                }
                            }

                        } else if(purchase.getSku().equals(ITEM_SKU12)){
                            int gif = R.drawable.gif_su_creep_ton_tan_rang;
                            int mp3 = R.raw.sucreeptontanrang_thai;
                            intentData("Su-creep Ton Tan Rang",gif,mp3);
                            updateData(14,"Su-creep Ton Tan Rang",R.drawable.img_su_creep_ton_tan_rang);

                            sku12 = 1;

                            File mediaStorageDir = new File(Environment.getExternalStorageDirectory(),
                                    "Muay_thai/fafour.projectthaiboxing/data/ITEM_SKU12");
                            if (!mediaStorageDir.exists()) {
                                if (!mediaStorageDir.mkdirs()) {

                                }
                            }

                        } else if(purchase.getSku().equals(ITEM_SKU13)){
                            int gif = R.drawable.gif_ka_mee_kum_sao;
                            int mp3 = R.raw.kameekumsao_thai;
                            intentData("Ka-mee Kum Sao",gif,mp3);
                            updateData(15,"Ka-mee Kum Sao",R.drawable.img_ka_mee_kum_sao);

                            sku13 = 1;

                            File mediaStorageDir = new File(Environment.getExternalStorageDirectory(),
                                    "Muay_thai/fafour.projectthaiboxing/data/ITEM_SKU13");
                            if (!mediaStorageDir.exists()) {
                                if (!mediaStorageDir.mkdirs()) {

                                }
                            }

                        } else if(purchase.getSku().equals(ITEM_SKU14)){
                            int gif = R.drawable.gif_ta_tay_kum_fak;
                            int mp3 = R.raw.tateykumfak_thai;
                            intentData("Ta Tay Kum Fak",gif,mp3);
                            updateData(16,"Ta Tay Kum Fak",R.drawable.img_ta_tay_kum_fak);

                            sku14 = 1;

                            File mediaStorageDir = new File(Environment.getExternalStorageDirectory(),
                                    "Muay_thai/fafour.projectthaiboxing/data/ITEM_SKU14");
                            if (!mediaStorageDir.exists()) {
                                if (!mediaStorageDir.mkdirs()) {

                                }
                            }


                        } else if(purchase.getSku().equals(ITEM_SKU15)){
                            int gif = R.drawable.gif_bhuma_rum_kwan;
                            int mp3 = R.raw.bhumarumkwan_thai;
                            intentData("Bhuma Rum Kwan",gif,mp3);
                            updateData(17,"Bhuma Rum Kwan",R.drawable.img_bhuma_rum_kwan);

                            sku15 = 1;

                            File mediaStorageDir = new File(Environment.getExternalStorageDirectory(),
                                    "Muay_thai/fafour.projectthaiboxing/data/ITEM_SKU15");
                            if (!mediaStorageDir.exists()) {
                                if (!mediaStorageDir.mkdirs()) {

                                }
                            }

                        }
//
                    }
                }
            };


    @Override
    public void onHandleSelection(int position, String textName ,int img) {
        if(position == 0){
            int gif = R.drawable.gif_sa_lap_fun_pla;
            int mp3 = R.raw.salaufunpla_thai;
            intentData(textName,gif,mp3);
        }
        else if(position == 1){
            int gif = R.drawable.gif_cha_wa_sad_hok;
            int mp3 = R.raw.chawasadhok_thai;
            intentData(textName,gif,mp3);
        }
        else if(position == 2){
            int gif = R.drawable.gif_tad_ma_la;
            int mp3 = R.raw.tadmala_thai;
            intentData(textName,gif,mp3);

        }
        else if(position == 3){
            int gif = R.drawable.gif_pak_look_toy;
            int mp3 = R.raw.paklooktoy_thai;
            intentData(textName,gif,mp3);

        }
        else if(position == 4){
            int gif = R.drawable.gif_hak_ngong_ai_ra;
            int mp3 = R.raw.hugnungaiyara_thai;
            intentData(textName,gif,mp3);

        }
        else if(position == 5){
            int gif = R.drawable.gif_pak_sa_weg_rang;
            int mp3 = R.raw.paksawekrang_thai;
            intentData(textName,gif,mp3);
        }
        else if(position == 6){
            int gif = R.drawable.gif_dub_cha_la_wa;
            int mp3 = R.raw.dubchalawa_thai;
            intentData(textName,gif,mp3);
        }
        else if(position == 7){
            int gif = R.drawable.gif_kun_yak_jab_ling;
            int mp3 = R.raw.kunyakjabling_thai;
            intentData(textName,gif,mp3);

        }
        else if(position == 8){
            int gif = R.drawable.gif_mon_to_nang_tan;
            int mp3 = R.raw.montonangtan_thai;
            intentData(textName,gif,mp3);

        }

        else if(position == 9) {
            if(sku7 != 0 ){
                int gif = R.drawable.gif_ha_nu_man_ta_waii_van;
                int mp3 = R.raw.hanumantawaiivan_thai;
                intentData("Ha-nu-man Ta-waii Van",gif,mp3);

            }else {
                mHelper.launchPurchaseFlow(this, ITEM_SKU7, 1002,
                        mPurchaseFinishedListener1, "mypurchasetoken");
            }

        }else if(position == 10) {
            if(sku8 != 0 ){
                int gif = R.drawable.gif_taii_kao_pra_su_main;
                int mp3 = R.raw.taiikaoprasumain_thai;
                intentData("Taii Kao Pra-su-main",gif,mp3);
            }else {
                mHelper.launchPurchaseFlow(this, ITEM_SKU8, 1002,
                        mPurchaseFinishedListener1, "mypurchasetoken");
            }
        }else if(position == 11) {
            if(sku9 != 0 ){
                int gif = R.drawable.gif_ae_ra_wan_soey_nga;
                int mp3 = R.raw.aerawansoeynga_thai;
                intentData("Ae-ra-wan Soey Nga",gif,mp3);
            }else {
                mHelper.launchPurchaseFlow(this, ITEM_SKU9, 1002,
                        mPurchaseFinishedListener1, "mypurchasetoken");
            }

        }else if(position == 12) {
            if(sku10 != 0 ){
                int gif = R.drawable.gif_fan_look_boub;
                int mp3 = R.raw.fanlookboub_thai;
                intentData("Fan Look Boub",gif,mp3);

            }else {
                mHelper.launchPurchaseFlow(this, ITEM_SKU10, 1002,
                        mPurchaseFinishedListener1, "mypurchasetoken");
            }

        }else if(position == 13) {
            if(sku11 != 0 ){
                int gif = R.drawable.gif_sup_hua_mad_cha;
                int mp3 = R.raw.subhuamadcha_thai;
                intentData("Sup Hua Mad-cha",gif,mp3);

            }else {
                mHelper.launchPurchaseFlow(this, ITEM_SKU11, 1002,
                        mPurchaseFinishedListener1, "mypurchasetoken");
            }

        }else if(position == 14) {
            if(sku12 != 0 ){
                int gif = R.drawable.gif_su_creep_ton_tan_rang;
                int mp3 = R.raw.sucreeptontanrang_thai;
                intentData("Su-creep Ton Tan Rang",gif,mp3);

            }else {
                mHelper.launchPurchaseFlow(this, ITEM_SKU12, 1002,
                        mPurchaseFinishedListener1, "mypurchasetoken");
            }

        }else if(position == 15) {
            if(sku13 != 0 ){
                int gif = R.drawable.gif_ka_mee_kum_sao;
                int mp3 = R.raw.kameekumsao_thai;
                intentData("Ka-mee Kum Sao",gif,mp3);

            }else {
                mHelper.launchPurchaseFlow(this, ITEM_SKU13, 1002,
                        mPurchaseFinishedListener1, "mypurchasetoken");
            }

        }else if(position == 16) {
            if(sku14 != 0 ){
                int gif = R.drawable.gif_ta_tay_kum_fak;
                int mp3 = R.raw.tateykumfak_thai;
                intentData("Ta Tay Kum Fak",gif,mp3);

            }else {
                mHelper.launchPurchaseFlow(this, ITEM_SKU14, 1002,
                        mPurchaseFinishedListener1, "mypurchasetoken");
            }

        }else if(position == 17) {
            if(sku15 != 0 ){
                int gif = R.drawable.gif_bhuma_rum_kwan;
                int mp3 = R.raw.bhumarumkwan_thai;
                intentData("Bhuma Rum Kwan",gif,mp3);

            }else {
                mHelper.launchPurchaseFlow(this, ITEM_SKU15, 1002,
                        mPurchaseFinishedListener1, "mypurchasetoken");
            }
        }
    }
    public void intentData(String name, int gif, int mp3){
        Intent secondActivity = new Intent(ScrollingUpperPartActivity.this, ShowSkillActivity.class);
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
        mAdapter = new SkillAdapter(ScrollingUpperPartActivity.this, data);
        mRVList.setAdapter(mAdapter);
        mRVList.scrollToPosition(position);
        mAdapter.notifyDataSetChanged();
    }
}
