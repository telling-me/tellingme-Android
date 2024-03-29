package com.tellingus.tellingme.presentation.ui.feature.auth.signup

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.common.component.appbar.BasicAppBar
import com.tellingus.tellingme.presentation.ui.common.component.box.SelectBox
import com.tellingus.tellingme.presentation.ui.common.component.button.PrimaryButton
import com.tellingus.tellingme.presentation.ui.common.component.button.TellingmeIconButton
import com.tellingus.tellingme.presentation.ui.common.component.layout.MainLayout
import com.tellingus.tellingme.presentation.ui.common.model.ButtonSize
import com.tellingus.tellingme.presentation.ui.common.navigation.AuthDestinations
import com.tellingus.tellingme.presentation.ui.theme.Base0
import com.tellingus.tellingme.presentation.ui.theme.Gray500
import com.tellingus.tellingme.presentation.ui.theme.Gray600
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme
import com.tellingus.tellingme.util.collectWithLifecycle

@Composable
fun SignupJobScreen(
    navController: NavController,
    viewModel: SignupViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    MainLayout(
        header = {
            BasicAppBar(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(all = 12.dp),
                leftSlot = {
                    TellingmeIconButton(
                        iconRes = R.drawable.icon_caret_left,
                        size = ButtonSize.MEDIUM,
                        color = Gray500,
                        onClick = {
                            navController.popBackStack()
                        }
                    )
                }
            )
        },
        content = {
            SignupJobContentScreen(
                navController = navController,
                viewModel = viewModel
            )
        },
        isScrollable = false,
        background = Base0
    )
}

@Composable
fun SignupJobContentScreen(
    navController: NavController,
    viewModel: SignupViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var selectedJob by remember { mutableStateOf(-1) }

    val jobList = listOf<Job>(
        Job(R.drawable.icon_bagpack, "중·고등학생"),
        Job(R.drawable.icon_graduationcap, "대학(원)생"),
        Job(R.drawable.icon_smiley, "취업준비생"),
        Job(R.drawable.icon_briefcase, "직장인"),
        Job(R.drawable.icon_cookpot, "주부"),
        Job(R.drawable.icon_etc, "기타"),
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 30.dp, bottom = 16.dp, start = 20.dp, end = 20.dp)
    ) {
        Text(
            text = "직업을 선택해주세요",
            style = TellingmeTheme.typography.head2Bold,
            color = Gray600
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "나와 비슷한 텔러들의 이야기를 먼저 확인할 수 있어요\n(마이페이지에서 변경 가능)",
            style = TellingmeTheme.typography.body2Regular,
            color = Gray600
        )
        Spacer(modifier = Modifier.size(30.dp))

        LazyColumn() {
            itemsIndexed(
                items = jobList,
//                key = { _, item ->
//                    item.isSelected.hashCode()
//                }
            ) { index, item ->
                SelectBox(
                    text = item.text,
                    icon = item.icon,
                    isSelected = index == selectedJob,
                    onClick = {
                        selectedJob = index
                    }
                )
                Spacer(modifier = modifier.size(12.dp))
            }
        }
        Spacer(modifier = Modifier
            .fillMaxHeight()
            .weight(1f))

        PrimaryButton(
            modifier = modifier
                .fillMaxWidth(),
            size = ButtonSize.LARGE,
            text = "다음",
            enable = selectedJob != -1,
            onClick = {
                viewModel.processEvent(
                    SignupContract.Event.NextButtonClickedInJob(job = selectedJob)
                )
            }
        )
    }

    viewModel.effect.collectWithLifecycle { effect ->
        when(effect) {
            is SignupContract.Effect.MoveToWorry -> {
                navController.navigate(AuthDestinations.Signup.SIGNUP_WORRY)
            }
            else -> {}
        }
    }
}

data class Job(
    @DrawableRes val icon: Int,
    val text: String,
    var isSelected: Boolean = false
)

@Preview
@Composable
fun SignupJobScreenPreview() {
    SignupJobScreen(navController = rememberNavController())
}