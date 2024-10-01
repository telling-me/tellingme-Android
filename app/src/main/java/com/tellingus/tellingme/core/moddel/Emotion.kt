package com.tellingus.tellingme.core.moddel

import androidx.annotation.DrawableRes
import com.tellingus.tellingme.R

data class Emotion(
    @DrawableRes val icon: Int,
    val description: String,
    val emotion: Int
)

val emotionList = listOf<Emotion>(
    Emotion(R.drawable.emotion_happy_large, "행복해요", 0),
    Emotion(R.drawable.emotion_proud_large, "뿌듯해요", 1),
    Emotion(R.drawable.emotion_meh_large, "그저 그래요", 2),
    Emotion(R.drawable.emotion_tired_large, "피곤해요", 3),
    Emotion(R.drawable.emotion_sad_large, "슬퍼요", 4),
    Emotion(R.drawable.emotion_angry_large, "화나요", 5),
    Emotion(R.drawable.emotion_excited_large, "설레요", 6),
    Emotion(R.drawable.emotion_thrilled_large, "신나요", 7),
    Emotion(R.drawable.emotion_relaxed_large, "편안해요", 8),
    Emotion(R.drawable.emotion_lethargic_large, "무기력해요", 9),
    Emotion(R.drawable.emotion_lonely_large, "외로워요", 10),
    Emotion(R.drawable.emotion_complicated_large, "복잡해요", 11),
)