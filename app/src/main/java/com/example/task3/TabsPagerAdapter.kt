import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.task3.Tab1Fragment
import com.example.task3.Tab2Fragment

// Адаптер для управления вкладками
class TabsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    // Указываем количество вкладок
    override fun getItemCount(): Int = 2

    // Возвращаем фрагмент для каждой вкладки
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Tab1Fragment() // Фрагмент для первой вкладки
            1 -> Tab2Fragment() // Фрагмент для второй вкладки
            else -> throw IllegalArgumentException("Некорректная позиция: $position")
        }
    }
}
