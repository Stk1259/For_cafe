package com.example.forcafe.screens.Login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.forcafe.R
import com.example.forcafe.ui.theme.AppTheme
import com.example.forcafe.screens.Login.models.LoginEvents
import com.example.forcafe.screens.Login.models.LoginSubState
import com.example.forcafe.screens.Login.models.LoginViewState
import com.example.forcafe.screens.Login.views.ForgotView
import com.example.forcafe.screens.Login.views.SignInView
import com.example.forcafe.screens.Login.views.SignUpView

@Composable
fun LoginScreen(
     loginViewModel: LoginViewModel
){
    val viewState = loginViewModel.viewState.observeAsState(LoginViewState())
    with(viewState.value){
        LazyColumn(
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp)
        ){
            item {
                Text(
                    modifier = Modifier.padding(top = 32.dp, start = 16.dp),
                    text = when (loginSubState) {
                        LoginSubState.SignIn -> stringResource(id = R.string.sign_in_title)
                        LoginSubState.SignUp -> stringResource(id = R.string.sign_up_title)
                        LoginSubState.Forgot -> stringResource(id = R.string.forgot_title)
                    },
                    style = TextStyle(
                        color = AppTheme.colors.headerTextColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 50.sp
                    )
                )
            }
            item{
                Row(modifier = Modifier.padding(top = 17.dp)){
                    Text( text = when (loginSubState) {
                        LoginSubState.SignIn -> stringResource(id = R.string.sign_in_subtitle)
                        LoginSubState.SignUp -> stringResource(id = R.string.sign_up_subtitle)
                        LoginSubState.Forgot -> stringResource(id = R.string.forgot_subtitle)
                    },
                    style = TextStyle(
                        color = AppTheme.colors.primaryTextColor
                    )
                    )
                    Spacer(modifier = Modifier.width(4.dp))

                    if (loginSubState != LoginSubState.Forgot) {
                        Text(
                            modifier = Modifier.clickable {
                                when (loginSubState) {
                                    LoginSubState.SignIn -> loginViewModel.obtainEvent(LoginEvents.SignUpClicked)
                                    LoginSubState.SignUp -> loginViewModel.obtainEvent(LoginEvents.SignInClicked)
                                    LoginSubState.Forgot -> Unit
                                }
                            },
                            text = when (loginSubState) {
                                LoginSubState.SignIn -> stringResource(id = R.string.sign_in_action)
                                LoginSubState.SignUp -> stringResource(id = R.string.sign_up_action)
                                else -> ""
                            },
                            style = TextStyle(
                                color = AppTheme.colors.ActionTextColor,
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                }
            }
            item {
                when (loginSubState){
                    LoginSubState.SignIn -> SignInView(
                        viewState = this@with,
                        onTextFieldChanged = {
                            loginViewModel.obtainEvent(LoginEvents.EmailChanged(it))
                        }
                    )
                    LoginSubState.SignUp -> SignUpView()
                    LoginSubState.Forgot -> ForgotView()
                }
            }
        }

    }
}