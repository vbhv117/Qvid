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

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceHolder> {
    private List<SalonServices> salonServices = new ArrayList<>();

    private Context mcontext;

    public ServiceAdapter(Context context)
    {
        mcontext = context;
    }

    @NonNull
    @Override
    public ServiceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.salon_service_view, parent, false);
        return new ServiceHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceHolder holder, int position) {
        final SalonServices currentService = salonServices.get(position);
        holder.textViewServiceName.setText(currentService.getServicename());
        holder.textViewServiceRate.setText(String.valueOf(currentService.getServicerate()));

        holder.servicecardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, AppointmentActivity.class);
                intent.putExtra("sername",currentService.getServicename());
                intent.putExtra("srate",currentService.getServicerate());
                mcontext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return salonServices.size();
    }
    public void setSalonServices(List<SalonServices> salonServices) {
        this.salonServices = salonServices;
        notifyDataSetChanged();
    }


    class ServiceHolder extends RecyclerView.ViewHolder
    {
        private TextView textViewServiceName;
        private TextView textViewServiceRate;
        private CardView servicecardview;

        public ServiceHolder(@NonNull View itemView) {
            super(itemView);
            textViewServiceName = itemView.findViewById(R.id.service_name);
            textViewServiceRate = itemView.findViewById(R.id.service_rate);
            servicecardview = itemView.findViewById(R.id.service_card_view);
        }
    }
}
