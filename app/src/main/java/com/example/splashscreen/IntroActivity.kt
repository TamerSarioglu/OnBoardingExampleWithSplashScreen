package com.example.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class IntroActivity : AppCompatActivity() {

    private lateinit var onBoardingItemsAdapter: OnBoardingItemsAdapter
    private lateinit var indicatorsContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        setOnBoardingItems()
        setUpIndicators()
        setCurrentIndicator(0)

    }

    private fun setOnBoardingItems() {
        onBoardingItemsAdapter = OnBoardingItemsAdapter(
            listOf(
                OnBoardingItem(
                    onBoardingImage = R.drawable.huawei,
                    title = "Manage Your Task",
                    description = "Organize all your todos and projects. Color tag them to set priorities and categories"
                ),
                OnBoardingItem(
                    onBoardingImage = R.drawable.huawei,
                    title = "Work On Time",
                    description = "When you are overwhelmed by the amount of work you have on your plate, stop and rethink"
                ),
                OnBoardingItem(
                    onBoardingImage = R.drawable.huawei,
                    title = "Get Reminder on Time",
                    description = "When you encounter a small task that takes less than 5 minutes to complete"
                ),
            )
        )
        val onboardingViewPager = findViewById<ViewPager2>(R.id.onBoardingViewPager)
        onboardingViewPager.adapter = onBoardingItemsAdapter
        onboardingViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })

        (onboardingViewPager.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER
        findViewById<ImageView>(R.id.imageNext).setOnClickListener {
            if (onboardingViewPager.currentItem + 1 < onBoardingItemsAdapter.itemCount) {
                onboardingViewPager.currentItem += 1
            } else {
                navigateToMainActivity()
            }
        }

        findViewById<TextView>(R.id.textSkip).setOnClickListener {
            navigateToMainActivity()
        }

        findViewById<Button>(R.id.buttonGetStarted).setOnClickListener {
            navigateToMainActivity()
        }
    }

    private fun navigateToMainActivity() {
        startActivity(Intent(applicationContext, MainActivity::class.java))
        finish()
    }

    private fun setUpIndicators() {
        indicatorsContainer = findViewById(R.id.indicatorsContainer)

        val indicators = arrayOfNulls<ImageView>(onBoardingItemsAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
                it.layoutParams = layoutParams
                indicatorsContainer.addView(it)
            }
        }
    }

    private fun setCurrentIndicator(position: Int) {

        val childCount = indicatorsContainer.childCount

        for (i in 0 until childCount) {
            val imageView = indicatorsContainer.getChildAt(i) as ImageView

            if (i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active_background
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext, R.drawable.indicator_inactive_background
                    )
                )
            }
        }
    }
}