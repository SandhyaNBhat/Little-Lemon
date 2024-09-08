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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import com.spcreations.littlelemon.Home
import com.spcreations.littlelemon.Onboarding
import com.spcreations.littlelemon.R
import com.spcreations.littlelemon.ui.theme.LittleLemonTheme


@Composable
fun ProfileScreen(sharedPreferences: SharedPreferences,
                  navController:NavHostController){
   // var firstName by remember { mutableStateOf("") }
    //var lastName by remember { mutableStateOf("") }
    //var emailId by remember { mutableStateOf("") }

    val context = LocalContext.current


    Column(

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        var firstName:String = sharedPreferences.getString("firstName","XXX").toString()
        var lastName:String = sharedPreferences.getString("lastName","XXX").toString()
        var emailId: String = sharedPreferences.getString("emailId","XXX").toString()



        Image(painter= painterResource(id = R.drawable.littlelemon_logo),
            contentDescription="Little Lemon Logo",
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp))


        Spacer(modifier = Modifier.height(20.dp))

        Text("Personal Information",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(16.dp)
        )

        Text("First Name",
            modifier= Modifier.align(Alignment.Start)
                .padding(start=16.dp),
            fontSize = 12.sp)

       TextField(
            value = firstName,
            onValueChange = { firstName = it },
            modifier= Modifier
                .align(Alignment.Start)
                .padding(start=16.dp),
            readOnly = true
        )

        Text("Last Name",
            modifier= Modifier.align(Alignment.Start)
                .padding(start=16.dp,top = 16.dp),
            fontSize = 12.sp)

    TextField(
            value = lastName,
            onValueChange = { lastName = it },
            modifier= Modifier
                .align(Alignment.Start)
                .padding(start=16.dp,)
        )


        Text("Email",
            modifier= Modifier.align(Alignment.Start)
                .padding(start=16.dp, top = 16.dp),
            fontSize = 12.sp)
       TextField(
            value = emailId,
            onValueChange = { emailId = it },
            modifier= Modifier
                .align(Alignment.Start)
                .padding(start=16.dp)
        )

        Button(onClick = {
            sharedPreferences.edit().clear().apply()
            navController.navigate(Onboarding.route)

        },
            colors = ButtonDefaults.buttonColors(Color(0xFFF4CE14)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text("Log out", color = Color.Black, fontSize =20.sp)

        }

    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    LittleLemonTheme {
        //ProfileScreen()
    }
}