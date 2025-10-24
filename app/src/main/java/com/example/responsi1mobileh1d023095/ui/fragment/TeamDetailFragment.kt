package com.example.responsi1mobileh1d023095.ui.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.responsi1mobileh1d023095.data.model.Player
import com.example.responsi1mobileh1d023095.databinding.FragmentTeamDetailBinding
import java.text.SimpleDateFormat
import java.util.Locale

class TeamDetailFragment : DialogFragment() {

    private var _binding: FragmentTeamDetailBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val ARG_PLAYER = "arg_player"
        private const val ARG_BG_COLOR = "arg_bg_color"

        fun newInstance(player: Player, backgroundColor: Int): TeamDetailFragment {
            val fragment = TeamDetailFragment()
            val args = Bundle().apply {
                putParcelable(ARG_PLAYER, player)
                putInt(ARG_BG_COLOR, backgroundColor)
            }
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTeamDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val window = dialog?.window
        window?.let {
            it.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

            it.setGravity(Gravity.BOTTOM)

            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val player = arguments?.getParcelable<Player>(ARG_PLAYER)
        val bgColor = arguments?.getInt(ARG_BG_COLOR) ?: Color.GRAY

        if (player != null) {
            binding.tvName.text = player.name
            binding.tvNationality.text = player.nationality
            binding.tvPosition.text = player.position
            binding.tvDateOfBirth.text = formatBirthDate(player.dateOfBirth)

            val backgroundDrawable = binding.fragmentCard.background as? GradientDrawable

            backgroundDrawable?.let {
                it.setColor(Color.WHITE)
                val strokeWidthPx = (2 * resources.displayMetrics.density).toInt() // Lebar stroke 2dp
                it.setStroke(strokeWidthPx, bgColor)
            }
        }
    }

    private fun formatBirthDate(dateStr: String): String {
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val outputFormat = SimpleDateFormat("d MMMM yyyy", Locale.getDefault())
            val date = inputFormat.parse(dateStr)
            date?.let { outputFormat.format(it) } ?: dateStr
        } catch (e: Exception) {
            dateStr
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}