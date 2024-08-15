package com.tellingus.tellingme.presentation.ui.feature.myspace

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tellingus.tellingme.R
import com.tellingus.tellingme.presentation.ui.common.component.button.FloatingButton
import com.tellingus.tellingme.presentation.ui.common.navigation.HomeDestinations
import com.tellingus.tellingme.presentation.ui.theme.Background200
import com.tellingus.tellingme.presentation.ui.theme.Error400
import com.tellingus.tellingme.presentation.ui.theme.Gray500
import com.tellingus.tellingme.presentation.ui.theme.Gray600
import com.tellingus.tellingme.presentation.ui.theme.Primary400
import com.tellingus.tellingme.presentation.ui.theme.TellingmeTheme
import java.time.LocalDate

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("NewApi")
@Composable
fun MySpaceScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: MySpaceViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val initialPage = (uiState.today.year - CALENDAR_RANGE.startYear) * 12 + uiState.today.monthValue - 1
    val pageCount = (CALENDAR_RANGE.lastYear - CALENDAR_RANGE.startYear) * 12
    val calendarPagerState = rememberPagerState(pageCount = {pageCount}, initialPage = initialPage)
    var currentDate by remember { mutableStateOf(uiState.today) }
    var currentPage by remember { mutableIntStateOf(initialPage) }

    LaunchedEffect(key1 = calendarPagerState.currentPage) {
        val swipe = (calendarPagerState.currentPage - currentPage).toLong()
        currentDate = currentDate.plusMonths(swipe)
        currentPage = calendarPagerState.currentPage
    }

    Box(
        modifier = modifier
    ) {
        Column {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp, horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${currentDate.year}년",
                        style = TellingmeTheme.typography.head3Bold.copy(
                            color = Gray500,
                            fontSize = 18.sp
                        )
                    )
                    Image(
                        modifier = modifier.padding(start = 2.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.icon_caret_down),
                        colorFilter = ColorFilter.tint(Gray500),
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f))
                Box(
                    modifier = modifier
                        .background(
                            shape = RoundedCornerShape(48.dp),
                            color = Background200
                        )
                        .padding(horizontal = 16.dp, vertical = 7.5.dp)
                ) {
                    Text(
                        text = "오늘",
                        style = TellingmeTheme.typography.body2Bold.copy(
                            color = Gray600,
                            fontSize = 14.sp
                        )
                    )
                }
            }

            Box(
                modifier = modifier
                    .padding(vertical = 10.dp, horizontal = 20.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "${currentDate.monthValue}월",
                    style = TellingmeTheme.typography.head2Bold.copy(
                        color = Gray600,
                        fontSize = 20.sp
                    )
                )
            }
            Spacer(modifier = Modifier.size(4.dp))

            Column(
                modifier = modifier
                    .padding(horizontal = 12.5.dp)
            ) {
                VerticalPager(
                    state = calendarPagerState
                ) { page ->
                    Row(modifier) {
                        val dayOfWeek = listOf("일","월","화","수","목","금","토")
                        dayOfWeek.forEach { day ->
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 14.5.dp, horizontal = 18.dp)
                                    .weight(1f),
                                text = day,
                                textAlign = TextAlign.Center,
                                style = TellingmeTheme.typography.body2Regular.copy(
                                    color = if (day == "일") Error400 else Gray500,
                                    fontSize = 14.sp
                                )
                            )
                        }
                    }
                    Spacer(modifier = Modifier.size(4.dp))

                    val date = LocalDate.of(
                        CALENDAR_RANGE.startYear + page / 12,
                        page % 12 + 1,
                        1
                    )

                    val lastDay by remember { mutableIntStateOf(date.lengthOfMonth()) }    // 해당 달의 마지막 날
                    val firstDayOfWeek by remember { mutableIntStateOf(date.dayOfWeek.value) }  // 1일
                    val days by remember { mutableStateOf(IntRange(1, lastDay).toList()) }    // 해당 달의 총 일수 리스트

                    Column(modifier = modifier.fillMaxWidth()) {
                        LazyVerticalGrid(
                            modifier = modifier.fillMaxWidth(),
                            columns = GridCells.Fixed(7),
//                            verticalArrangement = Arrangement.spacedBy(24.dp)
                        ) {
                            var emptyCount = 0
                            // 1일이 시작하는 요일 전까지 공백 생성, 일요일부터 시작할 수 있도록 +1
                            for (i in 1 until firstDayOfWeek +1) {
                                Log.d("taag", "1")
                                item {
                                    Box(modifier = Modifier.fillMaxWidth())
                                }
                                emptyCount++
                            }

                            itemsIndexed(items = days) { index, day ->
                                val date = date.withDayOfMonth(day)

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (date.monthValue == uiState.today.monthValue
                                        && date.dayOfMonth == uiState.today.dayOfMonth) {
                                        Box(
                                            modifier = modifier
                                                .background(
                                                    shape = RoundedCornerShape(48.dp),
                                                    color = Primary400
                                                )
                                                .size(6.dp)
                                                .align(Alignment.TopCenter)
                                        )
                                    }

                                    Column {
                                        Text(
                                            modifier = modifier
                                                .padding(vertical = 13.dp),
                                            text = date.dayOfMonth.toString(),
                                            style = TellingmeTheme.typography.body1Bold.copy(
                                                color = if ((index + emptyCount) % 7 == 0) Error400 else Gray500,
                                                fontSize = 16.sp
                                            ),
                                            textAlign = TextAlign.Center
                                        )

                                        Spacer(modifier = Modifier.height(24.dp))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(bottom = 16.dp, end = 20.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            FloatingButton {
                navController.navigate(HomeDestinations.RECORD)
            }
        }
    }

}


object CALENDAR_RANGE {
    const val startYear = 2000
    const val lastYear = 2030
}

@Preview(showBackground = true)
@Composable
fun MySpaceScreenPreview() {
    MySpaceScreen(
        navController = rememberNavController()
    )
}

