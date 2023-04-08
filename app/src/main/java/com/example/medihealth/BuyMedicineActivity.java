package com.example.medihealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicineActivity extends AppCompatActivity {
    private String[][] packages =
            {
                    {"Uprise-D-3 1000IU Capsule","","","","50"},
                    {"Healthvit Chromium Picolinate 200mcg Capsule","","","","305"},
                    {"Vitamin B Complex Capsules","","","","448"},
                    {"Inlife Vitamin E Wheat Germ Oil Capsule","","","","539"},
                    {"Dolo 650 Tablet","","","","30"},
                    {"Crocin 650 Advance Tablet","","","","50"},
                    {"Strepsils Medicated Lozenges for Sore Throat","","","","40"},
                    {"Tata 1mg Calcium + Vitamin D3","","","","30"},
                    {"Feronia-XT Tablet","","","","130"},
            };
    private String[] package_details = {
            "Building and keeping the bones & teeth strong\n" + "Reducing Fatigue/stress and muscular pains\n"+ "Boosting immunity and increasing resistance against infection",
            "Chromium is an essential trace mineral that plays an important role in helping insulin regulation"+ "Provides relief from vitamin B deficiencies\n"+ "Helps in formation of red blood cells\n"+ "Maintains healthy nervous system\n",
            "It promotes health as well as skin benefits.\n"+"It helps reduce skin blemish and pigmentation.\n"+ "It act as safeguard the skin from the harsh UVA and UVB sun rays.",
            "Building and keeping the bones & teeth strong\n" + "Reducing Fatigue/stress and muscular pains\n"+ "Boosting immunity and increasing resistance against infection",
            "Chromium is an essential trace mineral that plays an important role in helping insulin regulation"+ "Provides relief from vitamin B deficiencies\n"+ "Helps in formation of red blood cells\n"+ "Maintains healthy nervous system\n",
            "It promotes health as well as skin benefits.\n"+"It helps reduce skin blemish and pigmentation.\n"+ "It act as safeguard the skin from the harsh UVA and UVB sun rays.",
            "Building and keeping the bones & teeth strong\n" + "Reducing Fatigue/stress and muscular pains\n"+ "Boosting immunity and increasing resistance against infection",
    };
    HashMap<String,String> item;
    SimpleAdapter sa;
    ArrayList list;
    ListView listView;
    Button btnBack,btnCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        listView = findViewById(R.id.listViewBM);
        btnBack = findViewById(R.id.btnBMBack);
        btnCart = findViewById(R.id.btnBMCart);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this,HomeActivity.class));
            }
        });

        list = new ArrayList();
        for(int i=0;i<packages.length;i++){
            item = new HashMap<String,String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", packages[i][5]);
            list.add(item);
        }

        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        listView.setAdapter(sa);
    }
}