package com.example.astralapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.astralapp.databinding.FragmentDetailsBinding
import com.example.astralapp.databinding.FullRowCardDesignBinding
import com.example.astralapp.databinding.SingleCardDesignBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentDetailsBinding
            .inflate(inflater, container, false).apply {
                binding = this
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val user = requireArguments().getParcelable<User>(getString(R.string.user_retrieve_identifier))
        if (user != null) {
            viewModel.calculateUserDetails(user)
        }
        setupObserver()
        setButtonListener()
    }

    private fun setupObserver() {
        viewModel.userInformation.observe(viewLifecycleOwner, {
            initView(it)
        })
    }

    private fun initView(userPresentation: UserPresentation) {
        with(binding) {
            nameTextView.text = userPresentation.fullName
            birthDateTextView.text =
                String.format(getString(R.string.date_of_birth_pattern),
                    userPresentation.birthDate?.toStringDate(requireContext()) ?: userPresentation.toString())
            with(userPresentation) {
                val cards = listOf(
                    Pair(ageCardView, age),
                    Pair(chineseCardView, chineseSign),
                    Pair(zodiacCardView, zodiacSign),
                    Pair(hobbyCardView, hobby)
                )
                cards.forEach { card -> setCardInfo(card.first, card.second) }
                setCardInfo(postalCodeCardView, postalCode)
            }
        }
    }

    private fun setCardInfo(cardView: SingleCardDesignBinding, cardInfo: CardInfo) {
        cardView.resultTextView.text = cardInfo.name
        loadImage(cardView.descriptiveImageView, cardInfo.image)
    }

    private fun setCardInfo(cardView: FullRowCardDesignBinding, cardInfo: CardInfo) {
        cardView.postalCodeTextView.text =
            String.format(getString(R.string.postal_code_pattern), cardInfo.name)
        loadImage(cardView.postalCodeImageView, cardInfo.image)
    }

    private fun loadImage(imageView: ImageView, image: Int) {
        imageView.setImageDrawable(
            context?.let {
                ContextCompat.getDrawable(it, image)
            }
        )
    }

    private fun setButtonListener() {
        binding.newQueryButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}