package com.example.carappcompose.fragments

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button

import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.carappcompose.ui.theme.primaryColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FilterScreen(navController: NavController) {
      Column(modifier = Modifier.padding(10.dp)){
          val pagerState = rememberPagerState (
              pageCount = {3}
          )
          val coroutineScope = rememberCoroutineScope()
          TabRow(selectedTabIndex = pagerState.currentPage,
            containerColor = TabRowDefaults.containerColor,
            contentColor = TabRowDefaults.contentColor,
            divider = {},
            indicator = {tabPositions -> TabRowDefaults.Indicator(
               modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
               height = 2.dp,
               color = primaryColor
            ) }) {
            Tab(
              selected = pagerState.currentPage == 0,
              text = { Text(text = "All", color = Color(255, 87, 34, 255),fontWeight = FontWeight.Bold)},
              onClick = {
                  coroutineScope.launch { pagerState.scrollToPage(0) }
              }
            )
            Tab(
              selected = pagerState.currentPage == 1,
              text = { Text(text = "New",color = Color(255, 87, 34, 255),fontWeight = FontWeight.Bold)},
              onClick = { coroutineScope.launch { pagerState.scrollToPage(1) }}
            )
            Tab(
              selected = pagerState.currentPage == 2,
              text = { Text(text = "Used",color = Color(255, 87, 34, 255), fontWeight = FontWeight.Bold)},
              onClick = { coroutineScope.launch { pagerState.scrollToPage(2) }}
            )
          }
        HorizontalPager(state = pagerState, userScrollEnabled = true) {
            if (pagerState.currentPage == 0) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(onClick = { /*TODO*/ },) {
                        Text(
                            text = "Search",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
            if (pagerState.currentPage == 1) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(onClick = { /*TODO*/ },) {
                        Text(
                            text = "New",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
            if (pagerState.currentPage == 2) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(onClick = { /*TODO*/ },) {
                        Text(
                            text = "Used",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }

        }
      }
}

  


