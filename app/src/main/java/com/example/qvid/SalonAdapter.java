package com.example.qvid;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SalonAdapter extends RecyclerView.Adapter<SalonAdapter.SalonHolder> {
    private List<Salon> salons = new ArrayList<>();
    private Context mcontext;
    public static int SALON_ID;
    public static String SALON_NAME;

    public SalonAdapter(Context context)
    {
        mcontext = context;
    }

    @NonNull
    @Override
    public SalonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.salon_view, parent, false);
        return new SalonHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SalonHolder holder, int position) {
        final Salon currentSalon = salons.get(position);
        holder.textViewSalonName.setText(currentSalon.getSalonname());

        holder.saloncardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, ServicesActivity.class);
                intent.putExtra("sname",currentSalon.getSalonname());
                SALON_ID = currentSalon.getSalonid();
                SALON_NAME = currentSalon.getSalonname();
                mcontext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return salons.size();
    }
    public void setSalons(List<Salon> salons) {
        this.salons = salons;
        notifyDataSetChanged();
    }


    class SalonHolder extends RecyclerView.ViewHolder
    {
        private TextView textViewSalonName;
        private CardView saloncardview;


        public SalonHolder(@NonNull View itemView) {
            super(itemView);
            textViewSalonName = itemView.findViewById(R.id.salon_name);
            saloncardview = itemView.findViewById(R.id.salon_card_view);
        }
    }
}
