package com.saikauskas.julius.cinamon;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SubscriptionAdapter extends RecyclerView.Adapter<SubscriptionAdapter.SubscriptionViewHolder> {

    private Context context;
    private List<Subscription> subscriptionList;

    public SubscriptionAdapter(Context context, List<Subscription> subscriptionList) {
        this.context = context;
        this.subscriptionList = subscriptionList;
    }

    @Override
    public SubscriptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.subscriptions_list_layout, parent, false);

        return new SubscriptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SubscriptionViewHolder holder, int i) {
        Subscription subscription = subscriptionList.get(i);

        holder.tvSubName.setText(subscription.getSubscriptionName());
        holder.tvCycleOfSub.setText(subscription.getSubscriptionCycle());
        holder.tvSubPrice.setText(String.valueOf(subscription.getSubscriptionPrice()));

        holder.ivSubcriptionIcon.setImageDrawable(context.getResources().getDrawable(subscription.getSubscribtionImage()));
        holder.ivColorOfSub.setImageDrawable(context.getResources().getDrawable(subscription.getSubscriptionColor()));

    }


    @Override
    public int getItemCount() {
        return subscriptionList.size();
    }

    class SubscriptionViewHolder extends RecyclerView.ViewHolder {

        ImageView ivSubcriptionIcon, ivColorOfSub;
        TextView tvSubName, tvSubPrice, tvCycleOfSub;

        public SubscriptionViewHolder(View itemView) {
            super(itemView);

            ivSubcriptionIcon = itemView.findViewById(R.id.ivSubscriptionIcon);
            ivColorOfSub = itemView.findViewById(R.id.ivColorOfSub);
            tvSubName = itemView.findViewById(R.id.tvNameOfSubscription);
            tvSubPrice = itemView.findViewById(R.id.tvPriceOfSubscription);
            tvCycleOfSub = itemView.findViewById(R.id.tvCycleOfSub);
        }


    }
}
