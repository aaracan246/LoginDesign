import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import jdk.jfr.Enabled

@Composable
@Preview
fun Login() {

    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val buttonEnabled = user.isNotBlank() && password.isNotBlank()
    var passVisible by remember { mutableStateOf(false) }

    MaterialTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp, alignment = Alignment.CenterVertically),
            modifier = Modifier.fillMaxSize()
        ){
            Usuario(user) { user = it }

            Password(password, passVisible, {password = it},{passVisible = it })

            Button (buttonEnabled){ user = ""; password = "" }

        }


    }
}
@Composable
fun Usuario(
    user: String,
    onUsuarioChange: (String) -> Unit
){
    OutlinedTextField(          // <- OutlinedTextField queda más bonito que TextField()
        value = user,
        onValueChange = onUsuarioChange,
        label = { Text(text = "Usuario") })
}

@Composable
fun Password(
    password: String,
    passVisible: Boolean,
    onPasswordChange: (String) -> Unit,
    onCheckedPasswdChange: (Boolean) -> Unit
){
    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text(text = "Contraseña") },
        visualTransformation = if (passVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconToggleButton(
                checked = passVisible,
                onCheckedChange = onCheckedPasswdChange
            ) {
                Icon(
                    imageVector = if (passVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
fun Button(onButtonEnabled: Boolean, onButtonReset: () -> Unit){
    Button(
        onClick = onButtonReset,
        enabled = onButtonEnabled
    ) {
        Text(text = "Login")
    }
}


fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Login") {
        Login()
    }
}
