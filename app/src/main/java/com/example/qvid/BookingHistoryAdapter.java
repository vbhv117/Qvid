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

public class BookingHistoryAdapter extends RecyclerView.Adapter<BookingHistoryAdapter.BookingHistoryHolder> {
    private List<BookingDetails> bookingDetails = new ArrayList<>();


    @NonNull
    @Override
    public BookingHistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.booking_history_view, parent, false);
        return new BookingHistoryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingHistoryHolder holder, int position) {
        BookingDetails currentbookingdetails = bookingDetails.get(position);
        holder.textViewSalonName.setText(currentbookingdetails.getSalonName());
        holder.textViewBookingId.setText(String.valueOf(currentbookingdetails.getBookingId()));
        holder.textViewServiceName.setText(currentbookingdetails.getServiceName());
        holder.textViewServiceRate.setText(String.valueOf(currentbookingdetails.getServiceRate()));
        holder.textViewDayName.setText(currentbookingdetails.getDayName());
        holder.textViewSlotName.setText(currentbookingdetails.getSlotName());
        holder.textViewStatus.setText(currentbookingdetails.getStatus());


    }

    @Override
    public int getItemCount() {
        return bookingDetails.size();
    }
    public void setBookingDetails_user(List<BookingDetails> bookingDetails) {
        this.bookingDetails = bookingDetails;
        notifyDataSetChanged();
    }


    class BookingHistoryHolder extends RecyclerView.ViewHolder
    {
        private TextView textViewSalonName;
        private TextView textViewBookingId;
        private TextView textViewServiceName;
        private TextView textViewServiceRate;
        private TextView textViewDayName;
        private TextView textViewSlotName;
        private CardView bookinghistorycardview;
        private TextView textViewStatus;

        public BookingHistoryHolder(@NonNull View itemView) {
            super(itemView);
            textViewSalonName = itemView.findViewById(R.id.salon_name_user);
            textViewBookingId = itemView.findViewById(R.id.booking_id_user);
            textViewServiceName = itemView.findViewById(R.id.service_name_user);
            textViewServiceRate = itemView.findViewById(R.id.service_rate_user);
            textViewDayName = itemView.findViewById(R.id.day_name_user);
            textViewSlotName = itemView.findViewById(R.id.slot_name_user);
            textViewStatus = itemView.findViewById(R.id.status_user);
            bookinghistorycardview = itemView.findViewById(R.id.booking_history_card_view);
        }
    }
}
