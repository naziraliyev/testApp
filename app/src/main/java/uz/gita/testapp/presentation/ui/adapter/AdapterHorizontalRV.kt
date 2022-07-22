package uz.gita.testapp.presentation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import timber.log.Timber
import uz.gita.testapp.R
import uz.gita.testapp.data.local.entity.ImageEntity
import uz.gita.testapp.databinding.ItemHorizontalBinding
import uz.gita.testapp.utils.isAvailableNetwork

class AdapterHorizontalRV(private val context: Context): ListAdapter<ImageEntity, AdapterHorizontalRV.Holder>(DiffUtils) {

    private var itemOnclickListener :((ImageEntity)->Unit)? = null
   private object DiffUtils: DiffUtil.ItemCallback<ImageEntity>(){
        override fun areItemsTheSame(oldItem: ImageEntity, newItem: ImageEntity): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: ImageEntity, newItem: ImageEntity): Boolean {
            return oldItem == newItem
        }

    }


    inner class Holder(private val binding: ItemHorizontalBinding): RecyclerView.ViewHolder(binding.root){

        init {
            binding.imageRvHorizontal.setOnClickListener {
                if (context.isAvailableNetwork()){
                    Timber.d("itemOnclickListener$bindingAdapterPosition")
                    itemOnclickListener?.invoke(getItem(bindingAdapterPosition))
                }else{
                    Toast.makeText(context,"You are offline! Check your internet connection ",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
        fun bind(): ImageEntity? = with(binding){
                getItem(bindingAdapterPosition).apply {
                    Glide.with(binding.root).load(download_url).centerCrop().placeholder(R.drawable.ic_baseline_image_24)
                        .error(R.drawable.ic_error).into(imageRvHorizontal)
                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ItemHorizontalBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind()
    }

    fun setItemOnclickListener(block:(ImageEntity)->Unit){
        itemOnclickListener = block
    }
}