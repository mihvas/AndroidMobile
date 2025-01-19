import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.task3.Tab1Fragment

class TabsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2


    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Tab1Fragment.newInstance(Tab1Fragment.TYPE_MY_ACTIVITIES)
            1 -> Tab1Fragment.newInstance(Tab1Fragment.TYPE_USER_ACTIVITIES)
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}
