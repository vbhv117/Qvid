package com.example.qvid;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdminBookingHistoryAdapter extends RecyclerView.Adapter<AdminBookingHistoryAdapter.AdminBookingHistoryHolder> {
    private List<BookingDetails> bookingDetails = new ArrayList<>();


    @NonNull
    @Override
    public AdminBookingHistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.booking_history_admin_view, parent, false);
        return new AdminBookingHistoryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminBookingHistoryHolder holder, int position) {
        BookingDetails currentbookingdetails_admin = bookingDetails.get(position);
        holder.textViewUserName.setText(currentbookingdetails_admin.getUsername());
        holder.textViewBookingId.setText(String.valueOf(currentbookingdetails_admin.getBookingId()));
        holder.textViewServiceName.setText(currentbookingdetails_admin.getServiceName());
        holder.textViewServiceRate.setText(String.valueOf(currentbookingdetails_admin.getServiceRate()));
        holder.textViewDayName.setText(currentbookingdetails_admin.getDayName());
        holder.textViewSlotName.setText(currentbookingdetails_admin.getSlotName());
        holder.textViewStatus.setText(currentbookingdetails_admin.getStatus());

    }

    @Override
    public int getItemCount() {
        return bookingDetails.size();
    }

    public void setBookingDetails_admin(List<BookingDetails> bookingDetails) {
        this.bookingDetails = bookingDetails;
        notifyDataSetChanged();
    }


    class AdminBookingHistoryHolder extends RecyclerView.ViewHolder
    {
        private TextView textViewUserName;
        private TextView textViewBookingId;
        private TextView textViewServiceName;
        private TextView textViewServiceRate;
        private TextView textViewDayName;
        private TextView textViewSlotName;
        private TextView textViewStatus;
        private CardView adminbookinghistorycardview;

        public AdminBookingHistoryHolder(@NonNull View itemView) {
            super(itemView);
            textViewUserName = itemView.findViewById(R.id.user_admin);
            textViewBookingId = itemView.findViewById(R.id.booking_id_admin);
            textViewServiceName = itemView.findViewById(R.id.service_name_admin);
            textViewServiceRate = itemView.findViewById(R.id.service_rate_admin);
            textViewDayName = itemView.findViewById(R.id.day_name_admin);
            textViewSlotName = itemView.findViewById(R.id.slot_name_admin);
            textViewStatus = itemView.findViewById(R.id.status_admin);
            adminbookinghistorycardview = itemView.findViewById(R.id.booking_history_admin_card_view);
        }
    }
}
