package com.spcreations.littlelemon

import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.spcreations.littlelemon.ui.theme.LittleLemonTheme


@Composable
fun Onboarding(navController: NavHostController, sharedPreferences: SharedPreferences) {

    var firstName by remember { mutableStateOf("") }
    var lastName by remember {mutableStateOf("")}
    var emailId by remember {mutableStateOf("")}
    val textMeasure = rememberTextMeasurer()
    val headerText = "Let's get to know you"
    val textLayoutResult = remember(headerText){
        textMeasure.measure(headerText)
    }
    val context = LocalContext.current


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Image(painter= painterResource(id =R.drawable.littlelemon_logo),
            contentDescription="Little Lemon Logo",
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp))

        Canvas(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth(1f)
                .fillMaxHeight(0.25f)
        ) {
            val width = size.width
            val height = size.height
            drawRect(
                color = Color(0xFF4E6B61),
                size = Size(width, 300f),
                topLeft = Offset(0f, 100f))
            drawText(
                text= headerText,
                textMeasurer = textMeasure,
                topLeft = Offset(
                    x = width/2 - textLayoutResult.size.width,
                    y= height/2
                ),
                style = TextStyle(
                    fontSize = 28.sp,
                    color = Color.White
                )
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text("Personal Information",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(16.dp)
                )

       TextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("First Name") },
            modifier= Modifier
                .align(Alignment.Start)
                .padding(16.dp)
        )

      TextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Last Name") },
            modifier= Modifier
                .align(Alignment.Start)
                .padding(16.dp)
                  )

      TextField(
            value = emailId,
            onValueChange = { emailId = it },
            label = { Text("Email") },
            modifier= Modifier
                .align(Alignment.Start)
                .padding(16.dp)
        )

        Button(onClick = {
            if(firstName.isBlank() || lastName.isBlank() || emailId.isBlank()){
                    Toast.makeText(context,
                        "Registration unsuccessful. Please enter all data.",
                        Toast.LENGTH_SHORT).show()

            }else{
               sharedPreferences.edit()
                    .putString("firstName", firstName)
                    .putString("lastName",lastName)
                    .putString("emailId",emailId)
                    .apply()

                Toast.makeText(context,
                    "Registration successful!",
                    Toast.LENGTH_SHORT).show()

               navController.navigate(Home.route)



            }
        },
            colors = ButtonDefaults.buttonColors(Color(0xFFF4CE14)),
          modifier = Modifier
              .fillMaxWidth()
              .padding(20.dp)
        ) {
            Text("Register", color = Color.Black, fontSize =20.sp)

        }

    }

}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    LittleLemonTheme {
        //Onboarding(navController)
    }
}