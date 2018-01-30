package br.com.psoa.listacomasynctask.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.psoa.listacomasynctask.R;
import br.com.psoa.listacomasynctask.listener.OnItemClickListener;
import br.com.psoa.listacomasynctask.model.Android;

/**
 * Created by logonrm on 29/01/2018.
 */

public class AndroidAdapter extends RecyclerView.Adapter<AndroidAdapter.AndroidItemViewHolder>{

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Android> androidList;
    private OnItemClickListener listener;
    public  AndroidAdapter(Context context, List<Android> androidList) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.androidList = androidList;
    }

    @Override
    public AndroidItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_android, parent, false);
        return new AndroidItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AndroidItemViewHolder holder, int position) {
        Android android = androidList.get(position);
        holder.tvAPI.setText(android.getApi());
        holder.tvName.setText(android.getNome());
        holder.tvVersion.setText(android.getVersao());
        Picasso.with(context).load(android.getUrlImagem())
                .error(R.drawable.error404)
                .placeholder(R.drawable.refreshbutton)
                .into(holder.ivLogo);
    }

    @Override
    public int getItemCount() {
        return androidList.size();
    }

    public Android getItem (int position) {
        return androidList.get(position);
    }

    public void setClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    protected class AndroidItemViewHolder
            extends RecyclerView.ViewHolder
            implements  View.OnClickListener{

        private ImageView ivLogo;
        private TextView tvName;
        private TextView tvAPI;
        private TextView tvVersion;

        public AndroidItemViewHolder(View itemView) {
            super(itemView);
            ivLogo = itemView.findViewById(R.id.ivLogo);
            tvName = itemView.findViewById(R.id.tvName);
            tvAPI = itemView.findViewById(R.id.tvAPI);
            tvVersion = itemView.findViewById(R.id.tvVersion);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (listener != null) {
                listener.onClick(view, getAdapterPosition());
            }
        }
    }

}
