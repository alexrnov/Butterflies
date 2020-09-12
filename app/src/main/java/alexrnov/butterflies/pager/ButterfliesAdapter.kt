package alexrnov.butterflies.pager

import alexrnov.butterflies.R
import alexrnov.butterflies.details.DetailsActivity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ButterfliesAdapter constructor(private val list: List<ButterflyData>,
                                     private val landscape: Boolean) : RecyclerView.Adapter<ButterfliesAdapter.CardViewHolder>() {

  // Provide a reference to the views for each data item. Complex data items may need more than one
  // view per item, and you provide access to all the views for a data item in a view holder.
  class CardViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)

  // Create new views (invoked by the layout manager)
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
    // create a new view
    val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_item, parent, false) as CardView
    // here may set the view's size, margins, paddings and layout parameters
    return CardViewHolder(cardView)
  }

  // replace the contents of a view (invoked by the layout manager)
  override fun onBindViewHolder(holder: CardViewHolder, position: Int) {

    val textView = holder.cardView.findViewById<TextView>(R.id.description)
    val imageView = holder.cardView.findViewById<ImageView>(R.id.imageView)

    imageView.setImageDrawable(list[position].smallImage)

    textView.text = if (landscape) list[position].titleLand else list[position].titlePort

    holder.cardView.tag = position
    holder.cardView.isClickable = true

    holder.cardView.setOnClickListener { view ->
      val context = view.context
      val intent = Intent(view.context, DetailsActivity::class.java)

      intent.putExtra("linkBigImage", list[position].linkBigImage)
      intent.putExtra("linkDescription", list[position].linkDescription)

      context.startActivity(intent)
    }
  }

  // return the size of your dataset (invoked by the layout manager)
  override fun getItemCount() = list.size

  }