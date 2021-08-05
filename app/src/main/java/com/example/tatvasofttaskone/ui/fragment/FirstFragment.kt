package com.example.tatvasofttaskone.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tatvasofttaskone.R
import com.example.tatvasofttaskone.data.model.CustomButton
import com.example.tatvasofttaskone.databinding.FragmentFirstBinding
import com.example.tatvasofttaskone.ui.`interface`.OnItemClickListener
import com.example.tatvasofttaskone.ui.adapter.ButtonAdapter


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSubmit.setOnClickListener {
            if (isPerfectSquare(binding.edittextFirst.text.toString().toLong())) {

                val listSize = binding.edittextFirst.text.toString().toLong()
                val gridSpan = Math.sqrt(binding.edittextFirst.text.toString().toDouble()).toInt()
                val buttonList = mutableListOf<CustomButton>()
                val excludeList = mutableListOf<Long>()

                val random = (0 until listSize).random()
                excludeList.add(random)

                for (i in 0 until listSize) {

                    if (i == random) {
                        buttonList.add(
                            CustomButton(
                                resources.getColor(android.R.color.holo_red_dark),
                                true
                            )
                        )

                    } else {
                        buttonList.add(CustomButton(resources.getColor(R.color.white), false))

                    }
                }

                binding.recycleView.layoutManager = GridLayoutManager(requireContext(), gridSpan)
                binding.recycleView.adapter =
                    ButtonAdapter(requireContext(), buttonList, object : OnItemClickListener {
                        override fun onItemClick(t: CustomButton, position: Int) {
                            if (t.isClickable) {
                                buttonList[position].buttonColor =
                                    resources.getColor(android.R.color.holo_blue_dark)
                                buttonList[position].isClickable = false
                                binding.recycleView.adapter?.notifyDataSetChanged()

                                if (excludeList.size.toLong() < 0 + listSize) {
                                    val r =
                                        getRandomWithExclusion(0, listSize - 1, excludeList)
                                    excludeList.add(r)
                                    buttonList[r.toInt()].buttonColor =
                                        resources.getColor(android.R.color.holo_red_dark)
                                    buttonList[r.toInt()].isClickable = true
                                    binding.recycleView.adapter?.notifyDataSetChanged()
                                } else {
                                    Toast.makeText(
                                        requireContext(),
                                        resources.getString(R.string.you_won_game),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }

                    })


            } else {
                Toast.makeText(requireContext(), resources.getString(R.string.add_value), Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun isPerfectSquare(n: Long): Boolean {

        val number = Math.sqrt(n.toDouble()).toLong()
        return (number * number) == n
    }

    fun getRandomWithExclusion(start: Long, end: Long, exclude: List<Long>): Long {
        var random: Long = (start..end).random()

        var isValid = false
        while (!isValid) {
            isValid = true
            for (element in exclude) {
                if (random == element) {
                    random = (start..end).random()
                    Log.d("Random", random.toString())
                    isValid = false
                    break
                }
            }
        }
        return random
    }

    private fun generateRandomTimeDalay(){

    }
}