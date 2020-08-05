package alexrnov.butterflies

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

//class ButterfliesAdapter(private val parentActivity: FragmentActivity) : RecyclerView.Adapter<ButterfliesAdapter.TextViewHolder>() {
class ButterfliesAdapter(val list: List<String>) : RecyclerView.Adapter<ButterfliesAdapter.CardViewHolder>() {

  private var dataset: Array<String> = arrayOf("1", "2", "3", "4", "5", "6", "7")

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
    //holder.textView.text = dataset[position]

    val textView = holder.cardView.findViewById<TextView>(R.id.description)
    val imageView = holder.cardView.findViewById<ImageView>(R.id.imageView)

    textView.text = list[position]
    if (position == 0) {
      //textView.text = "Papilio maackii"
      imageView.setImageResource(R.drawable.pic1)
    } else if (position == 1) {
      //textView.text = list[0]
      imageView.setImageResource(R.drawable.pic2)
    } else if (position == 2) {
      //textView.text = list[1]
      imageView.setImageResource(R.drawable.pic3)
    } else if (position == 3) {
      //textView.text = list[2]
      imageView.setImageResource(R.drawable.pic4)
    } else if (position == 4) {
      //textView.text = list[3]
      imageView.setImageResource(R.drawable.pic5)
    } else if (position == 5) {
      //textView.text = "text6"
      imageView.setImageResource(R.drawable.pic6)
    } else if (position == 6) {
      //textView.text = "text7"
      imageView.setImageResource(R.drawable.pic7)
    }

    holder.cardView.tag = position
    holder.cardView.isClickable = true
  }

  // return the size of your dataset (invoked by the layout manager)
  override fun getItemCount() = list.size

  }