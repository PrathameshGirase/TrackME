package com.example.trackme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackme.adapter.CardAdapter;
import com.example.trackme.adapter.ViewPagerAdapter;
import com.example.trackme.databinding.ActivityHomePageBinding;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Activity_HomePage extends AppCompatActivity {


    BottomAppBar bottomAppBar;
    ViewPagerAdapter viewPagerAdapter;

    BottomNavigationView bottomNavigationView;
    TextView userName, incAmt, expAmt;
    ActivityHomePageBinding binding;

    CardAdapter cardAdapter;
    List<cards> cardsList, tempList, tempList1;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


//        bottomNavigationView = findViewById(R.id.bottomNavView);
//        bottomAppBar = findViewById(R.id.bottomAppBar);
//
//        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                int id = item.getItemId();
//                if(id == R.id.navigation_home) {
//                    bottomAppBar.setOnMenuItemClickListener();
//                }
//
//                return false;
//            }
//        }
//        {
//            @SuppressLint("NonConstantResourceId")
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id = item.getItemId();
//                if(id == R.id.navigation_home){
//
//                    case R.id.navigation_home:
//                        //viewPager2.setCurrentItem(0);
//                        break;
//
//                    case R.id.navigation_transaction:
//                        //viewPager2.setCurrentItem(1);
//                        break;
//                }
//                return false;
//            }
//        });

//        bottomAppBar.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position) {
//                switch(position) {
//                    case 0:
//                        bottomNavigationView.getMenu().findItem(R.id.navigation_home).setChecked(true);
//                        break;
//
//                    case 1:
//                        bottomNavigationView.getMenu().findItem(R.id.navigation_transaction).setChecked(true);
//                        break;
//                }
//                super.onPageSelected(position);
//            }
//        });
//

        userName = findViewById(R.id.textViewProfile);
        userName.setText("Igmite Solutions");

        cardsList = new ArrayList<>();
        tempList= new ArrayList<>();
        tempList1= new ArrayList<>();
        cards card1 = new cards("Tea",500,"23-02-2023", 1,1);
        cards card2 = new cards("Pantry",800,"28-02-2023",1, 1);
        cards card3 = new cards("Project",500,"01-03-2023", 2);
        cards card4 = new cards("Staff",1000,"27-04-2023", 4,1);
        cards card5 = new cards("Stationary",1000,"27-04-2023",3, 1);
        cards card6 = new cards("Pantry",1000,"27-04-2023", 1, 1);
        cards card7 = new cards("Project",1000,"27-04-2023", 2);
        cards card8 = new cards("Travel",500,"27-04-2023", 2, 1);


        cardsList.add(card1);
        cardsList.add(card2);
        cardsList.add(card3);
        cardsList.add(card4);
        cardsList.add(card5);
        cardsList.add(card6);
        cardsList.add(card7);
        cardsList.add(card8);

        incAmt = findViewById(R.id.incomeAmt);
        expAmt = findViewById(R.id.expenseAmt);



        int exp = 0, inc = 0;
        for(cards card : cardsList) {
            String date = card.getDate();
            if (date == "11-03-2023") {
                tempList.add(card);
                int num = card.getAmount();
                if(card.getExpId() == 1){
                    exp += num;
                }
                if(card.getExpId() == 2){
                    inc += num;
                }
            }
        }
        incAmt.setText(Integer.toString(inc));
        expAmt.setText(Integer.toString(exp));


        recyclerView = findViewById(R.id.itemsRecycler);
        cardAdapter = new CardAdapter(Activity_HomePage.this, tempList);
        recyclerView.setAdapter(cardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Activity_HomePage.this));

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_HomePage.this, AddActivity.class));
                finish();
            }
        }) ;



        binding.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_HomePage.this, Activity_UserProfile.class));
                finish();
            }
        });

        binding.bottomNavView.setBackground(null);

        binding.bottomNavView.setOnItemSelectedListener(item->{
            int id = item.getItemId();
            switch(id) {
                case R.id.navigation_home:
                    break;

                case R.id.navigation_transaction:
                    Intent intent = new Intent(Activity_HomePage.this, Activity_Transaction.class);
                    intent.putExtra(Activity_Transaction.Description, "null");
                    intent.putExtra(Activity_Transaction.Amount, -1);
                    intent.putExtra(Activity_Transaction.Expense, -1);
                    intent.putExtra(Activity_Transaction.Category, -1);

                    startActivity(intent);
                    finish();
                    break;

                default:

            }
            return true;
        });

        binding.trendCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_HomePage.this, Activity_Report.class));
                finish();
            }
        });









    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        Fragment fragment = null;
//        switch (item.getItemId()) {
//            case R.id.navigation_home:
//                fragment = new homeFragement();
//                return true;
//            case R.id.navigation_transaction:
//                fragment = new transactionFragment();
//                return true;
//        }
//
//        return false;
//    }
    void loadFragment(Fragment fragment) {
        //to attach fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.relativelayout, fragment).commit();
    }


    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction transactionFragment= fragmentManager.beginTransaction();
        transactionFragment.replace(R.id.bottomAppBar, fragment);
        transactionFragment.commit();
    }

//    public void openUserPage(View v) {
//        Intent intent = new Intent(this, Activity_UserProfile.class);
//        startActivity(intent);
//    }




}

