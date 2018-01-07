package de.uni_stuttgart.sopra_ws1617_team8.tong.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import de.uni_stuttgart.sopra_ws1617_team8.tong.Lists.PersonList;
import de.uni_stuttgart.sopra_ws1617_team8.tong.R;

/**
 * AdapterClass which handles the structure and the clicks of the RecyclerView
 */
public class RecyclerViewAdapterMainContact extends RecyclerView.Adapter<RecyclerViewAdapterMainContact.MyViewHolder> {

    private LayoutInflater inflater;
    private List<PersonList> data = Collections.emptyList();
    private PersonClickListener click;


    /**
     * Constructor of the Adapter Class which handles a RecyclerView
     *
     * @param context the ActivityContext
     * @param data    the Data which will be displayed
     */
    public RecyclerViewAdapterMainContact(Context context, List<PersonList> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    /**
     * Method which loads the entries of the RecyclerView once
     *
     * @param parent   The ParentView Group
     * @param viewType .
     * @return the Constructor of the Class
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.small_personcardview, parent, false);
        return new MyViewHolder(view);
    }

    /**
     * Method which updates the entries of the RecyclerView once they are loaded
     *
     * @param holder   Object of the MyViewHolder Class
     * @param position the position of the View
     */
    @Override
    public void onBindViewHolder(RecyclerViewAdapterMainContact.MyViewHolder holder, int position) {
        PersonList current = data.get(position);
        holder.txtGivenName.setText(current.givenname);
        holder.txtSurName.setText(current.surname);
        holder.txtRegion.setText(current.region);
        holder.txtComapany.setText(current.company);
        holder.rBar.setRating(current.rating);
    }

    /**
     * Set the ClickListener to the variable click
     *
     * @param click ClickListener of the clickable Item
     */
    public void setPersonClickListener(PersonClickListener click) {
        this.click = click;
    }

    /**
     * Method to count the complete RecyclerView size
     *
     * @return the Size of the RecyclerView
     */
    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * Interface for the clicking which is used in the class where the RecyclerView is implemented
     */
    public interface PersonClickListener {
        void itemClickPerson(View view, int position);
    }

    /**
     * Class to
     * a) Handle the Clicks inside the RecyclerView
     * b) Setup every Single Card
     * <p>
     * TODO: Setup ImageView to implement a personal User Image (Additional Feature)
     */
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtGivenName, txtSurName, txtComapany, txtRegion;
        RatingBar rBar;

        /**
         * Constructor of the helper class
         *
         * @param itemView the given View of the class
         */
        private MyViewHolder(View itemView) {
            super(itemView);
            CardView card = (CardView) itemView.findViewById(R.id.personCard);
            txtGivenName = (TextView) itemView.findViewById(R.id.tv_givenName);
            txtSurName = (TextView) itemView.findViewById(R.id.tv_surName);
            txtComapany = (TextView) itemView.findViewById(R.id.tv_Company);
            txtRegion = (TextView) itemView.findViewById(R.id.tv_Region);
            rBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
            card.setOnClickListener(this);
        }

        /**
         * Method to handle the click on a single View
         *
         * @param view the given view of the clicked item
         */
        @Override
        public void onClick(View view) {
            click.itemClickPerson(view, getAdapterPosition());
        }
    }
}