package kynv1.fsoft.signupdesign

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kynv1.fsoft.signupdesign.ui.theme.SignupDesignTheme

private val String.isValidName: Boolean
    get() {
        return this.length > 3
    }

private val String.isValidEmail: Boolean
    get() {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

private val String.isValidPassword: Boolean
    get() {
        return this.length >= 8
    }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignupDesignTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xff101010)
                ) {
                    SignupScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(24.dp)
    ) {
        Row() {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(
                        shape = RoundedCornerShape(12.dp)
                    )
                    .background(
                        color = Color(0xff171717)
                    )
                    .border(
                        border = BorderStroke(
                            width = 2.dp,
                            color = Color(0xff3E3E3E)
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable {

                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }
            Spacer(Modifier.width(24.dp))
            Text(
                "Sign up",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )
        }
        Spacer(Modifier.height(48.dp))
        Text(
            text = stringResource(id = R.string.create_account),
            color = Color.White,
            fontSize = 15.sp
        )
        Spacer(Modifier.height(24.dp))
        Text(
            text = stringResource(id = R.string.label_name),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 9.dp)
        )
        Spacer(Modifier.height(13.dp))
        NameField()
        Spacer(Modifier.height(24.dp))
        Text(
            text = stringResource(id = R.string.label_email),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 9.dp)
        )
        Spacer(Modifier.height(13.dp))
        EmailField()
        Spacer(Modifier.height(18.dp))
        Text(
            text = stringResource(id = R.string.label_password),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 9.dp)
        )
        Spacer(Modifier.height(13.dp))
        PasswordField()
        /////////////////////////////////
        Spacer(modifier = Modifier.height(31.dp))
        val buttonPress = remember {
            mutableStateOf(false)
        }
        val buttonLeftColor = animateColorAsState(
            targetValue =
            if (buttonPress.value)
                Color(0xff480F63)
            else Color(0xff3C28C6),
            animationSpec = tween(
                durationMillis = 100,
                easing = LinearEasing
            )
        )
        val buttonRightColor = animateColorAsState(
            targetValue =
            if (buttonPress.value)
                Color(0xff3C28C6)
            else
                Color(0xff480F63),
            animationSpec = tween(
                durationMillis = 100,
                easing = LinearEasing
            )
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            buttonLeftColor.value,
                            buttonRightColor.value
                        )
                    )
                )
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = {
                            buttonPress.value = true
                        },
                        onTap = {
                            buttonPress.value = false
                        }
                    )
                },

            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.sign_up),
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                color = Color.White
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                color = Color.Gray,
                thickness = 2.dp,
            )
            Text(
                modifier = Modifier.padding(16.dp),
                text = stringResource(id = R.string.label_or),
                color = Color(0xffAAAAAA),
                fontSize = 12.sp
            )
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                color = Color.Gray,
                thickness = 2.dp,
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = Color(0xff171717))
                    .border(
                        width = 2.dp,
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xff252525)
                    )
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.google_logo),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                    tint = Color.White
                )
            }
            Spacer(Modifier.width(18.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = Color(0xff171717))
                    .border(
                        width = 2.dp,
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xff252525)
                    )
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.twitter),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                    tint = Color.White
                )
            }

        }
        Spacer(Modifier.height(12.dp))
        Row(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                stringResource(id = R.string.question_login),
                color = Color.Gray
            )
            TextButton(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color.White
                )
            ) {
                Text(
                    stringResource(id = R.string.label_log_in),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun NameField() {
    val name = remember {
        mutableStateOf("")
    }
    val nameFocused = remember {
        mutableStateOf(false)
    }
    val nameFieldUpperBorderColor = animateColorAsState(
        targetValue = if (nameFocused.value) Color(0xff5133FF) else Color(0xff282828),
        animationSpec = tween(durationMillis = 300, easing = LinearEasing)
    )

    val nameFieldBottomBorderColor = animateColorAsState(
        targetValue = if (nameFocused.value) Color(0xff100F6C) else Color(0xff282828),
        animationSpec = tween(durationMillis = 300, easing = LinearEasing)
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .clip(shape = RoundedCornerShape(12.dp))
            .background(color = Color(0xff171717))
            .border(
                width = 2.dp,
                brush = Brush.verticalGradient(
                    listOf(
                        nameFieldUpperBorderColor.value,
                        nameFieldBottomBorderColor.value
                    )
                ),
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 18.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                if (name.value.isEmpty()) {
                    Text(text = stringResource(id = R.string.input_name), color = Color.Gray)
                }
                BasicTextField(
                    value = name.value,
                    onValueChange = { newName ->
                        name.value = newName
                    },
                    textStyle = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    ),
                    cursorBrush = SolidColor(Color.White),
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged {
                            nameFocused.value = it.isFocused
                            Logger.lod("onFocusChange")
                        }
                )
            }
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(shape = RoundedCornerShape(6.dp))
                    .background(color = if (name.value.isValidName) Color(0xff282828) else Color.Transparent),
                contentAlignment = Alignment.Center
            ) {
                AnimatorVectorExample(name.value.isValidName)
            }
        }
    }
}

@Composable
fun EmailField() {
    val email = remember {
        mutableStateOf("")
    }
    val emailFocused = remember {
        mutableStateOf(false)
    }
    val emailFieldUpperBorderColor = animateColorAsState(
        targetValue = if (emailFocused.value) Color(0xff5133FF) else Color(0xff282828),
        animationSpec = tween(durationMillis = 300, easing = LinearEasing)
    )

    val emailFieldBottomBorderColor = animateColorAsState(
        targetValue = if (emailFocused.value) Color(0xff100F6C) else Color(0xff282828),
        animationSpec = tween(durationMillis = 300, easing = LinearEasing)
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .clip(shape = RoundedCornerShape(12.dp))
            .background(color = Color(0xff171717))
            .border(
                width = 2.dp,
                brush = Brush.verticalGradient(
                    listOf(
                        emailFieldUpperBorderColor.value,
                        emailFieldBottomBorderColor.value
                    )
                ),
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 18.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                if (email.value.isEmpty()) {
                    Text(text = stringResource(id = R.string.input_email), color = Color.Gray)
                }
                BasicTextField(
                    value = email.value,
                    onValueChange = { newName ->
                        email.value = newName
                    },
                    textStyle = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    ),
                    cursorBrush = SolidColor(Color.White),
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged {
                            emailFocused.value = it.isFocused
                            Logger.lod("onFocusChange")
                        },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    )
                )
            }
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(shape = RoundedCornerShape(6.dp))
                    .background(color = if (email.value.isValidEmail) Color(0xff282828) else Color.Transparent),
                contentAlignment = Alignment.Center
            ) {
                AnimatorVectorExample(email.value.isValidEmail)
            }
        }
    }
}

@Composable
fun PasswordField() {
    val email = remember {
        mutableStateOf("")
    }
    val emailFocused = remember {
        mutableStateOf(false)
    }
    val emailFieldUpperBorderColor = animateColorAsState(
        targetValue = if (emailFocused.value) Color(0xff5133FF) else Color(0xff282828),
        animationSpec = tween(durationMillis = 300, easing = LinearEasing)
    )

    val emailFieldBottomBorderColor = animateColorAsState(
        targetValue = if (emailFocused.value) Color(0xff100F6C) else Color(0xff282828),
        animationSpec = tween(durationMillis = 300, easing = LinearEasing)
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .clip(shape = RoundedCornerShape(12.dp))
            .background(color = Color(0xff171717))
            .border(
                width = 2.dp,
                brush = Brush.verticalGradient(
                    listOf(
                        emailFieldUpperBorderColor.value,
                        emailFieldBottomBorderColor.value
                    )
                ),
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 18.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                if (email.value.isEmpty()) {
                    Text(text = stringResource(id = R.string.input_password), color = Color.Gray)
                }
                BasicTextField(
                    value = email.value,
                    onValueChange = { newName ->
                        email.value = newName
                    },
                    textStyle = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    ),
                    cursorBrush = SolidColor(Color.White),
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged {
                            emailFocused.value = it.isFocused
                            Logger.lod("onFocusChange")
                        },
                    visualTransformation = PasswordVisualTransformation()
                )
            }
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(shape = RoundedCornerShape(6.dp))
                    .background(color = if (email.value.isValidPassword) Color(0xff282828) else Color.Transparent),
                contentAlignment = Alignment.Center
            ) {
                AnimatorVectorExample(email.value.isValidPassword)
            }
        }
    }
}

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun AnimatorVectorExample(validName: Boolean) {
    val painter = rememberAnimatedVectorPainter(
        animatedImageVector = AnimatedImageVector.animatedVectorResource(
            id = R.drawable.avd_anim_2
        ), atEnd = validName
    )
    Image(painter = painter, contentDescription = null, modifier = Modifier.size(18.dp))
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SignupDesignTheme {
        SignupScreen(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xff101010))
        )
    }
}